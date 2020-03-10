package com.smart.canteen.dto.card;

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
    private Long cardId;

    @NotNull
    @Min(value = 0, message = "补扣金额不能小于0")
    private Double money;
}
