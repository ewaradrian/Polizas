package polizas.General;

import java.util.List;

/**
 * Interfaz Data Access Object (Patrón DAO).
 * Define el contrato estándar CRUD que todos los modelos del sistema implementan.
 */
public interface DAO {
    int create(Object obj);   // Insertar registro (INSERT)
    List read();              // Consultar registros (SELECT)
    int update(Object obj);   // Actualizar registro (UPDATE)
    int delete(Object obj);   // Eliminar registro (DELETE)
}
