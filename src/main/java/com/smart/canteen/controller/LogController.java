package com.smart.canteen.controller;


import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.log.LoginSearch;
import com.smart.canteen.dto.log.OperationSearch;
import com.smart.canteen.entity.OperationLog;
import com.smart.canteen.service.ILoginLogService;
import com.smart.canteen.service.IOperationLogService;
import com.smart.canteen.vo.LoginLogVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import live.lumia.annotations.Permission;
import live.lumia.annotations.Secret;
import live.lumia.controller.BaseController;
import live.lumia.dto.ResponseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lc
 * @since 2020-03-16
 */
@Secret
@Api(tags = "日志管理", description = "日志管理")
@Permission
@RestController
@RequestMapping("/log")
public class LogController extends BaseController {

    @Autowired
    private ILoginLogService iLoginLogService;

    @Autowired
    private IOperationLogService iOperationLogService;

    @Permission(code = "log:login")
    @ApiModelProperty(value = "查询登录日志")
    @RequestMapping(value = "/list/login", method = RequestMethod.POST)
    public ResponseInfo<CommonList<LoginLogVO>> listLogin(@RequestBody LoginSearch loginSearch) {
        return new ResponseInfo<>(iLoginLogService.listLogs(loginSearch));
    }

    @Permission(code = "log:operation")
    @ApiModelProperty(value = "查询操作日志")
    @RequestMapping(value = "/list/operation", method = RequestMethod.POST)
    public ResponseInfo<CommonList<OperationLog>> listOperation(@RequestBody OperationSearch search) {
        return new ResponseInfo<>(iOperationLogService.listLogs(search));
    }

    @Permission(code = "log:operation")
    @ApiModelProperty(value = "查询一个操作日志")
    @RequestMapping(value = "/get/operation/{id}", method = RequestMethod.GET)
    public ResponseInfo<OperationLog> getOperation(@PathVariable("id") Long id) {
        return new ResponseInfo<>(iOperationLogService.getById(id));
    }

}

