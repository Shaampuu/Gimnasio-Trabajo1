import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;


@Getter
@Setter
@Builder

public class Cliente extends Usuario{

    private String  direccion, correo, contrasena;


    public Arrays getHistorialEntrenamientos() {
        return null;
    }
}
