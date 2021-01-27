package com.smart.canteen.controller;


import com.smart.canteen.annotations.Log;
import com.smart.canteen.dto.role.PermissionForm;
import com.smart.canteen.service.IPermissionService;
import com.smart.canteen.service.IRolePermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import live.lumia.annotations.Permission;
import live.lumia.annotations.Secret;
import live.lumia.controller.BaseController;
import live.lumia.dto.ResponseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lc
 * @since 2020-03-11
 */
@Secret
@Permission()
@Api(tags = {"权限管理"})
@RestController
@RequestMapping("/rolePermission")
public class RolePermissionController extends BaseController {

    @Autowired
    private IPermissionService iPermissionService;

    @Autowired
    private IRolePermissionService iRolePermissionService;


    @Permission(code = "role:authorization")
    @ApiOperation(value = "获取权限集合", notes = "获取权限集合")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    public ResponseInfo listAll() {
        return new ResponseInfo<>((Serializable) iPermissionService.listAll());
    }


    @Log(module = "权限管理", action = "添加", clazz = PermissionForm.class)
    @Permission(code = "role:authorization")
    @ApiOperation(value = "添加权限", notes = "添加权限")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseInfo add(@RequestBody PermissionForm form) {
        iRolePermissionService.addPermission(form, getCurrentUser());
        return new ResponseInfo<>();
    }

    @Permission(code = "role:authorization")
    @ApiOperation(value = "获取权限", notes = "获取权限")
    @RequestMapping(value = "getPermission/{role}", method = RequestMethod.GET)
    public ResponseInfo getPermission(@PathVariable("role") Long role) {
        return new ResponseInfo<>((Serializable) iRolePermissionService.getRolePermission(role));
    }
}

