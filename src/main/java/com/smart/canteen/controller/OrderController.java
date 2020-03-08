package com.smart.canteen.controller;


import com.lc.core.annotations.Valid;
import com.lc.core.controller.BaseController;
import com.lc.core.dto.ResponseInfo;
import com.smart.canteen.dto.CommonList;
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

/**
 * <p>
 * 充值记录 前端控制器
 * </p>
 *
 * @author lc
 * @since 2020-03-08
 */
@Api(tags = "消费管理")
@Valid(needLogin = true)
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    private IOrderService iOrderService;

    @ApiOperation(value = "查询记录", notes = "查询记录")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ResponseInfo<CommonList<OrderVo>> list(@RequestBody OrderSearch params) {
        return new ResponseInfo<>(iOrderService.listLogs(params));
    }

}

