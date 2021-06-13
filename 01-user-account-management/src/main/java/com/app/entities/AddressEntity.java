package com.app.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import com.app.entities.attributes.ids.generators.CustomStringIdGenerator;

@Entity(name="ADDRESS_1")
@Table(name="ADDRESS_1")
public class AddressEntity extends AbstractEntityObject<String>{
	@Id
	@GenericGenerator(
			name = "addressSequence", 
			strategy = "com.app.entities.attributes.ids.generators.CustomStringIdGenerator", 
			parameters = {
					@Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value ="ADDRESS_SEQ"),
		            @Parameter(name = CustomStringIdGenerator.SEQUENCE_FORMAT, value ="05d"),
		            @Parameter(name = CustomStringIdGenerator.SEPARATOR_REQUIRED, value = "true"),
		            @Parameter(name = CustomStringIdGenerator.SEQUENCE_PREFIX_SEPARATOR_REQUIRED, value = "true"),
		            @Parameter(name = CustomStringIdGenerator.SEQUENCE_PREFIX_SEPARATOR, value = "<"),
		            @Parameter(name = CustomStringIdGenerator.SEQUENCE_SUFFIX_SEPARATOR_REQUIRED, value = "true"),
		            @Parameter(name = CustomStringIdGenerator.SEQUENCE_SUFFIX_SEPARATOR, value = ">"),
		            @Parameter(name = CustomStringIdGenerator.SEQUENCE_PREFIX, value = "ADDR"),
		            @Parameter(name = CustomStringIdGenerator.SEQUENCE_SUFFIX, value = "TEST")
		            
	})
	@GeneratedValue(generator="addressSequence", strategy=GenerationType.SEQUENCE)
	@Column(name="ADDR_ID")
	protected String id;
	@Column(name="ADDR_LINE1")
	private String addressLine1;
	@Column(name="ADDR_LINE2")
	private String addressLine2;
	@Column(name="CITY")
	private String city;
	@Column(name="STATE")
	private String state;
	@Column(name="CONTACT_PHONE")
	private String contactPhone;
	
	@OneToOne(cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH},fetch = FetchType.EAGER, mappedBy="address")
	private UserProfileEntity userProfile;

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public UserProfileEntity getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfileEntity userProfile) {
		this.userProfile = userProfile;
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
		return "AddressEntity [id=" + id + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2
				+ ", city=" + city + ", state=" + state + ", contactPhone=" + contactPhone + ", userProfile="
				+ userProfile + "]";
	}	
}
