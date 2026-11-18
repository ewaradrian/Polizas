package polizas.PolizasModulo;

import java.awt.Font;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import polizas.Aseguradoras.Aseguradora;
import polizas.Clientes.Cliente;
import polizas.Coberturas.Cobertura;
import polizas.Vehiculos.Vehiculo;

public class PolizasVista extends JPanel {
    protected JComboBox<Cliente> cmbCliente;
    protected JComboBox<Vehiculo> cmbVehiculo;
    protected JComboBox<Aseguradora> cmbAseguradora;
    protected JComboBox<Cobertura> cmbCobertura;
    protected JComboBox<String> cmbEstado;
    protected JTextField txtNumPoliza, txtFechaInicio, txtFechaFin;
    protected JButton btnConsultar, btnAgregar, btnActualizar, btnEliminar;
    protected JTable tabla;
    protected PolizasTM modeloTabla;

    public PolizasVista() {
        setLayout(null);

        JLabel titulo = new JLabel("Administración de PÓLIZAS DE SEGURO");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setBounds(40, 15, 420, 28);
        add(titulo);

        JLabel l1 = new JLabel("Titular (Cliente):"); l1.setBounds(40, 52, 110, 26); add(l1);
        cmbCliente = new JComboBox<>(); cmbCliente.setBounds(150, 52, 260, 26); add(cmbCliente);

        JLabel l2 = new JLabel("Vehículo:"); l2.setBounds(430, 52, 80, 26); add(l2);
        cmbVehiculo = new JComboBox<>(); cmbVehiculo.setBounds(510, 52, 390, 26); add(cmbVehiculo);

        JLabel l3 = new JLabel("Aseguradora:"); l3.setBounds(40, 88, 110, 26); add(l3);
        cmbAseguradora = new JComboBox<>(); cmbAseguradora.setBounds(150, 88, 260, 26); add(cmbAseguradora);

        JLabel l4 = new JLabel("Cobertura:"); l4.setBounds(430, 88, 80, 26); add(l4);
        cmbCobertura = new JComboBox<>(); cmbCobertura.setBounds(510, 88, 180, 26); add(cmbCobertura);

        JLabel l5 = new JLabel("Estado:"); l5.setBounds(705, 88, 60, 26); add(l5);
        cmbEstado = new JComboBox<>(new String[]{"Activa", "Por vencer", "Vencida", "Cancelada"});
        cmbEstado.setBounds(765, 88, 135, 26); add(cmbEstado);

        JLabel l6 = new JLabel("Núm. Póliza:"); l6.setBounds(40, 124, 110, 26); add(l6);
        txtNumPoliza = new JTextField(); txtNumPoliza.setBounds(150, 124, 180, 26); add(txtNumPoliza);

        JLabel l7 = new JLabel("Inicio (AAAA-MM-DD):"); l7.setBounds(350, 124, 135, 26); add(l7);
        txtFechaInicio = new JTextField("2026-11-18"); txtFechaInicio.setBounds(485, 124, 120, 26); add(txtFechaInicio);

        JLabel l8 = new JLabel("Fin (AAAA-MM-DD):"); l8.setBounds(625, 124, 125, 26); add(l8);
        txtFechaFin = new JTextField("2027-11-18"); txtFechaFin.setBounds(750, 124, 150, 26); add(txtFechaFin);

        btnConsultar = new JButton("Consultar"); btnConsultar.setBounds(40, 165, 110, 30); add(btnConsultar);
        btnAgregar = new JButton("Agregar"); btnAgregar.setBounds(160, 165, 110, 30); add(btnAgregar);
        btnActualizar = new JButton("Actualizar"); btnActualizar.setBounds(280, 165, 110, 30); add(btnActualizar);
        btnEliminar = new JButton("Eliminar"); btnEliminar.setBounds(400, 165, 110, 30); add(btnEliminar);

        modeloTabla = new PolizasTM();
        tabla = new JTable(modeloTabla);
        JScrollPane sc = new JScrollPane(tabla);
        sc.setBounds(40, 210, 880, 380);
        add(sc);
    }

    public void actualizarVista(List<Poliza> lista) {
        modeloTabla.llenar(lista);
    }
}
