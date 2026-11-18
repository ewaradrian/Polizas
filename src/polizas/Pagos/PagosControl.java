package polizas.Pagos;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.Date;
import javax.swing.JOptionPane;
import polizas.PolizasModulo.Poliza;
import polizas.PolizasModulo.PolizasModelo;

public class PagosControl {
    private PagosVista vista;
    private PagosModelo modelo;
    private PolizasModelo polizasModelo;

    public PagosControl(PagosVista vista) {
        this.vista = vista;
        this.modelo = new PagosModelo();
        this.polizasModelo = new PolizasModelo();
        cargarCombos();
        eventos();
        vista.actualizarVista(modelo.read());
    }

    private void cargarCombos() {
        vista.cmbPoliza.removeAllItems();
        for (Poliza p : polizasModelo.read()) {
            vista.cmbPoliza.addItem(p);
        }
    }

    private void eventos() {
        vista.btnConsultar.addActionListener(e -> {
            cargarCombos();
            vista.actualizarVista(modelo.read());
        });

        vista.btnAgregar.addActionListener(e -> {
            Pago pg = construirDesdeFormulario(0);
            if (pg == null) return;
            if (modelo.create(pg) > 0) {
                JOptionPane.showMessageDialog(vista, "Pago registrado correctamente.");
                vista.txtMonto.setText("");
            } else {
                JOptionPane.showMessageDialog(vista, "Error al registrar el pago.");
            }
            vista.actualizarVista(modelo.read());
        });

        vista.btnActualizar.addActionListener(e -> {
            int fila = vista.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Seleccione un pago de la tabla.");
                return;
            }
            int id = (int) vista.modeloTabla.getValueAt(fila, 0);
            Pago pg = construirDesdeFormulario(id);
            if (pg == null) return;
            if (modelo.update(pg) > 0) {
                JOptionPane.showMessageDialog(vista, "Pago actualizado.");
            }
            vista.actualizarVista(modelo.read());
        });

        vista.btnEliminar.addActionListener(e -> {
            int fila = vista.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Seleccione un pago de la tabla.");
                return;
            }
            int id = (int) vista.modeloTabla.getValueAt(fila, 0);
            if (JOptionPane.showConfirmDialog(vista, "¿Eliminar pago?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (modelo.delete(id) > 0) {
                    JOptionPane.showMessageDialog(vista, "Pago eliminado.");
                    vista.txtMonto.setText("");
                }
                vista.actualizarVista(modelo.read());
            }
        });

        vista.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = vista.tabla.getSelectedRow();
                if (fila != -1) {
                    Pago pg = vista.modeloTabla.getPago(fila);
                    vista.txtFechaPago.setText(pg.getFechaPago().toString());
                    vista.txtMonto.setText(pg.getMonto().toPlainString());
                    vista.cmbMetodo.setSelectedItem(pg.getMetodoPago());

                    for (int i = 0; i < vista.cmbPoliza.getItemCount(); i++) {
                        if (vista.cmbPoliza.getItemAt(i).getId() == pg.getIdPoliza()) {
                            vista.cmbPoliza.setSelectedIndex(i);
                            break;
                        }
                    }
                }
            }
        });
    }

    private Pago construirDesdeFormulario(int id) {
        Poliza p = (Poliza) vista.cmbPoliza.getSelectedItem();
        if (p == null) {
            JOptionPane.showMessageDialog(vista, "Debe registrar al menos una Póliza primero.");
            return null;
        }
        String fechaStr = vista.txtFechaPago.getText().trim();
        String montoStr = vista.txtMonto.getText().trim();
        if (fechaStr.isEmpty() || montoStr.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Fecha y monto son obligatorios.");
            return null;
        }
        try {
            Date fecha = Date.valueOf(fechaStr);
            BigDecimal monto = new BigDecimal(montoStr);
            if (monto.compareTo(BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(vista, "El monto debe ser mayor a 0.");
                return null;
            }
            String metodo = (String) vista.cmbMetodo.getSelectedItem();
            return new Pago(id, p.getId(), fecha, monto, metodo);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(vista, "Verifica formato de fecha (AAAA-MM-DD) o monto numérico.");
            return null;
        }
    }
}
