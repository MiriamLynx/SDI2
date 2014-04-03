package com.sdi.business;

import java.util.List;

import com.sdi.business.exception.BusinessException;
import com.sdi.model.Usuario;

public interface UsuariosService {

	List<Usuario> getProfesores() throws Exception;

	void insertarUsuario(String id, String nombre, String apellidos,
			String correo, boolean validado, String password, String privilegios)
			throws BusinessException;

}