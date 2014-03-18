/**
 * 
 */
package com.mk.Delicious;

import javax.xml.xpath.XPathConstants;

import org.w3c.dom.Node;

/**
 * 
 *
 */
public class DeliciousBean {
	private String description;
	private String extended;
	private String href;
	private String tags;
	private String time;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExtended() {
		return extended;
	}
	public void setExtended(String extended) {
		this.extended = extended;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	

}
