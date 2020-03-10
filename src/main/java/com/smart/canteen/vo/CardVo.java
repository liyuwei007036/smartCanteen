package com.smart.canteen.vo;

import com.smart.canteen.enums.CardAccountEnum;
import com.smart.canteen.enums.CardStatusEnum;
import com.smart.canteen.enums.CardTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lc
 * @date 2020/3/8上午 1:31
 */
@Data
@ApiModel
public class CardVo implements Serializable {

    @ApiModelProperty(value = "ic卡Id")
    private Long id;

    @ApiModelProperty(value = "用户Id")
    private Long empId;

    @ApiModelProperty(value = "姓名")
    private String empName;

    @ApiModelProperty(value = "账号")
    private String empNo;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "卡号")
    private String cardNo;

    @ApiModelProperty(value = "卡类型")
    private CardTypeEnum cardType;

    @ApiModelProperty(value = "押金")
    private Double deposit;

    @ApiModelProperty(value = "卡余额")
    private Double currentBalance;

    @ApiModelProperty(value = "组织名称")
    private String originationName;

    @ApiModelProperty(value = "卡状态")
    private CardStatusEnum status;

    @ApiModelProperty(value = "账户状态")
    private CardAccountEnum accountStatus;

    @ApiModelProperty(value = "有效期")
    private Date validityTime;
}
