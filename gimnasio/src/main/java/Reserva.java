import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Reserva {
    private Clase clase;
    private Cliente cliente;
    private LocalDateTime fechaReserva;

    public Reserva(Clase clase, Cliente cliente, LocalDateTime fechaReserva) {
        this.clase = clase;
        this.cliente = cliente;
        this.fechaReserva = fechaReserva;
    }
}
