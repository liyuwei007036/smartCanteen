package com.smart.canteen.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import live.lumia.utils.ObjectUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author lc
 * @date 2020/3/20下午 9:15
 */
@ApiModel
@Data
public class CardUserVo implements Serializable {

    @ApiModelProperty(value = "卡余额")
    private Long cardId;

    @JsonIgnore
    @ApiModelProperty(value = "卡余额")
    private Long empId;

    @ApiModelProperty(value = "姓名")
    private String empName;

    @ApiModelProperty(value = "账号")
    private String empNo;

    @ApiModelProperty(value = "身份证号")
    private String idCard;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "组织名称")
    private String orgName;

    @ApiModelProperty(value = "角色名称")
    private Set<String> roleName;

    @ApiModelProperty(value = "卡号")
    private String cardNo;

    @ApiModelProperty(value = "卡余额")
    private Date validityTime;

    @ApiModelProperty(value = "卡有效期")
    private Double currentBalance;

    public Double getCurrentBalance() {
        return ObjectUtil.getDouble(currentBalance);
    }
}
