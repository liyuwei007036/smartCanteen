package com.smart.canteen.controller;


import live.lumia.annotations.Valid;
import live.lumia.controller.BaseController;
import live.lumia.dto.ResponseInfo;
import live.lumia.utils.ObjectUtil;
import com.smart.canteen.annotations.Log;
import com.smart.canteen.annotations.Permission;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.card.DeductionForm;
import com.smart.canteen.dto.origination.OriginationForm;
import com.smart.canteen.dto.origination.OriginationSearch;
import com.smart.canteen.entity.Origination;
import com.smart.canteen.service.IOriginationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * <p>
 * 组织 前端控制器
 * </p>
 *
 * @author lc
 * @since 2020-03-02
 */
@Valid(needLogin = true)
@Api(tags = {"组织管理"})
@RestController
@RequestMapping("/origination")
public class OriginationController extends BaseController {

    @Autowired
    private IOriginationService iOriginationService;

    @Log(module = "组织管理", action = "添加组织", clazz = OriginationForm.class)
    @Permission(code = "origination:add")
    @ApiOperation(value = "添加组织", notes = "添加")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseInfo add(@RequestBody OriginationForm params) {
        iOriginationService.add(params, getCurrentUser());
        return new ResponseInfo<>();
    }

    @Permission(code = "origination:update")
    @ApiOperation(value = "编辑组织", notes = "编辑组织")
    @Log(module = "组织管理", action = "编辑", clazz = OriginationForm.class)
    @RequestMapping(value = "update", method = RequestMethod.PATCH)
    public ResponseInfo update(@RequestBody OriginationForm params) {
        iOriginationService.update(params, getCurrentUser());
        return new ResponseInfo<>();
    }

    @Permission(code = "origination:deleted")
    @Log(module = "组织管理", action = "删除", dataDesc = "组织ID")
    @ApiOperation(value = "删除组织", notes = "删除组织")
    @RequestMapping(value = "deleted/{id}", method = RequestMethod.DELETE)
    public ResponseInfo deleted(@PathVariable("id") Long id) {
        iOriginationService.delete(id, getCurrentUser());
        return new ResponseInfo<>();
    }

    @Permission(code = "origination:view")
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ResponseInfo<CommonList<Origination>> list(@RequestBody OriginationSearch params) {
        return new ResponseInfo<>(iOriginationService.listByConditional(params));
    }

    @Permission(code = "origination:view")
    @ApiOperation(value = "获取组织", notes = "获取组织")
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseInfo<Origination> get(@PathVariable String id) {
        return new ResponseInfo<>(iOriginationService.getById(ObjectUtil.getLong(id)));
    }

    @Permission(code = "origination:view")
    @ApiOperation(value = "获取所有组织", notes = "获取所有组织")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    public ResponseInfo listAll() {
        return new ResponseInfo<>((Serializable) iOriginationService.listAll());
    }

    @Permission(code = "origination:view")
    @ApiOperation(value = "获取所有根节点", notes = "获取所有根节点")
    @RequestMapping(value = "getRoot", method = RequestMethod.GET)
    public ResponseInfo getRoot() {
        return new ResponseInfo<>((Serializable) iOriginationService.getAllRoot());
    }

    @Permission(code = "origination:view")
    @ApiOperation(value = "获取子节点", notes = "获取子节点")
    @RequestMapping(value = "get/nodes/{id}", method = RequestMethod.GET)
    public ResponseInfo getNodes(@PathVariable String id) {
        return new ResponseInfo<>((Serializable) iOriginationService.getChildren(ObjectUtil.getLong(id)));
    }

}

