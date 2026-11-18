package polizas.Clientes;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class ClientesControl {
    private ClientesVista vista;
    private ClientesModelo modelo;

    public ClientesControl(ClientesVista vista) {
        this.vista = vista;
        this.modelo = new ClientesModelo();
        eventos();
        vista.actualizarVista(modelo.read());
    }

    private void eventos() {
        vista.btnConsultar.addActionListener(e -> vista.actualizarVista(modelo.read()));

        vista.btnAgregar.addActionListener(e -> {
            if (!validarCampos()) return;
            Cliente c = new Cliente(0,
                    vista.txtNombre.getText().trim(),
                    vista.txtApellidos.getText().trim(),
                    vista.txtTelefono.getText().trim(),
                    vista.txtEmail.getText().trim(),
                    vista.txtRfc.getText().trim());
            if (modelo.create(c) > 0) {
                JOptionPane.showMessageDialog(vista, "Cliente registrado correctamente.");
                vista.limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al registrar: verifica que RFC o Email no estén duplicados.");
            }
            vista.actualizarVista(modelo.read());
        });

        vista.btnActualizar.addActionListener(e -> {
            int fila = vista.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Seleccione un cliente de la tabla.");
                return;
            }
            if (!validarCampos()) return;
            int id = (int) vista.modeloTabla.getValueAt(fila, 0);
            Cliente c = new Cliente(id,
                    vista.txtNombre.getText().trim(),
                    vista.txtApellidos.getText().trim(),
                    vista.txtTelefono.getText().trim(),
                    vista.txtEmail.getText().trim(),
                    vista.txtRfc.getText().trim());
            if (modelo.update(c) > 0) {
                JOptionPane.showMessageDialog(vista, "Cliente actualizado correctamente.");
            }
            vista.actualizarVista(modelo.read());
        });

        vista.btnEliminar.addActionListener(e -> {
            int fila = vista.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Seleccione un cliente de la tabla.");
                return;
            }
            int id = (int) vista.modeloTabla.getValueAt(fila, 0);
            int op = JOptionPane.showConfirmDialog(vista, "¿Eliminar cliente seleccionado?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (op == JOptionPane.YES_OPTION) {
                if (modelo.delete(id) > 0) {
                    JOptionPane.showMessageDialog(vista, "Cliente eliminado.");
                    vista.limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(vista, "No se puede eliminar el cliente porque tiene vehículos o pólizas activas.");
                }
                vista.actualizarVista(modelo.read());
            }
        });

        vista.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = vista.tabla.getSelectedRow();
                if (fila != -1) {
                    Cliente c = vista.modeloTabla.getCliente(fila);
                    vista.txtNombre.setText(c.getNombre());
                    vista.txtApellidos.setText(c.getApellidos());
                    vista.txtTelefono.setText(c.getTelefono());
                    vista.txtEmail.setText(c.getEmail());
                    vista.txtRfc.setText(c.getRfc());
                }
            }
        });
    }

    private boolean validarCampos() {
        if (vista.txtNombre.getText().trim().isEmpty()
                || vista.txtApellidos.getText().trim().isEmpty()
                || vista.txtTelefono.getText().trim().isEmpty()
                || vista.txtEmail.getText().trim().isEmpty()
                || vista.txtRfc.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Todos los campos del cliente son obligatorios.");
            return false;
        }
        return true;
    }
}
