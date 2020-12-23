package com.smart.canteen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import live.lumia.dto.Account;
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
     * 获取用户所有角色
     *
     * @param empId
     * @return
     */
    List<String> getEmpRoleName(@Param("empId") Long empId);

}
