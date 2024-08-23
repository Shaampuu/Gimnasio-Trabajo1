import enums.EstadoClase;
import enums.TipoClase;
import java.time.LocalDateTime;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class Clase {

    // Atributos
    private String Id;
    private String nombre;
    private LocalDateTime horario; //List<String>
    private int capacidad;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private boolean estado;
    private TipoClase tipoClase;
    private Entrenador entrenador;
    //disponible boolean
    //inscritos

    public Clase(String id, String nombre, LocalDateTime horario, int capacidad,LocalDateTime fechaInicio, LocalDateTime fechaFin, boolean estado, TipoClase TIPOCLASE, Entrenador ENTRENADOR){
        this.Id = id;
        this.nombre = nombre;
        this.horario = horario;
        this.capacidad = capacidad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.tipoClase = TIPOCLASE;
        this.entrenador = ENTRENADOR;
    }

    public boolean reservarClase(){
        if(capacidad > 0){
            capacidad--;
            return true;
        }
        return false;
    }

    public void cancalarReserva(){
        capacidad++;
    }

    public String getId() {
        return Id;
    }
    public void setId(String id) {}
    
}
