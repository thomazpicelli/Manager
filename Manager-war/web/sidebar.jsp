<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="sidebar" class="sidebar">
    <!-- begin sidebar scrollbar -->
    <div data-scrollbar="true" data-height="100%">
        <!-- begin sidebar user -->
        <ul class="nav">
            <li class="nav-profile">
                <c:choose>
                    <c:when test="${usuario != null && usuario != ''}">
                        <div class="info">Olá, ${usuario.getNome()} - ${usuario.getNivelAcesso()}</div>
                    </c:when>
                    <c:otherwise>
                        <c:redirect url="index.jsp"></c:redirect>
                    </c:otherwise>
                </c:choose>  
                <p><a href="./index.jsp">SAIR</a></p>
            </li>
        </ul>
        <!-- end sidebar user -->
        <!-- begin sidebar nav -->
        <ul class="nav">
            <li class="nav-header">
                <c:if test="${usuario.getNivelAcesso() != 'DESENVOLVEDOR'}">
                    <div class="panel-heading">
                        <a class="text-white" href="CadastroProjeto.jsp" style="text-decoration: none; ">
                            <span class="label label-success m-r-10 pull-left">+</span>Novo projeto
                        </a>
                    </div>
                </c:if>
                <div class="panel-heading">
                    <h4 class="title text-white">Projetos</h4>
                </div>
            </li>            
            <c:forEach var="p" items="${projetos}">
                <li>
                    <form action="FrontController"  method="POST">
                        <input type="hidden" name="command" value="ProjetoCommand_atual"/>
                        <input type="hidden" name="cdProjeto" value="${p.getCdProjeto()}"/>
                        <a href="#" onclick="$(this).closest('form').submit()"> 
                            <i class="fa fa-laptop"></i>
                            <span>${p.getNome()}</span>
                        </a>
                    </form>
                </li>
            </c:forEach>
            <li><a href="javascript:;" class="sidebar-minify-btn" data-click="sidebar-minify"><i class="fa fa-angle-double-left"></i></a></li>
            <!-- end sidebar minify button -->
        </ul>
        <!-- end sidebar nav -->
    </div>
    <!-- end sidebar scrollbar -->
</div>