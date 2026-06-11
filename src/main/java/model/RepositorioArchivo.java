package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class RepositorioArchivo implements IRepositorioResultados {
    private static final String RUTA_ARCHIVO = "data/historial.csv";

    public RepositorioArchivo() {
        File directorio = new File("data");
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
    }

    @Override
    public void registrar(Resultado resultado) {
        String linea = resultado.getNombreJugador() + ";" +
                resultado.getNumeroGanador() + ";" +
                resultado.getTipoApuesta() + ";" +
                resultado.getMontoApuesta() + ";" +
                resultado.isGanar() + System.lineSeparator();
        try {
            Files.writeString(Paths.get(RUTA_ARCHIVO), linea, StandardOpenOption.CREATE, StandardOpenOption.APPEND, StandardOpenOption.SYNC);
        } catch (IOException e) {
            System.err.println("Error al guardar resultado: " + e.getMessage());
        }
    }

    @Override
    public List<Resultado> obtenerTodos() {
        List<Resultado> lista = new ArrayList<>();
        try {
            if (Files.exists(Paths.get(RUTA_ARCHIVO))) {
                List<String> lineas = Files.readAllLines(Paths.get(RUTA_ARCHIVO));
                for (String linea : lineas) {
                    if (linea.trim().isEmpty()) continue;
                    String[] partes = linea.split(";");
                    if (partes.length == 5) {
                        String nombre = partes[0];
                        int numero = Integer.parseInt(partes[1]);
                        String tipo = partes[2];
                        int monto = Integer.parseInt(partes[3]);
                        boolean ganar = Boolean.parseBoolean(partes[4]);

                        lista.add(new Resultado(numero, tipo, monto, ganar, nombre));
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al leer historial: " + e.getMessage());
        }
        return lista;
    }
}