

package Modelos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class DevolucionesModelo extends Conexion{
    
    public DefaultTableModel getTablaDepartamentos(){
        DefaultTableModel tablamodelo = new DefaultTableModel();
        int numregistros = 0;
        String[] NombreColumnas = {"FechDevo","Firma", "Observaciones"};
        
        try{
            String sql = "SELECT count(*) as total FROM devoluciones;";
            PreparedStatement sentencia = this.getConexion().prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            resultado.next();
            numregistros = resultado.getInt("Total");
            resultado.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        Object[][] datos = new String[numregistros][4];
        try{
            String sql = "Select * from devoluciones;";
            PreparedStatement sentencia = this.getConexion().prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            int i = 0;
            while(resultado.next()){
                datos[i][0] = resultado.getString("FechDevo");
                datos[i][1] = resultado.getString("Firma");
                datos[i][2] = resultado.getString("Observaciones");
                i++;
            }
            resultado.close();
            
            tablamodelo.setDataVector(datos, NombreColumnas);
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            return tablamodelo;
    }
    
    public boolean NuevaDevolucion(String FechDevo, String Firma, String Observaciones){
        boolean bandera = false;
        if(Valida_datos(FechDevo, Firma, Observaciones)){
            
            String sql = "Insert into devoluciones values('"+FechDevo+"','"+Firma+"' , '"+Observaciones+"');";
            
            try {
                PreparedStatement sentencia = this.getConexion().prepareStatement(sql);
                sentencia.execute();
                sentencia.close();
                bandera = true;
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "la Devolucion ya existe!");
            }          
        }
        return bandera;        
    }
    
    public boolean EliminarDevolucion(String id){
        boolean bandera = false;
        
        String sql = "DELETE FROM devoluciones WHERE FechDevo= '"+id+"';";
        
        try{
            PreparedStatement sentencia = this.getConexion().prepareStatement(sql);
            sentencia.execute();
            sentencia.close();
            bandera = true;
        }catch( SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return bandera;
    }
    
    private boolean Valida_datos(String FechDevo,  String Firma, String Observaciones){
         if (FechDevo.length() > 0 &&  Firma.length() > 0 &&  Observaciones.length() > 0 ){
            return true;
            
        }else
            return false; 
    }

    public TableModel getTablaDevoluciones() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean NuevoDevolucion(Date date, String text, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    

   
}
