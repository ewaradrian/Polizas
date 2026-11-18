package polizas.Clientes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import polizas.General.Conexion;
import polizas.General.DAO;

public class ClientesModelo extends Conexion implements DAO {

    @Override
    public List<Cliente> read() {
        List<Cliente> lista = new ArrayList<>();
        try {
            this.conectar();
            ps = cnx.prepareStatement("SELECT id, nombre, apellidos, telefono, email, rfc FROM CLIENTE ORDER BY id ASC");
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("rfc")
                ));
            }
        } catch (SQLException ex) {
            System.err.println("Error al leer CLIENTE: " + ex.getMessage());
        } finally {
            this.close();
        }
        return lista;
    }

    @Override
    public int create(Object obj) {
        try {
            this.conectar();
            Cliente c = (Cliente) obj;
            ps = cnx.prepareStatement("INSERT INTO CLIENTE (nombre, apellidos, telefono, email, rfc) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellidos());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getRfc().toUpperCase());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al insertar CLIENTE: " + ex.getMessage());
        } finally {
            this.close();
        }
        return 0;
    }

    @Override
    public int update(Object obj) {
        try {
            this.conectar();
            Cliente c = (Cliente) obj;
            ps = cnx.prepareStatement("UPDATE CLIENTE SET nombre=?, apellidos=?, telefono=?, email=?, rfc=? WHERE id=?");
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellidos());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getRfc().toUpperCase());
            ps.setInt(6, c.getId());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al actualizar CLIENTE: " + ex.getMessage());
        } finally {
            this.close();
        }
        return 0;
    }

    @Override
    public int delete(Object obj) {
        try {
            this.conectar();
            int id = (int) obj;
            ps = cnx.prepareStatement("DELETE FROM CLIENTE WHERE id = ?");
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al eliminar CLIENTE: " + ex.getMessage());
        } finally {
            this.close();
        }
        return 0;
    }
}
