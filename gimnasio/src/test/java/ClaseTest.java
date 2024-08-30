import enums.TipoClase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ClaseTest {

    private Clase clase;
    private Entrenador entrenador;

    @BeforeEach
    public void setUp() {
        entrenador = new Entrenador("Juan Pérez", "12345", "Cardio");
        clase = new Clase("C001", "Yoga", LocalDateTime.of(2024, 8, 29, 10, 0), 20, TipoClase.YOGA, entrenador);
    }

    @Test
    public void testConstructor() {
        assertEquals("C001", clase.getCodigoClase());
        assertEquals("Yoga", clase.getNombre());
        assertEquals(LocalDateTime.of(2024, 8, 29, 10, 0), clase.getHorario());
        assertEquals(20, clase.getCapacidad());
        assertEquals(TipoClase.YOGA, clase.getTipoClase());
        assertEquals(entrenador, clase.getEntrenador());
        assertEquals(0, clase.getInscritos());
        assertTrue(clase.isDisponible());
    }

    @Test
    public void testSetCodigoClase() {
        clase.setCodigoClase("C002");
        assertEquals("C002", clase.getCodigoClase());
    }

    @Test
    public void testSetNombre() {
        clase.setNombre("Pilates");
        assertEquals("Pilates", clase.getNombre());
    }

    @Test
    public void testSetHorario() {
        LocalDateTime nuevoHorario = LocalDateTime.of(2024, 8, 29, 14, 0);
        clase.setHorario(nuevoHorario);
        assertEquals(nuevoHorario, clase.getHorario());
    }

    @Test
    public void testSetCapacidad() {
        clase.setCapacidad(30);
        assertEquals(30, clase.getCapacidad());
    }

    @Test
    public void testSetTipoClase() {
        clase.setTipoClase(TipoClase.CARDIO);
        assertEquals(TipoClase.CARDIO, clase.getTipoClase());
    }

    @Test
    public void testSetEntrenador() {
        Entrenador nuevoEntrenador = new Entrenador("Luis García", "67890", "Pesas");
        clase.setEntrenador(nuevoEntrenador);
        assertEquals(nuevoEntrenador, clase.getEntrenador());
    }

    @Test
    public void testSetInscritos() {
        clase.setInscritos(10);
        assertEquals(10, clase.getInscritos());
    }

    @Test
    public void testSetDisponible() {
        clase.setDisponible(false);
        assertFalse(clase.isDisponible());
    }

    @Test
    public void testAumentarInscritos() {
        clase.setInscritos(5);
        clase.setInscritos(clase.getInscritos() + 1);
        assertEquals(6, clase.getInscritos());
    }

    @Test
    public void testDisponibilidadClaseCompleta() {
        clase.setInscritos(20);
        clase.setDisponible(clase.getInscritos() < clase.getCapacidad());
        assertFalse(clase.isDisponible());
    }
}
