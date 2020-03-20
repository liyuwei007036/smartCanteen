package com.smart.canteen.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lc
 * @date 2020/3/20下午 9:15
 */
@ApiModel
@Data
public class CardUserVo implements Serializable {

    @ApiModelProperty(value = "卡余额")
    private Long cardId;

    @ApiModelProperty(value = "姓名")
    private String empName;

    @ApiModelProperty(value = "账号")
    private String empNo;

    @ApiModelProperty(value = "身份证号")
    private String idCard;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "卡号")
    private String cardNo;

    @ApiModelProperty(value = "卡余额")
    private Date validityTime;

    @ApiModelProperty(value = "卡有效期")
    private Double currentBalance;

}
