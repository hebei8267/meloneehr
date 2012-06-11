package org.springside.examples.miniservice.webservice.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springside.examples.showcase.common.entity.User;

@XmlRootElement(name = "users")
public class UserDTOList {
	List<User> users;

	@XmlElement(name = "user")
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
