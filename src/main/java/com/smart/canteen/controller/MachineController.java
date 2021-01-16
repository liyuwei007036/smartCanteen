package com.smart.canteen.controller;


import live.lumia.annotations.Secret;
import live.lumia.annotations.Valid;
import live.lumia.controller.BaseController;
import live.lumia.dto.ResponseInfo;
import com.smart.canteen.annotations.Log;
import com.smart.canteen.annotations.Permission;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.machine.MachineDTO;
import com.smart.canteen.dto.machine.MachineSearch;
import com.smart.canteen.entity.Machine;
import com.smart.canteen.service.IMachineService;
import com.smart.canteen.vo.MachineVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 卡机 前端控制器
 * </p>
 *
 * @author lc
 * @since 2020-03-25
 */
@Secret
@Api(tags = "卡机管理", description = "卡机管理")
@Valid(needLogin = true)
@RestController
@RequestMapping("/machine")
public class MachineController extends BaseController {

    @Autowired
    private IMachineService iMachineService;

    @Log(module = "卡机管理", action = "添加")
    @Permission(code = "machine:add")
    @ApiOperation(value = "添加卡机", notes = "添加卡机")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseInfo add(@RequestBody MachineDTO params) {
        iMachineService.add(params, getCurrentUser());
        return new ResponseInfo<>();
    }

    @Log(module = "卡机管理", action = "编辑")
    @Permission(code = "machine:update")
    @ApiOperation(value = "编辑卡机", notes = "编辑卡机")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResponseInfo update(@RequestBody MachineDTO params) {
        iMachineService.edit(params, getCurrentUser());
        return new ResponseInfo<>();
    }

    @Permission(code = "machine:view")
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ResponseInfo<CommonList<MachineVO>> page(@RequestBody MachineSearch params) {
        return new ResponseInfo<>(iMachineService.page(params));
    }

    @Permission(code = "machine:update")
    @ApiOperation(value = "查询", notes = "查询")
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseInfo<Machine> get(@PathVariable("id") Long id) {
        return new ResponseInfo<>(iMachineService.getById(id));
    }

    @Log(module = "卡机管理", action = "删除")
    @Permission(code = "machine:delete")
    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseInfo delete(@PathVariable("id") Long id) {
        iMachineService.delete(id, getCurrentUser());
        return new ResponseInfo<>();
    }
}

