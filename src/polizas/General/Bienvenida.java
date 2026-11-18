package polizas.General;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Ventana principal (JFrame) con navegación mediante CardLayout y JMenuBar.
 */
public class Bienvenida extends JFrame {
    // Barra de menús y opciones
    protected JMenuBar mb;
    protected JMenu mArchivo, mCatalogos, mOperacion;
    protected JMenuItem itemHome, itemProbarBD, itemSalir;
    protected JMenuItem itemClientes, itemMarcas, itemAseguradoras, itemCoberturas;
    protected JMenuItem itemVehiculos, itemPolizas, itemPagos;

    // Botones de acceso rápido en panel lateral izquierdo
    protected JButton btnNavClientes, btnNavMarcas, btnNavVehiculos, btnNavAseguradoras, btnNavCoberturas, btnNavPolizas, btnNavPagos;

    // Contenedores principales
    protected CardLayout cl;
    protected JPanel pi, ps, pc;

    public Bienvenida() {
        this.setTitle("Gestor de Pólizas - Sistema Administrativo de Seguros");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setMinimumSize(new Dimension(1150, 720));

        this.menu();

        // Panel Superior (Encabezado institucional)
        ps = new JPanel();
        ps.setBackground(new Color(20, 110, 55));
        ps.setPreferredSize(new Dimension(getWidth(), 50));
        JLabel titulo = new JLabel("SISTEMA DE GESTIÓN DE PÓLIZAS ");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        ps.add(titulo);

        // Panel Izquierdo (Acceso rápido a los 7 módulos)
        pi = new JPanel(new GridLayout(9, 1, 6, 8));
        pi.setBackground(new Color(241, 245, 249));
        pi.setBorder(BorderFactory.createEmptyBorder(15, 12, 15, 12));
        pi.setPreferredSize(new Dimension(210, getHeight()));

        JLabel lblNav = new JLabel("MÓDULOS DEL SISTEMA", SwingConstants.CENTER);
        lblNav.setFont(new Font("Arial", Font.BOLD, 12));
        lblNav.setForeground(new Color(30, 41, 59));
        pi.add(lblNav);

        btnNavClientes = new JButton("1. Clientes");
        btnNavMarcas = new JButton("2. Marcas");
        btnNavVehiculos = new JButton("3. Vehículos");
        btnNavAseguradoras = new JButton("4. Aseguradoras");
        btnNavCoberturas = new JButton("5. Coberturas");
        btnNavPolizas = new JButton("6. Pólizas");
        btnNavPagos = new JButton("7. Pagos");

        pi.add(btnNavClientes);
        pi.add(btnNavMarcas);
        pi.add(btnNavVehiculos);
        pi.add(btnNavAseguradoras);
        pi.add(btnNavCoberturas);
        pi.add(btnNavPolizas);
        pi.add(btnNavPagos);

        // Panel Central con CardLayout
        cl = new CardLayout();
        pc = new JPanel(cl);
        pc.add(new Mensaje(), "home");

        this.setLayout(new BorderLayout());
        this.add(ps, BorderLayout.NORTH);
        this.add(pi, BorderLayout.WEST);
        this.add(pc, BorderLayout.CENTER);

        this.setVisible(true);
    }

    private void menu() {
        this.mb = new JMenuBar();
        this.setJMenuBar(mb);

        // Menú Archivo
        mArchivo = new JMenu("Archivo");
        itemHome = new JMenuItem("Inicio");
        itemProbarBD = new JMenuItem("Probar Conexión MySQL");
        itemSalir = new JMenuItem("Salir");
        mArchivo.add(itemHome);
        mArchivo.add(itemProbarBD);
        mArchivo.addSeparator();
        mArchivo.add(itemSalir);

        // Menú Catálogos (Tablas maestras)
        mCatalogos = new JMenu("Base de Datos (Catálogos)");
        itemClientes = new JMenuItem("Clientes");
        itemMarcas = new JMenuItem("Marcas");
        itemAseguradoras = new JMenuItem("Aseguradoras");
        itemCoberturas = new JMenuItem("Coberturas");
        mCatalogos.add(itemClientes);
        mCatalogos.add(itemMarcas);
        mCatalogos.add(itemAseguradoras);
        mCatalogos.add(itemCoberturas);

        // Menú Operación (Tablas transaccionales con FK)
        mOperacion = new JMenu("Operación (Pólizas)");
        itemVehiculos = new JMenuItem("Vehículos");
        itemPolizas = new JMenuItem("Pólizas");
        itemPagos = new JMenuItem("Pagos");
        mOperacion.add(itemVehiculos);
        mOperacion.add(itemPolizas);
        mOperacion.add(itemPagos);

        mb.add(mArchivo);
        mb.add(mCatalogos);
        mb.add(mOperacion);
    }
}
