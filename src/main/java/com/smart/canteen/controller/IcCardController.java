package com.smart.canteen.controller;


import com.lc.core.annotations.Valid;
import com.lc.core.controller.BaseController;
import com.lc.core.dto.ResponseInfo;
import com.lc.core.error.BaseException;
import com.lc.core.service.RedisService;
import com.lc.core.utils.ObjectUtil;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.card.CardSearch;
import com.smart.canteen.enums.CanteenExceptionEnum;
import com.smart.canteen.service.IIcCardService;
import com.smart.canteen.vo.CardVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
    private RedisService redisService;

    @Autowired
    private IIcCardService iIcCardService;

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

    @ApiOperation(value = "准备获取卡号", notes = "准备获取卡号")
    @RequestMapping(value = "before/get/card", method = RequestMethod.GET)
    public ResponseInfo beforeGetCard() {
        boolean lock = redisService.putIfAbsent("GET_CARD_NO", getSessionId(), 9, 40);
        if (!lock) {
            Object session = redisService.get("GET_CARD_NO", 9);
            if (ObjectUtil.getString(session).equals(getSessionId())) {
                throw new BaseException(CanteenExceptionEnum.UPDATE_FAIL);
            }
            throw new BaseException(CanteenExceptionEnum.GET_CARD_ERROR);
        }
        return new ResponseInfo<>();
    }

    @ApiOperation(value = "获取卡号", notes = "获取卡号")
    @RequestMapping(value = "/get/card", method = RequestMethod.GET)
    public ResponseInfo getCard() {
        String cardNo = null;
        Object session = redisService.get("GET_CARD_NO", 9);
        if (session.equals(getSessionId())) {
            cardNo = (String) redisService.get("CARD_NO", 9);
        }
        if (!StringUtils.isEmpty(cardNo)) {
            redisService.remove("GET_CARD_NO", 9);
            redisService.remove("CARD_NO", 9);
        }
        return new ResponseInfo<>(ObjectUtil.getString(cardNo).trim());
    }
}

