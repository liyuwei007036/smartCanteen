package com.smart.canteen.dto.role;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * @author lc
 * @date 2020/3/11下午 9:49
 */

@Data
public class PermissionForm implements Serializable {

    @NotNull
    @ApiModelProperty("角色的权限集合")
    private Set<String> permissions;

    @Min(0)
    @NotNull
    @ApiModelProperty("角色ID")
    private Long id;
}
