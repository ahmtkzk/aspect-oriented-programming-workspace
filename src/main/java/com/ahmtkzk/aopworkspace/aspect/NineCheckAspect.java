package com.ahmtkzk.aopworkspace.aspect;

import com.ahmtkzk.aopworkspace.aspect.annotation.NineCheck;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
public class NineCheckAspect {

    @Before("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void checkNine(JoinPoint joinPoint) throws NoSuchMethodException {
        Method method = getMethodFromJoinPoint(joinPoint);
        Object[] args = joinPoint.getArgs();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();

        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (Annotation annotation : parameterAnnotations[i]) {
                if (annotation instanceof NineCheck) {
                    Object paramValue = args[i];
                    if (Integer.parseInt(paramValue.toString()) == 9) {
                        throw new IllegalArgumentException("The number 9 cannot be entered.");
                    }
                }
            }
        }
    }

    private Method getMethodFromJoinPoint(JoinPoint joinPoint) throws NoSuchMethodException {
        String methodName = joinPoint.getSignature().getName();
        Class<?>[] parameterTypes = ((org.aspectj.lang.reflect.MethodSignature) joinPoint.getSignature()).getParameterTypes();
        return joinPoint.getTarget().getClass().getMethod(methodName, parameterTypes);

    }

}
