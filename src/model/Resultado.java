package model;

public class Resultado {
    private int numeroGanador;
    private TipoApuesta tipoApuesta;
    private int montoApuesta;
    private boolean ganar;

    public Resultado(int numeroGanador, TipoApuesta tipoApuesta, int montoApuesta, boolean ganar) {
        this.numeroGanador = numeroGanador;
        this.tipoApuesta = tipoApuesta;
        this.montoApuesta = montoApuesta;
        this.ganar = ganar;
    }

    public int getNumeroGanador() {
        return numeroGanador;
    }

    public TipoApuesta getTipoApuesta() {
        return tipoApuesta;
    }

    public int getMontoApuesta() {
        return montoApuesta;
    }

    public boolean isGanar() {
        return ganar;
    }
}
