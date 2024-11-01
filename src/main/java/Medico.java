public class Medico extends Thread {
    private String nombre;
    private Especialidad especialidad;
    private int numeroPacientes;

    public Medico(String nombre, Especialidad especialidad, int numeroPacientes) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.numeroPacientes = numeroPacientes;
    }

    @Override
    public void run() {
        for (int i = 0; i < numeroPacientes; i++) {
            try {
                Thread.sleep((int) (Math.random() * 1000)); // Simular tiempo de atención
                System.out.println(nombre + " (" + especialidad + ") atendió al paciente " + (i + 1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        BaseDatos.guardarAtencion(nombre, especialidad, numeroPacientes);
    }
}
