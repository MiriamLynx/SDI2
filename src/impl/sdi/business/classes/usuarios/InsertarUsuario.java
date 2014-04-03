package impl.sdi.business.classes.usuarios;

import com.sdi.business.exception.BusinessException;
import com.sdi.infrastructure.Factories;
import com.sdi.persistence.UsuarioDao;

public class InsertarUsuario {

	public void insertarUsuario(String id, String nombre, String apellidos,
			String correo, boolean validado, String password, String privilegios)
			throws BusinessException {
		UsuarioDao dao = Factories.persistence.createUsuarioDao();
		dao.insertarUsuario(id, nombre, apellidos, correo, validado, password,
				privilegios);

	}
}
