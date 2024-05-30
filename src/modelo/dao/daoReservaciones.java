package modelo.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import modelo.dominio.operacionesBD;
import modelo.entidad.Reservaciones;

/**
 * Universidad Dr. Jose Matias Delgado
 * Catedra Programacion de computadoras 4
 * @author Pedro Navarrete
 *          202102186
 */
public class daoReservaciones {
    
    // Método para obtener todas las reservaciones de la base de datos
    public List<Reservaciones> getAll() {
        List<Reservaciones> lst = new ArrayList<>();
        String sql = "SELECT idreserva, idcliente, idhabitacion, fecha_inicio, fecha_fin, estado FROM reservaciones";
        
        operacionesBD op = new operacionesBD();
        List<Object[]> resultados = op.consultar(sql);
        
        for (Object[] fila : resultados) {
            Reservaciones reserva = new Reservaciones();
            reserva.setIdreserva((int) fila[0]);
            reserva.setIdcliente((int) fila[1]);
            reserva.setIdhabitacion((int) fila[2]);
            reserva.setFecha_inicio((Date) fila[3]);
            reserva.setFecha_fin((Date) fila[4]);
            reserva.setEstado((String) fila[5]);
            lst.add(reserva);
        }
        return lst;
    }
    
    // Método para insertar una nueva reservación en la base de datos
    public int insertarReservacion(Reservaciones reserva) {
        operacionesBD op = new operacionesBD();
        String sql = "INSERT INTO reservaciones (idcliente, idhabitacion, fecha_inicio, fecha_fin, estado) VALUES (" 
            + reserva.getIdcliente() + ", " 
            + reserva.getIdhabitacion() + ", '"
            + new java.sql.Date(reserva.getFecha_inicio().getTime()) + "', '"
            + new java.sql.Date(reserva.getFecha_fin().getTime()) + "', '"
            + reserva.getEstado() + "')";
        return op.ejecutar(sql);
    }
    
    // Método para actualizar una reservación existente en la base de datos
    public int actualizarReservacion(Reservaciones reserva) {
        String sql = "UPDATE reservaciones SET idcliente=" + reserva.getIdcliente() + 
            ", idhabitacion=" + reserva.getIdhabitacion() + 
            ", fecha_inicio='" + new java.sql.Date(reserva.getFecha_inicio().getTime()) + 
            "', fecha_fin='" + new java.sql.Date(reserva.getFecha_fin().getTime()) + 
            "', estado='" + reserva.getEstado() + 
            "' WHERE idreserva=" + reserva.getIdreserva();
        operacionesBD op = new operacionesBD();
        return op.ejecutar(sql);
    }
    
    // Método para eliminar una reservación de la base de datos por su ID
    public int eliminarReservacion(int id) {
        String sql = "DELETE FROM reservaciones WHERE idreserva=" + id;
        operacionesBD op = new operacionesBD();
        return op.ejecutar(sql);
    }

    // Método para obtener una reservación por su ID
    public Reservaciones getReservacionByID(int idReserva) {
        Reservaciones reserva = null;
        String sql = "SELECT idreserva, idcliente, idhabitacion, fecha_inicio, fecha_fin, estado FROM reservaciones WHERE idreserva = " + idReserva;
        operacionesBD op = new operacionesBD();
        
        List<Object[]> resultados = op.consultar(sql);
        if (!resultados.isEmpty()) {
            Object[] fila = resultados.get(0);
            reserva = new Reservaciones();
            reserva.setIdreserva((int) fila[0]);
            reserva.setIdcliente((int) fila[1]);
            reserva.setIdhabitacion((int) fila[2]);
            reserva.setFecha_inicio((Date) fila[3]);
            reserva.setFecha_fin((Date) fila[4]);
            reserva.setEstado((String) fila[5]);
        }
        
        return reserva;
    }
}
