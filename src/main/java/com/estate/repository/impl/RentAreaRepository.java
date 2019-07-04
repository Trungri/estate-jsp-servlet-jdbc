package com.estate.repository.impl;

import com.estate.entity.RentArea;
import com.estate.repository.IRentAreaRepository;

public class RentAreaRepository extends AbstractJDBC<RentArea> implements IRentAreaRepository{

	@Override
	public void deleteByBuilding(long id) {
		String where = "WHERE buildingid = "+id+"";
		this.deleteByProperty(where);
	}

}
