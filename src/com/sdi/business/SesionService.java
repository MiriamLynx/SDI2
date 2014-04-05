package com.sdi.business;

import java.sql.SQLException;

import com.sdi.model.Usuario;

public interface SesionService {

	Usuario validar(String idUsuario, String password) throws SQLException;

}
