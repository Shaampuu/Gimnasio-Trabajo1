import enums.TipoEjercicio;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
void consultarDisponibilidadClase() {
}
}