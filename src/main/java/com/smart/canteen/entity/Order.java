package com.smart.canteen.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@TableName("order")
@ApiModel(value = "order", description = "订单")
public class Order extends Model<Order> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "卡id")
    @TableField("card_id")
    private Long cardId;

    @ApiModelProperty(value = "卡号")
    @TableField("card_no")
    private String cardNo;

    @ApiModelProperty(value = "消费金额")
    @TableField("money")
    private Double money;

    @ApiModelProperty(value = "消费后金额")
    @TableField("balance")
    private Double balance;

    @ApiModelProperty(value = "消费类型")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "员工工号")
    @TableField("employee_no")
    private String employeeNo;

    @ApiModelProperty(value = "员工姓名")
    @TableField("employee_name")
    private String employeeName;

    @ApiModelProperty(value = "消费时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "逻辑锁")
    @TableField("version")
    @Version
    private Long version;

    @ApiModelProperty(value = "是否删除 0 1")
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
