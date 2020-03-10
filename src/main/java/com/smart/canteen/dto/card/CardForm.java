package com.smart.canteen.dto.card;

import com.smart.canteen.enums.CardTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lc
 * @date 2020/3/7下午 11:17
 */
@ApiModel
@Data
public class CardForm implements Serializable {


    public interface Insert {

    }

    public interface Update {

    }

    @NotNull(groups = Update.class)
    @ApiModelProperty(value = "主键Id")
    private Long id;

    @NotNull(groups = Insert.class)
    @ApiModelProperty(value = "卡号")
    private String no;

    @NotNull(groups = {Insert.class, Update.class})
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

    @Future
    @ApiModelProperty(value = "有效期")
    private Date validityTime;

}
