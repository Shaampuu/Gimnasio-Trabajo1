import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;


@Getter
@Setter
@Builder

public class Cliente extends Usuario{

    private String direccion, password, telefono, correo;
    private List<Entrenamiento> entrenamientos;

    public Cliente(String nombre, String identificacion, String direccion, String password, String correo, String telefono) {
        super(nombre, identificacion);
        this.direccion = direccion;
        this.password = password;
        this.correo = correo;
        this.telefono = telefono;
    }

    public Arrays getHistorialEntrenamientos() {
        return null;
    }
}
