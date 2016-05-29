package com.br.manager.controlle.commands;

import com.br.manager.model.dao.ColaboradorDAO;
import com.br.manager.model.javabeans.Colaborador;
import com.br.manager.model.javabeans.Usuario;
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
            case "registro":
                //INICIALIZE
                request.getSession().setAttribute("senhaAuth", true);
                request.getSession().setAttribute("regAuth", true);
                request.getSession().setAttribute("RegOk", false);
                
                //PARAMETERS FORM
                String loginr = request.getParameter("username");
                String senhar = request.getParameter("password");
                String senhar2 = request.getParameter("password2");
                String nomer = request.getParameter("nome");
                String emailr = request.getParameter("email");
                String telefoner = request.getParameter("phone");
                int tipor = Integer.parseInt(request.getParameter("tipo"));
                
                
                //VALIDACAO DE SENHA
                if(!senhar.endsWith(senhar2)){
                    try {
                        request.getSession().setAttribute("senhaAuth", false);
                        response.sendRedirect("Registro.jsp");
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                
                //VALIDACAO DE DUPLICIDADE USUARIO
                colaboradorDAO = new ColaboradorDAO();
                ArrayList<Colaborador> todos = colaboradorDAO.read();
                
                for (Colaborador todo : todos) {
                    if(todo.getEmail().equals(emailr) || todo.getUsername().equals(loginr)){
                        try {
                            request.getSession().setAttribute("regAuth", false);
                            response.sendRedirect("Registro.jsp");
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                }
                
                //INSERE
                NivelAcesso n = null;
                if(tipor == 2)
                    n =  Usuario.NivelAcesso.GERENTE;
                else
                    n = Usuario.NivelAcesso.DESENVOLVEDOR;
                
                Colaborador novo = new Colaborador(-1, loginr, senhar, nomer, emailr, telefoner, n);
                colaboradorDAO = new ColaboradorDAO();
                boolean ok = colaboradorDAO.insert(novo);
 
                if(ok){
                    try {
                        request.getSession().setAttribute("RegOk", true);
                        response.sendRedirect("login.jsp");
                    } catch (IOException ex) {
                        Logger.getLogger(UsuarioCommand.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    try {
                        request.getSession().setAttribute("regAuth", false);
                        response.sendRedirect("Registro.jsp");
                    } catch (IOException ex) {
                        Logger.getLogger(UsuarioCommand.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    
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
