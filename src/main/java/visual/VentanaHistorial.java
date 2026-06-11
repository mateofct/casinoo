package visual;

import controller.SessionController;
import model.Resultado;
import model.IRepositorioResultados;
import model.RepositorioArchivo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaHistorial {
    private SessionController session;
    private JFrame frame;
    private JTable tablaHistorial;
    private DefaultTableModel modeloTabla;
    private JButton btnVolver;

    public VentanaHistorial(SessionController session) {
        this.session = session;
        frame = new JFrame("Historial Global de Ruleta");
        btnVolver = new JButton("Volver");

        String[] columnas = {"Jugador", "Número", "Apuesta", "Monto", "Resultado"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaHistorial = new JTable(modeloTabla);

        configurarVentana();
        cargarDatos();
    }

    private void configurarVentana() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JScrollPane(tablaHistorial), BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        panelInferior.add(btnVolver);
        panel.add(panelInferior, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        btnVolver.addActionListener(e -> {
            frame.dispose();
            new VentanaMenu(session).mostrarMenu();
        });
    }

    private void cargarDatos() {
        IRepositorioResultados repo = new RepositorioArchivo();
        List<Resultado> historial = repo.obtenerTodos();

        for (Resultado r : historial) {
            String estado = r.isGanar() ? "Ganó" : "Perdió";
            Object[] fila = {
                    r.getNombreJugador(),
                    r.getNumeroGanador(),
                    r.getTipoApuesta(),
                    "$" + r.getMontoApuesta(),
                    estado
            };
            modeloTabla.addRow(fila);
        }
    }

    public void mostrar() {
        frame.setVisible(true);
    }
}