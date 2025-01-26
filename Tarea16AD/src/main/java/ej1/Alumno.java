package ej1;

public class Alumno {
    private int nia;
    private String nombre;
    private String apellido;
    private char genero;
    private java.sql.Date fechaNacimiento; // Usamos java.sql.Date para bases de datos
    private String ciclo;
    private String curso;
    private int grupo;

    // Constructor vacío
    public Alumno() {}

    // Constructor con parámetros
    public Alumno(int nia, String nombre, String apellido, char genero, java.sql.Date fechaNacimiento, String ciclo, String curso, int grupo) {
        this.nia = nia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.ciclo = ciclo;
        this.curso = curso;
        this.grupo = grupo;
    }

    // Getters y Setters
    public int getNia() {
        return nia;
    }

    public void setNia(int nia) {
        this.nia = nia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public java.sql.Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(java.sql.Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nia=" + nia +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", genero=" + genero +
                ", fechaNacimiento=" + fechaNacimiento +
                ", ciclo='" + ciclo + '\'' +
                ", curso='" + curso + '\'' +
                ", grupo=" + grupo +
                '}';
    }
}
