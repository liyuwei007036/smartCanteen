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
 * 岗位
 * </p>
 *
 * @author lc
 * @since 2020-03-02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("role")
@ApiModel(value = "Role对象", description = "岗位")
public class Role extends BaseEntity {

    @ApiModelProperty(value = "主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "名称")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty(value = "能否删除")
    @TableField(value = "can_edit")
    private Boolean canEdit = true;

    @ApiModelProperty(value = "下拉是否显示")
    @TableField(value = "can_select")
    private Boolean canSelect = true;

    @ApiModelProperty(value = "是否默认角色")
    @TableField(value = "is_default")
    private Boolean isDefault = false;

}
