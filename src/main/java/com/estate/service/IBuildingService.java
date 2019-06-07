package com.estate.service;

import com.estate.dto.BuildingDTO;

public interface IBuildingService {
	BuildingDTO save (BuildingDTO newBuilding);
	BuildingDTO update (BuildingDTO building);
	BuildingDTO delete (Long id);
	BuildingDTO findById (Long id);
}
