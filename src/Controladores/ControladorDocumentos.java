/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;
import Modelos.DocumentoModelo;
import Vistas.FrmDocumentos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Sala221
 */
public class ControladorDocumentos implements ActionListener,MouseListener {
    FrmDocumentos vtnDocumentos;
    DocumentoModelo modelo = new DocumentoModelo();
   
    public enum AccionMVC{
        __VER_DOCUMENTOS,
        __AGREGAR_DOCUMENTOS,
        __ELIMINAR_DOCUMENTOS,
        __ADICIONAR_DOCUMENTOS
    }
    
    public ControladorDocumentos(FrmDocumentos vista){
        this.vtnDocumentos = vista;
        
    } 
    
    public void iniciar(){
       try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vtnDocumentos);
            vtnDocumentos.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {}
          catch (ClassNotFoundException ex) {
              JOptionPane.showMessageDialog(vtnDocumentos, "Error de driver de video: "+ex.getMessage());
          }
          catch (InstantiationException ex) {}
          catch (IllegalAccessException ex) {}
       
       
          this.vtnDocumentos.jBtnVerDocu.setActionCommand("__VER_DOCUMENTOS");
          this.vtnDocumentos.jBtnVerDocu.addActionListener(this);
        
        
          this.vtnDocumentos.jBtnAdicionarDocu.setActionCommand("__ADICIONAR_DOCUMENTOS");
          this.vtnDocumentos.jBtnAdicionarDocu.addActionListener(this);
        
          this.vtnDocumentos.jBtnCrearDocu.setActionCommand("__AGREGAR_DOCUMENTOS");
          this.vtnDocumentos.jBtnCrearDocu.addActionListener(this);
        
          this.vtnDocumentos.jBtnEliminarDocu.setActionCommand("__ELIMINAR_DOCUMENTOS");
          this.vtnDocumentos.jBtnEliminarDocu.addActionListener(this);
         
          this.vtnDocumentos.jtbDocumento.addMouseListener(this);
          this.vtnDocumentos.jtbDocumento.setModel(new DefaultTableModel());
        
        }
    
    
    public void mouseClicked(MouseEvent e){
        if (e. getButton() == 1)//boton izquierdo
            
        {
            int fila = this.vtnDocumentos.jtbDocumento.rowAtPoint(e.getPoint());
            if (fila > -1){
                this.vtnDocumentos.jFtNumRadi.setText(String.valueOf(
                     this.vtnDocumentos.jtbDocumento.getValueAt(fila, 0)  ));
                
                this.vtnDocumentos.jDCalendario.setDateFormatString(String.valueOf(
                     this.vtnDocumentos.jtbDocumento.getValueAt(fila, 1)  ));
                
                this.vtnDocumentos.jTAdesc.setText(String.valueOf(
                     this.vtnDocumentos.jtbDocumento.getValueAt(fila, 2)  ));
                
                this.vtnDocumentos.jFtUbicacion.setText(String.valueOf(
                     this.vtnDocumentos.jtbDocumento.getValueAt(fila, 3)  ));
                
                
                this.vtnDocumentos.jFtNumRadi.setEditable(false);
                this.vtnDocumentos.jDCalendario.setEnabled(false);
                this.vtnDocumentos.jTAdesc.setEditable(false);
                this.vtnDocumentos.jFtUbicacion.setEditable(false); 
           
            }
            
        }
    
    }
   public void mousePressed(MouseEvent e) {}
   public void mouseReleased(MouseEvent e) {}
   public void mouseEntered(MouseEvent e) {}
   public void mouseExited(MouseEvent e) {}
   
   @Override
   public void actionPerformed(ActionEvent e) {
       switch(AccionMVC.valueOf(e.getActionCommand())){
           
           case __VER_DOCUMENTOS:{
               this.vtnDocumentos.jtbDocumento.setModel(this.modelo.getTablaDocumentos());
               this.vtnDocumentos.jFtNumRadi.requestFocus();
               break;
           }
           case __ADICIONAR_DOCUMENTOS:{
               this.vtnDocumentos.jFtNumRadi.setEditable(true);
               this.vtnDocumentos.jDCalendario.setEnabled(true);
               this.vtnDocumentos.jTAdesc.setEditable(true);
               this.vtnDocumentos.jFtUbicacion.setEditable(true);
               this.vtnDocumentos.jFtNumRadi.requestFocus();
               this.vtnDocumentos.jFtNumRadi.setText("");
               this.vtnDocumentos.jDCalendario.setDateFormatString("");
               this.vtnDocumentos.jTAdesc.setText("");
               this.vtnDocumentos.jFtUbicacion.setText("");
               break;  
           }
           case __AGREGAR_DOCUMENTOS:{
            if (this.modelo.NuevoDocumento(
                  this.vtnDocumentos.jFtNumRadi.getText(),
                  this.vtnDocumentos.jDCalendario.getDateFormatString(),
                  this.vtnDocumentos.jTAdesc.getText(),
                  this.vtnDocumentos.jFtUbicacion.getText())){
                  this.vtnDocumentos.jtbDocumento.setModel(this.modelo.getTablaDocumentos());
                  JOptionPane.showMessageDialog(vtnDocumentos,"DOCUMENTO CREADO!!. ");
                  this.vtnDocumentos.jFtNumRadi.setText("");
                  this.vtnDocumentos.jDCalendario.setDateFormatString("");
                  this.vtnDocumentos.jTAdesc.setText("");
                  this.vtnDocumentos.jFtUbicacion.setText("");     
            }
            
            else 
                JOptionPane.showMessageDialog(vtnDocumentos,"DATOS INCORRECTOS!!.");
            break;
   
           }
           case __ELIMINAR_DOCUMENTOS:{
               if (this.modelo.EliminarDocumento(this.vtnDocumentos.jFtNumRadi.getText())){
                   this.vtnDocumentos.jtbDocumento.setModel(this.modelo.getTablaDocumentos());
                   JOptionPane.showMessageDialog(vtnDocumentos,"DOCUMENTO ELIMINADO!!.");
                    this.vtnDocumentos.jFtNumRadi.setText("");
                    this.vtnDocumentos.jTAdesc.setText("");
                    this.vtnDocumentos.jTAdesc.setText("");
                    this.vtnDocumentos.jFtUbicacion.setText("");
               }
               break;                       
         }
      }
   }
}
