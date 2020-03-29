package com.smart.canteen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lc
 * @date 2020/3/29下午 5:22
 */
@AllArgsConstructor
@Data
public class RechargeSummaryDTO {

    private Double normal;

    private Double refund;
}
