package com.puc.pss.aspectos;

public aspect LogOperacao {

	pointcut command():
		execution(void *.say*(..));
	
	before(): command() {
		com.puc.pss.util.Logging.log("Comando executado: "+thisJoinPoint.getSignature());
	}
}
