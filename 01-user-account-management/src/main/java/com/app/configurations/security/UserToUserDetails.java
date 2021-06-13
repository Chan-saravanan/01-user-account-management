package com.app.configurations.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.app.entities.UserProfileEntity;

@Component
public class UserToUserDetails implements Converter<UserProfileEntity, UserDetails>{

	@Override
	public UserDetails convert(UserProfileEntity source) {
		UserDetailsImpl userDetail = null;
		if(Objects.nonNull(source))
		{
			Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
			source.getRoles().forEach((role)->{
				authorities.add(new SimpleGrantedAuthority(role.getRole()));
			});
			userDetail = new UserDetailsImpl(source.getUsername(), source.getEncryptedPassword(), true, authorities);
		}
		return userDetail;
	}

}
