package uo.sdi.util;

import com.sdi.business.exception.BusinessException;

public class Check {

	public static void validPassword(String nuevo_password,
			String nuevo_repetir_password) {
		if (!nuevo_password.equals(nuevo_repetir_password)) {
			throw new BusinessException("Las contraseñas no coinciden");
		}

	}

	public static void validPassword(String antiguo_password,
			String nuevo_password, String nuevo_repetir_password,
			String actual_password) {
		if (!nuevo_password.equals(nuevo_repetir_password)
				|| !antiguo_password.equals(actual_password)) {
			throw new BusinessException("Las contraseñas no coinciden");
		}
	}

}
