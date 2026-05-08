package model;

import java.util.HashMap;
import java.util.Map;

public class Estadistica {
    private int totalJugadas;
    private int victorias;
    private int rachaActual;
    private int rachaMaxima;
    private Map<TipoApuesta, Integer> conteoApuestas;

    public Estadistica() {
        this.totalJugadas = 0;
        this.victorias = 0;
        this.rachaActual = 0;
        this.rachaMaxima = 0;
        this.conteoApuestas = new HashMap<>();
    }

    public void registrarResultado(Resultado r) {
        totalJugadas++;

        if (r.isGanar()) {
            victorias++;
            rachaActual++;
            if (rachaActual > rachaMaxima) {
                rachaMaxima = rachaActual;
            }
        } else {
            rachaActual = 0;
        }
        //freq
        conteoApuestas.put(r.getTipoApuesta(), conteoApuestas.getOrDefault(r.getTipoApuesta(), 0) + 1);
    }

    public int getTotalJugadas() {
        return totalJugadas;
    }

    public int getVictorias() {
        return victorias;
    }

    public int getRachaMaxima() {
        return rachaMaxima;
    }

    public double getPorcentajeVictorias() {
        if (totalJugadas == 0) return 0.0;
        return ((double) victorias / totalJugadas) * 100;
    }

    public TipoApuesta getTipoMasJugado() {
        TipoApuesta favorito = null;
        int max = 0;
        for (Map.Entry<TipoApuesta, Integer> entry : conteoApuestas.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                favorito = entry.getKey();
            }
        }
        return favorito;
    }

    public void reiniciarEstadisticas() {
        this.totalJugadas = 0;
        this.victorias = 0;
        this.rachaActual = 0;
        this.rachaMaxima = 0;
        this.conteoApuestas.clear();
    }
}