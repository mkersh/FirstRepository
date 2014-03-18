package com.mk.Delicious;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DeliciousPropertyFile {

	private static DeliciousPropertyFile INSTANCE = null;
	private Properties prop = null;
	
	public static DeliciousPropertyFile getInstance(){
		if (INSTANCE == null){
			INSTANCE = new DeliciousPropertyFile();
		}
		
		return INSTANCE;
	}
	
	private DeliciousPropertyFile(){
		loadPropFile();
	}
	
	public void loadPropFile()
	{
		prop = new Properties();
		InputStream input = null;
	 
		try {
			String propFilePath = URLHelper.expandHomeSymbol("~/delicious.properties");
			input = new FileInputStream(propFilePath);
	 
			// load a properties file
			prop.load(input);
	 
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String getProperty(String propName){
		return prop.getProperty(propName);
	}

}
