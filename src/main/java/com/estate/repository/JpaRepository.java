package com.estate.repository;

import java.util.List;
import java.util.Map;

import com.estate.paging.Pageble;

public interface JpaRepository<T> {
	/*List<T> query(String sql, Object... parameters);
	void update(String sql, Object... parameters);
	Long insert(String sql, Object... parameters);*/
	
	List<T> findAll(Map<String, Object> properties,Pageble pageble, Object... where);
	Long insert(Object object);
	void update(Object object);
	void delete(long id);
	void deleteByProperty(String where);
	<T> T findById(long id);
	int countByProperty(Map<String, Object> properties, Object... where);
}
