package uniandes.dpoo.taller4.consola;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Cuadricula extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tamano = 5;
	private Juego elJuego;
	private int tamcuadricula;

	public Cuadricula (Juego game, int i)  {
		this.tamano = i;
		this.elJuego = game;
		setPreferredSize(new Dimension(500, 500));
	}
	
	public void paint(Graphics gB) {
		Graphics2D g = (Graphics2D) gB;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 500, 500);
		tamcuadricula = 500 / tamano;
		for (int i = 0; i < tamano ; i++) {
			for (int j = 0; j < tamano; j++) {
				g.setColor(Color.BLACK);
				if (elJuego.prendido(i, j))
					g.setColor(Color.yellow);
				g.fillRect(i * tamcuadricula,j * tamcuadricula, tamcuadricula , tamcuadricula);
			}
		}
	}
	
	public void setTamano(int n) {
		this.tamano = n;
	}

	public int getTamano() {
		return this.tamano;
	}

}

