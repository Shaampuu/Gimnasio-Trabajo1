import lombok.*;
import java.util.ArrayList;

@Getter
@Setter

public class Gimnasio {

    private final ArrayList<Clase> clases;
    private final ArrayList<Reserva> reservas;
    private final ArrayList<Cliente> clientes;
    private final ArrayList<Entrenador> entrenadores;



    public Gimnasio(ArrayList<Clase> clases, ArrayList<Reserva> reservas, ArrayList<Cliente> clientes, ArrayList<Entrenador> entrenadores) {
        this.clases = clases;
        this.reservas = reservas;
        this.clientes = clientes;
        this.entrenadores = entrenadores;
    }

    //metodo de agregar usuario //angelica
    //metodo de eliminar usuario
    //metodo de actualizar usuario
    //metodo de busqueda de clases //juanita
    //metodo de reserva de clases
    //metodo de cancelacion de reserva de clases
    //metodo de registro de entrenamientos //simon
    //metodo de consulta de historial de entrenamientos 
    //metodo de generacion de reportes
    // metodo de consulta de disponibilidad de una clase
}




