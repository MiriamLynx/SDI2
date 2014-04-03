package com.sdi.persistence;

import java.util.List;

import com.sdi.model.Usuario;

public interface UsuarioDao {

	Usuario findById(String id);

	List<Usuario> getProfesores();

	Usuario validar(String id, String password);

	void insertarUsuario(String id, String nombre, String apellidos,
			String correo, boolean validado, String password, String privilegios);

}