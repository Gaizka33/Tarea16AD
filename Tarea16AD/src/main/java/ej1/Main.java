package ej1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		try (Connection conexion = Pool.getConnection()) {
			DatabaseMetaData datos = conexion.getMetaData();
			String[] tablas = { "TABLE" };
			ResultSet tabl = datos.getTables(null, null, "%", tablas);
			while (tabl.next()) {
				System.out.println(tabl.getString("NOMBRE"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
