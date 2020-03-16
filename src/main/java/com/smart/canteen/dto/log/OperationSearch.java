package com.smart.canteen.dto.log;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lc
 * @date 2020/3/16下午 10:40
 */
@ApiModel
@Data
public class OperationSearch implements Serializable {

    @ApiModelProperty(value = "模块")
    private String module;

    @ApiModelProperty(value = "动作")
    private String action;

    @ApiModelProperty(value = "用户")
    private String empName;

    @ApiModelProperty(value = "结束时间")
    private Date end;

    @ApiModelProperty(value = "开始时间")
    private Date start;

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
