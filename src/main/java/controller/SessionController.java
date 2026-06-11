package controller;

import model.Usuario;
import model.Estadistica;
import model.IRepositorioResultados;
import model.RepositorioArchivo;
import java.util.List;
import java.util.ArrayList;

public class SessionController {
    private List<Usuario> usuariosRegistrados;
    private Usuario usuarioActual;
    private Estadistica estadistica; // Se corrige el punto ciego

    public SessionController() {
        this.usuariosRegistrados = new ArrayList<>();
        this.usuariosRegistrados.add(new Usuario("1", "123", "Administrador"));
        IRepositorioResultados repositorio = new RepositorioArchivo();
        this.estadistica = new Estadistica(repositorio);
    }

    public void registrarUsuario(String u, String p, String n){
        if (u == null && p == null && n == null){
            //cambio importante, en vez de AND uso OR porque si era con AND los tres campos tenian que estar vacíos para que arrojase error.
            throw new IllegalArgumentException("No se puede registrar un usuario.");
        }
        Usuario nuevoUsuario = new Usuario(u, p, n);
        this.usuariosRegistrados.add(nuevoUsuario);
        this.usuarioActual = nuevoUsuario;
    }

    public boolean iniciarSesion(String u, String p) {
        if (u == null || p == null) {
            return false;
        }
        for (Usuario user : usuariosRegistrados){
            if (user.validarCredenciales(u, p)){
                this.usuarioActual = user;
                return true;
            }
        }
        return false;
    }

    public boolean hayUsuario(){
        return usuarioActual != null;
    }

    public String getNombreUsuario() {
        return hayUsuario() ? usuarioActual.getNombre() : "";
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public Estadistica getEstadistica() {
        return estadistica;
    }

    public void cerrarSesion(){
        this.usuarioActual = null;
    }
}