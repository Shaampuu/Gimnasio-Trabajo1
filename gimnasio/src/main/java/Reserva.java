import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Reserva {
    private Clase clase;
    private Cliente cliente;
    private LocalDate fechaReserva;

    public Reserva(Clase clase, Cliente cliente, LocalDate fechaReserva) {
        this.clase = clase;
        this.cliente = cliente;
        this.fechaReserva = fechaReserva;
    }
}
