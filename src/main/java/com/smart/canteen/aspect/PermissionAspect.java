package com.smart.canteen.aspect;

import com.lc.core.controller.BaseController;
import com.lc.core.dto.Account;
import com.lc.core.enums.BaseErrorEnums;
import com.lc.core.error.BaseException;
import com.lc.core.utils.SpringUtil;
import com.smart.canteen.annotations.Permission;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author lc
 * @date 2020/3/11下午 7:18
 */
@Component
@Aspect
public class PermissionAspect {

    @Pointcut("@annotation(com.smart.canteen.annotations.Permission)")
    public void permissionCut() {

    }

    @Order(-1)
    @Before(value = "permissionCut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?> clazz = method.getDeclaringClass();
        Permission permission = method.getAnnotation(Permission.class);
        if (permission == null) {
            permission = clazz.getAnnotation(Permission.class);
        }
        if (permission == null) {
            return;
        }
        String code = permission.code();
        if (StringUtils.isEmpty(code)) {
            return;
        }
        Object o = SpringUtil.getBean(clazz);
        if (!(o instanceof BaseController)) {
            return;
        }
        BaseController controller = (BaseController) o;
        Account currentUser = controller.getCurrentUser();
        if (currentUser == null) {
            throw new BaseException(BaseErrorEnums.ERROR_LOGIN);
        }
        if (currentUser.getId() == 1) {
            return;
        }
        List<String> powers = currentUser.getPowers();
        if (powers == null || powers.isEmpty() || !powers.contains(code)) {
            throw new BaseException(BaseErrorEnums.ERROR_AUTH);
        }
    }
}
