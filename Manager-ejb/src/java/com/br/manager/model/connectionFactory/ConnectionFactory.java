package com.br.manager.model.connectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Lucas Brasilio dos Santos
 * @since 03-07-2016
 * @version 1.0
 * 
 */
public class ConnectionFactory {
    private final String driver = "org.apache.derby.jdbc.ClientDriver";
    private final String driverMYSQL = "com.mysql.jdbc.Driver";
    
    private final String protocolo = "jdbc:derby:";
    private final String dbname = "bd";
    private final String dominio = "//localhost:1527/";
    private Connection connection;

    public ConnectionFactory() {
    }

    public Connection getConnection(String tipo) {
        if(tipo.equalsIgnoreCase("derby")) {
            try {
                Class.forName(driver).newInstance();
                connection = DriverManager.getConnection(protocolo + dominio + dbname, "bd", "bd");
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                ex.printStackTrace();
            }
        }
        else if(tipo.equalsIgnoreCase("mysql")) {
            try {
                Class.forName(driverMYSQL).newInstance();
                connection = DriverManager.getConnection("bd.ct7gonp4uaww.sa-east-1.rds.amazonaws.com:3306/mack", "mackmack", "mackmack");
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                ex.printStackTrace();
            }
        }
        return connection;
    }
    
}
