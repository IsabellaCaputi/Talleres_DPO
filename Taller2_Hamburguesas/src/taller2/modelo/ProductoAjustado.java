package taller2.modelo;

import java.util.ArrayList;
import java.util.List;

public class ProductoAjustado implements Producto {
	
	/**
	* La lista de los ingredientes agregados a un producto ajustado. 
	*/
	private List<Ingrediente> agregados;

	/**
	* La lista de los ingredientes eliminados a un producto ajustado. 
	*/
	private List<Ingrediente> eliminados;
	
	/**
	* El producto base que va a ser ajustado en la orden. 
	*/
	private Producto base;
	
	private double precio;
	
	
	public ProductoAjustado(Producto productoSeleccionado)
	{
		this.base = productoSeleccionado;
		this.agregados = new ArrayList<>();
		this.eliminados = new ArrayList<>();
		int precio = base.getPrecio();
		
	}
	public void agregarAgregados(Ingrediente elIngrediente) {
        this.agregados.add(elIngrediente);
    }
	
	public void agregarEliminados(Ingrediente elIngrediente) {
        this.eliminados.add(elIngrediente);
    }

	public String getNombre() {		
		String nombre = base.getNombre();
		return nombre;
		
	}
	
	public int getPrecio() {
		int precio=0;
		for(int i=0; i<agregados.size();i++) {
			precio += agregados.get(i).getCostoAdicional();
			}
		return (int) precio;
		
	}
	
	public String generarTextoFactura() {
        int numero = 45 - getNombre().length() + Integer.toString(getPrecio()).length();
        String barras = "-";
        for (int j = 0; j < numero-1; j++) {
        	barras += "-";
        }
        String textoFactura = "";
        String formatoDescripcion = "\n\t\t - %s %s $%s";
        for (int i = 0; i < agregados.size(); i++) {
            Ingrediente ing = agregados.get(i);
            textoFactura += String.format(formatoDescripcion, "con " +ing.getNombre(), barras, ing.getCostoAdicional());
        }
        for (int i = 0; i < eliminados.size(); i++) {
            Ingrediente ing = eliminados.get(i);
            textoFactura += String.format(formatoDescripcion, "sin " + ing.getNombre(), barras, "$0");
        }
        return textoFactura;
    }
    
		
	}

