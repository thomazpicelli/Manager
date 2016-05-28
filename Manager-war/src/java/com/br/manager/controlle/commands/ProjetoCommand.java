package com.br.manager.controlle.commands;

import com.br.manager.model.dao.*;
import com.br.manager.model.javabeans.*;
import com.br.manager.model.javabeans.Usuario.NivelAcesso;
import java.io.IOException;
import java.util.ArrayList;
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

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, String commando) {
        switch(commando){
            case "getCurrent":
                //Com base no usuario, carrega projetos(usuarios e atividades)
                Usuario u = (Usuario)request.getSession().getAttribute("usuario");
                
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
                
                //Carrega TAREFAS
                for (Projeto projeto : projetos) {
                    for (Colaborador usuario : projeto.getUsuarios()) {
                        tarefaDAO = new TarefaDAO();
                        projeto.setTarefas(tarefaDAO.readByFK(usuario, projeto.getCdProjeto()));
                    }
                }
                
                request.getSession().setAttribute("projetos", projetos);
                request.getSession().setAttribute("projeto", null);
                request.getSession().setAttribute("atividade", null);
                for (Projeto projeto : projetos) {
                    System.out.println(projeto.toString());
                }
                break;
            case "atual":
                int CdProjeto = Integer.parseInt(request.getParameter("cdProjeto"));
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
