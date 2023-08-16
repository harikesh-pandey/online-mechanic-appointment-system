package amdocs_project;

import java.sql.*;

public class MySQLConnection {
    public static Connection myconn=null;
    public static Statement stat=null;
    
    public static Statement getMyConnection(String DB_Name) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver registered successfully...");
            myconn = DriverManager.getConnection("jdbc:mysql://localhost/"+DB_Name,"root","");
            System.out.println("Connection established successfully with database : "+DB_Name);
            stat = myconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println("We are ready to execute...");
            System.out.println();
        }
        catch(ClassNotFoundException e) {
        	System.out.println("Couldn't register JDBC Driver : "+e.getMessage());
        }
        catch(SQLException e) {
        	System.out.println("SQL problem : "+e.getMessage());
        }
        catch(Exception e) {
        	System.out.println("Some other error : "+e.getMessage());
        }
        return stat;
    }
}
