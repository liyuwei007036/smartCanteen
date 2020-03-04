package com.smart.canteen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 员工组织关系
 * </p>
 *
 * @author lc
 * @since 2020-03-02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("employee_origination")
@ApiModel(value = "EmployeeOrigination对象", description = "员工组织关系")
public class EmployeeOrigination extends BaseEntity {

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
    @TableField("origination_id")
    private Long originationId;

    @ApiModelProperty(value = "组织名称")
    @TableField("origination_name")
    private String originationName;

}
