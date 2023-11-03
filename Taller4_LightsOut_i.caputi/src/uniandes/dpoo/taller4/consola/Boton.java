package uniandes.dpoo.taller4.consola;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;


public class Boton extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel botonesPanel=new JPanel();
	//creamos los nombres de las opciones de los botones del panel
	private static String[] opciones = { "Reiniciar", "Nuevo","Cambiar Jugador", "Top-10" };
	private Juego elJuego;
	public Boton(Juego real) {
		botonesPanel.setLayout(new FlowLayout());
		botonesPanel.setBounds(10, 10, 20, 20);
		this.elJuego = real;
		JButton[] botones = new JButton[4];
		for (int i = 0; i < 4; i++) {
			botones[i] = new JButton(opciones[i]);
			botones[i].addActionListener(this);
			botonesPanel.add(botones[i], BorderLayout.EAST);
		}
		add(botonesPanel);
	}

	public void actionPerformed(ActionEvent evento) {
		String elegida = evento.getActionCommand();
		if (opciones[0].equals(elegida)) {
			elJuego.reiniciarJuego();
		} else if (opciones[1].equals(elegida)) {
			elJuego.juegoNuevo();
		} else if (opciones[2].equals(elegida)) {
			elJuego.cambiarJugador();
		} else if (opciones[3].equals(elegida)) {
			elJuego.top10();
		}
	}

}
