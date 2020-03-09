package com.smart.canteen.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @author lc
 * @date 2020/3/9下午 10:15
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class OriginationVo {

    private Long id;

    private String label;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<OriginationVo> children;
}
