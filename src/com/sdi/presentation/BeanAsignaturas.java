package com.sdi.presentation;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import com.sdi.business.AsignaturasService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Asignatura;

@ManagedBean(name = "controllerAsignaturas")
@SessionScoped
public class BeanAsignaturas implements Serializable {

	private static final long serialVersionUID = 2426530828727040310L;

	private Asignatura asignatura = new Asignatura();

	private Asignatura[] asignaturas = null;

	public BeanAsignaturas() {
		iniciaAsignatura(null);
	}

	public Asignatura[] getAsignaturas() {
		return (asignaturas);
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura[] asignaturas) {
		this.asignaturas = asignaturas;
	}

	public void iniciaAsignatura(ActionEvent event) {
		asignatura.setId("Codigo");
		asignatura.setNombre("Nombre");
		asignatura.setCurso("Curso");
		asignatura.setCreditos(6);
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

}
