package com.pmanage.framework.persist;

abstract public class SqlMap extends SqlMapBase {
	
	protected String protectSqlInjection(String param0) {
		
		String param = param0;
		param = param.replaceAll("%","");
		param = param.replaceAll("\'","");
		param = param.replaceAll("--","");
		param = param.replaceAll(";","");
		
		return param;
	}
}
