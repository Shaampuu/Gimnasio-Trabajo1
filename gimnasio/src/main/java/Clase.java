import enums.TipoClase;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
public class Clase {

    // Atributos
    private String codigoClase;
    private String nombre;
    private LocalDateTime horario;
    private int capacidad;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    // Métodos adicionales
    @Setter
    @Getter
    private boolean disponible; // Corregido el nombre del atributo para que coincida con el método isDisponible()
    private TipoClase tipoClase;
    private Entrenador entrenador;
    @Setter
    @Getter
    private int inscritos;

    // Constructor sin los parámetros opcionales
    public Clase(String codigoClase, String nombre, LocalDateTime horario, int capacidad, TipoClase tipoClase, Entrenador entrenador) {
        this.codigoClase = codigoClase;
        this.nombre = nombre;
        this.horario = horario;
        this.capacidad = capacidad;
        this.tipoClase = tipoClase;
        this.entrenador = entrenador;
        this.fechaInicio = null; // Valor predeterminado si no se pasa
        this.fechaFin = null; // Valor predeterminado si no se pasa
        this.disponible = true; // Inicializamos la clase como disponible
        this.inscritos = 0; // Inicializamos inscritos en 0
    }

    public Object getcodigoClase() {
        return null;
    }
}
