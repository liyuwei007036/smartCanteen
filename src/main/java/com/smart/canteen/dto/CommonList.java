package com.smart.canteen.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lc
 * @date 2020/3/4下午 8:48
 */
@AllArgsConstructor
@ApiModel
@Data
public class CommonList<T extends Serializable> implements Serializable {

    private Boolean hasNext;

    private Long total;

    private Long currentPage;

    private List<T> data;
}
