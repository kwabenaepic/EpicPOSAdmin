package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectMySql {
    
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String CONN_STRING ="jdbc:mysql://localhost/questionsandanswers";
    
    private Connection conn = null;
    private Statement stmt = null;
    public static ResultSet rs = null;
    
    // mysql connection 
    public void ConnectMySql(String select){
        
        try{
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            stmt = conn.createStatement();
            
            String sql = select;
            rs = stmt.executeQuery(sql);
            
           // System.out.println("Connected!");                     
            
        }
        catch(SQLException e)
        {
            System.out.println(e);
        
        }
    }
    
}
