package polizas.Pagos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import polizas.General.Conexion;
import polizas.General.DAO;

public class PagosModelo extends Conexion implements DAO {

    @Override
    public List<Pago> read() {
        List<Pago> lista = new ArrayList<>();
        String sql = "SELECT pg.id, pg.idPoliza, pg.fechaPago, pg.monto, pg.metodoPago, p.numeroPoliza "
                + "FROM PAGO pg "
                + "INNER JOIN POLIZA p ON pg.idPoliza = p.id "
                + "ORDER BY pg.id ASC";
        try {
            this.conectar();
            ps = cnx.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pago pg = new Pago(
                        rs.getInt("id"),
                        rs.getInt("idPoliza"),
                        rs.getDate("fechaPago"),
                        rs.getBigDecimal("monto"),
                        rs.getString("metodoPago")
                );
                pg.setNumeroPoliza(rs.getString("numeroPoliza"));
                lista.add(pg);
            }
        } catch (SQLException ex) {
            System.err.println("Error al leer PAGO: " + ex.getMessage());
        } finally {
            this.close();
        }
        return lista;
    }

    @Override
    public int create(Object obj) {
        try {
            this.conectar();
            Pago pg = (Pago) obj;
            ps = cnx.prepareStatement("INSERT INTO PAGO (idPoliza, fechaPago, monto, metodoPago) VALUES (?, ?, ?, ?)");
            ps.setInt(1, pg.getIdPoliza());
            ps.setDate(2, pg.getFechaPago());
            ps.setBigDecimal(3, pg.getMonto());
            ps.setString(4, pg.getMetodoPago());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al insertar PAGO: " + ex.getMessage());
        } finally {
            this.close();
        }
        return 0;
    }

    @Override
    public int update(Object obj) {
        try {
            this.conectar();
            Pago pg = (Pago) obj;
            ps = cnx.prepareStatement("UPDATE PAGO SET idPoliza=?, fechaPago=?, monto=?, metodoPago=? WHERE id=?");
            ps.setInt(1, pg.getIdPoliza());
            ps.setDate(2, pg.getFechaPago());
            ps.setBigDecimal(3, pg.getMonto());
            ps.setString(4, pg.getMetodoPago());
            ps.setInt(5, pg.getId());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al actualizar PAGO: " + ex.getMessage());
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
            ps = cnx.prepareStatement("DELETE FROM PAGO WHERE id = ?");
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al eliminar PAGO: " + ex.getMessage());
        } finally {
            this.close();
        }
        return 0;
    }
}
