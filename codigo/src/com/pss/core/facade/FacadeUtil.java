package com.pss.core.facade;

import com.pss.core.util.FeatureMapper;
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
	
	public static void registrarFeature(String featureName, String estado) {
		FeatureMapper.getInstance().registrarFeature(featureName, estado);
	}
	
	public static boolean featureHabilitada(String featureName) {
		return FeatureMapper.getInstance().featureHabilitada(featureName);
	}

}
