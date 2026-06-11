package app;

import controller.SessionController;
import visual.VentanaLogin;

public class Launcher {
	public static void main(String[] args) {
		SessionController session = new SessionController();
		new VentanaLogin(session).mostrarVentana();
	}
}