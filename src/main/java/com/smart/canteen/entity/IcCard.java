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
 * iC卡
 * </p>
 *
 * @author lc
 * @since 2020-03-03
 */
@Data
@TableName("ic_card")
@ApiModel(value = "IcCard对象", description = "iC卡")
public class IcCard extends Model<IcCard> implements BaseEntity {

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

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    @TableField(value = "creator_id", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NEVER)
    private Long creatorId;

    @ApiModelProperty(value = "创建人工号")
    @TableField(value = "creator_account", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NEVER)
    private String creatorAccount;

    @ApiModelProperty(value = "创建人姓名")
    @TableField(value = "creator_name", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NEVER)
    private String creatorName;

    @ApiModelProperty(value = "最近更新时间")
    @TableField(value = "last_update_time")
    private LocalDateTime lastUpdateTime;

    @ApiModelProperty(value = "最近更新人id")
    @TableField(value = "last_update_id")
    private Long lastUpdateId;

    @ApiModelProperty(value = "最近更新人工号")
    @TableField(value = "last_update_account")
    private String lastUpdateAccount;

    @ApiModelProperty(value = "最近更新人姓名")
    @TableField(value = "last_update_name")
    private String lastUpdateName;

    @ApiModelProperty(value = "逻辑锁")
    @TableField(value = "version", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Long version;

    @ApiModelProperty(value = "是否删除 0 1")
    @TableField(value = "deleted", fill = FieldFill.INSERT)
    @TableLogic
    private Boolean deleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
