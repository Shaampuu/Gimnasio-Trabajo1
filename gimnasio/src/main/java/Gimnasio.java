import enums.TipoClase;
import enums.TipoEjercicio;
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

    public void agregarUsuario(Usuario usuario) throws Exception{
        if(usuario == null){
            throw new Exception("Usuario no puede ser nulo");
        }
        usuarios.add(usuario);
    }
    public Usuario crearCliente(String nombre, String identificacion, String direccion, String contrasena, String telefono, String correo) throws  Exception {

        if (nombre == null || nombre.isBlank()) {
            throw new Exception("El nombre es obligatorio");
        }

        if (identificacion == null || identificacion.isBlank()) {
            throw new Exception("El número de identificación es obligatorio.");
        }

        if (direccion == null || direccion.isBlank()) {
            throw new Exception("La dirección es obligatoria");
        }

        if (contrasena == null || contrasena.isBlank()) {
            throw new Exception("La contraseña es obligatoria");
        }

        if (telefono == null || telefono.isBlank()) {
            throw new Exception("El teléfono es obligatorio");
        }
        if (correo == null || correo.isBlank()) {
            throw new Exception("El correo es obligatorio");
        }

        return new Cliente(nombre,identificacion, direccion, contrasena, telefono, correo);
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

        // Verificar si el entrenador tiene una cédula válida
        String identificacion = entrenador.getIdentificacion();
        if (identificacion == null) {
            throw new Exception("El entrenador debe tener una cédula válida.");
        }

        // Verificar que no exista una clase con el mismo código
        for (Clase claseExistente : clases) {
            if (claseExistente.getCodigoClase().equals(codigoClase)) {
                throw new Exception("Ya existe una clase con el código " + codigoClase + ".");
            }
        }

        // Verificar si el entrenador está registrado en el gimnasio
        boolean entrenadorRegistrado = false;
        for (Entrenador entrenadorExistente : entrenadores) {
            if (identificacion.equals(entrenadorExistente.getIdentificacion())) {
                entrenadorRegistrado = true;
                break;
            }
        }

        if (!entrenadorRegistrado) {
            throw new Exception("El entrenador con cédula " + identificacion + " no está registrado en el gimnasio.");
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
    public void reservarClase(String codigoClase, String identificacion, LocalDate fechaReserva) throws Exception {
        if (codigoClase == null || identificacion == null) {
            throw new Exception("Código de clase o identificación de cliente no pueden ser nulos.");
        }
        Clase clase = buscarClasePorCodigo(codigoClase);
        if (clase == null) {
            throw new Exception("Clase con código " + codigoClase + " no encontrada.");
        }
        Cliente cliente = buscarClientePorIdentificacion(identificacion);
        if (cliente == null) {
            throw new Exception("Cliente con cédula " + identificacion + " no encontrado.");
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
    Cliente buscarClientePorIdentificacion(String identificacion) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdentificacion().equals(identificacion)) {
                return cliente;
            }
        }
        return null;
    }

    Clase buscarClasePorCodigo(String codigoClase) {
        for (Clase clase : clases) {
            if (clase.getCodigoClase().equals(codigoClase)) {
                return clase;
            }
        }
        return null;
    }
    // Método de cancelación de reserva de clases

    public void cancelarReserva(String codigoClase, String identificacion, LocalDate fechaReserva) throws Exception {
        if (codigoClase == null || identificacion == null || fechaReserva == null) {
            throw new Exception("Código de clase, identificación de cliente y fecha de reserva no pueden ser nulos.");
        }

        Reserva reservaACancelar = null;

        for (Reserva reserva : reservas) {
            if (reserva.getClase().getCodigoClase().equals(codigoClase) &&
                    reserva.getCliente().getIdentificacion().equals(identificacion) &&
                    reserva.getFechaReserva().equals(fechaReserva)) {
                reservaACancelar = reserva;
                break;
            }
        }

        if (reservaACancelar == null) {
            throw new Exception("No se encontró una reserva para cancelar con los datos proporcionados.");
        }

        reservas.remove(reservaACancelar);
        Clase clase = reservaACancelar.getClase();

        clase.setInscritos(clase.getInscritos() - 1);
        clase.setDisponible(true);
    }

    // Método de registro de entrenamientos (Simón)
    public void registrarEntrenamiento(String identificacionCliente, TipoEjercicio tipoEjercicio, int duracion, int caloriasQuemadas, LocalDateTime fechaHora, int idSesion) throws Exception {
        if (identificacionCliente == null || tipoEjercicio == null || fechaHora == null || idSesion < 0) {
            throw new Exception("Datos del entrenamiento no pueden ser nulos");
        }

        Cliente cliente = buscarClientePorIdentificacion(identificacionCliente);
        if (cliente == null) {
            throw new Exception("Cliente con cedula" + identificacionCliente + " no se encontro al usuario");
        }

        Entrenamiento nuevoEntrenamiento = new Entrenamiento(idSesion, tipoEjercicio, duracion, caloriasQuemadas, fechaHora);

        cliente.getHistorialEntrenamientos().add(nuevoEntrenamiento);

        System.out.println("Entrenamiento registrado exitosamente para el cliente con cedula" + identificacionCliente);

    }

    // Método de generación de reportes
    public Clase obtenerClaseMasPopular() {
        if (clases.isEmpty()) {
            return null;
        }

        Clase claseMasPopular = null;
        int maxInscritos = -1;

        for (Clase clase : clases) {
            int numInscritos = 0;
            for (Cliente cliente : clientes) {
                if (cliente.estaInscritoEn(clase)) {
                    numInscritos++;
                }
            }

            if (numInscritos > maxInscritos) {
                maxInscritos = numInscritos;
                claseMasPopular = clase;
            }
        }

        return claseMasPopular;
    }

    public List<Cliente> obtenerTopTresUsuariosMasActivos() {
        Cliente primero = null;
        Cliente segundo = null;
        Cliente tercero = null;

        for (Cliente cliente : clientes) {
            int caloriasQuemadas = cliente.caloriasTotalesQuemadas();

            if (primero == null || caloriasQuemadas > primero.caloriasTotalesQuemadas()) {
                tercero = segundo;
                segundo = primero;
                primero = cliente;
            } else if (segundo == null || caloriasQuemadas > segundo.caloriasTotalesQuemadas()) {
                tercero = segundo;
                segundo = cliente;
            } else if (tercero == null || caloriasQuemadas > tercero.caloriasTotalesQuemadas()) {
                tercero = cliente;
            }
        }

        List<Cliente> topTres = new ArrayList<>();
        if (primero != null) topTres.add(primero);
        if (segundo != null) topTres.add(segundo);
        if (tercero != null) topTres.add(tercero);

        return topTres;
    }

    // Reporte 3: Tipo de ejercicio más practicado (el que más tiempo tiene registrado)
    public TipoEjercicio obtenerTipoEjercicioMasPracticado() {
        if (clientes.isEmpty()) {
            return null;
        }
        return null;
    }

    // Método de consulta de disponibilidad de una clase

    public boolean consultarDisponibilidadClase(String codigoClase) throws Exception {
        if (codigoClase == null) {
            throw new Exception("El código de la clase no puede ser nulo.");
        }

        Clase clase = buscarClasePorCodigo(codigoClase);
        if (clase == null) {
            throw new Exception("No se encontró una clase con el código " + codigoClase + ".");
        }

        return clase.isDisponible();
    }

}
