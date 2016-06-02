package com.br.manager.controlle.commands;

import com.br.manager.model.dao.TarefaDAO;
import com.br.manager.model.javabeans.Colaborador;
import com.br.manager.model.javabeans.Projeto;
import com.br.manager.model.javabeans.Tarefa;
import com.br.manager.model.javabeans.Usuario;
import java.io.IOException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TPicelli
 */
public class TarefaCommand implements Command{
    TarefaDAO tarefaDAO = lookupTarefaDAOBean();
    private int CdTarefa;
    private Usuario u;
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, String commando) {
        switch(commando){
            case "atual":
                CdTarefa = Integer.parseInt(request.getParameter("CdTarefa"));
                
                Projeto p = (Projeto)request.getSession().getAttribute("projeto");
                for (Tarefa t : p.getTarefas()) {
                    if(t.getCdTarefa() == CdTarefa)
                        request.getSession().setAttribute("tarefa", t);
                }
                try {
                    response.sendRedirect("atividade.jsp");
                } catch (IOException ex) {
                    Logger.getLogger(TarefaCommand.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "deleta":
                CdTarefa = Integer.parseInt(request.getParameter("CdTarefa"));
                
                tarefaDAO = new TarefaDAO();
                boolean ok = tarefaDAO.delete(CdTarefa);
                if(ok){
                    ProjetoCommand proje = new ProjetoCommand();
                    proje.execute(request, response, "getCurrent");
                    proje.execute(request, response, "refrash");
                    try {
                        response.sendRedirect("projeto.jsp");
                    } catch (IOException ex) {
                        Logger.getLogger(TarefaCommand.class.getName()).log(Level.SEVERE, null, ex);
                    }                    
                }
                //dao
                break;
            case "nova":
                u = (Usuario)request.getSession().getAttribute("usuario");
                Projeto proj = (Projeto) request.getSession().getAttribute("projeto");
                
                String nome = request.getParameter("nome");
                String descricao  = request.getParameter("descricao");
                String ferramenta  = request.getParameter("ferramenta");
                String datafim = request.getParameter("datafim");
                int CdResponsavel = Integer.parseInt(request.getParameter("responsavel"));

                Date d1 = java.sql.Date.valueOf(datafim);
                    
                Tarefa ta = new Tarefa(-1, nome, descricao, d1, ferramenta, new Colaborador(CdResponsavel), Tarefa.Status.NAO_INICIADA);
                tarefaDAO = new TarefaDAO();
                boolean ok1 = tarefaDAO.insert(ta,CdResponsavel,proj.getCdProjeto());
                if(ok1){
                    ProjetoCommand pCom = new ProjetoCommand();
                    pCom.execute(request, response, "getCurrent");
                    pCom.execute(request, response, "refrash");
                    try {
                        response.sendRedirect("projeto.jsp");
                    } catch (IOException ex) {
                        Logger.getLogger(TarefaCommand.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    try {
                        response.sendRedirect("CadastroAtividade.jsp");
                    } catch (IOException ex) {
                        Logger.getLogger(TarefaCommand.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case "altera":
                u = (Usuario)request.getSession().getAttribute("usuario");
                Projeto proje = (Projeto) request.getSession().getAttribute("projeto");
                Tarefa tarefa = (Tarefa) request.getSession().getAttribute("tarefa");
                
                String nomeU = request.getParameter("nome");
                String descricaoU  = request.getParameter("descricao");
                String ferramentaU  = request.getParameter("ferramenta");
                String datafimU = request.getParameter("datafim");
                String StatusU = request.getParameter("status");
                int CdResponsavelU = 0;
                if(request.getParameter("responsavel") != null)
                    CdResponsavelU = Integer.parseInt(request.getParameter("responsavel"));
                else
                    CdResponsavelU = tarefa.getColaborador().getCdUsuario();

                Tarefa.Status s = Tarefa.Status.NAO_INICIADA;
                switch(StatusU){
                    case "NAO_INICIADA": s = Tarefa.Status.NAO_INICIADA; break;
                    case "INICIADA": s = Tarefa.Status.INICIADA; break;
                    case "INCOMPLETA": s = Tarefa.Status.INCOMPLETA; break;
                    case "FINALIZANDO": s = Tarefa.Status.FINALIZANDO; break;
                    case "TESTE": s = Tarefa.Status.TESTE; break;
                    case "FINALIZADA": s = Tarefa.Status.FINALIZADA; break;
                    case "CANCELADA": s = Tarefa.Status.CANCELADA; break;                
                }
                Tarefa tarrr = (Tarefa) request.getSession().getAttribute("tarefa");
                
                
                Date d = java.sql.Date.valueOf(datafimU);
                    
                Tarefa tar = new Tarefa(tarrr.getCdTarefa(), nomeU, descricaoU, d, ferramentaU, new Colaborador(CdResponsavelU), s);
                tarefaDAO = new TarefaDAO();
                boolean ok2 = tarefaDAO.update(tar, CdResponsavelU, proje.getCdProjeto());
                if(ok2){
                    ProjetoCommand pCom = new ProjetoCommand();
                    pCom.execute(request, response, "getCurrent");
                    pCom.execute(request, response, "refrash");
                    try {
                        response.sendRedirect("projeto.jsp");
                    } catch (IOException ex) {
                        Logger.getLogger(TarefaCommand.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    try {
                        response.sendRedirect("EditarAtividade.jsp");
                    } catch (IOException ex) {
                        Logger.getLogger(TarefaCommand.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            default:
        }
    }

    private TarefaDAO lookupTarefaDAOBean() {
        try {
            Context c = new InitialContext();
            return (TarefaDAO) c.lookup("java:global/Manager/Manager-ejb/TarefaDAO!com.br.manager.model.dao.TarefaDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
