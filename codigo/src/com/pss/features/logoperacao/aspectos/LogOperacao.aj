package com.pss.features.logoperacao.aspectos;

import java.util.Date;

public aspect LogOperacao {

	pointcut command():
		execution(void *.say*(..));
	
	before(): command() {
		String msg = "Comando executado: "+thisJoinPoint.getSignature();
		System.out.println(new Date()+" "+msg);
	}
}
