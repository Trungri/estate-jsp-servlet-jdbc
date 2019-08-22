package com.estate.service;


import java.util.List;

import com.estate.builder.BuildingSearchBuilder;
import com.estate.dto.BuildingDTO;
import com.estate.paging.Pageble;

public interface IBuildingService {
	BuildingDTO save (BuildingDTO newBuilding);
	void update (BuildingDTO updateBuilding, long id);
	void delete(Long[] ids);
	List<BuildingDTO> findAll(BuildingSearchBuilder builder, Pageble pageble);
	List<BuildingDTO> findAll(String url);
	int getTotalItem(BuildingSearchBuilder builder);
	int getTotalItem(String url);
	BuildingDTO findById (long id);
	BuildingDTO findById (String url);
	
	BuildingDTO delete (Long id);
	
}
