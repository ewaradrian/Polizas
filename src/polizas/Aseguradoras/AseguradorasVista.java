package polizas.Aseguradoras;

import java.awt.Font;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AseguradorasVista extends JPanel {
    protected JTextField txtNombre, txtTelefono;
    protected JButton btnConsultar, btnAgregar, btnActualizar, btnEliminar;
    protected JTable tabla;
    protected AseguradorasTM modeloTabla;

    public AseguradorasVista() {
        setLayout(null);

        JLabel titulo = new JLabel("Administración de Catálogo: ASEGURADORAS");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setBounds(40, 15, 420, 28);
        add(titulo);

        JLabel l1 = new JLabel("Nombre Compañía:"); l1.setBounds(40, 60, 130, 28); add(l1);
        txtNombre = new JTextField(); txtNombre.setBounds(175, 60, 230, 28); add(txtNombre);

        JLabel l2 = new JLabel("Tel. Contacto:"); l2.setBounds(430, 60, 100, 28); add(l2);
        txtTelefono = new JTextField(); txtTelefono.setBounds(535, 60, 180, 28); add(txtTelefono);

        btnConsultar = new JButton("Consultar"); btnConsultar.setBounds(40, 105, 110, 30); add(btnConsultar);
        btnAgregar = new JButton("Agregar"); btnAgregar.setBounds(160, 105, 110, 30); add(btnAgregar);
        btnActualizar = new JButton("Actualizar"); btnActualizar.setBounds(280, 105, 110, 30); add(btnActualizar);
        btnEliminar = new JButton("Eliminar"); btnEliminar.setBounds(400, 105, 110, 30); add(btnEliminar);

        modeloTabla = new AseguradorasTM();
        tabla = new JTable(modeloTabla);
        JScrollPane sc = new JScrollPane(tabla);
        sc.setBounds(40, 155, 850, 420);
        add(sc);
    }

    public void actualizarVista(List<Aseguradora> lista) {
        modeloTabla.llenar(lista);
    }
}
