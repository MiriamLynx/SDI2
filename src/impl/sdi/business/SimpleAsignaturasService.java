package impl.sdi.business;

import impl.sdi.business.classes.asignaturas.AsignaturasListado;

import java.util.List;

import com.sdi.business.AsignaturasService;
import com.sdi.model.Asignatura;

public class SimpleAsignaturasService implements AsignaturasService {

	@Override
	public List<Asignatura> getAsignaturas() throws Exception {
		return new AsignaturasListado().getAsignaturas();
	}

}
