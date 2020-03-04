package com.smart.canteen.dto.employee;

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
public class EmployeeForm implements Serializable {

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
    private String no;

    @NotEmpty(groups = {Insert.class, Update.class})
    @Length(min = 2, max = 40, groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "姓名")
    private String name;

    @NotEmpty(groups = {Insert.class, Update.class})
    @Length(min = 11, max = 11, groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "手机号")
    private String mobile;

    @NotEmpty(groups = {Insert.class, Update.class})
    @Length(min = 18, max = 18, groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "身份证号")
    private String idCard;

    @NotEmpty(groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "角色Id")
    private Long roleId;

    @ApiModelProperty(value = "角色")
    private String roleName;

    @NotEmpty(groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "组织ID")
    private Long originationId;

    @ApiModelProperty(value = "组织")
    private String originationName;

    @Length(min = 6, max = 20, groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "密码")
    private String password;

    @Length(min = 6, max = 20, groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "确认密码")
    private String confirmPassword;

}
