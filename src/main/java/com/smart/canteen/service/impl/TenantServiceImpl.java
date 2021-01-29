package com.smart.canteen.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.canteen.entity.Tenant;
import com.smart.canteen.mapper.TenantMapper;
import com.smart.canteen.service.ITenantService;
import live.lumia.annotations.Cache;
import live.lumia.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 租户配置 服务实现类
 * </p>
 *
 * @author lc
 * @since 2021-01-15
 */
@Slf4j
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements ITenantService {

    @Cache(key = "#code", timeout = 3600 * 12, name = "TENANT")
    @Override
    public Tenant getByCode(String code) {
        return getOne(Wrappers.<Tenant>lambdaQuery().eq(Tenant::getCode, code), false);
    }


    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void testTranction(String code) {
        Tenant tenant = new Tenant();
        tenant.setName("a");
        tenant.setCode(code);
        tenant.setSerKey("a");
        tenant.setIv("a");
        tenant.setType("1");
        save(tenant);
        boolean s = "err2".equals(code);
        if (s) {
            log.error("error {}", code);
            throw new RuntimeException(code);
        } else {
            log.info("success {}", code);
        }
    }

    @Override
    public void doTestTranction() {
        for (int i = 0; i < 4; i++) {
            try {
                SpringUtil.getBean(ITenantService.class).testTranction("err" + i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
