package modelo.dao;

import java.util.ArrayList;
import java.util.List;
import modelo.dominio.operacionesBD;
import modelo.entidad.Cliente;

/**
 * Universidad Dr. Jose Matias Delgado Catedra Programacion de computadoras 4
 *
 * @author Pedro Navarrete 202102186
 */
public class daoCliente {

    private List<Cliente> lst;

    // Constructor que inicializa la lista de clientes
    public daoCliente() {
        this.lst = new ArrayList();
    }

    public List<Cliente> getAll() {
        String sql = "SELECT idcliente, nombre, apellido, email FROM Cliente";
        operacionesBD op = new operacionesBD();
        List<Object[]> resultados = op.consultar(sql);
        List<Cliente> lst = new ArrayList<>();

        for (Object[] fila : resultados) {
            try {
                Cliente cli = new Cliente();
                cli.setIdcliente((int) fila[0]);
                cli.setNombre((String) fila[1]);
                cli.setApellido((String) fila[2]);
                cli.setEmail((String) fila[3]);
                lst.add(cli);
            } catch (ClassCastException e) {
                System.out.println("Error al convertir tipos de datos: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error al procesar los datos: " + e.getMessage());
            }
        }
        return lst;
    }

    // Método para insertar un nuevo cliente en la base de datos
    public int insertarCliente(Cliente cli) {
        int resultado = 0;
        operacionesBD op = new operacionesBD();
        String sql = "INSERT INTO Cliente (nombre, apellido, email) VALUES ('" + cli.getNombre() + "', '" + cli.getApellido() + "', '" + cli.getEmail() + "')";
        return op.ejecutar(sql);
    }

    // Método para actualizar un cliente existente en la base de datos
    public int actualizarCliente(Cliente cli) {
        String sql = "UPDATE Cliente SET nombre='" + cli.getNombre() + "', apellido='" + cli.getApellido() + "', email='" + cli.getEmail() + "' WHERE idcliente=" + cli.getIdcliente();
        System.out.println("Ejecutando SQL: " + sql);
        operacionesBD op = new operacionesBD();
        return op.ejecutar(sql);
    }

    // Método para eliminar un cliente de la base de datos por su ID
    public int eliminarCliente(int id) {
        String sql = "DELETE FROM Cliente WHERE idcliente=" + id;
        operacionesBD op = new operacionesBD();
        return op.ejecutar(sql);
    }

    // Método para obtener un cliente por su ID
    public Cliente getClienteByID(int idCliente) {
        Cliente cliente = null;
        String sql = "SELECT idcliente, nombre, apellido, email FROM Cliente WHERE idcliente = " + idCliente;
        operacionesBD op = new operacionesBD();

        List<Object[]> resultados = op.consultar(sql);
        if (!resultados.isEmpty()) {
            Object[] fila = resultados.get(0);
            cliente = new Cliente();
            cliente.setIdcliente((int) fila[0]);
            cliente.setNombre((String) fila[1]);
            cliente.setApellido((String) fila[2]);
            cliente.setEmail((String) fila[3]);
        }

        return cliente;
    }
}
