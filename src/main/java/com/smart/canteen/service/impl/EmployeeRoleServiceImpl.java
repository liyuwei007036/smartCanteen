package com.smart.canteen.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.core.dto.Account;
import com.lc.core.error.BaseException;
import com.lc.core.utils.ObjectUtil;
import com.smart.canteen.entity.EmployeeRole;
import com.smart.canteen.enums.CanteenExceptionEnum;
import com.smart.canteen.mapper.EmployeeRoleMapper;
import com.smart.canteen.service.IEmployeeRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 员工角色关系 服务实现类
 * </p>
 *
 * @author lc
 * @since 2020-03-05
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service
public class EmployeeRoleServiceImpl extends ServiceImpl<EmployeeRoleMapper, EmployeeRole> implements IEmployeeRoleService {

    @Override
    public void batchAdd(List<Long> roleIds, Long employeeId, Account operator) {
        if (roleIds == null || roleIds.isEmpty()) {
            return;
        }
        List<Map<String, Object>> maps = getAllRoleByEmployee(employeeId);
        List<Long> oldIds = new ArrayList<>();
        maps.forEach(x -> {
            Object roleId = x.getOrDefault("role_id", 0);
            oldIds.add(ObjectUtil.getLong(roleId));
        });
        List<Long> addIds = roleIds.parallelStream().filter(x -> !oldIds.contains(x)).collect(Collectors.toList());
        List<Long> removeIds = oldIds.parallelStream().filter(x -> !roleIds.contains(x)).collect(Collectors.toList());
        Long adds = batchInsert(addIds, employeeId, operator);
        log.info("添加条数:{},实际添加条数{}", addIds.size(), adds);

        adds = batchDelete(addIds, employeeId, operator);
        log.info("删除条数:{},实际删除条数{}", removeIds.size(), adds);

    }


    @Override
    public List<Map<String, Object>> getAllRoleByEmployee(Long employee) {
        return listMaps(Wrappers.<EmployeeRole>lambdaQuery().select(EmployeeRole::getRoleId).eq(EmployeeRole::getEmployeeId, employee));
    }

    @Override
    public Long batchDelete(List<Long> roleIds, Long employeeId, Account operator) {
        return getBaseMapper().batchDeleted(roleIds, employeeId, operator);
    }

    @Override
    public Long batchInsert(List<Long> roleIds, Long employeeId, Account operator) {
        return getBaseMapper().batchAdd(roleIds, employeeId, operator);
    }
}
