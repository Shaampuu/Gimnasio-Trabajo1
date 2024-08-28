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

    //metodo de agregar usuario //angelica
    //metodo de eliminar usuario
    //metodo de actualizar usuario

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
    // Método de consulta de historial de entrenamientos (Falta implementar)
    private Cliente buscarClientePorCedula(String cedula) {
        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equals(cedula)){
                return cliente;
            }
        }
        return null;
    }


    // Método de generación de reportes (Falta implementar)
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
    // Método de consulta de disponibilidad de una clase (Falta implementar)