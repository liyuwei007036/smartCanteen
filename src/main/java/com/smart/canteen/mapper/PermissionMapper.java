package com.smart.canteen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.canteen.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 组织 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2020-03-11
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}
