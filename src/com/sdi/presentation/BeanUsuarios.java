package com.sdi.presentation;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import uo.sdi.util.Check;

import com.sdi.business.UsuariosService;
import com.sdi.business.exception.BusinessException;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Usuario;

@ManagedBean(name = "controllerUsuarios")
@SessionScoped
public class BeanUsuarios implements Serializable {

	private static final long serialVersionUID = 2220767639613106740L;

	private Usuario[] profesores = null;

	private Usuario profesor = new Usuario();

	private Usuario usuarioLogin = new Usuario();

	private String nuevo_id;
	private String nuevo_nombre;
	private String nuevo_apellidos;
	private String nuevo_correo;
	private boolean nuevo_validado;
	private String nuevo_password;
	private String nuevo_privilegios;
	private String nuevo_repetir_password;
	private boolean registro_incorrecto;

	public BeanUsuarios() {
		iniciaUsuario(null);
	}

	public void iniciaUsuario(ActionEvent event) {
		profesor.setId("IdUser");
		profesor.setNombre("Nombre");
		profesor.setApellidos("Apellidos");
		profesor.setEmail("email@domain.com");
	}

	public String listadoProfesores() {
		UsuariosService service;
		try {
			service = Factories.services.createUsuariosService();
			setProfesores((Usuario[]) service.getProfesores().toArray(
					new Usuario[0]));

			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public String registro() {
		UsuariosService service;
		try {
			Check.validPassword(nuevo_password, nuevo_repetir_password);
			service = Factories.services.createUsuariosService();
			service.insertarUsuario(nuevo_id, nuevo_nombre, nuevo_apellidos,
					nuevo_correo, nuevo_validado, nuevo_password,
					nuevo_privilegios);
			setRegistro_incorrecto(false);
			return "exito";

		} catch (BusinessException e) {
			setRegistro_incorrecto(true);
			e.printStackTrace();
			return "incorrecto";
		}
	}

	public Usuario[] getProfesores() {
		return profesores;
	}

	public void setProfesores(Usuario[] profesores) {
		this.profesores = profesores;
	}

	public Usuario getProfesor() {
		return profesor;
	}

	public void setProfesor(Usuario profesor) {
		this.profesor = profesor;
	}

	public Usuario getUsuarioLogin() {
		return usuarioLogin;
	}

	public void setUsuarioLogin(Usuario usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	public String getNuevo_id() {
		return nuevo_id;
	}

	public void setNuevo_id(String nuevo_id) {
		this.nuevo_id = nuevo_id;
	}

	public String getNuevo_nombre() {
		return nuevo_nombre;
	}

	public void setNuevo_nombre(String nuevo_nombre) {
		this.nuevo_nombre = nuevo_nombre;
	}

	public String getNuevo_apellidos() {
		return nuevo_apellidos;
	}

	public void setNuevo_apellidos(String nuevo_apellidos) {
		this.nuevo_apellidos = nuevo_apellidos;
	}

	public String getNuevo_correo() {
		return nuevo_correo;
	}

	public void setNuevo_correo(String nuevo_correo) {
		this.nuevo_correo = nuevo_correo;
	}

	public boolean isNuevo_validado() {
		return nuevo_validado;
	}

	public void setNuevo_validado(boolean nuevo_validado) {
		this.nuevo_validado = nuevo_validado;
	}

	public String getNuevo_password() {
		return nuevo_password;
	}

	public void setNuevo_password(String nuevo_password) {
		this.nuevo_password = nuevo_password;
	}

	public String getNuevo_privilegios() {
		return nuevo_privilegios;
	}

	public void setNuevo_privilegios(String nuevo_privilegios) {
		this.nuevo_privilegios = nuevo_privilegios;
	}

	public String getNuevo_repetir_password() {
		return nuevo_repetir_password;
	}

	public void setNuevo_repetir_password(String nuevo_repetir_password) {
		this.nuevo_repetir_password = nuevo_repetir_password;
	}

	public boolean isRegistro_incorrecto() {
		return registro_incorrecto;
	}

	public void setRegistro_incorrecto(boolean registro_incorrecto) {
		this.registro_incorrecto = registro_incorrecto;
	}
}
