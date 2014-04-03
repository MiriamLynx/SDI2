package impl.sdi.business.classes.asignaturas;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Asignatura;
import com.sdi.persistence.AsignaturaDao;

public class AsignaturasListado {

	public List<Asignatura> getAsignaturas() throws Exception {

		AsignaturaDao dao = Factories.persistence.createAsignaturaDao();
		return dao.getAsignaturas();

	}
}
