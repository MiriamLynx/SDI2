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
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception ex) {
				}
			}
			;
			if (st != null) {
				try {
					st.close();
				} catch (Exception ex) {
				}
			}
			;
			if (c != null) {
				try {
					c.close();
				} catch (Exception ex) {
				}
			}
			;
		}

		return asignaturas;
	}

	public static List<Usuario> getProfesoresByAsignaturaId(String idAsignatura)
			throws SQLException {

		List<Usuario> profesores = new ArrayList<Usuario>();

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
		return profesores;
	}
}
