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
 * 岗位
 * </p>
 *
 * @author lc
 * @since 2020-03-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("role")
@ApiModel(value = "Role对象", description = "岗位")
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "名称")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    @TableField(value = "creator_id")
    private Long creatorId;

    @ApiModelProperty(value = "创建人工号")
    @TableField(value = "creator_no")
    private String creatorNo;

    @ApiModelProperty(value = "创建人姓名")
    @TableField(value = "creator_name")
    private String creatorName;

    @ApiModelProperty(value = "最近更新时间")
    @TableField(value = "last_update_time")
    private LocalDateTime lastUpdateTime;

    @ApiModelProperty(value = "最近更新人id")
    @TableField(value = "last_update_id")
    private Long lastUpdateId;

    @ApiModelProperty(value = "最近更新人工号")
    @TableField(value = "last_update_no")
    private String lastUpdateNo;

    @ApiModelProperty(value = "最近更新人姓名")
    @TableField(value = "last_update_name")
    private String lastUpdateName;

    @ApiModelProperty(value = "逻辑锁")
    @TableField(value = "version")
    @Version
    private Long version;

    @ApiModelProperty(value = "是否删除 0 1")
    @TableField(value = "deleted")
    @TableLogic
    private Boolean deleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
