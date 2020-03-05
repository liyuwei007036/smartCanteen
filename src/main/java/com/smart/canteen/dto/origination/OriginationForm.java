package com.smart.canteen.dto.origination;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.smart.canteen.dto.employee.EmployeeForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author lc
 * @date 2020/3/5下午 10:56
 */
@ApiModel
@Data
public class OriginationForm implements Serializable {

    public interface Insert {

    }

    public interface Update {

    }

    @NotNull(groups = OriginationForm.Update.class)
    @ApiModelProperty(value = "主键Id")
    private Long id;

    @NotEmpty(groups = {OriginationForm.Insert.class, OriginationForm.Update.class})
    @Length(min = 2, max = 40, groups = {EmployeeForm.Insert.class, OriginationForm.Update.class})
    @ApiModelProperty(value = "名称")
    private String name;

    @NotNull(groups = {OriginationForm.Insert.class, OriginationForm.Update.class})
    @ApiModelProperty(value = "上级部门id")
    private Long parentId;

    @NotNull(groups = {OriginationForm.Insert.class, OriginationForm.Update.class})
    @ApiModelProperty(value = "部门代码")
    private String code;

    @Length(max = 255)
    @ApiModelProperty(value = "描述")
    private String description;


}
