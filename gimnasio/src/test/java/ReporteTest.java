import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import enums.TipoEjercicio;

public class ReporteTest {

    private Gimnasio gimnasio;
    private Reporte reporte;

    @BeforeEach
    public void setUp() {
        // Crear una lista de tipos de ejercicios de prueba
        List<TipoEjercicio> tiposDeEjercicios = new ArrayList<>();
        tiposDeEjercicios.add(TipoEjercicio.CARDIO);
        tiposDeEjercicios.add(TipoEjercicio.CARDIO);
        tiposDeEjercicios.add(TipoEjercicio.FUERZA);

        // Crear una instancia de Reporte con el gimnasio de prueba
        reporte = new Reporte(gimnasio);
    }

    @Test
    public void testObtenerTipoEjercicioMasPracticado() {
        // Ejecutar el método a probar
        TipoEjercicio tipoMasPracticado = reporte.obtenerTipoEjercicioMasPracticado();


        assertEquals(TipoEjercicio.CARDIO, tipoMasPracticado, "El tipo de ejercicio más practicado debería ser CARDIO");
    }
}
