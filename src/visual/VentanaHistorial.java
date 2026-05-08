package visual;

import controller.SessionController;
import controller.ResultadoController;
import model.Resultado;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaHistorial {
    private SessionController session;
    private ResultadoController resultadoController;
    private JFrame frame;
    private JTextArea txtHistorial;
    private JButton btnVolver;

    public VentanaHistorial(SessionController session) {
        this.session = session;
        this.resultadoController = new ResultadoController(session);

        frame = new JFrame("Historial de apuestas de " + session.getNombreUsuario());
        txtHistorial = new JTextArea(15, 30);
        txtHistorial.setEditable(false);
        btnVolver = new JButton("Volver");

        configurarVentana();
        cargarHistorial();
    }

    private void configurarVentana() {
        JPanel panel = new JPanel(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(txtHistorial);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnVolver);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnVolver.addActionListener(e -> volver());
    }

    private void cargarHistorial() {
        List<Resultado> historial = resultadoController.obtenerHistorial();

        if (historial.isEmpty()) {
            txtHistorial.setText("No hay apuestas registradas.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < historial.size(); i++) {
            Resultado r = historial.get(i);
            sb.append("Apuesta ").append(i + 1).append(":\n")
                    .append(" -Tipo: ").append(r.getTipoApuesta()).append("\n")
                    .append(" -Monto: $").append(r.getMontoApuesta()).append("\n")
                    .append(" -Número Ganador: ").append(r.getNumeroGanador()).append("\n")
                    .append(" -Resultado: ").append(r.isGanar() ? "GANADA" : "PERDIDA").append("\n")
                    .append(" -----------------\n");
        }
        txtHistorial.setText(sb.toString());
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