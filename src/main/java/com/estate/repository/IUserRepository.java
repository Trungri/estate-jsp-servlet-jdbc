package com.estate.repository;

import com.estate.entity.UserEntity;

public interface IUserRepository {
	Long insert(UserEntity userEntity);
}
