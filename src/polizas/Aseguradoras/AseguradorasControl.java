package polizas.Aseguradoras;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class AseguradorasControl {
    private AseguradorasVista vista;
    private AseguradorasModelo modelo;

    public AseguradorasControl(AseguradorasVista vista) {
        this.vista = vista;
        this.modelo = new AseguradorasModelo();
        eventos();
        vista.actualizarVista(modelo.read());
    }

    private void eventos() {
        vista.btnConsultar.addActionListener(e -> vista.actualizarVista(modelo.read()));

        vista.btnAgregar.addActionListener(e -> {
            String nombre = vista.txtNombre.getText().trim();
            String tel = vista.txtTelefono.getText().trim();
            if (nombre.isEmpty() || tel.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Complete todos los campos.");
                return;
            }
            if (modelo.create(new Aseguradora(0, nombre, tel)) > 0) {
                JOptionPane.showMessageDialog(vista, "Aseguradora registrada.");
                vista.txtNombre.setText("");
                vista.txtTelefono.setText("");
            } else {
                JOptionPane.showMessageDialog(vista, "Error: verifique que el nombre no esté duplicado.");
            }
            vista.actualizarVista(modelo.read());
        });

        vista.btnActualizar.addActionListener(e -> {
            int fila = vista.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Seleccione un registro primero.");
                return;
            }
            int id = (int) vista.modeloTabla.getValueAt(fila, 0);
            String nombre = vista.txtNombre.getText().trim();
            String tel = vista.txtTelefono.getText().trim();
            if (nombre.isEmpty() || tel.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Complete todos los campos.");
                return;
            }
            if (modelo.update(new Aseguradora(id, nombre, tel)) > 0) {
                JOptionPane.showMessageDialog(vista, "Aseguradora actualizada.");
            }
            vista.actualizarVista(modelo.read());
        });

        vista.btnEliminar.addActionListener(e -> {
            int fila = vista.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Seleccione un registro primero.");
                return;
            }
            int id = (int) vista.modeloTabla.getValueAt(fila, 0);
            if (JOptionPane.showConfirmDialog(vista, "¿Eliminar aseguradora?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (modelo.delete(id) > 0) {
                    JOptionPane.showMessageDialog(vista, "Aseguradora eliminada.");
                    vista.txtNombre.setText("");
                    vista.txtTelefono.setText("");
                } else {
                    JOptionPane.showMessageDialog(vista, "No se puede eliminar: existen pólizas emitidas por esta aseguradora.");
                }
                vista.actualizarVista(modelo.read());
            }
        });

        vista.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = vista.tabla.getSelectedRow();
                if (fila != -1) {
                    Aseguradora a = vista.modeloTabla.getAseguradora(fila);
                    vista.txtNombre.setText(a.getNombre());
                    vista.txtTelefono.setText(a.getTelefonoContacto());
                }
            }
        });
    }
}
