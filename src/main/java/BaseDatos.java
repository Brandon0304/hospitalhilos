import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDatos {
    private static final String URL = "jdbc:mysql://localhost:3306/atencion_medica";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void guardarAtencion(String medico, Especialidad especialidad, int numeroPacientes) {
        String query = "INSERT INTO atenciones (medico, especialidad, numero_pacientes) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, medico);
            statement.setString(2, especialidad.name());
            statement.setInt(3, numeroPacientes);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void borrarDatos() {
        String query = "DELETE FROM atenciones";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
            System.out.println("Datos borrados de la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> consultarAtenciones() {
        List<String> atenciones = new ArrayList<>();
        String query = "SELECT medico, especialidad, numero_pacientes, fecha FROM atenciones";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String medico = resultSet.getString("medico");
                String especialidad = resultSet.getString("especialidad");
                int numeroPacientes = resultSet.getInt("numero_pacientes");
                Timestamp fecha = resultSet.getTimestamp("fecha");
                atenciones.add("Médico: " + medico + ", Especialidad: " + especialidad +
                        ", Pacientes atendidos: " + numeroPacientes + ", Fecha: " + fecha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return atenciones;
    }
}
