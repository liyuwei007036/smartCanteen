package com.smart.canteen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.smart.canteen.enums.CardAccountEnum;
import com.smart.canteen.enums.CardStatusEnum;
import com.smart.canteen.enums.CardTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

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

    @ApiModelProperty(value = "卡片主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "卡号")
    @TableField(value = "no")
    private String no;

    @ApiModelProperty(value = "员工id")
    @TableField(value = "employee_id")
    private Long employeeId;

    @ApiModelProperty(value = "员工工号")
    @TableField(value = "employee_no")
    private String employeeNo;

    @ApiModelProperty(value = "员工姓名")
    @TableField(value = "employee_name")
    private String employeeName;

    @ApiModelProperty(value = "状态")
    @TableField(value = "status")
    private CardStatusEnum status;

    @ApiModelProperty(value = "类型")
    @TableField(value = "type")
    private CardTypeEnum type;

    @ApiModelProperty(value = "账户状态")
    @TableField(value = "account_status")
    private CardAccountEnum accountStatus;

    @ApiModelProperty(value = "工本费")
    @TableField(value = "expense")
    private Double expense;

    @ApiModelProperty(value = "押金")
    @TableField(value = "deposit")
    private Double deposit;

    @ApiModelProperty(value = "开卡金额")
    @TableField(value = "open_card_amount")
    private Double openCardAmount;

    @ApiModelProperty(value = "当前余额")
    @TableField(value = "current_balance")
    private Double currentBalance;

    @ApiModelProperty(value = "最低余额")
    @TableField(value = "minimum_balance")
    private Double minimumBalance;

    @ApiModelProperty(value = "有效期")
    @TableField(value = "validity_time")
    private Date validityTime;


}
