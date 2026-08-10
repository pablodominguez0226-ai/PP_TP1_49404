import java.util.ArrayList;
import java.util.List;

public abstract class Actividad {
    private int id;
    private String titulo;
    private int cupoMaximo;
    public static final int CUPO_MINIMO = 5;
    private List<Inscripcion> inscripciones;

    public Actividad(int id, String titulo, int cupoMaximo) {
        this.id = id;
        this.titulo = titulo;
        this.cupoMaximo = cupoMaximo;
        this.inscripciones = new ArrayList<>();
    }

    // Método abstracto: obliga a las subclases a definir su propia regla de costo
    public abstract double calcularCosto();

    // Método final: no puede ser sobrescrito por ninguna subclase
    public final void mostrarIdentificacion() {
        System.out.println("  [Actividad #" + id + "] " + titulo +
                " | Tipo: " + getClass().getSimpleName() +
                " | Costo Indiv.: $" + calcularCosto());
    }

    public Inscripcion inscribir(Estudiante estudiante) {
        if (inscripciones.size() < cupoMaximo) {
            Inscripcion nuevaInscripcion = new Inscripcion(estudiante, "CONFIRMADA");
            inscripciones.add(nuevaInscripcion);
            return nuevaInscripcion;
        } else {
            System.out.println("  [!] Sin cupo para " + estudiante.getNombre() + " en: " + titulo);
            return null;
        }
    }

    public void mostrarInscripciones() {
        mostrarIdentificacion(); // Llamada al método final
        if (inscripciones.isEmpty()) {
            System.out.println("    Sin estudiantes inscriptos.");
        } else {
            for (Inscripcion ins : inscripciones) {
                System.out.println("    * Inscripto: " + ins.getEstudiante().getNombre() +
                        " | Legajo: " + ins.getEstudiante().getLegajo() +
                        " | Fecha: " + ins.getFecha() +
                        " | Estado: " + ins.getEstado());
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }
}
