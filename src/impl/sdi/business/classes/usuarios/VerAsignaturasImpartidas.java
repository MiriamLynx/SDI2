package impl.sdi.business.classes.usuarios;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Asignatura;
import com.sdi.persistence.UsuarioDao;

public class VerAsignaturasImpartidas {

	public List<Asignatura> getAsignaturas(String id) {
		UsuarioDao dao = Factories.persistence.createUsuarioDao();
		return dao.verAsignaturasImpartidas(id);
	}

}
