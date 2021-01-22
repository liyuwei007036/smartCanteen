package com.smart.canteen.vo;

import com.smart.canteen.enums.LoginEnum;
import io.swagger.annotations.ApiModelProperty;
import live.lumia.utils.IPV4Utils;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

@Data
public class LoginLogVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "员工id")
    private String empNo;

    @ApiModelProperty(value = "员工名称")
    private String empName;

    @ApiModelProperty(value = "登录时间")
    private Date loginTime;

    @ApiModelProperty(value = "登录ip")
    private String ip;

    @ApiModelProperty(value = "类型")
    private LoginEnum type;

    public String getIp() {
        if (!StringUtils.isEmpty(ip)) {
            return ip + " / " + IPV4Utils.getLocationAndOperator(ip);
        }
        return ip;
    }
}
