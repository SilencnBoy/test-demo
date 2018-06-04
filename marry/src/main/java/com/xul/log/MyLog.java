package com.xul.log;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyLog {

	@SuppressWarnings("unused")
	public void doLog(JoinPoint joinPoint) {

		String method = joinPoint.getSignature().getName();
//		System.out.println("方法名：" + method);
		Object target = joinPoint.getTarget();
		System.out.println(target);

	}

	@Around("execution(* *com.xul.service..*.*(..))")
	public Object aroundMethod(ProceedingJoinPoint pjd) throws Throwable {
		Object result = null;
		String methodName = "class:" + pjd.getTarget().getClass().getName() + " method:" + pjd.getSignature().getName(); // 获取方法名称
        //System.out.println(methodName);
		//获取请求的URL//执行目标方法
		//HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); 
		//System.out.println(request);
		try {
			// 前置通知
			result = pjd.proceed();// 返回通知
		} catch (Throwable e) {
			// 异常通知
			System.out.println("The method " + methodName + " occurs expection : " + e);
			throw e; //让spring处理异常
		} finally {
			// 记录日志
		}
		return result; // 必须返回结果，否则controller无法获取service返回的结果（正常情况下）
	}

}
