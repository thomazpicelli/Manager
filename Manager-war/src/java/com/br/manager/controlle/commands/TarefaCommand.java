package com.br.manager.controlle.commands;

import com.br.manager.model.javabeans.Projeto;
import com.br.manager.model.javabeans.Tarefa;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TPicelli
 */
public class TarefaCommand implements Command{
    private int CdTarefa;
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
                
                //dao
                break;
            default:
        }
    }
    
}
