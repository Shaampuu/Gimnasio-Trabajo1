import lombok.*;
import java.util.ArrayList;

@Getter
@Setter

public class Gimnasio {

    private final Clase[][] clases;
    private final ArrayList<Reserva> reservas;
    private final ArrayList<Cliente> usuarios;//cliente
    //entrenadores


    public Gimnasio(Clase[][] clases, ArrayList<Reserva> reservas, ArrayList<Cliente> usuarios) {
        this.clases = clases;
        this.reservas = reservas;
        this.usuarios = usuarios;
    }

    //metodo de agregar usuario

    private Cliente crearUsuario(String cedula, String nombre, String direccion, String correo, String contrasena) throws Exception{

        if(cedula == null || cedula.isBlank()){
            throw new Exception("La cédula es obligatoria");
        }

        if(nombre == null || nombre.isBlank()){
            throw new Exception("El nombre es obligatorio");
        }

        if(direccion == null || direccion.isBlank()){
            throw new Exception("La direccion es obligatorio");
        }

        if(correo == null || correo.isBlank()){
            throw new Exception("El correo es obligatorio");
        }

        if(buscarUsuario(cedula) != null){
            throw new Exception("El cliente ya existe");
        }

        if(contrasena == null || contrasena.isBlank()){
            throw new Exception("La contraseña es obligatorio");
        }

        Cliente usuario = Cliente.builder()
                .cedula(cedula)
                .nombre(nombre)
                .direccion(direccion)
                .correo(correo)
                .contrasena(contrasena)
                .build();

        usuarios.add(usuario);
        return usuario;

    }

    //@Override
    public Cliente buscarUsuario(String cedula){

        for(Cliente usuario : usuarios){
            if(usuario.getCedula().equals(cedula)){
                return usuario;
            }
        }

        return null;
    }

    //metodo de eliminar usuario
    //metodo de actualizar usuario
    //metodo de busqueda de clases
    //metodo de reserva de clases
    //metodo de cancelacion de reserva de clases
    //metodo de registro de entrenamientos
    //metodo de consulta de historial de entrenamientos
    //metodo de generacion de reportes
    // metodo de consulta de disponibilidad de una clase
}




