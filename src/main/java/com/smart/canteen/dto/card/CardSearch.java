package com.smart.canteen.dto.card;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询DTO
 *
 * @author lc
 * @date 2020/3/2下午 8:41
 */
@Data
@ApiModel
public class CardSearch implements Serializable {

    @ApiModelProperty(value = "姓名")
    private String empName;

    @ApiModelProperty(value = "工号")
    private String empNo;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "卡号")
    private String cardNo;

    @ApiModelProperty(value = "卡状态")
    private String cardStatus;

    @ApiModelProperty(value = "卡账户状态")
    private String accountStatus;

    @ApiModelProperty(value = "开始时间")
    private Date start;

    @ApiModelProperty(value = "结束时间")
    private Date end;

    @ApiModelProperty(value = "页数")
    private Integer page = 1;

    @ApiModelProperty(value = "每页数量")
    private Integer size = 10;

    public void setPage(Integer page) {
        if (page == null || page <= 0) {
            this.page = 1;
        } else {
            this.page = page;
        }
    }

    public void setSize(Integer size) {
        int maxSize = 100;
        if (size == null || size <= 0) {
            this.size = 10;
        } else if (size > maxSize) {
            this.size = maxSize;
        } else {
            this.size = size;
        }
    }

}
