package model;

import java.util.ArrayList;
import java.util.List;

public class RepositorioMemoria implements IRepositorioResultados {
    private List<Resultado> historial = new ArrayList<>();

    @Override
    public void registrar(Resultado resultado) {
        historial.add(resultado);
    }

    @Override
    public List<Resultado> obtenerTodos() {
        return new ArrayList<>(historial);
    }
}