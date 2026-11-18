package polizas.Marcas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class MarcasTM extends AbstractTableModel {
    protected List<Marca> marcaslista;
    protected String[] encabezados = {"ID", "Nombre de Marca"};

    public MarcasTM() {
        marcaslista = new ArrayList<>();
    }

    @Override
    public String getColumnName(int column) {
        return encabezados[column];
    }

    public void vaciarlista() {
        marcaslista.clear();
    }

    @Override
    public int getRowCount() {
        return marcaslista.size();
    }

    @Override
    public int getColumnCount() {
        return encabezados.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex >= 0 && rowIndex < marcaslista.size()) {
            Marca m = marcaslista.get(rowIndex);
            switch (columnIndex) {
                case 0: return m.getId();
                case 1: return m.getNombre();
                default: return null;
            }
        }
        return null;
    }

    public Marca getMarca(int fila) {
        return marcaslista.get(fila);
    }

    public void llenar(List<Marca> lista) {
        this.marcaslista = lista;
        fireTableDataChanged();
    }
}
