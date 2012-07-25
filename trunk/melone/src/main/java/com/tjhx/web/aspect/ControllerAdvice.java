package com.tjhx.web.aspect;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import com.tjhx.globals.Constants;
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

		// 连接点所在的目标对象
		if (joinPoint.getTarget() instanceof BaseController && !StringUtils.isBlank(ex.getMessage())
				&& ex.getMessage().startsWith(Constants.ERR_MSG_PREFIX)) {

			Model _model = getPageModel(joinPoint);
			if (null != _model) {
				BaseController _controller = (BaseController) joinPoint.getTarget();
				// 初始化消息列表
				_controller.initMsgList();

				// 添加错误消息
				_controller.addError(_model, ex.getMessage());

				// 将消息保存至表单中
				_controller.insertMsgListToPageMode();
			}
		}

	}

	/**
	 * 取得调用对象的Model参数
	 * 
	 * @param joinPoint 编制点
	 * @return
	 */
	private Model getPageModel(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		for (int i = 0; i < args.length; i++) {
			if (args[i] instanceof Model) {
				return (Model) args[i];
			}
		}
		return null;
	}

	@After("com.tjhx.web.aspect.ControllerAdvice.inControllerLayer()")
	public void aaaaaa(JoinPoint joinPoint) {
	//joinPoint.
		System.out.println("##############################");
	}
}
