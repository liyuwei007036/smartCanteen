package com.smart.canteen.vo;

import com.smart.canteen.dto.SummaryDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author lc
 * @date 2020/3/22下午 12:17
 */
@Data
public class SummaryVO implements Serializable {

    private SummaryDTO year;

    private SummaryDTO month;

    private SummaryDTO day;

    private Map<String, Long> line;
}
