package com.smart.canteen.dto.card;

import com.smart.canteen.enums.CardTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lc
 * @date 2020/3/7下午 11:17
 */
@Data
public class CardForm implements Serializable {


    public interface Insert {

    }

    public interface Update {

    }

    @NotNull(groups = CardForm.Update.class)
    @ApiModelProperty(value = "主键Id")
    private Long id;

    @NotNull(groups = CardForm.Insert.class)
    @ApiModelProperty(value = "卡号")
    private String no;

    @NotNull(groups = {CardForm.Insert.class})
    @ApiModelProperty(value = "类型")
    private CardTypeEnum type;

    @ApiModelProperty(value = "工本费")
    private Double expense;

    @ApiModelProperty(value = "押金")
    private Double deposit;

    @ApiModelProperty(value = "开卡金额")
    private Double openCardAmount;

    @ApiModelProperty(value = "卡最低余额")
    private Double minimumBalance;

    @ApiModelProperty(value = "有效期")
    private LocalDateTime validityTime;

}
