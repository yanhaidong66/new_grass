package top.haidong556.chat_server.common.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAspect {
    @Before("execution(* top.haidong556.response.*(..))")
    public void before(){

    }
    @Pointcut("execution(* top.haidong556.*.* (..) )")
    public void pointcut(){};
    @After("pointcut()")
    public void after(){};
}
