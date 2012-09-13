package com.tjhx.service.account;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tjhx.common.utils.FileUtils;
import com.tjhx.dao.jpa.account.RoleJpaDao;
import com.tjhx.dao.jpa.account.UserJpaDao;
import com.tjhx.dao.jpa.shop.ShopJpaDao;
import com.tjhx.dao.myBatis.account.UserMyBatisDao;
import com.tjhx.entity.account.Role;
import com.tjhx.entity.account.User;
import com.tjhx.entity.shop.Shop;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.ServiceException;
import com.tjhx.service.io.FileManager;

/**
 * 账户相关管理类
 */
@Service
@Transactional(readOnly = true)
public class AccountManager {
	@Resource
	private UserJpaDao userJpaDao;
	@Resource
	private ShopJpaDao shopJpaDao;
	@Resource
	private RoleJpaDao roleJpaDao;
	@Resource
	private UserMyBatisDao userMyBatisDao;
	@Resource
	private FileManager fileManager;
	@Resource
	private SysConfig sysConfig;

	/**
	 * 取得所有用户信息
	 * 
	 * @return 用户信息列表
	 */
	public List<User> getAllUser() {
		return (List<User>) userJpaDao.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "uuid")));
	}

	/**
	 * 取得用户信息(根据参数)
	 * 
	 * @return 用户信息列表
	 */
	public List<User> getUserList(User user) {
		return userMyBatisDao.getUserList(user);
	}

	/**
	 * 取得用户信息
	 * 
	 * @param loginName 登录名称
	 * @return 用户信息
	 */
	public User findByLoginName(String loginName) {
		return userJpaDao.findByLoginName(loginName);
	}

	/**
	 * 删除用户信息
	 * 
	 * @param uuid User编号
	 */
	@Transactional(readOnly = false)
	public void delUserByUuid(Integer uuid) {
		userJpaDao.delete(uuid);
	}

	/**
	 * 根据编号取得用户信息
	 * 
	 * @param uuid 用户编号
	 * @return 用户信息
	 */
	public User getUserByUuid(Integer uuid) {
		return userJpaDao.findOne(uuid);
	}

	/**
	 * 更新用户信息
	 * 
	 * @param user 用户信息
	 * @param imgFile 上传文件信息
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@Transactional(readOnly = false)
	public void updateUser(User user, MultipartFile imgFile) throws IllegalStateException, IOException {
		User _user = userJpaDao.findOne(user.getUuid());
		if (null == _user) {
			// 用户不存在
			throw new ServiceException("ERR_MSG_ACC_004");
		}

		// 登录密码
		if (StringUtils.isNotBlank(user.getPassWord())) {
			_user.setPassWord(user.getPassWord());
		}

		// 用户姓名
		_user.setName(user.getName());
		// 联系电话
		_user.setTelNum(user.getTelNum());
		// 角色
		Role _dbRole = roleJpaDao.findOne(user.getRoleUuid());
		_user.setRole(_dbRole);
		// 门店
		Shop _dbShop = shopJpaDao.findById(user.getShopId());
		_user.setShop(_dbShop);
		// Email地址
		_user.setEmail(user.getEmail());
		// 详细描述
		_user.setDescTxt(user.getDescTxt());
		// 保存用户信息
		userJpaDao.save(_user);

		// 保存用户上传相片
		fileManager.saveUploadFile(imgFile, sysConfig.getUserPhotoPath(), _user.getPhotoName());
	}

	/**
	 * 添加新用户信息
	 * 
	 * @param user 用户信息
	 * @param imgFile 上传文件信息
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@Transactional(readOnly = false)
	public void addNewUser(User user, MultipartFile imgFile) throws IllegalStateException, IOException {

		User _dbUser = findByLoginName(user.getLoginName());
		// 该用户已存在!
		if (null != _dbUser) {
			throw new ServiceException("ERR_MSG_ACC_003");
		}
		// 角色
		Role _dbRole = roleJpaDao.findOne(user.getRoleUuid());
		user.setRole(_dbRole);
		// 门店
		Shop _dbShop = shopJpaDao.findById(user.getShopId());
		user.setShop(_dbShop);

		// 用户上传相片名称
		if (StringUtils.isNotBlank(user.getLoginName())) {
			String _suffix = FileUtils.getSuffix(imgFile.getOriginalFilename());
			String _photoName = user.getLoginName() + _suffix;

			user.setPhotoName(_photoName);
		}
		// 保存用户信息
		userJpaDao.save(user);

		// 保存用户上传相片
		fileManager.saveUploadFile(imgFile, sysConfig.getUserPhotoPath(), user.getPhotoName());
	}

}
