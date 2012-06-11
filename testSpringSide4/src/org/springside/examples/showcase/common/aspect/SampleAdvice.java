package org.springside.examples.showcase.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springside.examples.showcase.common.entity.User;
//@Component
@Aspect
public class SampleAdvice {
	@Before("org.springside.examples.showcase.common.aspect.PointcutsDefinition.inServiceLayer()")
	public void logInfo() {
		System.out.println("=====================================");
		System.out.println("Aop: do before in service layer");
		System.out.println("=====================================");
	}

	@Around("org.springside.examples.showcase.common.aspect.PointcutsDefinition.inServiceLayer()&&args(id)")
	public User getUserAspect(ProceedingJoinPoint call, Long id) throws Throwable {
		User user = null;
		System.out.println("11111111111");
		System.out.println(id);
		user = (User) call.proceed();
		System.out.println("22222222222");
		return user;
	}
}
