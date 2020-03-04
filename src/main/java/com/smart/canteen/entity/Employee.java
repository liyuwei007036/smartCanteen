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
 * 员工
 * </p>
 *
 * @author lc
 * @since 2020-03-03
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("employee")
@ApiModel(value = "Employee对象", description = "员工")
public class Employee extends BaseEntity {

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "工号")
    @TableField(value = "no")
    private String no;

    @ApiModelProperty(value = "姓名")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty(value = "手机号")
    @TableField(value = "mobile")
    private String mobile;

    @ApiModelProperty(value = "身份证号")
    @TableField(value = "id_card")
    private String idCard;

    @ApiModelProperty(value = "ic卡Id")
    @TableField(value = "card_id")
    private Long cardId;

    @ApiModelProperty(value = "卡号")
    @TableField(value = "card_no")
    private String cardNo;

    @ApiModelProperty(value = "角色Id")
    @TableField(value = "role_id")
    private Long roleId;

    @ApiModelProperty(value = "角色")
    @TableField(value = "role_name")
    private String roleName;

    @ApiModelProperty(value = "组织ID")
    @TableField(value = "origination_id")
    private Long originationId;

    @ApiModelProperty(value = "组织")
    @TableField(value = "origination_name")
    private String originationName;

    @ApiModelProperty(value = "盐")
    @TableField(value = "salt")
    private String salt;

    @ApiModelProperty(value = "密码")
    @TableField(value = "password")
    private String password;

}
