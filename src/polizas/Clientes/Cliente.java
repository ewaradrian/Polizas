package polizas.Clientes;

public class Cliente {
    protected int id;
    protected String nombre;
    protected String apellidos;
    protected String telefono;
    protected String email;
    protected String rfc;

    public Cliente(int id, String nombre, String apellidos, String telefono, String email, String rfc) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.rfc = rfc;
    }

    @Override
    public String toString() {
        return nombre + " " + apellidos + " (" + rfc + ")";
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRfc() { return rfc; }
    public void setRfc(String rfc) { this.rfc = rfc; }
}
