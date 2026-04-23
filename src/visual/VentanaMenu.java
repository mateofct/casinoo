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
                abrirInicio();
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
    void abrirInicio() {
        frame.dispose();
        new VentanaMenu("Nombre").mostrarMenu();
    }
    void abrirJugar() {
        //frame.dispose();
        new Ruleta().mostrarMenu();
    }
    void abrirHistorial() {
        new Ruleta().mostrarEstadisticas();
        new VentanaMenu("Nombre").mostrarMenu();
    }
    void abrirSalir() {
        frame.dispose();
        return;
    }
}