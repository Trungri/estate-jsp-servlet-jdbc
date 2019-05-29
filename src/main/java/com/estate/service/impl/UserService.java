package com.estate.service.impl;

import com.estate.converter.UserConverter;
import com.estate.dto.UserDTO;
import com.estate.entity.UserEntity;
import com.estate.service.IUserService;

public class UserService implements IUserService{

	@Override
	public UserDTO save(UserDTO newUser) {
		UserConverter converter = new UserConverter();
		UserEntity userEntity = converter.convertToEntity(newUser);
		return null;
	}

}
