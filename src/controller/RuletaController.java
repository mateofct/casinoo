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

}
