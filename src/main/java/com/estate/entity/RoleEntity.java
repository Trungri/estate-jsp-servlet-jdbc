package com.estate.entity;

import com.estate.annotation.Column;
import com.estate.annotation.Entity;

@Entity
public class RoleEntity {
	@Column(name = "name")
	private String name;
	
	@Column(name = "code")
	private String code;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
