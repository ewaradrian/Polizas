package polizas.Pagos;

import java.awt.Font;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import polizas.PolizasModulo.Poliza;

public class PagosVista extends JPanel {
    protected JComboBox<Poliza> cmbPoliza;
    protected JComboBox<String> cmbMetodo;
    protected JTextField txtFechaPago, txtMonto;
    protected JButton btnConsultar, btnAgregar, btnActualizar, btnEliminar;
    protected JTable tabla;
    protected PagosTM modeloTabla;

    public PagosVista() {
        setLayout(null);

        JLabel titulo = new JLabel("Administración de PAGOS DE PÓLIZAS");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setBounds(40, 15, 420, 28);
        add(titulo);

        JLabel l1 = new JLabel("Póliza:"); l1.setBounds(40, 60, 70, 26); add(l1);
        cmbPoliza = new JComboBox<>(); cmbPoliza.setBounds(110, 60, 230, 26); add(cmbPoliza);

        JLabel l2 = new JLabel("Fecha (AAAA-MM-DD):"); l2.setBounds(360, 60, 140, 26); add(l2);
        txtFechaPago = new JTextField("2026-11-18"); txtFechaPago.setBounds(505, 60, 120, 26); add(txtFechaPago);

        JLabel l3 = new JLabel("Monto ($):"); l3.setBounds(645, 60, 70, 26); add(l3);
        txtMonto = new JTextField(); txtMonto.setBounds(720, 60, 130, 26); add(txtMonto);

        JLabel l4 = new JLabel("Método de Pago:"); l4.setBounds(40, 98, 120, 26); add(l4);
        cmbMetodo = new JComboBox<>(new String[]{"Efectivo", "Transferencia", "Tarjeta Crédito", "Tarjeta Débito"});
        cmbMetodo.setBounds(165, 98, 180, 26); add(cmbMetodo);

        btnConsultar = new JButton("Consultar"); btnConsultar.setBounds(40, 140, 110, 30); add(btnConsultar);
        btnAgregar = new JButton("Agregar"); btnAgregar.setBounds(160, 140, 110, 30); add(btnAgregar);
        btnActualizar = new JButton("Actualizar"); btnActualizar.setBounds(280, 140, 110, 30); add(btnActualizar);
        btnEliminar = new JButton("Eliminar"); btnEliminar.setBounds(400, 140, 110, 30); add(btnEliminar);

        modeloTabla = new PagosTM();
        tabla = new JTable(modeloTabla);
        JScrollPane sc = new JScrollPane(tabla);
        sc.setBounds(40, 185, 880, 400);
        add(sc);
    }

    public void actualizarVista(List<Pago> lista) {
        modeloTabla.llenar(lista);
    }
}
