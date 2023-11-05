package app.configdb;

import java.sql.*;
public abstract class Config {
    
    final protected String DRIVER = "com.mysql.jdbc.Driver";
    final protected String URL = "jdbc:mysql://localhost:3306/shoes_and_apparel_ims";
    final protected String USER = "root";
    final protected String PASSWORD = "";
    
    public Connection connect;
    public Statement stmt;
    public ResultSet result;
    public PreparedStatement pst;
}
