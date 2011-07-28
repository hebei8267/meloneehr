package com.ppiaobuy.dao.security;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.ppiaobuy.entity.security.Role;

/**
 * 角色对象的泛型DAO.
 */
@Repository
public class RoleDao extends HibernateDao<Role, String> {

}
