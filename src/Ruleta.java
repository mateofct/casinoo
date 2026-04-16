import java.util.Random;

public class Ruleta {
    private int saldo;
    private Random rng;
    private int[] numerosRojos = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};

    public Ruleta(int saldoInicio) {
        this.saldo = saldoInicio;
        this.rng = new Random();
    }

    public Ruleta() {
        this(0);
        this.rng = new Random();
    }

    public int getSaldo() {
        return this.saldo;
    }

    public void deposito(int monto) {
        if (monto > 0) {;
            this.saldo += monto;
            System.out.println("Depósito exitoso. Saldo actual: " + this.saldo);
        } else {
            System.out.println("Monto inválido para depósito.");
        }
    }

    public int girarRuleta() {
        return rng.nextInt(37);
    }

    public boolean evaluarResultado(int numero, TipoApuesta tipo) {
        if (numero == 0) return false;
        switch (tipo) {
            case ROJO:
                return esRojo(numero);
            case NEGRO:
                return !esRojo(numero);
            case PAR:
                return numero % 2 == 0;
            case IMPAR:
                return numero % 2 != 0;
            default:
                return false;
        }
    }

    public static void mostrarMenu() {
        System.out.println("CASINO BLACK CAT");
        System.out.println("1.- Iniciar Ronda");
        System.out.println("2.- Ver estadisticas");
        System.out.println("3.- Salir");
        System.out.print("Seleccionar ");
    }

    public static int leerOpcion(Scanner in) {
        while (!in.hasNextInt()) in.next();
        return in.nextInt();
    }

    public static void ejecutarOpcion(int opcion, Scanner in) {
        if (opcion == 1) {iniciarRonda(in);}
        else if (opcion == 2) {mostrarEstadisticas();}
        else if (opcion != 3) {System.out.println("Invalido");}
    }


    public static void iniciarRonda(Scanner in) {
        char tipo = leerTipoApuesta(in);
        System.out.print("Monto a apostar: ");
        int monto = in.nextInt();
        int numero = girarRuleta();
        boolean acierto = evaluarResultado(numero, tipo);
        registrarResultado(numero, monto, acierto);
        mostrarResultado(numero, tipo, monto, acierto);
    }

    public static char leerTipoApuesta(Scanner in) {
        System.out.print("Tipo (R: Rojo, N: Negro, P: Par, I: Impar): ");
        return in.next().toUpperCase().charAt(0);
    }


    public static boolean esRojo(int n) {
        for (int r : numerosRojos) if (r == n) return true;
        return false;
    }

    public static void registrarResultado(int n, int a, boolean ac) {
        if (historialSize < MAX_HISTORIAL) {
            historialNumeros[historialSize] = n;
            historialApuestas[historialSize] = a;
            historialAciertos[historialSize] = ac;
            historialSize++;
        }
    }

    public static void mostrarResultado(int n, char t, int m, boolean ac) {
        String res = ac ? "Ganaste" : "Perdiste";
        System.out.println("Numero: " + n + " | Apuesta: " + t + " | " + res);
    }

    public static void mostrarEstadisticas() {
        int total = 0, aciertos = 0;
        for (int i = 0; i < historialSize; i++) {
            total += historialApuestas[i];
            if (historialAciertos[i]) aciertos++;
        }
        System.out.println("Rondas: " + historialSize + " | Total apostado: " + total);
        System.out.println("Aciertos: " + aciertos);
    }
}
