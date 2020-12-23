package com.smart.canteen.service;

import live.lumia.dto.Account;
import com.smart.canteen.dto.role.PermissionForm;
import com.smart.canteen.entity.Permission;
import com.smart.canteen.entity.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lc
 * @since 2020-03-11
 */
public interface IRolePermissionService extends IService<RolePermission> {

    /**
     * 获取单个角色的权限
     *
     * @param id
     * @return
     */
    Set<String> getRolePermission(Long id);

    /**
     * 获取多个角色的权限
     *
     * @param ids
     * @return
     */
    Set<String> getRolePermission(List<Long> ids);

    /**
     * 添加权限
     *
     * @param form
     * @param account
     */
    void addPermission(PermissionForm form, Account account);


}
