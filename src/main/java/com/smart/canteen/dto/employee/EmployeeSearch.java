package com.smart.canteen.dto.employee;

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
public class EmployeeSearch implements Serializable {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "工号")
    private String no;

    @ApiModelProperty(value = "卡号")
    private String cardNo;

    @ApiModelProperty(value = "人员状态")
    private String status;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "组织")
    private String orgName;

    @ApiModelProperty(value = "卡有效期")
    private String cardTimeStart;

    @ApiModelProperty(value = "卡有效期end")
    private String cardTimeEnd;

    @ApiModelProperty(value = "创建时间")
    private String createStart;

    @ApiModelProperty(value = "创建时间end")
    private String createEnd;


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
