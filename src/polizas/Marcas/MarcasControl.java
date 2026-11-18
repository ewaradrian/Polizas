package polizas.Marcas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;

public class MarcasControl {
    private MarcasVista mv;
    private MarcasModelo mm;
    private List<Marca> lista;

    public MarcasControl(MarcasVista marcasVista) {
        this.mv = marcasVista;
        this.mm = new MarcasModelo();
        marcasEventos();
        // Carga inicial automática al abrir el módulo
        mv.actualizarVista(mm.read());
    }

    public void marcasEventos() {
        mv.btn1.addActionListener(e -> {
            lista = mm.read();
            mv.actualizarVista(lista);
        });

        mv.btn_a.addActionListener(e -> {
            String nombre = mv.txt1.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(mv, "El nombre de la marca no puede estar vacío.");
                return;
            }
            if (mm.existeMarca(nombre)) {
                JOptionPane.showMessageDialog(mv, "Esta marca ya se encuentra registrada.");
                return;
            }
            if (mm.create(new Marca(0, nombre)) > 0) {
                JOptionPane.showMessageDialog(mv, "Marca registrada correctamente.");
                mv.txt1.setText("");
            }
            mv.actualizarVista(mm.read());
        });

        mv.btn_u.addActionListener(e -> {
            int fila = mv.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(mv, "Seleccione un registro de la tabla primero.");
                return;
            }
            String nuevoNombre = mv.txt1.getText().trim();
            if (nuevoNombre.isEmpty()) {
                JOptionPane.showMessageDialog(mv, "El nombre no puede estar vacío.");
                return;
            }
            int id = (int) mv.marcasModelo.getValueAt(fila, 0);
            String nombreActual = (String) mv.marcasModelo.getValueAt(fila, 1);

            if (!nuevoNombre.equalsIgnoreCase(nombreActual) && mm.existeMarca(nuevoNombre)) {
                JOptionPane.showMessageDialog(mv, "Ya existe otra marca con ese nombre.");
                return;
            }
            if (mm.update(new Marca(id, nuevoNombre)) > 0) {
                JOptionPane.showMessageDialog(mv, "Marca actualizada con éxito.");
            }
            mv.actualizarVista(mm.read());
        });

        mv.btn_d.addActionListener(e -> {
            int fila = mv.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(mv, "Seleccione un registro de la tabla primero.");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(mv, "¿Seguro que desea eliminar esta marca?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int id = (int) mv.marcasModelo.getValueAt(fila, 0);
                if (mm.delete(id) > 0) {
                    JOptionPane.showMessageDialog(mv, "Marca eliminada correctamente.");
                    mv.txt1.setText("");
                } else {
                    JOptionPane.showMessageDialog(mv, "No se pudo eliminar: existen vehículos asociados a esta marca (Integridad Referencial).");
                }
                mv.actualizarVista(mm.read());
            }
        });

        mv.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = mv.tabla.getSelectedRow();
                if (fila != -1) {
                    mv.txt1.setText((String) mv.marcasModelo.getValueAt(fila, 1));
                }
            }
        });
    }
}
