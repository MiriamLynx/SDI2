package impl.sdi.business.classes.usuarios;

import com.sdi.infrastructure.Factories;
import com.sdi.persistence.UsuarioDao;

public class ActualizarPassword {

	public void actualizarPassword(String id, String nuevo_password) {
		UsuarioDao dao = Factories.persistence.createUsuarioDao();
		dao.actualizarPassword(id, nuevo_password);
	}

}
