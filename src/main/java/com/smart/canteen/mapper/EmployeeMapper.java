package com.smart.canteen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lc.core.dto.Account;
import com.smart.canteen.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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


    /**
     * 逻辑删除
     *
     * @param account
     * @param eid
     */
    void logicDeleted(@Param("account") Account account, @Param("eid") Long eid);

}
