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

        frame = new JFrame("Ruleta / Jugador:" + session.getNombreUsuario());
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

    private void configurarVentana() {
        JPanel panel = new JPanel();
        panel.add(lblSaldo);
        panel.add(new JLabel("Monto de apuesta:"));
        panel.add(txtMonto);
        panel.add(new JLabel("Tipo de apuesta:"));
        panel.add(cboTipoApuesta);
        panel.add(btnApostar);
        panel.add(btnRecargar);
        panel.add(btnVolver);

        frame.add(panel);
        frame.setSize(250,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        agregarEventos();
    }

    private void refrescarSaldo() {
        int saldo = ruleta.getSaldoActual();
        lblSaldo.setText("Saldo: " + saldo);
    }

    private void agregarEventos() {
        btnApostar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                apostar();
            }
        });

        btnRecargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                recargar();
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                volver();
            }
        });
    }

    private void apostar() {
        try{
            int monto = Integer.parseInt(txtMonto.getText());
            String seleccion = cboTipoApuesta.getSelectedItem().toString().toUpperCase();
            TipoApuesta tipo = TipoApuesta.valueOf(seleccion);

            Resultado res = ruleta.realizarApuesta(monto, tipo);
            String mensaje = "Numero ganador: " + res.getNumeroGanador() + "\n";
            if (res.isGanar()) {
                mensaje += "Ganaste";
            } else {
                mensaje += "Perdiste";
            }

            JOptionPane.showMessageDialog(null, mensaje);
            refrescarSaldo();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese un monto valido de apuesta");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void recargar() {
        try{
            String input = JOptionPane.showInputDialog("Ingrese monto de recarga:");
            if (input != null && !input.trim().isEmpty()) {
                int monto = Integer.parseInt(input);
                ruleta.recargarSaldo(monto);
                refrescarSaldo();
                JOptionPane.showMessageDialog(null, "Saldo recargado");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese un monto valido de recarga");
        }
    }

    private void volver() {
        frame.dispose();
        new VentanaMenu(session).mostrarMenu();
    }

    public void mostrar() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
