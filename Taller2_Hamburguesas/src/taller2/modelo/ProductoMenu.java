package taller2.modelo;


public class ProductoMenu implements Producto {
	
	// ************************************************************************
	// Atributos
	// ************************************************************************

	/**
	* El nombre del producto. 
	*/
	private String nombre;
		
	/**
	* El precio base del producto. 
	*/
	private int precioBase;
	

    public ProductoMenu(String nombre, int precioBase) {
        this.nombre = nombre;
        this.precioBase = precioBase;
    }
    
    public String getNombre() {
    	return this.nombre;
    	
    }
    
    public int getPrecio() {
    	return this.precioBase;
    	
    }
    
    public String generarTextoFactura() {
        String formato = "\t%s %s $%s";
        int numero = 45 - getNombre().length() + Integer.toString(getPrecio()).length();
        String barras = "-";
        for (int j = 0; j < numero; j++) {
        	barras += "-";
        }
        String textoFactura = String.format(formato, getNombre(), barras, getPrecio());
		return textoFactura;
    }


}
