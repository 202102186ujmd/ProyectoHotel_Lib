package modelo.dao;

import java.util.ArrayList;
import java.util.List;
import modelo.dominio.operacionesBD;
import modelo.entidad.Cliente;
import modelo.entidad.Usuario;

/**
 * Universidad Dr. Jose Matias Delgado Catedra 7
 * Programacion de computadoras 4
 * @author Pedro Navarrete 
 *          202102186
 */
public class daoUsuario {
     private List<Usuario> lst;

    // Constructor que inicializa la lista de clientes
    public daoUsuario() {
        this.lst = new ArrayList();
    }

    public boolean validar(String us, String pw) {
        boolean r = false;
        String sql = "SELECT username FROM Usuario WHERE username='" + us + "'AND password=MD5('" + pw + "')";
        operacionesBD op = new operacionesBD();
        List<Object[]> lst = op.consultar(sql);
        if (lst.size() > 0) {
            r = true;
        }
        return r;
    }

    //Crear usuario
    public boolean crearUsuario(Usuario usuario) {
        boolean resultado = false;
        String sql = "INSERT INTO Usuario (username, password, typeuser) VALUES ('" + usuario.getUsername() + "', MD5('" + usuario.getPassword() + "'), " + usuario.getTypeuser() + ")";
        operacionesBD op = new operacionesBD();
        int filasAfectadas = op.ejecutar(sql);
        if (filasAfectadas > 0) {
            resultado = true;
        }
        return resultado;
    }

    //Editar usuario
    public boolean editarUsuario(Usuario usuario) {
        boolean resultado = false;
        String sql = "UPDATE Usuario SET password = MD5('" + usuario.getPassword() + "'), typeuser = " + usuario.getTypeuser() + " WHERE username = '" + usuario.getUsername() + "'";
        operacionesBD op = new operacionesBD();
        int filasAfectadas = op.ejecutar(sql);
        if (filasAfectadas > 0) {
            resultado = true;
        }
        return resultado;
    }

    //Eliminar usuario
    public boolean eliminarUsuario(String username) {
        boolean resultado = false;
        String sql = "DELETE FROM Usuario WHERE username = '" + username + "'";
        operacionesBD op = new operacionesBD();
        int filasAfectadas = op.ejecutar(sql);
        if (filasAfectadas > 0) {
            resultado = true;
        }
        return resultado;
    }
    
    // Método para obtener todos los usuarios de la base de datos
    public List<Usuario> getAll() {
        // Construir la consulta SQL para la consulta de la tabla
        String sql = "SELECT username, password, typeuser FROM Usuario";
        operacionesBD op = new operacionesBD();
        
        List<Object[]> lista = op.consultar(sql);
        this.lst = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            Usuario user = new Usuario();
            user.setUsername(lista.get(i)[0].toString());
            user.setPassword(lista.get(i)[1].toString());
            user.setTypeuser(Integer.parseInt(lista.get(i)[2].toString()));
            this.lst.add(user);
        }
        return this.lst;
    }

}
