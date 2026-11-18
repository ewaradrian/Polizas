package polizas.General;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Clase base encargada de gestionar la conexión JDBC hacia MySQL.
 * Todos los modelos DAO heredan de esta clase (extends Conexion).
 */
public class Conexion {
    // Parámetros centralizados de conexión a la BD gestor_polizas
    private static final String BD = "gestor_polizas";
    private static final String URL = "jdbc:mysql://localhost:3306/" + BD
            + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Ajusta tu contraseña de MySQL local

    public Connection cnx;
    public PreparedStatement ps;
    public ResultSet rs;

    /**
     * Abre la conexión con MySQL.
     */
    public void conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cnx = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Falta agregar el archivo mysql-connector-j.jar en Libraries: " + e.getMessage());
            JOptionPane.showMessageDialog(null,
                    "No se encontró el driver JDBC de MySQL (.jar).\nAgrégalo en Libraries del proyecto.",
                    "Driver JDBC Faltante", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            System.err.println("Error de conexión SQL: " + e.getMessage());
        }
    }

    /**
     * Método de prueba rápida para verificar conectividad desde la interfaz (Fase 3).
     */
    public boolean probarConexion() {
        conectar();
        try {
            if (cnx != null && !cnx.isClosed()) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar conexión: " + e.getMessage());
        } finally {
            close();
        }
        return false;
    }

    /**
     * Cierra ResultSet, PreparedStatement y Connection de forma segura.
     */
    public void close() {
        try {
            if (rs != null && !rs.isClosed()) rs.close();
            if (ps != null && !ps.isClosed()) ps.close();
            if (cnx != null && !cnx.isClosed()) cnx.close();
        } catch (SQLException ex) {
            System.err.println("Error al cerrar recursos JDBC: " + ex.getMessage());
        }
    }
}
