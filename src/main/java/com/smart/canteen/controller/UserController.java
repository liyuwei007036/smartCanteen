package com.smart.canteen.controller;


import com.lc.core.annotations.Valid;
import com.lc.core.controller.BaseController;
import com.lc.core.dto.Account;
import com.lc.core.dto.ResponseInfo;
import com.lc.core.utils.ObjectUtil;
import com.lc.core.utils.ValidatorUtil;
import com.smart.canteen.dto.employee.EmployeeForm;
import com.smart.canteen.dto.employee.EmployeeSearch;
import com.smart.canteen.dto.employee.ListEmployee;
import com.smart.canteen.dto.user.ListUser;
import com.smart.canteen.dto.user.LoginForm;
import com.smart.canteen.dto.user.UserForm;
import com.smart.canteen.dto.user.UserSearch;
import com.smart.canteen.entity.Employee;
import com.smart.canteen.entity.User;
import com.smart.canteen.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lc
 * @since 2020-03-03
 */
@Api(value = "user-module", tags = "用户中心")
@Valid
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService iUserService;

    @Valid
    @ApiOperation(value = "登录", notes = "登录")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseInfo<Account> login(@RequestBody LoginForm params) {
        iUserService.login(params, this);
        return new ResponseInfo<>(getCurrentUser());
    }

    @ApiOperation(value = "登出", notes = "登出")
    @RequestMapping(value = "loginout", method = RequestMethod.DELETE)
    public ResponseInfo<Account> loginOut() {
        removeSession();
        return new ResponseInfo<>();
    }

    @ApiOperation(value = "添加用户", notes = "添加用户")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseInfo addEmployee(@RequestBody UserForm params) {
        iUserService.add(params, getCurrentUser());
        return new ResponseInfo<>();
    }


    @ApiOperation(value = "编辑用户", notes = "编辑用户")
    @RequestMapping(value = "update", method = RequestMethod.PATCH)
    public ResponseInfo updateEmployee(@RequestBody UserForm params) {
        iUserService.update(params, getCurrentUser());
        return new ResponseInfo<>();
    }


    @ApiOperation(value = "删除用户", notes = "删除用户")
    @RequestMapping(value = "deleted/{id}", method = RequestMethod.DELETE)
    public ResponseInfo deleteEmployee(@PathVariable("id") Long id) {
        iUserService.delete(id, getCurrentUser());
        return new ResponseInfo<>();
    }

    @ApiOperation(value = "列表查询", notes = "列表查询")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ResponseInfo<ListUser> addEmployee(@RequestBody UserSearch params) {
        return new ResponseInfo<>(iUserService.listByConditional(params));
    }

    @ApiOperation(value = "获取用户", notes = "获取用户")
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseInfo<User> addEmployee(@PathVariable String id) {
        return new ResponseInfo<>(iUserService.getById(ObjectUtil.getLong(id)));
    }
}

