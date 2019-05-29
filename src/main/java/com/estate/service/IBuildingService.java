package com.estate.service;

import com.estate.dto.BuildingDTO;

public interface IBuildingService {
	BuildingDTO save (BuildingDTO newBuilding);
	BuildingDTO update (BuildingDTO building);
	BuildingDTO delete (BuildingDTO building);
	BuildingDTO findById (BuildingDTO building);
}
