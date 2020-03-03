package com.smart.canteen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.core.controller.BaseController;
import com.lc.core.dto.Account;
import com.smart.canteen.dto.employee.EmployeeForm;
import com.smart.canteen.dto.employee.EmployeeSearch;
import com.smart.canteen.dto.employee.ListEmployee;
import com.smart.canteen.dto.user.LoginForm;
import com.smart.canteen.entity.Employee;

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
     * 获取用户信息
     *
     * @param account
     * @return
     */
    Employee getByAccount(String account);

    /**
     * 新增
     *
     * @param dto
     * @param creator
     */
    void add(EmployeeForm dto, Account creator);

    /**
     * 修改
     *
     * @param employee
     * @param updater
     */
    void update(EmployeeForm employee, Account updater);

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
     * @param form
     * @return
     */
    ListEmployee listByConditional(EmployeeSearch form);

    /**
     * 通过idCard mobile no 查询
     *
     * @param idCard
     * @param mobile
     * @param no
     * @return
     */
    Employee matchAny(String idCard, String mobile, String no);

    /**
     * 通过Id查询
     *
     * @param id
     * @return
     */
    Employee getById(Long id);

}
