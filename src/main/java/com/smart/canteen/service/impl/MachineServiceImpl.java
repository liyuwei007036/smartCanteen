package com.smart.canteen.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.core.dto.Account;
import com.lc.core.error.BaseException;
import com.lc.core.utils.ModelMapperUtils;
import com.lc.core.utils.ValidatorUtil;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.machine.MachineDTO;
import com.smart.canteen.dto.machine.MachineSearch;
import com.smart.canteen.entity.Machine;
import com.smart.canteen.enums.CanteenExceptionEnum;
import com.smart.canteen.mapper.MachineMapper;
import com.smart.canteen.service.IMachineService;
import com.smart.canteen.utils.EntityLogUtil;
import com.smart.canteen.vo.MachineVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 卡机 服务实现类
 * </p>
 *
 * @author lc
 * @since 2020-03-25
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class MachineServiceImpl extends ServiceImpl<MachineMapper, Machine> implements IMachineService {

    @Override
    public void add(MachineDTO dto, Account operator) {
        ValidatorUtil.validator(dto, MachineDTO.Insert.class);
        Machine machine = getByNameOrCode(dto.getName(), dto.getCode());
        if (machine != null) {
            throw new BaseException(CanteenExceptionEnum.CODE_OR_NAME_REPEAT);
        }
        machine = ModelMapperUtils.strict(dto, Machine.class);
        EntityLogUtil.addNormalUser(machine, operator);
        save(machine);
    }

    @Override
    public void edit(MachineDTO dto, Account operator) {
        ValidatorUtil.validator(dto, MachineDTO.Update.class);
        Machine machine = getById(dto.getId());
        if (machine == null) {
            throw new BaseException(CanteenExceptionEnum.MACHINE_NOT_EXITS);
        }
        Machine machine2 = getByNameOrCode(dto.getName(), dto.getCode());
        if (machine2 != null && !machine2.getId().equals(machine.getId())) {
            throw new BaseException(CanteenExceptionEnum.CODE_OR_NAME_REPEAT);
        }
        BeanUtils.copyProperties(dto, machine);
        EntityLogUtil.addNormalUser(machine, operator);
        updateById(machine);
    }

    @Override
    public void delete(Long id, Account operator) {
        Machine machine = getById(id);
        if (machine == null) {
            throw new BaseException(CanteenExceptionEnum.MACHINE_NOT_EXITS);
        }
        getBaseMapper().logicDeleted(operator, id);
    }

    @Override
    public Machine getById(Long id) {
        return super.getById(id);
    }

    @Override
    public Machine getByCode(String code) {
        return getOne(Wrappers.<Machine>lambdaQuery().eq(Machine::getCode, code), false);
    }

    @Override
    public Machine getByNameOrCode(String name, String code) {
        return getOne(Wrappers.<Machine>lambdaQuery().or().eq(Machine::getCode, code)
                .or().eq(Machine::getName, name), false);
    }

    @Override
    public CommonList<MachineVO> page(MachineSearch search) {
        Page<Machine> page = new Page<>(search.getPage(), search.getSize());
        page(page, Wrappers.<Machine>lambdaQuery()
                .orderByDesc(Machine::getCreateTime)
        );
        return new CommonList<>(page.hasNext(), page.getTotal(), page.getCurrent(),
                page.getRecords().stream().map(x -> ModelMapperUtils.strict(x, MachineVO.class)).collect(Collectors.toList()));
    }

    @Override
    public Map<String, String> mapByNo(List<String> nos) {
        if (nos == null || nos.isEmpty()) {
            return new HashMap<>(1);
        }
        return list(Wrappers.<Machine>lambdaQuery()
                .select(Machine::getName, Machine::getCode)
                .in(Machine::getCode, nos)
        ).parallelStream().collect(Collectors.toMap(Machine::getCode, Machine::getName));
    }
}
