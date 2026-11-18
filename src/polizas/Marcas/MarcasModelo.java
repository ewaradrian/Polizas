package polizas.Marcas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import polizas.General.Conexion;
import polizas.General.DAO;

public class MarcasModelo extends Conexion implements DAO {

    @Override
    public List<Marca> read() {
        List<Marca> lista = new ArrayList<>();
        try {
            this.conectar();
            ps = cnx.prepareStatement("SELECT id, nombre FROM MARCA ORDER BY id ASC");
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Marca(rs.getInt("id"), rs.getString("nombre")));
            }
        } catch (SQLException ex) {
            System.err.println("Error al leer MARCA: " + ex.getMessage());
        } finally {
            this.close();
        }
        return lista;
    }

    @Override
    public int create(Object obj) {
        try {
            this.conectar();
            Marca marca = (Marca) obj;
            ps = cnx.prepareStatement("INSERT INTO MARCA (nombre) VALUES (?)");
            ps.setString(1, marca.getNombre());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al insertar MARCA: " + ex.getMessage());
        } finally {
            this.close();
        }
        return 0;
    }

    public boolean existeMarca(String nombre) {
        boolean existe = false;
        try {
            this.conectar();
            ps = cnx.prepareStatement("SELECT COUNT(*) FROM MARCA WHERE nombre = ?");
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                existe = true;
            }
        } catch (SQLException ex) {
            System.err.println("Error al validar duplicado en MARCA: " + ex.getMessage());
        } finally {
            this.close();
        }
        return existe;
    }

    @Override
    public int update(Object obj) {
        try {
            this.conectar();
            Marca marca = (Marca) obj;
            ps = cnx.prepareStatement("UPDATE MARCA SET nombre = ? WHERE id = ?");
            ps.setString(1, marca.getNombre());
            ps.setInt(2, marca.getId());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al actualizar MARCA: " + ex.getMessage());
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
            ps = cnx.prepareStatement("DELETE FROM MARCA WHERE id = ?");
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al eliminar MARCA (posible FK activa): " + ex.getMessage());
        } finally {
            this.close();
        }
        return 0;
    }
}
