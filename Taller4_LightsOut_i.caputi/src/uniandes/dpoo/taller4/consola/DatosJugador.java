package uniandes.dpoo.taller4.consola;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class DatosJugador extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private int jugadas = 0;

	public DatosJugador(String playerName) {
		this.nombre = playerName;
		JLabel[] keys = new JLabel[2];
		keys[0] = new JLabel("Jugadas");
		keys[1] = new JLabel("                  Jugador");
		JLabel[] values = new JLabel[2];
		values[0] = new JLabel(Integer.toString(jugadas));
		values[1] = new JLabel(nombre);
		add(keys[0]);
		add(values[0]);
		add(keys[1]);
		add(values[1]);
		
	}

	public void setJugadas(int i) {
		this.jugadas = i;
	}

	public void setPlayer(String name) {
		this.nombre = name;
	}

	public int getJugadas() {
		return this.jugadas;
	}
}
