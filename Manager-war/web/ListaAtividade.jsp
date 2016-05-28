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
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody style="color: #000;">
                    <c:forEach var="t" items="${projeto.getTarefas()}">
                        <tr> 
                            <td></td>
                            <td>${t.getNome()}</td>
                            <td>${t.getColaborador().getNome()}</td>
                            <td>${t.getDtFinal()}</td>
                            <td>${t.getStatus()}</td>
                            <td>
                                <form action="FrontController" method="POST">
                                    <input type="hidden" name="command" value="TarefaCommand_deleta"/>
                                    <input type="hidden" name="CdTarefa" value="${t.getCdTarefa()}"/>
                                    <a href="#" class="btn btn-small btn-icon btn-circle btn-danger" onclick="$(this).closest('form').submit()">
                                        <i class="fa fa-times"></i>
                                    </a>
                                </form>
                            </td>
                            <td>
                                <form action="FrontController" method="POST">
                                    <input type="hidden" name="command" value="TarefaCommand_atual"/>
                                    <input type="hidden" name="CdTarefa" value="${t.getCdTarefa()}"/>                                    
                                    <a href="#" class="btn btn-small btn-icon btn-circle btn-success" onclick="$(this).closest('form').submit()">
                                        <i class="fa fa-expand"></i>
                                    </a>
                                </form>
                            </td>    
                        </tr>
                    </c:forEach>                    
                </tbody>
            </table>
        </div>
    </div>
</div>