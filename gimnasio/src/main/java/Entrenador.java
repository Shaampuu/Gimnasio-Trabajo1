import lombok.*;

@Getter
@Setter

public class Entrenador extends Usuario{

    private String especialidad;

    public Entrenador(String nombre, String identificacion, String especialidad) {
        super(nombre, identificacion);
        this.especialidad = especialidad;
    }

    public String getCedula() {
        return null;
    }
}
