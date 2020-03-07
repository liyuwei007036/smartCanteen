package com.smart.canteen.vo;

import com.smart.canteen.dto.employee.EmployeeForm;
import com.smart.canteen.enums.CardTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author lc
 * @date 2020/3/7下午 11:59
 */
@ApiModel
@Data
public class EmployeeVO implements Serializable {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "工号")
    private String no;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "身份证号")
    private String idCard;

    @ApiModelProperty(value = "组织ID")
    private Long originationId;

    @ApiModelProperty(value = "组织")
    private String originationName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "角色")
    private List<Map<String, Object>> roles;

    @ApiModelProperty(value = "卡Id")
    private Long cardId;

    @ApiModelProperty(value = "卡号")
    private String cardNo;

    @ApiModelProperty(value = "卡类型")
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
    private LocalDateTime validityTime;


}
