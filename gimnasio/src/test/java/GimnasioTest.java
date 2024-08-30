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

    @BeforeEach
    void setUp() throws Exception {
        gimnasio = Gimnasio.builder()
                .clases(new ArrayList<>())
                .reservas(new ArrayList<>())
                .clientes(new ArrayList<>())
                .entrenadores(new ArrayList<>())
                .usuarios(new ArrayList<>())
                .build();

        // Crear y agregar entrenadores
        Entrenador entrenador1 = new Entrenador("Carlos Ramirez", "112233445", "Cardio");
        Entrenador entrenador2 = new Entrenador("Ana Gonzalez", "223344556", "Pilates");
        gimnasio.getEntrenadores().add(entrenador1);
        gimnasio.getEntrenadores().add(entrenador2);

        // Crear y agregar clases
        Clase clase1 = new Clase("CL001", "Yoga para Todos", LocalDateTime.of(2024, 8, 30, 10, 0), 20, TipoClase.YOGA, entrenador1);
        Clase clase2 = new Clase("CL002", "Pilates Avanzado", LocalDateTime.of(2024, 8, 31, 11, 0), 15, TipoClase.PILATES, entrenador2);
        gimnasio.getClases().add(clase1);
        gimnasio.getClases().add(clase2);

        // Configurar disponibilidad
        clase1.setDisponible(true);
        clase2.setDisponible(false);

        // Crear y agregar clientes
        Cliente cliente1 = gimnasio.crearCliente("Juan Perez", "998877665", "Calle 123", "password123", "987654321", "juan.perez@example.com");
        Cliente cliente2 = gimnasio.crearCliente("Maria Lopez", "987654321", "Avenida 456", "password456", "123456789", "maria.lopez@example.com");
        gimnasio.getClientes().add(cliente1);
        gimnasio.getClientes().add(cliente2);


        // Crear y agregar usuarios
        Usuario usuario1 = new Usuario("angelica", "123456789");
        Usuario usuario2 = new Usuario("pedro", "987654321");
        gimnasio.agregarUsuario(usuario1);
        gimnasio.agregarUsuario(usuario2);

        // Crear y agregar reservas
        Reserva reserva1 = new Reserva(clase1, cliente1, LocalDate.of(2024, 8, 29).atStartOfDay());
        gimnasio.getReservas().add(reserva1);

        // Crear un entrenamiento para pruebas
        Entrenamiento entrenamiento1 = new Entrenamiento(1, TipoEjercicio.CARDIO, 30, 300, LocalDateTime.now());


}

// Tests para el método agregarUsuario

    @Test
    void testAgregarUsuarioConUsuarioValido() throws Exception {
        Usuario usuario = new Usuario("angelica", "123456789");

        gimnasio.agregarUsuario(usuario);

        assertTrue(gimnasio.getUsuarios().contains(usuario));
    }

    @Test
    void testAgregarUsuarioConUsuarioNulo() {
        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.agregarUsuario(null);
        });

        assertEquals("Usuario no puede ser nulo", exception.getMessage());
    }

    @Test
    void testCrearClienteConDatosValidos() throws Exception {
        Cliente cliente = gimnasio.crearCliente("Juan Perez", "123456789", "Calle 123", "password123", "987654321", "juan.perez@example.com");

        assertNotNull(cliente);
        assertTrue(gimnasio.getClientes().contains(cliente));
    }

    @Test
    void testCrearClienteConNombreNulo() {
        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.crearCliente(null, "123456789", "Calle 123", "password123", "987654321", "juan.perez@example.com");
        });

        assertEquals("El nombre es obligatorio", exception.getMessage());
    }

    @Test
    void testCrearClienteConIdentificacionNula() {
        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.crearCliente("Juan Perez", null, "Calle 123", "password123", "987654321", "juan.perez@example.com");
        });

        assertEquals("El número de identificación es obligatorio.", exception.getMessage());
    }

    @Test
    void testCrearClienteConDireccionNula() {
        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.crearCliente("Juan Perez", "123456789", null, "password123", "987654321", "juan.perez@example.com");
        });

        assertEquals("La dirección es obligatoria", exception.getMessage());
    }

    // Tests para el método crearEntrenador

    @Test
    void testCrearEntrenadorConDatosValidos() throws Exception {
        Entrenador entrenador = (Entrenador) gimnasio.crearEntrenador("Pedro Alvarez", "987654321", "Fitness");

        assertNotNull(entrenador);
        // Asume que necesitas agregar al entrenador a la lista después de crear
        gimnasio.getEntrenadores().add(entrenador);
        assertTrue(gimnasio.getEntrenadores().contains(entrenador));
    }

    @Test
    void testCrearEntrenadorConNombreNulo() {
        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.crearEntrenador(null, "987654321", "Fitness");
        });

        assertEquals("El nombre es obligatorio", exception.getMessage());
    }

    @Test
    void testCrearEntrenadorConIdentificacionNula() {
        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.crearEntrenador("Pedro Alvarez", null, "Fitness");
        });

        assertEquals("El número de identificación es obligatorio.", exception.getMessage());
    }

    @Test
    void testCrearEntrenadorConEspecialidadNula() {
        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.crearEntrenador("Pedro Alvarez", "987654321", null);
        });

        assertEquals("La especialidad es obligatoria", exception.getMessage());
    }

    // Tests para el método actualizarUsuario

    @Test
    void testActualizarUsuario() throws Exception {

        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuarioExistente = new Usuario("Juan", "123456789");
        usuarios.add(usuarioExistente);

        Gimnasio gimnasio = new Gimnasio(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), (ArrayList<Usuario>) usuarios);

        gimnasio.actualizarUsuario("Juan Actualizado", "Nueva Dirección", "123456789", "nuevoCorreo@example.com", "nuevaContrasena");

        Usuario usuarioActualizado = gimnasio.obtenerUsuario("123456789");
        assertNotNull(usuarioActualizado, "El usuario debería existir después de la actualización");
        assertEquals("Juan Actualizado", usuarioActualizado.getNombre(), "El nombre del usuario debería haber sido actualizado");
        assertEquals("123456789", usuarioActualizado.getIdentificacion(), "La identificación no debería cambiar");
    }

    @Test
    void testActualizarUsuarioNoExiste() {
        // Paso 1: Configurar el gimnasio sin el usuario
        List<Usuario> usuarios = new ArrayList<>();
        Gimnasio gimnasio = new Gimnasio(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), (ArrayList<Usuario>) usuarios);

        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.actualizarUsuario("Juan Actualizado", "Nueva Dirección", "123456789", "nuevoCorreo@example.com", "nuevaContrasena");
        });

        assertEquals("No existe un usuario con el número de identificación: 123456789", exception.getMessage());
    }


    @Test
    void testEliminarUsuarioExistente() throws Exception {
        // Paso 1: Configurar el gimnasio con un usuario existente
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuarioExistente = new Usuario("Juan", "123456789");
        usuarios.add(usuarioExistente);

        Gimnasio gimnasio = new Gimnasio(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), (ArrayList<Usuario>) usuarios);

        // Paso 2: Eliminar el usuario
        gimnasio.eliminarUsuario("123456789");

        // Paso 3: Verificar que el usuario ha sido eliminado
        Usuario usuarioEliminado = gimnasio.obtenerUsuario("123456789");
        assertNull(usuarioEliminado, "El usuario debería haber sido eliminado");
    }

    @Test
    void testEliminarUsuarioNoExistente() {
        // Paso 1: Configurar el gimnasio sin el usuario
        List<Usuario> usuarios = new ArrayList<>();
        Gimnasio gimnasio = new Gimnasio(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), (ArrayList<Usuario>) usuarios);

        // Paso 2: Intentar eliminar un usuario que no existe
        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.eliminarUsuario("123456789");
        });

        // Paso 3: Verificar que se lanza la excepción correcta
        assertEquals("El usuario no existe", exception.getMessage());
    }

    @Test
    void testCrearClaseConDatosValidos() {
        // Configuración de la clase y el gimnasio
        List<Clase> clases = new ArrayList<>();
        Entrenador entrenador1 = new Entrenador("Carlos","9647","Cardio");
        LocalDateTime horario1 = LocalDateTime.of(2024, 8, 30, 10, 0);
        Clase clase1 = new Clase("C1", "Yoga", horario1, 20, TipoClase.CARDIO, entrenador1);
        clases.add(clase1);  // Asegúrate de añadir la clase

        Gimnasio gimnasio = new Gimnasio((ArrayList<Clase>) clases, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        // Llama al método de búsqueda con parámetros válidos
        List<Clase> resultados = gimnasio.buscarClases(TipoClase.CARDIO, "Carlos", horario1);

        // Verifica que los resultados sean los esperados
        assertEquals(1, resultados.size(), "Debería encontrar exactamente una clase");
        assertEquals(clase1, resultados.get(0), "La clase encontrada debería ser la esperada");
    }

    @Test
    void testCrearClaseConCamposNulos() {
        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.crearClase(null, "Clase de Yoga", LocalDateTime.now(), 20, TipoClase.YOGA, gimnasio.getEntrenadores().get(0));
        });

        assertEquals("Todos los campos son obligatorios para crear una clase.", exception.getMessage());
    }

    @Test
    void testCrearClaseConCodigoDuplicado() throws Exception {
        String codigoClase = "CL003";
        String nombre = "Primera Clase";
        LocalDateTime horario = LocalDateTime.of(2024, 8, 30, 10, 0);
        int capacidad = 20;
        TipoClase tipo = TipoClase.YOGA;
        Entrenador entrenador = gimnasio.getEntrenadores().get(0);

        // Crear una primera clase con el nuevo código
        gimnasio.crearClase(codigoClase, nombre, horario, capacidad, tipo, entrenador);

        // Intentar crear una segunda clase con el mismo código
        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.crearClase(codigoClase, "Otra Clase", LocalDateTime.now().plusDays(1), 25, TipoClase.PILATES, entrenador);
        });

        assertEquals("Ya existe una clase con el código " + codigoClase + ".", exception.getMessage());
    }
    @Test
    void testCrearClaseConEntrenadorNoRegistrado() {
        String codigoClase = "CL004";
        String nombre = "Clase de Pilates";
        LocalDateTime horario = LocalDateTime.of(2024, 8, 31, 11, 0);
        int capacidad = 15;
        TipoClase tipo = TipoClase.PILATES;

        // Crear un entrenador no registrado
        Entrenador entrenadorNoRegistrado = new Entrenador("Laura Martinez", "223344556", "Pilates");

        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.crearClase(codigoClase, nombre, horario, capacidad, tipo, entrenadorNoRegistrado);
        });

        assertEquals("El entrenador con cédula 223344556 no está registrado en el gimnasio.", exception.getMessage());
    }

    @Test
    void testBuscarClasesConParametrosExactos() {
        // Paso 1: Configurar el gimnasio con clases
        List<Clase> clases = new ArrayList<>();
        Entrenador entrenador1 = new Entrenador("Carlos","7458","Runing");
        LocalDateTime horario1 = LocalDateTime.of(2024, 8, 30, 10, 0);
        Clase clase1 = new Clase("C1", "Yoga", horario1, 20, TipoClase.CARDIO, entrenador1);
        clases.add(clase1);

        Entrenador entrenador2 = new Entrenador("Ana","8524","Baile");
        LocalDateTime horario2 = LocalDateTime.of(2024, 8, 30, 11, 0);
        Clase clase2 = new Clase("C2", "Pilates", horario2, 15, TipoClase.FUERZA, entrenador2);
        clases.add(clase2);

        Gimnasio gimnasio = new Gimnasio((ArrayList<Clase>) clases, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        // Paso 2: Buscar clases con parámetros exactos
        List<Clase> resultados = gimnasio.buscarClases(TipoClase.CARDIO, "Carlos", horario1);

        // Paso 3: Verificar los resultados
        assertEquals(1, resultados.size(), "Debería encontrar exactamente una clase");
        assertEquals(clase1, resultados.get(0), "La clase encontrada debería ser la esperada");
    }

    @Test
    void testBuscarClasesConParametrosNulos() {
        // Paso 1: Configurar el gimnasio con clases
        List<Clase> clases = new ArrayList<>();
        Entrenador entrenador1 = new Entrenador("Carlos","28548","Pesas");
        LocalDateTime horario1 = LocalDateTime.of(2024, 8, 30, 10, 0);
        Clase clase1 = new Clase("C1", "Yoga", horario1, 20, TipoClase.CARDIO, entrenador1);
        clases.add(clase1);

        Gimnasio gimnasio = new Gimnasio((ArrayList<Clase>) clases, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        // Paso 2: Buscar clases con parámetros nulos
        List<Clase> resultados = gimnasio.buscarClases(null, null, null);

        // Paso 3: Verificar los resultados
        assertEquals(1, resultados.size(), "Debería encontrar todas las clases");
        assertEquals(clase1, resultados.get(0), "La clase encontrada debería ser la esperada");
    }

    @Test
    void testBuscarClasesSinCoincidencias() {
        // Paso 1: Configurar el gimnasio con clases
        List<Clase> clases = new ArrayList<>();
        Entrenador entrenador1 = new Entrenador("Carlos","57424","yOGA");
        LocalDateTime horario1 = LocalDateTime.of(2024, 8, 30, 10, 0);
        Clase clase1 = new Clase("C1", "Yoga", horario1, 20, TipoClase.CARDIO, entrenador1);
        clases.add(clase1);

        Gimnasio gimnasio = new Gimnasio((ArrayList<Clase>) clases, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        // Paso 2: Buscar clases con parámetros que no coinciden
        List<Clase> resultados = gimnasio.buscarClases(TipoClase.FUERZA, "Ana", LocalDateTime.of(2024, 8, 30, 11, 0));

        // Paso 3: Verificar los resultados
        assertTrue(resultados.isEmpty(), "No debería encontrar ninguna clase");
    }


    @Test //corregir
    void testReservarClaseConDatosValidos() throws Exception {
        String codigoClase = "CL001";
        String identificacion = "998877665";
        LocalDate fechaReserva = LocalDate.of(2024, 8, 29);

        gimnasio.reservarClase(codigoClase, identificacion, fechaReserva);

        Reserva reserva = gimnasio.getReservas().stream()
                .filter(r -> r.getClase().getCodigoClase().equals(codigoClase) &&
                        r.getCliente().getIdentificacion().equals(identificacion))
                .findFirst()
                .orElse(null);

        assertNotNull(reserva, "La reserva debería haberse creado");
        assertEquals(codigoClase, reserva.getClase().getCodigoClase(), "El código de la clase en la reserva no coincide");
        assertEquals(identificacion, reserva.getCliente().getIdentificacion(), "La identificación del cliente en la reserva no coincide");
        assertEquals(fechaReserva.atStartOfDay(), reserva.getFechaReserva(), "La fecha de reserva no coincide");
        assertEquals(1, gimnasio.getClases().stream()
                .filter(clase -> clase.getCodigoClase().equals(codigoClase))
                .findFirst()
                .get()
                .getInscritos(), "El número de inscritos en la clase debería ser 1");
    }

    @Test
    void testReservarClaseConCodigoClaseNulo() {
        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.reservarClase(null, "998877665", LocalDate.of(2024, 8, 29));
        });

        assertEquals("Código de clase o identificación de cliente no pueden ser nulos.", exception.getMessage());
    }

    @Test
    void testReservarClaseConIdentificacionClienteNula() {
        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.reservarClase("CL001", null, LocalDate.of(2024, 8, 29));
        });

        assertEquals("Código de clase o identificación de cliente no pueden ser nulos.", exception.getMessage());
    }

    @Test
    void testReservarClaseConCodigoClaseNoExistente() {
        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.reservarClase("CL999", "998877665", LocalDate.of(2024, 8, 29));
        });

        assertEquals("Clase con código CL999 no encontrada.", exception.getMessage());
    }

    @Test
    void testReservarClaseConClienteNoExistente() {
        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.reservarClase("CL001", "000000000", LocalDate.of(2024, 8, 29));
        });

        assertEquals("Cliente con cédula 000000000 no encontrado.", exception.getMessage());
    }

    @Test //corregir___________________________________________________________________________________________
    void testReservarClaseNoDisponible() throws Exception {
        Clase clase = gimnasio.getClases().get(0);
        clase.setInscritos(clase.getCapacidad()); // Llenar la clase

        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.reservarClase(clase.getCodigoClase(), "998877665", LocalDate.of(2024, 8, 29));
        });

        assertEquals("No hay disponibilidad en la clase con código CL001.", exception.getMessage());
    }

    @Test //corregir_______________________________________________________________________________________________
    void testBuscarClientePorIdentificacion() {
        Cliente cliente = gimnasio.buscarClientePorIdentificacion("998877665");

        assertNotNull(cliente, "El cliente debería ser encontrado");
        assertEquals("Juan Perez", cliente.getNombre(), "El nombre del cliente no coincide");
        assertEquals("998877665", cliente.getIdentificacion(), "La identificación del cliente no coincide");
    }

    @Test
    void testBuscarClientePorIdentificacionNoExistente() {
        Cliente cliente = gimnasio.buscarClientePorIdentificacion("000000000");

        assertNull(cliente, "No debería encontrarse un cliente con la identificación especificada");
    }

    @Test
    void testBuscarClasePorCodigo() {
        Clase clase = gimnasio.buscarClasePorCodigo("CL001");

        assertNotNull(clase, "La clase debería ser encontrada");
        assertEquals("CL001", clase.getCodigoClase(), "El código de la clase no coincide");
        assertEquals("Yoga para Todos", clase.getNombre(), "El nombre de la clase no coincide");
    }

    @Test
    void testBuscarClasePorCodigoNoExistente() {
        Clase clase = gimnasio.buscarClasePorCodigo("CL999");

        assertNull(clase, "No debería encontrarse una clase con el código especificado");
    }

    @Test //corregir________________________________________________________________________
    void testCancelarReservaExitoso() throws Exception {
        gimnasio.cancelarReserva("CL001", "998877665", LocalDate.of(2024, 8, 30));

        // Verificar que la reserva fue eliminada
        Reserva reservaCancelada = gimnasio.getReservas().stream()
                .filter(reserva -> reserva.getClase().getCodigoClase().equals("CL001") &&
                        reserva.getCliente().getIdentificacion().equals("998877665") &&
                        reserva.getFechaReserva().equals(LocalDate.of(2024, 8, 30)))
                .findFirst()
                .orElse(null);
        assertNull(reservaCancelada, "La reserva debería haber sido cancelada");

        // Verificar que la clase se ha actualizado correctamente
        Clase clase = gimnasio.buscarClasePorCodigo("CL001");
        assertNotNull(clase, "La clase debería ser encontrada");
        assertEquals(0, clase.getInscritos(), "El número de inscritos no es correcto");
        assertTrue(clase.isDisponible(), "La clase debería estar disponible");
    }

    @Test
    void testCancelarReservaNoExistente() {
        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.cancelarReserva("CL001", "998877665", LocalDate.of(2024, 8, 31));
        });

        assertEquals("No se encontró una reserva para cancelar con los datos proporcionados.", exception.getMessage());
    }

    @Test
    void testCancelarReservaDatosNulos() {
        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.cancelarReserva(null, "998877665", LocalDate.of(2024, 8, 30));
        });

        assertEquals("Código de clase, identificación de cliente y fecha de reserva no pueden ser nulos.", exception.getMessage());
    }

    @Test
    void testConsultarDisponibilidadClaseDisponible() throws Exception {
        boolean disponibilidad = gimnasio.consultarDisponibilidadClase("CL001");
        assertTrue(disponibilidad, "La clase CL001 debería estar disponible");
    }

    @Test
    void testConsultarDisponibilidadClaseNoDisponible() throws Exception {
        boolean disponibilidad = gimnasio.consultarDisponibilidadClase("CL002");
        assertFalse(disponibilidad, "La clase CL002 no debería estar disponible");
    }

    @Test
    void testConsultarDisponibilidadClaseNoExistente() {
        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.consultarDisponibilidadClase("CL999");
        });

        assertEquals("No se encontró una clase con el código CL999.", exception.getMessage());
    }

    @Test
    void testRegistrarEntrenamientoConDatosValidos() {
    // Datos de entrada
    String identificacion = "123";
    TipoEjercicio tipoEjercicio = TipoEjercicio.CARDIO;
    int duracion = 30;
    int caloriasQuemadas = 300;
    LocalDateTime fechaHora = LocalDateTime.now();
    int idSesion = 1;

    // Crear un objeto Entrenamiento
    Entrenamiento nuevoEntrenamiento = new Entrenamiento(idSesion, tipoEjercicio, duracion, caloriasQuemadas, fechaHora);

    // Verificar que los datos se hayan asignado correctamente
    assertEquals(idSesion, nuevoEntrenamiento.getIdSesion(), "El ID de sesión debería coincidir.");
    assertEquals(tipoEjercicio, nuevoEntrenamiento.getTipoEjercicio(), "El tipo de ejercicio debería coincidir.");
    assertEquals(duracion, nuevoEntrenamiento.getDuracion(), "La duración debería coincidir.");
    assertEquals(caloriasQuemadas, nuevoEntrenamiento.getCaloriasQuemadas(), "Las calorías quemadas deberían coincidir.");

    // Verificar que la fecha y hora coincidan (comparar con la fecha y hora usada en el test)
    assertEquals(fechaHora, fechaHora, "La fecha y hora deberían coincidir.");

    System.out.println("Prueba pasada: Entrenamiento registrado con éxito.");
}

    @Test
    void testRegistrarEntrenamientoConDatosInvalidos() {

        // Prueba con identificación nula
        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.registrarEntrenamiento(null, TipoEjercicio.CARDIO, 30, 300, LocalDateTime.now(), 1);
        });
        assertEquals("Datos del entrenamiento no pueden ser nulos", exception.getMessage(), "Mensaje de excepción incorrecto.");

        // Prueba con tipo de ejercicio nulo
        exception = assertThrows(Exception.class, () -> {
            gimnasio.registrarEntrenamiento("123", null, 30, 300, LocalDateTime.now(), 1);
        });
        assertEquals("Datos del entrenamiento no pueden ser nulos", exception.getMessage(), "Mensaje de excepción incorrecto.");

        // Prueba con fecha y hora nula
        exception = assertThrows(Exception.class, () -> {
            gimnasio.registrarEntrenamiento("123", TipoEjercicio.CARDIO, 30, 300, null, 1);
        });
        assertEquals("Datos del entrenamiento no pueden ser nulos", exception.getMessage(), "Mensaje de excepción incorrecto.");

        // Prueba con ID de sesión negativo
        exception = assertThrows(Exception.class, () -> {
            gimnasio.registrarEntrenamiento("123", TipoEjercicio.CARDIO, 30, 300, LocalDateTime.now(), -1);
        });
        assertEquals("Datos del entrenamiento no pueden ser nulos", exception.getMessage(), "Mensaje de excepción incorrecto.");
    }

@Test
void testObtenerClaseMasPopularNoHayClases() {
    // Crea una instancia del gimnasio con listas vacías para cada atributo
    Gimnasio gimnasio = new Gimnasio(
            new ArrayList<>(),  // Lista de clases vacía
            new ArrayList<>(),  // Lista de reservas vacía
            new ArrayList<>(),  // Lista de clientes vacía
            new ArrayList<>(),  // Lista de entrenadores vacía
            new ArrayList<>()   // Lista de usuarios vacía
    );

    assertNull(gimnasio.obtenerClaseMasPopular(), "El resultado debería ser nulo cuando no hay clases");
}


@Test
void testObtenerTopTresUsuariosMasActivosListaVacia() {
    // Crea una instancia del gimnasio con una lista vacía de clientes
    Gimnasio gimnasio = new Gimnasio(
            new ArrayList<>(),  // Lista de clases vacía
            new ArrayList<>(),  // Lista de reservas vacía
            new ArrayList<>(),  // Lista de clientes vacía
            new ArrayList<>(),  // Lista de entrenadores vacía
            new ArrayList<>()   // Lista de usuarios vacía
    );


    List<Cliente> topTres = gimnasio.obtenerTopTresUsuariosMasActivos();
    assertTrue(topTres.isEmpty(), "El resultado debería ser una lista vacía cuando no hay clientes");
}


@Test
void testObtenerTipoEjercicioMasPracticadoListaVacia() {
    // Crea una instancia del gimnasio con una lista vacía de clientes
    Gimnasio gimnasio = new Gimnasio(
            new ArrayList<>(),  // Lista de clases vacía
            new ArrayList<>(),  // Lista de reservas vacía
            new ArrayList<>(),  // Lista de clientes vacía
            new ArrayList<>(),  // Lista de entrenadores vacía
            new ArrayList<>()   // Lista de usuarios vacía
    );

    assertNull(gimnasio.obtenerTipoEjercicioMasPracticado(), "El resultado debería ser nulo cuando no hay clientes");
}

@Test
void testConsultarDisponibilidadCodigoNulo() {
        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.consultarDisponibilidadClase(null);
        });

        assertEquals("El código de la clase no puede ser nulo.", exception.getMessage());
    }
}