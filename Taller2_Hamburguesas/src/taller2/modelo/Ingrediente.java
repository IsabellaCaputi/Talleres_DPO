package taller2.modelo;

public class Ingrediente {
	// ************************************************************************
	// Atributos
	// ************************************************************************

	/**
	* El nombre del ingrediente. 
	*/
	private String nombre;
		
	/**
	* El costo adicional del ingrediente. 
	*/
	private int costoAdicional;
	
    public Ingrediente(String elNombre, int elCostoAdicional) {
        this.nombre = elNombre;
        this.costoAdicional = elCostoAdicional;
    }
    
    public String getNombre()
    {
    	return this.nombre;
    }
    
    public int getCostoAdicional()
    {
    	return this.costoAdicional;
    }

}
