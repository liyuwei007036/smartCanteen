package com.smart.canteen.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
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
    @TableField(value = "name", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY)
    private String name;

    @JsonIgnore
    @ApiModelProperty(value = "上级部门id")
    @TableField(value = "parent_id", insertStrategy = FieldStrategy.NOT_NULL)
    private Long parentId;

    @JsonIgnore
    @ApiModelProperty(value = "级别")
    @TableField(value = "level", insertStrategy = FieldStrategy.NOT_NULL, updateStrategy = FieldStrategy.NOT_EMPTY)
    private Long level;

    @ApiModelProperty(value = "部门代码")
    @TableField(value = "code", insertStrategy = FieldStrategy.NOT_EMPTY, updateStrategy = FieldStrategy.NOT_EMPTY)
    private String code;

    @ApiModelProperty(value = "描述")
    @TableField(value = "description")
    private String description;

    @JsonIgnore
    @ApiModelProperty(value = "路径")
    @TableField(value = "path")
    private String path;

}
