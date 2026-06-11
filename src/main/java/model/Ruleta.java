package model;

import java.util.Random;

public class Ruleta {
    private int saldo;
    private Random rng;
    private int[] numerosRojos = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};

    public Ruleta(int saldoInicio) {
        if (saldoInicio < 0) {
            throw new IllegalArgumentException("Saldo inicial inválido");
        }
        this.saldo = saldoInicio;
        this.rng = new Random();
    }

    public Ruleta() {
        this(0);
    }

    public int getSaldo() {
        return this.saldo;
    }

    public void deposito(int monto) {
        if (monto > 0) {
            this.saldo += monto;
            System.out.println("Depósito exitoso. Saldo actual: " + this.saldo);
        } else {
            System.out.println("Monto inválido para depósito.");
        }
    }

    public void retirar(int monto) {
        if (monto > 0 && this.saldo >= monto) {
            this.saldo -= monto;
        }
    }

    public int girarRuleta() {
        return rng.nextInt(37);
    }

    public boolean evaluarResultado(int numero, ApuestaBase apuesta) {
        if (numero == 0) return false;
        String color = esRojo(numero) ? "Rojo" : "Negro";
        return apuesta.acierta(numero, color);
    }

    private boolean esRojo(int n){
        for (int r: numerosRojos){
            if (r == n) return true;
        }
        return false;
    }
}
