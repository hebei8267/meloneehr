package com.tjhx.web.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tjhx.service.ServiceException;
import com.tjhx.web.BaseController;

/**
 * Controller层切面、追加消息信息列表至页面模型
 */
@Aspect
public class ControllerAdvice {
	private static Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

	@Pointcut("execution(public * com.tjhx.web..*.*(..))")
	public void inControllerLayer() {
	}

	@Around("com.tjhx.web.aspect.ControllerAdvice.inControllerLayer()")
	public Object insertMsgList(ProceedingJoinPoint callJoinPoint) throws Throwable {
		// 连接点所在的目标对象
		if (callJoinPoint.getTarget() instanceof BaseController) {
			BaseController _controller = (BaseController) callJoinPoint.getTarget();
			// 初始化消息列表
			_controller.initMsgList();
			// controller处理
			Object reval = callJoinPoint.proceed();
			// 将消息保存至表单中
			_controller.insertMsgListToPageMode();
			return reval;
		}
		return callJoinPoint.proceed();
	}

	@AfterThrowing(pointcut = "com.tjhx.web.aspect.ControllerAdvice.inControllerLayer()", throwing = "ex")
	public void throwException(JoinPoint joinPoint, ServiceException ex) {
		logger.info(ex.getMessage(), ex);
	}

}
