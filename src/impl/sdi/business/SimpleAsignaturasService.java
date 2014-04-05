package impl.sdi.business;

import impl.sdi.business.classes.asignaturas.AsignaturasListado;
import impl.sdi.business.classes.asignaturas.VerAlumnosMatriculados;
import impl.sdi.business.classes.usuarios.VerAsignaturasImpartidas;

import java.util.List;

import com.sdi.business.AsignaturasService;
import com.sdi.model.Asignatura;
import com.sdi.model.Usuario;

public class SimpleAsignaturasService implements AsignaturasService {

	@Override
	public List<Asignatura> getAsignaturas() throws Exception {
		return new AsignaturasListado().getAsignaturas();
	}

	@Override
	public List<Asignatura> getAsignaturasByProfesorId(String id) {
		return new VerAsignaturasImpartidas().getAsignaturas(id);
	}

	@Override
	public List<Usuario> getAlumnos(String idAsignatura) {
		return new VerAlumnosMatriculados().getAlumnos(idAsignatura);
	}

}
