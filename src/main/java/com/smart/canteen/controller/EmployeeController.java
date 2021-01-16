package com.smart.canteen.controller;


import com.smart.canteen.annotations.Log;
import com.smart.canteen.annotations.Permission;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.employee.EmployeeForm;
import com.smart.canteen.dto.employee.EmployeeSearch;
import com.smart.canteen.dto.user.ChangePasswordForm;
import com.smart.canteen.dto.user.LoginForm;
import com.smart.canteen.enums.LoginEnum;
import com.smart.canteen.service.IEmployeeService;
import com.smart.canteen.service.ILoginLogService;
import com.smart.canteen.vo.EmployeeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import live.lumia.annotations.Secret;
import live.lumia.annotations.Valid;
import live.lumia.controller.BaseController;
import live.lumia.dto.Account;
import live.lumia.dto.ResponseInfo;
import live.lumia.utils.ObjectUtil;
import live.lumia.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lc
 * @since 2020-03-01
 */
@Secret
@Valid(needLogin = true)
@Api(value = "EmployeeController", tags = "员工中心")
@RestController
@RequestMapping("/employee")
public class EmployeeController extends BaseController {


    @Autowired
    private IEmployeeService iEmployeeService;

    @Autowired
    private ILoginLogService iLoginLogService;

    @Valid
    @ApiOperation(value = "登录", notes = "登录")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseInfo<Account> login(@RequestBody LoginForm params) {
        return new ResponseInfo<>(iEmployeeService.login(params, this));
    }

    @ApiOperation(value = "获取当前用户", notes = "获取当前用户")
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public ResponseInfo<Account> detail() {
        return new ResponseInfo<>(getCurrentUser());
    }

    @ApiOperation(value = "登出", notes = "登出")
    @RequestMapping(value = "loginout", method = RequestMethod.DELETE)
    public ResponseInfo<Account> loginOut() {
        iLoginLogService.addLog(getCurrentUser(), RequestUtils.getIpAddress(this.getRequest()), LoginEnum.LOGIN_OUT);
        removeSession();
        return new ResponseInfo<>();
    }

    @Log(module = "人员管理", action = "添加", clazz = EmployeeForm.class)
    @Permission(code = "employee:add")
    @ApiOperation(value = "添加员工", notes = "添加员工")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseInfo addEmployee(@RequestBody EmployeeForm params) {
        iEmployeeService.add(params, getCurrentUser());
        return new ResponseInfo<>();
    }

    @Log(module = "人员管理", action = "编辑", clazz = EmployeeForm.class)
    @Permission(code = "employee:update")
    @ApiOperation(value = "编辑员工", notes = "编辑员工")
    @RequestMapping(value = "update", method = RequestMethod.PATCH)
    public ResponseInfo updateEmployee(@RequestBody EmployeeForm params) {
        iEmployeeService.update(params, getCurrentUser());
        return new ResponseInfo<>();
    }

    @Permission(code = "employee:view")
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ResponseInfo<CommonList<EmployeeVO>> addEmployee(@RequestBody EmployeeSearch params) {
        return new ResponseInfo<>(iEmployeeService.listByConditional(params));
    }

    @Permission(code = "employee:view")
    @ApiOperation(value = "获取员工", notes = "获取员工")
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseInfo<EmployeeVO> getEmployee(@PathVariable String id) {
        return new ResponseInfo<>(iEmployeeService.getEmpInfo(ObjectUtil.getLong(id)));
    }

    @Log(module = "人员管理", action = "修改密码", clazz = ChangePasswordForm.class)
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public ResponseInfo changePassword(@RequestBody ChangePasswordForm passwordForm) {
        iEmployeeService.changePassword(passwordForm, getCurrentUser());
        return new ResponseInfo<>();
    }


}

