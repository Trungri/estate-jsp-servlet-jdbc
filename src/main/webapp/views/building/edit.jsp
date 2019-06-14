<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<!-- upload pic -->
<link
	href="<c:url value='/template/web/bootstrap/css/bootstrap.min.css'/>  id="bootstrap-css">
<link rel="stylesheet"
	href="<c:url value='/template/admin/css/uploadPicture.css' />" />
</head>

<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trang
							chủ</a></li>
					<li>Sản phẩm</li>
					<li>Chỉnh sửa thông tin</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<div class="form-horizontal">
							<div class="form-group">
								<label class="col-sm-3"><b>Tên Sản phẩm</b></label> <input
									class="col-sm-9" type="text" class="form-control input-sm" />
							</div>
							<div class="form-group">
								<label class="col-sm-12"><b>Người quản lí sản phẩm</b></label>
							</div>
							<div class="form-group">
								<label class="col-sm-3"><b>Quận</b></label> <select
									class="form-control" id="sel1" style="width: 20%">
									<option value="">--- Chọn quận ---</option>
									<option>Thanh Khuê</option>
									<option>Hải Châu</option>
									<option>Sơn Trà</option>
									<option>Liên Chiểu</option>
								</select>
							</div>
							<div class="form-group">
								<label class="col-sm-3"><b>Phường</b></label> <input
									class="col-sm-9" type="text" class="form-control input-sm" />
							</div>
							<div class="form-group">
								<label class="col-sm-3"><b>Đường</b></label> <input
									class="col-sm-9" type="text" class="form-control input-sm" />
							</div>
							<div class="form-group">
								<label class="col-sm-3"><b>Kết cấu</b></label> <input
									class="col-sm-9" type="text" class="form-control input-sm" />
							</div>
							<div class="form-group">
								<label class="col-sm-3"><b>Số tầng hầm</b></label> <input
									class="col-sm-9" type="text" class="form-control input-sm" />
							</div>
							<div class="form-group">
								<label class="col-sm-3"><b>Diện tích sàn</b></label> <input
									class="col-sm-9" type="text" class="form-control input-sm" />
							</div>
							<div class="form-group">
								<label class="col-sm-3"><b>Hướng</b></label> <input
									class="col-sm-9" type="text" class="form-control input-sm" />
							</div>
							<div class="form-group">
								<label class="col-sm-3"><b>Hạng</b></label> <input
									class="col-sm-9" type="text" class="form-control input-sm" />
							</div>
							<div class="form-group">
								<label class="col-sm-3"><b>Diện tích thuê</b></label> <input
									class="col-sm-9" type="text" class="form-control input-sm" />
							</div>
							<div class="form-group">
								<label class="col-sm-3"><b>Mô tả diện tích</b></label>
								<textarea class="col-sm-9" class="form-control"></textarea>
							</div>
							<div class="form-group">
								<label class="col-sm-3"><b>Giá thuê</b></label> <input
									class="col-sm-9" type="text" class="form-control input-sm" />
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
								<label class="col-sm-3"><b>Tên quản lý</b></label> <input
									class="col-sm-9" type="text" class="form-control input-sm" />
							</div>
							<div class="form-group">
								<label class="col-sm-3"><b>SĐT quản lý</b></label> <input
									class="col-sm-9" type="text" class="form-control input-sm" />
							</div>
							<div class="form-group">
								<label class="col-sm-3"><b>Phí môi giới</b></label> <input
									class="col-sm-9" type="text" class="form-control input-sm" />
							</div>
							<div class="form-group">
								<label class="col-sm-3"><b>Loại sản phẩm</b></label>
								<div class="col-sm-9">
									<div class="checkbox">
										<label><input type="checkbox" value="">Option
											1</label>
									</div>
									<div class="checkbox">
										<label><input type="checkbox" value="">Option
											2</label>
									</div>
									<div class="checkbox">
										<label><input type="checkbox" value="">Option
											3</label>
									</div>
								</div>
							</div>
							<!-- Upload picture -->

							<div class="container">
								<div class="row">
									<div
										class="col-xs-12 col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
										<!-- image-preview-filename input [CUT FROM HERE]-->
										<div class="input-group image-preview">
											<input type="text"
												class="form-control image-preview-filename"
												disabled="disabled">
											<!-- don't give a name === doesn't send on POST/GET -->
											<span class="input-group-btn"> <!-- image-preview-clear button -->
												<button type="button"
													class="btn btn-default image-preview-clear"
													style="display: none;">
													<span class="glyphicon glyphicon-remove"></span> Clear
												</button> <!-- image-preview-input -->
												<div class="btn btn-default image-preview-input">
													<span class="glyphicon glyphicon-folder-open"></span> <span
														class="image-preview-input-title">Browse</span> <input
														type="file" accept="image/png, image/jpeg, image/gif"
														name="input-file-preview" />
													<!-- rename it -->
												</div>
											</span>
										</div>
										<!-- /input-group image-preview [TO HERE]-->
									</div>
								</div>
							</div>

							<button class="btn btn-info" type="button">
								<i class="ace-icon fa fa-check bigger-110"></i> Sửa
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- upload pic -->
	<script
		src="<c:url value='/template/admin/assets/js/bootstrap.min.js' />"></script>
	<script src="<c:url value='/template/admin/js/jquery-1.11.1.min.js'/>"></script>
	<script src="<c:url value='template/admin/js/uploadPicture.js' />"></script>
</body>

</html>