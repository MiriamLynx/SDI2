package com.sdi.presentation;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sdi.business.SesionService;
import com.sdi.business.UsuariosService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Usuario;

@ManagedBean(name = "controllerSesion")
@SessionScoped
public class BeanSesion implements Serializable {

	private static final long serialVersionUID = -4904448454039912124L;

	private String idUsuario;
	private String password;
	private Usuario usuario;
	private boolean invalido;

	public String actualizar() {
		UsuariosService service;
		try {
			service = Factories.services.createUsuariosService();

			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
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

}
