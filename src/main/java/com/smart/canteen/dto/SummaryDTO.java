package com.smart.canteen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author lc
 * @date 2020/3/18下午 9:02
 */
@AllArgsConstructor
@Data
public class SummaryDTO implements Serializable {

    private Map<String, Double> data;

    private Double total;

}
