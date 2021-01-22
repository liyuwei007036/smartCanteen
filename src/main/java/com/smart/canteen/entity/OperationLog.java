package com.smart.canteen.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 操作日志
 * </p>
 *
 * @author lc
 * @since 2020-03-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("operation_log")
@ApiModel(value = "OperationLog对象", description = "操作日志")
public class OperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "模块")
    @TableField("module")
    private String module;

    @ApiModelProperty(value = "动作")
    @TableField("action")
    private String action;

    @ApiModelProperty(value = "操作人工号")
    @TableField("emp_no")
    private String empNo;

    @ApiModelProperty(value = "操作人姓名")
    @TableField("emp_name")
    private String empName;

    @ApiModelProperty(value = "操作时间")
    @TableField("operation_time")
    private Date operationTime;

    @ApiModelProperty(value = "详情")
    @TableField("detail")
    private String detail;

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
