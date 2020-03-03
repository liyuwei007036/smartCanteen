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
 *
 * </p>
 *
 * @author lc
 * @since 2020-03-03
 */
@Data
@TableName("employee")
@ApiModel(value = "Employee对象", description = "员工")
public class Employee  extends Model<Employee> implements BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "ic卡Id")
    @TableField(value = "card_id")
    private Long cardId;

    @ApiModelProperty(value = "卡号")
    @TableField(value = "card_no")
    private String cardNo;

    @ApiModelProperty(value = "工号")
    @TableField(value = "no")
    private String no;

    @ApiModelProperty(value = "姓名")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty(value = "手机号")
    @TableField(value = "mobile")
    private String mobile;

    @ApiModelProperty(value = "身份证号")
    @TableField(value = "id_card")
    private String idCard;

    @ApiModelProperty(value = "盐")
    @TableField(value = "salt")
    private String salt;

    @ApiModelProperty(value = "密码")
    @TableField(value = "password")
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
