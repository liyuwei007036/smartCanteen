package com.smart.canteen.aspect;

import live.lumia.controller.BaseController;
import live.lumia.utils.SpringUtil;
import com.smart.canteen.annotations.Log;
import com.smart.canteen.service.IOperationLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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


    @Order(-1)
    @Around(value = "logCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log log = method.getAnnotation(Log.class);
        Class<?> clazz = method.getDeclaringClass();
        Object o = SpringUtil.getBean(clazz);
        if ((o instanceof BaseController)) {
            BaseController controller = (BaseController) o;
            SpringUtil.getBean(IOperationLogService.class).addLog(joinPoint.getArgs(), log, controller.getCurrentUser());
        }
        return proceed;
    }
}
