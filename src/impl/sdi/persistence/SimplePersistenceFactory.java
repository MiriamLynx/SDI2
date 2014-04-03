package impl.sdi.persistence;

import com.sdi.persistence.AsignaturaDao;
import com.sdi.persistence.MatriculaDao;
import com.sdi.persistence.PersistenceFactory;
import com.sdi.persistence.UsuarioDao;

/**
 * Implementaci??????n de la factoria que devuelve implementaci??????n de la
 * capa de persistencia con Jdbc
 * 
 * @author alb
 * 
 */
public class SimplePersistenceFactory implements PersistenceFactory {

	@Override
	public UsuarioDao createUsuarioDao() {
		return new UsuarioJdbcDAO();
	}

	@Override
	public AsignaturaDao createAsignaturaDao() {
		return new AsignaturaJdbcDAO();
	}

	@Override
	public MatriculaDao createMatriculaDao() {
		return new MatriculaJdbcDAO();
	}

}
