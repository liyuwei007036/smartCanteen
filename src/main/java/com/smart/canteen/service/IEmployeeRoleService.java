package com.smart.canteen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.core.dto.Account;
import com.smart.canteen.entity.EmployeeRole;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 员工角色关系 服务类
 * </p>
 *
 * @author lc
 * @since 2020-03-05
 */
public interface IEmployeeRoleService extends IService<EmployeeRole> {

    /**
     * 批量保存
     *
     * @param roleIds
     * @param employeeId
     * @param operator
     * @return
     */
    void batchAdd(List<Long> roleIds, Long employeeId, Account operator);

    /**
     * 查询用户的所有角色
     *
     * @param employee
     * @return
     */
    List<Map<String, Object>> getAllRoleByEmployee(Long employee);

    /**
     * 批量删除
     *
     * @param roleIds
     * @param employeeId
     * @param operator
     * @return
     */
    Long batchDelete(List<Long> roleIds, Long employeeId, Account operator);

    /**
     * 批量插入
     *
     * @param roleIds
     * @param employeeId
     * @param operator
     * @return
     */
    Long batchInsert(List<Long> roleIds, Long employeeId, Account operator);
}
