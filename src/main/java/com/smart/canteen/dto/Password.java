package com.smart.canteen.dto;

import com.lc.core.utils.EncryptionUtils;
import lombok.Data;

import java.util.UUID;

/**
 * @author lc
 * @date 2020/3/3下午 10:29
 */
@Data
public class Password {

    private String password;

    private String salt;

    private Password() {

    }

    public Password(String password) {
        this.salt = UUID.randomUUID().toString().replaceAll("-", "");
        this.password = EncryptionUtils.md5(password, salt, false);
    }
}
