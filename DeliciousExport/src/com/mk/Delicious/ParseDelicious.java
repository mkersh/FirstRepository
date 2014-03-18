package com.mk.Delicious;

public interface ParseDelicious {
		/***
		 * Go through the Delicious XML file filePath and call visitor for each tag in file.
		 * Stop the parsing if the visitor returns false.
		 * 
		 * @param filePath File to parse
		 * @param visitor DeliciousVisitor to process each node in file
		 */
		public void parse(String filePath, DeliciousVisitor visitor);
}
