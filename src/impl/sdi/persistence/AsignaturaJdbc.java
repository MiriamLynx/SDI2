package impl.sdi.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import uo.sdi.util.Jdbc;

import com.sdi.model.Asignatura;
import com.sdi.model.Usuario;
import com.sdi.persistence.exception.PersistenceException;

public class AsignaturaJdbc {

	private static final String LISTAR_ASIGNATURAS = "select distinct * from tasignaturas ";
	private static final String GET_PROFESORES_BY_ASIGNATURA_ID = "SELECT distinct u.* FROM Tusuarios u, TImparte i, "
			+ "TAsignaturas a WHERE u.id = i.id_usuario "
			+ "AND a.id = i.id_asignatura" + " AND a.id = ? ";
	private static final String GET_ALUMNOS_BY_ASIGNATURA_ID = "select distinct u.* from tasignaturas a, tmatriculas m, tusuarios u where u.id = m.id_usuario and m.id_asignatura = a.id and a.id = ?";
	private static final String GET_NOTA_BY_ALUMNO_ID = "select calificacion from tmatriculas where id_usuario = ? and id_asignatura = ?";

	private static Connection c = null;

	public List<Asignatura> getAsignaturas() {
		List<Asignatura> asignaturas = new ArrayList<Asignatura>();
		Statement st = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getConnection();
			st = c.createStatement();
			rs = st.executeQuery(LISTAR_ASIGNATURAS);
			while (rs.next()) {
				String id = rs.getString("id");
				Asignatura asignatura = new Asignatura(id,
						rs.getString("nombre"), rs.getInt("creditos"),
						rs.getString("curso"));
				List<Usuario> misProfesores = getProfesoresByAsignaturaId(id);
				asignatura.setProfesores(misProfesores);
				asignaturas.add(asignatura);

			}
			Jdbc.close(rs, st);
			Jdbc.close(c);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}

		return asignaturas;
	}

	public static List<Usuario> getProfesoresByAsignaturaId(String idAsignatura) {

		List<Usuario> profesores = new ArrayList<Usuario>();
		try {
			c = Jdbc.getConnection();
			PreparedStatement ps = c
					.prepareStatement(GET_PROFESORES_BY_ASIGNATURA_ID);
			ps.setString(1, idAsignatura);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Usuario profesor = new Usuario(rs.getString("id"),
						rs.getString("nombre"), rs.getString("apellidos"),
						rs.getString("correo"), rs.getString("password"),
						rs.getBoolean("validado"), rs.getString("privilegios"));
				profesores.add(profesor);
			}
			Jdbc.close(rs, ps);
			Jdbc.close(c);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		return profesores;
	}

	public List<Usuario> getAlumnos(String idAsignatura) {
		List<Usuario> alumnos = new ArrayList<Usuario>();
		try {
			c = Jdbc.getConnection();
			PreparedStatement ps = c
					.prepareStatement(GET_ALUMNOS_BY_ASIGNATURA_ID);
			ps.setString(1, idAsignatura);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Usuario alumno = new Usuario(rs.getString("id"),
						rs.getString("nombre"), rs.getString("apellidos"),
						rs.getString("correo"), rs.getString("password"),
						rs.getBoolean("validado"), rs.getString("privilegios"));
				alumno.setNota(getNota(rs.getString("id"), idAsignatura));
				alumnos.add(alumno);
			}
			Jdbc.close(rs, ps);
			Jdbc.close(c);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		return alumnos;
	}

	public int getNota(String idAlumno, String idAsignatura) {
		int nota = Integer.MAX_VALUE;
		try {
			c = Jdbc.getConnection();
			PreparedStatement ps = c.prepareStatement(GET_NOTA_BY_ALUMNO_ID);
			ps.setString(1, idAlumno);
			ps.setString(2, idAsignatura);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nota = rs.getInt("calificacion");
			}
			Jdbc.close(rs, ps);
			Jdbc.close(c);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		return nota;
	}
}
