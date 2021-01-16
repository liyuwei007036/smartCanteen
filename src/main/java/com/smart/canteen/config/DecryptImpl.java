package com.smart.canteen.config;

import com.alibaba.fastjson.JSON;
import com.smart.canteen.entity.Tenant;
import com.smart.canteen.service.ITenantService;
import live.lumia.config.DefaultAesDecrypt;
import live.lumia.utils.EncryptionUtils;
import live.lumia.utils.SpringUtil;
import lombok.SneakyThrows;

public class DecryptImpl implements DefaultAesDecrypt {


    @Override
    public String decrypt(String telnetId, String data) {
        try {
            Tenant tenant = SpringUtil.getBean(ITenantService.class).getByCode(telnetId);
            return EncryptionUtils.aesDecrypt(data, tenant.getSerKey(), tenant.getIv());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("解密失败", e);
        }
    }


    @SneakyThrows
    @Override
    public String encrypt(String telnetId, Object data) {
        Tenant tenant = SpringUtil.getBean(ITenantService.class).getByCode(telnetId);
        return EncryptionUtils.aesEncrypt(JSON.toJSONString(data), tenant.getSerKey(), tenant.getIv());
    }


}
