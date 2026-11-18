package polizas.Vehiculos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import polizas.General.Conexion;
import polizas.General.DAO;

public class VehiculosModelo extends Conexion implements DAO {

    @Override
    public List<Vehiculo> read() {
        List<Vehiculo> lista = new ArrayList<>();
        String sql = "SELECT v.id, v.idCliente, v.idMarca, v.modelo, v.anio, v.placas, v.numeroSerie, "
                + "CONCAT(c.nombre, ' ', c.apellidos) AS clienteNombre, m.nombre AS marcaNombre "
                + "FROM VEHICULO v "
                + "INNER JOIN CLIENTE c ON v.idCliente = c.id "
                + "INNER JOIN MARCA m ON v.idMarca = m.id "
                + "ORDER BY v.id ASC";
        try {
            this.conectar();
            ps = cnx.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vehiculo v = new Vehiculo(
                        rs.getInt("id"),
                        rs.getInt("idCliente"),
                        rs.getInt("idMarca"),
                        rs.getString("modelo"),
                        rs.getInt("anio"),
                        rs.getString("placas"),
                        rs.getString("numeroSerie")
                );
                v.setClienteNombre(rs.getString("clienteNombre"));
                v.setMarcaNombre(rs.getString("marcaNombre"));
                lista.add(v);
            }
        } catch (SQLException ex) {
            System.err.println("Error al leer VEHICULO: " + ex.getMessage());
        } finally {
            this.close();
        }
        return lista;
    }

    @Override
    public int create(Object obj) {
        try {
            this.conectar();
            Vehiculo v = (Vehiculo) obj;
            ps = cnx.prepareStatement("INSERT INTO VEHICULO (idCliente, idMarca, modelo, anio, placas, numeroSerie) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, v.getIdCliente());
            ps.setInt(2, v.getIdMarca());
            ps.setString(3, v.getModelo());
            ps.setInt(4, v.getAnio());
            ps.setString(5, v.getPlacas().toUpperCase());
            ps.setString(6, v.getNumeroSerie().toUpperCase());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al insertar VEHICULO: " + ex.getMessage());
        } finally {
            this.close();
        }
        return 0;
    }

    @Override
    public int update(Object obj) {
        try {
            this.conectar();
            Vehiculo v = (Vehiculo) obj;
            ps = cnx.prepareStatement("UPDATE VEHICULO SET idCliente=?, idMarca=?, modelo=?, anio=?, placas=?, numeroSerie=? WHERE id=?");
            ps.setInt(1, v.getIdCliente());
            ps.setInt(2, v.getIdMarca());
            ps.setString(3, v.getModelo());
            ps.setInt(4, v.getAnio());
            ps.setString(5, v.getPlacas().toUpperCase());
            ps.setString(6, v.getNumeroSerie().toUpperCase());
            ps.setInt(7, v.getId());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al actualizar VEHICULO: " + ex.getMessage());
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
            ps = cnx.prepareStatement("DELETE FROM VEHICULO WHERE id = ?");
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al eliminar VEHICULO: " + ex.getMessage());
        } finally {
            this.close();
        }
        return 0;
    }
}
