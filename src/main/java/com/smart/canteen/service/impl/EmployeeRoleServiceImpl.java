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
            Object roleId = x.getOrDefault("roleId", 0);
            oldIds.add(ObjectUtil.getLong(roleId));
        });
        List<Long> addIds = roleIds.parallelStream().filter(x -> !oldIds.contains(x)).collect(Collectors.toList());
        List<Long> removeIds = oldIds.parallelStream().filter(x -> !roleIds.contains(x)).collect(Collectors.toList());
        batchInsert(addIds, employeeId, operator);
        Long delete = batchDelete(removeIds, employeeId, operator);
        log.info("删除条数:{},实际删除条数{}", removeIds.size(), delete);
        if (removeIds.size() != delete) {
            throw new BaseException(CanteenExceptionEnum.ADD_ROLE_FAIL);
        }
    }


    @Override
    public List<Map<String, Object>> getAllRoleByEmployee(Long employee) {
        return listMaps(Wrappers.<EmployeeRole>lambdaQuery().select(EmployeeRole::getRoleId).eq(EmployeeRole::getEmployeeId, employee));
    }

    @Override
    public Long batchDelete(List<Long> roleIds, Long employeeId, Account operator) {
        if (roleIds != null && roleIds.size() > 1) {
            return getBaseMapper().batchDeleted(roleIds, employeeId, operator);
        }
        return 0L;
    }

    @Override
    public void batchInsert(List<Long> roleIds, Long employeeId, Account operator) {
        if (roleIds != null && roleIds.size() > 1) {
            getBaseMapper().batchAdd(roleIds, employeeId, operator);
        }
    }

    @Override
    public List<Long> getEmpRole(Long empId) {
        List<Map<String, Object>> maps = listMaps(Wrappers.<EmployeeRole>lambdaQuery().select(EmployeeRole::getRoleId).eq(EmployeeRole::getEmployeeId, empId));
        return maps.parallelStream()
                .map(x -> ObjectUtil.getLong(x.get("roleId")))
                .filter(x -> x > 0).distinct().collect(Collectors.toList());
    }
}
