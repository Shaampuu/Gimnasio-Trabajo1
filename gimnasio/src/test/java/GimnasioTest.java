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

        // Configurar datos iniciales para las pruebas
        Entrenador entrenador = new Entrenador("Carlos Ramirez", "112233445", "Cardio");
        Clase clase1 = new Clase("CL001", "Yoga para Todos", LocalDateTime.of(2024, 8, 30, 10, 0), 20, TipoClase.YOGA, entrenador);
        Clase clase2 = new Clase("CL002", "Pilates Avanzado", LocalDateTime.of(2024, 8, 31, 11, 0), 15, TipoClase.PILATES, entrenador);
        gimnasio.getClases().add(clase1);
        gimnasio.getClases().add(clase2);

        // Configurar disponibilidad
        clase1.setDisponible(true);
        clase2.setDisponible(false);

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

    @Test //corregir
    void testActualizarUsuario() throws Exception {
        gimnasio.actualizarUsuario("Juan Pérez Actualizado", "Calle 456", "123456789", "juan.perez.update@example.com", "newpassword123");

        Usuario usuarioActualizado = gimnasio.obtenerUsuario("123456789");

        assertNotNull(usuarioActualizado);
        assertEquals("Juan Pérez Actualizado", usuarioActualizado.getNombre());
        assertEquals("Calle 456", ((Cliente) usuarioActualizado).getDireccion());  // Cast a Cliente para obtener atributos específicos
        assertEquals("juan.perez.update@example.com", ((Cliente) usuarioActualizado).getCorreo());}

    @Test //corregir
    void testObtenerUsuario() throws Exception{
        Usuario usuario = gimnasio.obtenerUsuario("123456789");

        assertNotNull(usuario, "El usuario debería existir");
        assertEquals("123456789", usuario.getIdentificacion(), "La identificación del usuario no coincide");
        assertEquals("Juan Perez", usuario.getNombre(), "El nombre del usuario no coincide");
    }

    @Test
    void testObtenerUsuarioNoExistente() {
        Usuario usuario = gimnasio.obtenerUsuario("000000000");

        assertNull(usuario, "El usuario no debería existir");
    }

    @Test //corregir
    void testEliminarUsuarioConIdentificacionValida() throws Exception {
        // Verifica que el usuario esté presente antes de eliminarlo
        Usuario usuarioAntes = gimnasio.obtenerUsuario("123456789");
        assertNotNull(usuarioAntes, "El usuario debería estar presente antes de eliminarlo");

        // Elimina el usuario
        gimnasio.eliminarUsuario("123456789");

        // Verifica que el usuario ha sido eliminado
        Usuario usuarioDespues = gimnasio.obtenerUsuario("123456789");
        assertNull(usuarioDespues, "El usuario debería haber sido eliminado");
    }

    @Test
    void testEliminarUsuarioConIdentificacionInvalida() {
        // Intenta eliminar un usuario que no existe
        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.eliminarUsuario("000000000");
        });

        assertEquals("El usuario no existe", exception.getMessage(), "El mensaje de excepción debería indicar que el usuario no existe");
    }

    @Test //corregir
    void testCrearClaseConDatosValidos() throws Exception {
        String codigoClase = "CL001";
        String nombre = "Clase de Yoga";
        LocalDateTime horario = LocalDateTime.of(2024, 8, 30, 10, 0);
        int capacidad = 20;
        TipoClase tipo = TipoClase.YOGA; // Ajusta según tu enum
        Entrenador entrenador = gimnasio.getEntrenadores().get(0);

        gimnasio.crearClase(codigoClase, nombre, horario, capacidad, tipo, entrenador);

        Clase claseCreada = gimnasio.getClases().stream()
                .filter(clase -> clase.getCodigoClase().equals(codigoClase))
                .findFirst()
                .orElse(null);

        assertNotNull(claseCreada, "La clase debería haber sido creada");
        assertEquals(codigoClase, claseCreada.getCodigoClase(), "El código de la clase no coincide");
        assertEquals(nombre, claseCreada.getNombre(), "El nombre de la clase no coincide");
        assertEquals(horario, claseCreada.getHorario(), "El horario de la clase no coincide");
        assertEquals(capacidad, claseCreada.getCapacidad(), "La capacidad de la clase no coincide");
        assertEquals(tipo, claseCreada.getTipoClase(), "El tipo de la clase no coincide");
        assertEquals(entrenador, claseCreada.getEntrenador(), "El entrenador de la clase no coincide");
    }
    @Test //corregir
    void testCrearClaseConCamposNulos() {
        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.crearClase(null, "Clase de Yoga", LocalDateTime.now(), 20, TipoClase.YOGA, gimnasio.getEntrenadores().get(0));
        });

        assertEquals("Todos los campos son obligatorios para crear una clase.", exception.getMessage());
    }

    @Test //corregir
    void testCrearClaseConCodigoDuplicado() throws Exception {
        String codigoClase = "CL001";
        String nombre = "Primera Clase";
        LocalDateTime horario = LocalDateTime.of(2024, 8, 30, 10, 0);
        int capacidad = 20;
        TipoClase tipo = TipoClase.YOGA;
        Entrenador entrenador = gimnasio.getEntrenadores().get(0);

        // Crear una primera clase
        gimnasio.crearClase(codigoClase, nombre, horario, capacidad, tipo, entrenador);

        // Intentar crear una segunda clase con el mismo código
        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.crearClase(codigoClase, "Otra Clase", LocalDateTime.now().plusDays(1), 25, TipoClase.PILATES, entrenador);
        });

        assertEquals("Ya existe una clase con el código CL001.", exception.getMessage());
    }

    @Test //corregir
    void testCrearClaseConEntrenadorNoRegistrado() {
        String codigoClase = "CL002";
        String nombre = "Clase de Pilates";
        LocalDateTime horario = LocalDateTime.of(2024, 8, 31, 11, 0);
        int capacidad = 15;
        TipoClase tipo = TipoClase.PILATES;

        // Entrenador no registrado
        Entrenador entrenadorNoRegistrado = new Entrenador("Laura Martinez", "223344556", "Pilates");

        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.crearClase(codigoClase, nombre, horario, capacidad, tipo, entrenadorNoRegistrado);
        });

        assertEquals("El entrenador con cédula 223344556 no está registrado en el gimnasio.", exception.getMessage());
    }

    @Test //corregir
    void testBuscarClasesPorTipo() {
        List<Clase> resultados = gimnasio.buscarClases(TipoClase.YOGA, null, null);

        assertEquals(2, resultados.size(), "Deberían encontrarse 2 clases de tipo YOGA");
        assertTrue(resultados.stream().allMatch(clase -> clase.getTipoClase() == TipoClase.YOGA), "Todas las clases deberían ser de tipo YOGA");
    }

    @Test //corregir
    void testBuscarClasesPorNombreInstructor() {
        List<Clase> resultados = gimnasio.buscarClases(null, "Laura Martinez", null);

        assertEquals(1, resultados.size(), "Debería encontrarse 1 clase con Laura Martinez como instructor");
        assertEquals("Pilates Avanzado", resultados.get(0).getNombre(), "El nombre de la clase no coincide");
    }

    @Test
    void testBuscarClasesPorHorario() {
        LocalDateTime horario = LocalDateTime.of(2024, 8, 30, 10, 0);
        List<Clase> resultados = gimnasio.buscarClases(null, null, horario);

        assertEquals(1, resultados.size(), "Debería encontrarse 1 clase en el horario especificado");
        assertEquals("Yoga para Todos", resultados.get(0).getNombre(), "El nombre de la clase no coincide");
    }

    @Test //corregir
    void testBuscarClasesPorTipoYNombreInstructor() {
        List<Clase> resultados = gimnasio.buscarClases(TipoClase.YOGA, "Carlos Ramirez", null);

        assertEquals(2, resultados.size(), "Deberían encontrarse 2 clases de tipo YOGA con Carlos Ramirez como instructor");
        assertTrue(resultados.stream().allMatch(clase -> clase.getTipoClase() == TipoClase.YOGA), "Todas las clases deberían ser de tipo YOGA");
        assertTrue(resultados.stream().allMatch(clase -> clase.getEntrenador().getNombre().equalsIgnoreCase("Carlos Ramirez")), "Todos los instructores deberían ser Carlos Ramirez");
    }

    @Test //corregir
    void testBuscarClasesSinResultados() {
        List<Clase> resultados = gimnasio.buscarClases(TipoClase.PILATES, "Carlos Ramirez", LocalDateTime.of(2024, 8, 31, 11, 0));

        assertTrue(resultados.isEmpty(), "No deberían encontrarse clases con los criterios especificados");
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

    @Test //corregir
    void testReservarClaseNoDisponible() throws Exception {
        Clase clase = gimnasio.getClases().get(0);
        clase.setInscritos(clase.getCapacidad()); // Llenar la clase

        Exception exception = assertThrows(Exception.class, () -> {
            gimnasio.reservarClase(clase.getCodigoClase(), "998877665", LocalDate.of(2024, 8, 29));
        });

        assertEquals("No hay disponibilidad en la clase con código CL001.", exception.getMessage());
    }

    @Test //corregir
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

    @Test //corregir
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

@Test//corregir
void testRegistrarEntrenamientoConDatosInvalidos() {
    Exception exception;

    // Prueba con identificación nula
    exception = assertThrows(Exception.class, () -> {
        new Entrenamiento(1, TipoEjercicio.CARDIO, 30, 300, LocalDateTime.now());
    });
    assertEquals("Datos del entrenamiento no pueden ser nulos", exception.getMessage(), "Mensaje de excepción incorrecto.");

    // Prueba con tipo de ejercicio nulo
    exception = assertThrows(Exception.class, () -> {
        new Entrenamiento(1, null, 30, 300, LocalDateTime.now());
    });
    assertEquals("Datos del entrenamiento no pueden ser nulos", exception.getMessage(), "Mensaje de excepción incorrecto.");

    // Prueba con fecha y hora nula
    exception = assertThrows(Exception.class, () -> {
        new Entrenamiento(1, TipoEjercicio.CARDIO, 30, 300, null);
    });
    assertEquals("Datos del entrenamiento no pueden ser nulos", exception.getMessage(), "Mensaje de excepción incorrecto.");

    // Prueba con ID de sesión negativo
    exception = assertThrows(Exception.class, () -> {
        new Entrenamiento(-1, TipoEjercicio.CARDIO, 30, 300, LocalDateTime.now());
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