package com.pss.core.util;

import java.util.Date;

public class Logging {

	public static void log(Object obj, String msg) {
		System.out.println("["+new Date()+"] "+"["+obj.getClass().getName()+"] "+msg);
	}
}
