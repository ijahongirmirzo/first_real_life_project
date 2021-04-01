package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnector {
    public static Connection Connector(){
        String string_connector="jdbc:sqlite:PatientData.sqlite";
        Connection con=null;
        try{
            con=DriverManager.getConnection(string_connector);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return con;
    }
}
