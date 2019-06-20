<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

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
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trang chủ</a></li>
					<li>Quản lí sản phẩm</li>
					<li>Danh sách sản phẩm</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<!--  star form -->
						<form action="${buildingURL}" method="get">
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
														<input type="text" class="form-control input-sm" name="name" value="${model.name}"/>
													</div>
												</div>
												<div class="col-sm-6">
													<label><b>Diện tích sàn</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm" name="buildingArea" value="${model.buildingArea}" />
													</div>
												</div>
											</div>
											<div class="form-group">
												<div class="col-sm-4">
													<label><b>Quận hiện có</b></label>
													<div class="fg-line">
														<select class="form-control" id="sel1" style="width: 50%" name="district">
															<option selected>--- Chọn quận ---</option>
															<option value="QUAN_1">Quận 1</option>
															<option value="QUAN_2">Quận 2</option>
															<option value="QUAN_3">Quận 3</option>
															<option value="QUAN_4">Quận 4</option>
														</select>
													</div>
												</div>
												<div class="col-sm-4">
													<label><b>Phường</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm" name="ward" value="${model.ward}"  />
													</div>
												</div>
												<div class="col-sm-4">
													<label><b>Đường</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm" name="street" value="${model.street}"  />
													</div>
												</div>
											</div>
											<div class="form-group">
												<div class="col-sm-4">
													<label><b>Số tầng hầm</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm" name="numberOfBasement" value="${model.numberOfBasement}"/>
													</div>
												</div>
												<div class="col-sm-4">
													<label><b>Hướng</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm" name="direction" value="${model.direction}" />
													</div>
												</div>
												<div class="col-sm-4">
													<label><b>Hạng</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm" name="level" value="${model.level}"/>
													</div>
												</div>
											</div>
											<div class="form-group">
												<div class="col-sm-3">
													<label><b>Diện tích từ</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm" name="areaRentFrom" value="${model.areaRentFrom}"/>
													</div>
												</div>
												<div class="col-sm-3">
													<label><b>Diện tích đến</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm" name="areaRentTo" value="${model.areaRentTo}"/>
													</div>
												</div>
												<div class="col-sm-3">
													<label><b>Giá thuê từ</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm" name="costRentFrom" value="${model.costRentFrom}"/>
													</div>
												</div>
												<div class="col-sm-3">
													<label><b>Giá thuê đến</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm" name="costRentTo" value="${model.costRentTo}"/>
													</div>
												</div>
											</div>
	
											<div class="form-group">
												<div class="col-sm-4">
													<label><b>Tên quản lý</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm" name="managerName" value="${model.managerName}" />
													</div>
												</div>
												<div class="col-sm-4">
													<label><b>Điện thoại quản lý</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm" name="managerPhone" value="${model.managerPhone}"  />
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
														<label class="checkbox-inline"><input type="checkbox" value="TANG_TRET" name="buildingTypes"><b>Tầng trệt</b></label> 
														<label class="checkbox-inline"><input type="checkbox" value="NGUYEN_CAN" name="buildingTypes"><b>Nguyên căn</b></label> 
														<label class="checkbox-inline"><input type="checkbox" value="NOI_THAT" name="buildingTypes"><b>Nội thất</b></label>
													</div>
												</div>
											</div>
											<input type="hidden" name="action" value="LIST" />
											<div class="form-group">
												<div class="col-sm-6">
													<button type="submit" class="btn btn-sm btn-success">
														Tìm kiếm
														<i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
													</button>
												</div>
											</div>
											
										</div>
									</div>
								</div>
							</div>
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
									<button type="button"
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
									<th>Ngày</th>
									<th>Tên sản phẩm</th>
									<th>Địa chỉ</th>
									<th>Tên quản lí</th>
									<th>SĐT</th>
									<th>Thao tác</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>John</td>
									<td>Doe</td>
									<td>john@example.com</td>
									<td>John</td>
									<td>Doe</td>
									<td>hi</td>
								</tr>
								<tr>
									<td>Mary</td>
									<td>Moe</td>
									<td>mary@example.com</td>
									<td>John</td>
									<td>Doe</td>
									<td>john@example.com</td>
								</tr>
								<tr>
									<td>July</td>
									<td>Dooley</td>
									<td>july@example.com</td>
									<td>John</td>
									<td>Doe</td>
									<td>john@example.com</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.main-content -->
</body>

</html>