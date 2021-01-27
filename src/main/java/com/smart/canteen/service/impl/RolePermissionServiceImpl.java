package com.smart.canteen.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.canteen.dto.role.PermissionForm;
import com.smart.canteen.entity.RolePermission;
import com.smart.canteen.mapper.RolePermissionMapper;
import com.smart.canteen.service.IRolePermissionService;
import com.smart.canteen.utils.EntityLogUtil;
import live.lumia.dto.Account;
import live.lumia.utils.DiffUtils;
import live.lumia.utils.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lc
 * @since 2020-03-11
 */
@Slf4j
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
        List<RolePermission> oldPermissions = list(Wrappers.<RolePermission>lambdaQuery()
                .eq(RolePermission::getRoleId, roleId))
                .parallelStream().collect(Collectors.toList());

        List<String> newPermissionCodes = new ArrayList<>(form.getPermissions());

        DiffUtils.DiffResult<RolePermission, String> result = DiffUtils.diffList(oldPermissions, newPermissionCodes, RolePermission::getPermissionCode, x -> x, (x, y) -> x.getPermissionCode().equals(y));

        List<String> addedList = result.getAddedList();

        List<RolePermission> deletedList = result.getDeletedList();

        List<RolePermission> newPermission = addedList.stream()
                .map(x -> {
                    RolePermission rp = new RolePermission();
                    rp.setRoleId(roleId);
                    rp.setPermissionCode(x);
                    EntityLogUtil.addNormalUser(rp, account);
                    return rp;
                }).collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(deletedList)) {
            removeByIds(deletedList.stream().map(RolePermission::getId).collect(Collectors.toList()));
        }
        if (!CollectionUtils.isEmpty(newPermission)) {
            saveBatch(newPermission);
        }
        log.info("新增 {} 条，删除 {} 条， 相同的 {} 条， 不同的 {} 条", addedList.size(), deletedList.size(), result.getSameMap().size(), result.getChangedMap().size());
    }
}
