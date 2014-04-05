package impl.sdi.business.classes.usuarios;

import com.sdi.infrastructure.Factories;
import com.sdi.persistence.UsuarioDao;

public class ActualizarPerfil {

	public void actualizarPerfil(String id, String nombre, String apellidos,
			String email, String password, String privilegios) {
		UsuarioDao dao = Factories.persistence.createUsuarioDao();
		dao.actualizarPerfil(id, nombre, apellidos, email);

	}
}
