package impl.sdi.business.classes.usuarios;

import com.sdi.infrastructure.Factories;
import com.sdi.persistence.UsuarioDao;

public class ActualizarNota {

	public int actualizarNota(String idAsignatura, String idAlumno, int nota) {
		UsuarioDao dao = Factories.persistence.createUsuarioDao();
		return dao.actualizarNota(idAsignatura, idAlumno, nota);
	}

}
