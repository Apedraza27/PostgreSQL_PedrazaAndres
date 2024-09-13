package Conexion_BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:postgresql://uoukrgt18wamszk76nt5:gHHHI90POWahOuE4FZ1sPodXEjwIW5@bqiwj81b3x2ax25n1mdm-postgresql.services.clever-cloud.com:50013/bqiwj81b3x2ax25n1mdm";
    private static final String USER = "uoukrgt18wamszk76nt5"; 
    private static final String PASSWORD = "gHHHI90POWahOuE4FZ1sPodXEjwIW5"; 

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("PostgreSQL driver not found", e);
        }

        // Obtener la conexión
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
