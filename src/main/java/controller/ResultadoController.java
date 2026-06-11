package controller;

import model.Resultado;
import java.util.List;
import java.util.Collections;

public class ResultadoController {
    private SessionController session;

    public ResultadoController(SessionController session) {
        this.session = session;
    }

    public List<Resultado> obtenerHistorial() {
        if (session.hayUsuario()) {
            return session.getUsuarioActual().getHistorial();
        }
        return Collections.emptyList();
    }
}