package model;

import controller.RuletaController;
import controller.SessionController;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RuletaTest {

    // Caso 1: Constructor rechaza saldo negativo
    @Test
    public void testConstructorRechazaSaldoNegativo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Ruleta(-500);
        });
        assertEquals("Saldo inicial inválido", exception.getMessage());
    }

    // Caso 2: Depósito válido incrementa el saldo
    @Test
    public void testDepositoValidoIncrementaSaldo() {
        Ruleta ruleta = new Ruleta(1000);
        ruleta.deposito(500);
        assertEquals(1500, ruleta.getSaldo());
    }

    // Caso 3: Apuesta nula es rechazada
    @Test
    public void testApuestaNulaEsRechazada() {
        RuletaController controller = new RuletaController();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.realizarApuesta(null, "Jugador1");
        });
        assertEquals("Apuesta requerida", exception.getMessage());
    }

    // Caso 4: Apuesta con monto mayor al saldo
    @Test
    public void testApuestaMayorAlSaldo() {
        RuletaController controller = new RuletaController(); // Se inicializa con saldo de 500
        ApuestaBase apuesta = new ApuestaRojo(600);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.realizarApuesta(apuesta, "Jugador1");
        });
        assertEquals("Saldo insuficiente.", exception.getMessage());
    }

    // Caso 5: Estadísticas calculan racha y tipo más jugado ignorando nulos
    @Test
    public void testEstadisticasCalculanRachaYTipo() {
        IRepositorioResultados repoMemoria = new RepositorioMemoria();
        Estadistica est = new Estadistica(repoMemoria);

        Resultado r1 = new Resultado(14, "Rojo", 100, true, "Jugador1");
        Resultado r2 = new Resultado(12, "Rojo", 100, true, "Jugador1");
        Resultado r3 = new Resultado(2, "Negro", 100, false, "Jugador1");
        Resultado r4 = new Resultado(18, "Rojo", 100, true, "Jugador1");

        est.registrarResultado(r1);
        est.registrarResultado(r2);
        est.registrarResultado(null); // Validando la exclusión forzosa
        est.registrarResultado(r3);
        est.registrarResultado(r4);

        assertEquals(4, est.getTotalJugadas());
        assertEquals(3, est.getVictorias());
        assertEquals(2, est.getRachaMaxima());
        assertEquals(75.0, est.getPorcentajeVictorias(), 0.01);
        assertEquals("Rojo", est.getTipoMasJugado());
    }

    // Caso 6: Inicio de sesión con usuario no registrado
    @Test
    public void testInicioSesionUsuarioNoRegistrado() {
        SessionController session = new SessionController();
        boolean resultado = session.iniciarSesion("inexistente", "1234");
        assertFalse(resultado, "El sistema debe denegar el acceso a usuarios no registrados");
    }

    // Caso 7: Inicio de sesión con username nulo
    @Test
    public void testInicioSesionUsernameNulo() {
        SessionController session = new SessionController();
        boolean resultado = session.iniciarSesion(null, "1234");
        assertFalse(resultado, "El sistema debe rechazar el inicio de sesión con username null");
    }
}