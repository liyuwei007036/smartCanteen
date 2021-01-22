package com.smart.canteen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.smart.canteen.enums.LoginEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import live.lumia.utils.IPV4Utils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Profile;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author lc
 * @since 2020-03-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("login_log")
@ApiModel(value = "LoginLog对象", description = "用户登录记录")
public class LoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "员工id")
    @TableField("emp_no")
    private String empNo;

    @ApiModelProperty(value = "员工名称")
    @TableField("emp_name")
    private String empName;

    @ApiModelProperty(value = "登录时间")
    @TableField("login_time")
    private Date loginTime;

    @ApiModelProperty(value = "登录ip")
    @TableField("ip")
    private String ip;

    @ApiModelProperty(value = "类型")
    @TableField("type")
    private LoginEnum type;

    public String getIp() {
        if (!StringUtils.isEmpty(ip)) {
            return ip + "/" + IPV4Utils.getLocationAndOperator(ip);
        }
        return ip;
    }
}
