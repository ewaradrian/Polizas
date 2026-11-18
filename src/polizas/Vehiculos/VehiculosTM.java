package polizas.Vehiculos;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class VehiculosTM extends AbstractTableModel {
    protected List<Vehiculo> lista = new ArrayList<>();
    protected String[] encabezados = {"ID", "Propietario (Cliente)", "Marca", "Modelo", "Año", "Placas", "Número de Serie"};

    @Override
    public String getColumnName(int col) { return encabezados[col]; }
    @Override
    public int getRowCount() { return lista.size(); }
    @Override
    public int getColumnCount() { return encabezados.length; }

    @Override
    public Object getValueAt(int r, int c) {
        Vehiculo v = lista.get(r);
        switch (c) {
            case 0: return v.getId();
            case 1: return v.getClienteNombre();
            case 2: return v.getMarcaNombre();
            case 3: return v.getModelo();
            case 4: return v.getAnio();
            case 5: return v.getPlacas();
            case 6: return v.getNumeroSerie();
            default: return null;
        }
    }

    public Vehiculo getVehiculo(int fila) { return lista.get(fila); }

    public void llenar(List<Vehiculo> datos) {
        this.lista = datos;
        fireTableDataChanged();
    }
}
