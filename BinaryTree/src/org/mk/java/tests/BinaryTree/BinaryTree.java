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
	
	public void add(String itemToAdd) throws Exception{
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
		return rootNode.getTreeAsString(1);
	}
	

}

class BinaryTreeNode {
	private String data = null; 		// data for this node
	private BinaryTreeNode left = null; 	// nodes less than this one
	private BinaryTreeNode right = null; 	// nodes greater than this one.

	//Constructors
	public BinaryTreeNode(){}
	
	// methods
	
	private int compareData(String data ){
		int i1 = Integer.parseInt(this.data);
		int i2 = Integer.parseInt(data);
		if (i1 == i2) return 0;
		else if (i1 < i2) return -1;
		else return 1;
	}
	
	public String find(String itemToFind) {
		if (this.data == null) {
			// Must be an empty Tree return null
			return null;
		}
		else if (this.compareData(itemToFind) == 0){
			// We have a match
			return this.data;
		}
		else if (this.compareData(itemToFind) > 0){
			if (this.left == null) return null;
			return this.left.find(itemToFind);
		}
		else {
			if (this.right == null) return null;
			return this.right.find(itemToFind);
		}
	}

	public void add(String itemToAdd) throws Exception{
		if (this.data == null) {
			// empty tree, store in this node
			this.data = itemToAdd;
			
		}
		else if (this.compareData(itemToAdd) == 0){
			// Throw an Exception
			throw new Exception("Duplicate Item");
		}
		else if (this.compareData(itemToAdd) > 0){
			if (this.left == null) this.left = new BinaryTreeNode();
			this.left.add(itemToAdd);
		}
		else {
			if (this.right == null) this.right = new BinaryTreeNode();
			this.right.add(itemToAdd);
		}
		
	}
	
	public String getTreeAsString(int depth){
		String str = "";
		if (this.left != null){
			str += this.left.getTreeAsString(depth+1);
			System.out.println("After left: " + str);
		}
		if (this.data != null){
			str += padString("", depth, '-');
			str += this.data + "\n";
			System.out.println("Node: " + str);
		}
		if (this.right != null){
			str += this.right.getTreeAsString(depth+1);
			System.out.println("After right: " + str);
		}
		
		return str;
	}
	
	private static String padString(String str, int leng,char chr) {
        for (int i = str.length(); i < leng; i++)
            str += chr;
        return str;
	}
}
