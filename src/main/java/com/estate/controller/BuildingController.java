package com.estate.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estate.builder.BuildingSearchBuilder;
import com.estate.dto.BuildingDTO;
import com.estate.paging.PageRequest;
import com.estate.paging.Pageble;
import com.estate.service.IBuildingService;
import com.estate.service.impl.BuildingService;
import com.estate.utils.FormUtil;

@WebServlet(urlPatterns = { "/admin-building" })
public class BuildingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IBuildingService buildingService;

	public BuildingController() {
		if(buildingService == null) {
			buildingService = new BuildingService();
		}
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BuildingDTO model = FormUtil.toModel(BuildingDTO.class, request);
		String action = request.getParameter("action");
		
		String url = "";
		if (action.equals("LIST")) {
			url = "/views/building/list.jsp";
			BuildingSearchBuilder builder = initBuildingBuider(model);
			Pageble pageble = new PageRequest(null, null, null);
			model.setListResults(buildingService.findAll(builder, pageble));
		} else if (action.equals("EDIT")) {
			url = "/views/building/edit.jsp";
		}
		
		request.setAttribute("model", model);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	private BuildingSearchBuilder initBuildingBuider(BuildingDTO model) {
		BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder()
				.setName(model.getName())
				.setNumberOfBasement(model.getNumberOfBasement())
				.setWard(model.getWard())
				.setStreet(model.getStreet())
				.setAreaRentFrom(model.getAreaRentFrom())
				.setAreaRentTo(model.getAreaRentTo())
				.setCostRentFrom(model.getAreaRentFrom())
				.setCostRentTo(model.getCostRentTo()).build();
		return builder;
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
