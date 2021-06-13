package com.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.app.entities.UserProfileEntity;

public interface UserAccountRepository extends CrudRepository<UserProfileEntity, String>{
	UserProfileEntity findByUsername(String username);
}
