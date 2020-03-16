package com.smart.canteen.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty("老密码")
    private String oldPassword;

    @Length(min = 6, max = 20)
    @NotEmpty
    @ApiModelProperty("新密码")
    private String newPassword;

    @Length(min = 6, max = 20)
    @NotEmpty
    @ApiModelProperty("新密码确认")
    private String confirmPassword;

}
