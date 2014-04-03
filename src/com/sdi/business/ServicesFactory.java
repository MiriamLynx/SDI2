package com.sdi.business;

public interface ServicesFactory {

	AsignaturasService createAsignaturasService();

	UsuariosService createUsuariosService();

	MatriculasService createMatriculasService();

	SesionService createSesionService();

}
