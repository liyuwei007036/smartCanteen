package com.smart.canteen.dto.role;

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
    private Set<String> permissions;

    @Min(0)
    @NotNull
    private Long id;
}
