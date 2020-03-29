package com.smart.canteen.dto;

import com.smart.canteen.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author lc
 * @date 2020/3/29下午 5:22
 */
@AllArgsConstructor
@Data
public class OrderSummaryDTO {

    private Double total;

    private Double fillBuckle;

    private List<Order> orders;

    private Double avg;


}
