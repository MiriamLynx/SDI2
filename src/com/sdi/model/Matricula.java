package com.sdi.model;

public class Matricula {

	private Usuario alumno;
	private Asignatura asignatura;
	private int calificacion;

	public Matricula(Usuario alumno, Asignatura asignatura, int calificacion) {
		this.setAlumno(alumno);
		this.setAsignatura(asignatura);
		this.setCalificacion(calificacion);
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public Usuario getAlumno() {
		return alumno;
	}

	public void setAlumno(Usuario alumno) {
		this.alumno = alumno;
	}

	@Override
	public String toString() {
		return "Matricula [id_alumno=" + alumno + ", id_asignatura="
				+ asignatura + "]";
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

}
