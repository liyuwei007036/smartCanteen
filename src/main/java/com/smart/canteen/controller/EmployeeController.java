package com.smart.canteen.controller;


import com.lc.core.annotations.Valid;
import com.lc.core.controller.BaseController;
import com.lc.core.dto.ResponseInfo;
import com.lc.core.dto.User;
import com.smart.canteen.dto.employee.EmployeeForm;
import com.smart.canteen.dto.employee.EmployeeSearch;
import com.smart.canteen.dto.employee.ListEmployee;
import com.smart.canteen.dto.employee.LoginForm;
import com.smart.canteen.service.IEmployeeService;
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
 * @since 2020-03-01
 */
@Valid(needLogin = true)
@Api(value = "EmployeeController", tags = "员工中心")
@RestController
@RequestMapping("/employee")
public class EmployeeController extends BaseController {

    @Autowired
    private IEmployeeService iEmployeeService;

    @Valid
    @ApiOperation(value = "登录", notes = "登录")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseInfo<User> login(@Validated @RequestBody LoginForm params) {
        iEmployeeService.login(params, this);
        return new ResponseInfo<>(getCurrentUser());
    }


    @ApiOperation(value = "添加员工", notes = "添加员工")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseInfo addEmployee(@Validated(value = EmployeeForm.Insert.class) @RequestBody EmployeeForm params) {
        iEmployeeService.add(params, getCurrentUser());
        return new ResponseInfo<>();
    }


    @ApiOperation(value = "编辑员工", notes = "编辑员工")
    @RequestMapping(value = "update", method = RequestMethod.PATCH)
    public ResponseInfo updateEmployee(@Validated(value = EmployeeForm.Update.class) @RequestBody EmployeeForm params) {
        iEmployeeService.update(params, getCurrentUser());
        return new ResponseInfo<>();
    }


    @ApiOperation(value = "删除员工", notes = "删除员工")
    @RequestMapping(value = "deleted/{id}", method = RequestMethod.DELETE)
    public ResponseInfo deleteEmployee(@PathVariable("id") Long id) {
        iEmployeeService.delete(id, getCurrentUser());
        return new ResponseInfo<>();
    }

    @ApiOperation(value = "列表查询", notes = "列表查询")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ResponseInfo<ListEmployee> addEmployee(@RequestBody EmployeeSearch params) {
        return new ResponseInfo<>(iEmployeeService.listByConditional(params));
    }
}

