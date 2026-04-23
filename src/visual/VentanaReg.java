package visual;

import controller.SessionController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaReg {
    private SessionController session;
    private JFrame frame;
    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private JTextField txtNombre;
    private JButton btnGuardar;

    public VentanaReg(SessionController session) {
        this.session = session;
        frame = new JFrame("Registro");
        txtUsuario = new JTextField(10);
        txtClave = new JPasswordField(10);
        txtNombre = new JTextField(10);
        btnGuardar = new JButton("Registrar");

        configurarVentana();
    }

    private void configurarVentana() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("model.Usuario:")); panel.add(txtUsuario);
        panel.add(new JLabel("Clave:")); panel.add(txtClave);
        panel.add(new JLabel("Nombre:")); panel.add(txtNombre);
        panel.add(btnGuardar);

        frame.add(panel);
        frame.setSize(250, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        agregarEventos();
    }

    private void agregarEventos() {
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrar();
            }
        });
    }

    public void mostrar() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void registrar() {
        String u = txtUsuario.getText();
        String p = new String(txtClave.getPassword());
        String n = txtNombre.getText();

        if (u.isEmpty() || p.isEmpty() || n.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Ingrese todos los campos");
        } else {
            try {
                session.registrarUsuario(u,p,n);
                JOptionPane.showMessageDialog(null, "Usuario registrado correctamente");
                frame.dispose();
                new VentanaLogin(session).mostrarVentana();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
    }
