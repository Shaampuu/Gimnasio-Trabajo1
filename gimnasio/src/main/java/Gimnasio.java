import enums.TipoClase;
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

    // Método para agregar usuario (Angelica)
    // Método para eliminar usuario
    // Método para actualizar usuario

    public void crearClase(String codigoClase, String nombre, LocalDateTime horario, int capacidad, TipoClase tipo, Entrenador entrenador) throws Exception {
        // Verificar que los parámetros obligatorios no sean nulos
        if (codigoClase == null || nombre == null || horario == null || tipo == null || entrenador == null) {
            throw new Exception("Todos los campos son obligatorios para crear una clase.");
        }

        // Verificar si la clase ya existe con el mismo código
        for (Clase claseExistente : clases) {
            if (claseExistente.getCodigoClase().equals(codigoClase)) { // Aquí se usa getCodigoClase()
                throw new Exception("Ya existe una clase con el código " + codigoClase + ".");
            }
        }

        // Verificar si el entrenador está registrado en el gimnasio
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

        // Crear una nueva clase
        Clase nuevaClase = new Clase(codigoClase, nombre, horario, capacidad, tipo, entrenador);

        // Agregar la nueva clase a la lista de clases del gimnasio
        clases.add(nuevaClase);
    }

    // Método de búsqueda de clases (Juanita)
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
            throw new Exception("Codigo de clase o identificación de cliente no pueden ser nulos.");
        }

        Clase clase = buscarClasePorCodigo(codigoClase);
        if (clase == null) {
            throw new Exception("Clase con codigo " + codigoClase + " no encontrada.");
        }

        Cliente cliente = buscarClientePorIdentificacion(identificacionCliente);
        if (cliente == null) {
            throw new Exception("Cliente con cédula " + identificacionCliente + " no encontrado.");
        }

        if (!clase.isDisponible()) {
            throw new Exception("La clase con codigo " + codigoClase + " no está disponible.");
        }

        if (clase.getInscritos() >= clase.getCapacidad()) {
            throw new Exception("No hay clases disponibles en la clase con ID " + codigoClase + ".");
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
            if (clase.getCodigoClase().equals(codigoClase)) { // Aquí usamos getCodigoClase()
                return clase;
            }
        }
        return null;
    }


    // Método de cancelación de reserva de clases
    public void cancelarReserva(String idReserva) throws Exception {
    }

    // Método de registro de entrenamientos (Simón) (Falta implementar)
    // Método de consulta de historial de entrenamientos (Falta implementar)
    // Método de generación de reportes (Falta implementar)
    // Método de consulta de disponibilidad de una clase (Falta implementar)

}
