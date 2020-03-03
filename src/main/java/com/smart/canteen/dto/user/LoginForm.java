package com.smart.canteen.dto.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author lc
 * @date 2020/3/2下午 12:04
 */
@ApiModel
@Data
public class LoginForm implements Serializable {

    @NotEmpty
    private String account;

    @NotEmpty
    private String password;

    @NotEmpty
    private String code;
}
