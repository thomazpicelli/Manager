package com.br.manager.controlle.commands;

import com.br.manager.model.dao.ColaboradorDAO;
import com.br.manager.model.javabeans.Colaborador;
import java.io.IOException;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsuarioCommand implements Command{
    ColaboradorDAO colaboradorDAO = lookupColaboradorDAOBean();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, String commando) {
        switch(commando){
            case "login":
                String login = request.getParameter("username");
                String senha = request.getParameter("password");
                
                colaboradorDAO = new ColaboradorDAO();
                ArrayList<Colaborador> listUsuario = colaboradorDAO.read();
                boolean auth = false;
                for (Colaborador listUsuario1 : listUsuario) {
                    if(listUsuario1.getUsername().equals(login) && listUsuario1.getPassword().equals(senha)){
                        
                        request.getSession().setAttribute("usuario", listUsuario1);
                        request.getSession().setAttribute("auth", true);

                        ProjetoCommand pCommand = new ProjetoCommand();
                        pCommand.execute(request, response, "getCurrent");
                        
                        try {
                            response.sendRedirect("dashboard.jsp");
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                        auth = true;
                    }
                }
                if(!auth){
                    request.getSession().setAttribute("usuario", null);
                    request.getSession().setAttribute("auth", false);
                    try {
                        response.sendRedirect("index.jsp");
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                break;
            case "Registro":
                String loginr = request.getParameter("username");
                String senhar = request.getParameter("password");
                String senha2 = request.getParameter("password2");
                String email = request.getParameter("email");
                String telefone = request.getParameter("phone");
                break;
            default:
        }
    }

    private ColaboradorDAO lookupColaboradorDAOBean() {
        try {
            Context c = new InitialContext();
            return (ColaboradorDAO) c.lookup("java:global/Manager/Manager-ejb/ColaboradorDAO!com.br.manager.model.dao.ColaboradorDAO");
        } catch (NamingException ne) {
            System.out.println(ne.getMessage());
            throw new RuntimeException(ne);
        }
    }
}
