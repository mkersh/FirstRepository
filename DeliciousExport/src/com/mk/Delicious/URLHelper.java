package com.mk.Delicious;

import java.io.File;

public class URLHelper {
	
	public static String convertToFileURL(String filename) {
        String path = new File(filename).getAbsolutePath();
        if (File.separatorChar != '/') {
            path = path.replace(File.separatorChar, '/');
        }

        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        return "file:" + path;
    }
	
	public static String expandHomeSymbol(String filename){
		String homedir = System.getProperty("user.home"); 
		return filename.replaceFirst("~", homedir);
	}
	

}
