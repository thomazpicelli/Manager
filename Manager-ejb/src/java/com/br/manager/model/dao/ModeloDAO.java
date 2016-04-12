package com.br.manager.model.dao;

import com.br.manager.model.connectionFactory.ConnectionFactory;
import com.br.manager.model.javabeans.ClasseModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Lucas Brasilio dos Santos
 */
public class ModeloDAO implements GenericDAO<ClasseModelo> {
    private static Connection connection;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public ModeloDAO() {
        ConnectionFactory cf = new ConnectionFactory();
        connection = cf.getConnection("derby");
    }

    @Override
    public boolean insert(ClasseModelo e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ClasseModelo> read() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(int pk, ClasseModelo e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int pk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
