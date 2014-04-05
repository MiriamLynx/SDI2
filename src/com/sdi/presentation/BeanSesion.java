package com.sdi.presentation;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import uo.sdi.util.Check;

import com.sdi.business.AsignaturasService;
import com.sdi.business.SesionService;
import com.sdi.business.UsuariosService;
import com.sdi.business.exception.BusinessException;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Asignatura;
import com.sdi.model.Usuario;

@ManagedBean(name = "controllerSesion")
@SessionScoped
public class BeanSesion implements Serializable {

	private static final long serialVersionUID = -4904448454039912124L;

	// Datos introducidos para login
	private String idUsuario;
	private String password;

	// Usuario actualmente en sesion
	private Usuario usuario;

	// Campo que indica si ha habido un error de login
	private boolean invalido;

	// Campo que indica si ha habido un error cambiando la contraseña
	private boolean nomatch;

	// Datos introducidos para cambiar contraseña
	private String nuevo_password;
	private String nuevo_repetir_password;
	private String antiguo_password;

	// Asignaturas del profesor en sesion
	private Asignatura[] misAsignaturas;

	public String verAsignaturasImpartidas() {
		AsignaturasService service;
		try {
			service = Factories.services.createAsignaturasService();
			misAsignaturas = (Asignatura[]) service.getAsignaturasByProfesorId(
					usuario.getId()).toArray(new Asignatura[0]);

			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public String actualizarPerfil() {
		UsuariosService service;
		try {
			service = Factories.services.createUsuariosService();
			service.actualizarPerfil(usuario);
			switch (usuario.getPrivilegios()) {
			case "alumno":
				setInvalido(false);
				return "exito alumno";
			case "administrador":
				setInvalido(false);
				return "exito admin";
			case "profesor":
				setInvalido(false);
				return "exito profesor";
			}
			return "error";
		} catch (Exception e) {
			e.printStackTrace();
			setInvalido(true);
			return "error";
		}
	}

	public String actualizarPassword() {
		UsuariosService service;
		try {
			Check.validPassword(antiguo_password, nuevo_password,
					nuevo_repetir_password, usuario.getPassword());
			service = Factories.services.createUsuariosService();
			service.actualizarPassword(usuario.getId(), nuevo_password);
			switch (usuario.getPrivilegios()) {
			case "alumno":
				usuario.setPassword(nuevo_password);
				setNomatch(false);
				return "exito alumno";
			case "administrador":
				setNomatch(false);
				usuario.setPassword(nuevo_password);
				return "exito admin";
			case "profesor":
				setNomatch(false);
				usuario.setPassword(nuevo_password);
				return "exito profesor";
			}
			return "error";
		} catch (BusinessException e) {
			setNomatch(true);
			e.printStackTrace();
			return "nomatch";
		}
	}

	public String validar() {
		SesionService service = Factories.services.createSesionService();
		try {
			usuario = service.validar(idUsuario, password);
			if (usuario == null) {
				setInvalido(true);
				return "invalido";
			}
			establecerSesion(usuario);
			switch (usuario.getPrivilegios()) {
			case "alumno":
				setInvalido(false);
				return "exito alumno";
			case "administrador":
				setInvalido(false);
				return "exito admin";
			case "profesor":
				setInvalido(false);
				return "exito profesor";
			}
			return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String logout() {
		finalizarSesion();
		return "home";
	}

	public BeanSesion() {

	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private void establecerSesion(Usuario usuario) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("USUARIO_LOGUEADO", usuario);
	}

	private void finalizarSesion() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.clear();
	}

	public boolean isInvalido() {
		return invalido;
	}

	public void setInvalido(boolean invalido) {
		this.invalido = invalido;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNuevo_password() {
		return nuevo_password;
	}

	public void setNuevo_password(String nuevo_password) {
		this.nuevo_password = nuevo_password;
	}

	public String getNuevo_repetir_password() {
		return nuevo_repetir_password;
	}

	public void setNuevo_repetir_password(String nuevo_repetir_password) {
		this.nuevo_repetir_password = nuevo_repetir_password;
	}

	public String getAntiguo_password() {
		return antiguo_password;
	}

	public void setAntiguo_password(String antiguo_password) {
		this.antiguo_password = antiguo_password;
	}

	public boolean isNomatch() {
		return nomatch;
	}

	public void setNomatch(boolean nomatch) {
		this.nomatch = nomatch;
	}

	public Asignatura[] getMisAsignaturas() {
		return misAsignaturas;
	}

	public void setMisAsignaturas(Asignatura[] misAsignaturas) {
		this.misAsignaturas = misAsignaturas;
	}

}
