package com.smart.canteen.annotations;

import java.lang.annotation.*;

/**
 * @author lc
 * @date 2020/3/11下午 7:16
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Permission {

    String code();
}
