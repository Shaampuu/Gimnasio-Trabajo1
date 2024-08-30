import enums.TipoEjercicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class EntrenamientoTest {

    private Entrenamiento entrenamiento;
    private LocalDateTime fechaHora;

    @BeforeEach
    public void setUp() {
        fechaHora = LocalDateTime.of(2024, 8, 29, 10, 0);
        entrenamiento = new Entrenamiento(1, TipoEjercicio.CARDIO, 60, 500, fechaHora);
    }

    @Test
    public void testConstructor() {
        assertEquals(1, entrenamiento.getIdSesion());
        assertEquals(TipoEjercicio.CARDIO, entrenamiento.getTipoEjercicio());
        assertEquals(60, entrenamiento.getDuracion());
        assertEquals(500, entrenamiento.getCaloriasQuemadas());
        assertEquals(fechaHora, entrenamiento.getFecha());
    }

    @Test
    public void testSetDuracion() {
        entrenamiento.setDuracion(45);
        assertEquals(45, entrenamiento.getDuracion());
    }

    @Test
    public void testSetCaloriasQuemadas() {
        entrenamiento.setCaloriasQuemadas(600);
        assertEquals(600, entrenamiento.getCaloriasQuemadas());
    }

    @Test
    public void testSetTipoEjercicio() {
        entrenamiento.setTipoEjercicio(TipoEjercicio.PESAS);
        assertEquals(TipoEjercicio.PESAS, entrenamiento.getTipoEjercicio());
    }

    @Test
    public void testSetFecha() {
        LocalDateTime nuevaFecha = LocalDateTime.of(2024, 8, 30, 12, 0);
        entrenamiento.setFecha(nuevaFecha);
        assertEquals(nuevaFecha, entrenamiento.getFecha());
    }

    @Test
    public void testSetNumeroSesion() {
        entrenamiento.setNumeroSesion("002");
        assertEquals("002", entrenamiento.getNumeroSesion());
    }
}
