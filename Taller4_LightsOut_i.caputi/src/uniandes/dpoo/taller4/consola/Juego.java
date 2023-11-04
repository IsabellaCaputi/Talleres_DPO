package uniandes.dpoo.taller4.consola;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import uniandes.dpoo.taller4.modelo.RegistroTop10;
import uniandes.dpoo.taller4.modelo.Tablero;
import uniandes.dpoo.taller4.modelo.Top10;

public class Juego extends JFrame {
	private static final long serialVersionUID = 1L;
	public JPanel interfaz;
	private String nombreJugador;
	private Cuadricula elTablero;
	private DatosJugador datos;
	private Boton buttons;
	private Ajuste arriba;
	private Top10 top10;
	private Tablero actual;
	private static File archivo = new File("data/top10.csv");
	private static Color color = new Color(78, 179, 241);

	public static void main(String[] args) {
		//creamos un nuevo juego
		new Juego();
	}
	
	public Juego() {
		//Le pedimos el nombre al usuario para comenzar el juego
		this.nombreJugador = getUser();
		//Creamos un nuevo panel para el juego
		interfaz = new JPanel();
		//Le damos el color azul al fondo
		interfaz.setBackground(color);
		//Creamos el layout para poder colocar los componentes en el panel
		interfaz.setLayout(new BorderLayout());
		setContentPane(interfaz);
		//Creamos el componente que contiene los tamanos del tablero y la dificultad
		arriba = new Ajuste();
		//lo agregamos a la parte de arriba del panel
		interfaz.add(arriba, BorderLayout.NORTH);
		//creamos los botones de opciones
		buttons = new Boton(this);
		//las ponemos al lado derecho del panel 
		interfaz.add(buttons,BorderLayout.EAST);
		//obtenemos los datos del jugador (nombre y numero de jugadas)
		datos = new DatosJugador(this.nombreJugador);
		add(datos, BorderLayout.SOUTH);
		//Creamos y agrregamos la cuadricula vacia
		elTablero = new Cuadricula(this, 1);
		interfaz.add(elTablero, BorderLayout.CENTER);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
	}

	private String getUser() {
		String nombre = JOptionPane.showInputDialog(null, "Porfavor ingrese su nombre", "Lights Out",JOptionPane.QUESTION_MESSAGE);
		return nombre;
	}

	public boolean prendido(int i, int j) {
		if (actual == null)
			return true;
		else {
			return actual.darTablero()[i][j];
		}
	}
	

	public void top10() {
		this.top10 = new Top10();
		top10.cargarRecords(archivo);
		JDialog Top10puntajes = new JDialog();
		Top10puntajes.setSize(100, 200);
		Top10puntajes.setTitle("Registro del 10");
		Collection<RegistroTop10> registro = top10.darRegistros();
		ArrayList<RegistroTop10> priorityList = new ArrayList<>(registro);
        // Crear el modelo de lista
        DefaultListModel<String> listModel = new DefaultListModel<>();
        int i=0;
        for (RegistroTop10 element : priorityList) 
        {
        	String texto = i+1+") "+element.toString();
            listModel.addElement(texto);
            i++;
        }
        // Crear el JList con el modelo de lista
        JList<String> jList = new JList<>(listModel);
        // Agregar el JList a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(jList);
        Top10puntajes.setForeground(new Color(255, 0, 0));
        Top10puntajes.add(scrollPane);
		Top10puntajes.setVisible(true);
	}


	
	public void cambiarJugador() {
		datos.setJugadas(0);
		String nuevoNombre= getUser();
		datos.setPlayer(nuevoNombre);
		juegoNuevo();
	}
	
	public void juegoNuevo() {
		int tamano = arriba.getTamano();
		int dificultad = arriba.getDificultad();
		actual = new Tablero(tamano);
		elTablero.setTamano(tamano);
		actual.desordenar(dificultad);
		elTablero.repaint();
	}

	public void reiniciarJuego() {
		actual.reiniciar();
		elTablero.repaint();
	}

}