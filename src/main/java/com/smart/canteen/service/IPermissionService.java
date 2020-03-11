package com.smart.canteen.service;

import com.smart.canteen.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.canteen.vo.OriginationVo;
import com.smart.canteen.vo.PermissionVo;

import java.util.List;

/**
 * <p>
 * 组织 服务类
 * </p>
 *
 * @author lc
 * @since 2020-03-11
 */
public interface IPermissionService extends IService<Permission> {

    /**
     * 查询所有
     *
     * @return
     */
    List<PermissionVo> listAll();


}
