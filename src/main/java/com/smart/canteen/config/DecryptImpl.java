package com.smart.canteen.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.smart.canteen.entity.Tenant;
import com.smart.canteen.service.ITenantService;
import live.lumia.config.DefaultAesDecrypt;
import live.lumia.utils.EncryptionUtils;
import live.lumia.utils.SpringUtil;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;

public class DecryptImpl implements DefaultAesDecrypt {


    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


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
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setDateFormat(dateFormat);
        String string = mapper.writeValueAsString(data);
        return EncryptionUtils.aesEncrypt(string, tenant.getSerKey(), tenant.getIv());
    }


}
