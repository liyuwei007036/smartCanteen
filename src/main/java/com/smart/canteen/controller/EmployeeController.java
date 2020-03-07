package com.smart.canteen.controller;


import com.lc.core.annotations.Valid;
import com.lc.core.controller.BaseController;
import com.lc.core.dto.Account;
import com.lc.core.dto.ResponseInfo;
import com.lc.core.utils.ObjectUtil;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.employee.EmployeeForm;
import com.smart.canteen.dto.employee.EmployeeSearch;
import com.smart.canteen.dto.user.LoginForm;
import com.smart.canteen.entity.Employee;
import com.smart.canteen.service.IEmployeeService;
import com.smart.canteen.vo.EmployeeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    public ResponseInfo<Account> login(@RequestBody LoginForm params) {
        iEmployeeService.login(params, this);
        return new ResponseInfo<>(getCurrentUser());
    }

    @ApiOperation(value = "登出", notes = "登出")
    @RequestMapping(value = "loginout", method = RequestMethod.DELETE)
    public ResponseInfo<Account> loginOut() {
        removeSession();
        return new ResponseInfo<>();
    }


    @ApiOperation(value = "添加员工", notes = "添加员工")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseInfo addEmployee(@RequestBody EmployeeForm params) {
        iEmployeeService.add(params, getCurrentUser());
        return new ResponseInfo<>();
    }


    @ApiOperation(value = "编辑员工", notes = "编辑员工")
    @RequestMapping(value = "update", method = RequestMethod.PATCH)
    public ResponseInfo updateEmployee(@RequestBody EmployeeForm params) {
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
    public ResponseInfo<CommonList<Employee>> addEmployee(@RequestBody EmployeeSearch params) {
        return new ResponseInfo<>(iEmployeeService.listByConditional(params));
    }


    @ApiOperation(value = "获取员工", notes = "获取员工")
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseInfo<EmployeeVO> addEmployee(@PathVariable String id) {
        return new ResponseInfo<>(iEmployeeService.getEmpInfo(ObjectUtil.getLong(id)));
    }


}

