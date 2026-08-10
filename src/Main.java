import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // a. Registrar estudiantes
        List<Estudiante> estudiantes = new ArrayList<>();
        Estudiante est1 = new Estudiante("EST-101", "Ana Gómez");
        Estudiante est2 = new Estudiante("EST-102", "Carlos Pérez");
        Estudiante est3 = new Estudiante("EST-103", "Lucía Fernández");
        estudiantes.add(est1);
        estudiantes.add(est2);
        estudiantes.add(est3);

        // b. Construir eventos
        EventoUniversitario evento1 = new EventoUniversitario("EVT-01", "Tech Summit 2026", 20000.0, false);
        EventoUniversitario evento2 = new EventoUniversitario("EVT-02", "Expo Vocacional", 0.0, true);

        // c. Asignar sala a cada evento
        evento1.asignarSala(new Sala(101, "Auditorio Principal"));
        evento2.asignarSala(new Sala(102, "Pabellón Abierto"));

        // d. Crear actividades (Charla y Taller)
        evento1.crearActividad(1, "Keynote: Futuro de la IA", 100, "Charla", "Dr. Martín Rossi");
        evento1.crearActividad(2, "Taller Práctico de Java 17", 2, "Taller", true);  // Con notebook ($5000)
        evento1.crearActividad(3, "Taller de Diseño UX", 10, "Taller", false);      // Sin notebook ($2000)

        evento2.crearActividad(4, "Charla Informativa Becas", 50, "Charla", "Lic. Sofia López");

        // e. Inscribir estudiantes
        evento1.getActividades().get(0).inscribir(est1); // Charla IA
        evento1.getActividades().get(1).inscribir(est1); // Taller Java
        evento1.getActividades().get(1).inscribir(est2); // Taller Java
        evento1.getActividades().get(2).inscribir(est3); // Taller UX

        evento2.getActividades().get(0).inscribir(est2); // Charla Becas

        // f. Mostrar resumen de datos e identificación polimórfica
        System.out.println("=== RESUMEN DEL SISTEMA DE EVENTOS UNIVERSITARIOS ===\n");
        evento1.mostrarDatos();
        System.out.println();
        evento2.mostrarDatos();

        // g. Mostrar el total de eventos creados
        System.out.println("\n==================================================================");
        System.out.println("Total de eventos registrados en el sistema: " + EventoUniversitario.getCantidadEventos());
        System.out.println("==================================================================");
    }
}