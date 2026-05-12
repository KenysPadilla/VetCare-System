package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {

    private static Connection instancia;

    private ConexionBD() {
    }

    public static Connection getConexion() throws SQLException {
        if (instancia == null || instancia.isClosed()) {
            try {
                Properties props = new Properties();
                props.load(ConexionBD.class.getResourceAsStream("/config.properties"));

                String url = props.getProperty("db.url");
                String usuario = props.getProperty("db.usuario");
                String password = props.getProperty("db.password");

                instancia = DriverManager.getConnection(url, usuario, password);
                System.out.println("Conexión exitosa a Oracle XE");

            } catch (Exception e) {
                System.err.println("Error al conectar: " + e.getMessage());
            }
        }
        return instancia;
    }

    public static void cerrarConexion() {
        try {
            if (instancia != null && !instancia.isClosed()) {
                instancia.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar: " + e.getMessage());
        }
    }
}
