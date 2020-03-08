package com.smart.canteen.controller;


import com.lc.core.annotations.Valid;
import com.lc.core.controller.BaseController;
import com.lc.core.dto.ResponseInfo;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.recharge.RechargeForm;
import com.smart.canteen.dto.recharge.RechargeLogSearch;
import com.smart.canteen.service.IRechargeLogService;
import com.smart.canteen.service.impl.IcCardServiceImpl;
import com.smart.canteen.vo.RechargeLogVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 充值记录 前端控制器
 * </p>
 *
 * @author lc
 * @since 2020-03-08
 */
@Api(tags = "充值管理")
@Valid(needLogin = true)
@RestController
@RequestMapping("/recharge")
public class RechargeLogController extends BaseController {

    @Autowired
    private IcCardServiceImpl icCardService;

    @Autowired
    private IRechargeLogService iRechargeLogService;


    @ApiOperation(value = "充值", notes = "充值")
    @RequestMapping(value = "recharge", method = RequestMethod.POST)
    public ResponseInfo recharge(@RequestBody RechargeForm form) {
        icCardService.recharge(form, getCurrentUser());
        return new ResponseInfo();
    }

    @ApiOperation(value = "查询记录", notes = "查询记录")
    @RequestMapping(value = "list/log", method = RequestMethod.POST)
    public ResponseInfo<CommonList<RechargeLogVO>> list(@RequestBody RechargeLogSearch params) {
        return new ResponseInfo<>(iRechargeLogService.listLogs(params));
    }


}

