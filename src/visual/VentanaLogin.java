package visual;

import controller.SessionController;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaLogin {
    private SessionController session;
    private JFrame frame;
    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private JButton btnIngresar;
    private JButton btnRegistrar;

    public VentanaLogin(SessionController session) {
        this.session = session;
        frame = new JFrame("Casino Black Cat");
        txtUsuario = new JTextField(10);
        txtClave = new JPasswordField(10);
        btnIngresar = new JButton("Ingresar");
        btnRegistrar = new JButton("Registrar");

        configurarVentana();
    }

    private void configurarVentana() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("model.Usuario:"));
        panel.add(txtUsuario);
        panel.add(new JLabel("Clave:"));
        panel.add(txtClave);
        panel.add(btnIngresar);
        panel.add(btnRegistrar);

        frame.add(panel);
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        agregarEventos();
    }

    private void agregarEventos() {
        btnIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirRegistro();
            }
        });
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void login() {
        String u = txtUsuario.getText();
        String p = new String(txtClave.getPassword());
        boolean loginExitoso = session.iniciarSesion(u,p);

        if (!loginExitoso) {
            JOptionPane.showMessageDialog(null, "Error de credenciales");
        } else {
            String nombre = session.getNombreUsuario();
            JOptionPane.showMessageDialog(null, "Iniciado" + nombre);
            frame.dispose();

            VentanaMenu menu = new VentanaMenu(session);
            menu.mostrarMenu();
        }
    }

    void abrirRegistro() {
        frame.dispose();
        new VentanaReg(session).mostrar();
    }
}