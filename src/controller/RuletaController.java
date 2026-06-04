package controller;

import model.Resultado;
import model.Ruleta;
import model.ApuestaBase;

public class RuletaController {
    private Ruleta modeloRuleta;

    public RuletaController(){
        this.modeloRuleta = new Ruleta(500);
    }

    public int getSaldoActual(){
        return modeloRuleta.getSaldo();
    }

    public void recargarSaldo(int monto){
        modeloRuleta.deposito(monto);
    }

    public Resultado realizarApuesta(ApuestaBase apuesta, String nombreJugador){
        int monto = apuesta.getMonto();

        if (monto <= 0) {
            throw new IllegalArgumentException("La apuesta no puede ser 0.");
        }
        if (monto > getSaldoActual()) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }

        modeloRuleta.retirar(monto);
        int numeroGanador = modeloRuleta.girarRuleta();

        boolean ganar = modeloRuleta.evaluarResultado(numeroGanador, apuesta);

        if (ganar) {
            modeloRuleta.deposito(monto * 2);
        }

        return new Resultado(numeroGanador, apuesta.getEtiqueta(), monto, ganar, nombreJugador);
    }
}