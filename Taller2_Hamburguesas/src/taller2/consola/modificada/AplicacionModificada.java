package taller2.consola.modificada;
import taller2.modelo.modificado.*;

import java.io.*;
import java.util.List;


public class AplicacionModificada {
	private RestauranteModificado restaurante;
	private Pedido pedido;

	public static void main(String[] args) throws IOException {
		AplicacionModificada consola = new AplicacionModificada();
		System.out.println("Bienvenido a la tienda de hamburguesas");
		consola.ejecutarCargarArchivos();
		consola.ejecutarOpcion();
}
	public void mostrarMenu() {
		System.out.println("Menu de Opciones\r\n"
				+ "1. Mostrar el menú\r\n"
				+ "2. Iniciar un nuevo pedido\r\n"
				+ "3. Agregar un elemento a tu pedido\r\n"
				+ "4. Cerrar un pedido y guardar la factura\r\n"
				+ "5. Consultar la información de un pedido dado su id\r\n"
				+ "6. Salir de la Aplicacion");
	}

	public void ejecutarCargarArchivos() throws IOException {
        this.restaurante = new RestauranteModificado();
        this.restaurante.cargarInformacion(
                new File("./data/ingredientes.txt"),
                new File("./data/calorias.txt"),
                new File("./data/combos2.txt"), 
                new File("./data/bebidas.txt"));
	}
	
    public void ejecutarOpcion() throws IOException {
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcionSeleccionada = Integer.parseInt(input("Por favor seleccione una opción"));

            switch (opcionSeleccionada) {
                case 1:
                    this.ejecutarMostrarMenu();
                    break;
                case 2:
                	this.ejecutarIniciarPedido();
                    break;
                case 3:
                    if (pedido != null) {
                	this.modificarPedido();
                    }
                    else {
                        System.out.print("\n No puedes modificar tu pedido antes de iniciarlo.\r\n");
                    }
                    break;
                case 4:
                    if (pedido != null) {
                    	ejecutarFinalizarPedido();
                    } else {
                        System.out.print("\n No puedes finalizar tu pedido antes de iniciarlo.\r\n");
                    }
                    break;
                case 5:
                    if (pedido != null) {
                    	ConsultarPedido();
                    } else {
                        System.out.print("\n Debes finalizar tu pedido antes de poder consultarlo.\r\n");
                    }
                    break;
                case 6:
                    System.out.println("\nSaliendo de la aplicación...");
                    continuar = false;
                    break;
                default:
                    System.out.println("\nDebes seleccionar uno de los números de las opciones.\r\n");
                    break;
            }
        }
    }
    
    private void modificarPedido() {
        System.out.println("Se va a modificar el pedido con ID: " + pedido.getIdPedido());
        ejecutarMostrarMenu();
        int orden = Integer.parseInt(input("Ingresa cual producto que desea agregar"));
        List<Producto> menu = restaurante.getMenuBase();
        Producto productoSeleccionado = menu.get(orden - 1);
        pedido.agregarProducto(productoSeleccionado);
        System.out.println("Se añadió " + productoSeleccionado.getNombre() + " al pedido");
        int opcion = Integer.parseInt(input("Ingresa 1 si desea modificar los ingredientes de su producto actual, 0 de lo contrario"));
        boolean modificar=true;
        while(modificar==true) {
	        if(opcion==0) {
	        	modificar=false;
	        }
	        if (opcion == 1) {
	            ProductoAjustado ajustar = new ProductoAjustado(productoSeleccionado);
	            pedido.agregarProducto(ajustar);
	        	ejecutarMostrarIngredientes();
	            int opcion1 = Integer.parseInt(input("Ingrese 1 si desea agregar un ingrediente, 0 si desea eliminar un ingrediente"));
	            if (opcion1 == 1) {
		            int productoDeseado = Integer.parseInt(input("Ingrese el numero correspondiente al ingrediente que desea añadir"));
		            List<Ingrediente> ings = restaurante.getIngredientes();

		            Ingrediente ingredienteSeleccionado = ings.get(productoDeseado - 1);

		            ajustar.agregarAgregados(ingredienteSeleccionado);
		            System.out.println("Se añadió " + ingredienteSeleccionado.getNombre() + " a tu "+ productoSeleccionado.getNombre());
		            
		            int modif = Integer.parseInt(input("Desea seguir modificando su producto? 1: si, 0: no."));
		            if (modif==0) {
		            	modificar=false;
		            }
		        } else if (opcion1 == 0) {
		            int productoDeseado = Integer.parseInt(input("Ingrese el numero correspondiente al ingrediente que desea eliminar"));
		            List<Ingrediente> ings = restaurante.getIngredientes();

		            Ingrediente ingredienteSeleccionado = ings.get(productoDeseado - 1);

		            ajustar.agregarEliminados(ingredienteSeleccionado);
		            System.out.println("Se elimino " + ingredienteSeleccionado.getNombre() + " de tu "+ productoSeleccionado.getNombre());
		            int modif = Integer.parseInt(input("Desea seguir modificando su producto? 1: si, 0: no."));
		            if (modif==0) {
		            	modificar=false;
		            }
		        }
	        else {
	            System.out.println("\nPor favor ingresa una opción válida.");
	        }
	    }

    }
		
	}
	private void ejecutarFinalizarPedido() throws IOException {
		restaurante.finalizarPedido();
	}
	private void ConsultarPedido() {
        System.out.println("\nConsulta del pedido con ID ");
        int ID = Integer.parseInt(this.input("Ingrese el ID del pedido que desea consultar"));
        if (ID == pedido.getIdPedido()) {
            System.out.println("La información del pedido dado es: ");
            System.out.println("Nombre: " + pedido.getNombre());
            System.out.println("Dirección: " + pedido.getDireccion());
            System.out.println("Productos en el pedido actualmente: ");
            for (int i = 0; i < pedido.listarItemsPedido().size(); i++) {
            	imprimir(i + 1, pedido.listarItemsPedido().get(i).getNombre(), pedido.listarItemsPedido().get(i).getPrecio());
            }
        } else {
            System.out.println("\nPor favor ingresa una opción válida.");
        }
    }
		
	
	private void ejecutarMostrarIngredientes() {
        System.out.println("\n Modificar Ingredientes \n");
        for (int i = 0; i < restaurante.getIngredientes().size(); i++) {
        	imprimir(i + 1, restaurante.getIngredientes().get(i).getNombre(), restaurante.getIngredientes().get(i).getCostoAdicional());
        }
    }
	
	private void ejecutarMostrarMenu() {
        System.out.println("\nMenu del restaurante \n");
        for (int i = 0; i < restaurante.getMenuBase().size(); i++) {
        	imprimir(i + 1, restaurante.getMenuBase().get(i).getNombre(), restaurante.getMenuBase().get(i).getPrecio());
        	if(i==22) {
                System.out.println("\n        ---------------------Bebidas---------------------- \n");
        	}
        }
    }
    
    private void imprimir(int i, String nombre, int precio) {
        String formato = "\t%s. %s %s $%s";
        int numero = 45 - (Integer.toString(i).length() + nombre.length() + Integer.toString(precio).length());
        String barras = "";
        for (int j = 0; j < numero; j++) {
        	barras += "-";
        }
        System.out.println(String.format(formato, i, nombre, barras, precio));
		
	}
	private void ejecutarIniciarPedido() {
        String nombreCliente = input("Por favor ingresa tu nombre");
        String direccionCliente = input("Por favor ingresa la dirección de envío");
        pedido= restaurante.iniciarPedido(nombreCliente, direccionCliente);
        System.out.println("\nHola " + nombreCliente + ", el ID de tu pedido es: " + pedido.getIdPedido() + ".");
        System.out.println("Selecciona la opción 3 para agregar los productos que deseas del menu.");
		
	}
	public String input(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje + ": ");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                return (reader.readLine());
            } catch (IOException | NumberFormatException e) {
                System.out.println("\nPor favor ingresa una respuesta válida.");
            }
        }
    }

}

