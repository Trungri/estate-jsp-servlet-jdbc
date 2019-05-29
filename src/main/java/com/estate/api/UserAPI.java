package com.estate.api;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUtils;

import com.estate.dto.BuildingDTO;
import com.estate.dto.UserDTO;
import com.estate.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = {"/api-admin-user"})
public class UserAPI extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		UserDTO userDTO = HttpUtil.of(request.getReader()).toModel(UserDTO.class);
		
		mapper.writeValue(response.getOutputStream(), userDTO);
	}
	

}
