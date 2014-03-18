/**
 * 
 */
package com.mk.Delicious;

/**
 * @author mark
 *
 */
public class DeliciousVisitor1 implements DeliciousVisitor {
	
	private int count = 0;
	private final int MAXRETURN = 99999; // 0 = 1, 1=2 etc
	private DeliciousPropertyFile prop = DeliciousPropertyFile.getInstance(); 
	private SendEmail email = null;
	
	public DeliciousVisitor1(){
		email = new SendEmail();
	}

	/* (non-Javadoc)
	 * @see com.mk.Delicious.DeliciousVisitor#visitNode()
	 */
	@Override
	public boolean visitNode(DeliciousBean bean) {
		count += 1;
		
		// Create and send an email containing the details of the post
		String from = prop.getProperty("FromEmailAddress");
		String to = prop.getProperty("ToEmailAddress");
		String title = modifyTags(bean.getTags()); // Add #log and unique tag
		String body = bean.getDescription();
		body += "\n\n" + bean.getHref();
		body += "\n\n" + bean.getExtended(); 
		body += "\n\nCreated in Delicious: " + bean.getTime();
		email.post(from, to, title, body);
		System.out.print(".");
		if (count > MAXRETURN)
			return false; 
		else
			return true;
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

}
