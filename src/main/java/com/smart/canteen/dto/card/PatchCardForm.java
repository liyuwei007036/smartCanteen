package com.smart.canteen.dto.card;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 补卡
 *
 * @author lc
 * @date 2020/3/2下午 8:41
 */
@Data
@ApiModel
public class PatchCardForm implements Serializable {

    public interface Insert {

    }

    @NotNull(groups = Insert.class)
    @ApiModelProperty(value = "老卡Id")
    private Long cardId;

    @NotNull(groups = Insert.class)
    @ApiModelProperty(value = "新卡号")
    private String cardNo;

}
