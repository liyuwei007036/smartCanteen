package com.smart.canteen.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smart.canteen.enums.OrderChannelEnum;
import com.smart.canteen.enums.OrderTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
@TableName("c_order")
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

    @ApiModelProperty(value = "机器编号")
    @TableField("machine_no")
    private String machineNo;

    @ApiModelProperty(value = "消费金额")
    @TableField(value = "money", insertStrategy = FieldStrategy.NOT_NULL)
    private Double money;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "")
    @ApiModelProperty(value = "消费后金额")
    @TableField("balance")
    private Double balance;

    @ApiModelProperty(value = "消费渠道")
    @TableField(value = "channel", insertStrategy = FieldStrategy.NOT_NULL)
    private OrderChannelEnum channel;

    @ApiModelProperty(value = "消费类型")
    @TableField(value = "type", insertStrategy = FieldStrategy.NOT_NULL)
    private OrderTypeEnum type;


    @ApiModelProperty(value = "员工工号")
    @TableField(value = "employee_no", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String employeeNo;

    @ApiModelProperty(value = "员工姓名")
    @TableField(value = "employee_name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String employeeName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "消费时间")
    @TableField("create_time")
    private Date createTime;

    @JsonIgnore
    @ApiModelProperty(value = "创建人id")
    @TableField(value = "creator_id")
    private Long creatorId;

    @JsonIgnore
    @ApiModelProperty(value = "创建人工号")
    @TableField(value = "creator_account")
    private String creatorAccount;

    @JsonIgnore
    @ApiModelProperty(value = "创建人姓名")
    @TableField(value = "creator_name")
    private String creatorName;


    @JsonIgnore
    @ApiModelProperty(value = "逻辑锁")
    @TableField(value = "version", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Long version;

    @JsonIgnore
    @ApiModelProperty(value = "是否删除 0 1")
    @TableField(value = "deleted", fill = FieldFill.INSERT)
    @TableLogic
    private Boolean deleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
