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



}
