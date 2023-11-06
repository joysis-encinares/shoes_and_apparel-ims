package app.configdb;

import java.sql.*;
import java.text.SimpleDateFormat;
public abstract class Config {
    
    final protected String DRIVER = "com.mysql.jdbc.Driver";
    final protected String URL = "jdbc:mysql://localhost:3306/shoes_and_apparel_ims";
    final protected String USER = "root";
    final protected String PASSWORD = "";
    
    public Connection connect;
    public Statement stmt;
    public ResultSet result;
    public PreparedStatement pst;
    
    public final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    public final SimpleDateFormat tf = new SimpleDateFormat("HH:mm:ss");
    public final SimpleDateFormat dtformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    
}
