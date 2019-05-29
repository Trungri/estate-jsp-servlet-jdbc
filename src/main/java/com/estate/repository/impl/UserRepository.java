package com.estate.repository.impl;

import com.estate.entity.UserEntity;
import com.estate.repository.IUserRepository;

public class UserRepository extends AbstractJDBC<UserEntity> implements IUserRepository{

	@Override
	public Long insert(UserEntity userEntity) {
		
		return null;
	}

}
