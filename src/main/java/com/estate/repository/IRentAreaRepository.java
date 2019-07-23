package com.estate.repository;

import com.estate.entity.RentArea;

public interface IRentAreaRepository extends JpaRepository<RentArea>{
	void deleteByBuilding(long id);
}
