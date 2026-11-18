package polizas.Coberturas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class CoberturasTM extends AbstractTableModel {
    protected List<Cobertura> lista = new ArrayList<>();
    protected String[] encabezados = {"ID", "Tipo de Cobertura", "Descripción"};

    @Override
    public String getColumnName(int col) { return encabezados[col]; }
    @Override
    public int getRowCount() { return lista.size(); }
    @Override
    public int getColumnCount() { return encabezados.length; }

    @Override
    public Object getValueAt(int r, int c) {
        Cobertura cob = lista.get(r);
        switch (c) {
            case 0: return cob.getId();
            case 1: return cob.getTipo();
            case 2: return cob.getDescripcion();
            default: return null;
        }
    }

    public Cobertura getCobertura(int fila) { return lista.get(fila); }

    public void llenar(List<Cobertura> datos) {
        this.lista = datos;
        fireTableDataChanged();
    }
}
