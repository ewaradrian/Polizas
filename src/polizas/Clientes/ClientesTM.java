package polizas.Clientes;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ClientesTM extends AbstractTableModel {
    protected List<Cliente> lista;
    protected String[] encabezados = {"ID", "Nombre", "Apellidos", "Teléfono", "Email", "RFC"};

    public ClientesTM() {
        lista = new ArrayList<>();
    }

    @Override
    public String getColumnName(int col) { return encabezados[col]; }

    @Override
    public int getRowCount() { return lista.size(); }

    @Override
    public int getColumnCount() { return encabezados.length; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente c = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return c.getId();
            case 1: return c.getNombre();
            case 2: return c.getApellidos();
            case 3: return c.getTelefono();
            case 4: return c.getEmail();
            case 5: return c.getRfc();
            default: return null;
        }
    }

    public Cliente getCliente(int fila) { return lista.get(fila); }

    public void llenar(List<Cliente> datos) {
        this.lista = datos;
        fireTableDataChanged();
    }
}
