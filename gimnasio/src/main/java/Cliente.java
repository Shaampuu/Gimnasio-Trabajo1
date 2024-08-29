import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String identificacion;
    private String nombre;
    private String direccion;
    private String correo;
    private String contrasena;
    private List<Entrenamiento> historialEntrenamientos;

    public Cliente(String identificacion, String nombre, String direccion, String correo, String contrasena) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
        this.contrasena = contrasena;
        this.historialEntrenamientos = new ArrayList<>();
    }

    // Getters y Setters

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public List<Entrenamiento> getHistorialEntrenamientos() {
        return historialEntrenamientos;
    }

    public void setHistorialEntrenamientos(List<Entrenamiento> historialEntrenamientos) {
        this.historialEntrenamientos = historialEntrenamientos;
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
