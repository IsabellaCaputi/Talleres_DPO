package taller2.modelo;

import java.util.ArrayList;
import java.util.List;



public class Combo implements Producto {
	
	// ************************************************************************
	// Atributos
	// ************************************************************************

	/**
	* El descuento. 
	*/
	private double descuento;
		
	/**
	* El nombre del combo. 
	*/
	private String nombreCombo;
	
	/**
	* La lista que contiene los productos del combo. 
	*/
	private List<ProductoMenu> itemsCombo;

	public Combo(String elCombo, double elDescuento)
	{
		this.descuento = elDescuento;
		this.nombreCombo = elCombo;
		this.itemsCombo = new ArrayList<>();
	}
				
    public void agregarItemACombo(ProductoMenu producto) {
        this.itemsCombo.add(producto);
    }
    
    public int getPrecio() {
    	
    	double precioT= 0;
    	int items= itemsCombo.size();
    			
    	for(int i = 0; i<items; i++) {
    		precioT += itemsCombo.get(i).getPrecio()*(1-descuento);
    	}
    	/*System.out.println(precioT);*/
    	return (int) precioT;
    	
    }
    
    
    public String generarTextoFactura() {
        String formato = "\t%s %s $%s";
        int numero = 45 - getNombre().length() + Integer.toString(getPrecio()).length();
        String barras = "-";
        for (int j = 0; j < numero-1; j++) {
        	barras += "-";
        }
        String textoFactura = String.format(formato, getNombre(), barras, getPrecio());
        String formatoDescripcion = "\n\t\t - %s";
        for (int i = 0; i < itemsCombo.size(); i++) {
            Producto producto = itemsCombo.get(i);
            textoFactura += String.format(formatoDescripcion, producto.getNombre());
        }
        return textoFactura;
    }
    
    
    public String getNombre() {
    	return this.nombreCombo;
    }

}
