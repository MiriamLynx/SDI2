package com.sdi.persistence;

import java.util.List;

import com.sdi.model.Asignatura;
import com.sdi.model.Usuario;

public interface UsuarioDao {

	Usuario findById(String id);

	List<Usuario> getProfesores();

	Usuario validar(String id, String password);

	void insertarUsuario(String id, String nombre, String apellidos,
			String correo, boolean validado, String password, String privilegios);

	void actualizarPerfil(String id, String nombre, String apellidos,
			String email);

	void actualizarPassword(String id, String nuevo_password);

	List<Asignatura> verAsignaturasImpartidas(String id);

	int actualizarNota(String idAsignatura, String idAlumno, int nota);

}