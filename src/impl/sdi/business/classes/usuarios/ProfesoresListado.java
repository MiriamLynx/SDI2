package impl.sdi.business.classes.usuarios;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Usuario;
import com.sdi.persistence.UsuarioDao;

public class ProfesoresListado {

	public List<Usuario> getProfesores() throws Exception {

		UsuarioDao dao = Factories.persistence.createUsuarioDao();
		return dao.getProfesores();

	}
}
