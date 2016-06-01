package com.br.manager.controlle.commands;

import com.br.manager.model.dao.*;
import com.br.manager.model.javabeans.*;
import com.br.manager.model.javabeans.Tarefa.Status;
import com.br.manager.model.javabeans.Usuario.NivelAcesso;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class ProjetoCommand implements Command{
    TarefaDAO tarefaDAO = lookupTarefaDAOBean();
    ColaboradorDAO colaboradorDAO = lookupColaboradorDAOBean();
    ProjetoDAO projetoDAO = lookupProjetoDAOBean();
    
    private int CdProjeto;
    private Usuario u;
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, String commando) {
        switch(commando){
            case "getCurrent":
                request.getSession().setAttribute("projetos", null);
                //Com base no usuario, carrega projetos(usuarios e atividades)
                u = (Usuario)request.getSession().getAttribute("usuario");
                
                projetoDAO = new ProjetoDAO();
                ArrayList<Projeto> projetos = null;
                if(u.getNivelAcesso() == NivelAcesso.ADMINISTRADOR)
                    projetos = projetoDAO.read();
                else
                    projetos = projetoDAO.readByUser(u.getCdUsuario());
                
                //Carrega GERENTE
                for (Projeto projeto : projetos) {
                    colaboradorDAO = new ColaboradorDAO();
                    projeto.setGerente(colaboradorDAO.readById(projeto.getGerente().getCdUsuario()));
                }
                
                //Carrega COOLABORADORES
                for (Projeto projeto : projetos) {
                    colaboradorDAO = new ColaboradorDAO();
                    projeto.setUsuarios(colaboradorDAO.readByFK(projeto.getCdProjeto()));
                }
                
                colaboradorDAO = new ColaboradorDAO();
                ArrayList<Colaborador> colaborador = colaboradorDAO.readByGerente(u.getCdUsuario());
                
                //Carrega TAREFAS
                for (Projeto projeto : projetos) {
                    ArrayList<Tarefa> tarefa = new ArrayList();
                    for (Colaborador usuario : projeto.getUsuarios()) {
                        tarefaDAO = new TarefaDAO();
                        tarefa.addAll(tarefaDAO.readByFK(usuario, projeto.getCdProjeto()));
                    }
                    projeto.setTarefas(tarefa);
                }
                
                // CARREGA STATUS
                ArrayList<Status> statuss = new ArrayList();
                for (Status s : Status.values()) {
                    statuss.add(s);
                }
                
                //Carrega COUNT STATUS
                tarefaDAO = new TarefaDAO();
                ArrayList<CountStatus> cs = tarefaDAO.readByStatus(u.getCdUsuario());
                
                request.getSession().setAttribute("projetos", projetos);
                request.getSession().setAttribute("colaborador", colaborador);
                request.getSession().setAttribute("countStatus", cs);
                request.getSession().setAttribute("status", statuss);
                break;
            case "refrash":
                Projeto projetooo = (Projeto)request.getSession().getAttribute("projeto");
                CdProjeto = projetooo.getCdProjeto();
                
                ArrayList<Projeto> listaR = (ArrayList<Projeto>)request.getSession().getAttribute("projetos");
                for (Projeto p : listaR) {
                    if(p.getCdProjeto() == CdProjeto)
                        request.getSession().setAttribute("projeto", p);
                }
                break;
            case "atual":
                CdProjeto = Integer.parseInt(request.getParameter("cdProjeto"));
                ArrayList<Projeto> lista = (ArrayList<Projeto>)request.getSession().getAttribute("projetos");
                for (Projeto p : lista) {
                    if(p.getCdProjeto() == CdProjeto)
                        request.getSession().setAttribute("projeto", p);
                }
                try {
                    response.sendRedirect("projeto.jsp");
                } catch (IOException ex) {
                    Logger.getLogger(ProjetoCommand.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "criar":
                u = (Usuario)request.getSession().getAttribute("usuario");
                ArrayList<Colaborador> colaboradores = (ArrayList<Colaborador>) request.getSession().getAttribute("colaborador");
                
                String nome = request.getParameter("nome");
                String descricao  = request.getParameter("descricao");
                String[] c = request.getParameterValues("colaborador");

                Date d1 = null, d2 = null;
                d1 = java.sql.Date.valueOf(request.getParameter("datainicio"));
                d2 = java.sql.Date.valueOf(request.getParameter("datafinal"));
                    
                ArrayList<Colaborador> col = new ArrayList<>();
                for (String c1 : c) {
                    col.add(new Colaborador(Integer.parseInt(c1)));
                }
                
                Projeto p = new Projeto(-1, nome, descricao, d1, d2, (Colaborador)u, col, null);
                
                projetoDAO = new ProjetoDAO();
                boolean ok = projetoDAO.insert(p);
                if(ok){
                    for (Colaborador col1 : col) {
                        projetoDAO = new ProjetoDAO();
                        projetoDAO.insertPU(col1.getCdUsuario());
                    }
                    projetoDAO = new ProjetoDAO();
                    p = projetoDAO.readByString(p.getNome());
                    ProjetoCommand pCom = new ProjetoCommand();
                    request.getSession().setAttribute("projeto", p);
                    pCom.execute(request, response, "getCurrent");
                    pCom.execute(request, response, "refrash");
                    try {
                        response.sendRedirect("projeto.jsp");
                    } catch (IOException ex) {
                        Logger.getLogger(ProjetoCommand.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    try {
                        response.sendRedirect("CadastroProjeto.jsp");
                    } catch (IOException ex) {
                        Logger.getLogger(ProjetoCommand.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case "deleta":
            Projeto projetu = (Projeto)request.getSession().getAttribute("projeto");
            //ProjetoDAO
                break;
            default:
            try {
                response.sendRedirect("index.jsp");
            } catch (IOException ex) {
                Logger.getLogger(ProjetoCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private ProjetoDAO lookupProjetoDAOBean() {
        try {
            Context c = new InitialContext();
            return (ProjetoDAO) c.lookup("java:global/Manager/Manager-ejb/ProjetoDAO!com.br.manager.model.dao.ProjetoDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private ColaboradorDAO lookupColaboradorDAOBean() {
        try {
            Context c = new InitialContext();
            return (ColaboradorDAO) c.lookup("java:global/Manager/Manager-ejb/ColaboradorDAO!com.br.manager.model.dao.ColaboradorDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
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
