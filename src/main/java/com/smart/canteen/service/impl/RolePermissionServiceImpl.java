package com.smart.canteen.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import live.lumia.dto.Account;
import live.lumia.utils.ValidatorUtil;
import com.smart.canteen.dto.role.PermissionForm;
import com.smart.canteen.entity.RolePermission;
import com.smart.canteen.mapper.RolePermissionMapper;
import com.smart.canteen.service.IRolePermissionService;
import com.smart.canteen.utils.EntityLogUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lc
 * @since 2020-03-11
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

    @Override
    public Set<String> getRolePermission(Long id) {
        return list(Wrappers.<RolePermission>lambdaQuery()
                .select(RolePermission::getPermissionCode)
                .eq(RolePermission::getRoleId, id))
                .parallelStream().map(RolePermission::getPermissionCode)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getRolePermission(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return new HashSet<>();
        }
        return list(Wrappers.<RolePermission>lambdaQuery()
                .select(RolePermission::getPermissionCode)
                .in(RolePermission::getRoleId, ids))
                .parallelStream()
                .map(RolePermission::getPermissionCode)
                .collect(Collectors.toSet());
    }

    @Override
    public void addPermission(PermissionForm form, Account account) {
        ValidatorUtil.validator(form);
        Long roleId = form.getId();
        Set<String> oldPermissions = getRolePermission(roleId);
        Set<String> permissions = form.getPermissions();
        if (oldPermissions.equals(permissions)) {
            return;
        }
        if (oldPermissions.size() > 0) {
            remove(Wrappers.<RolePermission>lambdaQuery()
                    .eq(RolePermission::getRoleId, form.getId())
                    .in(RolePermission::getPermissionCode, oldPermissions));
        }
        Set<RolePermission> collect = permissions.stream().map(x -> {
            RolePermission rp = new RolePermission();
            rp.setRoleId(roleId);
            rp.setPermissionCode(x);
            EntityLogUtil.addNormalUser(rp, account);
            return rp;
        }).collect(Collectors.toSet());
        if (collect.size() > 0) {
            saveBatch(collect);
        }
    }
}
