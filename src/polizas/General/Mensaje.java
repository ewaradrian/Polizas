package polizas.General;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Panel inicial de bienvenida mostrado en el CardLayout principal.
 */
public class Mensaje extends JPanel {
    public Mensaje() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 250));

        JPanel centro = new JPanel(new GridBagLayout());
        centro.setBackground(new Color(245, 247, 250));

        JLabel lbl = new JLabel(
                "<html><div style='text-align:center;'>"
                + "<h1 style='color:#146E37;'>Sistema de Gestión de Pólizas</h1>"
                + "<p style='font-size:13px; color:#334155;'>Selecciona un módulo en el menú superior <b>Base de Datos</b> para administrar registros.</p>"
                + "<p style='font-size:11px; color:#64748B;'>Catálogos: Clientes • Marcas • Aseguradoras • Coberturas &nbsp;|&nbsp; Operación: Vehículos • Pólizas • Pagos</p>"
                + "</div></html>",
                SwingConstants.CENTER
        );
        lbl.setFont(new Font("Arial", Font.PLAIN, 14));
        centro.add(lbl);
        add(centro, BorderLayout.CENTER);
    }
}
