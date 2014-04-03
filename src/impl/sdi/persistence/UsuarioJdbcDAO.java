package impl.sdi.persistence;

import java.util.List;

import com.sdi.model.Usuario;
import com.sdi.persistence.UsuarioDao;

public class UsuarioJdbcDAO implements UsuarioDao {

	private UsuarioJdbc jdbcUsuario = null;

	public UsuarioJdbc getJdbcUsuario() {
		return jdbcUsuario;
	}

	public void setJdbcUsuario(UsuarioJdbc jdbcUsuario) {
		this.jdbcUsuario = jdbcUsuario;
	}

	public UsuarioJdbcDAO() {
		setJdbcUsuario(new UsuarioJdbc());
	}

	@Override
	public List<Usuario> getProfesores() {
		return new UsuarioJdbc().getProfesores();
	}

	@Override
	public Usuario findById(String id) {
		return new UsuarioJdbc().getUsuarioById(id);
	}

	@Override
	public Usuario validar(String id, String password) {
		return new UsuarioJdbc().validar(id, password);
	}

	@Override
	public void insertarUsuario(String id, String nombre, String apellidos,
			String correo, boolean validado, String password, String privilegios) {
		new UsuarioJdbc().insertarUsuario(id, nombre, apellidos, correo,
				validado, password, privilegios);
	}
}
