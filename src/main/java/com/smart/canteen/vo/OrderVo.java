package com.smart.canteen.vo;

import com.smart.canteen.enums.OrderChannelEnum;
import com.smart.canteen.enums.OrderTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lc
 * @date 2020/3/8下午 10:22
 */
@Data
@ApiModel
public class OrderVo implements Serializable {

    @ApiModelProperty(value = "员工姓名")
    private String employeeName;

    @ApiModelProperty(value = "员工工号")
    private String employeeNo;

    @ApiModelProperty(value = "卡号")
    private String cardNo;

    @ApiModelProperty(value = "消费金额")
    private Double money;

    @ApiModelProperty(value = "消费后金额")
    private Double balance;

    @ApiModelProperty(value = "消费渠道")
    private OrderChannelEnum channel;

    @ApiModelProperty(value = "消费类型")
    private OrderTypeEnum type;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人姓名")
    private String creatorName;

    @ApiModelProperty(value = "终端编号")
    private String machineNo;

    @ApiModelProperty(value = "创建人工号")
    private String creatorAccount;

    @ApiModelProperty(value = "描述")
    private String description;
}
