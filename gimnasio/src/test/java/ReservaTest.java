import enums.TipoClase;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

class ReservaTest {

    @Test
    void testReserva() throws Exception {
        // Configuración del entorno de prueba
        TipoClase tipoClase = TipoClase.YOGA; // Suponiendo que TipoClase es un enum predefinido
        Entrenador entrenador = new Entrenador("Juan", "2145","Yoga"); // Suponiendo que tienes una clase Entrenador

        Clase clase = new Clase("C001", "Yoga Matutino", LocalDateTime.of(2024, 8, 30, 10, 0), 20, tipoClase, entrenador);
        Cliente cliente = new Cliente("Maria López", "Calle 123", "ID123", "maria@example.com", "123456789", "password123");
        LocalDateTime fechaReserva = LocalDateTime.now();

        // Crea una instancia de Reserva
        Reserva reserva = new Reserva(clase, cliente, fechaReserva);

        // Validaciones
        assertEquals(clase, reserva.getClase());
        assertEquals(cliente, reserva.getCliente());

    }

    @Test
    void testSettersAndGetters() throws Exception {
        // Configuración del entorno de prueba
        TipoClase tipoClase = TipoClase.YOGA; // Suponiendo que TipoClase es un enum predefinido
        Entrenador entrenador = new Entrenador("Juan Pérez","65861","Pesas"); // Suponiendo que tienes una clase Entrenador

        Clase clase = new Clase("C001", "Yoga Matutino", LocalDateTime.of(2024, 8, 30, 10, 0), 20, tipoClase, entrenador);
        Cliente cliente = new Cliente("Maria López", "Calle 123", "ID123", "maria@example.com", "123456789", "password123");
        LocalDateTime fechaReserva = LocalDateTime.now();

        // Crea una instancia de Reserva
        Reserva reserva = new Reserva(null, null, null);

        // Asignación de valores usando setters
        reserva.setClase(clase);
        reserva.setCliente(cliente);
        reserva.setFechaReserva(fechaReserva);

        // Validaciones
        assertEquals(clase, reserva.getClase());
        assertEquals(cliente, reserva.getCliente());
        assertEquals(fechaReserva, reserva.getFechaReserva());
    }
}
