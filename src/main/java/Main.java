import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("PACIENTES SAN JUAN DE DIOS:");
            System.out.println("1. Iniciar la simulación");
            System.out.println("2. Ver pacientes atendidos");
            System.out.println("3. Borrar datos de la base de datos");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    List<Medico> medicos = new ArrayList<>();
                    medicos.add(new Medico("Dr. Juan", Especialidad.CARDIOLOGIA, 7));
                    medicos.add(new Medico("Dra. Maria", Especialidad.PEDIATRIA, 4));
                    medicos.add(new Medico("Dra. Lucía", Especialidad.NEUROLOGIA, 10));
                    medicos.add(new Medico("Dr. Brandon", Especialidad.GINECOLOGIA, 6));

                    System.out.println("Iniciando simulación...");
                    for (Medico medico : medicos) {
                        medico.start();
                    }

                    for (Medico medico : medicos) {
                        try {
                            medico.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Simulación finalizada.");
                    break;

                case 2:
                    List<String> atenciones = BaseDatos.consultarAtenciones();
                    if (atenciones.isEmpty()) {
                        System.out.println("No hay registros de atenciones en la base de datos.");
                    } else {
                        System.out.println("Pacientes atendidos:");
                        for (String atencion : atenciones) {
                            System.out.println(atencion);
                        }
                    }
                    break;

                case 3:
                    BaseDatos.borrarDatos();
                    break;

                case 4:
                    continuar = false;
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }

        scanner.close();
    }
}
