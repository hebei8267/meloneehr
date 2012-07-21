package com.tjhx.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.tjhx.web.BaseController;

/**
 * Controller层切面、追加消息信息列表至页面模型
 */
@Aspect
public class ControllerAdvice {

	@Pointcut("execution(public * com.tjhx.web..*.*(..))")
	public void inControllerLayer() {
	}

	@Around("com.tjhx.web.aspect.ControllerAdvice.inControllerLayer()")
	public Object setControllerAspect(ProceedingJoinPoint call) throws Throwable {
		// 连接点所在的目标对象
		if (call.getTarget() instanceof BaseController) {
			BaseController _controller = (BaseController) call.getTarget();
			// 初始化消息列表
			_controller.initMsgList();
			// controller处理
			Object reval = call.proceed();
			// 将消息保存至表单中
			_controller.insertMsgListToPageMode();
			return reval;
		}
		return call.proceed();
	}
}
