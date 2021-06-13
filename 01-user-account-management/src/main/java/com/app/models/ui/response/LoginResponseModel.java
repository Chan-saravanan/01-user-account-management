package com.app.models.ui.response;

import java.util.List;

import com.app.models.ui.Role;

public class LoginResponseModel {
	private String userId;

	private List<Role> roles;

	//Authorization will will happen only after login!
	//This is for specifying whether the user is authorized to access the resource!
	private String authorizationToken;
	//This is for specifying the respective user have the appropriate access rights to access rights to access the resource!
	private String accessToken;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAuthorizationToken() {
		return authorizationToken;
	}
	public void setAuthorizationToken(String authorizationToken) {
		this.authorizationToken = authorizationToken;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "LoginResponseModel [userId=" + userId + ", roles=" + roles + ", authorizationToken=" + authorizationToken
				+ ", accessToken=" + accessToken + "]";
	}
}
