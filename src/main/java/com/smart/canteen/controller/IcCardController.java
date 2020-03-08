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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "查询卡片列表", notes = "查询卡片列表")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ResponseInfo<CommonList<CardVo>> list(@RequestBody CardSearch params) {
        return new ResponseInfo<>(iIcCardService.listCard(params));
    }
}

