package com.app.models.converters;

import java.util.List;

import com.app.models.Role;
import com.fasterxml.jackson.databind.util.StdConverter;

public class UserRoleListToArray extends StdConverter<List<Role>, String[]>{
	@Override
	public String[] convert(List<Role> list) {
		String[] values = new String[list.size()];
		return list.toArray(values);
	}
}