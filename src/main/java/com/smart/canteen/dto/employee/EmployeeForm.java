package com.smart.canteen.dto.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smart.canteen.enums.CardTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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

    @NotNull(groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "组织ID")
    private Long originationId;

    @ApiModelProperty(value = "组织")
    private String originationName;

    @ApiModelProperty(value = "角色Id列表")
    private List<Long> roles;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "确认密码")
    private String confirmPassword;

    @NotNull(groups = Update.class)
    @ApiModelProperty(value = "主键Id")
    private Long cardId;

    @NotNull(groups = Insert.class)
    @ApiModelProperty(value = "卡号")
    private String cardNo;

    @NotNull(groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "卡类型")
    private CardTypeEnum type;

    @Min(value = 0, groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "工本费")
    private Double expense;

    @Min(value = 0, groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "押金")
    private Double deposit;

    @Min(value = 0, groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "开卡金额")
    private Double openCardAmount;

    @Min(value = 0, groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "卡最低余额")
    private Double minimumBalance;

    @Future(groups = {Insert.class, Update.class})
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "有效期")
    private Date validityTime;

}
