package polizas;

import javax.swing.SwingUtilities;
import polizas.General.Bienvenida;
import polizas.General.Controlador;

/**
 * Clase principal que arranca la aplicación bajo el patrón MVC.
 */
public class Inicio {
    private Controlador controlador;
    private Bienvenida vista;

    public Inicio() {
        vista = new Bienvenida();
        controlador = new Controlador(vista);
    }

    public static void main(String[] args) {
        // Ejecuta la interfaz gráfica de Swing en el Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(Inicio::new);
    }
}
