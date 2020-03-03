package com.smart.canteen.dto.user;

import com.smart.canteen.entity.User;
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
public class ListUser implements Serializable {

    private Boolean hasNext;

    private Long total;

    private Long currentPage;

    private List<User> userList;
}
