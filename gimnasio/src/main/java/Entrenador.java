import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Entrenador extends Usuario {

    private String especialidad;

    // Constructor
    public Entrenador(String nombre, String identificacion, String especialidad) {
        super(nombre, identificacion); // Llama al constructor de la clase base Usuario
        this.especialidad = especialidad;
    }
}
