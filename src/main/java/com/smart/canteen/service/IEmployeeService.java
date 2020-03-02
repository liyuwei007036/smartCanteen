package com.smart.canteen.service;

import com.lc.core.dto.User;
import com.smart.canteen.dto.LoginFormDTO;
import com.smart.canteen.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lc
 * @since 2020-03-01
 */
public interface IEmployeeService extends IService<Employee> {

    /**
     * 登录
     *
     * @param dto
     * @return
     */
    User login(LoginFormDTO dto);

    /**
     * 获取用户信息
     *
     * @param account
     * @return
     */
    Employee getByAccount(String account);
}
