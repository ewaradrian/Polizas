package polizas.General;

import javax.swing.JOptionPane;
import polizas.Aseguradoras.AseguradorasControl;
import polizas.Aseguradoras.AseguradorasVista;
import polizas.Clientes.ClientesControl;
import polizas.Clientes.ClientesVista;
import polizas.Coberturas.CoberturasControl;
import polizas.Coberturas.CoberturasVista;
import polizas.Marcas.MarcasControl;
import polizas.Marcas.MarcasVista;
import polizas.Pagos.PagosControl;
import polizas.Pagos.PagosVista;
import polizas.PolizasModulo.PolizasControl;
import polizas.PolizasModulo.PolizasVista;
import polizas.Vehiculos.VehiculosControl;
import polizas.Vehiculos.VehiculosVista;

/**
 * Controlador principal que enlaza las opciones del menú y botones laterales
 * con cada módulo MVC mediante el CardLayout.
 */
public class Controlador {
    protected Bienvenida vista;

    public Controlador(Bienvenida vista) {
        this.vista = vista;
        this.eventos();
    }

    private void eventos() {
        // Archivo -> Inicio
        vista.itemHome.addActionListener(e -> vista.cl.show(vista.pc, "home"));

        // Archivo -> Probar Conexión MySQL
        vista.itemProbarBD.addActionListener(e -> {
            Conexion c = new Conexion();
            if (c.probarConexion()) {
                JOptionPane.showMessageDialog(vista,
                        "Conexión exitosa con la base de datos 'gestor_polizas' en MySQL.",
                        "Conexión Verificada", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(vista,
                        "No se pudo conectar a 'gestor_polizas'.\nVerifica que MySQL esté activo, la BD exista y usuario/contraseña coincidan en Conexion.java.",
                        "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Archivo -> Salir
        vista.itemSalir.addActionListener(e -> System.exit(0));

        // 1. Clientes
        vista.itemClientes.addActionListener(e -> abrirClientes());
        vista.btnNavClientes.addActionListener(e -> abrirClientes());

        // 2. Marcas
        vista.itemMarcas.addActionListener(e -> abrirMarcas());
        vista.btnNavMarcas.addActionListener(e -> abrirMarcas());

        // 3. Vehículos
        vista.itemVehiculos.addActionListener(e -> abrirVehiculos());
        vista.btnNavVehiculos.addActionListener(e -> abrirVehiculos());

        // 4. Aseguradoras
        vista.itemAseguradoras.addActionListener(e -> abrirAseguradoras());
        vista.btnNavAseguradoras.addActionListener(e -> abrirAseguradoras());

        // 5. Coberturas
        vista.itemCoberturas.addActionListener(e -> abrirCoberturas());
        vista.btnNavCoberturas.addActionListener(e -> abrirCoberturas());

        // 6. Pólizas
        vista.itemPolizas.addActionListener(e -> abrirPolizas());
        vista.btnNavPolizas.addActionListener(e -> abrirPolizas());

        // 7. Pagos
        vista.itemPagos.addActionListener(e -> abrirPagos());
        vista.btnNavPagos.addActionListener(e -> abrirPagos());
    }

    private void abrirClientes() {
        ClientesVista v = new ClientesVista();
        new ClientesControl(v);
        vista.pc.add(v, "clientes");
        vista.cl.show(vista.pc, "clientes");
    }

    private void abrirMarcas() {
        MarcasVista v = new MarcasVista();
        new MarcasControl(v);
        vista.pc.add(v, "marcas");
        vista.cl.show(vista.pc, "marcas");
    }

    private void abrirVehiculos() {
        VehiculosVista v = new VehiculosVista();
        new VehiculosControl(v);
        vista.pc.add(v, "vehiculos");
        vista.cl.show(vista.pc, "vehiculos");
    }

    private void abrirAseguradoras() {
        AseguradorasVista v = new AseguradorasVista();
        new AseguradorasControl(v);
        vista.pc.add(v, "aseguradoras");
        vista.cl.show(vista.pc, "aseguradoras");
    }

    private void abrirCoberturas() {
        CoberturasVista v = new CoberturasVista();
        new CoberturasControl(v);
        vista.pc.add(v, "coberturas");
        vista.cl.show(vista.pc, "coberturas");
    }

    private void abrirPolizas() {
        PolizasVista v = new PolizasVista();
        new PolizasControl(v);
        vista.pc.add(v, "polizas");
        vista.cl.show(vista.pc, "polizas");
    }

    private void abrirPagos() {
        PagosVista v = new PagosVista();
        new PagosControl(v);
        vista.pc.add(v, "pagos");
        vista.cl.show(vista.pc, "pagos");
    }
}
