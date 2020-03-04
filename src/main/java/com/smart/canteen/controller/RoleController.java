package com.smart.canteen.controller;


import com.lc.core.annotations.Valid;
import com.lc.core.controller.BaseController;
import com.lc.core.dto.ResponseInfo;
import com.lc.core.utils.ObjectUtil;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.role.RoleForm;
import com.smart.canteen.dto.role.RoleSearch;
import com.smart.canteen.entity.Role;
import com.smart.canteen.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Valid(needLogin = true)
@Api(tags = {"角色管理"})
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService iRoleService;

    @ApiOperation(value = "添加角色", notes = "添加角色")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseInfo addEmployee(@RequestBody RoleForm params) {
        iRoleService.add(params, getCurrentUser());
        return new ResponseInfo<>();
    }

    @ApiOperation(value = "编辑角色", notes = "编辑角色")
    @RequestMapping(value = "update", method = RequestMethod.PATCH)
    public ResponseInfo updateEmployee(@RequestBody RoleForm params) {
        iRoleService.update(params, getCurrentUser());
        return new ResponseInfo<>();
    }

    @ApiOperation(value = "删除角色", notes = "删除角色")
    @RequestMapping(value = "deleted/{id}", method = RequestMethod.DELETE)
    public ResponseInfo deleteEmployee(@PathVariable("id") Long id) {
        iRoleService.delete(id, getCurrentUser());
        return new ResponseInfo<>();
    }

    @ApiOperation(value = "列表查询", notes = "列表查询")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ResponseInfo<CommonList<Role>> addEmployee(@RequestBody RoleSearch params) {
        return new ResponseInfo<>(iRoleService.listByConditional(params));
    }

    @ApiOperation(value = "获取角色", notes = "获取角色")
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseInfo<Role> addEmployee(@PathVariable String id) {
        return new ResponseInfo<>(iRoleService.getById(ObjectUtil.getLong(id)));
    }

    @ApiOperation(value = "获取所有角色", notes = "获取角色")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    public ResponseInfo listAll() {
        return new ResponseInfo((Serializable) iRoleService.listAll());
    }


}

