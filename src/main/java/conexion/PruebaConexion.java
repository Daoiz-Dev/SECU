package conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PruebaConexion {

    public static void main(String[] args) {

        ConexionBD conexionBD = ConexionBD.getInstancia();

        Connection conexion = conexionBD.getConexion();

        if (conexion == null) {
            System.out.println("No se pudo conectar a la base de datos.");
            return;
        }

        System.out.println("Conexión establecida correctamente.");

        try {

            String sql = "SELECT * FROM \"PROYECTO\".usuarios LIMIT 5";

            Statement statement = conexion.createStatement();

            ResultSet resultado = statement.executeQuery(sql);

            System.out.println("Usuarios encontrados:");

            while (resultado.next()) {

                int cantidadColumnas =
                        resultado.getMetaData().getColumnCount();

                for (int i = 1; i <= cantidadColumnas; i++) {

                    String nombreColumna =
                            resultado.getMetaData().getColumnName(i);

                    Object valor =
                            resultado.getObject(i);

                    System.out.print(
                            nombreColumna + ": " + valor + " | "
                    );
                }

                System.out.println();
            }

            resultado.close();
            statement.close();

        } catch (Exception e) {

            System.out.println("Error ejecutando la consulta.");
            e.printStackTrace();
        }
    }
}