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
 * 组织
 * </p>
 *
 * @author lc
 * @since 2020-03-02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("origination")
@ApiModel(value = "Origination对象", description = "组织")
public class Origination extends BaseEntity {

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

}
