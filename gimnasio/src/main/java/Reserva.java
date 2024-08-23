import java.time.LocalDate;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter

public class Reserva {


    private Clase Id;
    private Cliente cedula;
    private LocalDate fechaReserva;
}
