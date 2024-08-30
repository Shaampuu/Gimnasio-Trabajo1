import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Cliente extends  Usuario{
    private String direccion;
    private String correo;
    private String telefono;
    private String contrasena;
    private List<Entrenamiento> historialEntrenamientos;

    public Cliente(String identificacion, String nombre, String direccion, String correo, String telefono, String contrasena) {
        super(nombre, identificacion);
        this.direccion = direccion;
        this.correo = correo;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.historialEntrenamientos = new ArrayList<>();
    }


    public int caloriasTotalesQuemadas() {
        int totalCalorias = 0;
        for (Entrenamiento entrenamiento : historialEntrenamientos) {
            totalCalorias += entrenamiento.getCaloriasQuemadas();
        }
        return totalCalorias;
    }

    public boolean estaInscritoEn(Clase clase) {
        // Implementar la lógica para verificar si el cliente está inscrito en la clase
        return false;
    }
}
