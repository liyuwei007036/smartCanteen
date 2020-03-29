package com.smart.canteen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lc
 * @date 2020/3/21下午 11:45
 */
@Data
@AllArgsConstructor
public class SummaryData implements Serializable {

    private String key;

    private Double value;
}
