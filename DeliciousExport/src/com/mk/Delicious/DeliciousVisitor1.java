/**
 * 
 */
package com.mk.Delicious;

import java.io.PrintWriter;

/**
 * @author mark
 *
 */
public class DeliciousVisitor1 implements DeliciousVisitor {
	
	private Registry registry;
	
	public Registry getRegistry() {
		return registry;
	}

	public void setRegistry(Registry registry) {
		this.registry = registry;
	}

	private int count = 0;
	private final int MAXRETURN = 4; // 0 = 1, 1=2 etc
	private DeliciousPropertyFile prop = DeliciousPropertyFile.getInstance(); 
	private StringBuffer buffer = new StringBuffer();
	
	public DeliciousVisitor1(){
	}

	/* (non-Javadoc)
	 * @see com.mk.Delicious.DeliciousVisitor#visitNode()
	 */
	@Override
	public boolean visitNode(DeliciousBean bean) {
		count += 1;
		
		// Create and send an email containing the details of the post
		String title = modifyTags(bean.getTags()); // Add #log and unique tag
		String body = bean.getDescription();
		body += "<br/><br/>" + modifyHref(bean.getHref());
		if (!bean.getExtended().equals(""))
			body += "<br/><br/>" + bean.getExtended(); 
		body += "<br/><br/>Created in Delicious: " + bean.getTime();
		
		buffer.append(title);
		buffer.append("<br/><br/>");
		buffer.append(body);
		buffer.append("<hr/>");
		
		
		if (count > MAXRETURN)
			return false; 
		else
			return true;
	}
	
	public void finish(){
		//String from = prop.getProperty("FromEmailAddress");
		//String to = prop.getProperty("ToEmailAddress");
		//email.post(from, to, "#Delicious #Export", buffer.toString());
		//System.out.print(".");
		PrintWriter out = null;
		try{
			out = new PrintWriter(URLHelper.expandHomeSymbol(prop.getProperty("OutputFile")));
			out.println(buffer.toString());
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		finally {
			out.close();
		}
		
	}

	
	/***
	 * Convert the tags into #tag1 #tag2 format.
	 * Add #log tag if not present and add a tag for this program
	 * @param tags
	 * @return
	 */
	private String modifyTags(String tags){
		tags = tags.replaceAll(",", " "); // replace commas with spaces
		String[] array = tags.split("[ ]+"); //tags.split("\\s+"); // If a use " " as the regex I get extra "" in the array!!
		tags = "";
		for (int i = 0; i<array.length; i++){
			String item = array[i];
			item.trim();
			if (item.equals("")==false){
				tags += "#" + item + " ";
			}
		}
		tags = "#log #DeliciousExport " + tags; 
		return tags;
	}
	
	private String modifyHref(String href){
		String aTag = String.format("<a href=\"%s\">%s</a>", href, href);
		return aTag;
	}

}
