package com.smart.canteen.annotations;

import java.lang.annotation.*;

/**
 * @author lc
 * @date 2020/3/11下午 7:16
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Log {

    String module();

    String action();

    Class<?> clazz() default void.class;

    String dataDesc() default "";
}
