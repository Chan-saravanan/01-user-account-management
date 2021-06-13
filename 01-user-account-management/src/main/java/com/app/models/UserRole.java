package com.app.models;

import java.util.Arrays;
import java.util.List;

public enum UserRole {
	//by default this role will be provided!
	NORMAL("normal","ROLE_NORMAL", new AccessLevel[] {AccessLevel.ACCESS_LEVEL_ONE}),
	MANAGER("manager","ROLE_MANAGER", new AccessLevel[] {AccessLevel.ACCESS_LEVEL_ONE,AccessLevel.ACCESS_LEVEL_TWO}),
	ADMINISTRATOR("administrator", "ROLE_ADMIN", new AccessLevel[] {AccessLevel.ACCESS_LEVEL_ONE, AccessLevel.ACCESS_LEVEL_TWO, AccessLevel.ACCESS_LEVEL_THREE});
	
	private final String name;
	private final String securityRoleName;
	private final List<AccessLevel> accessLevelList;
	
	private UserRole(String name, String securityRoleName, AccessLevel[] accessLevels) {
		this.name = name;
		this.securityRoleName = securityRoleName;
		accessLevelList = Arrays.asList(accessLevels);
	}
	
	public enum AccessLevel{
		ACCESS_LEVEL_ONE(1),//for engaging into a course
		ACCESS_LEVEL_TWO(2),//for registering a course, engaging into a course
		ACCESS_LEVEL_THREE(3);
		
		private final Integer accessLevel;
		
		private AccessLevel(Integer accessLevel) {
			this.accessLevel = accessLevel;
		}
		
		public Integer getAccessLevel() {
			return accessLevel;
		}
	}
	
	public List<AccessLevel> getAccessLevelList() {
		return accessLevelList;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSecurityRoleName() {
		return securityRoleName;
	}
	
	public static UserRole getRole(String roleName) {
		return Arrays
		.stream(values())
		.filter(role-> role.getName().equals(roleName))
		.findFirst()
		.orElseGet(()->UserRole.NORMAL);
	}
}
