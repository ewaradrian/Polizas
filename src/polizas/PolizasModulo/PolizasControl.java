package polizas.PolizasModulo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import javax.swing.JOptionPane;
import polizas.Aseguradoras.Aseguradora;
import polizas.Aseguradoras.AseguradorasModelo;
import polizas.Clientes.Cliente;
import polizas.Clientes.ClientesModelo;
import polizas.Coberturas.Cobertura;
import polizas.Coberturas.CoberturasModelo;
import polizas.Vehiculos.Vehiculo;
import polizas.Vehiculos.VehiculosModelo;

public class PolizasControl {
    private PolizasVista vista;
    private PolizasModelo modelo;
    private ClientesModelo clientesModelo;
    private VehiculosModelo vehiculosModelo;
    private AseguradorasModelo aseguradorasModelo;
    private CoberturasModelo coberturasModelo;

    public PolizasControl(PolizasVista vista) {
        this.vista = vista;
        this.modelo = new PolizasModelo();
        this.clientesModelo = new ClientesModelo();
        this.vehiculosModelo = new VehiculosModelo();
        this.aseguradorasModelo = new AseguradorasModelo();
        this.coberturasModelo = new CoberturasModelo();
        cargarCombos();
        eventos();
        vista.actualizarVista(modelo.read());
    }

    private void cargarCombos() {
        vista.cmbCliente.removeAllItems();
        for (Cliente c : clientesModelo.read()) vista.cmbCliente.addItem(c);

        vista.cmbVehiculo.removeAllItems();
        for (Vehiculo v : vehiculosModelo.read()) vista.cmbVehiculo.addItem(v);

        vista.cmbAseguradora.removeAllItems();
        for (Aseguradora a : aseguradorasModelo.read()) vista.cmbAseguradora.addItem(a);

        vista.cmbCobertura.removeAllItems();
        for (Cobertura cob : coberturasModelo.read()) vista.cmbCobertura.addItem(cob);
    }

    private void eventos() {
        vista.btnConsultar.addActionListener(e -> {
            cargarCombos();
            vista.actualizarVista(modelo.read());
        });

        vista.btnAgregar.addActionListener(e -> {
            Poliza p = construirDesdeFormulario(0);
            if (p == null) return;
            if (modelo.create(p) > 0) {
                JOptionPane.showMessageDialog(vista, "Póliza registrada correctamente.");
                vista.txtNumPoliza.setText("");
            } else {
                JOptionPane.showMessageDialog(vista, "Error al registrar: verifica que el número de póliza sea único y fechaFin > fechaInicio.");
            }
            vista.actualizarVista(modelo.read());
        });

        vista.btnActualizar.addActionListener(e -> {
            int fila = vista.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Seleccione una póliza de la tabla.");
                return;
            }
            int id = (int) vista.modeloTabla.getValueAt(fila, 0);
            Poliza p = construirDesdeFormulario(id);
            if (p == null) return;
            if (modelo.update(p) > 0) {
                JOptionPane.showMessageDialog(vista, "Póliza actualizada correctamente.");
            }
            vista.actualizarVista(modelo.read());
        });

        vista.btnEliminar.addActionListener(e -> {
            int fila = vista.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Seleccione una póliza de la tabla.");
                return;
            }
            int id = (int) vista.modeloTabla.getValueAt(fila, 0);
            if (JOptionPane.showConfirmDialog(vista, "¿Eliminar póliza? (También eliminará sus pagos asociados)", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (modelo.delete(id) > 0) {
                    JOptionPane.showMessageDialog(vista, "Póliza eliminada.");
                    vista.txtNumPoliza.setText("");
                }
                vista.actualizarVista(modelo.read());
            }
        });

        vista.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = vista.tabla.getSelectedRow();
                if (fila != -1) {
                    Poliza p = vista.modeloTabla.getPoliza(fila);
                    vista.txtNumPoliza.setText(p.getNumeroPoliza());
                    vista.txtFechaInicio.setText(p.getFechaInicio().toString());
                    vista.txtFechaFin.setText(p.getFechaFin().toString());
                    vista.cmbEstado.setSelectedItem(p.getEstado());

                    for (int i = 0; i < vista.cmbCliente.getItemCount(); i++) {
                        if (vista.cmbCliente.getItemAt(i).getId() == p.getIdCliente()) {
                            vista.cmbCliente.setSelectedIndex(i); break;
                        }
                    }
                    for (int i = 0; i < vista.cmbVehiculo.getItemCount(); i++) {
                        if (vista.cmbVehiculo.getItemAt(i).getId() == p.getIdVehiculo()) {
                            vista.cmbVehiculo.setSelectedIndex(i); break;
                        }
                    }
                    for (int i = 0; i < vista.cmbAseguradora.getItemCount(); i++) {
                        if (vista.cmbAseguradora.getItemAt(i).getId() == p.getIdAseguradora()) {
                            vista.cmbAseguradora.setSelectedIndex(i); break;
                        }
                    }
                    for (int i = 0; i < vista.cmbCobertura.getItemCount(); i++) {
                        if (vista.cmbCobertura.getItemAt(i).getId() == p.getIdCobertura()) {
                            vista.cmbCobertura.setSelectedIndex(i); break;
                        }
                    }
                }
            }
        });
    }

    private Poliza construirDesdeFormulario(int id) {
        Cliente c = (Cliente) vista.cmbCliente.getSelectedItem();
        Vehiculo v = (Vehiculo) vista.cmbVehiculo.getSelectedItem();
        Aseguradora a = (Aseguradora) vista.cmbAseguradora.getSelectedItem();
        Cobertura cob = (Cobertura) vista.cmbCobertura.getSelectedItem();

        if (c == null || v == null || a == null || cob == null) {
            JOptionPane.showMessageDialog(vista, "Debe haber al menos un Cliente, Vehículo, Aseguradora y Cobertura registrados.");
            return null;
        }
        String num = vista.txtNumPoliza.getText().trim();
        String iniStr = vista.txtFechaInicio.getText().trim();
        String finStr = vista.txtFechaFin.getText().trim();
        if (num.isEmpty() || iniStr.isEmpty() || finStr.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Complete todos los campos de la póliza.");
            return null;
        }
        try {
            Date fInicio = Date.valueOf(iniStr);
            Date fFin = Date.valueOf(finStr);
            if (!fFin.after(fInicio)) {
                JOptionPane.showMessageDialog(vista, "La fecha de fin debe ser posterior a la fecha de inicio.");
                return null;
            }
            String estado = (String) vista.cmbEstado.getSelectedItem();
            return new Poliza(id, c.getId(), v.getId(), a.getId(), cob.getId(), num, fInicio, fFin, estado);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(vista, "Formato de fecha inválido. Use AAAA-MM-DD (ej. 2026-11-18).");
            return null;
        }
    }
}
