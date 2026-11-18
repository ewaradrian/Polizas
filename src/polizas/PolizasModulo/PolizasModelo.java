package polizas.PolizasModulo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import polizas.General.Conexion;
import polizas.General.DAO;

public class PolizasModelo extends Conexion implements DAO {

    @Override
    public List<Poliza> read() {
        List<Poliza> lista = new ArrayList<>();
        String sql = "SELECT p.*, CONCAT(c.nombre, ' ', c.apellidos) AS clienteNombre, "
                + "CONCAT(m.nombre, ' ', v.modelo, ' (', v.placas, ')') AS vehiculoDesc, "
                + "a.nombre AS aseguradoraNombre, cob.tipo AS coberturaTipo "
                + "FROM POLIZA p "
                + "INNER JOIN CLIENTE c ON p.idCliente = c.id "
                + "INNER JOIN VEHICULO v ON p.idVehiculo = v.id "
                + "INNER JOIN MARCA m ON v.idMarca = m.id "
                + "INNER JOIN ASEGURADORA a ON p.idAseguradora = a.id "
                + "INNER JOIN COBERTURA cob ON p.idCobertura = cob.id "
                + "ORDER BY p.id ASC";
        try {
            this.conectar();
            ps = cnx.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Poliza p = new Poliza(
                        rs.getInt("id"),
                        rs.getInt("idCliente"),
                        rs.getInt("idVehiculo"),
                        rs.getInt("idAseguradora"),
                        rs.getInt("idCobertura"),
                        rs.getString("numeroPoliza"),
                        rs.getDate("fechaInicio"),
                        rs.getDate("fechaFin"),
                        rs.getString("estado")
                );
                p.setClienteNombre(rs.getString("clienteNombre"));
                p.setVehiculoDesc(rs.getString("vehiculoDesc"));
                p.setAseguradoraNombre(rs.getString("aseguradoraNombre"));
                p.setCoberturaTipo(rs.getString("coberturaTipo"));
                lista.add(p);
            }
        } catch (SQLException ex) {
            System.err.println("Error al leer POLIZA: " + ex.getMessage());
        } finally {
            this.close();
        }
        return lista;
    }

    @Override
    public int create(Object obj) {
        try {
            this.conectar();
            Poliza p = (Poliza) obj;
            ps = cnx.prepareStatement("INSERT INTO POLIZA (idCliente, idVehiculo, idAseguradora, idCobertura, numeroPoliza, fechaInicio, fechaFin, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, p.getIdCliente());
            ps.setInt(2, p.getIdVehiculo());
            ps.setInt(3, p.getIdAseguradora());
            ps.setInt(4, p.getIdCobertura());
            ps.setString(5, p.getNumeroPoliza().toUpperCase());
            ps.setDate(6, p.getFechaInicio());
            ps.setDate(7, p.getFechaFin());
            ps.setString(8, p.getEstado());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al insertar POLIZA: " + ex.getMessage());
        } finally {
            this.close();
        }
        return 0;
    }

    @Override
    public int update(Object obj) {
        try {
            this.conectar();
            Poliza p = (Poliza) obj;
            ps = cnx.prepareStatement("UPDATE POLIZA SET idCliente=?, idVehiculo=?, idAseguradora=?, idCobertura=?, numeroPoliza=?, fechaInicio=?, fechaFin=?, estado=? WHERE id=?");
            ps.setInt(1, p.getIdCliente());
            ps.setInt(2, p.getIdVehiculo());
            ps.setInt(3, p.getIdAseguradora());
            ps.setInt(4, p.getIdCobertura());
            ps.setString(5, p.getNumeroPoliza().toUpperCase());
            ps.setDate(6, p.getFechaInicio());
            ps.setDate(7, p.getFechaFin());
            ps.setString(8, p.getEstado());
            ps.setInt(9, p.getId());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al actualizar POLIZA: " + ex.getMessage());
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
            ps = cnx.prepareStatement("DELETE FROM POLIZA WHERE id = ?");
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al eliminar POLIZA: " + ex.getMessage());
        } finally {
            this.close();
        }
        return 0;
    }
}
