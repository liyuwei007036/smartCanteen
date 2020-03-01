package com.smart.canteen.mapper;

import com.smart.canteen.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2020-03-01
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
