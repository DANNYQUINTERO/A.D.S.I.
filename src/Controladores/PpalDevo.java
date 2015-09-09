package Controladores;

import Vistas.FrmDevoluciones;


/**
 *
 * @author Sala221
 */
public class PpalDevo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new ControladorDevoluciones(new FrmDevoluciones ()).iniciar();
    }   
}
