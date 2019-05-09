/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author slinger
 */
public class DatabaseConnector {

    private Connection conn;

    public  Connection getConnection() {

        try {
            String url = "jdbc:sqlite:/home/slinger/Albums.sqlite";
            conn = DriverManager.getConnection(url);
            System.out.println("connected");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Download found at: https://uploadfiles.io/wxvdndmw");

        }

        return null;

    }
    
    public void closeConnection() {
        try {
            conn.close();
            System.out.println("connection closed: " + conn.isClosed());
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
}
