package com.smart.canteen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.core.dto.Account;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.role.RoleForm;
import com.smart.canteen.dto.role.RoleSearch;
import com.smart.canteen.entity.Role;

import java.util.List;

/**
 * <p>
 * 岗位 服务类
 * </p>
 *
 * @author lc
 * @since 2020-03-02
 */
public interface IRoleService extends IService<Role> {

    /**
     * 新增
     *
     * @param dto
     * @param creator
     */
    void add(RoleForm dto, Account creator);


    /**
     * 修改
     *
     * @param user
     * @param updater
     */
    void update(RoleForm user, Account updater);

    /**
     * 删除
     *
     * @param id
     * @param updater
     */
    void delete(Long id, Account updater);

    /**
     * 条件查询
     *
     * @param search
     * @return
     */
    CommonList<Role> listByConditional(RoleSearch search);

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    Role getById(Long id);

    /**
     * 通过名称查询
     *
     * @param name
     * @return
     */
    Role getByName(String name);

    /**
     * 查询所有角色
     *
     * @param select
     * @return
     */
    List<Role> listAll(Boolean select);


}
