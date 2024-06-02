package modelo.dao;

import java.util.ArrayList;
import java.util.List;
import modelo.dominio.operacionesBD;
import modelo.entidad.Habitaciones;

/**
 * Universidad Dr. Jose Matias Delgado
 * Catedra Programacion de computadoras 4
 * @author Pedro Navarrete
 *          202102186
 */
public class daoHabitaciones {
    
    // Método para obtener todas las habitaciones de la base de datos
    public List<Habitaciones> getAll() {
        List<Habitaciones> lst = new ArrayList<>();
        String sql = "SELECT idhabitacion, typehabitacion, precioxnoche, estado FROM habitaciones";
        
        operacionesBD op = new operacionesBD();
        List<Object[]> resultados = op.consultar(sql);
        
        for (Object[] fila : resultados) {
            Habitaciones habitacion = new Habitaciones();
            habitacion.setIdhabitacion((int) fila[0]);
            habitacion.setTypehabitacion((String) fila[1]);
            habitacion.setPrecioxnoche((float) fila[2]);
            habitacion.setEstado((String) fila[3]);
            lst.add(habitacion);
        }
        return lst;
    }
    
    // Método para insertar una nueva habitación en la base de datos
    public int insertarHabitacion(Habitaciones habitacion) {
        operacionesBD op = new operacionesBD();
        String sql = "INSERT INTO habitaciones (typehabitacion, precioxnoche, estado) VALUES ('" 
            + habitacion.getTypehabitacion() + "', " 
            + habitacion.getPrecioxnoche() + ", '" 
            + habitacion.getEstado() + "')";
        return op.ejecutar(sql);
    }
    
    // Método para actualizar una habitación existente en la base de datos
    public int actualizarHabitacion(Habitaciones habitacion) {
        String sql = "UPDATE habitaciones SET typehabitacion='" 
            + habitacion.getTypehabitacion() + "', precioxnoche=" 
            + habitacion.getPrecioxnoche() + ", estado='" 
            + habitacion.getEstado() + "' WHERE idhabitacion=" 
            + habitacion.getIdhabitacion();
        operacionesBD op = new operacionesBD();
        return op.ejecutar(sql);
    }
    
    // Método para eliminar una habitación de la base de datos por su ID
    public int eliminarHabitacion(int id) {
        String sql = "DELETE FROM habitaciones WHERE idhabitacion=" + id;
        operacionesBD op = new operacionesBD();
        return op.ejecutar(sql);
    }

    // Método para obtener una habitación por su ID
    public Habitaciones getHabitacionByID(int idHabitacion) {
        Habitaciones habitacion = null;
        String sql = "SELECT idhabitacion, typehabitacion, precioxnoche, estado FROM habitaciones WHERE idhabitacion = " + idHabitacion;
        operacionesBD op = new operacionesBD();
        
        List<Object[]> resultados = op.consultar(sql);
        if (!resultados.isEmpty()) {
            Object[] fila = resultados.get(0);
            habitacion = new Habitaciones();
            habitacion.setIdhabitacion((int) fila[0]);
            habitacion.setTypehabitacion((String) fila[1]);
            habitacion.setPrecioxnoche((float) fila[2]);
            habitacion.setEstado((String) fila[3]);
        }
        
        return habitacion;
    }
}
