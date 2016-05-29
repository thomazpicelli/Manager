package com.br.manager.model.dao;

import com.br.manager.model.connectionFactory.ConnectionFactory;
import com.br.manager.model.javabeans.Colaborador;
import com.br.manager.model.javabeans.Tarefa;
import java.sql.Connection;
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
public class TarefaDAO implements GenericDAO<Tarefa>{
    private static Connection connection;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public TarefaDAO() {
        ConnectionFactory cf = new ConnectionFactory();
        connection = cf.getConnection("derby");
    }

    @Override
    public boolean insert(Tarefa e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Tarefa> read() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Tarefa> readByUser(int cdUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tarefa readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tarefa readByString(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Tarefa> readByFK(int cdFK) {
        return null;
    }
    
    public ArrayList<Tarefa> readByFK(Colaborador c, int cdProjeto) {
        ArrayList<Tarefa> lista = new ArrayList<>();
        try{
            String sql = "SELECT T.* FROM TAREFA T INNER JOIN PROJETO_USUARIO PU ON PU.CD_PROJETO_USUARIO = T.CD_PROJETO_USUARIO INNER JOIN PROJETO P ON P.CD_PROJETO = PU.CD_PROJETO WHERE PU.CD_USUARIO = ? AND PU.CD_PROJETO = ?";
            pst = connection.prepareStatement(sql); 
            pst.setInt(1, c.getCdUsuario());
            pst.setInt(2, cdProjeto);
            
            rs = pst.executeQuery();
            while (rs.next()) {
                Tarefa t = new Tarefa(rs.getInt("CD_TAREFA"), rs.getString("NM_TAREFA"), rs.getString("DC_TAREFA"), rs.getDate("DT_FINALIZACAO"), rs.getString("DC_FERRAMENTA"), c, null);
                switch(rs.getInt("CD_STATUS")){
                    case 1: t.setStatus(Tarefa.Status.NAO_INICIADA); break;
                    case 2: t.setStatus(Tarefa.Status.INICIADA); break;
                    case 3: t.setStatus(Tarefa.Status.INCOMPLETA); break;
                    case 4: t.setStatus(Tarefa.Status.FINALIZANDO); break;
                    case 5: t.setStatus(Tarefa.Status.TESTE); break;
                    case 6: t.setStatus(Tarefa.Status.FINALIZADA); break;
                    case 7: t.setStatus(Tarefa.Status.CANCELADA); break;    
                    default: t.setStatus(Tarefa.Status.CANCELADA);
                }
                lista.add(t);
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
    public boolean update(int pk, Tarefa e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int pk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
