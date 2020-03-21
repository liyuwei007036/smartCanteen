package com.smart.canteen.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lc
 * @date 2020/3/22上午 1:10
 */
@AllArgsConstructor
@Data
public class SummaryTotal implements Serializable {
    private Double total;
    private Double avg;
}
