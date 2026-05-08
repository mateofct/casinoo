package controller;

import model.Resultado;
import model.Ruleta;
import model.TipoApuesta;

public class RuletaController {
    private Ruleta modeloRuleta;

    public RuletaController(){
        this.modeloRuleta = new Ruleta(500); //consultar cuanto tiene q ser el saldo inicial.
    }

    public int getSaldoActual(){
        return modeloRuleta.getSaldo();
    }

    public void recargarSaldo(int monto){
        modeloRuleta.deposito(monto);
    }

    public Resultado realizarApuesta(int monto, TipoApuesta tipo){
        if (monto <= 0) {
            throw new IllegalArgumentException("La apuesta no puede ser 0.");
        }
        if (monto > getSaldoActual()) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }

        modeloRuleta.retirar(monto);
        int numeroGanador = modeloRuleta.girarRuleta();
        boolean ganar = modeloRuleta.evaluarResultado(numeroGanador, tipo);
        if (ganar) {
            modeloRuleta.deposito(monto * 2);
        }

        return new Resultado(numeroGanador, tipo, monto, ganar);
    }
}
