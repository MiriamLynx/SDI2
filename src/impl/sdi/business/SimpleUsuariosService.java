package impl.sdi.business;

import impl.sdi.business.classes.usuarios.InsertarUsuario;
import impl.sdi.business.classes.usuarios.ProfesoresListado;

import java.util.List;

import com.sdi.business.UsuariosService;
import com.sdi.business.exception.BusinessException;
import com.sdi.model.Usuario;

public class SimpleUsuariosService implements UsuariosService {

	@Override
	public List<Usuario> getProfesores() throws Exception {
		return new ProfesoresListado().getProfesores();
	}

	@Override
	public void insertarUsuario(String id, String nombre, String apellidos,
			String correo, boolean validado, String password, String privilegios)
			throws BusinessException {
		new InsertarUsuario().insertarUsuario(id, nombre, apellidos, correo,
				validado, password, privilegios);
	}

}
