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
    public void testCrearUsuario() throws Exception {
        gimnasio.crearCliente("11111", "Carlos López", "Calle Verdadera 456", "carlos@correo.com", "121212","otraContrasena");
        Cliente nuevoCliente = gimnasio.buscarClientePorIdentificacion("11111");
        assertNotNull(nuevoCliente);
        assertEquals("Carlos López", nuevoCliente.getNombre());
    }

    @Test
    public void testAgregarUsuario() throws Exception {
        Cliente nuevoCliente = new Cliente("22222", "Laura Martínez", "Calle Real 789", "laura@correo.com", "121212", "contrasenaSegura");
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
        Cliente clienteActualizado = new Cliente(cliente.getIdentificacion(), "Ana Gómez", "Calle Falsa 123", "ana@nuevoCorreo.com", "1213","nuevaContrasena");
        gimnasio.actualizarUsuario(clienteActualizado);
        Cliente clienteRecuperado = gimnasio.buscarClientePorIdentificacion(cliente.getIdentificacion());
        assertEquals("ana@nuevoCorreo.com", clienteRecuperado.getCorreo());
    }

    @Test
    public void testCrearClase() throws Exception {
        Entrenador nuevoEntrenador = new Entrenador("54321", "Pedro López", "esp");
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
