package com.pss.core.util;

import java.util.Date;

public class Logging {

	public static void log(String msg) {
		System.out.println(new Date()+" "+msg);
	}
}
