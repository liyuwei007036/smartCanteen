package com.smart.canteen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.core.controller.BaseController;
import com.lc.core.dto.Account;
import com.smart.canteen.dto.employee.ListEmployee;
import com.smart.canteen.dto.user.ListUser;
import com.smart.canteen.dto.user.LoginForm;
import com.smart.canteen.dto.user.UserForm;
import com.smart.canteen.dto.user.UserSearch;
import com.smart.canteen.entity.Employee;
import com.smart.canteen.entity.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lc
 * @since 2020-03-03
 */
public interface IUserService extends IService<User> {


    /**
     * 登录
     *
     * @param dto
     * @param controller
     */
    void login(LoginForm dto, BaseController controller);

    /**
     * 获取用户信息
     *
     * @param account
     * @return
     */
    User getByAccount(String account);

    /**
     * 新增
     *
     * @param dto
     * @param creator
     */
    void add(UserForm dto, Account creator);


    /**
     * 修改
     *
     * @param user
     * @param updater
     */
    void update(UserForm user, Account updater);

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
    ListUser listByConditional(UserSearch search);

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    User getById(Long id);


}
