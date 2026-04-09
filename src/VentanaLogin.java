import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VentanaLogin {
    public static final List<User> USUARIOS = new ArrayList<>();

    private JFrame frame;
    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private JButton btnIngresar;
    private JButton btnRegistrar;

    public VentanaLogin() {
        frame = new JFrame("Casino Black Cat");
        txtUsuario = new JTextField(10);
        txtClave = new JPasswordField(10);
        btnIngresar = new JButton("Ingresar");
        btnRegistrar = new JButton("Registrar");

        inicializarUsuarios();
        configurarVentana();
    }

    private void inicializarUsuarios() {
        if (USUARIOS.isEmpty()) {
            User admin = new User("admin", "1234", "Administrador");
            USUARIOS.add(admin);
        }
    }

    private void configurarVentana() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Usuario:"));
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
        String nombre = validarCredenciales(u, p);

        if (nombre.equals("")) {
            JOptionPane.showMessageDialog(null, "Error de credenciales");
        } else {
            JOptionPane.showMessageDialog(null, "Iniciado" + nombre);
            frame.dispose();

            VentanaSaludo saludo = new VentanaSaludo(nombre);
            saludo.mostrar();
        }
    }

    private String validarCredenciales(String u, String p) {
        for (int i = 0; i < USUARIOS.size(); i++) {
            User actual = USUARIOS.get(i);
            if (actual.validarCredenciales(u, p)) {
                return actual.getNombre();
            }
        }
        return "";
    }

    void abrirRegistro() {
        frame.dispose();
        new VentanaReg().mostrar();
    }
}