package com.smart.canteen.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lc
 * @date 2020/3/8上午 1:31
 */
@Data
public class CardVo implements Serializable {

    private String no;

    private String empNo;

    private String empName;

    private Double currentBalance;
}
