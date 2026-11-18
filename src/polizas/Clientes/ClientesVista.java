package polizas.Clientes;

import java.awt.Font;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ClientesVista extends JPanel {
    protected JTextField txtNombre, txtApellidos, txtTelefono, txtEmail, txtRfc;
    protected JButton btnConsultar, btnAgregar, btnActualizar, btnEliminar;
    protected JTable tabla;
    protected ClientesTM modeloTabla;

    public ClientesVista() {
        setLayout(null);

        JLabel titulo = new JLabel("Administración de CLIENTES");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setBounds(40, 15, 350, 28);
        add(titulo);

        JLabel l1 = new JLabel("Nombre:"); l1.setBounds(40, 55, 90, 26); add(l1);
        txtNombre = new JTextField(); txtNombre.setBounds(130, 55, 200, 26); add(txtNombre);

        JLabel l2 = new JLabel("Apellidos:"); l2.setBounds(360, 55, 90, 26); add(l2);
        txtApellidos = new JTextField(); txtApellidos.setBounds(450, 55, 220, 26); add(txtApellidos);

        JLabel l3 = new JLabel("Teléfono:"); l3.setBounds(40, 92, 90, 26); add(l3);
        txtTelefono = new JTextField(); txtTelefono.setBounds(130, 92, 200, 26); add(txtTelefono);

        JLabel l4 = new JLabel("Email:"); l4.setBounds(360, 92, 90, 26); add(l4);
        txtEmail = new JTextField(); txtEmail.setBounds(450, 92, 220, 26); add(txtEmail);

        JLabel l5 = new JLabel("RFC:"); l5.setBounds(700, 55, 60, 26); add(l5);
        txtRfc = new JTextField(); txtRfc.setBounds(760, 55, 160, 26); add(txtRfc);

        btnConsultar = new JButton("Consultar"); btnConsultar.setBounds(40, 135, 110, 30); add(btnConsultar);
        btnAgregar = new JButton("Agregar"); btnAgregar.setBounds(160, 135, 110, 30); add(btnAgregar);
        btnActualizar = new JButton("Actualizar"); btnActualizar.setBounds(280, 135, 110, 30); add(btnActualizar);
        btnEliminar = new JButton("Eliminar"); btnEliminar.setBounds(400, 135, 110, 30); add(btnEliminar);

        modeloTabla = new ClientesTM();
        tabla = new JTable(modeloTabla);
        JScrollPane sc = new JScrollPane(tabla);
        sc.setBounds(40, 180, 880, 400);
        add(sc);
    }

    public void actualizarVista(List<Cliente> lista) {
        modeloTabla.llenar(lista);
    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtApellidos.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtRfc.setText("");
    }
}
