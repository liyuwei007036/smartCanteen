package com.smart.canteen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lc.core.dto.Account;
import com.smart.canteen.entity.EmployeeRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 员工角色关系 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2020-03-05
 */
@Mapper
public interface EmployeeRoleMapper extends BaseMapper<EmployeeRole> {

    /**
     * 批量添加
     *
     * @param roleIds
     * @param eid
     * @param operator
     * @return
     */
    Long batchAdd(@Param("roleIds") List<Long> roleIds, @Param("eid") Long eid, @Param("operator") Account operator);


    /**
     * 批量逻辑删除
     *
     * @param roleIds
     * @param eid
     * @param operator
     * @return
     */
    Long batchDeleted(@Param("roleIds") List<Long> roleIds, @Param("eid") Long eid, @Param("operator") Account operator);


    /**
     * 获取员工角色
     *
     * @param empId
     * @return
     */
    List<Map<String, Object>> getEmpRole(@Param("empId") Long empId);

}
