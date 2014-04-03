package impl.sdi.business;

import com.sdi.business.AsignaturasService;
import com.sdi.business.SesionService;
import com.sdi.business.MatriculasService;
import com.sdi.business.ServicesFactory;
import com.sdi.business.UsuariosService;

public class SimpleServicesFactory implements ServicesFactory {

	@Override
	public AsignaturasService createAsignaturasService() {
		return new SimpleAsignaturasService();
	}

	@Override
	public UsuariosService createUsuariosService() {
		return new SimpleUsuariosService();
	}

	@Override
	public MatriculasService createMatriculasService() {
		return new SimpleMatriculasService();
	}

	@Override
	public SesionService createSesionService() {
		return new SimpleSesionService();
	}

}
