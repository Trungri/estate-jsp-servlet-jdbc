package com.estate.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.estate.builder.BuildingSearchBuilder;
import com.estate.dto.BuildingDTO;
import com.estate.service.IBuildingService;
import com.estate.utils.DataUtils;
import com.estate.utils.FormUtil;

@WebServlet(urlPatterns = {"/admin-building"})
public class BuildingController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	final static Logger logger = Logger.getLogger(BuildingController.class);

	@Inject
	private IBuildingService buildingService;

	/*public BuildingController() {
		if(buildingService == null) {
			buildingService = new BuildingService();
		}
	}*/
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BuildingDTO model = FormUtil.toModel(BuildingDTO.class, request);
		String action = request.getParameter("action");
		String url = "";
		if (action.equals("LIST")) {
			url = "/views/building/list.jsp";
			
			BuildingSearchBuilder builder = initBuildingBuider(model);
			String findAllStr = "http://localhost:8087/api/building";
			StringBuilder findAllAPI = initBuildingParams(findAllStr,builder, model);
			String getTotalItemStr = "http://localhost:8087/api/building/total";
			StringBuilder getTotalItemAPI = initBuildingParams(getTotalItemStr,builder, model);

			/*old code*/
			/*Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(), new Sorter(model.getSortName(), model.getSortBy()));
			model.setTotalItem(buildingService.getTotalItem(builder));
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			model.setListResults(buildingService.findAll(builder, pageble));*/

			/*new code*/
			model.setTotalItem(buildingService.getTotalItem(getTotalItemAPI.toString()));
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			model.setListResults(buildingService.findAll(findAllAPI.toString()));

		} else if (action.equals("EDIT")) {
			if(model.getId() != null) {
				/*old code*/
				//model = buildingService.findById(model.getId());
				
				/*new code*/
				String urlFindById = "http://localhost:8087/api/"+model.getId()+"/building";
				model = buildingService.findById(urlFindById);
			}
			url = "/views/building/edit.jsp";
		}
		request.setAttribute("districts", DataUtils.getDistricts());
		request.setAttribute("buildingTypes", DataUtils.getBuildingType());
		request.setAttribute("model", model);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	private StringBuilder initBuildingParams(String sql, BuildingSearchBuilder builder, BuildingDTO model) {
		StringBuilder findAllAPI = new StringBuilder(sql);
		findAllAPI.append("?page="+model.getPage()+"&maxPageItem="+model.getMaxPageItem()+"");
		
		logger.info("begin add parameter to URL API");
		Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try{
				if(field.get(builder) != null){
					if(field.getName().equals("buildingTypes")){
						if(((String[]) field.get(builder)).length > 0){
							String[] buildingTypes = (String[]) field.get(builder);
							findAllAPI.append("&buildingTypes="+buildingTypes[0]+"");
							Arrays.stream(buildingTypes).filter(item -> !item.equals(buildingTypes[0]))
									.forEach(item -> findAllAPI.append(","+item+""));
							logger.info("URL API: "+findAllAPI.toString());
						}
					}else{
						findAllAPI.append("&"+field.getName()+"="+field.get(builder)+"");
					}
				}
			}catch (IllegalAccessException e){
				logger.error("Error add parameter to URL API : "+e.getMessage(), e);
			}
		}
		if(StringUtils.isNotBlank(model.getSortName())){
			findAllAPI.append("&sortBy="+model.getSortBy()+"&sortName="+model.getSortName()+"");
		}
		logger.info("URL API : "+findAllAPI.toString());
		return findAllAPI;
	}

	private BuildingSearchBuilder initBuildingBuider(BuildingDTO model) {
		BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder()
				.setName(model.getName())
				.setNumberOfBasement(model.getNumberOfBasement())
				.setBuildingArea(model.getBuildingArea())
				.setDistrict(model.getDistrict())
				.setWard(model.getWard())
				.setStreet(model.getStreet()).setAreaRentFrom(model.getAreaRentFrom()).setAreaRentTo(model.getAreaRentTo())
				.setCostRentFrom(model.getCostRentFrom()).setCostRentTo(model.getCostRentTo())
				.setBuildingTypes(model.getBuildingTypes())
				.build();
		return builder;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		super.doPost(req, resp);
	}
}
