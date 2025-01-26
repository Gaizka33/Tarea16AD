package ej1;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AlumnoDaoImpl implements AlumnoDao {

	private static AlumnoDaoImpl instancia;

	static {
		instancia = new AlumnoDaoImpl();
	}

	public static AlumnoDaoImpl getInstancia() {
		return instancia;
	}

	public <T> T elegir(ArrayList<T> lista) {
		// Mostrar elementos con índices
		System.out.println("Estos son los elementos disponibles:");
		for (int i = 0; i < lista.size(); i++) {
			System.out.printf("[%d] %s%n", i, lista.get(i).toString());
		}

		// Pedir elección al usuario
		System.out.println("Elige el índice del elemento:");
		try (Scanner scanner = new Scanner(System.in)) {
			int eleccion = scanner.nextInt();

			// Validar que la elección está dentro del rango
			if (eleccion >= 0 && eleccion < lista.size()) {
				return lista.get(eleccion);
			} else {
				System.out.println("Índice fuera de rango. Operación cancelada.");
				return null;
			}
		}
	}

	@Override
	public int anadir(Alumno a) throws SQLException {
		String query = "INSERT INTO Alumno (NIA, NOMBRE, APELLIDO, GENERO, FECHANACIMIENTO, CICLO, CURSO, GRUPO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = Pool.getConnection().prepareStatement(query)) {
			ps.setInt(1, a.getNia());
			ps.setString(2, a.getNombre());
			ps.setString(3, a.getApellido());
			ps.setString(4, String.valueOf(a.getGenero()));
			ps.setDate(5, a.getFechaNacimiento());
			ps.setString(6, a.getCiclo());
			ps.setString(7, a.getCurso());
			ps.setInt(8, a.getGrupo());
			return ps.executeUpdate();
		}
	}

	@Override
	public Alumno chuparPorPk(int id) throws SQLException {
		String query = "SELECT NIA, NOMBRE, APELLIDO, GENERO, FECHANACIMIENTO, CICLO, CURSO, GRUPO FROM Alumno WHERE NIA = ?";
		try (PreparedStatement ps = Pool.getConnection().prepareStatement(query)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new Alumno(rs.getInt("NIA"), rs.getString("NOMBRE"), rs.getString("APELLIDO"),
							rs.getString("GENERO").charAt(0), rs.getDate("FECHANACIMIENTO"), rs.getString("CICLO"),
							rs.getString("CURSO"), rs.getInt("GRUPO"));
				}
			}
		}
		return null;
	}

	@Override
	public List<Alumno> chuparTodo() throws SQLException {
		List<Alumno> alumnos = new ArrayList<>();
		String query = "SELECT NIA, NOMBRE, APELLIDO, GENERO, FECHANACIMIENTO, CICLO, CURSO, GRUPO FROM Alumno";
		try (PreparedStatement ps = Pool.getConnection().prepareStatement(query); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Alumno a = new Alumno(rs.getInt("NIA"), rs.getString("NOMBRE"), rs.getString("APELLIDO"),
						rs.getString("GENERO").charAt(0), rs.getDate("FECHANACIMIENTO"), rs.getString("CICLO"),
						rs.getString("CURSO"), rs.getInt("GRUPO"));
				alumnos.add(a);
			}
		}
		return alumnos;
	}

	@Override
	public Alumno chuparTodoYElegir() throws SQLException {
		List<Alumno> alumnos = chuparTodo();
		if (alumnos.isEmpty()) {
			System.out.println("No hay alumnos disponibles.");
			return null;
		}
		return elegir(new ArrayList<>(alumnos));
	}

	public void ModificarGrupoYElegir() throws SQLException {
		Alumno alumnoSeleccionado = chuparTodoYElegir();
		if (alumnoSeleccionado == null) {
			return;
		}

		List<Grupo> grupos = chuparTodosLosGrupos();
		if (grupos.isEmpty()) {
			System.out.println("No hay grupos disponibles.");
			return;
		}

		System.out.println("Elige un nuevo grupo para el alumno seleccionado:");
		Grupo grupoSeleccionado = elegir(new ArrayList<>(grupos));
		if (grupoSeleccionado != null) {
			alumnoSeleccionado.setGrupo(grupoSeleccionado.getId());
			modificar(alumnoSeleccionado);
			System.out.println("Grupo actualizado correctamente.");
		} else {
			System.out.println("No se realizó ningún cambio.");
		}
	}

	@Override
	public List<Grupo> chuparTodosLosGrupos() throws SQLException {
		List<Grupo> grupos = new ArrayList<>();
		String query = "SELECT DISTINCT GRUPO AS ID, 'Grupo ' || GRUPO AS NOMBRE FROM Alumno";
		try (PreparedStatement ps = Pool.getConnection().prepareStatement(query); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				grupos.add(new Grupo(rs.getInt("ID"), rs.getString("NOMBRE")));
			}
		}
		return grupos;
	}

	@Override
	public int modificar(Alumno a) throws SQLException {
		String query = "UPDATE Alumno SET NOMBRE = ?, APELLIDO = ?, GENERO = ?, FECHANACIMIENTO = ?, CICLO = ?, CURSO = ?, GRUPO = ? WHERE NIA = ?";
		try (PreparedStatement ps = Pool.getConnection().prepareStatement(query)) {
			ps.setString(1, a.getNombre());
			ps.setString(2, a.getApellido());
			ps.setString(3, String.valueOf(a.getGenero()));
			ps.setDate(4, a.getFechaNacimiento());
			ps.setString(5, a.getCiclo());
			ps.setString(6, a.getCurso());
			ps.setInt(7, a.getGrupo());
			ps.setInt(8, a.getNia());
			return ps.executeUpdate();
		}
	}

	@Override
	public void borrar(int id) throws SQLException {
		String query = "DELETE FROM Alumno WHERE NIA = ?";
		try (PreparedStatement ps = Pool.getConnection().prepareStatement(query)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		}
	}

	@Override
	public void BaseDeDatos() throws SQLException {
		// Método por implementar
	}

	@Override
	public void ficheros(GrupoConAlumnos grupoConAlumnos, String rutaArchivo) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(rutaArchivo), grupoConAlumnos);
		System.out.println("Datos guardados en JSON: " + rutaArchivo);
	}

	@Override
	public void ficherosXML() {
		// Método por implementar
	}
}
