package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Usuario {
    private String username;
    private String password;
    private String nombre;

    private final List<Resultado> historial;

    public Usuario(String username, String password, String nombre) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.historial = new ArrayList<>();
    }

    public Usuario(){
        this.username = "invitado";
        this.password = "";
        this.nombre = "Invitado";
        this.historial = new ArrayList<>();
    }

    public boolean validarCredenciales(String u, String p) {
        return this.username.equals(u) && this.password.equals(p);
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        }
    }

    public void agregarResultado(Resultado r) {
        this.historial.add(r);
    }

    public List<Resultado> getHistorial() {
        return Collections.unmodifiableList(historial);
    }
}