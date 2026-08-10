import java.util.ArrayList;
import java.util.List;

public class EventoUniversitario {
    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos = 0;

    private Sala sala;
    private List<Actividad> actividades; // Colección polimórfica (guarda Charlas y Talleres)

    public EventoUniversitario(String id, String titulo, double costoBase, boolean gratuito) {
        this.id = id;
        this.titulo = titulo;
        this.costoBase = costoBase;
        this.gratuito = gratuito;
        this.actividades = new ArrayList<>();
        cantidadEventos++;
    }

    // Sobrecarga de crearActividad para crear 'Charla'
    public void crearActividad(int id, String titulo, int cupo, String tipo, String disertante) {
        if ("Charla".equalsIgnoreCase(tipo)) {
            actividades.add(new Charla(id, titulo, cupo, disertante));
        }
    }

    // Sobrecarga de crearActividad para crear 'Taller'
    public void crearActividad(int id, String titulo, int cupo, String tipo, boolean requiereNotebook) {
        if ("Taller".equalsIgnoreCase(tipo)) {
            actividades.add(new Taller(id, titulo, cupo, requiereNotebook));
        }
    }

    public double calcularCostoEstimado() {
        if (gratuito) {
            return 0.0;
        }

        double costoActividades = 0.0;
        for (Actividad act : actividades) {
            costoActividades += act.calcularCosto(); // Polimorfismo en acción
        }

        // Fórmula: (costoBase + costo de actividades) * 1.21 (21% impuestos)
        return (costoBase + costoActividades) * 1.21;
    }

    public void asignarSala(Sala sala) {
        this.sala = sala;
    }

    public void mostrarDatos() {
        System.out.println("==================================================================");
        System.out.println("EVENTO: " + titulo + " [ID: " + id + "]");
        System.out.println("Costo Base: $" + costoBase + " | Gratuito: " + (gratuito ? "Sí" : "No"));
        System.out.printf("Costo Estimado Final (c/ Impuestos 21%%): $%.2f%n", calcularCostoEstimado());
        System.out.println("Sala Asignada: " + (sala != null ? sala.getNombre() : "Sin sala"));
        System.out.println("\nACTIVIDADES DEL EVENTO:");

        if (actividades.isEmpty()) {
            System.out.println("  Sin actividades.");
        } else {
            for (Actividad act : actividades) {
                act.mostrarInscripciones();
            }
        }
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public static int getCantidadEventos() {
        return cantidadEventos;
    }
}
