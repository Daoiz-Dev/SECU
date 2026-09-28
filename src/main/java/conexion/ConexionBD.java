package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static ConexionBD instancia;
    private Connection conexion;

    private static final String URL =
            "jdbc:postgresql://localhost:5433/proyecto?currentSchema=PROYECTO";

    private static final String USUARIO = "postgres";
    private static final String PASSWORD = "postgres";

    private ConexionBD() {
        try {
            conexion = DriverManager.getConnection(
                    URL,
                    USUARIO,
                    PASSWORD
            );

            System.out.println("Conexión a PostgreSQL exitosa.");

        } catch (SQLException e) {
            System.out.println("Error al conectar con PostgreSQL.");
            e.printStackTrace();
        }
    }

    public static ConexionBD getInstancia() {

        if (instancia == null) {
            instancia = new ConexionBD();
        }

        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }
}