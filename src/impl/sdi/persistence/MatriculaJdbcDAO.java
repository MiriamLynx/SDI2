package impl.sdi.persistence;

import java.util.List;

import com.sdi.model.Matricula;
import com.sdi.persistence.MatriculaDao;

public class MatriculaJdbcDAO implements MatriculaDao {

	private MatriculaJdbc jdbcMatricula = null;

	public MatriculaJdbc getJdbcMatricula() {
		return jdbcMatricula;
	}

	public void setJdbcMatricula(MatriculaJdbc jdbcMatricula) {
		this.jdbcMatricula = jdbcMatricula;
	}

	public MatriculaJdbcDAO() {
		setJdbcMatricula(new MatriculaJdbc());
	}

	@Override
	public List<Matricula> getMatriculas() {
		return new MatriculaJdbc().getMatriculas();
	}

}
