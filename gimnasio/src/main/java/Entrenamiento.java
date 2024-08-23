import enums.TipoEjercicio;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter

public class Entrenamiento {

    private TipoEjercicio tipo;
    private int duracion;
    private  String numeroSesion;
    private LocalDateTime fecha;
    private int caloriasQuemadas;

    public Object getTipoEjercicio() {
        return null;
    }
}
