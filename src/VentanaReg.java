import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaReg {
    private JFrame frame;
    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private JTextField txtNombre;
    private JButton btnGuardar;

    public VentanaReg() {
        frame = new JFrame("Registro");
        txtUsuario = new JTextField(10);
        txtClave = new JPasswordField(10);
        txtNombre = new JTextField(10);
        btnGuardar = new JButton("Registrar");

        configurarVentana();
    }

    private void configurarVentana() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Usuario:")); panel.add(txtUsuario);
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

        if (u.equals("") || p.equals("") || n.equals("")) {
            JOptionPane.showMessageDialog(null, "Incompleto");
        } else {
            guardarUsuario(u, p, n);
        }
    }

    private void guardarUsuario(String u, String p, String n) {
        VentanaLogin.USUARIOS.add(new Usuario(u, p, n));
        JOptionPane.showMessageDialog(null, "Registrado!");
        frame.dispose();
        new VentanaLogin().mostrarVentana();
    }
}