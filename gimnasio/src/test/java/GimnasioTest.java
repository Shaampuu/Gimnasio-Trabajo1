import enums.TipoClase;
import enums.TipoEjercicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GimnasioTest {

    private Gimnasio gimnasio;
    private Cliente cliente;
    private Entrenador entrenador;
    private Clase clase;
    private Usuario usuario;

    @BeforeEach
    void setUp() throws Exception {
        gimnasio = Gimnasio.builder()
                .clases(new ArrayList<>())
                .reservas(new ArrayList<>())
                .clientes(new ArrayList<>())
                .entrenadores(new ArrayList<>())
                .usuarios(new ArrayList<>())
                .build();

        cliente = new Cliente("John Doe", "12345", "123 Main St", "password", "555-1234", "johndoe@example.com");
        entrenador = new Entrenador("Jane Smith", "54321", "Fitness");
        usuario = new Usuario("John Doe", "12345");

        gimnasio.getClientes().add(cliente);
        gimnasio.getEntrenadores().add(entrenador);

        clase = new Clase("001", "Yoga", LocalDateTime.now(), 10, TipoClase.YOGA, entrenador);
        gimnasio.getClases().add(clase);
    }

    @Test
    void agregarUsuario() throws Exception {
        gimnasio.agregarUsuario(usuario);
        assertTrue(gimnasio.getUsuarios().contains(usuario));
    }

    @Test
    void crearCliente() throws Exception {
        Cliente nuevoCliente = gimnasio.crearCliente("Jane Doe", "67890", "456 Main St", "password", "555-6789", "janedoe@example.com");
        assertNotNull(nuevoCliente);
        assertTrue(gimnasio.getClientes().contains(nuevoCliente));
    }

    @Test
    void crearEntrenador() throws Exception {
        Entrenador nuevoEntrenador = (Entrenador) gimnasio.crearEntrenador("Mike Johnson", "98765", "Cardio");
        assertNotNull(nuevoEntrenador);
        assertTrue(gimnasio.getEntrenadores().contains(nuevoEntrenador));
    }

    //@Test
    //    void actualizarUsuario() throws Exception {
    //        gimnasio.agregarUsuario(usuario);
    //        gimnasio.actualizarUsuario("John Updated", "New Address", "12345", "newemail@example.com", "newpassword");
    //        Usuario actualizado = gimnasio.obtenerUsuario("12345");
    //        assertEquals("John Updated", actualizado.getNombre());
    //        assertEquals("New Address", actualizado.getDireccion());
    //    }

    @Test
    void obtenerUsuario() throws Exception {
        gimnasio.agregarUsuario(usuario);
        Usuario encontrado = gimnasio.obtenerUsuario("12345");
        assertNotNull(encontrado);
    }

    @Test
    void eliminarUsuario() throws Exception {
        gimnasio.agregarUsuario(usuario);
        gimnasio.eliminarUsuario("12345");
        assertNull(gimnasio.obtenerUsuario("12345"));
    }

    @Test
    void crearClase() throws Exception {
        gimnasio.crearClase("002", "Pilates", LocalDateTime.now(), 15, TipoClase.PILATES, entrenador);
        assertEquals(2, gimnasio.getClases().size());
    }

    @Test
    void buscarClases() {
        LocalDateTime horario = clase.getHorario();
        assertFalse(gimnasio.buscarClases(TipoClase.YOGA, "Jane Smith", horario).isEmpty());
    }

    @Test
    void reservarClase() throws Exception {
        gimnasio.reservarClase("001", "12345", LocalDate.now());
        assertEquals(1, gimnasio.getReservas().size());
    }

    @Test
    void buscarClientePorIdentificacion() {
        Cliente encontrado = gimnasio.buscarClientePorIdentificacion("12345");
        assertNotNull(encontrado);
    }

    @Test
    void buscarClasePorCodigo() {
        Clase encontrada = gimnasio.buscarClasePorCodigo("001");
        assertNotNull(encontrada);
    }

    @Test
    void cancelarReserva() throws Exception {
        gimnasio.reservarClase("001", "12345", LocalDate.now());
        gimnasio.cancelarReserva("001", "12345", LocalDate.now());
        assertTrue(gimnasio.getReservas().isEmpty());
    }

    @Test
    void registrarEntrenamiento() throws Exception {
        gimnasio.registrarEntrenamiento("12345", TipoEjercicio.CARDIO, 30, 300, LocalDateTime.now(), 1);
        assertEquals(1, cliente.getHistorialEntrenamientos().size());
    }

    @Test
    void obtenerClaseMasPopular() {
        Clase claseMasPopular = gimnasio.obtenerClaseMasPopular();
        assertNotNull(claseMasPopular);
    }

    @Test
    void obtenerTopTresUsuariosMasActivos() {
        assertEquals(1, gimnasio.obtenerTopTresUsuariosMasActivos().size());
    }

    @Test
    void obtenerTipoEjercicioMasPracticado() {
        assertNull(gimnasio.obtenerTipoEjercicioMasPracticado());
    }

    @Test
    void consultarDisponibilidadClase() throws Exception {
        assertTrue(gimnasio.consultarDisponibilidadClase("001"));
    }

    @Test
    void getClases() {
        assertEquals(1, gimnasio.getClases().size());
    }

    @Test
    void getReservas() {
        assertTrue(gimnasio.getReservas().isEmpty());
    }

    @Test
    void getClientes() {
        assertEquals(1, gimnasio.getClientes().size());
    }

    @Test
    void getEntrenadores() {
        assertEquals(1, gimnasio.getEntrenadores().size());
    }

    @Test
    void getUsuarios() {
        assertTrue(gimnasio.getUsuarios().isEmpty());
    }

    @Test
    void builder() {
        Gimnasio gimnasioBuilder = Gimnasio.builder()
                .clases(new ArrayList<>())
                .reservas(new ArrayList<>())
                .clientes(new ArrayList<>())
                .entrenadores(new ArrayList<>())
                .usuarios(new ArrayList<>())
                .build();

        assertNotNull(gimnasioBuilder);
    }
}
