package com.smart.canteen.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 表单提交user
 *
 * @author lc
 * @date 2020/3/2下午 8:03
 */
@ApiModel
@Data
public class UserForm implements Serializable {

    public interface Insert {

    }

    public interface Update {

    }

    @NotNull(groups = Update.class)
    @ApiModelProperty(value = "主键id")
    private Long id;

    @NotEmpty(groups = {Insert.class, Update.class})
    @Length(min = 2, max = 40, groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "工号")
    private String account;

    @NotEmpty(groups = {Insert.class, Update.class})
    @Length(min = 2, max = 40, groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "姓名")
    private String name;

    @NotEmpty(groups = {Insert.class, Update.class})
    @Length(min = 6, max = 20, groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "密码")
    private String password;
}
