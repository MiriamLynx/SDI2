package impl.sdi.business.classes.matriculas;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Matricula;
import com.sdi.persistence.MatriculaDao;

public class MatriculasListado {

	public List<Matricula> getMatriculas() throws Exception {

		MatriculaDao dao = Factories.persistence.createMatriculaDao();
		return dao.getMatriculas();

	}
}
