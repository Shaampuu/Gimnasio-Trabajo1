import enums.TipoClase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class GimnasioTest {
    private Gimnasio gimnasio;
    private ArrayList<Clase> clases;
    private ArrayList<Reserva> reservas;
    private ArrayList<Cliente> clientes;
    private ArrayList<Entrenador> entrenadores;
    private ArrayList<Usuario> usuarios;
    @BeforeEach
    void setUp() {
        // Inicializar listas y el objeto Gimnasio
        clases = new ArrayList<>();
        reservas = new ArrayList<>();
        clientes = new ArrayList<>();
        entrenadores = new ArrayList<>();
        usuarios = new ArrayList<>();
        gimnasio = new Gimnasio(clases, reservas, clientes, entrenadores, usuarios);
    }

    @Test
    void testCrearClase() throws Exception {
        Entrenador entrenador = new Entrenador("123", "Juan", );
        entrenadores.add(entrenador);
        gimnasio.crearClase("C001", "Yoga", LocalDateTime.now(), 20, TipoClase.YOGA, entrenador);
        assertEquals(1, clases.size());
        assertEquals("C001", clases.get(0).getCodigoClase());
    }

    @Test
    void testBuscarClases() throws Exception {
        Entrenador entrenador = new Entrenador("123", "Juan", especialidad);
        entrenadores.add(entrenador);
        gimnasio.crearClase("C001", "Yoga", LocalDateTime.now(), 20, TipoClase.YOGA, entrenador);

        List<Clase> resultados = gimnasio.buscarClases(TipoClase.YOGA, "Juan", LocalDateTime.now());
        assertEquals(1, resultados.size());
        assertEquals("C001", resultados.get(0).getCodigoClase());
    }

    @Test
    void testReservarClase() throws Exception {
        Entrenador entrenador = new Entrenador("123", "Juan", especialidad);
        Cliente cliente = new Cliente("456", "Ana");
        entrenadores.add(entrenador);
        clientes.add(cliente);
        gimnasio.crearClase("C001", "Yoga", LocalDateTime.now(), 20, TipoClase.YOGA, entrenador);

        gimnasio.reservarClase("C001", "456", LocalDate.now());
        assertEquals(1, gimnasio.getReservas().size());
    }
}
