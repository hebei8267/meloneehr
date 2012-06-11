package org.springside.examples.showcase.common.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutsDefinition {
	// 1）execution(* *(..))
	// 表示匹配所有方法
	// 2）execution(public * com. savage.service.UserService.*(..))
	// 表示匹配com.savage.server.UserService中所有的公有方法
	// 3）execution(* com.savage.server..*.*(..))
	// 表示匹配com.savage.server包及其子包下的所有方法
	@Pointcut("execution(public * org.springside.examples.showcase.common.service.AccountManager.getUser(..))")
	public void inServiceLayer() {
	}
}
