package model;

public class Resultado {
    private int numeroGanador;
    private String tipoApuesta;
    private int montoApuesta;
    private boolean ganar;
    private String nombreJugador;

    public Resultado(int numeroGanador, String tipoApuesta, int montoApuesta, boolean ganar, String nombreJugador) {
        this.numeroGanador = numeroGanador;
        this.tipoApuesta = tipoApuesta;
        this.montoApuesta = montoApuesta;
        this.ganar = ganar;
        this.nombreJugador = nombreJugador;
    }

    public int getNumeroGanador() { return numeroGanador; }
    public String getTipoApuesta() { return tipoApuesta; }
    public int getMontoApuesta() { return montoApuesta; }
    public boolean isGanar() { return ganar; }
    public String getNombreJugador() { return nombreJugador; }
}