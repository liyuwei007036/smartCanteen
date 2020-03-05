package com.smart.canteen.dto.role;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author lc
 * @date 2020/3/4下午 8:56
 */
@ApiModel
@Data
@NotNull
public class RoleForm implements Serializable {

    public interface Insert {

    }

    public interface Update {

    }

    @NotNull(groups = {Update.class})
    private Long id;

    @NotEmpty(groups = {Insert.class, Update.class})
    @Length(min = 2, max = 40, groups = {Insert.class, Update.class})
    private String name;
}
