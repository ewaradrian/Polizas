package polizas.Vehiculos;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import polizas.Clientes.Cliente;
import polizas.Clientes.ClientesModelo;
import polizas.Marcas.Marca;
import polizas.Marcas.MarcasModelo;

public class VehiculosControl {
    private VehiculosVista vista;
    private VehiculosModelo modelo;
    private ClientesModelo clientesModelo;
    private MarcasModelo marcasModelo;

    public VehiculosControl(VehiculosVista vista) {
        this.vista = vista;
        this.modelo = new VehiculosModelo();
        this.clientesModelo = new ClientesModelo();
        this.marcasModelo = new MarcasModelo();
        cargarCombos();
        eventos();
        vista.actualizarVista(modelo.read());
    }

    private void cargarCombos() {
        vista.cmbCliente.removeAllItems();
        for (Cliente c : clientesModelo.read()) {
            vista.cmbCliente.addItem(c);
        }
        vista.cmbMarca.removeAllItems();
        for (Marca m : marcasModelo.read()) {
            vista.cmbMarca.addItem(m);
        }
    }

    private void eventos() {
        vista.btnConsultar.addActionListener(e -> {
            cargarCombos();
            vista.actualizarVista(modelo.read());
        });

        vista.btnAgregar.addActionListener(e -> {
            Vehiculo v = construirDesdeFormulario(0);
            if (v == null) return;
            if (modelo.create(v) > 0) {
                JOptionPane.showMessageDialog(vista, "Vehículo registrado correctamente.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al registrar: verifica que placas/serie no estén duplicadas y año >= 1950.");
            }
            vista.actualizarVista(modelo.read());
        });

        vista.btnActualizar.addActionListener(e -> {
            int fila = vista.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Seleccione un vehículo de la tabla.");
                return;
            }
            int id = (int) vista.modeloTabla.getValueAt(fila, 0);
            Vehiculo v = construirDesdeFormulario(id);
            if (v == null) return;
            if (modelo.update(v) > 0) {
                JOptionPane.showMessageDialog(vista, "Vehículo actualizado.");
            }
            vista.actualizarVista(modelo.read());
        });

        vista.btnEliminar.addActionListener(e -> {
            int fila = vista.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Seleccione un vehículo de la tabla.");
                return;
            }
            int id = (int) vista.modeloTabla.getValueAt(fila, 0);
            if (JOptionPane.showConfirmDialog(vista, "¿Eliminar vehículo?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (modelo.delete(id) > 0) {
                    JOptionPane.showMessageDialog(vista, "Vehículo eliminado.");
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(vista, "No se puede eliminar: existen pólizas asociadas a este vehículo.");
                }
                vista.actualizarVista(modelo.read());
            }
        });

        vista.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = vista.tabla.getSelectedRow();
                if (fila != -1) {
                    Vehiculo v = vista.modeloTabla.getVehiculo(fila);
                    vista.txtModelo.setText(v.getModelo());
                    vista.txtAnio.setText(String.valueOf(v.getAnio()));
                    vista.txtPlacas.setText(v.getPlacas());
                    vista.txtSerie.setText(v.getNumeroSerie());

                    for (int i = 0; i < vista.cmbCliente.getItemCount(); i++) {
                        if (vista.cmbCliente.getItemAt(i).getId() == v.getIdCliente()) {
                            vista.cmbCliente.setSelectedIndex(i);
                            break;
                        }
                    }
                    for (int i = 0; i < vista.cmbMarca.getItemCount(); i++) {
                        if (vista.cmbMarca.getItemAt(i).getId() == v.getIdMarca()) {
                            vista.cmbMarca.setSelectedIndex(i);
                            break;
                        }
                    }
                }
            }
        });
    }

    private Vehiculo construirDesdeFormulario(int id) {
        Cliente c = (Cliente) vista.cmbCliente.getSelectedItem();
        Marca m = (Marca) vista.cmbMarca.getSelectedItem();
        if (c == null || m == null) {
            JOptionPane.showMessageDialog(vista, "Debe registrar al menos un Cliente y una Marca primero.");
            return null;
        }
        String mod = vista.txtModelo.getText().trim();
        String anioStr = vista.txtAnio.getText().trim();
        String placas = vista.txtPlacas.getText().trim();
        String serie = vista.txtSerie.getText().trim();
        if (mod.isEmpty() || anioStr.isEmpty() || placas.isEmpty() || serie.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Todos los campos son obligatorios.");
            return null;
        }
        try {
            int anio = Integer.parseInt(anioStr);
            if (anio < 1950) {
                JOptionPane.showMessageDialog(vista, "El año debe ser mayor o igual a 1950.");
                return null;
            }
            return new Vehiculo(id, c.getId(), m.getId(), mod, anio, placas, serie);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "El año debe ser un número entero válido.");
            return null;
        }
    }

    private void limpiarCampos() {
        vista.txtModelo.setText("");
        vista.txtAnio.setText("");
        vista.txtPlacas.setText("");
        vista.txtSerie.setText("");
    }
}
