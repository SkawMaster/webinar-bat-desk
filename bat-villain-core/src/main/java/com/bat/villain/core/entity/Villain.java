package com.bat.villain.core.entity;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.bat.architecture.common.entity.AbstractModelBean;
import com.bat.villain.core.enumerate.VillainStatusEnum;

public class Villain extends AbstractModelBean {
	
	private static final long serialVersionUID = -5561781116865187153L;

	private Long id;

	private String name;
	
	private String description;
	
	private VillainStatusEnum status;
	
	private Date creationDate;
	
	private Date modificationDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public VillainStatusEnum getStatus() {
		return status;
	}

	public void setStatus(VillainStatusEnum status) {
		this.status = status;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}
	
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Villain)) {
			return false;
		}
		
		final Villain other = (Villain)object;

		return new EqualsBuilder().append(getId(), other.getId())
				.append(getName(), other.getName())
				.append(getDescription(), other.getDescription())
				.append(getStatus(), other.getStatus())
				.append(getCreationDate(), other.getCreationDate())
				.append(getModificationDate(), other.getModificationDate())
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).append(getName()).append(getDescription()).append(getCreationDate()).append(getModificationDate()).append(getStatus()).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", getId())
				.append("name", getName())
				.append("description", getDescription())
				.append("status", getStatus())
				.append("creationDate", getCreationDate())
				.append("modificationDate", getModificationDate())
				.toString();
	}

}