import enums.TipoClase;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Gimnasio {

    private final ArrayList<Clase> clases;
    private final ArrayList<Reserva> reservas;
    private final ArrayList<Cliente> clientes;
    private final ArrayList<Entrenador> entrenadores;
    private final ArrayList<Usuario> usuarios;

    //metodo de agregar usuario //angelica

    public void agregarUsuario(Usuario usuario) throws Exception{
        if(usuario == null){
            throw new Exception("Usuario no puede ser nulo");
        }
        usuarios.add(usuario);
    }
    private Usuario crearCliente(String nombre, String identificacion, String direccion, String password, String correo, String telefono) throws  Exception {

        if (nombre == null || nombre.isBlank()) {
            throw new Exception("El nombre es obligatorio");
        }

        if (identificacion == null || identificacion.isBlank()) {
            throw new Exception("El número de identificación es obligatorio.");
        }

        if (direccion == null || direccion.isBlank()) {
            throw new Exception("La dirección es obligatoria");
        }

        if (password == null || password.isBlank()) {
            throw new Exception("La contraseña es obligatoria");
        }

        if (correo == null || correo.isBlank()) {
            throw new Exception("El correo es obligatorio");
        }

        if (telefono == null || telefono.isBlank()) {
            throw new Exception("El teléfono es obligatorio");
        }
        return new Cliente(nombre,identificacion, direccion, password, telefono,correo);
    }
    public Usuario crearEntrenador(String nombre, String identificacion, String especialidad) throws Exception{

        if (nombre == null || nombre.isBlank()) {
            throw new Exception("El nombre es obligatorio");
        }

        if (identificacion == null || identificacion.isBlank()) {
            throw new Exception("El número de identificación es obligatorio.");
        }

        if (especialidad == null || especialidad.isBlank()) {
            throw new Exception("La especialidad es obligatoria");
        }

        return new Entrenador(nombre, identificacion, especialidad);

    }

    //método de actualizar usuario

    public void actualizarUsuario(String nombre, String direccion, String identificacion, String correoElectronico, String contrasena) throws Exception{

        if(nombre == null || nombre.isBlank()){
            throw new Exception("El nombre es obligatorio");
        }

        if(direccion == null || direccion.isBlank()){
            throw new Exception("La dirección es obligatoria");
        }

        if(correoElectronico == null || correoElectronico.isBlank()){
            throw new Exception("El correo electronico es obligatorio");
        }

        if(contrasena == null || contrasena.isBlank()){
            throw new Exception("La contraseña es obligatoria");
        }

        if(obtenerUsuario(identificacion) == null){
            throw new Exception("No existe un usuario con el número de identificación: "+identificacion);
        }

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getIdentificacion().equals(identificacion)) {
                Usuario usuario = new Usuario(nombre, identificacion );
                usuarios.set(i, usuario);
                break;
            }
        }
    }

    private Usuario obtenerUsuario(String numeroIdentificacion){
        for(int i = 0; i < usuarios.size(); i++){
            if(usuarios.get(i).getIdentificacion().equals(numeroIdentificacion)){
                return usuarios.get(i);
            }
        }
        return null;
    }

    //método de eliminar usuario

    public void eliminarUsuario(String identificacion) throws Exception{
        Usuario usuario = obtenerUsuario(identificacion);
        if (usuario != null) {
            usuarios.remove(usuario);
        }else{
            throw new Exception("El usuario no existe");
        }
    }

    // Método para crear una clase
    public void crearClase(String codigoClase, String nombre, LocalDateTime horario, int capacidad, TipoClase tipo, Entrenador entrenador) throws Exception {
        if (codigoClase == null || nombre == null || horario == null || tipo == null || entrenador == null) {
            throw new Exception("Todos los campos son obligatorios para crear una clase.");
        }
        for (Clase claseExistente : clases) {
            if (claseExistente.getCodigoClase().equals(codigoClase)) {
                throw new Exception("Ya existe una clase con el código " + codigoClase + ".");
            }
        }
        boolean entrenadorRegistrado = false;
        for (Entrenador entrenadorExistente : entrenadores) {
            if (entrenadorExistente.getCedula().equals(entrenador.getCedula())) {
                entrenadorRegistrado = true;
                break;
            }
        }
        if (!entrenadorRegistrado) {
            throw new Exception("El entrenador con cédula " + entrenador.getCedula() + " no está registrado en el gimnasio.");
        }

        Clase nuevaClase = new Clase(codigoClase, nombre, horario, capacidad, tipo, entrenador);
        clases.add(nuevaClase);
    }

    // Método de búsqueda de clases
    public List<Clase> buscarClases(TipoClase tipo, String nombreInstructor, LocalDateTime horario) {
        List<Clase> resultados = new ArrayList<>();
        for (Clase clase : clases) {
            if (clase != null) {
                boolean coincideTipo = (tipo == null || clase.getTipoClase() == tipo);
                boolean coincideInstructor = (nombreInstructor == null ||
                        (clase.getEntrenador() != null && clase.getEntrenador().getNombre().equalsIgnoreCase(nombreInstructor)));
                boolean coincideHorario = (horario == null || clase.getHorario().equals(horario));

                if (coincideTipo && coincideInstructor && coincideHorario) {
                    resultados.add(clase);
                }
            }
        }
        return resultados;
    }

    // Método para reservar clases
    public void reservarClase(String codigoClase, String identificacionCliente, LocalDate fechaReserva) throws Exception {
        if (codigoClase == null || identificacionCliente == null) {
            throw new Exception("Código de clase o identificación de cliente no pueden ser nulos.");
        }
        Clase clase = buscarClasePorCodigo(codigoClase);
        if (clase == null) {
            throw new Exception("Clase con código " + codigoClase + " no encontrada.");
        }
        Cliente cliente = buscarClientePorIdentificacion(identificacionCliente);
        if (cliente == null) {
            throw new Exception("Cliente con cédula " + identificacionCliente + " no encontrado.");
        }
        if (!clase.isDisponible()) {
            throw new Exception("La clase con código " + codigoClase + " no está disponible.");
        }
        if (clase.getInscritos() >= clase.getCapacidad()) {
            throw new Exception("No hay disponibilidad en la clase con código " + codigoClase + ".");
        }

        Reserva reserva = new Reserva(clase, cliente, fechaReserva);
        reservas.add(reserva);

        clase.setInscritos(clase.getInscritos() + 1);

        if (clase.getInscritos() >= clase.getCapacidad()) {
            clase.setDisponible(false);
        }
    }

    // Métodos privados para buscar cliente y clase
    private Cliente buscarClientePorIdentificacion(String identificacionCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdentificacion().equals(identificacionCliente)) {
                return cliente;
            }
        }
        return null;
    }

    private Clase buscarClasePorCodigo(String codigoClase) {
        for (Clase clase : clases) {
            if (clase.getCodigoClase().equals(codigoClase)) {
                return clase;
            }
        }
        return null;
    }
    // Método de cancelación de reserva de clases

    // Método de registro de entrenamientos (Simón) (Falta implementar)
    // Método de consulta de historial de entrenamientos (Falta implementar)
    // Método de generación de reportes (Falta implementar)
    // Método de consulta de disponibilidad de una clase (Falta implementar)

}
