package uo.sdi.util;

import com.sdi.business.exception.BusinessException;

public class Check {

	public static void validPassword(String nuevo_password,
			Object nuevo_repetir_password) {
		if (!nuevo_password.equals(nuevo_repetir_password)) {
			throw new BusinessException("Las contraseñas no coinciden");
		}

	}

}
