package polizas.Aseguradoras;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import polizas.General.Conexion;
import polizas.General.DAO;

public class AseguradorasModelo extends Conexion implements DAO {

    @Override
    public List<Aseguradora> read() {
        List<Aseguradora> lista = new ArrayList<>();
        try {
            this.conectar();
            ps = cnx.prepareStatement("SELECT id, nombre, telefonoContacto FROM ASEGURADORA ORDER BY id ASC");
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Aseguradora(rs.getInt("id"), rs.getString("nombre"), rs.getString("telefonoContacto")));
            }
        } catch (SQLException ex) {
            System.err.println("Error al leer ASEGURADORA: " + ex.getMessage());
        } finally {
            this.close();
        }
        return lista;
    }

    @Override
    public int create(Object obj) {
        try {
            this.conectar();
            Aseguradora a = (Aseguradora) obj;
            ps = cnx.prepareStatement("INSERT INTO ASEGURADORA (nombre, telefonoContacto) VALUES (?, ?)");
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getTelefonoContacto());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al insertar ASEGURADORA: " + ex.getMessage());
        } finally {
            this.close();
        }
        return 0;
    }

    @Override
    public int update(Object obj) {
        try {
            this.conectar();
            Aseguradora a = (Aseguradora) obj;
            ps = cnx.prepareStatement("UPDATE ASEGURADORA SET nombre=?, telefonoContacto=? WHERE id=?");
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getTelefonoContacto());
            ps.setInt(3, a.getId());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al actualizar ASEGURADORA: " + ex.getMessage());
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
            ps = cnx.prepareStatement("DELETE FROM ASEGURADORA WHERE id = ?");
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al eliminar ASEGURADORA: " + ex.getMessage());
        } finally {
            this.close();
        }
        return 0;
    }
}
