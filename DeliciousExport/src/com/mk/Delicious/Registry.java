package com.mk.Delicious;

/***
 * 
 * Provides a Facade for DI/IoC containers such as Spring.
 * 
 * Rather than use Spring's ApplicationContext directly use this simplified Registry Facade 
 * to remove dependency of Spring from your code .
 * 
 * I will add methods to this Registry as and when I need them.
 * 
 * The Registry is like a generic AbstractFacory (from GOF patterns).
 *
 */
public interface Registry {
	/***
	 * 
	 * Initialise the Registry using the details in filePath.
	 * 
	 * Problem here is that the format of the filepath file has to be compatible with the specific DI container
	 * that you are using. So this interface is not 100% independent from the DI Container.
	 * I may look to improve this in a future refactoring. 
	 */
	public void initFromFile(String filePath);
	public void initFromClassPathFile(String filePath);
	
	/***
	 * Given a beanName return a concrete object to use.
	 * 
	 * Any dependencies that the bean has should be resolved by Registry.
	 * 
	 * As a general pattern I plan to inject a Registry into all/most beans that I create.
	 * This way each bean will have the means to resolve dependencies dynamically. It should also 
	 * mean a simpler Registry configuration file. Rather than inject lots of properties in the config file
	 * we can do this in the object itself.
	 * 
	 * @param beanName
	 * @return Return concrete instance of the bean to use
	 */
	public Object getBean(String beanName);
	
	/***
	 * Shutdown the Registry, closing any resources that it is using
	 */
	public void shutdown();

}
