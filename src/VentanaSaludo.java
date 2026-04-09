import javax.swing.*;

public class VentanaSaludo {
    private JFrame frame;
    private JLabel lblMensaje;

    public VentanaSaludo(String nombreUsuario) {
        frame = new JFrame("CASINO BLACK CAT");
        lblMensaje = new JLabel("BIENVENIDO " + nombreUsuario);
        configurarVentana();
    }

    private void configurarVentana() {
        JPanel panel = new JPanel();
        panel.add(lblMensaje);

        frame.add(panel);
        frame.setSize(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void mostrar() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}