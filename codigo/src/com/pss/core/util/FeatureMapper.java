package com.pss.core.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.pss.core.model.repository.AtivoRepositoryHibernate;

public class FeatureMapper {
	
	private static Map features = null;
	private static FeatureMapper instance = null;
	
	public static FeatureMapper getInstance() {
		if (instance == null) {
			features = new HashMap();
			instance = new FeatureMapper();
		}
		return instance;
	}
	
	public void registrarFeature(String featureName, String estado) {
		features.put(featureName, new Boolean(estado));
	}
	
	public boolean featureHabilitada(String featureName){
		Boolean estado = (Boolean) features.get(featureName);
		if (estado == null) {
			return false;
		} else {
			return estado;
		}
	}
	
	public String toString() {
		Iterator iterator = features.keySet().iterator();
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		while (iterator.hasNext()) {
		   String key = (String) iterator.next();
		   Boolean estado = (Boolean) features.get(key);
		   sb.append(key+" -> "+String.valueOf(estado)+"\n");
		}
		return sb.toString();
	}
 
}
