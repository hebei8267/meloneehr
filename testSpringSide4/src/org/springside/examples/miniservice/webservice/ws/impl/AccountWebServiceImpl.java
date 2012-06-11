package org.springside.examples.miniservice.webservice.ws.impl;

import java.util.List;

import javax.jws.WebService;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springside.examples.miniservice.service.MiniAccountManager;
import org.springside.examples.miniservice.webservice.WsConstants;
import org.springside.examples.miniservice.webservice.dto.UserDTO;
import org.springside.examples.miniservice.webservice.ws.AccountWebService;
import org.springside.examples.miniservice.webservice.ws.response.UserListResponse;
import org.springside.examples.miniservice.webservice.ws.response.base.IdResponse;
import org.springside.examples.miniservice.webservice.ws.response.base.WSResponse;
import org.springside.examples.showcase.common.entity.User;
import org.springside.modules.beanvalidator.BeanValidators;
import org.springside.modules.mapper.BeanMapper;

/**
 * WebService服务端实现类.
 * 
 * 客户端实现见功能测试用例.
 * 
 * @author calvin
 */
// serviceName与portName属性指明WSDL中的名称, endpointInterface属性指向Interface定义类.
@WebService(serviceName = "AccountService", portName = "AccountServicePort", endpointInterface = "org.springside.examples.miniservice.webservice.ws.AccountWebService", targetNamespace = WsConstants.NS)
public class AccountWebServiceImpl implements AccountWebService {

	private static Logger logger = LoggerFactory.getLogger(AccountWebServiceImpl.class);

	private MiniAccountManager miniAccountManager;

	/**
	 * @see AccountWebService#searchUser()
	 */
	@Override
	public UserListResponse searchUser(String loginName, String name) {
		try {
			List<User> entityList = miniAccountManager.searchUser(loginName, name);

			List<UserDTO> dtoList = BeanMapper.mapList(entityList, UserDTO.class);

			return new UserListResponse(dtoList);
		} catch (RuntimeException e) {
			logger.error(e.getMessage(), e);
			return new UserListResponse().setDefaultError();
		}
	}

	/**
	 * @see AccountWebService#createUser()
	 */
	@Override
	public IdResponse createUser(UserDTO user) {
		try {
			User userEntity = BeanMapper.map(user, User.class);

			Long userId = miniAccountManager.saveUser(userEntity);

			return new IdResponse(userId);
		} catch (ConstraintViolationException e) {
			String message = StringUtils.join(BeanValidators.extractPropertyAndMessage(e), "\n");
			return new IdResponse().setError(WSResponse.PARAMETER_ERROR, message);
		} catch (DataIntegrityViolationException e) {
			String message = "新建用户参数存在唯一性冲突(用户:" + user + ")";
			logger.error(message, e);
			return new IdResponse().setError(WSResponse.PARAMETER_ERROR, message);
		} catch (RuntimeException e) {
			logger.error(e.getMessage(), e);
			return new IdResponse().setDefaultError();
		}
	}

	@Autowired
	public void setMiniAccountManager(MiniAccountManager miniAccountManager) {
		this.miniAccountManager = miniAccountManager;
	}
}
