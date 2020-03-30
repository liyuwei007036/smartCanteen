package com.smart.canteen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author lc
 * @date 2020/3/18下午 9:02
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Data
public class SummaryDTO implements Serializable {

    private List<SummaryData> data;

    private Double total;

    private Double recharge;

    private Double fillBuckle;

    private Double refund;

    private Double avg;

    private Map<String, Long> lineChat;

}
