package com.revature.advice;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
   private final Logger log = LoggerFactory.getLogger(LogAspect.class);

   // execution(access_specifier package_name class_name
   // method_name(argument_list))
   @Pointcut("execution(* com.revature.services.*.*(..))")
   private void servicePointcut() {
      // empty because @Pointcut annotation defines it
   }

   @Around("servicePointcut()")
   public Object aroundLog(ProceedingJoinPoint jp) throws Throwable {
      String methodName = jp.getSignature().getName();
      String className = jp.getTarget().getClass().getSimpleName();

      CodeSignature cs = (CodeSignature) jp.getSignature();

      String[] paramNames = cs.getParameterNames();
      Class<?>[] paramTypes = cs.getParameterTypes();

      List<String> paramStr = new ArrayList<String>();
      List<String> argsStr = new ArrayList<String>();
      Object[] args = jp.getArgs();

      for (int i = 0; i < paramTypes.length; i++) {
         paramStr.add(paramTypes[i].getSimpleName().concat(" " + paramNames[i]));
      }

      for (int i = 0; i < paramTypes.length; i++) {
         argsStr.add("" + args[i]);
      }

      StringJoiner parameterJoiner = new StringJoiner(",");
      paramStr.forEach(item -> parameterJoiner.add(item));

      StringJoiner argumentJoiner = new StringJoiner(",");
      argsStr.forEach(item -> argumentJoiner.add(item));

      log.info("Invoking {}.{}({}) with arguments: {}", className, methodName, parameterJoiner, argumentJoiner);
      Object obj = jp.proceed();
      log.info("{}.{}({}) returned: {}", className, methodName, argumentJoiner, obj.getClass().getTypeName());
      return obj;
   }

}
