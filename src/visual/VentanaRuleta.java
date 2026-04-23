package visual;

import controller.SessionController;
import controller.RuletaController;
import model.Resultado;
import model.TipoApuesta;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRuleta {
    private SessionController session;
    private RuletaController ruleta;

    private JFrame frame;
    private JLabel lblSaldo;
    private JTextField txtMonto;
    private JComboBox<String> cboTipoApuesta;
    private JButton btnApostar;
    private JButton btnRecargar;
    private JButton btnVolver;

    public VentanaRuleta(SessionController session) {
        this.session = session;
        this.ruleta = new RuletaController();

        frame new JFrame("Ruleta / Jugador:" + session.getNombreUsuario());
        lblSaldo = new JLabel();
        txtMonto = new JTextField(10);

        String[] opciones = {"ROJO", "NEGRO", "PAR", "IMPAR"};
        cboTipoApuesta = new JComboBox<>(opciones);
        btnApostar = new JButton("Apostar");
        btnRecargar = new JButton("Recargar");
        btnVolver = new JButton("Volver");

        configurarVentana();
        refrescarSaldo();
    }
}
