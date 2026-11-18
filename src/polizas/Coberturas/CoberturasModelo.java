package polizas.Coberturas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import polizas.General.Conexion;
import polizas.General.DAO;

public class CoberturasModelo extends Conexion implements DAO {

    @Override
    public List<Cobertura> read() {
        List<Cobertura> lista = new ArrayList<>();
        try {
            this.conectar();
            ps = cnx.prepareStatement("SELECT id, tipo, descripcion FROM COBERTURA ORDER BY id ASC");
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Cobertura(rs.getInt("id"), rs.getString("tipo"), rs.getString("descripcion")));
            }
        } catch (SQLException ex) {
            System.err.println("Error al leer COBERTURA: " + ex.getMessage());
        } finally {
            this.close();
        }
        return lista;
    }

    @Override
    public int create(Object obj) {
        try {
            this.conectar();
            Cobertura c = (Cobertura) obj;
            ps = cnx.prepareStatement("INSERT INTO COBERTURA (tipo, descripcion) VALUES (?, ?)");
            ps.setString(1, c.getTipo());
            ps.setString(2, c.getDescripcion());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al insertar COBERTURA: " + ex.getMessage());
        } finally {
            this.close();
        }
        return 0;
    }

    @Override
    public int update(Object obj) {
        try {
            this.conectar();
            Cobertura c = (Cobertura) obj;
            ps = cnx.prepareStatement("UPDATE COBERTURA SET tipo=?, descripcion=? WHERE id=?");
            ps.setString(1, c.getTipo());
            ps.setString(2, c.getDescripcion());
            ps.setInt(3, c.getId());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al actualizar COBERTURA: " + ex.getMessage());
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
            ps = cnx.prepareStatement("DELETE FROM COBERTURA WHERE id = ?");
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al eliminar COBERTURA: " + ex.getMessage());
        } finally {
            this.close();
        }
        return 0;
    }
}
