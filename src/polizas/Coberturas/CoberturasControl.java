package polizas.Coberturas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class CoberturasControl {
    private CoberturasVista vista;
    private CoberturasModelo modelo;

    public CoberturasControl(CoberturasVista vista) {
        this.vista = vista;
        this.modelo = new CoberturasModelo();
        eventos();
        vista.actualizarVista(modelo.read());
    }

    private void eventos() {
        vista.btnConsultar.addActionListener(e -> vista.actualizarVista(modelo.read()));

        vista.btnAgregar.addActionListener(e -> {
            String tipo = vista.txtTipo.getText().trim();
            String desc = vista.txtDescripcion.getText().trim();
            if (tipo.isEmpty() || desc.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Complete todos los campos.");
                return;
            }
            if (modelo.create(new Cobertura(0, tipo, desc)) > 0) {
                JOptionPane.showMessageDialog(vista, "Cobertura registrada.");
                vista.txtTipo.setText("");
                vista.txtDescripcion.setText("");
            } else {
                JOptionPane.showMessageDialog(vista, "Error: verifique que el tipo de cobertura no exista ya.");
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
            String tipo = vista.txtTipo.getText().trim();
            String desc = vista.txtDescripcion.getText().trim();
            if (tipo.isEmpty() || desc.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Complete todos los campos.");
                return;
            }
            if (modelo.update(new Cobertura(id, tipo, desc)) > 0) {
                JOptionPane.showMessageDialog(vista, "Cobertura actualizada.");
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
            if (JOptionPane.showConfirmDialog(vista, "¿Eliminar cobertura?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (modelo.delete(id) > 0) {
                    JOptionPane.showMessageDialog(vista, "Cobertura eliminada.");
                    vista.txtTipo.setText("");
                    vista.txtDescripcion.setText("");
                } else {
                    JOptionPane.showMessageDialog(vista, "No se puede eliminar: existen pólizas usando esta cobertura.");
                }
                vista.actualizarVista(modelo.read());
            }
        });

        vista.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = vista.tabla.getSelectedRow();
                if (fila != -1) {
                    Cobertura c = vista.modeloTabla.getCobertura(fila);
                    vista.txtTipo.setText(c.getTipo());
                    vista.txtDescripcion.setText(c.getDescripcion());
                }
            }
        });
    }
}
