package polizas.Pagos;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PagosTM extends AbstractTableModel {
    protected List<Pago> lista = new ArrayList<>();
    protected String[] encabezados = {"ID", "Póliza", "Fecha de Pago", "Monto ($)", "Método de Pago"};

    @Override
    public String getColumnName(int col) { return encabezados[col]; }
    @Override
    public int getRowCount() { return lista.size(); }
    @Override
    public int getColumnCount() { return encabezados.length; }

    @Override
    public Object getValueAt(int r, int c) {
        Pago pg = lista.get(r);
        switch (c) {
            case 0: return pg.getId();
            case 1: return pg.getNumeroPoliza();
            case 2: return pg.getFechaPago();
            case 3: return pg.getMonto();
            case 4: return pg.getMetodoPago();
            default: return null;
        }
    }

    public Pago getPago(int fila) { return lista.get(fila); }

    public void llenar(List<Pago> datos) {
        this.lista = datos;
        fireTableDataChanged();
    }
}
