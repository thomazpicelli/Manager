<!-- begin panel -->
<div class="panel panel-inverse" data-sortable-id="table-basic-7">
    <div class="panel-heading">
        <div class="panel-heading-btn">
            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
        </div>
        <a href="CadastroAtividade.jsp"><span class="label label-success m-r-10 pull-left">+</span></a>
        <h4 class="panel-title">Tarefas</h4>

    </div>
    <div class="panel-body">
        <div class="table-responsive">
            <table class="table">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Atividade</th>
                        <th>Responsável</th>
                        <th>Data Finalização</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="apa" items="${atividadeProjetoAtual}">
                        <tr>    
                            <td>${ap.getNomeAtividade()}</td>
                            <td>${ap.getUsuario().getNomeUsuario()}</td>
                            <td>${ap.getPrazo()}</td>
                            <td>${ap.getStatus()}</td>
                        </tr>
                    </c:forEach>                    
                </tbody>
            </table>
        </div>
    </div>
</div>