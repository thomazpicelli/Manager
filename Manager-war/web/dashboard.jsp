<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
	<meta charset="utf-8" />
	<title>Color Admin | Basic Tables</title>
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	
	<!-- ================== BEGIN BASE CSS STYLE ================== -->
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">
	<link href="assets/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css" rel="stylesheet" />
	<link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
	<link href="assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
	<link href="assets/css/animate.min.css" rel="stylesheet" />
	<link href="assets/css/style.min.css" rel="stylesheet" />
	<link href="assets/css/style-responsive.min.css" rel="stylesheet" />
	<link href="assets/css/theme/default.css" rel="stylesheet" id="theme" />
	<!-- ================== END BASE CSS STYLE ================== -->
	
	<!-- ================== BEGIN BASE JS ================== -->
	<script src="assets/plugins/pace/pace.min.js"></script>
	<!-- ================== END BASE JS ================== -->
</head>
<body>
	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in"><span class="spinner"></span></div>
	<!-- end #page-loader -->
	
	<!-- begin #page-container -->
	<div id="page-container" class="fade page-sidebar-fixed page-header-fixed">
		<!-- begin #header -->
                <%@ include file= "header.jsp" %>
                <!-- end #header -->
		
		<!-- begin #sidebar -->
                <%@ include file= "sidebar.jsp" %>
		<!-- end #sidebar -->
		
		<!-- begin #content -->
		<div id="content" class="content">
			<!-- begin breadcrumb -->
			<ol class="breadcrumb pull-right">
				<li><a href="javascript:;">Home</a></li>
				<li><a href="javascript:;">Dashboard</a></li>
			</ol>
			<!-- end breadcrumb -->
			<!-- begin page-header -->
			<h1 class="page-header">Dashboard</h1>
			<!-- end page-header -->
			<!-- begin row -->
			<div class="row">
			    <!-- begin col-6 -->
			    <div class="col-md-6">
			        <!-- begin panel -->
                    
                    <!-- end panel -->
			        <!-- begin panel -->
                    <div class="panel panel-inverse" data-sortable-id="table-basic-2">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">Hover Table</h4>
                        </div>
                        <div class="panel-body">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Username</th>
                                        <th>Email Address</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>Nicky Almera</td>
                                        <td>nicky@hotmail.com</td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>Edmund Wong</td>
                                        <td>edmund@yahoo.com</td>
                                    </tr>
                                    <tr>
                                        <td>3</td>
                                        <td>Harvinder Singh</td>
                                        <td>harvinder@gmail.com</td>
                                    </tr>
                                    <tr>
                                        <td>4</td>
                                        <td>Terry Khoo</td>
                                        <td>terry@gmail.com</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- end panel -->
			        <!-- begin panel -->
                    	<div class="row">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-inverse" data-sortable-id="table-basic-7">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">Tarefas em aberto</h4>
                        </div>
                        <div class="panel-body">
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr>
											<th>#</th>
											<th>Tarefa</th>
											<th>Data</th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>1</td>
											<td>Table cell</td>
											<td>Table cell</td>
											<td>Table cell</td>
											<td>Table cell</td>
											<td>Table cell</td>
											<td>Table cell</td>
										</tr>
										<tr>
											<td>2</td>
											<td>Table cell</td>
											<td>Table cell</td>
											<td>Table cell</td>
											<td>Table cell</td>
											<td>Table cell</td>
											<td>Table cell</td>
										</tr>
										<tr>
											<td>3</td>
											<td>Table cell</td>
											<td>Table cell</td>
											<td>Table cell</td>
											<td>Table cell</td>
											<td>Table cell</td>
											<td>Table cell</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="table-responsive">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th>#</th>
											<th>Table heading</th>
											<th>Table heading</th>
											<th>Table heading</th>
											<th>Table heading</th>
											<th>Table heading</th>
											<th>Table heading</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>1</td>
											<td>Table cell</td>
											<td>Table cell</td>
											<td>Table cell</td>
											<td>Table cell</td>
											<td>Table cell</td>
											<td>Table cell</td>
										</tr>
										<tr>
											<td>2</td>
											<td>Table cell</td>
											<td>Table cell</td>
											<td>Table cell</td>
											<td>Table cell</td>
											<td>Table cell</td>
											<td>Table cell</td>
										</tr>
										<tr>
											<td>3</td>
											<td>Table cell</td>
											<td>Table cell</td>
											<td>Table cell</td>
											<td>Table cell</td>
											<td>Table cell</td>
											<td>Table cell</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
                    <!-- end panel -->
			    </div>
			    <!-- end col-12 -->
			</div>
			<!-- end row -->
		</div>
		<!-- end #content -->
		
		<!-- begin scroll to top btn -->
		<a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
		<!-- end scroll to top btn -->
	</div>
	<!-- end page container -->
	
	<!-- ================== BEGIN BASE JS ================== -->
	<script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
	<script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
	<script src="assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
	<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
	<!--[if lt IE 9]>
		<script src="assets/crossbrowserjs/html5shiv.js"></script>
		<script src="assets/crossbrowserjs/respond.min.js"></script>
		<script src="assets/crossbrowserjs/excanvas.min.js"></script>
	<![endif]-->
	<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
	<!-- ================== END BASE JS ================== -->
	
	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->
	
	<script>
		$(document).ready(function() {
			App.init();
		});
	</script>
</body>
</html>
