package com.smart.canteen.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.smart.canteen.entity.Tenant;
import com.smart.canteen.service.ITenantService;
import live.lumia.config.secret.DefaultAesDecrypt;
import live.lumia.enums.BaseErrorEnums;
import live.lumia.error.BaseException;
import live.lumia.utils.EncryptionUtils;
import live.lumia.utils.SpringUtil;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Objects;


/**
 * @author liyuwei
 */
@Profile({"wj"})
@Component
public class DefaultAesDecryptImpl implements DefaultAesDecrypt {


    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public String decrypt(String telnetId, String data) {
        try {
            Tenant tenant = SpringUtil.getBean(ITenantService.class).getByCode(telnetId);
            if (Objects.isNull(tenant)) {
                throw new BaseException(BaseErrorEnums.NO_PERMISSION);
            }
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
        if (Objects.isNull(tenant)) {
            throw new BaseException(BaseErrorEnums.NO_PERMISSION);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setDateFormat(DATE_FORMAT);
        String string = mapper.writeValueAsString(data);
        return EncryptionUtils.aesEncrypt(string, tenant.getSerKey(), tenant.getIv());
    }


}
