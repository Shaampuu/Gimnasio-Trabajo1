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
public class Gimnasio {

    private final ArrayList<Clase> clases;
    private final ArrayList<Reserva> reservas;
    private final ArrayList<Cliente> clientes;
    private final ArrayList<Entrenador> entrenadores;

    // crear usuario
    public void crearUsuario(String identificacion, String nombre, String direccion, String correoElectronico, String contrasena) throws Exception {
        if (identificacion == null || nombre == null || direccion == null || correoElectronico == null || contrasena == null) {
            throw new Exception("Todos los campos son obligatorios para crear un usuario.");
        }

        // Verificar que no exista un cliente con la misma identificación
        for (Cliente clienteExistente : clientes) {
            if (clienteExistente.getIdentificacion().equals(identificacion)) {
                throw new Exception("Ya existe un cliente con la identificación " + identificacion + ".");
            }
        }

        // Crear un nuevo cliente
        Cliente nuevoCliente = new Cliente(identificacion, nombre, direccion, correoElectronico, contrasena);
        clientes.add(nuevoCliente);
    }


    //metodo de agregar usuario //angelica

    public void agregarUsuario(Cliente nuevoCliente) throws Exception {
        if (nuevoCliente == null || nuevoCliente.getIdentificacion() == null) {
            throw new Exception("El cliente y su identificación no pueden ser nulos.");
        }

        for (Cliente clienteExistente : clientes) {
            if (clienteExistente.getIdentificacion().equals(nuevoCliente.getIdentificacion())) {
                throw new Exception("Ya existe un cliente con la identificación " + nuevoCliente.getIdentificacion() + ".");
            }
        }

        clientes.add(nuevoCliente);
    }

    //metodo de eliminar usuario

    public void eliminarUsuario(String identificacionCliente) throws Exception {
        if (identificacionCliente == null) {
            throw new Exception("La identificación del cliente no puede ser nula.");
        }

        Cliente clienteAEliminar = buscarClientePorIdentificacion(identificacionCliente);

        if (clienteAEliminar == null) {
            throw new Exception("No se encontró un cliente con la identificación " + identificacionCliente + ".");
        }

        // Eliminar reservas asociadas al cliente
        reservas.removeIf(reserva -> reserva.getCliente().getIdentificacion().equals(identificacionCliente));

        clientes.remove(clienteAEliminar);
    }

    //metodo de actualizar usuario

    public void actualizarUsuario(Cliente clienteActualizado) throws Exception {
        if (clienteActualizado == null || clienteActualizado.getIdentificacion() == null) {
            throw new Exception("El cliente actualizado y su identificación no pueden ser nulos.");
        }

        Cliente clienteExistente = buscarClientePorIdentificacion(clienteActualizado.getIdentificacion());

        if (clienteExistente == null) {
            throw new Exception("No se encontró un cliente con la identificación " + clienteActualizado.getIdentificacion() + ".");
        }

        // Actualizar los datos del cliente existente
        clienteExistente.setNombre(clienteActualizado.getNombre());
        clienteExistente.setDireccion(clienteActualizado.getDireccion());
        clienteExistente.setCorreo(clienteActualizado.getCorreo());
        clienteExistente.setContrasena(clienteActualizado.getContrasena());
        // Puedes agregar aquí cualquier otro campo que desees actualizar
    }


    // Método para crear una clase
    public void crearClase(String codigoClase, String nombre, LocalDateTime horario, int capacidad, TipoClase tipo, Entrenador entrenador) throws Exception {
        if (codigoClase == null || nombre == null || horario == null || tipo == null || entrenador == null) {
            throw new Exception("Todos los campos son obligatorios para crear una clase.");
        }

        // Verificar si el entrenador tiene una cédula válida
        String cedulaEntrenador = entrenador.getIdentificacion();
        if (cedulaEntrenador == null) {
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
            if (cedulaEntrenador.equals(entrenadorExistente.getCedula())) {
                entrenadorRegistrado = true;
                break;
            }
        }

        if (!entrenadorRegistrado) {
            throw new Exception("El entrenador con cédula " + cedulaEntrenador + " no está registrado en el gimnasio.");
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
    Cliente buscarClientePorIdentificacion(String identificacionCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdentificacion().equals(identificacionCliente)) {
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

    public void cancelarReserva(String codigoClase, String identificacionCliente, LocalDate fechaReserva) throws Exception {
        if (codigoClase == null || identificacionCliente == null || fechaReserva == null) {
            throw new Exception("Código de clase, identificación de cliente y fecha de reserva no pueden ser nulos.");
        }

        Reserva reservaACancelar = null;

        for (Reserva reserva : reservas) {
            if (reserva.getClase().getCodigoClase().equals(codigoClase) &&
                    reserva.getCliente().getIdentificacion().equals(identificacionCliente) &&
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

    // Método de consulta de historial de entrenamientos
    private Cliente buscarClientePorCedula(String identificacion) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdentificacion().equals(identificacion)) {
                return cliente;
            }
        }
        return null;
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
