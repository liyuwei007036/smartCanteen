package com.smart.canteen.service;

import com.smart.canteen.entity.Tenant;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 租户配置 服务类
 * </p>
 *
 * @author lc
 * @since 2021-01-15
 */
public interface ITenantService extends IService<Tenant> {

    /**
     * 通过code获取租户信息
     *
     * @param code 租户编码
     * @return 租户信息
     */
    Tenant getByCode(String code);


    void testTranction(String code);

    void doTestTranction();
}
