package com.app.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import com.app.entities.attributes.ids.generators.CustomStringIdGenerator;

@Entity(name="USER_PROFILE")
@Table(name="USER_PROFILE")
public class UserProfileEntity extends AbstractEntityObject<String>{
	@Id
	@Column(name="USER_ID",unique=true)
	//@SequenceGenerator(sequenceName="USER_PROFILE_SEQ", name="userProfileSequence")
	@GenericGenerator(
			name = "userProfileSequence", 
			strategy = "com.app.entities.attributes.ids.generators.CustomStringIdGenerator", 
			parameters = {
		            @Parameter(name = CustomStringIdGenerator.SEQUENCE_FORMAT, value ="05d"),
		            @Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value ="USER_PROFILE_SEQ"),
		            @Parameter(name = CustomStringIdGenerator.SEPARATOR_REQUIRED, value = "true"),
		            @Parameter(name = CustomStringIdGenerator.SEQUENCE_PREFIX_SEPARATOR_REQUIRED, value = "true"),
		            @Parameter(name = CustomStringIdGenerator.SEQUENCE_PREFIX_SEPARATOR, value = "<"),
		            @Parameter(name = CustomStringIdGenerator.SEQUENCE_SUFFIX_SEPARATOR_REQUIRED, value = "true"),
		            @Parameter(name = CustomStringIdGenerator.SEQUENCE_SUFFIX_SEPARATOR, value = ">"),
		            @Parameter(name = CustomStringIdGenerator.SEQUENCE_PREFIX, value = "USER"),
		            @Parameter(name = CustomStringIdGenerator.SEQUENCE_SUFFIX, value = "TEST")
		            
	})
	@GeneratedValue(generator="userProfileSequence", strategy=GenerationType.SEQUENCE)
	protected String id;
	@Column(name="USERNAME",unique=true)
	private String username;
	@Column(name="FIRST_NAME")
	private String firstName;
	@Column(name="LAST_NAME")
	private String lastName;
	@Column(name="ENCRYPTED_PASSWORD")
	private String encryptedPassword;
	
	@OneToOne(cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH},fetch = FetchType.LAZY)
	@JoinColumn(name="ADDR_ID")
	private AddressEntity address;
	
	@OneToOne(cascade= {CascadeType.ALL },fetch=FetchType.LAZY)
	@JoinColumn(name="ACCESS_ACCOUNT_ID")
	private UserAccessDetailEntity accessDetail;
	
	
	@ManyToMany(cascade= {CascadeType.REMOVE,CascadeType.DETACH,CascadeType.REFRESH },fetch=FetchType.LAZY)
	@JoinTable(
			name="USER_ROLE_MAPPING",
			joinColumns=@JoinColumn(name="USER_ID"),
			inverseJoinColumns=@JoinColumn(name="ROLE")
	)
	private List<RoleEntity> roles = new ArrayList<>();

	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	public void addRole(RoleEntity role)
	{
		if(Objects.isNull(roles))
			roles = new ArrayList<>();
		
		if(!roles.contains(role))
		{
			roles.add(role);
		}
	}
	
	public void addRoles(List<RoleEntity> roles)
	{
		roles.stream().forEach(role->{
			this.addRole(role);
		});
	}
	
	public void removeRole(RoleEntity role) {
		this.roles.remove(role);
		role.getUsers().remove(this);
	}

	//getters & setters

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

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public UserAccessDetailEntity getAccessDetail() {
		return accessDetail;
	}

	public void setAccessDetail(UserAccessDetailEntity accessDetail) {
		this.accessDetail = accessDetail;
	}

	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserProfileEntity [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName="
				+ lastName + ", encryptedPassword=" + encryptedPassword + ", address=" + address //+ ", accessDetail->="
				//+ accessDetail + ", roles=" + roles 
				+ "]";
	}
}
