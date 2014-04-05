package com.sdi.presentation;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.sdi.business.AsignaturasService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Asignatura;
import com.sdi.model.Usuario;

@ManagedBean(name = "controllerAsignaturas")
@SessionScoped
public class BeanAsignaturas implements Serializable {

	private static final long serialVersionUID = 2426530828727040310L;

	// Listado de asignaturas
	private Asignatura[] asignaturas = null;

	// Listado de alumnos matriculados
	private Usuario[] alumnos = null;
	// Asignatura de la que son los alumnos
	private String idAsignatura;

	public Asignatura[] getAsignaturas() {
		return (asignaturas);
	}

	public void setAsignatura(Asignatura[] asignaturas) {
		this.asignaturas = asignaturas;
	}

	public String verAlumnosMatriculados(String idAsignatura) {
		AsignaturasService service;
		try {
			this.idAsignatura = idAsignatura;
			service = Factories.services.createAsignaturasService();
			alumnos = (Usuario[]) service.getAlumnos(idAsignatura).toArray(
					new Usuario[0]);

			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String listadoAsignaturas() {
		AsignaturasService service;
		try {
			service = Factories.services.createAsignaturasService();
			asignaturas = (Asignatura[]) service.getAsignaturas().toArray(
					new Asignatura[0]);

			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public Usuario[] getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(Usuario[] alumnos) {
		this.alumnos = alumnos;
	}

	public String getIdAsignatura() {
		return idAsignatura;
	}

	public void setIdAsignatura(String idAsignatura) {
		this.idAsignatura = idAsignatura;
	}

}
