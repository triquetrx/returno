package com.cognizant.componentprocessing.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public @Data @NoArgsConstructor class Users {

	@Id
	private Integer userId;
	private String username;
	private String password;
	private String roles;

	public Users(Integer userId, String username, String password, String roles) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

}
