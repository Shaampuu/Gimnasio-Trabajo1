import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder

public class Cliente extends Usuario{

    private String  direccion, correo, contrasena;
}
