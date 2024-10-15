package org.example.aplication;

import org.example.aplication.service.JugueteService;
import org.example.aplication.service.JugueteServiceImpl;
import org.example.domain.juguete;
import org.example.infraestructure.repository.jugueteRepositoryImpl;
import org.example.interfaces.jugueteRepository;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final JugueteService productoService;

    static {
        jugueteRepository productoRepository = new jugueteRepositoryImpl();
        productoService = new JugueteServiceImpl(productoRepository);
    }

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Listar juguetes");
            System.out.println("2. Crear juguete");
            System.out.println("3. Actualizar juguete");
            System.out.println("4. Eliminar juguete");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    listarProductos();
                    break;
                case 2:
                    crearProducto();
                    break;
                case 3:
                    actualizarProducto();
                    break;
                case 4:
                    eliminarProducto();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private static void listarProductos() {
        List<juguete> productos = productoService.findAll();
        if (productos.isEmpty()) {
            System.out.println("No hay juguetes registrados.");
        } else {
            System.out.println("Listado de juguetes:");
            for (juguete producto : productos) {
                System.out.println(producto);
            }
        }
    }

    private static void crearProducto() {
        System.out.print("Ingrese el còdigo del juguete: ");
        int cod  = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el nombre del juguete: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el precio del juguete: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea

        juguete producto = new juguete();
        producto.setId(cod);
        producto.setNombre(nombre);
        producto.setPrecio(precio);

        try {
            productoService.save(producto);
            System.out.println("Juguete creado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void actualizarProducto() {
        System.out.print("Ingrese el ID del juguete a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        juguete producto = productoService.findById(id);
        if (producto == null) {
            System.out.println("No se encontró el juguete con ID " + id);
            return;
        }

        System.out.print("Ingrese el nuevo nombre del juguete (dejar en blanco para no cambiar): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) {
            producto.setNombre(nombre);
        }

        System.out.print("Ingrese el nuevo precio del Juguete(0 para no cambiar): ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea
        if (precio > 0) {
            producto.setPrecio(precio);
        }

        try {
            productoService.update(producto);
            System.out.println("Juguete actualizado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void eliminarProducto() {
        System.out.print("Ingrese el ID del Juguete a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        juguete producto = productoService.findById(id);
        if (producto == null) {
            System.out.println("No se encontró el juguete con ID " + id);
            return;
        }

        productoService.delete(id);
        System.out.println("juguete eliminado exitosamente.");
    }
}
