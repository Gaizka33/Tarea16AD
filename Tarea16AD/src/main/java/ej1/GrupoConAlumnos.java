package ej1;

import java.util.List;

public class GrupoConAlumnos {
    private Grupo grupo;
    private List<Alumno> alumnos;

    public GrupoConAlumnos() {}

    public GrupoConAlumnos(Grupo grupo, List<Alumno> alumnos) {
        this.grupo = grupo;
        this.alumnos = alumnos;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
}
