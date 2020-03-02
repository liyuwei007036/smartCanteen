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
 * 员工角色关系
 * </p>
 *
 * @author lc
 * @since 2020-03-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("employee_role")
@ApiModel(value = "EmployeeRole对象", description = "员工角色关系")
public class EmployeeRole extends Model<EmployeeRole> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "员工id")
    @TableField(value = "employee_id")
    private Long employeeId;

    @ApiModelProperty(value = "员工工号")
    @TableField(value = "employee_no")
    private String employeeNo;

    @ApiModelProperty(value = "员工姓名")
    @TableField(value = "employee_name")
    private String employeeName;

    @ApiModelProperty(value = "角色id")
    @TableField(value = "role_id")
    private Long roleId;

    @ApiModelProperty(value = "角色名称")
    @TableField(value = "role_name")
    private String roleName;

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
