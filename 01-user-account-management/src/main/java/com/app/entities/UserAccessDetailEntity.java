package com.app.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.app.entities.attributes.converters.BooleanToStringConverter;

@Entity
@Table(name="USER_ACCESS_DETAIL")
public class UserAccessDetailEntity extends AbstractEntityObject<String>{
	
	@Id
	@Column(name="ACCESS_ACCOUNT_ID")
	protected String id;
	@Column(name="AUTH_TOKEN")
	private String authorizationToken;
	@Column(name="ACCESS_TOKEN")
	private String accessToken;
	@Column(name="REFRESH_TOKEN")
	private String refreshToken;
	@Column(name="AUTH_TOKEN_CREATED_TIME")
	private Date authTokenCreatedTime;
	@Column(name="AUTH_TOKEN_LAST_ACCESSED_TIME")
	private Date authLastAccessTime;
	@Column(name="ACCESS_COUNT")
	private Integer authCount;
	@Convert(converter=BooleanToStringConverter.class)
	@Column(name="LOGGED_ID", columnDefinition="NUMBER(1)", length=1)
	private Boolean loggedIn;
	
	@OneToOne(cascade= {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy="accessDetail")
	private UserProfileEntity userProfile;

	public UserAccessDetailEntity() {
	}

	@PrePersist
	private void createAction()
	{
		authTokenCreatedTime = new Date();
	}
	//This is for updating the last accessed time of the entity row, on its access!
	@PostLoad
	private void updateLastAccessedTime() {
		System.out.println("updateLastAccessedTime called!");
		authLastAccessTime = new Date();
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
	public Integer getAuthCount() {
		return authCount;
	}
	public void setAuthCount(Integer authCount) {
		this.authCount = authCount;
	}
	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}

	public UserProfileEntity getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfileEntity userProfile) {
		this.userProfile = userProfile;
	}

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}

	public String getAuthorizationToken() {
		return authorizationToken;
	}

	public void setAuthorizationToken(String authorizationToken) {
		this.authorizationToken = authorizationToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public String toString() {
		return "UserAccessDetailEntity [id=" + id + ", authorizationToken=" + authorizationToken + ", accessToken="
				+ accessToken + ", refreshToken=" + refreshToken + ", authTokenCreatedTime=" + authTokenCreatedTime
				+ ", authLastAccessTime=" + authLastAccessTime + ", authCount=" + authCount + ", loggedIn=" + loggedIn
				+ "]";
	}
}
