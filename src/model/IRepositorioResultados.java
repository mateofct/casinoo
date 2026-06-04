package model;

import java.util.List;

public interface IRepositorioResultados {
    void registrar(Resultado resultado);
    List<Resultado> obtenerTodos();
}