import enums.TipoClase;
import enums.TipoEjercicio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            // Crear el gimnasio con listas vacías
            Gimnasio gimnasio = Gimnasio.builder()
                    .clases(new ArrayList<>())
                    .reservas(new ArrayList<>())
                    .clientes(new ArrayList<>())
                    .entrenadores(new ArrayList<>())
                    .usuarios(new ArrayList<>())
                    .build();

            // Crear entrenadores
            Entrenador entrenador1 = (Entrenador) gimnasio.crearEntrenador("Juan Pérez", "12345678", "Cardio");
            Entrenador entrenador2 = (Entrenador) gimnasio.crearEntrenador("Ana García", "87654321", "Pesas");

            // Agregar entrenadores al gimnasio
            gimnasio.getEntrenadores().add(entrenador1);
            gimnasio.getEntrenadores().add(entrenador2);

            // Crear clientes
            Cliente cliente1 = (Cliente) gimnasio.crearCliente("Carlos Martínez", "111222333", "Calle 123", "password123", "555-1234", "carlos@example.com");
            Cliente cliente2 = (Cliente) gimnasio.crearCliente("Lucía Gómez", "444555666", "Avenida 456", "password456", "555-5678", "lucia@example.com");
            Cliente cliente3 = (Cliente) gimnasio.crearCliente("Fernando Ramírez", "777888999", "Carrera 789", "password789", "555-9101", "fernando@example.com");

            // Agregar clientes al gimnasio
            gimnasio.getClientes().add(cliente1);
            gimnasio.getClientes().add(cliente2);
            gimnasio.getClientes().add(cliente3);

            // Agregar usuarios al gimnasio
            gimnasio.agregarUsuario(cliente1);
            gimnasio.agregarUsuario(cliente2);
            gimnasio.agregarUsuario(cliente3);
            gimnasio.agregarUsuario(entrenador1);
            gimnasio.agregarUsuario(entrenador2);

            // Crear una clase
            gimnasio.crearClase("CL001", "Yoga", LocalDateTime.of(2024, 9, 1, 9, 0), 20, TipoClase.YOGA, entrenador1);

            // Reservar la clase para los clientes
            gimnasio.reservarClase("CL001", "111222333", LocalDate.now());
            gimnasio.reservarClase("CL001", "444555666", LocalDate.now());

            // Cancelar una reserva
            gimnasio.cancelarReserva("CL001", "111222333", LocalDate.now());

            // Consultar la disponibilidad de la clase
            boolean disponible = gimnasio.consultarDisponibilidadClase("CL001");
            System.out.println("La clase CL001 está disponible: " + disponible);

            // Mostrar la clase más popular
            Clase claseMasPopular = gimnasio.obtenerClaseMasPopular();
            if (claseMasPopular != null) {
                System.out.println("La clase más popular es: " + claseMasPopular.getNombre());
            }

            // Mostrar los tres usuarios más activos
            System.out.println("Los tres usuarios más activos son:");
            for (Cliente cliente : gimnasio.obtenerTopTresUsuariosMasActivos()) {
                System.out.println(cliente.getNombre() + " - Calorías quemadas: " + cliente.caloriasTotalesQuemadas());
            }

        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }
}
