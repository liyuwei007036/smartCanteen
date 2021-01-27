package com.smart.canteen.aspect;

import com.smart.canteen.annotations.Log;
import com.smart.canteen.service.IOperationLogService;
import live.lumia.controller.BaseController;
import live.lumia.utils.SpringUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

/**
 * @author lc
 * @date 2020/3/11下午 7:18
 */
@Transactional(rollbackFor = Exception.class)
@Component
@Aspect
public class LogAspect {

    @Pointcut("@annotation(com.smart.canteen.annotations.Log)")
    public void logCut() {

    }

    @Before(value = "logCut()")
    public void before(JoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log log = method.getAnnotation(Log.class);
        Class<?> clazz = method.getDeclaringClass();
        Object o = SpringUtil.getBean(clazz);
        if ((o instanceof BaseController)) {
            BaseController controller = (BaseController) o;
            SpringUtil.getBean(IOperationLogService.class).addLog(joinPoint.getArgs(), log, controller.getCurrentUser());
        }
    }
}
