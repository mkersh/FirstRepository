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
	public static BinaryTree currentInstance = null;
	
	private BinaryTreeNode rootNode = null; 
	
	// Constructors
	public BinaryTree(){
		rootNode = new BinaryTreeNode();
		currentInstance = this;
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
	
	public static String testPRINT(){
		return currentInstance.toString();
	}
	

}

class BinaryTreeNode {
	String data = null; 		// data for this node
	BinaryTreeNode left = null; 	// nodes less than this one
	BinaryTreeNode right = null; 	// nodes greater than this one.
	int leftCount = 0, rightCount = 0;
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
			TRACE(BinaryTree.testPRINT());
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
				// Need to re-balance tree
				
				BinaryTreeNode originalNode = new BinaryTreeNode();
				originalNode.data = this.data;
				originalNode.leftCount = 0;
				originalNode.left = null;
				originalNode.right = this.right;
				originalNode.rightCount = this.rightCount;
				
				BinaryTreeNode oldLeft = this.left;
				this.data = left.data; // Move the left node to be root node
				this.leftCount = oldLeft.leftCount;
				this.left = oldLeft.left;
				this.rightCount = oldLeft.rightCount;
				this.right = oldLeft.right;
				
				TRACE("Rebalance Left to right - promote " + this.data + " moving " + originalNode.data);
				TRACE(BinaryTree.testPRINT());
				addTree(originalNode); // will get added to right side
				
			}
		}
		else {
			if ((rightCount - leftCount) > 1){
				// Need to re-balance tree
				BinaryTreeNode originalNode = new BinaryTreeNode();
				originalNode.data = this.data;
				originalNode.leftCount = this.leftCount;
				originalNode.left = this.left;
				originalNode.right = null;
				originalNode.rightCount = 0;
				
				BinaryTreeNode oldRight = this.right;
				this.data = oldRight.data; // Move the left node to be root node
				this.leftCount = oldRight.leftCount;
				this.left = oldRight.left;
				this.rightCount = oldRight.rightCount;
				this.right = oldRight.right;
				
				TRACE("Rebalance right to left - promote " + this.data + " moving " + originalNode.data);
				TRACE(BinaryTree.testPRINT());
				addTree(originalNode); // will get added to right side
				
			}
		}
		
		// Now Add the new node
		if (this.compareData(itemToAdd) > 0){
			//TRACE("Going Left - " + itemToAdd);
			if (this.left == null) this.left = new BinaryTreeNode();
			this.leftCount += 1;
			this.left.add(itemToAdd);
			
		}
		else {
			//TRACE("Going Right - " + itemToAdd);
			if (this.right == null) this.right = new BinaryTreeNode();
			this.rightCount += 1;
			this.right.add(itemToAdd);
			
		}
	}
	
	private void addTree(BinaryTreeNode nodeToAdd) {
		if (this.data == null) {
			// empty tree, store in this node
			this.data = nodeToAdd.data;
			this.leftCount = nodeToAdd.leftCount;
			this.left = nodeToAdd.left;
			this.rightCount = nodeToAdd.rightCount;
			this.right = nodeToAdd.right;
			
			TRACE("Added TreeNode " + nodeToAdd.data);
			TRACE(BinaryTree.testPRINT());
		}
		else if (this.compareData(nodeToAdd.data) > 0){
			if (this.left == null) this.left = new BinaryTreeNode();
			this.leftCount += 1;
			this.left.addTree(nodeToAdd);
			
		}
		else {
			if (this.right == null) this.right = new BinaryTreeNode();
			this.rightCount += 1;
			this.right.addTree(nodeToAdd);
			
		}
	}
	
	
		
	public String getTreeAsString(String depth){
		String str = "";
		if (this.left != null){
			str += this.left.getTreeAsString(addDepth(depth,LEFT));
		}
		if (this.data != null){
			if (this.isActive == true){
				str += depth + this.data + "[" + leftCount + "," + rightCount + "]" +  "\n";
			}
			else {
				str += depth + this.data + "[" + leftCount + "," + rightCount + "]" + "(inactive) \n";
			}
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
