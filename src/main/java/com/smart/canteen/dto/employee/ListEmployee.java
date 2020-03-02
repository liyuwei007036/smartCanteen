package com.smart.canteen.dto.employee;

import com.smart.canteen.entity.Employee;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lc
 * @date 2020/3/2下午 9:10
 */
@Data
@AllArgsConstructor
@ApiModel
public class ListEmployee implements Serializable {

    private Boolean hasNext;

    private Long total;

    private Long currentPage;

    private List<Employee> employeeList;
}
