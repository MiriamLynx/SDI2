package impl.sdi.business.classes.usuarios;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Usuario;
import com.sdi.persistence.UsuarioDao;

public class UsuarioValidar {

	public Usuario validar(String id, String password) {
		UsuarioDao dao = Factories.persistence.createUsuarioDao();
		return dao.validar(id, password);
	}

}
