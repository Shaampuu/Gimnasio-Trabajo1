import enums.TipoClase;
import enums.TipoEjercicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GimnasioTest {

    private Gimnasio gimnasio;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        gimnasio = new Gimnasio(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        Entrenador entrenador = new Entrenador("12345", "Juan Pérez");
        gimnasio.getEntrenadores().add(entrenador);

        cliente = new Cliente("67890", "Ana Gómez", "Calle Falsa 123", "ana@correo.com", "contrasena");
        gimnasio.getClientes().add(cliente);

        Clase clase = new Clase("CL001", "Yoga", LocalDateTime.of(2024, 8, 30, 10, 0), 20, TipoClase.GRUPAL, entrenador);
        gimnasio.getClases().add(clase);
    }

    @Test
    public void testCrearUsuario() throws Exception {
        gimnasio.crearUsuario("11111", "Carlos López", "Calle Verdadera 456", "carlos@correo.com", "otraContrasena");
        Cliente nuevoCliente = gimnasio.buscarClientePorIdentificacion("11111");
        assertNotNull(nuevoCliente);
        assertEquals("Carlos López", nuevoCliente.getNombre());
    }

    @Test
    public void testAgregarUsuario() throws Exception {
        Cliente nuevoCliente = new Cliente("22222", "Laura Martínez", "Calle Real 789", "laura@correo.com", "contrasenaSegura");
        gimnasio.agregarUsuario(nuevoCliente);
        assertNotNull(gimnasio.buscarClientePorIdentificacion("22222"));
    }

    @Test
    public void testEliminarUsuario() throws Exception {
        gimnasio.eliminarUsuario(cliente.getIdentificacion());
        assertNull(gimnasio.buscarClientePorIdentificacion(cliente.getIdentificacion()));
    }

    @Test
    public void testActualizarUsuario() throws Exception {
        Cliente clienteActualizado = new Cliente(cliente.getIdentificacion(), "Ana Gómez", "Calle Falsa 123", "ana@nuevoCorreo.com", "nuevaContrasena");
        gimnasio.actualizarUsuario(clienteActualizado);
        Cliente clienteRecuperado = gimnasio.buscarClientePorIdentificacion(cliente.getIdentificacion());
        assertEquals("ana@nuevoCorreo.com", clienteRecuperado.getCorreo());
    }

    @Test
    public void testCrearClase() throws Exception {
        Entrenador nuevoEntrenador = new Entrenador("54321", "Pedro López");
        gimnasio.getEntrenadores().add(nuevoEntrenador);
        gimnasio.crearClase("CL002", "Pilates", LocalDateTime.of(2024, 8, 30, 12, 0), 15, TipoClase.GRUPAL, nuevoEntrenador);
        Clase nuevaClase = gimnasio.buscarClasePorCodigo("CL002");
        assertNotNull(nuevaClase);
        assertEquals("Pilates", nuevaClase.getNombre());
    }

    @Test
    public void testBuscarClases() {
        List<Clase> clasesEncontradas = gimnasio.buscarClases(TipoClase.GRUPAL, "Juan Pérez", LocalDateTime.of(2024, 8, 30, 10, 0));
        assertEquals(1, clasesEncontradas.size());
        assertEquals("Yoga", clasesEncontradas.get(0).getNombre());
    }

    @Test
    public void testReservarClase() throws Exception {
        gimnasio.reservarClase("CL001", cliente.getIdentificacion(), LocalDate.now());
        Reserva reserva = gimnasio.getReservas().get(0);
        assertNotNull(reserva);
        assertEquals("CL001", reserva.getClase().getCodigoClase());
        assertEquals(cliente.getIdentificacion(), reserva.getCliente().getIdentificacion());
    }

    @Test
    public void testCancelarReserva() throws Exception {
        gimnasio.reservarClase("CL001", cliente.getIdentificacion(), LocalDate.now());
        gimnasio.cancelarReserva("CL001", cliente.getIdentificacion(), LocalDate.now());
        assertTrue(gimnasio.getReservas().isEmpty());
    }

    @Test
    public void testRegistrarEntrenamiento() throws Exception {
        gimnasio.registrarEntrenamiento(cliente.getIdentificacion(), TipoEjercicio.CARDIO, 30, 300, LocalDateTime.now(), 1);
        Cliente clienteRecuperado = gimnasio.buscarClientePorIdentificacion(cliente.getIdentificacion());
        assertEquals(1, clienteRecuperado.getHistorialEntrenamientos().size());
    }

    @Test
    public void testObtenerClaseMasPopular() throws Exception {
        gimnasio.reservarClase("CL001", cliente.getIdentificacion(), LocalDate.now());
        Clase claseMasPopular = gimnasio.obtenerClaseMasPopular();
        assertEquals("CL001", claseMasPopular.getCodigoClase());
    }

    @Test
    public void testObtenerTopTresUsuariosMasActivos() throws Exception {
        gimnasio.registrarEntrenamiento(cliente.getIdentificacion(), TipoEjercicio.CARDIO, 30, 300, LocalDateTime.now(), 1);
        List<Cliente> topTres = gimnasio.obtenerTopTresUsuariosMasActivos();
        assertEquals(1, topTres.size());
        assertEquals(cliente.getIdentificacion(), topTres.get(0).getIdentificacion());
    }

    @Test
    public void testConsultarDisponibilidadClase() throws Exception {
        boolean disponible = gimnasio.consultarDisponibilidadClase("CL001");
        assertTrue(disponible);
    }
}
