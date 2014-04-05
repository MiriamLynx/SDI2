package com.sdi.business;

import java.util.List;

import com.sdi.model.Asignatura;
import com.sdi.model.Usuario;

public interface AsignaturasService {

	List<Asignatura> getAsignaturas() throws Exception;

	List<Asignatura> getAsignaturasByProfesorId(String id);

	List<Usuario> getAlumnos(String idAsignatura);

}