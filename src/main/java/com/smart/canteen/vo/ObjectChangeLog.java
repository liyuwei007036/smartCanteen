package com.smart.canteen.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lc
 * @date 2020/3/16下午 12:43
 */
@Data
public class ObjectChangeLog implements Serializable {

    private Long id;

    private List<ChangeLog> logList;


}
