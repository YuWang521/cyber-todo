package com.wy.cybertodoadmin.base.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.Arrays;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 系统日志
 * @date 2023/7/4 17:55:27
 */
@Aspect
@Component
public class SystemLogAspect {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(SystemLogAspect.class);

    @Pointcut("@annotation(com.wy.cybertodoadmin.base.aspect.annotation.SystemLog)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(point, time, result);

        return result;
    }

    /**
     *  保存日志
     * @param joinPoint 切点
     * @param time
     * @param obj
     */
    private void saveSysLog(ProceedingJoinPoint joinPoint, long time, Object obj) {
        ServletRequestAttributes attributes =
            (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录请求信息
        logger.info("URL: {}", request.getRequestURL());
        logger.info("HTTP Method: {}", request.getMethod());
        logger.info("Class Method: {}.{}",
            joinPoint.getSignature().getDeclaringTypeName(),
            joinPoint.getSignature().getName());
        logger.info("Request Params: {}", Arrays.toString(joinPoint.getArgs()));
    }



}
