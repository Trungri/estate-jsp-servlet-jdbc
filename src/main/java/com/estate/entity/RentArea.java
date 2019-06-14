package com.estate.entity;

import com.estate.annotation.Column;
import com.estate.annotation.Entity;
import com.estate.annotation.Table;

@Entity
@Table(name = "rentarea")
public class RentArea extends BaseEntity {
	
	@Column(name = "value")
	private String value;

	@Column(name = "buildingid")
	private Long buildingId;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	
	
}
