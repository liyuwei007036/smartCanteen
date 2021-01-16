package com.smart.canteen.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 租户配置
 * </p>
 *
 * @author lc
 * @since 2021-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tenant")
@ApiModel(value = "Tenant对象", description = "租户配置")
public class Tenant {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "租户名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "租户编码")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "租户密钥")
    @TableField("ser_key")
    private String serKey;

    @ApiModelProperty(value = "偏移量")
    @TableField("iv")
    private String iv;

    @ApiModelProperty(value = "加密类型")
    @TableField("type")
    private String type;

}
