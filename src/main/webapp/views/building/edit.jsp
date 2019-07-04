<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="buildingAPI" value="/api-admin-building"/>
<c:url var="buildingURL" value="admin-building" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cập nhật tòa nhà</title>
<!-- upload pic -->

<script
	src="<c:url value='template/admin/js/custom/uploadPicture.js' />"></script>
<link rel="stylesheet"
	href="<c:url value='/template/admin/css/custom/uploadPicture.css' />" />
</head>

<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a
						href="admin-home">Trang chủ</a></li>
					<li>Sản phẩm</li>
					<li>Chỉnh sửa thông tin</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<div class="form-horizontal">
							<!--  star form -->
							<form id="formEdit">
								<div class="form-group">
									<label class="col-sm-3"><b>Tên Sản phẩm</b></label> <input
										class="col-sm-9" type="text" class="form-control input-sm"
										name="name" value="${model.name}" />
								</div>
								<div class="form-group">
									<label class="col-sm-3"><b>Diện tích sàn</b></label> 
									<input name="buildingArea" value="${model.buildingArea}" class="col-sm-9" type="number" class="form-control input-sm" />
								</div>
								<div class="form-group">
									<label class="col-sm-3"><b>Quận</b></label> 
									<select name="district"
										class="form-control" id="sel1" style="width: 20%">
										<option value="">--- Chọn quận ---</option>
										<c:forEach var="item" items="${districts}">
											<option value="${item.key}"
												${item.key == model.district ? 'selected' : ''}>${item.value}</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label class="col-sm-3"><b>Phường</b></label> <input
										class="col-sm-9" type="text" class="form-control input-sm"
										name="ward" value="${model.ward}" />
								</div>
								<div class="form-group">
									<label class="col-sm-3"><b>Đường</b></label> <input
										class="col-sm-9" type="text" class="form-control input-sm"
										name="street" value="${model.street}" />
								</div>
								<div class="form-group">
									<label class="col-sm-3"><b>Hướng</b></label> <input
										class="col-sm-9" type="text" class="form-control input-sm"
										name="direction" value="${model.direction}" />
								</div>
								<div class="form-group">
									<label class="col-sm-3"><b>Hạng</b></label> <input
										class="col-sm-9" type="text" class="form-control input-sm"
										name="level" value="${model.level}" />
								</div>
								<div class="form-group">
									<label class="col-sm-3"><b>Số tầng hầm</b></label> <input
										class="col-sm-9" type="number" class="form-control input-sm"
										name="numberOfBasement" value="${model.numberOfBasement}" />
								</div>								
								<div class="form-group">
									<label class="col-sm-3"><b>Diện tích thuê</b></label> <input
										class="col-sm-9" type="text" class="form-control input-sm"
										name="rentArea" value="${model.rentArea}" />
								</div>
								<div class="form-group">
									<label class="col-sm-3"><b>Giá thuê</b></label> <input
										class="col-sm-9" type="number" class="form-control input-sm"
										name="costRent" value="${model.costRent}" />
								</div>	
								<div class="form-group">
									<label class="col-sm-3"><b>Tên quản lý</b></label> <input
										class="col-sm-9" type="text" class="form-control input-sm"
										name="managerName" value="${model.managerName}" />
								</div>
								<div class="form-group">
									<label class="col-sm-3"><b>SĐT quản lý</b></label> <input
										class="col-sm-9" type="text" class="form-control input-sm"
										name="managerPhone" value="${model.managerPhone}" />
								</div>
								<div class="form-group">
									<label class="col-sm-3"><b>Loại tòa nhà</b></label>
									<div class="col-sm-9">
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
														
								<!-- <div class="form-group">
									<label class="col-sm-12"><b>Người quản lí sản phẩm</b></label>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3"><b>Kết cấu</b></label> <input
										class="col-sm-9" type="text" class="form-control input-sm" />
								</div>
								
								<div class="form-group">
									<label class="col-sm-3"><b>Mô tả diện tích</b></label>
									<textarea class="col-sm-9" class="form-control"></textarea>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3"><b>Mô tả giá</b></label> <input
										class="col-sm-9" type="text" class="form-control input-sm" />
								</div>
								<div class="form-group">
									<label class="col-sm-3"><b>Phí dịch vụ</b></label> <input
										class="col-sm-9" type="text" class="form-control input-sm" />
								</div>
								<div class="form-group">
									<label class="col-sm-3"><b>Phí ô tô</b></label> <input
										class="col-sm-9" type="text" class="form-control input-sm" />
								</div>
								<div class="form-group">
									<label class="col-sm-3"><b>Phí mô tô</b></label> <input
										class="col-sm-9" type="text" class="form-control input-sm" />
								</div>
								<div class="form-group">
									<label class="col-sm-3"><b>Phí ngoài giờ</b></label> <input
										class="col-sm-9" type="text" class="form-control input-sm" />
								</div>
								<div class="form-group">
									<label class="col-sm-3"><b>Tiền điện</b></label> <input
										class="col-sm-9" type="text" class="form-control input-sm" />
								</div>
								<div class="form-group">
									<label class="col-sm-3"><b>Đặt cọc</b></label> <input
										class="col-sm-9" type="text" class="form-control input-sm" />
								</div>
								<div class="form-group">
									<label class="col-sm-3"><b>Thanh toán</b></label> <input
										class="col-sm-9" type="text" class="form-control input-sm" />
								</div>
								<div class="form-group">
									<label class="col-sm-3"><b>Thời hạn thuê</b></label> <input
										class="col-sm-9" type="text" class="form-control input-sm" />
								</div>
								<div class="form-group">
									<label class="col-sm-3"><b>Thời gian trang trí</b></label> <input
										class="col-sm-9" type="text" class="form-control input-sm" />
								</div>
								
								<div class="form-group">
									<label class="col-sm-3"><b>Phí môi giới</b></label> <input
										class="col-sm-9" type="text" class="form-control input-sm" />
								</div> -->
								
								<input type="hidden" name="id" value="${model.id}"
									id="buildingId" />

							</form>
							<!-- END form -->
							<!-- Upload picture -->
							<div class="form-group">
								<label class="col-sm-3"><b>Chọn hình ảnh</b></label>
								<div class="col-sm-9">
									<input type="file" class="form-control-file"
										id="exampleFormControlFile1">
								</div>
							</div>
							<c:if test="${empty model.id}">
								<button class="btn btn-info" type="button"
									id="btnAddOrUpdateBuilding">
									<i class="ace-icon fa fa-check bigger-110"></i>Thêm tòa nhà
								</button>
							</c:if>
							<c:if test="${not empty model.id}">
								<button class="btn btn-info" type="button"
									id="btnAddOrUpdateBuilding">
									<i class="ace-icon fa fa-check bigger-110"></i>Cập nhật tòa nhà
								</button>
							</c:if>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$("#btnAddOrUpdateBuilding").click(function() {
			addOrUpdateBuilding();
		});
		function addOrUpdateBuilding() {
			var buildingId = $('#buildingId').val();
			var formData = $('#formEdit').serializeArray();
			var data = {};
			var buildingTypes = [];
			$.each(formData, function(index, v) {
				if (v.name == 'buildingTypes') {
					buildingTypes.push(v.value);
				} else {
					data[""+v.name+""] = v.value;
				}
			});
			data['buildingTypes'] = buildingTypes;
			if (buildingId == '') {
				addBuilding(data);
			} else {
				editBuilding(data, buildingId);
			}
		}
		function addBuilding(data) {
			$.ajax({
				url : '${buildingAPI}',
				data : JSON.stringify(data),
				type : 'POST',
				contentType : 'application/json',
				dataType : 'json',
				success : function(data) {
					window.location.href = "${buildingURL}?action=EDIT&id="+data.id+"&message=insert_success";
				},
				error : function() {
					window.location.href = "${buildingURL}?action=LIST&message=error_system";
				}
			});
		}
		function editBuilding(data, id) {
			$.ajax({
				url : '${buildingAPI}',
				data : JSON.stringify(data),
				type : 'PUT',
				contentType : 'application/json',
				success : function(data) {
					window.location.href = "${buildingURL}?action=EDIT&id="+id+"&message=update_success";
				},
				error : function() {
					window.location.href = "${buildingURL}?action=LIST&message=error_system";
				}
			});
		}
	</script>
</body>

</html>






