package com.sdi.business;

import com.sdi.model.Usuario;

public interface SesionService {

	Usuario validar(String idUsuario, String password) throws Exception;

}
