package com.app.services.contracts;

import java.util.List;

import com.app.entities.RoleEntity;
import com.app.models.Role;

public interface RoleService extends CRUDService<RoleEntity, String>{
	RoleEntity findByRole(String role);
	List<RoleEntity> getDefaultRole();
	List<RoleEntity> getRoles(List<Role> roles);
	List<RoleEntity> createRoles(List<Role> roles);
}
