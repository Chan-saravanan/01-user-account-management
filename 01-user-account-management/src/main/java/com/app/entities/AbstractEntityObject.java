package com.app.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.app.entities.attributes.converters.BooleanToStringConverter;

@MappedSuperclass
public abstract class AbstractEntityObject<T extends Serializable> implements BasicEntityAcccessContract<T>{
	
	@Column(name="CREATED_TIME")
	private Date createdtime;
	@Column(name="MODIFIED_TIME")
	private Date modifiedtime;
	
	@Column(name="ACTIVE_STATUS", columnDefinition="NUMBER(1)", length=1)
	@Convert(converter=BooleanToStringConverter.class)
	private Boolean active;
	
	@PrePersist
	@PreUpdate
	private void udpateTimestamp() {
		if(Objects.isNull(createdtime))
			createdtime = new Date();
		
		modifiedtime = new Date();
	}
	public Date getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}
	public Date getModifiedtime() {
		return modifiedtime;
	}
	public void setModifiedtime(Date modifiedtime) {
		this.modifiedtime = modifiedtime;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
}
