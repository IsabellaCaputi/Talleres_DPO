package taller2.modelo.modificado;

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
	private int calorias;
	

    public ProductoMenu(String nombre, int precioBase, int lasCalorias) {
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.calorias = lasCalorias;
    }
    
    public String getNombre() {
    	return this.nombre;
    	
    }
    
    public int getPrecio() {
    	return this.precioBase;
    	
    }
    
    public String generarTextoFactura() {
        String formato = "\t%s %s $%s";
        String formato2 = "\t%s %s %s calorias";
        int numero = 45 - getNombre().length() + Integer.toString(getPrecio()).length();
        String barras = "-";
        for (int j = 0; j < numero-1; j++) {
        	barras += "-";
        }
        String textoFactura = String.format(formato, getNombre(), barras, getPrecio())+";"+ String.format(formato2, getNombre(), barras, getCalorias());
		return textoFactura;
    }

	public int getCalorias() {
		return this.calorias;
	}
	


}
