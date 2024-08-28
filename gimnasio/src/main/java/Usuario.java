import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class Usuario {

    private String nombre, identificacion;

    public Object getCedula() {
        return null;
    }
}