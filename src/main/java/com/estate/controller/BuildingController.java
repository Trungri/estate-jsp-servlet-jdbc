package com.estate.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.estate.builder.BuildingSearchBuilder;
import com.estate.dto.BuildingDTO;
import com.estate.paging.PageRequest;
import com.estate.paging.Pageble;
import com.estate.paging.Sorter;
import com.estate.service.IBuildingService;
import com.estate.service.impl.BuildingService;
import com.estate.utils.DataUtils;
import com.estate.utils.FormUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

@WebServlet(urlPatterns = { "/admin-building"})
public class BuildingController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
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
			Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(), new Sorter(model.getSortName(), model.getSortBy()));
			model.setTotalItem(buildingService.getTotalItem(builder));
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			model.setListResults(buildingService.findAll(builder, pageble));

			DefaultHttpClient httpClient = new DefaultHttpClient();

			StringBuilder findAllAPI = new StringBuilder("http://localhost:8087/api/building");
			findAllAPI.append("?page="+model.getPage()+"&maxPageItem="+model.getMaxPageItem()+"");

			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				try{
					if(field.get(builder) != null){
						if(field.getName().equals("buildingTypes")){
							String[] buildingTypes = (String[]) field.get(builder);
							for(String buildingType : buildingTypes){
								findAllAPI.append("&buildingTypes="+buildingType+"");
							}
						}
					}else{
						findAllAPI.append("&"+field.getName()+"="+field.get(builder)+"");
					}
				}catch (IllegalAccessException e){
					e.printStackTrace();
				}
			}
			if(StringUtils.isNotBlank(model.getSortName())){
				findAllAPI.append("&sortBy="+model.getSortBy()+"&sortName="+model.getSortName()+"");
			}

			HttpGet getRequest = new HttpGet(findAllAPI.toString());
			try {
				HttpResponse httpResponse = httpClient.execute(getRequest);
				int statusCode = httpResponse.getStatusLine().getStatusCode();
				String result = "";
				if (statusCode >= 200 && statusCode < 300)
				{
					HttpEntity httpEntity = httpResponse.getEntity();
					result = EntityUtils.toString(httpEntity);
				}
				System.out.print(result);
			}catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("EDIT")) {
			if(model.getId() != null) {
				model = buildingService.findById(model.getId());
			}
			url = "/views/building/edit.jsp";
		}
		request.setAttribute("districts", DataUtils.getDistricts());
		request.setAttribute("buildingTypes", DataUtils.getBuildingType());
		request.setAttribute("model", model);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
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
