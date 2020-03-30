package com.smart.canteen.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lc
 * @date 2020/3/30下午 7:58
 */
@Data
public class SummarySearchDTO implements Serializable {

    private Date start;

    private Date end;
}
