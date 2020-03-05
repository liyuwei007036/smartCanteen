package com.smart.canteen.dto.origination;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询DTO
 *
 * @author lc
 * @date 2020/3/2下午 8:41
 */
@Data
@ApiModel
public class OriginationSearch implements Serializable {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "组织代码")
    private String code;

    @ApiModelProperty(value = "页数")
    private Integer page;

    @ApiModelProperty(value = "每页数量")
    private Integer size;

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
