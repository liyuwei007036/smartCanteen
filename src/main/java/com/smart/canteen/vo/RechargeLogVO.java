package com.smart.canteen.vo;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smart.canteen.enums.RechargeTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lc
 * @date 2020/3/8下午 6:42
 */
@ApiModel
@Data
public class RechargeLogVO implements Serializable {

    @ApiModelProperty(value = "卡号")
    private String cardNo;

    @ApiModelProperty(value = "员工工号")
    private String employeeNo;

    @ApiModelProperty(value = "员工姓名")
    private String employeeName;

    @ApiModelProperty(value = "充值金额")
    private Double money;

    @ApiModelProperty(value = "充值后金额")
    private Double balance;

    @ApiModelProperty(value = "充值类型")
    private RechargeTypeEnum type;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private Long creatorId;

    @ApiModelProperty(value = "创建人工号")
    private String creatorAccount;

}
