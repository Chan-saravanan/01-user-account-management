package com.app.models;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.app.models.UserRole.AccessLevel;
import com.app.models.converters.UserRoleArrayToList;
import com.app.models.converters.UserRoleListToArray;
import com.app.services.validators.PasswordConfirmPasswordMatch;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@PasswordConfirmPasswordMatch
public class UserProfile {
	private String userId;
	@NotNull(message="username must be specified!")
	@Size(min=5, max=25, message="Last name should be atleast 5 characters of length!")
	private String username;
	@NotNull(message="first name must be specified!")
	@Size(min=5, max=35, message="First name should be atleast 5 characters of length!")
	private String firstName;
	@NotNull(message="last name must be specified!")
	@Size(min=5, max=35, message="Last name should be atleast 5 characters of length!")
	private String lastName;
	@NotNull(message="password must be specified!")
	@Size(min=10, message="Password should be at least 10 characters of length!")
	private String password;
	@NotNull(message="confirm password must be specified!")
	private String confirmPassword;
	
	private AccessLevel accessLevel;	
	private String authorizationToken;
	private String accessToken;
	private String refreshToken;
	private Integer authCount;
	private Boolean loggedIn;
	private Date authTokenCreatedTime;
	private Date authLastAccessTime;
	@JsonSerialize(converter = UserRoleListToArray.class)
	@JsonDeserialize(converter = UserRoleArrayToList.class)
	private List<Role> roles;	
	private Address address;
	
	public UserProfile() {
		// TODO Auto-generated constructor stub
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Integer getAuthCount() {
		return authCount;
	}

	public void setAuthCount(Integer authCount) {
		this.authCount = authCount;
	}

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public Date getAuthTokenCreatedTime() {
		return authTokenCreatedTime;
	}

	public void setAuthTokenCreatedTime(Date authTokenCreatedTime) {
		this.authTokenCreatedTime = authTokenCreatedTime;
	}

	public Date getAuthLastAccessTime() {
		return authLastAccessTime;
	}

	public void setAuthLastAccessTime(Date authLastAccessTime) {
		this.authLastAccessTime = authLastAccessTime;
	}
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}
	@JsonIgnore
	public String getConfirmPassword() {
		return confirmPassword;
	}
	@JsonProperty
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	@JsonIgnore
	public AccessLevel getAccessLevel() {
		return accessLevel;
	}
	@JsonIgnore
	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}