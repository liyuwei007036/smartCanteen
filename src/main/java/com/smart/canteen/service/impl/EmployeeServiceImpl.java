package com.smart.canteen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.core.controller.BaseController;
import com.lc.core.dto.User;
import com.lc.core.enums.BaseErrorEnums;
import com.lc.core.error.BaseException;
import com.lc.core.utils.EncryptionUtils;
import com.lc.core.utils.LoginUtils;
import com.lc.core.utils.ModelMapperUtils;
import com.smart.canteen.dto.employee.EmployeeForm;
import com.smart.canteen.dto.employee.EmployeeSearch;
import com.smart.canteen.dto.employee.ListEmployee;
import com.smart.canteen.dto.employee.LoginForm;
import com.smart.canteen.entity.Employee;
import com.smart.canteen.enums.CanteenExceptionEnum;
import com.smart.canteen.mapper.EmployeeMapper;
import com.smart.canteen.service.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;


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
    public void login(LoginForm dto, BaseController controller) {
        Employee employee = getByAccount(dto.getAccount());
        if (employee == null) {
            throw new BaseException(CanteenExceptionEnum.USER_NOT_EXIST);
        }
        String salt = employee.getSalt();
        String password = EncryptionUtils.md5(dto.getPassword(), salt, false);
        if (password.equals(employee.getPassword())) {
            User user = new User();
            user.setAccount(employee.getNo());
            user.setUName(employee.getName());
            user.setId(employee.getId());
            // todo 获取用户权限
            user.setPowers(new ArrayList<>());
            LoginUtils.doUserLogin(user, controller);
        } else {
            throw new BaseException(BaseErrorEnums.ERROR_AUTH);
        }
    }

    @Override
    public void add(EmployeeForm dto, User creator) {
        Employee employee = getByAccount(dto.getNo());
        if (employee != null) {
            throw new BaseException(CanteenExceptionEnum.ACCOUNT_REPEAT);
        }
        employee = ModelMapperUtils.strict(dto, Employee.class);
        init(employee, creator);
        String password = dto.getPassword();
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        password = EncryptionUtils.md5(password, salt, false);
        employee.setSalt(salt);
        employee.setPassword(password);
        boolean save = save(employee);
        if (!save) {
            throw new BaseException(CanteenExceptionEnum.CREATE_FAIL);
        }
    }

    @Override
    public void init(Employee employee, User updater) {
        if (employee == null || updater == null) {
            throw new BaseException(BaseErrorEnums.SYSTEM_ERROR);
        }
        LocalDateTime now = LocalDateTime.now();
        employee.setCreateTime(now);
        employee.setCreatorId(updater.getId());
        employee.setCreatorNo(updater.getAccount());
        employee.setCreatorName(updater.getUName());
        employee.setLastUpdateId(updater.getId());
        employee.setLastUpdateName(updater.getUName());
        employee.setLastUpdateNo(updater.getAccount());
        employee.setLastUpdateTime(now);
    }

    @Override
    public void update(EmployeeForm employee, User updater) {
        Long id = employee.getId();
        Employee byId = getById(id);
        if (byId == null) {
            throw new BaseException(CanteenExceptionEnum.USER_NOT_EXIST);
        }
        byId.setName(employee.getName());
        byId.setNo(employee.getNo());
        String password = employee.getPassword();
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        password = EncryptionUtils.md5(password, salt, false);
        byId.setSalt(salt);
        byId.setPassword(password);
        init(byId, updater);
        boolean b = updateById(byId);
        if (!b) {
            throw new BaseException(CanteenExceptionEnum.UPDATE_FAIL);
        }
    }

    @Override
    public void delete(Long id, User updater) {
        Employee employee = getById(id);
        if (employee == null) {
            throw new BaseException(CanteenExceptionEnum.USER_NOT_EXIST);
        }
        init(employee, updater);
        employee.setDeleted(true);
        updateById(employee);
    }

    @Override
    public Employee getByAccount(String account) {
        return getOne(new LambdaQueryWrapper<Employee>().eq(Employee::getNo, account), false);
    }

    @Override
    public ListEmployee listByConditional(EmployeeSearch form) {
        Page<Employee> page = new Page<>(form.getPage(), form.getSize());
        super.page(page, new LambdaQueryWrapper<Employee>()
                .likeLeft(!StringUtils.isEmpty(form.getMobile()), Employee::getMobile, form.getMobile())
                .likeLeft(!StringUtils.isEmpty(form.getName()), Employee::getName, form.getName())
                .likeLeft(!StringUtils.isEmpty(form.getNo()), Employee::getNo, form.getNo())

        );
        return new ListEmployee(page.hasNext(), page.getTotal(), page.getCurrent(), page.getRecords());
    }
}
