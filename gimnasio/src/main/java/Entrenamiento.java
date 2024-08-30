import enums.TipoEjercicio;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter

public class Entrenamiento {

    private TipoEjercicio tipoEjercicio;
    private int duracion;
    private String numeroSesion;
    private LocalDateTime fecha;
    private int caloriasQuemadas;
    private int idSesion;

    public Entrenamiento(int idSesion, TipoEjercicio tipoEjercicio, int duracion, int caloriasQuemadas, LocalDateTime fechaHora) {

        this.idSesion = (idSesion);
        this.tipoEjercicio = tipoEjercicio;
        this.duracion = duracion;
        this.caloriasQuemadas = caloriasQuemadas;
        this.fecha = fechaHora;
    }
}
