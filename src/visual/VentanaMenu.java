package visual;

import controller.SessionController;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaMenu {
    private SessionController session;
    private JFrame frame;
    private JButton btnInicio;
    private JButton btnJugar;
    private JButton btnHistorial;
    private JButton btnSalir;

    public VentanaMenu(SessionController session) {
        this.session = session;
        frame = new JFrame("Casino Black Cat");
        btnInicio = new JButton("Inicio");
        btnJugar = new JButton("Jugar");
        btnHistorial = new JButton("Historial");
        btnSalir = new JButton("Salir");

        configurarVentana();
    }

    private void configurarVentana() {
        JPanel panel = new JPanel();
        panel.add(btnInicio);
        panel.add(btnJugar);
        panel.add(btnHistorial);
        panel.add(btnSalir);

        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        agregarEventos();
    }

    private void agregarEventos() {
        btnInicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Iniciado");
            }
        });

        btnJugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirJugar();
            }
        });

        btnHistorial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirHistorial();
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirSalir();
            }
        });
    }

    public void mostrarMenu() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void abrirJugar() {
        frame.dispose();
        new VentanaRuleta(session).mostrar();
    }

    private void abrirHistorial() {
        JOptionPane.showMessageDialog(null, "Historial de apuestas");
    }

    private void abrirSalir() {
        cerrarSesion();
    }

    private void cerrarSesion() {
        session.cerrarSesion();
        frame.dispose();
        new VentanaLogin(session).mostrarVentana();
    }
}