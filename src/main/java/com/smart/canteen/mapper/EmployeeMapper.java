package com.smart.canteen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import live.lumia.dto.Account;
import com.smart.canteen.dto.employee.EmployeeSearch;
import com.smart.canteen.entity.Employee;
import com.smart.canteen.vo.EmployeeVO;
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

    /**
     * 查询用户列表
     *
     * @param page
     * @param search
     * @return
     */
    IPage<EmployeeVO> listEmp(Page<?> page, @Param("search") EmployeeSearch search);


}
