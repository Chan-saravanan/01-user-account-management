package com.app.models.converters;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.app.models.Role;
import com.fasterxml.jackson.databind.util.StdConverter;

public class UserRoleArrayToList extends StdConverter<String[], List<Role>>{
	@Override
	public List<Role> convert(String[] roleArray) {
		List<Role> roles = Arrays.stream(roleArray).map(value-> new Role(value)).collect(Collectors.toList());
		return roles;
	}
}
