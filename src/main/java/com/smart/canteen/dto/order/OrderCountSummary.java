package com.smart.canteen.dto.order;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lc
 * @date 2020/3/17上午 12:01
 */
@Data
public class OrderCountSummary implements Serializable {

    private String date;

    private Long count;
}
