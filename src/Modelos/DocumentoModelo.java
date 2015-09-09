

package Modelos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class DocumentoModelo extends Conexion{
    
    public DefaultTableModel getTablaDocumentos(){
        DefaultTableModel tablamodelo = new DefaultTableModel();
        int numregistros = 0;
        String[] NombreColumnas = {"NumeRadi","FechRadi","DescDocu","UbicDocu"};
        
        try{
            String sql = "SELECT count(*) as total FROM documentos;";
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
            String sql = "Select * from documentos;";
            PreparedStatement sentencia = this.getConexion().prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            int i = 0;
            while(resultado.next()){
                datos[i][0] = resultado.getString("NumeRadi");
                datos[i][1] = resultado.getString("FechRadi");
                datos[i][2] = resultado.getString("DescDocu");
                datos[i][3] = resultado.getString("UbicDocu");
                i++;
            }
            resultado.close();
            
            tablamodelo.setDataVector(datos, NombreColumnas);
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            return tablamodelo;
    }
    
    public boolean NuevoDocumento(String NumeRadi, String fechRadi, String DescDocu, String UbicDocu){
        boolean bandera = false;
        if(Valida_datos(NumeRadi, fechRadi, DescDocu, UbicDocu)){
            
            
            String sql = "Insert into Documentos values('"+NumeRadi+"','"+fechRadi+"',"+DescDocu+","+UbicDocu+");";
            
            try {
                PreparedStatement sentencia = this.getConexion().prepareStatement(sql);
                sentencia.execute();
                sentencia.close();
                bandera = true;
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "El Documento ya existe!");
            }          
        }
        return bandera;        
    }
    
    public boolean EliminarDocumento(String id){
        boolean bandera = false;
        
        String sql = "DELETE FROM documentos WHERE NumeRadi= '"+id+"';";
        
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
    
    private boolean Valida_datos(String NumeRadi,  String FechRadi, String DescDocu, String UbicDocu){
       
        return FechRadi.length() > 0 &&  DescDocu.length() > 0 && UbicDocu.length()> 0 &&  NumeRadi.length() > 0; 
    }

  
}
