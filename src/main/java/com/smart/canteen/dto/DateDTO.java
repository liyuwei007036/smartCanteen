package com.smart.canteen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author lc
 * @date 2020/3/29下午 4:21
 */
@AllArgsConstructor
@Data
public class DateDTO {

    private Date start;
    private Date end;

}
