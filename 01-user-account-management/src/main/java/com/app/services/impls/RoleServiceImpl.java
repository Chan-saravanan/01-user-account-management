package com.app.services.impls;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.RoleEntity;
import com.app.models.Role;
import com.app.models.UserRole;
import com.app.repositories.RoleRepository;
import com.app.services.contracts.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleRepository roleRepo;

	@Override
	public List<?> listAll() {
		List<RoleEntity> roles = new ArrayList<>();
		roleRepo.findAll().forEach(roles::add);
		return roles;
	}

	@Override
	public RoleEntity getById(String id) {
		RoleEntity entity = roleRepo.findById(id).get();
		return entity;
	}

	@Override
	public RoleEntity saveOrUpdate(RoleEntity domainObject) {
		RoleEntity updatedObject = roleRepo.save(domainObject);
		return updatedObject;
	}

	@Override
	public void delete(RoleEntity domainObject) {
		roleRepo.delete(domainObject);
	}
	
	@Override
	public List<RoleEntity> getDefaultRole() {
		RoleEntity role = new RoleEntity();
		role.setRole(UserRole.NORMAL.getSecurityRoleName());
		
		List<RoleEntity> roles = new ArrayList<>();
		
		roles.add(role);
		
		return roles;
	}
	
	@Override
	public List<RoleEntity> getRoles(List<Role> roles) {
		
		List<RoleEntity> entityRoles = 
		roles
		.stream()
		.map((role)->UserRole.getRole(role.getRole()))
		.distinct()
		.map(userRole->findByRole(userRole.getSecurityRoleName()))
		.collect(Collectors.toList());
		
		return entityRoles;
	}
	
	@Override
	public RoleEntity findByRole(String role) {
		RoleEntity entity = roleRepo.findByRole(role);
		RoleEntity roleEntity = entity;
		return roleEntity;
	}
	
	private List<String> getSecurityRoleNames(List<Role> roles) {
		List<String> entityRoles = 
		roles
		.stream()
		.map((role)->UserRole.getRole(role.getRole()))		
		.map(entity->entity.getSecurityRoleName())
		.distinct()
		.collect(Collectors.toList());
		
		return entityRoles;
	}

	//This will create the roles which, does not already exists in the system!
	@Override
	public List<RoleEntity> createRoles(List<Role> roles) {
		List<String> roleNames = getSecurityRoleNames(roles);
		
		List<RoleEntity> roleEntityList = new ArrayList<>();
		
		roleNames.stream().forEach((role)->{
			RoleEntity entity = findByRole(role);

			if(Objects.isNull(entity))
			{
				//creating the new Role!
				RoleEntity roleEntity = new RoleEntity(role);
				roleEntity = roleRepo.save(roleEntity);
				roleEntityList.add(roleEntity);
			}
			else
			{
				//getting the existing role!
				roleEntityList.add(entity);
			}
		});
		return roleEntityList;
	}
}
