import enums.TipoClase;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
public class Clase {

    // Métodos adicionales
    // Corrige el tipo de retorno y el nombre del método
    // Atributos
    private String codigoClase;
    private String nombre;
    private LocalDateTime horario;
    private int capacidad;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private boolean disponible; // Indica si la clase está disponible
    private TipoClase tipoClase;
    private Entrenador entrenador;
    private int inscritos; // Número de inscritos en la clase

    // Constructor
    public Clase(String codigoClase, String nombre, LocalDateTime horario, int capacidad, TipoClase tipoClase, Entrenador entrenador) {
        this.codigoClase = codigoClase;
        this.nombre = nombre;
        this.horario = horario;
        this.capacidad = capacidad;
        this.tipoClase = tipoClase;
        this.entrenador = entrenador;
        this.fechaInicio = null; // Valor predeterminado si no se pasa
        this.fechaFin = null; // Valor predeterminado si no se pasa
        this.disponible = true; // Inicializa la clase como disponible
        this.inscritos = 0; // Inicializa inscritos en 0
    }

}
