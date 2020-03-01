package com.smart.canteen.controller;


import com.lc.core.annotations.Valid;
import com.lc.core.controller.BaseController;
import com.lc.core.dto.ResponseInfo;
import com.smart.canteen.entity.Employee;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lc
 * @since 2020-03-01
 */
@Valid
@Api(value = "EmployeeController", tags = "员工中心")
@RestController
@RequestMapping("/employee")
public class EmployeeController extends BaseController {

    @ApiOperation(value = "添加员工", notes = "添加员工", response = ResponseInfo.class)
    @PostMapping(value = "add")
    public ResponseInfo<Employee> addEmployee(@RequestBody Employee employee) {
        return new ResponseInfo<>(employee);
    }

}

