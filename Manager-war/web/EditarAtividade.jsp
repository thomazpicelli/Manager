<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
<head>
	<meta charset="utf-8" />
	<title>Management</title>
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
	
        <!-- ================== BEGIN PAGE CSS STYLE ================== -->
	<link href="assets/plugins/simple-line-icons/simple-line-icons.css" rel="stylesheet" />
        <!--[if lte IE 7]><script src="assets/plugins/simple-line-icons/icons-lte-ie7.js"></script><![endif]-->
	
        <!-- ================== BEGIN PAGE CSS STYLE ================== -->	
	<!-- ================== BEGIN BASE JS ================== -->
	<script src="assets/plugins/pace/pace.min.js"></script>
	<!-- ================== END BASE JS ================== -->
</head>
<body>
	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in"><span class="spinner"></span></div>
	<!-- end #page-loader -->
	
	<!-- begin #page-container -->
	<div id="page-container" class="fade page-sidebar-fixed page-header-fixed page-content-full-height">
            <!-- begin #header -->
            <%@ include file= "header.jsp" %>
            <!-- end #header -->

            <!-- begin #sidebar -->
            <%@ include file= "sidebar.jsp" %>
            <!-- end #sidebar -->

            <!-- begin #content -->
            <div id="content" class="content content-full-width">
                <div class="vertical-box">
                    <div class="vertical-box-column width-md">
                        <div class="vertical-box">
                            <div class="wrapper bg-grey-lighter text-uppercase" style="font-size: 16pt;">
                                <b>${projeto.getNome()}</b>
                            </div>
                            <div class="vertical-box-row bg-grey-lighter text-white">
                                <div class="vertical-box-cell">
                                    <div class="vertical-box-inner-cell">
                                        <div data-scrollbar="true" data-height="100%" class="wrapper">
                                            <div class="row">
                                                <!-- begin col-12 -->
                                                <div class="col-md-12">
                                                    <%@ include file= "ListaAtividade.jsp" %>
                                                </div>
                                                <!-- end col-12 -->
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="wrapper bg-grey-lighter" style="color: black;">
                                <form action="frontcontroller" method="POST">
                                    <input type="hidden" name="command" value="projetoCommand_Voltar"/>
                                    <a href="#" onclick="$(this).closest('form').submit()">
                                        Voltar
                                    </a>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="vertical-box-column width-md">
                        <div class="vertical-box">
                            <div class="wrapper bg-grey-lighter" style="font-size: 14pt;">
                                Editar Atividade
                            </div>
                            <div class="vertical-box-row bg-grey-lighter">
                                <div class="vertical-box-cell">
                                    <div class="vertical-box-inner-cell">
                                        <div data-scrollbar="true" data-height="100%" class="wrapper">
                                            <div class="row">
                                                <!-- begin panel -->
                    <div class="panel panel-inverse" data-sortable-id="form-validation-1">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">${tarefa.getNome()}</h4>
                        </div>
                        <div class="panel-body panel-form">
                            <form class="form-horizontal form-bordered" data-parsley-validate="true" name="demo-form" action="FrontController" method="POST">
								<div class="form-group">
									<label class="control-label col-md-3 col-sm-3" for="nome">Nome * :</label>
									<div class="col-md-7 col-sm-7">
                                                                            <input class="form-control" type="text" id="nome" name="nome" placeholder="Nome" data-parsley-required="true" value="${tarefa.getNome()}"/>
									</div>
                                                                        <label class="control-label col-md-3 col-sm-3" for="descricao">Descrição * :</label>
                                                                        <div class="col-md-7 col-sm-7">
										<textarea class="form-control" id="descricao" name="descricao" rows="3" placeholder="Descrição da Atividade" data-parsley-required="true" />${tarefa.getDescricao()}</textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3 col-sm-3" for="ferramenta">Ferramenta :</label>
									<div class="col-md-7 col-sm-7">
                                                                            <input class="form-control" type="text" id="ferramenta" name="ferramenta" placeholder="Ferramenta" value="${tarefa.getFerramenta()}"/>
									</div>
                                                                        <label class="control-label col-md-3 col-sm-3" for="datafim">Data Finalização *:</label>
                                                                        <div class="col-md-7 col-sm-7">
                                                                            <input class="form-control" type="text" id="datafim" name="datafim" placeholder="____-__-__" value="${tarefa.getDtFinal()}"/>
                                                                        </div>
								</div>
                                                                <div class="form-group">
                                                                    <label class="control-label col-md-3 col-sm-3">Status</label>
                                                                    <div class="col-md-7 col-sm-7">    
                                                                        <select name="status" class="form-control">
                                                                            <c:forEach var="s" items="${status}">
                                                                                <c:choose>
                                                                                    <c:when test="${s.toString() == tarefa.getStatus().toString()}">
                                                                                        <option selected value="${s.toString()}">${s.toString()}</option>
                                                                                    </c:when>
                                                                                    <c:otherwise>
                                                                                        <option value="${s.toString()}">${s.toString()}</option>
                                                                                    </c:otherwise>
                                                                                </c:choose>                                                                                
                                                                            </c:forEach>               
                                                                        </select>
                                                                    </div>
                                                               </div>                                                                        
                                                                <div class="form-group">
                                                                    <label class="control-label col-md-3 col-sm-3">Responsável</label>
                                                                    <div class="col-md-7 col-sm-7">    
                                                                        <select name="responsavel" class="form-control">
                                                                            <c:forEach var="c" items="${colaborador}">
                                                                                <c:choose>
                                                                                    <c:when test="${c.getCdUsuario() == tarefa.getColaborador().getCdUsuario()}">
                                                                                    <option selected value="${c.getCdUsuario()}">${c.getNome()}</option>
                                                                                    </c:when>
                                                                                    <c:otherwise>
                                                                                        <option value="${c.getCdUsuario()}">${c.getNome()}</option>
                                                                                    </c:otherwise>
                                                                                </c:choose>
                                                                            </c:forEach>               
                                                                        </select>
                                                                    </div>
                                                               </div>
                                                                <input type="hidden" name="command" value="TarefaCommand_altera"/>
								<div class="form-group">
									<label class="control-label col-md-3 col-sm-3"></label>
									<div class="col-md-7 col-sm-7">
										<button type="submit" class="btn btn-warning">Editar</button>
									</div>
								</div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
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
