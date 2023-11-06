package app.configdb;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnect extends Config{
    public Connection ConnectDB(){
        try {
            Class.forName(DRIVER);
            connect = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
           System.out.println(e);
        }
    return null;
    }
}
