package org.example.aplication;

import org.example.aplication.service.PacienteService;
import org.example.aplication.service.PacienteServiceImpl;
import org.example.domain.Paciente;
import org.example.infraestructure.repository.PacienteRepositoryImpl;
import org.example.interfaces.PacienteRepository;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final PacienteService pacienteService;

    static {

        PacienteRepository productoRepository = new PacienteRepositoryImpl() {
        };
        pacienteService = new PacienteServiceImpl(productoRepository);
    }



public static void main(String[] args) {





        boolean salir = false;
        while (!salir) {
            System.out.println("1. Listar de pacientes");
            System.out.println("2. Crear paciente");
            System.out.println("3. Actualizar paciente");
            System.out.println("4. Eliminar paciente");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    listarpacientes();
                    break;
                case 2:
                    crearpaciente();
                    break;
                case 3:
                    actualizarPaciente();
                    break;
                case 4:
                    eliminarPaciente();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private static void listarpacientes() {
        List<Paciente> pacientes = PacienteService.findAll();
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacinetes registrados.");
        } else {
            System.out.println("Listado de pacientes:");
            for (Paciente paciente : pacientes) {
                System.out.println(paciente);
            }
        }
    }

    private static void crearpaciente() {
        System.out.print("Ingrese el còdigo del paciente: ");
        int cod  = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el nombre del paciente: ");
        String nombre = scanner.nextLine();
        scanner.nextLine();

        Paciente paciente = new Paciente();
        paciente.setId(cod);
        paciente.setNombre(nombre);


        try {
            PacienteService.save(paciente);
            System.out.println("Paciente creado");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void actualizarPaciente() {
        System.out.print("Ingrese el ID del paciente: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Paciente paciente = PacienteService.findById(id);
        if (paciente == null) {
            System.out.println("No se encontró el paciente con ID " + id);
            return;
        }

        System.out.print("Ingrese el nuevo nombre del paciente (dejar en blanco para no cambiar): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) {
            paciente.setNombre(nombre);
        }



        try {
            PacienteService.update(paciente);
            System.out.println("Paciente actualizado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void eliminarPaciente() {
        System.out.print("Ingrese el ID del producto a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Paciente paciente = PacienteService.findById(id);
        if (paciente == null) {
            System.out.println("No se encontró el paciente con ID " + id);
            return;
        }

        PacienteService.delete(id);
        System.out.println("Paciente eliminado exitosamente.");
    }



    }

