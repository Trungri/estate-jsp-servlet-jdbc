package com.estate.repository;

import java.util.List;

import com.estate.builder.BuildingSearchBuilder;
import com.estate.entity.BuildingEntity;
import com.estate.paging.Pageble;

public interface IBuildingRepository extends GenericJDBC<BuildingEntity>{
	//Long insert(BuildingEntity buildingEntity);
	List<BuildingEntity> findAll(BuildingSearchBuilder builder, Pageble pageble);
}
