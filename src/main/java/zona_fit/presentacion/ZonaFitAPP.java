package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.Scanner;

public class ZonaFitAPP {
    public static void main(String[] args) {
        // Crear el menu
        // Actuar en relacion a la opcion seleccionada
        // Crear un bucle mientras el usuario no salga
        int opcionSeleccionada = -1;
        Scanner input = new Scanner(System.in);
        var clienteNegocio = new ClienteDAO();
        while(opcionSeleccionada != 6){
            mostrarMenu();
            opcionSeleccionada = Integer.parseInt(input.nextLine());
            realizarOpcionSeleccionada(opcionSeleccionada, clienteNegocio, input);
        }
    }
    private static void mostrarMenu(){
        System.out.print("""
                --- Bienvenido a ZONA FIT APP ---
                1. Listar clientes
                2. Buscar cliente por ID
                3. Registrar cliente
                4. Modificar cliente
                5. Eliminar cliente
                6. Salir
                
                Seleccione una opcion:""");
    }

    private static void realizarOpcionSeleccionada(int opcion, ClienteDAO negocio, Scanner input){
        switch (opcion){
            case 1 -> { // Listar clientes
                System.out.println("Aqui tienes la lista de clientes: ");
                negocio.listarClientes().forEach(System.out::println);
            }
            case 2 -> { // Buscar por ID
                System.out.print("Indique el ID del cliente: ");
                var idABuscar = Integer.parseInt(input.nextLine());
                var cliente = new Cliente(idABuscar);
                var encontrado = negocio.buscarClientePorId(cliente);
                if(encontrado){
                    System.out.println("Encontramos al cliente: " + cliente);
                }else{
                    System.out.println("No encontramos al cliente con ID: " + cliente.getId());
                }
            }
            case 3 ->{ // Registrar cliente
                System.out.println("--- Ingrese los datos del cliente ---");
                System.out.print("Nombre: ");
                var nombre = input.nextLine();
                System.out.print("Apellido: ");
                var apellido = input.nextLine();
                System.out.print("Membresia: ");
                var membresia = Integer.parseInt(input.nextLine());

                var cliente = new Cliente(membresia, nombre, apellido);
                var seRegistro = negocio.registrarCliente(cliente);
                if(seRegistro){
                    System.out.println("Cliente Registrado con exito!");
                }else{
                    System.out.println("No se pudo registrar el cliente");
                }
            }
            case 4 -> { // Modificar cliente
                System.out.println("--- Modificar Cliente ---");
                System.out.print("ID del cliente: ");
                var id = Integer.parseInt(input.nextLine());
                System.out.print("Nombre: ");
                var nombre = input.nextLine();
                System.out.print("Apellido: ");
                var apellido = input.nextLine();
                System.out.print("Membresia: ");
                var membresia = Integer.parseInt(input.nextLine());

                var cliente = new Cliente(id, membresia, nombre, apellido);
                var seModifico = negocio.modificarCliente(cliente);
                if(seModifico){
                    System.out.println("Se modifico correctamente!");
                }else{
                    System.out.println("No se pudo modificar el cliente");
                }
            }
            case 5 -> { // Eliminar cliente
                System.out.println("--- Eliminar Cliente ---");
                System.out.print("Indique el ID del cliente: ");
                var id = Integer.parseInt(input.nextLine());
                var cliente = new Cliente(id);
                var seElimino = negocio.eliminarCliente(cliente);
                if(seElimino){
                    System.out.println("Se elimino correctamente!");
                }else{
                    System.out.println("No se pudo eliminar.");
                }
            }
            case 6 -> { // Salir del Sistema
                System.out.println("Saliendo del sistema...");
            }
            default -> System.out.println("La opcion " + opcion + " no es valida");
        }
        System.out.print("\n");
    }
}
