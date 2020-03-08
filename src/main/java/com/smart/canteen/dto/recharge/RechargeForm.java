package com.smart.canteen.dto.recharge;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author lc
 * @date 2020/3/8下午 5:58
 */
@Data
@ApiModel
public class RechargeForm implements Serializable {

    @Size(min = 1)
    @NotNull
    @ApiModelProperty(value = "卡片Id")
    private List<Long> cardIds;

    @NotNull
    @ApiModelProperty(value = "充值类型")
    private Integer rechargeType;

    @Min(0)
    @NotNull
    @ApiModelProperty(value = "充值金额")
    private Double money;
}
