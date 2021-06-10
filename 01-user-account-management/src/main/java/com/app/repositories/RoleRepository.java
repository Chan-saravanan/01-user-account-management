package com.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.app.entities.RoleEntity;

public interface RoleRepository extends CrudRepository<RoleEntity, String>{

}
