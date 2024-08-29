import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;


@Getter
@Setter
@Builder

public class Cliente extends Usuario{

    private String  direccion, correo, contrasena;


    public List getHistorialEntrenamientos() {
        return null;
    }

    public boolean estaInscritoEn(Clase clase) {
        return false;
    }

    public int caloriasTotalesQuemadas() {
        return 0;
    }
}
