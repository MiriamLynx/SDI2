package impl.sdi.persistence;

import java.util.List;

import com.sdi.model.Asignatura;
import com.sdi.model.Usuario;
import com.sdi.persistence.AsignaturaDao;

public class AsignaturaJdbcDAO implements AsignaturaDao {

	private AsignaturaJdbc jdbcAsignatura = null;

	public AsignaturaJdbc getJdbcAsignatura() {
		return jdbcAsignatura;
	}

	public void setJdbcAsignatura(AsignaturaJdbc jdbcAsignatura) {
		this.jdbcAsignatura = jdbcAsignatura;
	}

	public AsignaturaJdbcDAO() {
		setJdbcAsignatura(new AsignaturaJdbc());
	}

	@Override
	public List<Asignatura> getAsignaturas() {
		return new AsignaturaJdbc().getAsignaturas();
	}

	@Override
	public List<Usuario> getAlumnos(String idAsignatura) {
		return new AsignaturaJdbc().getAlumnos(idAsignatura);
	}

}
