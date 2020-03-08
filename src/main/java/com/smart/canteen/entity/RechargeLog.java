package com.smart.canteen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.smart.canteen.enums.RechargeTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 充值记录
 * </p>
 *
 * @author lc
 * @since 2020-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("recharge_log")
@ApiModel(value = "RechargeLog对象", description = "充值记录")
public class RechargeLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "卡id")
    @TableField("card_id")
    private Long cardId;

    @ApiModelProperty(value = "卡号")
    @TableField(value = "card_no")
    private String cardNo;

    @ApiModelProperty(value = "员工工号")
    @TableField(value = "employee_no")
    private String employeeNo;

    @ApiModelProperty(value = "员工姓名")
    @TableField(value = "employee_name")
    private String employeeName;

    @ApiModelProperty(value = "充值金额")
    @TableField("money")
    private Double money;

    @ApiModelProperty(value = "充值后金额")
    @TableField("balance")
    private Double balance;

    @ApiModelProperty(value = "充值类型")
    @TableField("type")
    private RechargeTypeEnum type;


}
