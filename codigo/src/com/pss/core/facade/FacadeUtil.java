package com.pss.core.facade;

import com.pss.core.util.Logging;
import com.pss.core.util.Seguranca;

public class FacadeUtil {

	private FacadeUtil() {
		
	}
	
	public static void log(String msg) {
		Logging.log(msg);
	}
	
	public static String encriptar(String senha) {
		return Seguranca.encriptar(senha);
	}
}
