import enums.TipoClase;
import java.time.LocalDate;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class Clase {

    // Atributos
    private String Id;
    private String nombre;
    private String horario; //List<String>
    private int capacidad;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoClase estado;
    private TipoClase tipoClase;
    private Entrenador entrenador;
    //disponible boolean
    //inscritos

}
