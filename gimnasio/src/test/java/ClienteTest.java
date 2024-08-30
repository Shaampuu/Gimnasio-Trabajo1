import enums.TipoClase;
import enums.TipoEjercicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    public void setUp() throws Exception {
        cliente = new Cliente("Carlos Ruiz", "Avenida 456", "99999", "carlos@example.com", "555-5678", "securePass");
    }

    @Test
    public void testConstructor() throws Exception {
        assertEquals("Carlos Ruiz", cliente.getNombre());
        assertEquals("Avenida 456", cliente.getDireccion());
        assertEquals("carlos@example.com", cliente.getCorreo());
        assertEquals("555-5678", cliente.getTelefono());
        assertEquals("securePass", cliente.getContrasena());
        assertTrue(cliente.getHistorialEntrenamientos().isEmpty());
    }

    @Test
    public void testSetDireccion() {
        cliente.setDireccion("Calle 789");
        assertEquals("Calle 789", cliente.getDireccion());
    }

    @Test
    public void testSetCorreo() {
        cliente.setCorreo("nuevoemail@example.com");
        assertEquals("nuevoemail@example.com", cliente.getCorreo());
    }

    @Test
    public void testSetTelefono() {
        cliente.setTelefono("555-1234");
        assertEquals("555-1234", cliente.getTelefono());
    }

    @Test
    public void testSetContrasena() {
        cliente.setContrasena("newPass123");
        assertEquals("newPass123", cliente.getContrasena());
    }

    @Test
    public void testCaloriasTotalesQuemadas() {
        Entrenamiento entrenamiento1 = new Entrenamiento(1, TipoEjercicio.PESAS, 45, 300, null);
        Entrenamiento entrenamiento2 = new Entrenamiento(2, TipoEjercicio.CARDIO, 30, 400, null);

        cliente.getHistorialEntrenamientos().add(entrenamiento1);
        cliente.getHistorialEntrenamientos().add(entrenamiento2);

        assertEquals(700, cliente.caloriasTotalesQuemadas());
    }

    @Test
    public void testHistorialEntrenamientos() {
        Entrenamiento entrenamiento = new Entrenamiento(3, TipoEjercicio.CARDIO, 30, 400, null);

        cliente.getHistorialEntrenamientos().add(entrenamiento);
        List<Entrenamiento> historial = cliente.getHistorialEntrenamientos();

        assertEquals(1, historial.size());
        assertEquals(entrenamiento, historial.get(0));
    }

    @Test
    public void testEstaInscritoEn() {
        Clase clase = new Clase("C001", "Yoga", null, 20, TipoClase.YOGA, null);

        // Implementar la lógica de inscripción antes de realizar el assert
        // Ejemplo: cliente.inscribirseEn(clase);

        assertFalse(cliente.estaInscritoEn(clase));
    }
}
