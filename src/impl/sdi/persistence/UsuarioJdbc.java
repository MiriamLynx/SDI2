package impl.sdi.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import uo.sdi.util.Jdbc;

import com.sdi.business.exception.BusinessException;
import com.sdi.model.Asignatura;
import com.sdi.model.Usuario;
import com.sdi.persistence.exception.PersistenceException;

public class UsuarioJdbc {

	private static final String LISTAR_PROFESORES = "select distinct * from tusuarios where privilegios = 'profesor' ";
	private static final String GET_ASIGNATURAS_BY_PROFESOR_ID = "SELECT distinct a.* FROM Tusuarios u, TImparte i, "
			+ "TAsignaturas a WHERE u.id = i.id_usuario "
			+ "AND a.id = i.id_asignatura" + " AND u.id = ? ";
	private static final String GET_USUARIO_BY_ID = "select distinct u.* from TUsuarios u where u.id = ?";
	private static final String GET_VALIDATED_USER = "select distinct u.* from TUsuarios u where u.id = ? and u.password = ? and u.validado = true";
	private static final String INSERTAR_USUARIO = "INSERT INTO TUSUARIOS VALUES ( ?, ?, ?, ?, ?, ?, ?)";

	private static Connection c = null;

	public List<Usuario> getProfesores() {
		List<Usuario> profesores = new ArrayList<Usuario>();
		Statement st = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			st = c.createStatement();
			rs = st.executeQuery(LISTAR_PROFESORES);
			while (rs.next()) {
				String id = rs.getString("id");
				Usuario profesor = new Usuario(id, rs.getString("nombre"),
						rs.getString("apellidos"), rs.getString("correo"),
						rs.getString("password"), rs.getBoolean("validado"),
						rs.getString("privilegios"));
				List<Asignatura> misAsignaturas = getAsignaturasByProfesorId(id);
				profesor.setAsignaturas(misAsignaturas);
				profesores.add(profesor);

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

		return profesores;
	}

	public static List<Asignatura> getAsignaturasByProfesorId(String id)
			throws SQLException {

		List<Asignatura> asignaturas = new ArrayList<Asignatura>();

		c = Jdbc.getConnection();
		PreparedStatement ps = c
				.prepareStatement(GET_ASIGNATURAS_BY_PROFESOR_ID);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Asignatura asignatura = new Asignatura(rs.getString("id"),
					rs.getString("nombre"), rs.getInt("creditos"),
					rs.getString("curso"));
			asignaturas.add(asignatura);
		}
		Jdbc.close(rs, ps);
		Jdbc.close(c);
		return asignaturas;
	}

	public Usuario getUsuarioById(String id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getConnection();
			ps = c.prepareStatement(GET_USUARIO_BY_ID);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Usuario usuario = new Usuario(id, rs.getString("nombre"),
						rs.getString("apellidos"), rs.getString("correo"),
						rs.getString("password"), rs.getBoolean("validado"),
						rs.getString("privilegios"));
				return usuario;
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
			if (ps != null) {
				try {
					ps.close();
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
		return null;
	}

	public Usuario validar(String id, String password) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getConnection();
			ps = c.prepareStatement(GET_VALIDATED_USER);
			ps.setString(1, id);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while (rs.next()) {
				Usuario usuario = new Usuario(id, rs.getString("nombre"),
						rs.getString("apellidos"), rs.getString("correo"),
						rs.getString("password"), rs.getBoolean("validado"),
						rs.getString("privilegios"));
				return usuario;
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
			if (ps != null) {
				try {
					ps.close();
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
		return null;
	}

	public void insertarUsuario(String id, String nombre, String apellidos,
			String correo, boolean validado, String password, String privilegios) {
		try {
			c = Jdbc.getConnection();
			PreparedStatement ps = c.prepareStatement(INSERTAR_USUARIO);
			ps.setString(1, id);
			ps.setString(2, nombre);
			ps.setString(3, apellidos);
			ps.setString(4, correo);
			ps.setString(5, password);
			ps.setString(6, privilegios);
			ps.setBoolean(7, false);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Invalid SQL or database schema", e);
		} finally {
			if (c != null) {
				try {
					c.close();
				} catch (Exception ex) {
				}
			}
			;
		}
	}
}
