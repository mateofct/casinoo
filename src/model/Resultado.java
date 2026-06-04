package model;

public class Resultado {
    private int numeroGanador;
    private String tipoApuesta;
    private int montoApuesta;
    private boolean ganar;

    public Resultado(int numeroGanador, String tipoApuesta, int montoApuesta, boolean ganar) {
        this.numeroGanador = numeroGanador;
        this.tipoApuesta = tipoApuesta;
        this.montoApuesta = montoApuesta;
        this.ganar = ganar;
    }

    public int getNumeroGanador() {
        return numeroGanador;
    }

    public String getTipoApuesta() {
        return tipoApuesta;
    }

    public int getMontoApuesta() {
        return montoApuesta;
    }

    public boolean isGanar() {
        return ganar;
    }
}