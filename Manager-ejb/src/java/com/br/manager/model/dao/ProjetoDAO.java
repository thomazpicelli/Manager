package com.br.manager.model.dao;

import com.br.manager.model.connectionFactory.ConnectionFactory;
import com.br.manager.model.javabeans.Colaborador;
import com.br.manager.model.javabeans.Projeto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author TPicelli
 */
@Stateless
@LocalBean
public class ProjetoDAO implements GenericDAO<Projeto>{
    @EJB
    private ColaboradorDAO colaboradorDAO;
    private static Connection connection;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public ProjetoDAO() {
        ConnectionFactory cf = new ConnectionFactory();
        connection = cf.getConnection("derby");
    }    

    @Override
    public boolean insert(Projeto e) {
        boolean resultado = false;
        try {
            String sql = "INSERT INTO PROJETO(NM_PROJETO, DC_PROJETO, CD_COORDENADOR, DT_INICIO, DT_PREVISAO_FINALIZACAO) VALUES(?,?,?,?,?)";
            pst = connection.prepareStatement(sql);
            pst.setString(1, e.getNome());
            pst.setString(2, e.getDescricao());
            pst.setInt(3, e.getGerente().getCdUsuario());
            pst.setDate(4,(Date)e.getDtInicio());
            pst.setDate(5, (Date)e.getDtPrevisaoFim());
            
            int r = pst.executeUpdate();
            if(r>0)
                resultado = true;
        } catch (SQLException sQLException) {
            System.out.println(sQLException.getMessage());
        }
        return resultado;
    }
    
    public boolean insertPU(int Colaborador, int Projeto) {
        boolean resultado = false;
        try {
            String sql = "INSERT INTO PROJETO_USUARIO(CD_PROJETO, CD_USUARIO) VALUES(?,?)";
            pst = connection.prepareStatement(sql);
            pst.setInt(1, Projeto);
            pst.setInt(2, Colaborador);
            
            int r = pst.executeUpdate();
            if(r>0)
                resultado = true;
        } catch (SQLException sQLException) {
            System.out.println(sQLException.getMessage());
        }
        return resultado;
    }

    @Override
    public ArrayList<Projeto> read() {
        ArrayList<Projeto> lista = new ArrayList<>();
        try{
            String sql = "SELECT * FROM PROJETO";
            pst = connection.prepareStatement(sql); 
            
            rs = pst.executeQuery();
            while (rs.next()) {
                Projeto p = new Projeto(rs.getInt("CD_PROJETO"), rs.getString("NM_PROJETO"), rs.getString("DC_PROJETO"), rs.getDate("DT_INICIO"), rs.getDate("DT_PREVISAO_FINALIZACAO"), new Colaborador(rs.getInt("CD_COORDENADOR")), null, null);
                lista.add(p);
            }
        }catch(SQLException ex){
            try {
                connection.close();
            } catch (SQLException ex1) {
                Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            System.out.println(ex.getMessage());
        }
        finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    @Override
    public ArrayList<Projeto> readByUser(int cdUsuario) {
        ArrayList<Projeto> lista = new ArrayList<>();
        try{
            String sql = "SELECT P.* FROM PROJETO P WHERE P.CD_COORDENADOR = ?";
            pst = connection.prepareStatement(sql); 
            pst.setInt(1, cdUsuario);
            
            rs = pst.executeQuery();
            while (rs.next()) {
                Projeto p = new Projeto(rs.getInt("CD_PROJETO"), rs.getString("NM_PROJETO"), rs.getString("DC_PROJETO"), rs.getDate("DT_INICIO"), rs.getDate("DT_PREVISAO_FINALIZACAO"), new Colaborador(rs.getInt("CD_COORDENADOR")), null, null);
                lista.add(p);
            }
            
            sql = "SELECT P.* FROM PROJETO P INNER JOIN PROJETO_USUARIO PU ON P.CD_PROJETO = PU.CD_PROJETO WHERE PU.CD_USUARIO = ?";
            pst = connection.prepareStatement(sql); 
            pst.setInt(1, cdUsuario);
            
            rs = pst.executeQuery();
            while (rs.next()) {
                Projeto p = new Projeto(rs.getInt("CD_PROJETO"), rs.getString("NM_PROJETO"), rs.getString("DC_PROJETO"), rs.getDate("DT_INICIO"), rs.getDate("DT_PREVISAO_FINALIZACAO"), new Colaborador(rs.getInt("CD_COORDENADOR")), null, null);
                lista.add(p);
            }
        }catch(SQLException ex){
            try {
                connection.close();
            } catch (SQLException ex1) {
                Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            System.out.println(ex.getMessage());
        }
        finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    @Override
    public Projeto readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Projeto readByString(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Projeto> readByFK(int cdFK) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(int pk, Projeto e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int pk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
