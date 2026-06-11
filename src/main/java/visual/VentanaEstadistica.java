package visual;

import controller.SessionController;
import model.Estadistica;
import javax.swing.*;
import java.awt.*;

public class VentanaEstadistica {
    private SessionController session;
    private JFrame frame;
    private JTextArea txtDatos;
    private JButton btnVolver;

    public VentanaEstadistica(SessionController session) {
        this.session = session;
        frame = new JFrame("Estadísticas de " + session.getNombreUsuario());
        txtDatos = new JTextArea(10, 30);
        txtDatos.setEditable(false);
        btnVolver = new JButton("Volver");

        configurarVentana();
        cargarEstadisticas();
    }

    private void configurarVentana() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panel.add(new JScrollPane(txtDatos), BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        panelInferior.add(btnVolver);
        panel.add(panelInferior, BorderLayout.SOUTH);

        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        btnVolver.addActionListener(e -> {
            frame.dispose();
            new VentanaMenu(session).mostrarMenu();
        });
    }

    private void cargarEstadisticas() {
        Estadistica est = session.getEstadistica();

        String tipo = est.getTipoMasJugado();
        if (tipo == null || tipo.isEmpty()) {
            tipo = "Ninguno";
        }

        String info = "Estadísticas\n"
                + "Total de apuestas: " + est.getTotalJugadas() + "\n"
                + "Victorias: " + est.getVictorias() + "\n"
                + "Porcentaje de victorias: " + String.format("%.2f", est.getPorcentajeVictorias()) + "%\n"
                + "Racha de victorias: " + est.getRachaMaxima() + "\n"
                + "Apuesta favorita: " + tipo + "\n";

        txtDatos.setText(info);
    }

    public void mostrar() {
        frame.setVisible(true);
    }
}