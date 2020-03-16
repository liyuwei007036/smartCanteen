package com.smart.canteen.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lc
 * @date 2020/3/16下午 12:42
 */
@Data
public class ChangeLog implements Serializable {

    private String name;

    private String oldValue;

    private String newValue;
}
