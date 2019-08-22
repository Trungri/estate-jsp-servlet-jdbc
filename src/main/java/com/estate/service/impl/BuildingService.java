package com.estate.service.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.estate.utils.HttpClientUtils;
import org.apache.commons.lang.StringUtils;

import com.estate.builder.BuildingSearchBuilder;
import com.estate.converter.BuildingConverter;
import com.estate.dto.BuildingDTO;
import com.estate.entity.BuildingEntity;
import com.estate.entity.RentArea;
import com.estate.paging.Pageble;
import com.estate.repository.IBuildingRepository;
import com.estate.repository.IRentAreaRepository;
import com.estate.repository.impl.BuildingRepository;
import com.estate.repository.impl.RentAreaRepository;
import com.estate.service.IBuildingService;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

public class BuildingService implements IBuildingService {

	//@Inject
	private IBuildingRepository buildingRepository = new BuildingRepository();
	
	//@Inject
	private IRentAreaRepository rentAreaRepository = new RentAreaRepository();
	
	//@Inject
	private BuildingConverter buildingConverter = new BuildingConverter();

	final static Logger logger = Logger.getLogger(BuildingService.class);

	/*public BuildingService() {
		if(buildingRepository == null) {
			buildingRepository = new BuildingRepository();
		}
		if(buildingConverter == null) {
			buildingConverter = new BuildingConverter();
		}
	}*/

	@Override
	public BuildingDTO save(BuildingDTO buildingDTO) {
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		buildingEntity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		buildingEntity.setCreatedBy("");
		buildingEntity.setType(StringUtils.join(buildingDTO.getBuildingTypes(), ","));
		
		Long id = buildingRepository.insert(buildingEntity);
		//save rentarea
		for(String item : buildingDTO.getRentArea().split(",")) {
			RentArea rentArea = new RentArea();
			rentArea.setValue(item);
			rentArea.setBuildingId(id);
			rentAreaRepository.insert(rentArea);
		}
		return buildingConverter.convertToDTO(buildingRepository.findById(id));
	}


	@Override
	public BuildingDTO delete(Long id) {
		buildingRepository.delete(id);
		return null;
	}
	
	@Override
	public List<BuildingDTO> findAll(BuildingSearchBuilder builder, Pageble pageble) {
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(builder, pageble);
		List<BuildingDTO> results = buildingEntities.stream()
				.map(item -> buildingConverter.convertToDTO(item)).collect(Collectors.toList());
		return results;
	}

	@Override
	public List<BuildingDTO> findAll(String url) {
		String result = HttpClientUtils.httpGet(url);
		try{
			return Arrays.asList(new ObjectMapper().readValue(result, BuildingDTO[].class));
		}catch (IOException e){
			logger.error(e.getMessage(), e);
		}
		return new ArrayList<>();
	}

	@Override
	public int getTotalItem(String url) {
		String result = HttpClientUtils.httpGet(url);
		try{
			BuildingDTO buildingDTO = new ObjectMapper().readValue(result, BuildingDTO.class);
			return buildingDTO.getTotalItem();
		}catch (IOException e){
			logger.error(e.getMessage(), e);
		}
		return 0;
	}

	@Override
	public int getTotalItem(BuildingSearchBuilder builder) {
		return buildingRepository.countByProperty(builder);
	}

	@Override
	public BuildingDTO findById(long id) {
		return buildingConverter.convertToDTO(buildingRepository.findById(id));
	}

	@Override
	public void update(BuildingDTO updateBuilding, long id) {
		BuildingEntity oldBuilding = buildingRepository.findById(id);
		BuildingEntity newBuilding = buildingConverter.convertToEntity(updateBuilding);
		newBuilding.setCreatedBy(oldBuilding.getCreatedBy());
		newBuilding.setCreatedDate(oldBuilding.getCreatedDate());
		updateRentArea(updateBuilding.getRentArea(), id);
		
		newBuilding.setType(StringUtils.join(updateBuilding.getBuildingTypes(), ","));
		
		buildingRepository.update(newBuilding);
	}

	private void updateRentArea(String rentAreaStr, long buildingId) {
		//delete rent area by buildingId
		rentAreaRepository.deleteByBuilding(buildingId);
		//insert rent area
		for(String item : rentAreaStr.split(",")) {
			RentArea rentArea = new RentArea();
			rentArea.setBuildingId(buildingId);
			rentArea.setValue(item);
			rentAreaRepository.insert(rentArea);
		}
		
	}


	@Override
	public void delete(Long[] ids) {
		for(long item : ids) {
			rentAreaRepository.deleteByBuilding(item);
			buildingRepository.delete(item);			
		}
	}


	@Override
	public BuildingDTO findById(String url) {
		String result = HttpClientUtils.httpGet(url);
		try{
			return new ObjectMapper().readValue(result, BuildingDTO.class);
		}catch (IOException e){
			logger.error(e.getMessage(), e);
		}
		return new BuildingDTO();
	}

}
