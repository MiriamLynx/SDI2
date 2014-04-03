package com.sdi.model;

import java.util.List;

public class Asignatura {

	private String id;
	private String nombre;
	private int creditos;
	private String curso;

	private List<Usuario> profesores;
	private List<Matricula> matriculas;

	public Asignatura(String id, String nombre, int creditos, String curso) {
		this.setId(id);
		this.setCreditos(creditos);
		this.setNombre(nombre);
		this.setCurso(curso);
	}

	public Asignatura() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setProfesores(List<Usuario> profesores) {
		this.profesores = profesores;
	}

	public List<Usuario> getProfesores() {
		return profesores;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("");
		if (nombre != null)
			builder.append(nombre);
		builder.append(" ");
		return builder.toString();
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

}
