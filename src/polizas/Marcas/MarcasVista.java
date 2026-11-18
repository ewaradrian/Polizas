package polizas.Marcas;

import java.awt.Font;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MarcasVista extends JPanel {
    protected JButton btn1, btn_a, btn_u, btn_d;
    protected JTextField txt1;
    protected JLabel lbl1, lbl2, lblCombo;
    protected JTable tabla;
    protected MarcasTM marcasModelo;
    protected JComboBox<Marca> cmbMarcas;

    public MarcasVista() {
        setLayout(null);

        lbl1 = new JLabel("Administración de Catálogo: MARCAS");
        lbl1.setFont(new Font("Arial", Font.BOLD, 16));
        lbl1.setBounds(40, 15, 400, 30);
        add(lbl1);

        lbl2 = new JLabel("Nombre de la Marca:");
        lbl2.setBounds(40, 60, 140, 28);
        add(lbl2);

        txt1 = new JTextField();
        txt1.setBounds(185, 60, 240, 28);
        add(txt1);

        lblCombo = new JLabel("Vista previa Combo:");
        lblCombo.setBounds(460, 60, 130, 28);
        add(lblCombo);

        cmbMarcas = new JComboBox<>();
        cmbMarcas.setBounds(595, 60, 220, 28);
        add(cmbMarcas);

        btn1 = new JButton("Consultar");
        btn1.setBounds(40, 105, 110, 30);
        add(btn1);

        btn_a = new JButton("Agregar");
        btn_a.setBounds(160, 105, 110, 30);
        add(btn_a);

        btn_u = new JButton("Actualizar");
        btn_u.setBounds(280, 105, 110, 30);
        add(btn_u);

        btn_d = new JButton("Eliminar");
        btn_d.setBounds(400, 105, 110, 30);
        add(btn_d);

        marcasModelo = new MarcasTM();
        tabla = new JTable(marcasModelo);
        JScrollPane sc = new JScrollPane(tabla);
        sc.setBounds(40, 155, 850, 420);
        add(sc);
    }

    public void actualizarVista(List<Marca> lista) {
        marcasModelo.vaciarlista();
        marcasModelo.llenar(lista);

        cmbMarcas.removeAllItems();
        for (Marca m : lista) {
            cmbMarcas.addItem(m);
        }
    }
}
