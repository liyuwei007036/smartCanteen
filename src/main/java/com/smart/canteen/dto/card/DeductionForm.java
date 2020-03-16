package com.smart.canteen.dto.card;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author lc
 * @date 2020/3/10下午 10:09
 */
@Data
public class DeductionForm implements Serializable {

    @NotNull
    @ApiModelProperty("卡片ID")
    private Long cardId;

    @NotNull
    @ApiModelProperty("补扣金额")
    @Min(value = 0, message = "补扣金额不能小于0")
    private Double money;
}
