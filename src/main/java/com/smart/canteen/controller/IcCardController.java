package com.smart.canteen.controller;


import com.lc.core.annotations.Valid;
import com.lc.core.controller.BaseController;
import com.lc.core.dto.ResponseInfo;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.card.CardSearch;
import com.smart.canteen.service.IIcCardService;
import com.smart.canteen.vo.CardVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * iC卡 前端控制器
 * </p>
 *
 * @author lc
 * @since 2020-03-03
 */
@Api(tags = "卡片管理")
@Valid(needLogin = true)
@RestController
@RequestMapping("/icCard")
public class IcCardController extends BaseController {

    @Autowired
    private IIcCardService iIcCardService;
    private Long id;

    @ApiOperation(value = "查询卡片列表", notes = "查询卡片列表")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ResponseInfo<CommonList<CardVo>> list(@RequestBody CardSearch params) {
        return new ResponseInfo<>(iIcCardService.listCard(params));
    }

    @ApiOperation(value = "挂失", notes = "挂失")
    @RequestMapping(value = "report/loss/{id}", method = RequestMethod.PUT)
    public ResponseInfo reportList(@PathVariable(name = "id") Long id) {
        iIcCardService.reportLoss(id, getCurrentUser());
        return new ResponseInfo<>();
    }

}

