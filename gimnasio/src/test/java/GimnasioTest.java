import enums.TipoClase;
import enums.TipoEjercicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GimnasioTest {

    private Gimnasio gimnasio;
    private Cliente cliente;
    private Entrenador entrenador;

    @BeforeEach
    void setUp() throws Exception {
        gimnasio = Gimnasio.builder()
                .clases(new ArrayList<>())
                .reservas(new ArrayList<>())
                .clientes(new ArrayList<>())
                .entrenadores(new ArrayList<>())
                .usuarios(new ArrayList<>())
                .build();

        cliente = new Cliente("Juan Pérez", "123456789", "Calle Falsa 123", "password123", "3001234567", "juan@example.com");
        entrenador = new Entrenador("Carlos Rodríguez", "987654321", "Cardio");
        gimnasio.getClientes().add(cliente);
        gimnasio.getEntrenadores().add(entrenador);
    }

    @Test
    void testAgregarUsuario() throws Exception {
        Usuario usuario = new Usuario("Maria Gomez", "111222333");
        gimnasio.agregarUsuario(usuario);
        assertEquals(1, gimnasio.getUsuarios().size());
        assertEquals("Maria Gomez", gimnasio.getUsuarios().get(0).getNombre());
    }

    @Test
    void testAgregarUsuarioNull() {
        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.agregarUsuario(null);
        });

        assertEquals("Usuario no puede ser nulo", exception.getMessage());
    }

    @Test
    void testCrearCliente() throws Exception {
        Cliente cliente = (Cliente) gimnasio.crearCliente("Ana Garcia", "987654321", "Calle 456", "pass456", "3019876543", "ana@example.com");
        assertNotNull(cliente);
        assertEquals("Ana Garcia", cliente.getNombre());
    }

    @Test
    void testCrearClienteConDatosInvalidos() {
        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.crearCliente("", "987654321", "Calle 456", "pass456", "3019876543", "ana@example.com");
        });

        assertEquals("El nombre es obligatorio", exception.getMessage());
    }

    @Test
    void testCrearEntrenador() throws Exception {
        Entrenador entrenador = (Entrenador) gimnasio.crearEntrenador("Pedro Lopez", "1122334455", "Musculación");
        assertNotNull(entrenador);
        assertEquals("Pedro Lopez", entrenador.getNombre());
    }

    @Test
    void testCrearClase() throws Exception {
        LocalDateTime horario = LocalDateTime.now().plusDays(1);
        gimnasio.crearClase("001", "Yoga", horario, 20, TipoClase.YOGA, entrenador);
        assertEquals(1, gimnasio.getClases().size());
        assertEquals("Yoga", gimnasio.getClases().get(0).getNombre());
    }

    @Test
    void testCrearClaseConEntrenadorNoRegistrado() {
        Exception exception = assertThrows(Exception.class, () -> {
            LocalDateTime horario = LocalDateTime.now().plusDays(1);
            Entrenador entrenadorNoRegistrado = new Entrenador("Juan Ramirez", "111222333", "CrossFit");
            gimnasio.crearClase("002", "CrossFit", horario, 15, TipoClase.CROSSFIT, entrenadorNoRegistrado);
        });

        assertEquals("El entrenador con cédula 111222333 no está registrado en el gimnasio.", exception.getMessage());
    }

    @Test
    void testReservarClase() throws Exception {
        LocalDateTime horario = LocalDateTime.now().plusDays(1);
        gimnasio.crearClase("003", "Spinning", horario, 10, TipoClase.SPINNING, entrenador);
        gimnasio.reservarClase("003", cliente.getIdentificacion(), LocalDate.now());
        assertEquals(1, gimnasio.getReservas().size());
    }

    @Test
    void testReservarClaseInexistente() {
        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.reservarClase("999", cliente.getIdentificacion(), LocalDate.now());
        });

        assertEquals("Clase con código 999 no encontrada.", exception.getMessage());
    }

    @Test
    void testCancelarReserva() throws Exception {
        LocalDateTime horario = LocalDateTime.now().plusDays(1);
        gimnasio.crearClase("004", "Pilates", horario, 15, TipoClase.PILATES, entrenador);
        gimnasio.reservarClase("004", cliente.getIdentificacion(), LocalDate.now());

        gimnasio.cancelarReserva("004", cliente.getIdentificacion(), LocalDate.now());
        assertEquals(0, gimnasio.getReservas().size());
    }

    @Test
    void testCancelarReservaInexistente() {
        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.cancelarReserva("999", cliente.getIdentificacion(), LocalDate.now());
        });

        assertEquals("No se encontró una reserva para cancelar con los datos proporcionados.", exception.getMessage());
    }

    @Test
    void testObtenerClaseMasPopular() throws Exception {
        LocalDateTime horario = LocalDateTime.now().plusDays(1);
        gimnasio.crearClase("005", "Zumba", horario, 20, TipoClase.ZUMBA, entrenador);
        gimnasio.reservarClase("005", cliente.getIdentificacion(), LocalDate.now());

        Clase claseMasPopular = gimnasio.obtenerClaseMasPopular();
        assertNotNull(claseMasPopular);
        assertEquals("Zumba", claseMasPopular.getNombre());
    }

    @Test
    void testObtenerTopTresUsuariosMasActivos() throws Exception {
        Cliente cliente1 = new Cliente("Cliente 1", "111", "Direccion 1", "pass1", "300111", "cliente1@example.com");
        Cliente cliente2 = new Cliente("Cliente 2", "222", "Direccion 2", "pass2", "300222", "cliente2@example.com");
        Cliente cliente3 = new Cliente("Cliente 3", "333", "Direccion 3", "pass3", "300333", "cliente3@example.com");

        gimnasio.getClientes().add(cliente1);
        gimnasio.getClientes().add(cliente2);
        gimnasio.getClientes().add(cliente3);

        gimnasio.registrarEntrenamiento(cliente1.getIdentificacion(), TipoEjercicio.CARDIO, 30, 300, LocalDateTime.now(), 1);
        gimnasio.registrarEntrenamiento(cliente2.getIdentificacion(), TipoEjercicio.FUERZA, 40, 400, LocalDateTime.now(), 2);
        gimnasio.registrarEntrenamiento(cliente3.getIdentificacion(), TipoEjercicio.CARDIO, 50, 500, LocalDateTime.now(), 3);

        List<Cliente> topTres = gimnasio.obtenerTopTresUsuariosMasActivos();
        assertEquals(3, topTres.size());
        assertEquals(cliente3, topTres.get(0));
        assertEquals(cliente2, topTres.get(1));
        assertEquals(cliente1, topTres.get(2));
    }

    @Test
    void testConsultarDisponibilidadClase() throws Exception {
        LocalDateTime horario = LocalDateTime.now().plusDays(1);
        gimnasio.crearClase("006", "Boxeo", horario, 20, TipoClase.BOXEO, entrenador);
        assertTrue(gimnasio.consultarDisponibilidadClase("006"));
    }

    @Test
    void testConsultarDisponibilidadClaseInexistente() {
        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.consultarDisponibilidadClase("999");
        });

        assertEquals("No se encontró una clase con el código 999.", exception.getMessage());
    }
}
