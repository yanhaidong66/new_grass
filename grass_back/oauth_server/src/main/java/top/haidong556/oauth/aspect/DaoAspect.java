package top.haidong556.oauth.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import top.haidong556.chat_server.common.exception.oauth.OauthUserException;

@Aspect
@Component
@Slf4j
public class DaoAspect {

    public void after(){

    }
    @AfterThrowing(pointcut = "execution(* top.haidong556.oauth.repository.*.* (..))",throwing="e")
    public void handleException(JoinPoint joinPoint, OauthUserException e){
        String methodName = joinPoint.getSignature().getName();
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        Object[] args = joinPoint.getArgs();
        StringBuilder sb=new StringBuilder();
        for (Object arg:args){
            sb.append(arg.toString()+",");
        }
        sb.deleteCharAt(sb.length()-1);
        log.warn("{}.{}({}):{}",declaringTypeName,methodName,sb,e.getMessage());
    }
}
