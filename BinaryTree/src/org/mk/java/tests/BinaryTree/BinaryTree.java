package org.mk.java.tests.BinaryTree;

/**
 * 
 * @author kershaw
 *
 * Class for storing data in a binary sorted tree.
 * To make things interesting I am going to try and balance the tree
 * as items are added. 
 */
public class BinaryTree {
	public int count;
	
	private BinaryTreeNode rootNode = null; 
	
	// Constructors
	public BinaryTree(){
		rootNode = new BinaryTreeNode();
	}
	
	// Methods
	
	public void add(String itemToAdd){
		count += 1;
		rootNode.add(itemToAdd);
	}
	
	/**
	 * 
	 * @param itemToAdd
	 * @return
	 */
	public String find(String itemToFind){
		return rootNode.find(itemToFind);
	}
	
	public String toString(){
		return null;
	}
	

}

class BinaryTreeNode {
	private String data = null; 		// data for this node
	private BinaryTreeNode left = null; 	// nodes less than this one
	private BinaryTreeNode right = null; 	// nodes greater than this one.

	//Constructors
	public BinaryTreeNode(){}
	
	public String find(String itemToFind) {
		if (this.data == null) {
			// Must be an empty Tree return null
			return null;
		}
		else if (this.data.compareTo(itemToFind) == 0){
			// We have a match
			return this.data;
		}
		else if (this.data.compareTo(itemToFind) < 0){
			if (this.left == null) return null;
			return this.left.find(itemToFind);
		}
		else {
			if (this.right == null) return null;
			return this.right.find(itemToFind);
		}
	}

	// methods
	public void add(String itemToAdd){
		if (this.data == null) {
			// empty tree, store in this node
			this.data = itemToAdd;
			
		}
		else if (this.data.compareTo(itemToAdd) <= 0){
			if (this.left == null) this.left = new BinaryTreeNode();
			this.left.add(itemToAdd);
		}
		else {
			if (this.right == null) this.right = new BinaryTreeNode();
			this.right.add(itemToAdd);
		}
		
	}
}
