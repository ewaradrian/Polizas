package polizas.Vehiculos;

import java.awt.Font;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import polizas.Clientes.Cliente;
import polizas.Marcas.Marca;

public class VehiculosVista extends JPanel {
    protected JComboBox<Cliente> cmbCliente;
    protected JComboBox<Marca> cmbMarca;
    protected JTextField txtModelo, txtAnio, txtPlacas, txtSerie;
    protected JButton btnConsultar, btnAgregar, btnActualizar, btnEliminar;
    protected JTable tabla;
    protected VehiculosTM modeloTabla;

    public VehiculosVista() {
        setLayout(null);

        JLabel titulo = new JLabel("Administración de VEHÍCULOS");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setBounds(40, 15, 350, 28);
        add(titulo);

        JLabel l1 = new JLabel("Propietario:"); l1.setBounds(40, 55, 90, 26); add(l1);
        cmbCliente = new JComboBox<>(); cmbCliente.setBounds(130, 55, 250, 26); add(cmbCliente);

        JLabel l2 = new JLabel("Marca:"); l2.setBounds(400, 55, 60, 26); add(l2);
        cmbMarca = new JComboBox<>(); cmbMarca.setBounds(465, 55, 180, 26); add(cmbMarca);

        JLabel l3 = new JLabel("Modelo:"); l3.setBounds(665, 55, 65, 26); add(l3);
        txtModelo = new JTextField(); txtModelo.setBounds(730, 55, 170, 26); add(txtModelo);

        JLabel l4 = new JLabel("Año:"); l4.setBounds(40, 92, 90, 26); add(l4);
        txtAnio = new JTextField(); txtAnio.setBounds(130, 92, 100, 26); add(txtAnio);

        JLabel l5 = new JLabel("Placas:"); l5.setBounds(250, 92, 60, 26); add(l5);
        txtPlacas = new JTextField(); txtPlacas.setBounds(315, 92, 140, 26); add(txtPlacas);

        JLabel l6 = new JLabel("Núm. Serie (VIN):"); l6.setBounds(475, 92, 120, 26); add(l6);
        txtSerie = new JTextField(); txtSerie.setBounds(600, 92, 300, 26); add(txtSerie);

        btnConsultar = new JButton("Consultar"); btnConsultar.setBounds(40, 135, 110, 30); add(btnConsultar);
        btnAgregar = new JButton("Agregar"); btnAgregar.setBounds(160, 135, 110, 30); add(btnAgregar);
        btnActualizar = new JButton("Actualizar"); btnActualizar.setBounds(280, 135, 110, 30); add(btnActualizar);
        btnEliminar = new JButton("Eliminar"); btnEliminar.setBounds(400, 135, 110, 30); add(btnEliminar);

        modeloTabla = new VehiculosTM();
        tabla = new JTable(modeloTabla);
        JScrollPane sc = new JScrollPane(tabla);
        sc.setBounds(40, 180, 880, 400);
        add(sc);
    }

    public void actualizarVista(List<Vehiculo> lista) {
        modeloTabla.llenar(lista);
    }
}
