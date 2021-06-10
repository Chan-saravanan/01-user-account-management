package com.app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import com.app.entities.attributes.ids.generators.CustomStringIdGenerator;

@Entity(name="ROLE")
@Table(name="ROLE")
public class RoleEntity extends AbstractEntityObject<String>{
	@Id
	@GenericGenerator(
			name = "roleSequence", 
			strategy = "com.app.entities.attributes.ids.generators.CustomStringIdGenerator", 
			parameters = {
		            @Parameter(name = CustomStringIdGenerator.SEQUENCE_FORMAT, value ="02d"),
		            @Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value ="ROLE_SEQ"),
		            @Parameter(name = CustomStringIdGenerator.SEQUENCE_PREFIX, value = "ROLE"),
		            
	})
	@GeneratedValue(generator="roleSequence", strategy=GenerationType.SEQUENCE)
	@Column(name="ID")
	protected String id;
	
	@Column(name="ROLE")
	private String role;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade= {CascadeType.DETACH, CascadeType.REMOVE, CascadeType.REFRESH})
	@JoinTable(
			name="USER_ROLE_MAPPING",
			joinColumns=@JoinColumn(name="ROLE"),
			inverseJoinColumns=@JoinColumn(name="USER_ID")
	)
	private List<UserProfileEntity> users = new ArrayList<>();
	
	public void addAccount(UserProfileEntity account) {
		if(!users.contains(account))
		{
			users.add(account);
			account.getRoles().add(this);
		}
	}
	
	public void removeAccount(UserProfileEntity account) {
		users.remove(account);
		account.getRoles().remove(this);
	}
	
	//getters & setters	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<UserProfileEntity> getUsers() {
		return users;
	}
	public void setUsers(List<UserProfileEntity> users) {
		this.users = users;
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
		return "RoleEntity [id=" + id + ", role=" + role + ", users=" + users + "]";
	}
}
