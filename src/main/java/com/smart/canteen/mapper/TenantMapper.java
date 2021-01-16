package com.smart.canteen.mapper;

import com.smart.canteen.entity.Tenant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 租户配置 Mapper 接口
 * </p>
 *
 * @author lc
 * @since 2021-01-15
 */
@Mapper
public interface TenantMapper extends BaseMapper<Tenant> {

}
