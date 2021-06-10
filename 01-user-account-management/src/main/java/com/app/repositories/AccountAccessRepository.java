package com.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.app.entities.UserAccessDetailEntity;

public interface AccountAccessRepository extends CrudRepository<UserAccessDetailEntity, String>{
}
