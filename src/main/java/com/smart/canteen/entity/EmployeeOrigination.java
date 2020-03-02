package com.smart.canteen.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 员工组织关系
 * </p>
 *
 * @author lc
 * @since 2020-03-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("employee_origination")
@ApiModel(value = "EmployeeOrigination对象", description = "员工组织关系")
public class EmployeeOrigination extends Model<EmployeeOrigination> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "员工id")
    @TableField("employee_id")
    private Long employeeId;

    @ApiModelProperty(value = "员工工号")
    @TableField("employee_no")
    private String employeeNo;

    @ApiModelProperty(value = "员工姓名")
    @TableField("employee_name")
    private String employeeName;

    @ApiModelProperty(value = "组织id")
    @TableField("orgination_id")
    private Long orginationId;

    @ApiModelProperty(value = "组织名称")
    @TableField("origination_name")
    private String originationName;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    @TableField("creator_id")
    private Long creatorId;

    @ApiModelProperty(value = "创建人工号")
    @TableField("creator_no")
    private String creatorNo;

    @ApiModelProperty(value = "创建人姓名")
    @TableField("creator_name")
    private String creatorName;

    @ApiModelProperty(value = "最近更新时间")
    @TableField("last_update_time")
    private LocalDateTime lastUpdateTime;

    @ApiModelProperty(value = "最近更新人id")
    @TableField("last_update_id")
    private Long lastUpdateId;

    @ApiModelProperty(value = "最近更新人工号")
    @TableField("last_update_no")
    private String lastUpdateNo;

    @ApiModelProperty(value = "最近更新人姓名")
    @TableField("last_update_name")
    private String lastUpdateName;

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
