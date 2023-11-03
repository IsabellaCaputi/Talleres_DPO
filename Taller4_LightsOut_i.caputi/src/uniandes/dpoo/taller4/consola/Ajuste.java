package uniandes.dpoo.taller4.consola;

import java.awt.Color;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Ajuste extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> tamanos;
	private ButtonGroup dificultades;

	public Ajuste() {
		setBackground(new Color(78, 179, 241));
		//Le ponemos titulo al boton de los tamanos del tablero
		JLabel tamano = new JLabel("Porfavor escoja el Tamano del Tablero:");
		add(tamano);
		//Creamos una lista desplegable de Strings
		tamanos = new JComboBox<String>();
		//Le agregamos los posibles tamanos del tablero de juego
		tamanos.setModel(new DefaultComboBoxModel<String>(new String[] { "5x5", "6x6", "7x7", "8x8", "9x9", "10x10" }));
		add(tamanos);
		//creamos un nuevo titulo para los botones de dificultad
		JLabel dificultad = new JLabel("Porfavor escoja la dificultad del juego:");
		add(dificultad);
		//creamos un grupo de botones para poner las posibles dificultades del juego
		String[] labels = { "Facil", "Medio", "Dificil"};
		JRadioButton[] groupedRadioButtons = new JRadioButton[labels.length];
		dificultades = new ButtonGroup();
		//agregamos cada boton de la lista
		for (int i = 0; i < labels.length; i++) {
			groupedRadioButtons[i] = new JRadioButton(labels[i]);
			groupedRadioButtons[i].setActionCommand(Integer.toString(i));
			dificultades.add(groupedRadioButtons[i]);
			add(groupedRadioButtons[i]);
		}
		groupedRadioButtons[0].setSelected(true);
	}
	
	public int getDificultad() {
		//devolvemos la seleccion de dificultad para saber el numero de iteraciones aleatorias que hara el tablero en modelo
		int seleccion = Integer.parseInt(dificultades.getSelection().getActionCommand()) + 1;
		return seleccion*50;
	}

	public int getTamano() {
		//devolvemos el tamano del tablero real
		int tamanoReal = tamanos.getSelectedIndex() + 5;
		return tamanoReal;
	}


}