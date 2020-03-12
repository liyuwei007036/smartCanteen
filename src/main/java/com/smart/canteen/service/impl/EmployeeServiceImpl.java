package com.smart.canteen.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.core.controller.BaseController;
import com.lc.core.dto.Account;
import com.lc.core.error.BaseException;
import com.lc.core.utils.*;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.Password;
import com.smart.canteen.dto.card.CardForm;
import com.smart.canteen.dto.employee.EmployeeForm;
import com.smart.canteen.dto.employee.EmployeeSearch;
import com.smart.canteen.dto.user.ChangePasswordForm;
import com.smart.canteen.dto.user.LoginForm;
import com.smart.canteen.entity.Employee;
import com.smart.canteen.entity.IcCard;
import com.smart.canteen.enums.CanteenExceptionEnum;
import com.smart.canteen.enums.EmployeeStatusEnum;
import com.smart.canteen.mapper.EmployeeMapper;
import com.smart.canteen.service.*;
import com.smart.canteen.utils.EntityLogUtil;
import com.smart.canteen.vo.EmployeeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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

    @Autowired
    private IEmployeeRoleService iEmployeeRoleService;

    @Autowired
    private IRolePermissionService iRolePermissionService;

    @Autowired
    private IIcCardService iIcCardService;

    @Override
    public Account login(LoginForm dto, BaseController controller) {
        ValidatorUtil.validator(dto);
        Employee user = getByAccount(dto.getAccount());
        if (user == null || user.getStatus() != EmployeeStatusEnum.ENABLE) {
            throw new BaseException(CanteenExceptionEnum.USER_NOT_EXIST);
        }
        String salt = user.getSalt();
        String password = EncryptionUtils.md5(dto.getPassword(), salt, false);
        if (password.equals(user.getPassword())) {
            Account account = new Account();
            account.setAccount(user.getNo());
            account.setName(user.getName());
            account.setId(user.getId());
            List<Long> empRole = iEmployeeRoleService.getEmpRole(user.getId());
            account.setPowers(new ArrayList<>(iRolePermissionService.getRolePermission(empRole)));
            LoginUtils.doUserLogin(account, controller);
            return account;
        } else {
            throw new BaseException(CanteenExceptionEnum.USER_NOT_EXIST);
        }

    }

    @Override
    public void add(EmployeeForm dto, Account creator) {
        ValidatorUtil.validator(dto, EmployeeForm.Insert.class);
        Employee employee = matchAny(dto.getIdCard(), dto.getMobile(), dto.getNo());
        if (employee != null) {
            throw new BaseException(CanteenExceptionEnum.ACCOUNT_REPEAT);
        }
        employee = ModelMapperUtils.strict(dto, Employee.class);
        employee.setStatus(EmployeeStatusEnum.ENABLE);
        EntityLogUtil.addNormalUser(employee, creator);
        Password password = createPassword(dto.getPassword(), dto.getConfirmPassword());
        if (password != null) {
            employee.setSalt(password.getSalt());
            employee.setPassword(password.getPassword());
        }
        boolean save = save(employee);
        if (!save) {
            throw new BaseException(CanteenExceptionEnum.CREATE_FAIL);
        }
        iEmployeeRoleService.batchAdd(dto.getRoles(), employee.getId(), creator);
        CardForm strict = ModelMapperUtils.strict(dto, CardForm.class);
        strict.setNo(dto.getCardNo());
        employee.setCardId(iIcCardService.addCard(strict, employee, creator));
        updateById(employee);
    }

    private Password createPassword(String password, String conformPassword) {
        password = ObjectUtil.getString(password).trim();
        conformPassword = ObjectUtil.getString(conformPassword).trim();
        if (password.equals(conformPassword)) {
            if (StringUtils.isEmpty(password)) {
                return null;
            } else {
                return new Password(password);
            }
        } else {
            throw new BaseException(CanteenExceptionEnum.PASSWORD_NOT_SAME);
        }
    }


    @Override
    public void update(EmployeeForm employee, Account updater) {
        ValidatorUtil.validator(employee, EmployeeForm.Update.class);
        Long id = employee.getId();
        Employee byId = getById(id);
        if (byId == null) {
            throw new BaseException(CanteenExceptionEnum.USER_NOT_EXIST);
        }
        if (byId.getStatus() != EmployeeStatusEnum.ENABLE) {
            throw new BaseException(CanteenExceptionEnum.USER_IS_QUIT);
        }
        Employee other = matchAny(employee.getIdCard(), employee.getMobile(), employee.getNo());
        if (other != null && !other.getId().equals(id)) {
            throw new BaseException(CanteenExceptionEnum.ACCOUNT_REPEAT);
        }
        byId.setName(employee.getName());
        byId.setNo(employee.getNo());
        BeanUtils.copyProperties(employee, byId, "id", "password", "no");
        Password password = createPassword(employee.getPassword(), employee.getConfirmPassword());
        if (password != null) {
            byId.setSalt(password.getSalt());
            byId.setPassword(password.getPassword());
        }
        EntityLogUtil.addNormalUser(byId, updater);
        boolean b = updateById(byId);
        if (!b) {
            throw new BaseException(CanteenExceptionEnum.UPDATE_FAIL);
        }
        iEmployeeRoleService.batchAdd(employee.getRoles(), employee.getId(), updater);
        CardForm strict = ModelMapperUtils.strict(employee, CardForm.class);
        strict.setNo(employee.getCardNo());
        strict.setValidityTime(employee.getValidityTime());
        strict.setId(employee.getCardId());
        iIcCardService.update(strict, updater);
    }

    @Override
    public void delete(Long id, Account updater) {
        Employee employee = getById(id);
        if (employee == null) {
            throw new BaseException(CanteenExceptionEnum.USER_NOT_EXIST);
        }
        getBaseMapper().logicDeleted(updater, id);
    }

    @Override
    public Employee getByAccount(String account) {
        return getOne(Wrappers.<Employee>lambdaQuery().eq(Employee::getNo, account), false);
    }


    @Override
    public Employee matchAny(String idCard, String mobile, String no) {
        return getOne(Wrappers.<Employee>lambdaQuery()
                        .or().eq(!StringUtils.isEmpty(no), Employee::getNo, no)
                        .or().eq(!StringUtils.isEmpty(mobile), Employee::getMobile, mobile)
                        .or().eq(!StringUtils.isEmpty(idCard), Employee::getIdCard, idCard),
                false);
    }

    @Override
    public CommonList<EmployeeVO> listByConditional(EmployeeSearch form) {
        ValidatorUtil.validator(form);
        Page<EmployeeVO> page = new Page<>(form.getPage(), form.getSize());
        getBaseMapper().listEmp(page, form);
        return new CommonList<>(page.hasNext(), page.getTotal(), page.getCurrent(), page.getRecords());
    }

    @Override
    public Employee getById(Long id) {
        return super.getById(id);
    }

    @Override
    public EmployeeVO getEmpInfo(Long id) {
        Employee employee = getById(id);
        if (employee == null) {
            throw new BaseException(CanteenExceptionEnum.USER_NOT_EXIST);
        }
        EmployeeVO vo = ModelMapperUtils.strict(employee, EmployeeVO.class);
        IcCard card = iIcCardService.getById(employee.getCardId());
        if (card != null) {
            vo.setCardId(card.getId());
            vo.setCardNo(card.getNo());
            vo.setType(card.getType());
            vo.setExpense(card.getExpense());
            vo.setDeposit(card.getDeposit());
            vo.setOpenCardAmount(card.getOpenCardAmount());
            vo.setMinimumBalance(card.getMinimumBalance());
            vo.setValidityTime(card.getValidityTime());
        }
        vo.setRoles(iEmployeeRoleService.getEmpRole(id));
        return vo;
    }


    @Override
    public void changePassword(ChangePasswordForm form, Account account) {
        ValidatorUtil.validator(form);
        Employee employee = getById(account.getId());
        if (employee == null) {
            throw new BaseException(CanteenExceptionEnum.USER_NOT_EXIST);
        }
        String salt = employee.getSalt();
        String password = EncryptionUtils.md5(form.getPassword(), salt, false);
        if (password.equals(employee.getPassword())) {
            Password password1 = new Password(form.getPassword());
            employee.setPassword(password1.getPassword());
            employee.setSalt(password1.getSalt());
            EntityLogUtil.addNormalUser(employee, account);
            updateById(employee);
        } else {
            throw new BaseException(CanteenExceptionEnum.PASSWORD_ERROR);
        }
    }
}
