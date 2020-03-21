package com.smart.canteen.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lc
 * @date 2020/3/18下午 9:02
 */
@AllArgsConstructor
@Data
public class SummaryDTO implements Serializable {

    private JSONObject data;

    private Double total;

    private Double avg;

}
