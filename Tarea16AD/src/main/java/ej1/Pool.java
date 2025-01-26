package ej1;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Pool {

    private static HikariConfig configuracion = new HikariConfig();
    private static HikariDataSource pool;

    static {
        try {
            // Configuración de la conexión a la base de datos
            configuracion.setJdbcUrl("jdbc:mysql://localhost:3306/Alumnado"); // Cambia "tu_base_de_datos" por el nombre real de tu base de datos
            configuracion.setUsername("root"); // Cambia "tu_usuario" por el usuario de la base de datos
            configuracion.setPassword("linarespajero"); // Cambia "tu_contraseña" por la contraseña correspondiente

            configuracion.setMaximumPoolSize(10); // Número máximo de conexiones en el pool
            configuracion.setMinimumIdle(2); // Número mínimo de conexiones inactivas
            configuracion.setIdleTimeout(30000); // Tiempo de espera para liberar conexiones inactivas (en milisegundos)
            configuracion.setConnectionTimeout(30000); // Tiempo máximo para obtener una conexión (en milisegundos)
            configuracion.setMaxLifetime(1800000); // Tiempo máximo de vida de una conexión (en milisegundos)

            // Inicializar el pool de conexiones
            pool = new HikariDataSource(configuracion);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al configurar el pool de conexiones.", e);
        }
    }

    private Pool() {}

    // Método para obtener una conexión del pool
    public static Connection getConnection() throws SQLException {
        if (pool == null) {
            throw new SQLException("El pool de conexiones no está inicializado.");
        }
        return pool.getConnection();
    }

    // Método para cerrar el pool al finalizar la aplicacion, creo que va a ser util 
    public static void apagarPool() {
        if (pool != null) {
            pool.close();
        }
    }
}
