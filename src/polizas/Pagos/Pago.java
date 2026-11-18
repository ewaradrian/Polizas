package polizas.Pagos;

import java.math.BigDecimal;
import java.sql.Date;

public class Pago {
    protected int id;
    protected int idPoliza;
    protected Date fechaPago;
    protected BigDecimal monto;
    protected String metodoPago;

    // Campo descriptivo vía INNER JOIN
    protected String numeroPoliza;

    public Pago(int id, int idPoliza, Date fechaPago, BigDecimal monto, String metodoPago) {
        this.id = id;
        this.idPoliza = idPoliza;
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.metodoPago = metodoPago;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdPoliza() { return idPoliza; }
    public void setIdPoliza(int idPoliza) { this.idPoliza = idPoliza; }
    public Date getFechaPago() { return fechaPago; }
    public void setFechaPago(Date fechaPago) { this.fechaPago = fechaPago; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
    public String getNumeroPoliza() { return numeroPoliza; }
    public void setNumeroPoliza(String numeroPoliza) { this.numeroPoliza = numeroPoliza; }
}
