package polizas.Aseguradoras;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class AseguradorasTM extends AbstractTableModel {
    protected List<Aseguradora> lista = new ArrayList<>();
    protected String[] encabezados = {"ID", "Aseguradora", "Teléfono de Contacto"};

    @Override
    public String getColumnName(int col) { return encabezados[col]; }
    @Override
    public int getRowCount() { return lista.size(); }
    @Override
    public int getColumnCount() { return encabezados.length; }

    @Override
    public Object getValueAt(int r, int c) {
        Aseguradora a = lista.get(r);
        switch (c) {
            case 0: return a.getId();
            case 1: return a.getNombre();
            case 2: return a.getTelefonoContacto();
            default: return null;
        }
    }

    public Aseguradora getAseguradora(int fila) { return lista.get(fila); }

    public void llenar(List<Aseguradora> datos) {
        this.lista = datos;
        fireTableDataChanged();
    }
}
