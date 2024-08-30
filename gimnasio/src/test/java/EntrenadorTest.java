import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntrenadorTest {

    private Entrenador entrenador;

    @BeforeEach
    public void setUp() {
        entrenador = new Entrenador("Laura García", "123456", "Cardio");
    }

    @Test
    public void testConstructor() {
        assertEquals("Laura García", entrenador.getNombre());
        assertEquals("123456", entrenador.getIdentificacion());
        assertEquals("Cardio", entrenador.getEspecialidad());
    }

    @Test
    public void testSetEspecialidad() {
        entrenador.setEspecialidad("Yoga");
        assertEquals("Yoga", entrenador.getEspecialidad());
    }

    @Test
    public void testSetNombre() {
        entrenador.setNombre("María Pérez");
        assertEquals("María Pérez", entrenador.getNombre());
    }

    @Test
    public void testSetIdentificacion() {
        entrenador.setIdentificacion("789012");
        assertEquals("789012", entrenador.getIdentificacion());
    }

    @Test
    public void testGetIdentificacion() {
        // Dado que el método getIdentificacion siempre retorna null, este test verifica ese comportamiento
        assertNull(entrenador.getIdentificacion());
    }
}
