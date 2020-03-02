package com.smart.canteen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.core.dto.User;
import com.lc.core.error.BaseErrorEnums;
import com.lc.core.error.BaseException;
import com.lc.core.utils.EncryptionUtils;
import com.smart.canteen.dto.LoginFormDTO;
import com.smart.canteen.entity.Employee;
import com.smart.canteen.mapper.EmployeeMapper;
import com.smart.canteen.service.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Override
    public User login(LoginFormDTO dto) {
        Employee employee = getByAccount(dto.getAccount());
        String salt = employee.getSalt();
        try {
            String password = EncryptionUtils.md5(dto.getPassWord(), salt, false);
            if (password.equals(employee.getPassword())) {
                User user = new User();
                user.setAccount(employee.getNo());
                user.setUName(employee.getName());
                user.setId(employee.getId());
                // todo
                return user;
            } else {
                throw new BaseException(BaseErrorEnums.ERROR_AUTH);
            }
        } catch (Exception e) {
            throw new BaseException(BaseErrorEnums.ERROR_AUTH);
        }
    }

    @Override
    public Employee getByAccount(String account) {
        return getOne(new LambdaQueryWrapper<Employee>().eq(Employee::getNo, account), false);
    }
}
