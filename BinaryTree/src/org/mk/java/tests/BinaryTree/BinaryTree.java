package org.mk.java.tests.BinaryTree;

/**
 * 
 * @author mkersh
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
		return rootNode.getTreeAsString("");
	}
	

}

class BinaryTreeNode {
	private String data = null; 		// data for this node
	private BinaryTreeNode left = null; 	// nodes less than this one
	private BinaryTreeNode right = null; 	// nodes greater than this one.
	private int leftCount = 0, rightCount = 0;
	private boolean isActive = true; 
	
	private static final int LEFT = 1;
	private static final int RIGHT = 2;

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
			this.isActive = true;
			TRACE("Added " + itemToAdd);
			return;
			// TODO - reuse nodes that are markitemToAdded as isActive=false
			// if we have an inactive node and itemToAdd is > left and < right then
			// we can reuse the inactive node
		}
		else if ((this.compareData(itemToAdd) == 0) && this.isActive == true){
			// Throw an Exception
			throw new Exception("Duplicate Item");
		}
		
		// See if we need to re-balance the tree
		if (this.compareData(itemToAdd) > 0){
			if ((leftCount - rightCount) > 1){
				TRACE("Rebalance Left to right");
				// Need to re-balance tree
				String originalNodeData = this.data;
				this.data = left.data; // Move the left node to be root node
				this.leftCount -= 1;
				this.left.isActive = false;
				add(originalNodeData); // will get added to right side
			}
		}
		else {
			if ((rightCount - leftCount) > 1){
				TRACE("Rebalance right to left");
				// Need to re-balance tree
				String originalNodeData = this.data;
				this.data = right.data; // Move the left node to be root node
				this.rightCount -= 1;
				this.right.isActive = false;
				add(originalNodeData); // will get added to left side
			}
		}
		
		// Now Add the new node
		if (this.compareData(itemToAdd) > 0){
			TRACE("Going Left - " + itemToAdd);
			if (this.left == null) this.left = new BinaryTreeNode();
			this.leftCount += 1;
			this.left.add(itemToAdd);
			
		}
		else {
			TRACE("Going Right - " + itemToAdd);
			if (this.right == null) this.right = new BinaryTreeNode();
			this.rightCount += 1;
			this.right.add(itemToAdd);
			
		}
	}
	
	
	
	public String getTreeAsString(String depth){
		String str = "";
		if (this.left != null){
			str += this.left.getTreeAsString(addDepth(depth,LEFT));
		}
		if (this.data != null){
			str += depth + this.data + "\n";
		}
		if (this.right != null){
			str += this.right.getTreeAsString(addDepth(depth,RIGHT));
		}
		
		return str;
	}
	
	private static void TRACE( String str)
	{
		System.out.println(str);
	}
	
	private static String addDepth(String depthStr, int leftOrRight) {
        if (leftOrRight == LEFT){
        	depthStr += "<";
        }
        else if (leftOrRight == RIGHT) {
        	depthStr += ">";
        }
        return depthStr;
	}
	
	@SuppressWarnings("unused")
	private static String padString(String str, int leng,char chr) {
        for (int i = str.length(); i < leng; i++)
            str += chr;
        return str;
	}
}
