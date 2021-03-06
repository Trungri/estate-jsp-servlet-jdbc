<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="buildingAPI" value="/api-admin-building" />
<c:url var="buildingURL" value="admin-building" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Danh sách toà nhà</title>
</head>

<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trang
							chủ</a></li>
					<li>Quản lí sản phẩm</li>
					<li>Danh sách sản phẩm</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<!--  star form -->
						<form action="${buildingURL}" method="get" id="formSubmit">
							<!--  search box -->
							<div class="widget-box table-filter">
								<div class="widget-header">
									<h4 class="widget-title">Tìm kiếm</h4>
									<div class="widget-toolbar">
										<a href="#" data-action="collapse"> <i
											class="ace-icon fa fa-chevron-up"></i>
										</a>
									</div>
								</div>
								<div class="widget-body">
									<div class="widget-main">
										<div class="form-horizontal">
											<div class="form-group">
												<div class="col-sm-6">
													<label><b>Tên Sản phẩm</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="name" value="${model.name}" />
													</div>
												</div>
												<div class="col-sm-6">
													<label><b>Diện tích sàn</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="buildingArea" value="${model.buildingArea}" />
													</div>
												</div>
											</div>
											<div class="form-group">
												<div class="col-sm-4">
													<label><b>Quận</b></label>
													<div class="fg-line">
														<select class="form-control" id="sel1" style="width: 50%"
															name="district">
															<option value="">--- Chọn quận ---</option>
															<c:forEach var="item" items="${districts}">
																<option value="${item.key}"
																	${item.key == model.district ? 'selected' : ''}>${item.value}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="col-sm-4">
													<label><b>Phường</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="ward" value="${model.ward}" />
													</div>
												</div>
												<div class="col-sm-4">
													<label><b>Đường</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="street" value="${model.street}" />
													</div>
												</div>
											</div>
											<div class="form-group">
												<div class="col-sm-4">
													<label><b>Số tầng hầm</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="numberOfBasement" value="${model.numberOfBasement}" />
													</div>
												</div>
												<div class="col-sm-4">
													<label><b>Hướng</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="direction" value="${model.direction}" />
													</div>
												</div>
												<div class="col-sm-4">
													<label><b>Hạng</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="level" value="${model.level}" />
													</div>
												</div>
											</div>
											<div class="form-group">
												<div class="col-sm-3">
													<label><b>Diện tích từ</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="areaRentFrom" value="${model.areaRentFrom}" />
													</div>
												</div>
												<div class="col-sm-3">
													<label><b>Diện tích đến</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="areaRentTo" value="${model.areaRentTo}" />
													</div>
												</div>
												<div class="col-sm-3">
													<label><b>Giá thuê từ</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="costRentFrom" value="${model.costRentFrom}" />
													</div>
												</div>
												<div class="col-sm-3">
													<label><b>Giá thuê đến</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="costRentTo" value="${model.costRentTo}" />
													</div>
												</div>
											</div>

											<div class="form-group">
												<div class="col-sm-4">
													<label><b>Tên quản lý</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="managerName" value="${model.managerName}" />
													</div>
												</div>
												<div class="col-sm-4">
													<label><b>Điện thoại quản lý</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="managerPhone" value="${model.managerPhone}" />
													</div>
												</div>
												<!-- <div class="col-sm-4">
													<label><b>Chọn nhân viên phụ trách</b></label>
													<div class="fg-line">
														<select class="form-control" id="sel1" style="width: 50%">
															<option value="">--- Chọn nhân viên ---</option>
															<option>Luong Van Trung</option>
															<option>Nguyen Thi A</option>
															<option>Le Van B</option>
														</select>
													</div>
												</div> -->
											</div>

											<div class="form-group">
												<div class="col-sm-12">
													<label><b>Loại toà nhà</b></label>
													<div class="fg-line">
														<c:forEach var="item" items="${buildingTypes}">
															<label class="checkbox-inline"> <input
																type="checkbox" value="${item.key}" name="buildingTypes"
																${fn:contains(fn:join(model.buildingTypes, ','), item.key) ? 'checked' : ''}><b>${item.value}</b>
															</label>
														</c:forEach>
													</div>
												</div>
											</div>
											<input type="hidden" name="action" value="LIST" />
											<div class="form-group">
												<div class="col-sm-6">
													<button type="button" class="btn btn-sm btn-success" id="btnSearch">
														Tìm kiếm <i
															class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
													</button>
												</div>
											</div>

										</div>
									</div>
								</div>
							</div>
							<input type="hidden" value="" id="page" name="page" />
							<input type="hidden" value="10" id="maxPageItem" name="maxPageItem"/>
							<input type="hidden" value="" id="sortName" name="sortName" />
							<input type="hidden" value="" id="sortBy" name="sortBy"/>
						</form>
						<!-- END form -->

						<!--  button add & delete -->
						<div class="table-btn-controls">
							<div class="pull-right tableTools-container">
								<div class="dt-buttons btn-overlap btn-group">
									<a flag="info"
										class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
										data-toggle="tooltip" title='Thêm tòa nhà'
										href='<c:url value="/admin-building?action=EDIT"/>'> <span><i
											class="fa fa-plus-circle bigger-110 purple"></i></span>
									</a>
									<button type="button" id="btnDelete"
										class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
										data-toggle="tooltip" title='Xóa tòa nhà'>
										<span><i class="fa fa-trash-o bigger-110 pink"></i></span>
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- table -->
				<div class="row">
					<div class="col-xs-12">
						<table class="table table-striped">
							<thead>
								<tr>
									<th><input type="checkbox" value="" id="checkAll"></th>
									<th>Tên sản phẩm</th>
									<th>Diện tích sàn</th>
									<th>Số tầng hầm</th>
									<th>Địa chỉ</th>
									<th>Giá thuê</th>
									<th>Diện tích thuê</th>
									<th>Loại tòa nhà</th>
									<th>Tên quản lí</th>
									<th>SĐT quản lí</th>
									<th>Thao tác</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${model.listResults}">
									<tr>
										<td><input type="checkbox" value="${item.id}"
											id="checkbox_${item.id}"></td>
										<td>${item.name}</td>
										<td>${item.buildingArea}</td>
										<td>${item.numberOfBasement}</td>
										<td>${item.address}</td>
										<td>${item.costRent}</td>
										<td>${item.rentArea}</td>
										<td>${item.type}</td>
										<td>${item.managerName}</td>
										<td>${item.managerPhone}</td>
										<td><a class="btn btn-xs btn-primary btn-edit"
											data-toggle="tooltip" title='Cập nhật tòa nhà'
											href='<c:url value="/admin-building?action=EDIT&id=${item.id}" />'>
												<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
										</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="container">
					<ul class="pagination" id="pagination"></ul>
				</div>

			</div>
		</div>
	</div>
	
	 <!-- Modal assignment-->
      <!--
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Giao nhà cho nhân viên</h4>
        </div>
        <div class="modal-body">
          <table class="table">
		    <thead>
		      <tr>
		        <th>check</th>
		        <th>Nhân viên</th>
		      </tr>
		    </thead>
		    <tbody>
		      <tr>
		       <input type="checkbox" value="checked">
		        <td>Doe</td>
		      </tr>
		      <tr>
		        <input type="checkbox" value="">
		        <td>Moe</td>
		      </tr>
    		</tbody>
  		</table>

        </div>
        <div class="modal-footer">
       	 <button type="button" class="btn btn-success">Save</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div> 
      -->
	
	
	<!-- /.main-content -->
	<script type="text/javascript">
	
		$('#btnSearch').click(function () {
			$('#page').val(1);
    		$('#formSubmit').submit(); 
		});
	
		$('#btnDelete').click(
				function name() {
					var dataArray = $('tbody input[type=checkbox]:checked')
							.map(function() {
								return $(this).val();
							}).get();
					//var data = {};
					//data['ids'] = dataArray;
					//deleteBuilding(data);
					
					deleteBuilding(dataArray);
		});

		function deleteBuilding(data) {
			$.ajax({
				//url : '${buildingAPI}',
				url : 'http://localhost:8087/api/building',
				data : JSON.stringify(data),
				type : 'DELETE',
				contentType : 'application/json',
				success : function(data) {
					window.location.href = "${buildingURL}?action=LIST&page=1&maxPageItem=10&message=delete_success";
				},
				error : function() {
					window.location.href = "${buildingURL}?action=LIST&page=1&maxPageItem=10&message=error_system";
				}
			});
		}
		
		var totalPage = ${model.totalPage};
   		var currentPage = ${model.page};		
		$(function () {
	        window.pagObj = $('#pagination').twbsPagination({
	            totalPages: totalPage,
	            visiblePages: 5,
	            startPage : currentPage,
	            onPageClick: function (event, page) {
	            	if(currentPage != page){
	            		$('#page').val(page);
	            		//$('#sortName').val('name');
	            		//$('#sortBy').val('ASC');
	            		$('#formSubmit').submit(); 
	            	} 	               
	            }
	        });
	    });
	</script>
</body>

</html>