package com.estate.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estate.dto.BuildingDTO;
import com.estate.service.IBuildingService;
import com.estate.service.impl.BuildingService;
import com.estate.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = {"/api-admin-building"})
public class BuildingAPI extends HttpServlet{

	private static final long serialVersionUID = 5112640190250860531L;
	 
	private IBuildingService buildingService;
	
	public BuildingAPI() {
		buildingService = new BuildingService();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		BuildingDTO buildingDTO = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
		buildingDTO = buildingService.save(buildingDTO);
		mapper.writeValue(response.getOutputStream(), buildingDTO);
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		BuildingDTO updateBuilding = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
		buildingService.update(updateBuilding, updateBuilding.getId());
		mapper.writeValue(response.getOutputStream(), "{}");
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		BuildingDTO buildingDTO = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
		buildingService.delete(buildingDTO.getIds());
		mapper.writeValue(response.getOutputStream(), "{}");
	}
	
	/*@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		BuildingDTO buildingDTO = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
		Long id = buildingDTO.getId();
		buildingDTO = buildingService.findById(id);
		mapper.writeValue(response.getOutputStream(), buildingDTO);
		   
	}*/
	

}
