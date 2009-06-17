package com.pss.features.logoperacao.aspectos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pss.core.util.Logging;
import com.pss.core.commands.*;

public aspect LogOperacao {

	public pointcut commandExecuted(HttpServletRequest request, HttpServletResponse response):
		execution(public void com.pss.core.commands.Command+.execute(HttpServletRequest, HttpServletResponse))
            && args(request, response);
	
	before(HttpServletRequest request, HttpServletResponse response): commandExecuted(request, response) {
		
		String msg = "[LogOperacao] Comando executado: "+thisJoinPoint.getSignature();
		Logging.log(msg);

	}
}