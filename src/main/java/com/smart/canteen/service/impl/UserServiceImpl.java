package com.smart.canteen.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.core.controller.BaseController;
import com.lc.core.dto.Account;
import com.lc.core.enums.BaseErrorEnums;
import com.lc.core.error.BaseException;
import com.lc.core.utils.EncryptionUtils;
import com.lc.core.utils.LoginUtils;
import com.lc.core.utils.ModelMapperUtils;
import com.lc.core.utils.ValidatorUtil;
import com.smart.canteen.dto.Password;
import com.smart.canteen.dto.employee.ListEmployee;
import com.smart.canteen.dto.user.ListUser;
import com.smart.canteen.dto.user.LoginForm;
import com.smart.canteen.dto.user.UserForm;
import com.smart.canteen.dto.user.UserSearch;
import com.smart.canteen.entity.Employee;
import com.smart.canteen.entity.User;
import com.smart.canteen.enums.CanteenExceptionEnum;
import com.smart.canteen.mapper.UserMapper;
import com.smart.canteen.service.IUserService;
import com.smart.canteen.utils.EntityLogUtil;
import org.springframework.stereotype.Service;
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
 * @since 2020-03-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public void login(LoginForm dto, BaseController controller) {
        ValidatorUtil.validator(dto);
        User user = getByAccount(dto.getAccount());
        if (user == null) {
            throw new BaseException(CanteenExceptionEnum.USER_NOT_EXIST);
        }
        String salt = user.getSalt();
        String password = EncryptionUtils.md5(dto.getPassword(), salt, false);
        if (password.equals(user.getPassword())) {
            Account account = new Account();
            account.setAccount(user.getAccount());
            account.setName(user.getName());
            account.setId(user.getId());
            // todo 获取用户权限
            account.setPowers(new ArrayList<>());
            LoginUtils.doUserLogin(account, controller);
        } else {
            throw new BaseException(CanteenExceptionEnum.USER_NOT_EXIST);
        }
    }

    @Override
    public User getByAccount(String account) {
        return getOne(Wrappers.<User>lambdaQuery().eq(User::getAccount, account), false);
    }

    @Override
    public void add(UserForm dto, Account creator) {
        ValidatorUtil.validator(dto, UserForm.Insert.class);
        User user = getByAccount(dto.getAccount());
        if (user != null) {
            throw new BaseException(CanteenExceptionEnum.USER_ACCOUNT_REPEAT);
        }
        user = ModelMapperUtils.strict(dto, User.class);
        Password password = new Password(dto.getPassword());
        user.setSalt(password.getSalt());
        user.setPassword(password.getPassword());
        EntityLogUtil.addNormalUser(user, creator);
        boolean save = save(user);
        if (!save) {
            throw new BaseException(CanteenExceptionEnum.CREATE_FAIL);
        }
    }


    @Override
    public void update(UserForm form, Account updater) {
        ValidatorUtil.validator(form, UserForm.Update.class);
        Long id = form.getId();
        User user = getById(id);
        if (user == null) {
            throw new BaseException(CanteenExceptionEnum.USER_NOT_EXIST);
        }
        User account = getByAccount(form.getAccount());
        if (account != null && !account.getId().equals(id)) {
            throw new BaseException(CanteenExceptionEnum.USER_ACCOUNT_REPEAT);
        }
        Password password = new Password(form.getPassword());
        user.setName(form.getName());
        user.setAccount(form.getAccount());
        user.setPassword(password.getPassword());
        user.setSalt(password.getSalt());
        EntityLogUtil.addNormalUser(user, updater);
        boolean b = updateById(user);
        if (!b) {
            throw new BaseException(CanteenExceptionEnum.UPDATE_FAIL);
        }
    }

    @Override
    public void delete(Long id, Account updater) {
        User user = getById(id);
        if (user == null) {
            throw new BaseException(CanteenExceptionEnum.USER_NOT_EXIST);
        }
        EntityLogUtil.addNormalUser(user, updater);
        user.setDeleted(true);
        updateById(user);
    }

    @Override
    public ListUser listByConditional(UserSearch form) {
        ValidatorUtil.validator(form);
        Page<User> page = new Page<>(form.getPage(), form.getSize());
        super.page(page, Wrappers.<User>lambdaQuery()
                .likeLeft(!StringUtils.isEmpty(form.getName()), User::getName, form.getName())
                .likeLeft(!StringUtils.isEmpty(form.getAccount()), User::getAccount, form.getAccount())

        );
        return new ListUser(page.hasNext(), page.getTotal(), page.getCurrent(), page.getRecords());
    }

    @Override
    public User getById(Long id) {
        return super.getById(id);
    }
}
