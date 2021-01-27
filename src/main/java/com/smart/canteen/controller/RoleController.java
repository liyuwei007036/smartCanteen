package com.smart.canteen.controller;


import com.smart.canteen.annotations.Log;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.role.RoleForm;
import com.smart.canteen.dto.role.RoleSearch;
import com.smart.canteen.entity.Role;
import com.smart.canteen.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import live.lumia.annotations.Permission;
import live.lumia.annotations.Secret;
import live.lumia.controller.BaseController;
import live.lumia.dto.ResponseInfo;
import live.lumia.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * <p>
 * 岗位 前端控制器
 * </p>
 *
 * @author lc
 * @since 2020-03-02
 */
@Secret
@Permission()
@Api(tags = {"角色管理"})
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService iRoleService;

    @Log(module = "角色管理", action = "添加", clazz = RoleForm.class)
    @Permission(code = "role:add")
    @ApiOperation(value = "添加角色", notes = "添加角色")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseInfo add(@RequestBody RoleForm params) {
        iRoleService.add(params, getCurrentUser());
        return new ResponseInfo<>();
    }

    @Log(module = "角色管理", action = "编辑", clazz = RoleForm.class)
    @Permission(code = "role:update")
    @ApiOperation(value = "编辑角色", notes = "编辑角色")
    @RequestMapping(value = "update", method = RequestMethod.PATCH)
    public ResponseInfo update(@RequestBody RoleForm params) {
        iRoleService.update(params, getCurrentUser());
        return new ResponseInfo<>();
    }

    @Log(module = "角色管理", action = "删除", dataDesc = "角色ID")
    @Permission(code = "role:deleted")
    @ApiOperation(value = "删除角色", notes = "删除角色")
    @RequestMapping(value = "deleted/{id}", method = RequestMethod.DELETE)
    public ResponseInfo deleted(@PathVariable("id") Long id) {
        iRoleService.delete(id, getCurrentUser());
        return new ResponseInfo<>();
    }

    @Permission(code = "role:list")
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ResponseInfo<CommonList<Role>> list(@RequestBody RoleSearch params) {
        return new ResponseInfo<>(iRoleService.listByConditional(params));
    }

    @Permission(code = "role:get")
    @ApiOperation(value = "获取角色", notes = "获取角色")
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseInfo<Role> get(@PathVariable String id) {
        return new ResponseInfo<>(iRoleService.getById(ObjectUtil.getLong(id)));
    }

    @ApiOperation(value = "获取所有角色", notes = "获取角色")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public ResponseInfo listAll() {
        return new ResponseInfo((Serializable) iRoleService.listAll(true));
    }


}

