<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="sidebar" class="sidebar">
    <!-- begin sidebar scrollbar -->
    <div data-scrollbar="true" data-height="100%">
        <!-- begin sidebar user -->
        <ul class="nav">
            <li class="nav-profile">
                <!--<div class="image">
                    <a href="javascript:;"><img src="assets/img/user-13.jpg" alt="" /></a>
                </div>-->
                <div class="info">Olá, USUARIO
                    ${usuario.getNomeUsuario()}
                </div>
            </li>
        </ul>
        <!-- end sidebar user -->
        <!-- begin sidebar nav -->
        <ul class="nav">
            <li class="nav-header">
                <div class="panel-heading">
                    <a class="text-white" href="CadastroProjeto.jsp" style="text-decoration: none; ">
                        <span class="label label-success m-r-10 pull-left">+</span>Novo projeto
                    </a>
                </div>
                <div class="panel-heading">
                    <h4 class="title text-white">Projetos</h4>
                </div>
            </li>
            <c:forEach var="p" items="${projeto}">
                <li class="has-sub">
                    <form action="frontcontroller"  method="POST">
                        <input type="hidden" name="command" value="ProjetoCommand_Atividade"/>
                        <input type="hidden" name="projeto" value="${p.CdProjeto}"/>
                        <a href="#" onclick="$(this).closest('form').submit()">
                            <b class="caret pull-right"></b>
                            <i class="fa fa-laptop"></i>
                            <span>${p.getNomeProjeto()}</span>
                        </a>
                    </form>
                </li>
            </c:forEach>

            <!--<li class="has-sub">
                <a href="javascript:;">
                    <b class="caret pull-right"></b>
                    <i class="fa fa-suitcase"></i>
                    <span>Projetos</span> 
                </a>
                <ul class="sub-menu">
                    <li><a href="ui_general.html">Projeto 1</a></li>
                    <li><a href="ui_typography.html">Projeto 2</a></li>
                    <li><a href="ui_tabs_accordions.html">Projeto 3</a></li>
                    <li><a href="ui_unlimited_tabs.html">Projeto 4</a></li>
                    <li><a href="ui_modal_notification.html">Projeto 5</a></li>
                    <li><a href="ui_widget_boxes.html">Projeto 6</a></li>
                </ul>
            </li>-->
        <!-- begin sidebar minify button -->
                <li><a href="javascript:;" class="sidebar-minify-btn" data-click="sidebar-minify"><i class="fa fa-angle-double-left"></i></a></li>
        <!-- end sidebar minify button -->
        </ul>
        <!-- end sidebar nav -->
    </div>
    <!-- end sidebar scrollbar -->
</div>