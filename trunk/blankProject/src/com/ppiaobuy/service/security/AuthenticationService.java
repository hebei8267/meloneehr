package com.ppiaobuy.service.security;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Sets;
import com.ppiaobuy.entity.security.Role;
import com.ppiaobuy.entity.security.User;
import com.ppiaobuy.web.domain.security.OperatorDetails;

/**
 * spring security用户验证
 */
@Service
@Transactional(readOnly = true)
public class AuthenticationService implements UserDetailsService {
	private AccountService accountService;

	/**
	 * 获取用户Detail信息的回调函数.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		User user = accountService.findUserByLoginName(username);
		if (user == null) {
			throw new UsernameNotFoundException("用户" + username + " 不存在");
		}

		Set<GrantedAuthority> grantedAuths = obtainGrantedAuthorities(user);

		// User类中无以下属性,暂时全部设为true.
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		OperatorDetails userDetails = new OperatorDetails(user.getLoginName(), user.getPassword(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);
		// 加入登录时间信息和用户角色
		userDetails.setLoginTime(new Date());
		userDetails.setRoleList(user.getRoleList());
		return userDetails;
	}

	/**
	 * 获得用户所有角色的权限.
	 */
	private Set<GrantedAuthority> obtainGrantedAuthorities(User user) {
		Set<GrantedAuthority> authSet = Sets.newHashSet();
		for (Role role : user.getRoleList()) {
			authSet.add(new GrantedAuthorityImpl("ROLE_" + role.getName()));
		}
		return authSet;
	}

	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

}
