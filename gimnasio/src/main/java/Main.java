import enums.TipoClase;
import enums.TipoEjercicio;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            // Crear instancias de gimnasio, entrenadores y clientes
            Gimnasio gimnasio = new Gimnasio(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

            //Crear cliente
            Cliente cliente1 = new Cliente("987654321", "Ana Torres", "Calle Falsa 123", "ana.torres@mail.com", "password123");
            gimnasio.crearUsuario(cliente1.getIdentificacion(), cliente1.getNombre(), cliente1.getDireccion(), cliente1.getCorreo(), cliente1.getContrasena());

            // Crear un entrenador con cédula válida
            Entrenador entrenador1 = new Entrenador("123456789", "Carlos Perez");

            // Añadir el entrenador al gimnasio
            gimnasio.getEntrenadores().add(entrenador1);

            // Crear una clase
            gimnasio.crearClase("CL001", "Yoga", LocalDateTime.of(2024, 9, 1, 10, 0), 20, TipoClase.YOGA, entrenador1);

            // Buscar clases
            System.out.println("Buscando clases de Yoga con Carlos Perez...");
            gimnasio.buscarClases(TipoClase.YOGA, "Carlos Perez", LocalDateTime.of(2024, 9, 1, 10, 0))
                    .forEach(clase -> System.out.println("Clase encontrada: " + clase.getNombre()));

            // Reservar clase
            gimnasio.reservarClase("CL001", cliente1.getIdentificacion(), LocalDate.of(2024, 9, 1));

            // Consultar disponibilidad
            System.out.println("La clase CL001 está disponible: " + gimnasio.consultarDisponibilidadClase("CL001"));

            // Registrar entrenamiento
            gimnasio.registrarEntrenamiento(cliente1.getIdentificacion(), TipoEjercicio.CARDIO, 60, 500, LocalDateTime.now(), 1);

            // Obtener reporte de la clase más popular
            Clase claseMasPopular = gimnasio.obtenerClaseMasPopular();
            System.out.println("La clase más popular es: " + (claseMasPopular != null ? claseMasPopular.getNombre() : "No hay clases registradas"));

            // Obtener los tres usuarios más activos
            System.out.println("Top tres usuarios más activos:");
            gimnasio.obtenerTopTresUsuariosMasActivos()
                    .forEach(cliente -> System.out.println(cliente.getNombre() + " quemó " + cliente.caloriasTotalesQuemadas() + " calorías"));

            // Eliminar usuario
            gimnasio.eliminarUsuario(cliente1.getIdentificacion());
            System.out.println("Usuario " + cliente1.getNombre() + " eliminado.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
