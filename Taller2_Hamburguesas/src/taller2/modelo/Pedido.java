package taller2.modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class Pedido {
	// ************************************************************************
	// Atributos
	// ************************************************************************

	/**
	* La lista con los productos del pedido. 
	*/
	private List<Producto> itemsPedido;
	
	/**
	* El contador de los pedidos. 
	*/
	private static int numeroPedidos;
	
	/**
	* El numero del pedido actual. 
	*/
	private int idPedido;
	
	/**
	* Nombre del cliente. 
	*/
	private String nombreCliente;
	
	/**
	* Direccion del cliente. 
	*/
	private String direccionCliente;

    public Pedido(String elCliente, String laDireccion) {
        this.nombreCliente = elCliente;
        this.direccionCliente = laDireccion;
		this.itemsPedido = new ArrayList<>();
        this.idPedido = numeroPedidos;
        numeroPedidos += 1;
    }
    
    public int getIdPedido() {
    	return this.idPedido;
    }
    
    public String getNombre() {
    	return this.nombreCliente;
    }
    
    public String getDireccion() {
    	return this.direccionCliente;
    }
    
    public void agregarProducto(Producto nuevoitem) {
        this.itemsPedido.add(nuevoitem);
    }
    
    public List<Producto> listarItemsPedido() {
    	return itemsPedido;
    }
    
    private int getPrecioNetoPedido() {
    	double precioT= 0;
    	for(int i=0; i<itemsPedido.size(); i++) {
    		precioT += itemsPedido.get(i).getPrecio();
    	}
    	double IVA= precioT*0.19;
    	double precioN = precioT - IVA;
		return (int) precioN;
    	
    }
    
    private int getPrecioTotalPedido() {
    	double precioT= 0;
    	for(int i=0; i<itemsPedido.size(); i++) {
    		precioT += itemsPedido.get(i).getPrecio();
    	}
    	return (int) precioT;
    }
    
    
    private int getPrecioIVAPedido () {
    	double precioT= 0;
    	for(int i=0; i<itemsPedido.size(); i++) {
    		precioT += itemsPedido.get(i).getPrecio();
    	}
    	double IVA= precioT*0.19;
    	
    	return (int) IVA;
    }
    
    private String generarTextoFactura() {
    	return "";
    }
    
    public void guardarFactura(File archivo) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(archivo));
        bufferedWriter.write("Tienda de Hamburguesas");
        bufferedWriter.write("\nID Pedido: " + idPedido);
        bufferedWriter.write("\nCliente: " + nombreCliente);
        bufferedWriter.write("\nDirección: " + direccionCliente);
        bufferedWriter.write("\nResumen Factura:");
        for(int i = 0; i < itemsPedido.size(); i++) {
            Producto producto = itemsPedido.get(i);
            int numero = 45 - producto.getNombre().length() + Integer.toString(producto.getPrecio()).length();
            String barras = "-";
            for (int j = 0; j < numero-1; j++) {
            	barras += "-";
            }
            bufferedWriter.write("\n" + producto.generarTextoFactura());
        }
        bufferedWriter.write("\nVALOR NETO: $" + getPrecioNetoPedido());
        bufferedWriter.write("\nIVA: $" + getPrecioIVAPedido());
        bufferedWriter.write("\nTOTAL: $" + getPrecioTotalPedido());
        bufferedWriter.close();
    }
    }

