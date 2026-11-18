package polizas.Coberturas;

import java.awt.Font;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class CoberturasVista extends JPanel {
    protected JTextField txtTipo, txtDescripcion;
    protected JButton btnConsultar, btnAgregar, btnActualizar, btnEliminar;
    protected JTable tabla;
    protected CoberturasTM modeloTabla;

    public CoberturasVista() {
        setLayout(null);

        JLabel titulo = new JLabel("Administración de Catálogo: COBERTURAS");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setBounds(40, 15, 420, 28);
        add(titulo);

        JLabel l1 = new JLabel("Tipo (ej. Amplia):"); l1.setBounds(40, 60, 120, 28); add(l1);
        txtTipo = new JTextField(); txtTipo.setBounds(165, 60, 180, 28); add(txtTipo);

        JLabel l2 = new JLabel("Descripción:"); l2.setBounds(370, 60, 90, 28); add(l2);
        txtDescripcion = new JTextField(); txtDescripcion.setBounds(465, 60, 420, 28); add(txtDescripcion);

        btnConsultar = new JButton("Consultar"); btnConsultar.setBounds(40, 105, 110, 30); add(btnConsultar);
        btnAgregar = new JButton("Agregar"); btnAgregar.setBounds(160, 105, 110, 30); add(btnAgregar);
        btnActualizar = new JButton("Actualizar"); btnActualizar.setBounds(280, 105, 110, 30); add(btnActualizar);
        btnEliminar = new JButton("Eliminar"); btnEliminar.setBounds(400, 105, 110, 30); add(btnEliminar);

        modeloTabla = new CoberturasTM();
        tabla = new JTable(modeloTabla);
        JScrollPane sc = new JScrollPane(tabla);
        sc.setBounds(40, 155, 850, 420);
        add(sc);
    }

    public void actualizarVista(List<Cobertura> lista) {
        modeloTabla.llenar(lista);
    }
}
