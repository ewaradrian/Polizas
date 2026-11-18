package polizas.PolizasModulo;

import java.sql.Date;

public class Poliza {
    protected int id;
    protected int idCliente;
    protected int idVehiculo;
    protected int idAseguradora;
    protected int idCobertura;
    protected String numeroPoliza;
    protected Date fechaInicio;
    protected Date fechaFin;
    protected String estado;

    // Campos descriptivos vía INNER JOIN
    protected String clienteNombre;
    protected String vehiculoDesc;
    protected String aseguradoraNombre;
    protected String coberturaTipo;

    public Poliza(int id, int idCliente, int idVehiculo, int idAseguradora, int idCobertura,
                  String numeroPoliza, Date fechaInicio, Date fechaFin, String estado) {
        this.id = id;
        this.idCliente = idCliente;
        this.idVehiculo = idVehiculo;
        this.idAseguradora = idAseguradora;
        this.idCobertura = idCobertura;
        this.numeroPoliza = numeroPoliza;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return numeroPoliza + " [" + estado + "]";
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public int getIdVehiculo() { return idVehiculo; }
    public void setIdVehiculo(int idVehiculo) { this.idVehiculo = idVehiculo; }
    public int getIdAseguradora() { return idAseguradora; }
    public void setIdAseguradora(int idAseguradora) { this.idAseguradora = idAseguradora; }
    public int getIdCobertura() { return idCobertura; }
    public void setIdCobertura(int idCobertura) { this.idCobertura = idCobertura; }
    public String getNumeroPoliza() { return numeroPoliza; }
    public void setNumeroPoliza(String numeroPoliza) { this.numeroPoliza = numeroPoliza; }
    public Date getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }
    public Date getFechaFin() { return fechaFin; }
    public void setFechaFin(Date fechaFin) { this.fechaFin = fechaFin; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }
    public String getVehiculoDesc() { return vehiculoDesc; }
    public void setVehiculoDesc(String vehiculoDesc) { this.vehiculoDesc = vehiculoDesc; }
    public String getAseguradoraNombre() { return aseguradoraNombre; }
    public void setAseguradoraNombre(String aseguradoraNombre) { this.aseguradoraNombre = aseguradoraNombre; }
    public String getCoberturaTipo() { return coberturaTipo; }
    public void setCoberturaTipo(String coberturaTipo) { this.coberturaTipo = coberturaTipo; }
}
