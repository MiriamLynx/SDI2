package impl.sdi.business.classes.asignaturas;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Usuario;
import com.sdi.persistence.AsignaturaDao;

public class VerAlumnosMatriculados {

	public List<Usuario> getAlumnos(String idAsignatura) {

		AsignaturaDao dao = Factories.persistence.createAsignaturaDao();
		return dao.getAlumnos(idAsignatura);
	}

}
