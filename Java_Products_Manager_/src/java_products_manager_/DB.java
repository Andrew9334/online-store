/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_products_manager_;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI
 */


public class DB {
    
    public static String servername = "localhost";
    public static String username = "root";
    public static String dbname = "product_db";
    public static Integer portnumber = 3306;
    public static String password = ""; // no password
    
    
    // create a function to create and get the connection
    public static Connection getConnection()
    {
       MysqlDataSource datasource = new MysqlDataSource(); 
       
       datasource.setServerName(servername);
       datasource.setUser(username);
       datasource.setDatabaseName(dbname);
       datasource.setPortNumber(portnumber);
       datasource.setPassword(password);
       
        try {
            return datasource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
       
       
    }
    
}
