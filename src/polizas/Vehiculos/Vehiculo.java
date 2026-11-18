package polizas.Vehiculos;

public class Vehiculo {
    protected int id;
    protected int idCliente;
    protected int idMarca;
    protected String modelo;
    protected int anio;
    protected String placas;
    protected String numeroSerie;

    // Campos descriptivos obtenidos mediante INNER JOIN para mostrar en JTable y JComboBox
    protected String clienteNombre;
    protected String marcaNombre;

    public Vehiculo(int id, int idCliente, int idMarca, String modelo, int anio, String placas, String numeroSerie) {
        this.id = id;
        this.idCliente = idCliente;
        this.idMarca = idMarca;
        this.modelo = modelo;
        this.anio = anio;
        this.placas = placas;
        this.numeroSerie = numeroSerie;
    }

    @Override
    public String toString() {
        String m = (marcaNombre != null) ? marcaNombre + " " : "";
        return m + modelo + " (" + placas + ")";
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public int getIdMarca() { return idMarca; }
    public void setIdMarca(int idMarca) { this.idMarca = idMarca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }
    public String getPlacas() { return placas; }
    public void setPlacas(String placas) { this.placas = placas; }
    public String getNumeroSerie() { return numeroSerie; }
    public void setNumeroSerie(String numeroSerie) { this.numeroSerie = numeroSerie; }
    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }
    public String getMarcaNombre() { return marcaNombre; }
    public void setMarcaNombre(String marcaNombre) { this.marcaNombre = marcaNombre; }
}
