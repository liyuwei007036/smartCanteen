package com.smart.canteen.vo;

import com.smart.canteen.enums.CardTypeEnum;
import com.smart.canteen.enums.EmployeeStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author lc
 * @date 2020/3/7下午 11:59
 */
@ApiModel
@Data
public class EmployeeVO implements Serializable {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "工号")
    private String no;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "身份证号")
    private String idCard;

    @ApiModelProperty(value = "组织ID")
    private Long originationId;

    @ApiModelProperty(value = "组织")
    private String originationName;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "角色")
    private List<Long> roles;

    @ApiModelProperty(value = "卡Id")
    private Long cardId;

    @ApiModelProperty(value = "卡号")
    private String cardNo;

    @ApiModelProperty(value = "卡类型")
    private CardTypeEnum type;

    @ApiModelProperty(value = "工本费")
    private Double expense;

    @ApiModelProperty(value = "押金")
    private Double deposit;

    @ApiModelProperty(value = "开卡金额")
    private Double openCardAmount;

    @ApiModelProperty(value = "卡最低余额")
    private Double minimumBalance;

    @ApiModelProperty(value = "有效期")
    private Date validityTime;

    @ApiModelProperty(value = "状态")
    private EmployeeStatusEnum status;

}
