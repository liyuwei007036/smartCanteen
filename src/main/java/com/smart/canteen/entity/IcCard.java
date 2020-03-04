package com.smart.canteen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * iC卡
 * </p>
 *
 * @author lc
 * @since 2020-03-03
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ic_card")
@ApiModel(value = "IcCard对象", description = "iC卡")
public class IcCard extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "卡号")
    @TableField("no")
    private String no;

    @ApiModelProperty(value = "员工工号")
    @TableField("employee_no")
    private String employeeNo;

    @ApiModelProperty(value = "员工姓名")
    @TableField("employee_name")
    private String employeeName;

    @ApiModelProperty(value = "状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "类型")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "员工id")
    @TableField("employee_id")
    private Long employeeId;

    @ApiModelProperty(value = "工本费")
    @TableField("expense")
    private Double expense;

    @ApiModelProperty(value = "押金")
    @TableField("deposit")
    private Double deposit;

    @ApiModelProperty(value = "开卡金额")
    @TableField("open_card_amount")
    private Double openCardAmount;

    @ApiModelProperty(value = "当前余额")
    @TableField("current_balance")
    private Double currentBalance;

    @ApiModelProperty(value = "最低余额")
    @TableField("minimum_balance")
    private Double minimumBalance;

    @ApiModelProperty(value = "有效期")
    @TableField("validity_time")
    private LocalDateTime validityTime;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

}
