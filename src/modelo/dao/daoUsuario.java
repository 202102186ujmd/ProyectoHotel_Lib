package modelo.dao;

import java.util.ArrayList;
import java.util.List;
import modelo.dominio.operacionesBD;
import modelo.entidad.Usuario;

public class daoUsuario {

    private List<Usuario> lst;

    // Constructor que inicializa la lista de usuarios
    public daoUsuario() {
        this.lst = new ArrayList<>();
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

    // Crear usuario
    public int insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario (username, password, typeuser) VALUES ('" + usuario.getUsername() + "', MD5('" + usuario.getPassword() + "'), " + usuario.getTypeuser() + ")";
        operacionesBD op = new operacionesBD();
        return op.ejecutar(sql);
    }

    // Editar usuario
    public int actualizarUsuario(String username, Usuario usuario) {
        String sql = "UPDATE Usuario SET password = MD5('" + usuario.getPassword() + "'), typeuser = " + usuario.getTypeuser() + " WHERE username = '" + username + "'";
        operacionesBD op = new operacionesBD();
        return op.ejecutar(sql);
    }

    // Eliminar usuario
    public int eliminarUsuario(String username) {
        String sql = "DELETE FROM Usuario WHERE username = '" + username + "'";
        operacionesBD op = new operacionesBD();
        return op.ejecutar(sql);
    }

    // Método para obtener todos los usuarios de la base de datos
    public List<Usuario> getAll() {
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
