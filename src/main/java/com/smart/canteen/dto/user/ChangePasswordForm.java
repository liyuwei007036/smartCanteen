package com.smart.canteen.dto.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author lc
 * @date 2020/3/2下午 12:04
 */
@ApiModel
@Data
public class ChangePasswordForm implements Serializable {

    @Length(min = 6, max = 20)
    @NotEmpty
    private String oldPassword;

    @Length(min = 6, max = 20)
    @NotEmpty
    private String newPassword;

    @Length(min = 6, max = 20)
    @NotEmpty
    private String confirmPassword;

}
