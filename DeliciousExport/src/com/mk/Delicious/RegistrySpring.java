package com.mk.Delicious;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/***
 * 
 * Implementation of my Registry interface using Spring
 *
 */
public class RegistrySpring implements Registry {
	
	// Make ctx static so that we can share with all instances
	// Will this work in a web container though??
	static private ApplicationContext ctx = null;

	@Override
	public void initFromFile(String filePath) {
		ctx = new FileSystemXmlApplicationContext(filePath);
	}

	@Override
	public void initFromClassPathFile(String filePath) {
		// TODO Still need to implement this. Will do when I need it for the first time

	}

	@Override
	public Object getBean(String beanName) {
		return ctx.getBean(beanName);
	}
	
	@Override
	public void shutdown(){
		try {
			((AbstractApplicationContext) ctx).close();
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}

}
