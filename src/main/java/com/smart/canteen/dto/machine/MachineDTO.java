package com.smart.canteen.dto.machine;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 卡机
 * </p>
 *
 * @author lc
 * @since 2020-03-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Machine对象", description = "卡机")
public class MachineDTO implements Serializable {

    public interface Insert {

    }

    public interface Update {

    }

    @NotNull(groups = {Update.class})
    @ApiModelProperty(value = "主键")
    private Long id;

    @Length(max = 10, groups = {Insert.class, Update.class})
    @NotEmpty(groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "编号")
    private String code;

    @Length(max = 40, groups = {Insert.class, Update.class})
    @NotEmpty(groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "名称")
    private String name;

    @Length(max = 40, groups = {Insert.class, Update.class})
    @NotEmpty(groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "位置")
    private String location;

    @Length(max = 100, groups = {Insert.class, Update.class})
    @ApiModelProperty(value = "描述")
    private String description;
}
