/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.app.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class DBConnection {
    
    private final String jdbcUrl="jdbc:mysql://localhost:3306/employee_demo";
    private final String user="root";
    private final String pass="";
    private Connection sqlConnection;
    
    private static DBConnection connection=null;
    
    private DBConnection(){}
    
    public void connect(){
        try{
            sqlConnection=DriverManager.getConnection(jdbcUrl, user, pass);
        }catch(SQLException ex){
            System.err.println(ex);
        }
    }
    
    public Connection getSqlConnection(){
        return sqlConnection;
    }
    
    
    public static DBConnection getInstance(){
        if(connection==null){
            connection=new DBConnection();
        }
        return connection;
    }
    
    
    
}
