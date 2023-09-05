package taller2.modelo.modificado;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RestauranteModificado {

	/**
	* La lista con los combos del restaurante. 
	*/
	private List<Combo> combos;

	/**
	* La cola de los pedidos. 
	*/
	private Queue<Pedido> pedidos;
	
	/**
	* La lista de los ingredientes disponibles. 
	*/
	private List<Ingrediente> ingredientes;
	
	/**
	* La lista de los productos disponibles para la venta. 
	*/
	private List<ProductoMenu> menuBase;
	
	private List<ProductoMenu> bebidas;
	

    public RestauranteModificado() {
        this.menuBase = new ArrayList<>();
        this.combos = new ArrayList<>();
        this.pedidos = new LinkedList<>();
        this.ingredientes = new ArrayList<>();
        this.bebidas = new ArrayList<>();
    }

    public Pedido iniciarPedido(String nombreCliente, String direccionCliente) {
        Pedido Nuevopedido = new Pedido(nombreCliente, direccionCliente);
        pedidos.add(Nuevopedido);
        return Nuevopedido;
    }

    
   public void finalizarPedido() throws IOException{
   Pedido pedido = pedidos.remove();
   File facturas = new File("data/facturas");
   if (!facturas.exists()) facturas.mkdirs();
   pedido.guardarFactura(new File("data/facturas/pedido_modificado_" + pedido.getIdPedido() + ".txt"));
}

   public Pedido getPedidoEnCurso()
   {
	   return this.pedidos.peek();
   }
   
   public List<Producto> getMenuBase()
   {
	   List<Producto> menuFinal = new LinkedList<>();
	   this.menuBase.sort((producto1,producto2) -> Integer.compare(producto1.getPrecio(),producto2.getPrecio()));
	   this.combos.sort((combo1,combo2) -> Integer.compare(combo1.getPrecio(),combo2.getPrecio()));
	   this.bebidas.sort((bebida1,bebida2) -> Integer.compare(bebida1.getPrecio(),bebida2.getPrecio()));
	   menuFinal.addAll(this.menuBase);
	   menuFinal.addAll(this.combos);
	   menuFinal.addAll(this.bebidas);
	   return menuFinal;
   }
   
   public List<Ingrediente> getIngredientes()
   {
	   return this.ingredientes;
   }
   
   public void cargarInformacion(File archivoIngredientes, File ArchivoCalorias, File ArchivoCombos,  File ArchivoBebidas) throws IOException
   {
	   cargarIngrediente(archivoIngredientes);
	   cargarMenu(ArchivoCalorias);
	   cargarBebidas(ArchivoBebidas);
	   cargarCombos(ArchivoCombos);
   }
   
   

private void cargarIngrediente(File archivoIngredientes) throws IOException
   {
	   BufferedReader bufferedReader = new BufferedReader(new FileReader(archivoIngredientes));
	   String linea= bufferedReader.readLine();
	   linea= bufferedReader.readLine();
	   while (linea != null) {
		   String[] info = linea.split(";");
		   Ingrediente nuevoIngrediente = new Ingrediente(info[0], Integer.parseInt(info[1]));
		   this.ingredientes.add(nuevoIngrediente);
		   linea= bufferedReader.readLine();
		   }
	   bufferedReader.close();
	   }
  
   
   private void cargarMenu(File ArchivoCalorias) throws IOException
   {
	   BufferedReader bufferedReader = new BufferedReader(new FileReader(ArchivoCalorias));
	   String linea= bufferedReader.readLine();
	   while (linea != null) {
		   String[] info = linea.split(";");
		   ProductoMenu nuevoP = new ProductoMenu(info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2]));
		   this.menuBase.add(nuevoP);
		   linea= bufferedReader.readLine();
		   }
	   bufferedReader.close();
   }
	   
   public void cargarBebidas(File archivoBebidas) throws IOException
   {
	   BufferedReader bufferedReader = new BufferedReader(new FileReader(archivoBebidas));
	   String linea= bufferedReader.readLine();
	   while (linea != null) {
			String[] info = linea.split(";");
			ProductoMenu nuevoP = new ProductoMenu(info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2]));
			this.bebidas.add(nuevoP);
			linea= bufferedReader.readLine();
		}
	   bufferedReader.close();
   }
   
   private void cargarCombos(File archivoCombos) throws IOException
   {
	   BufferedReader bufferedReader = new BufferedReader(new FileReader(archivoCombos));
	   String linea= bufferedReader.readLine();
	   while (linea != null) {
		   String[] info = linea.split(";");
		   Combo nuevoCombo = new Combo(info[0], Double.parseDouble(info[1].split("%")[0])/100,Integer.parseInt(info[4].split("-")[1]));
		   for(int i = 2;i<info.length-1;i++) {
			   String Nproducto =info[i];
			   boolean encontrado=false;
			   int p=0;
			   ProductoMenu producto = null;
			   while(encontrado==false) {
				   if(this.menuBase.get(p).getNombre().equals(Nproducto)) {
					   producto= this.menuBase.get(p);
					   encontrado= true;
				   }
				   if(p<14 && this.bebidas.get(p).getNombre().equals(Nproducto)) {
					   producto= this.bebidas.get(p);
					   encontrado= true;
				   }
				   p++;
			   }
			   
			   while(encontrado==false) {
				   if(this.menuBase.get(p).getNombre().equals(info[p].split("-")[0])) {
					   producto= this.menuBase.get(p);
					   encontrado= true;
				   }
				   if(p<14 && this.bebidas.get(p).getNombre().equals(info[p].split("-")[0])) {
					   producto= this.bebidas.get(p);
					   encontrado= true;
				   }
				   p++;
			   }
			   nuevoCombo.agregarItemACombo(producto);
			   
		   }
		   this.combos.add(nuevoCombo);
		   linea= bufferedReader.readLine();
	   }
	   bufferedReader.close();
   }

}
