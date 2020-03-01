package com.smart.canteen.service.impl;

import com.smart.canteen.entity.Employee;
import com.smart.canteen.mapper.EmployeeMapper;
import com.smart.canteen.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lc
 * @since 2020-03-01
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
