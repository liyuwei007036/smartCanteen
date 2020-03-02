package com.smart.canteen.service;

import com.smart.canteen.entity.EmployeeRole;
import com.smart.canteen.mapper.EmployeeRoleMapper;
import com.smart.canteen.service.IEmployeeRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工角色关系 服务实现类
 * </p>
 *
 * @author lc
 * @since 2020-03-02
 */
@Service
public class EmployeeRoleServiceImpl extends ServiceImpl<EmployeeRoleMapper, EmployeeRole> implements IEmployeeRoleService {

}
