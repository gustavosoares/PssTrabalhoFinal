package com.pss.features.logoperacao.aspectos;

import java.util.Date;
import com.pss.core.util.Logging;

public aspect LogOperacao {

	pointcut command():
		execution(void *.say*(..));
	
	before(): command() {
		
		String msg = "Comando executado: "+thisJoinPoint.getSignature();
		Logging.log(msg);

	}
}
