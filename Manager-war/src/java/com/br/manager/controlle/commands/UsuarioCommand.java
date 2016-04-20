package com.br.manager.controlle.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsuarioCommand implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, String commando) {
        switch(commando){
            case "Login":
                String login = request.getParameter("username");
                String senha = request.getParameter("password");
                /*boolean acesso = DAO.readByLogin(login,senha);
                if(acesso){
                    Usuario u = DAO.readByString("");
                    request.getSession().setAttribute("usuario", u);
                    response.sendRedirect("home.jsp"};
                }
                else{
                    response.sendRedirect("login.jsp");
                }*/
                break;
            case "Registro":
                break;
            default:
        }
    }
}
