package ej1;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface AlumnoDao {
	
	int anadir (Alumno a) throws SQLException;
	
	Alumno chuparPorPk(int id) throws SQLException;
	
	List<Alumno> chuparTodo() throws SQLException;
	
	List<Grupo> chuparTodosLosGrupos() throws SQLException;
	
	Alumno chuparTodoYElegir() throws SQLException;
	
	void ModificarGrupoYElegir() throws SQLException;
	
	int modificar (Alumno a ) throws SQLException;
	
	void borrar (int id ) throws SQLException;
	
	void BaseDeDatos () throws SQLException;
	
	void ficheros(GrupoConAlumnos grupoConAlumnos, String rutaArchivo) throws IOException;
	
	void ficherosXML();
	
}
