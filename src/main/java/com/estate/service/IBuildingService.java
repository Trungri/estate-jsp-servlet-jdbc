package com.estate.service;


import java.util.List;

import com.estate.builder.BuildingSearchBuilder;
import com.estate.dto.BuildingDTO;
import com.estate.paging.Pageble;

public interface IBuildingService {
	BuildingDTO save (BuildingDTO newBuilding);
	List<BuildingDTO> findAll(BuildingSearchBuilder builder, Pageble pageble);
	
	BuildingDTO update (BuildingDTO building);
	BuildingDTO delete (Long id);
	BuildingDTO findById (Long id);
}
