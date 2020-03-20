package com.smart.canteen.controller;


import com.lc.core.annotations.Valid;
import com.lc.core.controller.BaseController;
import com.lc.core.dto.ResponseInfo;
import com.smart.canteen.annotations.Log;
import com.smart.canteen.annotations.Permission;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.card.CardSearch;
import com.smart.canteen.dto.card.DeductionForm;
import com.smart.canteen.dto.card.PatchCardForm;
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

    @Permission(code = "icCard:list")
    @ApiOperation(value = "查询卡片列表", notes = "查询卡片列表")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ResponseInfo<CommonList<CardVo>> list(@RequestBody CardSearch params) {
        return new ResponseInfo<>(iIcCardService.listCard(params));
    }

    @Permission(code = "icCard:list")
    @ApiOperation(value = "查询卡片列表", notes = "查询卡片列表")
    @RequestMapping(value = "list/recharge", method = RequestMethod.POST)
    public ResponseInfo<CommonList<CardVo>> listRecharge(@RequestBody CardSearch params) {
        return new ResponseInfo<>(iIcCardService.listCardForRecharge(params));
    }


    @Log(module = "卡片管理", action = "挂失", dataDesc = "卡片id")
    @Permission(code = "icCard:loss")
    @ApiOperation(value = "挂失", notes = "挂失")
    @RequestMapping(value = "report/loss/{id}", method = RequestMethod.PUT)
    public ResponseInfo reportList(@PathVariable(name = "id") Long id) {
        iIcCardService.reportLoss(id, getCurrentUser());
        return new ResponseInfo<>();
    }

    @Log(module = "卡片管理", action = "补卡", clazz = PatchCardForm.class)
    @Permission(code = "icCard:patch")
    @ApiOperation(value = "补卡", notes = "补卡")
    @RequestMapping(value = "/patch", method = RequestMethod.POST)
    public ResponseInfo patch(@RequestBody PatchCardForm form) {
        iIcCardService.patchCard(form, getCurrentUser());
        return new ResponseInfo<>();
    }

    @Log(module = "卡片管理", action = "补卡", clazz = PatchCardForm.class)
    @Permission(code = "icCard:patch")
    @ApiOperation(value = "补卡时获取用户信息", notes = "补卡时获取用户信息")
    @RequestMapping(value = "/patch/{cardId}", method = RequestMethod.GET)
    public ResponseInfo getCardUser(@PathVariable("cardId") Long cardId) {
        return new ResponseInfo<>(iIcCardService.getCardUser(cardId));
    }

    @Log(module = "卡片管理", action = "销户", dataDesc = "人员id")
    @Permission(code = "icCard:deleted")
    @ApiOperation(value = "销户", notes = "销户")
    @RequestMapping(value = "deleted/{id}", method = RequestMethod.DELETE)
    public ResponseInfo deletedCard(@PathVariable("id") Long id) {
        iIcCardService.cancellation(id, getCurrentUser());
        return new ResponseInfo<>();
    }

    @Log(module = "卡片管理", action = "补扣", clazz = DeductionForm.class)
    @Permission(code = "icCard:deduction")
    @ApiOperation(value = "补扣", notes = "补扣")
    @RequestMapping(value = "deduction", method = RequestMethod.POST)
    public ResponseInfo deduction(@RequestBody DeductionForm form) {
        iIcCardService.deduction(form, getCurrentUser());
        return new ResponseInfo<>();
    }
}

