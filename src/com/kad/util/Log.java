package com.kad.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {
	private static Logger logger = Logger.getLogger(Log.class);  
	
	public void log() {
		//PropertyConfigurator.configure("/opt/tomcat/webapps/datakad/log4.properties");
		//PropertyConfigurator.configure("c:/log4j.properties"); 
		logger.debug("This is debug message from Dao.");  
		     // 记录info级别的信息  
		      logger.error("This is info message from Dao.");  
		      // 记录error级别的信息  
		       logger.warn("This is error message from Dao.");  
		     
	}	
	public void log(String e){
		//PropertyConfigurator.configure("/opt/tomcat/webapps/datakad/log4.properties"); 
		  logger.error(e);  
	}
}

