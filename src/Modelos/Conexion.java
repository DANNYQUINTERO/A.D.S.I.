

package Modelos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    private String db     = "cjunibague";
    private String user   = "root";
    private String clave  = "";
    private String url    = "jdbc:mysql://localhost/"+db;
    private String driver = "com.mysql.jdbc.Driver";
    
    private Connection conexion = null;
    
    public  Conexion(){
      try{
        Class.forName(driver);
        conexion = DriverManager.getConnection(url, user, clave);
      }catch(SQLException e){
          JOptionPane.showMessageDialog(null,"Error de SQL"+e.getMessage());
      }catch(ClassNotFoundException e){
          JOptionPane.showMessageDialog(null, "Error con el driver"+e.getMessage());
      }
    }
    public Connection getConexion(){
        return this.conexion;
    }
}
