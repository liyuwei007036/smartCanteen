package com.smart.canteen.controller;


import com.alibaba.fastjson.JSONObject;
import live.lumia.annotations.Secret;
import live.lumia.annotations.Valid;
import live.lumia.controller.BaseController;
import live.lumia.dto.ResponseInfo;
import live.lumia.utils.ObjectUtil;
import com.smart.canteen.annotations.Permission;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.SummaryDTO;
import com.smart.canteen.dto.SummarySearchDTO;
import com.smart.canteen.dto.order.OrderSearch;
import com.smart.canteen.service.IOrderService;
import com.smart.canteen.vo.OrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

/**
 * <p>
 * 充值记录 前端控制器
 * </p>
 *
 * @author lc
 * @since 2020-03-08
 */
@Secret
@Api(tags = "消费管理")
@Valid(needLogin = true)
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    private IOrderService iOrderService;

    @Permission(code = "order:list")
    @ApiOperation(value = "消费记录", notes = "消费记录")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ResponseInfo<CommonList<OrderVo>> list(@RequestBody OrderSearch params) {
        return new ResponseInfo<>(iOrderService.listLogs(params));
    }

    @ApiOperation(value = "查询2个时间段之间的统计", notes = "查询2个时间段之间的统计")
    @RequestMapping(value = "/summary/other", method = RequestMethod.POST)
    public ResponseInfo<SummaryDTO> other(@RequestBody JSONObject params) {
        SummarySearchDTO data = new SummarySearchDTO();
        data.setStart(ObjectUtil.getDate(params.get("start")));
        Calendar end = Calendar.getInstance();
        end.setTime(ObjectUtil.getDate(params.get("end")));
        end.add(Calendar.MONTH, 1);
        data.setEnd(end.getTime());
        return new ResponseInfo<>(iOrderService.getOtherSaleData(data));
    }

    @ApiOperation(value = "年消费统计", notes = "年消费统计")
    @RequestMapping(value = "/summary/year", method = RequestMethod.GET)
    public ResponseInfo<SummaryDTO> summaryYear() {
        return new ResponseInfo<>(iOrderService.getYearSaleData());
    }

    @ApiOperation(value = "月消费统计", notes = "月消费统计")
    @RequestMapping(value = "/summary/month", method = RequestMethod.GET)
    public ResponseInfo<SummaryDTO> summaryMonth() {
        return new ResponseInfo<>(iOrderService.getMonthSaleData());
    }

    @ApiOperation(value = "天消费统计", notes = "天消费统计")
    @RequestMapping(value = "/summary/day", method = RequestMethod.GET)
    public ResponseInfo<SummaryDTO> summaryDay() {
        return new ResponseInfo<>(iOrderService.getDaySaleData());
    }
}

