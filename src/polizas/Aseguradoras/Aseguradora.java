package polizas.Aseguradoras;

public class Aseguradora {
    protected int id;
    protected String nombre;
    protected String telefonoContacto;

    public Aseguradora(int id, String nombre, String telefonoContacto) {
        this.id = id;
        this.nombre = nombre;
        this.telefonoContacto = telefonoContacto;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTelefonoContacto() { return telefonoContacto; }
    public void setTelefonoContacto(String telefonoContacto) { this.telefonoContacto = telefonoContacto; }
}
