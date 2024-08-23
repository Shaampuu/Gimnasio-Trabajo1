import lombok.*;

@Getter
@Setter

public class Entrenador extends Usuario{

    private String especialidad;

    public Entrenador(String nombre, String identificacion) {
        super(nombre, identificacion);
    }
}
