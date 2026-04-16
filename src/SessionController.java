import java.util.List;
import java.util.ArrayList;

public class SessionController {
    private List<Usuario> usuariosRegistrados;
    private Usuario usuarioActual;
}

public SessionController() {
    this.usuarios = new ArrayList<>();
    this.usuariosRegistrados.add(new Usuario("admin", "1234", "Administrador"));
}

public void registrarUsuario(String u, String p, String n){
    if (u == null && p == null && n == null){
        throw new IllegalArgumentException("No se puede registrar un usuario.");
    }
    Usuario nuevoUsuario = new Usuario(u, p, n);
    this.usuariosRegistrados.add(nuevoUsuario);
    this.usuarioActual = nuevoUsuario;
}

public boolean iniciarSesion(String u, String p){
    for (Usuario user : usuariosRegistrados){
        if (user.validarCredenciales(u, p)){
            this.usuarioActual = user;
            return true;
        }
    return false;
    }
}

public boolean hayUsuario(){
    return usuarioActual != null;
}

public String getNombreUsuario(){
    return hayUsuario() ? usuarioActual.getNombre() : "";
}

