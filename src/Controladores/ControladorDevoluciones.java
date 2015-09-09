/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;
import Modelos.DevolucionesModelo;
import Vistas.FrmDevoluciones;
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
public class ControladorDevoluciones implements ActionListener,MouseListener {
    FrmDevoluciones vtnDepartamentos;
    DevolucionesModelo modelo = new DevolucionesModelo();
    private final FrmDevoluciones vtnDevoluciones;
   
    public enum AccionMVC{
        __VER_DEVOLUCIONES,
        __AGREGAR_DEVOLUCIONES,
        __ELIMINAR_DEVOLUCIONES,
        __ADICIONAR_DEVOLUCIONES
    }
    
    public ControladorDevoluciones(FrmDevoluciones vistas){
        this.vtnDevoluciones = vistas;
        
    } 
    
    public void iniciar(){
//        try {
//            UIManager.setLookAndFeel("com.sum.java.swing.plaf.windows." + "WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vtnDevoluciones);
            vtnDevoluciones.setVisible(true);
//        } catch (UnsupportedLookAndFeelException ex) {}
//          catch (ClassNotFoundException ex) {}
//          catch (InstantiationException ex) {}
//          catch (IllegalAccessException ex) {} 
        
          this.vtnDevoluciones.jBtnVerDevo.setActionCommand("__VER_DEVOLUCIONES");
          this.vtnDevoluciones.jBtnVerDevo.addActionListener(this);
        
        
          this.vtnDevoluciones.jBtnAdicionarDevo.setActionCommand("__ADICIONAR_DEVOLUCIONES");
          this.vtnDevoluciones.jBtnAdicionarDevo.addActionListener(this);
        
          this.vtnDevoluciones.jBtnCrearDevo.setActionCommand("__AGREGAR_DEVOLUCIONES");
          this.vtnDevoluciones.jBtnCrearDevo.addActionListener(this);
        
          this.vtnDevoluciones.jBtnEliminarDevo.setActionCommand("__ELIMINAR_DEVOLUCIONES");
          this.vtnDevoluciones.jBtnEliminarDevo.addActionListener(this);
         
          this.vtnDevoluciones.jtblDevoluciones.addMouseListener(this);
          this.vtnDevoluciones.jtblDevoluciones.setModel(new DefaultTableModel());
        
        }
    
    
    public void mouseClicked(MouseEvent e){
        if (e. getButton() == 1)//boton izquierdo
            
        {
            int fila = this.vtnDevoluciones.jtblDevoluciones.rowAtPoint(e.getPoint());
            if (fila > -1){
                this.vtnDevoluciones.jDCfecha.setDateFormatString(String.valueOf(
                     this.vtnDevoluciones.jtblDevoluciones.getValueAt(fila, 0)  ));
                
                this.vtnDevoluciones.jFtFirma.setText(String.valueOf(
                     this.vtnDevoluciones.jtblDevoluciones.getValueAt(fila, 1)  ));
                
                this.vtnDevoluciones.jTAobser.setText(String.valueOf(
                     this.vtnDevoluciones.jtblDevoluciones.getValueAt(fila, 1)  ));
                
              
                this.vtnDevoluciones.jDCfecha.setEnabled(false);
                this.vtnDevoluciones.jFtFirma.setEditable(false);
                this.vtnDevoluciones.jTAobser.setEditable(false);
                
           
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
           
           case __VER_DEVOLUCIONES:{
               this.vtnDevoluciones.jtblDevoluciones.setModel(this.modelo.getTablaDepartamentos());
               this.vtnDevoluciones.jDCfecha.requestFocus();
               break;
           }
           case __ADICIONAR_DEVOLUCIONES:{
               this.vtnDevoluciones.jDCfecha.setEnabled(true);
               this.vtnDevoluciones.jFtFirma.setEditable(true);
               this.vtnDevoluciones.jTAobser.setEditable(true);
             
               this.vtnDevoluciones.jDCfecha.requestFocus();
               this.vtnDevoluciones.jDCfecha.setDateFormatString(null);
               this.vtnDevoluciones.jFtFirma.setText("");
               this.vtnDevoluciones.jTAobser.setText("");
              
               break;  
           }
           case __AGREGAR_DEVOLUCIONES:{
            if (this.modelo.NuevoDevolucion(
                  this.vtnDevoluciones.jDCfecha.getDate(),
                  this.vtnDevoluciones.jFtFirma.getText(),
                  this.vtnDevoluciones.jTAobser.getText())){
                  this.vtnDevoluciones.jtblDevoluciones.setModel(this.modelo.getTablaDevoluciones());
                  JOptionPane.showMessageDialog(vtnDepartamentos,"DEVOLUCION CREADO!!. ");
                  this.vtnDevoluciones.jDCfecha.setDateFormatString(null);
                  this.vtnDevoluciones.jFtFirma.setText("");
                  this.vtnDevoluciones.jTAobser.setText("");
                   
            }
            else 
                JOptionPane.showMessageDialog(vtnDepartamentos,"DATOS INCORRECTOS!!.");
            break;
   
           }
           case __ELIMINAR_DEVOLUCIONES:{
               if (this.modelo.EliminarDevolucion(this.vtnDevoluciones.jDCfecha.getDateFormatString())){
                   this.vtnDevoluciones.jtblDevoluciones.setModel(this.modelo.getTablaDevoluciones());
                   JOptionPane.showMessageDialog(vtnDevoluciones,"DEPARTAMENTO ELIMINADO!!.");
                    this.vtnDevoluciones.jDCfecha.setDateFormatString(null);
                    this.vtnDevoluciones.jFtFirma.setText("");
                    this.vtnDevoluciones.jTAobser.setText("");
                   
               }
               break;                       
           }
       }
   }
   
    
}
