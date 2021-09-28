public class BinarySearchTree {
	public Node root;
	
	public BinarySearchTree() {
		root = null;
		}
	
	public void insertToBST(Node root, Node node){
		Node temp = root;
		
		if(root == null) {
			root = node;
		}
		else if(temp.val > node.val){
			if(temp.left != null) {
				insertToBST(temp.left, node);
			}
			else {
				temp.left = node;
			}
		}
		else if(temp.val < node.val){
			if(temp.right != null) {
				insertToBST(temp.right, node);
			}
			else {
				temp.right = node;
			}
		}
	}
	
	public void inorderTraversal(Node root) {
		if(root != null) {
			inorderTraversal(root.left);
			System.out.print(root.val+" ");
			inorderTraversal(root.right);
		}
	}
	
	public void deleteFromBST(Node root, Node node){
		Node temp1 = root;
		Node successorParent = null;
		Node tempX = null;
		Node predecessorParent = null;
		if(root == node) {
			successorParent = findSuccessor(temp1.right);
			tempX = successorParent.left.right;
			
			successorParent.left.right = temp1.right;
			successorParent.left.left = temp1.left;
			root = successorParent.left;
			successorParent.left = tempX;
		}
		else if(temp1.left == node) {
			if(temp1.left.left == null && temp1.left.right == null) {
				temp1.left = null;
			}
			else {
				successorParent = findSuccessor(temp1.left.right);
				if(successorParent == null) {
						//if successor is null, find predecessor
					predecessorParent = findPredecessor(temp1.left.left);
					if(predecessorParent.left == null && predecessorParent.right == null) {
						//change pointers
						//predecessorParent.left = temp1.left.left;
						temp1.left = predecessorParent;
					}
					else{
						tempX = predecessorParent.right.left;
					
						predecessorParent.right.left = temp1.right.left;
						predecessorParent.right.right = temp1.right.right;
						temp1.right = predecessorParent.right;
						predecessorParent.right = tempX;
					}
				}
				else if(successorParent.left == null && successorParent.right == null) {
					successorParent.left = temp1.left.left;
					temp1.left = successorParent;
				}
				else {
					tempX = successorParent.left.right;
					successorParent.left.right = temp1.left.right;
					successorParent.left.left = temp1.left.left;
					temp1.left = successorParent.left;
					successorParent.left = tempX;
				}
			}
		}
		else if(temp1.right == node){
			if(temp1.right.left == null && temp1.right.right == null) {
				temp1.right = null;
			}
			else {
				successorParent = findSuccessor(temp1.right.right);
				//check if successorParent is a leaf node. If yes, remove the 
				//successorParent.left = successorParent
				predecessorParent = findPredecessor(temp1.right.left);
				if(successorParent == null) {
					//if successor is null, find predecessor
					if(predecessorParent.left == null && predecessorParent.right == null) {
						temp1.right = predecessorParent;
					}
					else{
						tempX = predecessorParent.right.left;
						predecessorParent.right.left = temp1.right.left;
						predecessorParent.right.right = temp1.right.right;
						temp1.right = predecessorParent.right;
						predecessorParent.right = tempX;
					}
				}
				else if(successorParent.left == null && successorParent.right == null) {
					successorParent.left = temp1.right.left;
					temp1.right = successorParent;
				}
				else {
					tempX = successorParent.left.right;
					successorParent.left.right = temp1.right.right;
					successorParent.left.left = temp1.right.left;
					temp1.right = successorParent.left;
					successorParent.left = tempX;
				}
			}
		}
		else if (temp1.val > node.val){
			deleteFromBST(temp1.left, node);
		}
		else if (temp1.val < node.val){
			deleteFromBST(temp1.right, node);
		}
	}
	public Node findPredecessor(Node node) {
		//The right node of the concerning node needs to be passed in the argument
		//This function returns the parent of successor where parent.left = successor
		if(node.right == null) {
			return node;
		}
		else if(node.right.right == null){
			return node;
		}
		else {
			return findPredecessor(node.right);
		}
	}

	public Node findSuccessor(Node node) {
		//The right node of the concerning node needs to be passed in the argument
		//This function returns the parent of successor where parent.left = successor
		if(node == null) {
			return null;
		}
		else if(node.left == null) {
			return node;
		}
		else if(node.left.left == null){
			return node;
		}
		else {
			return findSuccessor(node.left);
		}
	}
}
