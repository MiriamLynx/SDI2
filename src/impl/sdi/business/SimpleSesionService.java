package impl.sdi.business;

import impl.sdi.business.classes.usuarios.UsuarioValidar;

import java.sql.SQLException;

import com.sdi.business.SesionService;
import com.sdi.model.Usuario;

public class SimpleSesionService implements SesionService {

	@Override
	public Usuario validar(String idUsuario, String password)
			throws SQLException {
		return new UsuarioValidar().validar(idUsuario, password);
	}

}
