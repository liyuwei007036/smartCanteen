package com.smart.canteen.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lc
 * @date 2020/3/3下午 10:18
 */
@Data
public class BaseEntity implements Serializable {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NEVER)
    private Date createTime;

    @JsonIgnore
    @ApiModelProperty(value = "创建人id")
    @TableField(value = "creator_id", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NEVER)
    private Long creatorId;

    @JsonIgnore
    @ApiModelProperty(value = "创建人工号")
    @TableField(value = "creator_account", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NEVER)
    private String creatorAccount;

    @JsonIgnore
    @ApiModelProperty(value = "创建人姓名")
    @TableField(value = "creator_name", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NEVER)
    private String creatorName;

    @JsonIgnore
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最近更新时间")
    @TableField(value = "last_update_time")
    private Date lastUpdateTime;

    @JsonIgnore
    @ApiModelProperty(value = "最近更新人id")
    @TableField(value = "last_update_id")
    private Long lastUpdateId;

    @JsonIgnore
    @ApiModelProperty(value = "最近更新人工号")
    @TableField(value = "last_update_account")
    private String lastUpdateAccount;

    @JsonIgnore
    @ApiModelProperty(value = "最近更新人姓名")
    @TableField(value = "last_update_name")
    private String lastUpdateName;

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


}
