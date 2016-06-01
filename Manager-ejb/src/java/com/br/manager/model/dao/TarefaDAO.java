package com.br.manager.model.dao;

import com.br.manager.model.connectionFactory.ConnectionFactory;
import com.br.manager.model.javabeans.Colaborador;
import com.br.manager.model.javabeans.CountStatus;
import com.br.manager.model.javabeans.Tarefa;
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
    
    public boolean insert(Tarefa e, int CdUsuario, int CdProjeto) {
        boolean resultado = false;
        try {
            String sql = "SELECT PU.CD_PROJETO_USUARIO FROM PROJETO P INNER JOIN PROJETO_USUARIO PU ON PU.CD_PROJETO = P.CD_PROJETO WHERE P.CD_PROJETO = ? AND PU.CD_USUARIO = ?";
            pst = connection.prepareStatement(sql);
            pst.setInt(1, CdProjeto);
            pst.setInt(2, CdUsuario);
            rs = pst.executeQuery();
            
            int CdProjetoUsuario = 0;
            while (rs.next()) {
                CdProjetoUsuario = rs.getInt("CD_PROJETO_USUARIO");
            }
            
            sql = "INSERT INTO TAREFA(NM_TAREFA, DC_TAREFA, CD_PROJETO_USUARIO, DT_FINALIZACAO, DC_FERRAMENTA, CD_STATUS) VALUES(?,?,?,?,?,?)";
            pst = connection.prepareStatement(sql);
            pst.setString(1, e.getNome());
            pst.setString(2, e.getDescricao());
            pst.setInt(3, CdProjetoUsuario);
            pst.setDate(4,(Date)e.getDtFinal());
            pst.setString(5, e.getFerramenta());
            pst.setInt(6, 1);
            
            int r = pst.executeUpdate();
            if(r>0)
                resultado = true;
        } catch (SQLException sQLException) {
            System.out.println(sQLException.getMessage());
        }
        return resultado;       
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
            String sql = "SELECT T.* FROM TAREFA T INNER JOIN PROJETO_USUARIO PU ON PU.CD_PROJETO_USUARIO = T.CD_PROJETO_USUARIO INNER JOIN PROJETO P ON P.CD_PROJETO = PU.CD_PROJETO WHERE PU.CD_USUARIO = ? AND PU.CD_PROJETO = ? ORDER BY T.DT_FINALIZACAO";
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
    
    public ArrayList<CountStatus> readByStatus(int CdUsuario) {
        ArrayList<CountStatus> lista = new ArrayList<>();
        Tarefa.Status ts = null;
        boolean add = false;
        try{
            String sql = "SELECT ST.CD_STATUS, COUNT(T.CD_TAREFA) AS COUNT \n" +
                        "      FROM TAREFA T  \n" +
                        "     INNER JOIN PROJETO_USUARIO PU  \n" +
                        "    ON PU.CD_PROJETO_USUARIO = T.CD_PROJETO_USUARIO \n" +
                        "    INNER JOIN PROJETO P ON P.CD_PROJETO = PU.CD_PROJETO AND P.CD_COORDENADOR = ? \n" +
                        "    RIGHT JOIN STATUS_TAREFA ST \n" +
                        "    ON ST.CD_STATUS = T.CD_STATUS \n" +
                        "    GROUP BY ST.CD_STATUS ORDER BY ST.CD_STATUS";
            pst = connection.prepareStatement(sql); 
            pst.setInt(1, CdUsuario);
            
            rs = pst.executeQuery();
            while (rs.next()) {
                
                switch(rs.getInt("CD_STATUS")){
                    case 1: ts = Tarefa.Status.NAO_INICIADA; break;
                    case 2: ts = Tarefa.Status.INICIADA; break;
                    case 3: ts = Tarefa.Status.INCOMPLETA; break;
                    case 4: ts = Tarefa.Status.FINALIZANDO; break;
                    case 5: ts = Tarefa.Status.TESTE; break;
                    case 6: ts = Tarefa.Status.FINALIZADA; break;
                    case 7: ts = Tarefa.Status.CANCELADA; break;    
                    default: ts = Tarefa.Status.CANCELADA;
                }
                add = lista.add(new CountStatus(ts, rs.getInt("COUNT")));
            }
            
            sql = "SELECT ST.CD_STATUS, COUNT(T.CD_TAREFA) AS COUNT \n" +
                        "      FROM TAREFA T  \n" +
                        "     INNER JOIN PROJETO_USUARIO PU  \n" +
                        "    ON PU.CD_PROJETO_USUARIO = T.CD_PROJETO_USUARIO \n" +
                        "    AND PU.CD_USUARIO  = ?\n" +
                        "    INNER JOIN PROJETO P ON P.CD_PROJETO = PU.CD_PROJETO \n" +
                        "    RIGHT JOIN STATUS_TAREFA ST \n" +
                        "    ON ST.CD_STATUS = T.CD_STATUS \n" +
                        "    WHERE P.CD_COORDENADOR <> PU.CD_USUARIO \n" +
                        "    GROUP BY ST.CD_STATUS ORDER BY ST.CD_STATUS";
            pst = connection.prepareStatement(sql); 
            pst.setInt(1, CdUsuario);
            
            rs = pst.executeQuery();
            while (rs.next()) {
                switch(rs.getInt("CD_STATUS")){
                    case 1: ts = Tarefa.Status.NAO_INICIADA; break;
                    case 2: ts = Tarefa.Status.INICIADA; break;
                    case 3: ts = Tarefa.Status.INCOMPLETA; break;
                    case 4: ts = Tarefa.Status.FINALIZANDO; break;
                    case 5: ts = Tarefa.Status.TESTE; break;
                    case 6: ts = Tarefa.Status.FINALIZADA; break;
                    case 7: ts = Tarefa.Status.CANCELADA; break;    
                    default: ts = Tarefa.Status.CANCELADA;
                }
                add = lista.add(new CountStatus(ts, rs.getInt("COUNT")));
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

    public boolean update(Tarefa e, int CdUsuario, int CdProjeto) {
        boolean resultado = false;
        try {
            String sql = "SELECT PU.CD_PROJETO_USUARIO FROM PROJETO P INNER JOIN PROJETO_USUARIO PU ON PU.CD_PROJETO = P.CD_PROJETO WHERE P.CD_PROJETO = ? AND PU.CD_USUARIO = ?";
            pst = connection.prepareStatement(sql);
            pst.setInt(1, CdProjeto);
            pst.setInt(2, CdUsuario);
            rs = pst.executeQuery();
            
            int CdProjetoUsuario = 0;
            while (rs.next()) {
                CdProjetoUsuario = rs.getInt("CD_PROJETO_USUARIO");
            }
            
            sql = "UPDATE TAREFA SET NM_TAREFA = ?, DC_TAREFA = ?, CD_PROJETO_USUARIO = ?, DT_FINALIZACAO = ?, DC_FERRAMENTA = ?, CD_STATUS = ? WHERE CD_TAREFA = ?";
            pst = connection.prepareStatement(sql);
            pst.setString(1, e.getNome());
            pst.setString(2, e.getDescricao());
            pst.setInt(3, CdProjetoUsuario);
            pst.setDate(4,(Date)e.getDtFinal());
            pst.setString(5, e.getFerramenta());
            pst.setInt(6, e.getStatus().ordinal()+1);
            pst.setInt(7, e.getCdTarefa());
            
            int r = pst.executeUpdate();
            if(r>0)
                resultado = true;
        } catch (SQLException sQLException) {
            System.out.println(sQLException.getMessage());
        }
        return resultado;       
    }

    @Override
    public boolean delete(int pk) {
        boolean resultado = false;
        try {
            String sql = "DELETE FROM TAREFA WHERE CD_TAREFA = ?";
            pst = connection.prepareStatement(sql);
            pst.setInt(1, pk); 
            int r = pst.executeUpdate();
            if(r>0)
                resultado = true;
        } catch (SQLException sQLException) {
            System.out.println(sQLException.getMessage());
        }
        return resultado;
    }
    
}
