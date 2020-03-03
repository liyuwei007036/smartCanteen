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
 * 组织
 * </p>
 *
 * @author lc
 * @since 2020-03-02
 */
@Data
@TableName("origination")
@ApiModel(value = "Origination对象", description = "组织")
public class Origination extends Model<Origination> implements BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "名称")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty(value = "上级部门id")
    @TableField(value = "parent_id")
    private Long parentId;

    @ApiModelProperty(value = "上级部门名称")
    @TableField(value = "parent_name")
    private String parentName;

    @ApiModelProperty(value = "路径")
    @TableField(value = "path")
    private String path;

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
