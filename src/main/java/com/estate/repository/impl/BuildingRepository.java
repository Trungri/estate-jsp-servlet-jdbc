package com.estate.repository.impl;

import com.estate.entity.BuildingEntity;
import com.estate.repository.IBuildingRepository;

public class BuildingRepository extends AbstractJDBC<BuildingEntity> implements IBuildingRepository{

	/*@Override
	public Long insert(BuildingEntity entity) {
		StringBuilder sql = new StringBuilder("INSERT INTO building VALUES (?, ? )");
		sql.append("15:58");
		return this.insert(sql.toString(), entity.getName(), entity.getNumberOfBasement());
		
	}
*/
}
