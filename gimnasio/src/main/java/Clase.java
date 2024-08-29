import enums.TipoClase;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Clase {
    private String codigoClase;
    private String nombre;
    private LocalDateTime horario;
    private int capacidad;
    private TipoClase tipoClase;
    private Entrenador entrenador;
    private int inscritos;
    private boolean disponible;

    public Clase(String codigoClase, String nombre, LocalDateTime horario, int capacidad, TipoClase tipoClase, Entrenador entrenador) {
        this.codigoClase = codigoClase;
        this.nombre = nombre;
        this.horario = horario;
        this.capacidad = capacidad;
        this.tipoClase = tipoClase;
        this.entrenador = entrenador;
        this.inscritos = 0;
        this.disponible = true;
    }
}
