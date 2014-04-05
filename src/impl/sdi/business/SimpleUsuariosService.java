package impl.sdi.business;

import impl.sdi.business.classes.usuarios.ActualizarNota;
import impl.sdi.business.classes.usuarios.ActualizarPassword;
import impl.sdi.business.classes.usuarios.ActualizarPerfil;
import impl.sdi.business.classes.usuarios.InsertarUsuario;
import impl.sdi.business.classes.usuarios.ProfesoresListado;

import java.sql.SQLException;
import java.util.List;

import com.sdi.business.UsuariosService;
import com.sdi.business.exception.BusinessException;
import com.sdi.model.Usuario;

public class SimpleUsuariosService implements UsuariosService {

	@Override
	public List<Usuario> getProfesores() throws SQLException {
		return new ProfesoresListado().getProfesores();
	}

	@Override
	public void insertarUsuario(String id, String nombre, String apellidos,
			String correo, boolean validado, String password, String privilegios)
			throws BusinessException {
		new InsertarUsuario().insertarUsuario(id, nombre, apellidos, correo,
				validado, password, privilegios);
	}

	@Override
	public void actualizarPerfil(Usuario usuario) throws SQLException {
		new ActualizarPerfil().actualizarPerfil(usuario.getId(),
				usuario.getNombre(), usuario.getApellidos(),
				usuario.getEmail(), usuario.getPassword(),
				usuario.getPrivilegios());
	}

	@Override
	public void actualizarPassword(String id, String nuevo_password) {
		new ActualizarPassword().actualizarPassword(id, nuevo_password);
	}

	@Override
	public void actualizarNota(String idAsignatura, String idAlumno, int nota) {
		new ActualizarNota().actualizarNota(idAsignatura, idAlumno, nota);
	}
}
