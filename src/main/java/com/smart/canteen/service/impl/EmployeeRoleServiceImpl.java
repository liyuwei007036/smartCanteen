package com.smart.canteen.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.core.dto.Account;
import com.lc.core.utils.ObjectUtil;
import com.smart.canteen.entity.EmployeeRole;
import com.smart.canteen.mapper.EmployeeRoleMapper;
import com.smart.canteen.service.IEmployeeRoleService;
import com.smart.canteen.utils.EntityLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
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
        List<Long> old = getEmpRole(employeeId);
        old.removeAll(roleIds);
        roleIds.removeAll(old);
        if (old.size() > 0) {
            remove(Wrappers.<EmployeeRole>lambdaQuery()
                    .eq(EmployeeRole::getEmployeeId, employeeId)
                    .in(EmployeeRole::getRoleId, old));
        }
        Set<EmployeeRole> collect = roleIds.stream().map(x -> {
            EmployeeRole rp = new EmployeeRole();
            rp.setRoleId(x);
            rp.setEmployeeId(employeeId);
            EntityLogUtil.addNormalUser(rp, operator);
            return rp;
        }).collect(Collectors.toSet());
        if (collect.size() > 0) {
            saveBatch(collect);
        }
    }


    @Override
    public List<Map<String, Object>> getAllRoleByEmployee(Long employee) {
        return listMaps(Wrappers.<EmployeeRole>lambdaQuery().select(EmployeeRole::getRoleId).eq(EmployeeRole::getEmployeeId, employee));
    }


    @Override
    public List<Long> getEmpRole(Long empId) {
        List<Map<String, Object>> maps = listMaps(Wrappers.<EmployeeRole>lambdaQuery().select(EmployeeRole::getRoleId).eq(EmployeeRole::getEmployeeId, empId));
        return maps.parallelStream()
                .map(x -> ObjectUtil.getLong(x.get("roleId")))
                .filter(x -> x > 0).distinct().collect(Collectors.toList());
    }
}
