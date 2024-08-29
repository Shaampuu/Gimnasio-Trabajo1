import enums.TipoClase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            // Crear instancias de los objetos necesarios
            ArrayList<Clase> clases = new ArrayList<>();
            ArrayList<Reserva> reservas = new ArrayList<>();
            ArrayList<Cliente> clientes = new ArrayList<>();
            ArrayList<Entrenador> entrenadores = new ArrayList<>();

            // Crear un gimnasio
            Gimnasio gimnasio = new Gimnasio(clases, reservas, clientes, entrenadores);

            // Crear un entrenador
            Entrenador entrenador = new Entrenador("juan", "1086921277");
            entrenadores.add(entrenador);

            // Crear un cliente
            gimnasio.crearUsuario("987654", "Ana Gómez", "Calle Falsa 123", "ana@gmail.com", "password");

            // Crear una clase
            LocalDateTime horarioClase = LocalDateTime.of(2024, 9, 1, 9, 0);
            gimnasio.crearClase("CL001", "Yoga", horarioClase, 20, TipoClase.GIMNASIO, entrenador); // Asegúrate de que TipoClase.GIMNASIO es un valor válido

            // Reservar una clase
            gimnasio.reservarClase("CL001", "987654", LocalDate.now());

            // Consultar disponibilidad de la clase
            boolean disponible = gimnasio.consultarDisponibilidadClase("CL001");
            System.out.println("Clase disponible: " + disponible);

            // Obtener la clase más popular (en este caso es la única)
            Clase claseMasPopular = gimnasio.obtenerClaseMasPopular();
            if (claseMasPopular != null) {
                System.out.println("Clase más popular: " + claseMasPopular.getNombre());
            } else {
                System.out.println("No hay clases disponibles.");
            }

            // Consultar los tres usuarios más activos
            List<Cliente> topUsuarios = gimnasio.obtenerTopTresUsuariosMasActivos();
            System.out.println("Top 3 usuarios más activos:");
            for (Cliente cliente : topUsuarios) {
                System.out.println(cliente.getNombre());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
