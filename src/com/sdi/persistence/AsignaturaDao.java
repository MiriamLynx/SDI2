package com.sdi.persistence;

import java.util.List;

import com.sdi.model.Asignatura;
import com.sdi.model.Usuario;

public interface AsignaturaDao {

	List<Asignatura> getAsignaturas();

	List<Usuario> getAlumnos(String idAsignatura);

}