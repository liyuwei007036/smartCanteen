package com.smart.canteen.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.canteen.entity.Tenant;
import com.smart.canteen.mapper.TenantMapper;
import com.smart.canteen.service.ITenantService;
import live.lumia.annotations.Cache;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 租户配置 服务实现类
 * </p>
 *
 * @author lc
 * @since 2021-01-15
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements ITenantService {


    @Cache(key = "#code", timeout = 3600 * 12, name = "TENANT")
    @Override
    public Tenant getByCode(String code) {
        return getOne(Wrappers.<Tenant>lambdaQuery().eq(Tenant::getCode, code), false);
    }
}
