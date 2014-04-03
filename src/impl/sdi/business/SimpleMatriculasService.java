package impl.sdi.business;

import impl.sdi.business.classes.matriculas.MatriculasListado;

import java.util.List;

import com.sdi.business.MatriculasService;
import com.sdi.model.Matricula;

public class SimpleMatriculasService implements MatriculasService {

	@Override
	public List<Matricula> getMatriculas() throws Exception {
		return new MatriculasListado().getMatriculas();
	}

}
