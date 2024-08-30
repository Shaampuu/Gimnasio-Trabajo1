import enums.TipoClase;
import enums.TipoEjercicio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GimnasioApp {

    public static void main(String[] args) {
        try {
            // Crear una instancia del gimnasio
            Gimnasio gimnasio = new Gimnasio(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

            // Crear entrenadores
            Entrenador entrenador1 = (Entrenador) gimnasio.crearEntrenador("Juan Pérez", "123456789", "Cardio");
            Entrenador entrenador2 = (Entrenador) gimnasio.crearEntrenador("María González", "987654321", "Fuerza");
            Entrenador entrenador3 = (Entrenador) gimnasio.crearEntrenador("Carlos Ramírez", "111222333", "Yoga");
            Entrenador entrenador4 = (Entrenador) gimnasio.crearEntrenador("Ana Martínez", "444555666", "Pilates");

            // Agregar entrenadores al gimnasio
            gimnasio.getEntrenadores().add(entrenador1);
            gimnasio.getEntrenadores().add(entrenador2);
            gimnasio.getEntrenadores().add(entrenador3);
            gimnasio.getEntrenadores().add(entrenador4);

            // Crear clientes
            Cliente cliente1 = (Cliente) gimnasio.crearCliente("Laura Sánchez", "555555555", "Calle 1", "password1", "123456", "laura@example.com");
            Cliente cliente2 = (Cliente) gimnasio.crearCliente("Pedro López", "666666666", "Calle 2", "password2", "654321", "pedro@example.com");
            Cliente cliente3 = (Cliente) gimnasio.crearCliente("Marta Fernández", "777777777", "Calle 3", "password3", "789012", "marta@example.com");
            Cliente cliente4 = (Cliente) gimnasio.crearCliente("Luis García", "888888888", "Calle 4", "password4", "890123", "luis@example.com");

            // Agregar clientes al gimnasio
            gimnasio.getClientes().add(cliente1);
            gimnasio.getClientes().add(cliente2);
            gimnasio.getClientes().add(cliente3);
            gimnasio.getClientes().add(cliente4);

            // Crear clases
            gimnasio.crearClase("C001", "Clase de Cardio", LocalDateTime.now().plusDays(1), 10, TipoClase.CARDIO, entrenador1);
            gimnasio.crearClase("C002", "Clase de Fuerza", LocalDateTime.now().plusDays(2), 15, TipoClase.FUERZA, entrenador2);
            gimnasio.crearClase("C003", "Clase de Yoga", LocalDateTime.now().plusDays(3), 20, TipoClase.YOGA, entrenador3);
            gimnasio.crearClase("C004", "Clase de Pilates", LocalDateTime.now().plusDays(4), 25, TipoClase.PILATES, entrenador4);

            // Reservar clases
            gimnasio.reservarClase("C001", "555555555", LocalDate.now());
            gimnasio.reservarClase("C002", "666666666", LocalDate.now());
            gimnasio.reservarClase("C003", "777777777", LocalDate.now());
            gimnasio.reservarClase("C004", "888888888", LocalDate.now());

            // Registrar entrenamientos
            gimnasio.registrarEntrenamiento("555555555", TipoEjercicio.CARDIO, 30, 300, LocalDateTime.now(), 1);
            gimnasio.registrarEntrenamiento("666666666", TipoEjercicio.FUERZA, 45, 450, LocalDateTime.now(), 2);
            gimnasio.registrarEntrenamiento("777777777", TipoEjercicio.YOGA, 60, 200, LocalDateTime.now(), 3);
            gimnasio.registrarEntrenamiento("888888888", TipoEjercicio.PILATES, 40, 350, LocalDateTime.now(), 4);

            // Consultar la clase más popular
            Clase claseMasPopular = gimnasio.obtenerClaseMasPopular();
            if (claseMasPopular != null) {
                System.out.println("La clase más popular es: " + claseMasPopular.getNombre());
            } else {
                System.out.println("No se encontró ninguna clase popular.");
            }

            // Obtener los tres usuarios más activos
            List<Cliente> topTresUsuarios = gimnasio.obtenerTopTresUsuariosMasActivos();
            System.out.println("Los tres usuarios más activos son:");
            for (Cliente cliente : topTresUsuarios) {
                System.out.println(cliente.getNombre());
            }

            // Consultar la disponibilidad de una clase
            boolean disponibilidad = gimnasio.consultarDisponibilidadClase("C001");
            System.out.println("La clase C001 está " + (disponibilidad ? "disponible" : "no disponible"));

            // Cancelar una reserva
            gimnasio.cancelarReserva("C002", "666666666", LocalDate.now());
            System.out.println("Reserva cancelada.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}