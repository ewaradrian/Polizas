package polizas.PolizasModulo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PolizasTM extends AbstractTableModel {
    protected List<Poliza> lista = new ArrayList<>();
    protected String[] encabezados = {"ID", "Núm. Póliza", "Titular", "Vehículo", "Aseguradora", "Cobertura", "Inicio", "Fin", "Estado"};

    @Override
    public String getColumnName(int col) { return encabezados[col]; }
    @Override
    public int getRowCount() { return lista.size(); }
    @Override
    public int getColumnCount() { return encabezados.length; }

    @Override
    public Object getValueAt(int r, int c) {
        Poliza p = lista.get(r);
        switch (c) {
            case 0: return p.getId();
            case 1: return p.getNumeroPoliza();
            case 2: return p.getClienteNombre();
            case 3: return p.getVehiculoDesc();
            case 4: return p.getAseguradoraNombre();
            case 5: return p.getCoberturaTipo();
            case 6: return p.getFechaInicio();
            case 7: return p.getFechaFin();
            case 8: return p.getEstado();
            default: return null;
        }
    }

    public Poliza getPoliza(int fila) { return lista.get(fila); }

    public void llenar(List<Poliza> datos) {
        this.lista = datos;
        fireTableDataChanged();
    }
}
