package com.br.manager.model.dao;

import com.br.manager.model.connectionFactory.ConnectionFactory;
import com.br.manager.model.javabeans.Colaborador;
import com.br.manager.model.javabeans.Usuario.NivelAcesso;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author TPicelli
 */
@Stateless
@LocalBean
public class ColaboradorDAO implements GenericDAO<Colaborador>{
    private static Connection connection;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public ColaboradorDAO() {
        ConnectionFactory cf = new ConnectionFactory();
        connection = cf.getConnection("derby");
    }

    @Override
    public boolean insert(Colaborador e) {
        boolean resultado = false;
        try {
            String sql = "INSERT INTO USUARIO(NM_LOGIN, NM_SENHA, NM_USUARIO, NM_EMAIL, NR_TELEFONE, CD_NIVEL_ACESSO) VALUES(?,?,?,?,?,?)";
            pst = connection.prepareStatement(sql);
            pst.setString(1, e.getUsername());
            pst.setString(2, e.getPassword());
            pst.setString(3, e.getNome());
            pst.setString(4,e.getEmail());
            pst.setString(5, e.getTelefone());
            pst.setInt(6, e.getNivelAcesso().ordinal()+1);
            
            int r = pst.executeUpdate();
            if(r>0)
                resultado = true;
        } catch (SQLException sQLException) {
            System.out.println(sQLException.getMessage());
        }
        return resultado;
    }

    @Override
    public ArrayList<Colaborador> read() {
        ArrayList<Colaborador> lista = new ArrayList<>();
        try{
            String sql = "SELECT * FROM Usuario";
            pst = connection.prepareStatement(sql); 
            rs = pst.executeQuery();
            while (rs.next()) {
                Colaborador c = new Colaborador(rs.getInt("CD_USUARIO"), rs.getString("NM_LOGIN"), rs.getString("NM_SENHA"), rs.getString("NM_USUARIO"), rs.getString("NM_EMAIL"), rs.getString("NR_TELEFONE"),null);
                switch(rs.getInt("CD_NIVEL_ACESSO")){
                    case 1: c.setNivelAcesso(NivelAcesso.ADMINISTRADOR); break;
                    case 2: c.setNivelAcesso(NivelAcesso.GERENTE); break;
                    case 3: c.setNivelAcesso(NivelAcesso.DESENVOLVEDOR); break;
                }
                lista.add(c);
            }
        }catch(SQLException ex){
            try {
                connection.close();
            } catch (SQLException ex1) {
                Logger.getLogger(ColaboradorDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            System.out.println(ex.getMessage());
        }
        finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ColaboradorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    @Override
    public ArrayList<Colaborador> readByUser(int cdUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Colaborador readById(int id) {
        Colaborador c = null;
        try{
            String sql = "SELECT * FROM USUARIO WHERE CD_USUARIO = ?";
            pst = connection.prepareStatement(sql); 
            pst.setInt(1,id);
            
            rs = pst.executeQuery();
            while (rs.next()) {
                c = new Colaborador(rs.getInt("CD_USUARIO"), rs.getString("NM_LOGIN"), rs.getString("NM_SENHA"), rs.getString("NM_USUARIO"), rs.getString("NM_EMAIL"), rs.getString("NR_TELEFONE"),null);
                switch(rs.getInt("CD_NIVEL_ACESSO")){
                    case 1: c.setNivelAcesso(NivelAcesso.ADMINISTRADOR); break;
                    case 2: c.setNivelAcesso(NivelAcesso.GERENTE); break;
                    case 3: c.setNivelAcesso(NivelAcesso.DESENVOLVEDOR); break;
                }
            }
        }catch(SQLException ex){
            try {
                connection.close();
            } catch (SQLException ex1) {
                Logger.getLogger(ColaboradorDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            System.out.println(ex.getMessage());
        }
        finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ColaboradorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return c;
    }

    @Override
    public Colaborador readByString(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Colaborador> readByFK(int cdFK) {
        ArrayList<Colaborador> lista = new ArrayList<>();
        try{
            String sql = "SELECT U.* FROM USUARIO U INNER JOIN PROJETO_USUARIO PU ON PU.CD_USUARIO = U.CD_USUARIO WHERE PU.CD_PROJETO = ?";
            pst = connection.prepareStatement(sql); 
            pst.setInt(1, cdFK);
            rs = pst.executeQuery();
            while (rs.next()) {
                Colaborador c = new Colaborador(rs.getInt("CD_USUARIO"), rs.getString("NM_LOGIN"), rs.getString("NM_SENHA"), rs.getString("NM_USUARIO"), rs.getString("NM_EMAIL"), rs.getString("NR_TELEFONE"),null);
                switch(rs.getInt("CD_NIVEL_ACESSO")){
                    case 1: c.setNivelAcesso(NivelAcesso.ADMINISTRADOR); break;
                    case 2: c.setNivelAcesso(NivelAcesso.GERENTE); break;
                    case 3: c.setNivelAcesso(NivelAcesso.DESENVOLVEDOR); break;
                }
                lista.add(c);
            }
        }catch(SQLException ex){
            try {
                connection.close();
            } catch (SQLException ex1) {
                Logger.getLogger(ColaboradorDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            System.out.println(ex.getMessage());
        }
        finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ColaboradorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
    
    public ArrayList<Colaborador> readByGerente(int cdGerente) {
        ArrayList<Colaborador> lista = new ArrayList<>();
        try{
            String sql = "SELECT DISTINCT U.* FROM USUARIO U INNER JOIN PROJETO_USUARIO PU ON PU.CD_USUARIO = U.CD_USUARIO INNER JOIN PROJETO P ON P.CD_PROJETO = PU.CD_PROJETO WHERE P.CD_COORDENADOR = ?";
            pst = connection.prepareStatement(sql); 
            pst.setInt(1, cdGerente);
            rs = pst.executeQuery();
            while (rs.next()) {
                Colaborador c = new Colaborador(rs.getInt("CD_USUARIO"), rs.getString("NM_LOGIN"), rs.getString("NM_SENHA"), rs.getString("NM_USUARIO"), rs.getString("NM_EMAIL"), rs.getString("NR_TELEFONE"),null);
                switch(rs.getInt("CD_NIVEL_ACESSO")){
                    case 1: c.setNivelAcesso(NivelAcesso.ADMINISTRADOR); break;
                    case 2: c.setNivelAcesso(NivelAcesso.GERENTE); break;
                    case 3: c.setNivelAcesso(NivelAcesso.DESENVOLVEDOR); break;
                }
                lista.add(c);
            }
        }catch(SQLException ex){
            try {
                connection.close();
            } catch (SQLException ex1) {
                Logger.getLogger(ColaboradorDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            System.out.println(ex.getMessage());
        }
        finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ColaboradorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }    

    @Override
    public boolean update(int pk, Colaborador e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int pk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
