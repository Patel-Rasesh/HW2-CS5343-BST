public class BinarySearchTree {
	public Node root;
	
	public BinarySearchTree() {
		root = null;
		}
	
	public void insertToBST(Node root, Node node){
		/*
		 Accepts two arguments - root and the node which needs to be inserted
		 Description - 			 inserts the node into a tree while following the definition of BST
		 Returns - 				 void
		 */
		
		Node temp = root;
		
		//when tree is empty, new node becomes root and tree gets created.
		if(root == null) {
			root = node;
		}
		
		//According to the definition of BST, when value of new node is smaller than
		//the value of root, that value gets entered
		//on the left side of the tree
		else if(temp.val > node.val){
			if(temp.left != null) {
				insertToBST(temp.left, node);
			}
			else {
				temp.left = node;
			}
		}
		
		//when value of new node is larger than the value of root, that value gets entered
		//on the right side of the tree
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
		/*
		 Accepts one argument -  root
		 Description 		  -  following (left-parent-right) pattern for traversal
		 Returns 			  -  void
		 */
		
		if(root != null) {
			inorderTraversal(root.left);
			System.out.print(root.val+" ");
			inorderTraversal(root.right);
		}
	}
	
	public Node findPredecessor(Node node) {
		
		/*
		 Accepts one argument -  left child of the concerning node
		 Description - 			 finds the predecessor of the node whose left child is 
		 						 passed as the argument
		 Returns - 				 parent of the predecessor where parent.right = predecessor
		 */
				
		//The node passed in the argument will not be null when the delete function calls
		//findPredecessor. Hence we have avoided including that base condition here.
		if(node.right == null) {
			
			//When there is only one node in the subtree from which the predecessor node needs
			//to be found
			return node;
		}
		else if(node.right.right == null){
			
			//Conditions are kept separate as once node.right = null, node.right.right will throw
			//null pointer exception
			//Separate conditions (two else if) handle this exception
			return node;
		}
		else {
			return findPredecessor(node.right);
		}
	}

	public Node findSuccessor(Node node) {
		
		/*
		 Accepts one argument -  right child of the concerning node
		 Description - 			 finds the successor of the node whose right child is 
		 						 passed as the argument
		 Returns - 				 parent of the successor where parent.left = successor
		 */

		if(node == null) {
			
			//When there is no right child (in the subtree from which the successor node needs
			//to be found)
			return null;
		}
		else if(node.left == null) {
			
			//When there is only one node in the subtree from which the successor node needs
			//to be found
			return node;
		}
		else if(node.left.left == null){
			
			//Conditions are kept separate as once node.left = null, node.left.left will throw
			//null pointer exception
			//Separate conditions (two else if) handle this exception
			return node;
		}
		else {
			
			//Recursive call until we find the left most child in right-subtree
			return findSuccessor(node.left);
		}
	}
	
	public Node deleteFromBST(Node root, Node node){
		/*
		 Accepts two arguments - root node and the node which we want to delete
		 Description - 			 deletes the node passed as the second argument
		 Returns - 				 root node of the tree (changed or unchanged)
		 */
		
		//Temp1 node is a primary incremental pointer
		//tempSwap node is a temporary node used while changing the pointers 
		Node temp1 = root;
		Node tempSwap = null;
		
		//SuccessorParent and PredecessorParent are a parent of the successor node and
		//predecessor node respectively
		Node successorParent = null;
		Node predecessorParent = null;
		
		if(root == node) {
			
			//When we want to delete the root node
			successorParent = findSuccessor(temp1.right);
			tempSwap = successorParent.left.right;
			
			successorParent.left.right = temp1.right;
			successorParent.left.left = temp1.left;
			
			root = successorParent.left;
			successorParent.left = tempSwap;
			return root;
		}
		else if(temp1.left == node) {
			
			//When the node is on the left of the reference node (temp1)
			//and we encounter a leaf node that needs to be deleted
			if(temp1.left.left == null && temp1.left.right == null) {
				temp1.left = null;
			}
			else {
				
				successorParent = findSuccessor(temp1.left.right);
				if(successorParent == null) {
						
					//When successor is null, we find predecessor of the same node
					predecessorParent = findPredecessor(temp1.left.left);
					if(predecessorParent.left == null && predecessorParent.right == null) {
						
						//When the parent of a predecessor is a leaf node. Implying parent of a
						//predecessor and predecessor itself are equal
						temp1.left = predecessorParent;
					}
					else{
						tempSwap = predecessorParent.right.left;
					
						predecessorParent.right.left = temp1.left.left;
						predecessorParent.right.right = temp1.left.right;
						temp1.left = predecessorParent.right;
						predecessorParent.right = tempSwap;
					}
				}
				else if(successorParent.left == null && successorParent.right == null) {
					
					//When the parent of a successor is a leaf node. Implying parent of a
					//successor and successor itself are equal
					successorParent.left = temp1.left.left;
					temp1.left = successorParent;
				}
				else if(successorParent.left == null) {
					
					//When the parent of a successor does not have a left child. Implying 
					//parent of a successor and successor itself are equal
					successorParent.left = temp1.left.left;
					temp1.left = successorParent;
				}
				else {
					
					//When we have both of the children and none of the above mentioned
					//special case follows
					tempSwap = successorParent.left.right;
					
					successorParent.left.right = temp1.left.right;
					successorParent.left.left = temp1.left.left;
					temp1.left = successorParent.left;
					successorParent.left = tempSwap;
				}
			}
		}
		else if(temp1.right == node){
			
			//When the node is on the right of the reference node (temp1)
			//and we encounter a leaf node that needs to be deleted
			if(temp1.right.left == null && temp1.right.right == null) {
				temp1.right = null;
			}
			else {
				successorParent = findSuccessor(temp1.right.right);
				
				if(successorParent == null) {
					
					//When successor is null, we find predecessor of the same node
					predecessorParent = findPredecessor(temp1.right.left);
					
					//When the parent of a predecessor is a leaf node. Implying parent of a
					//predecessor and predecessor itself are equal
					if(predecessorParent.left == null && predecessorParent.right == null) {
						temp1.right = predecessorParent;
					}
					else{
						tempSwap = predecessorParent.right.left;
						predecessorParent.right.left = temp1.right.left;
						predecessorParent.right.right = temp1.right.right;
						temp1.right = predecessorParent.right;
						predecessorParent.right = tempSwap;
					}
				}
				else if(successorParent.left == null && successorParent.right == null) {
					
					//When the parent of a successor is a leaf node. Implying parent of a
					//successor and successor itself are equal
					successorParent.left = temp1.right.left;
					temp1.right = successorParent;
				}
				else if(successorParent.left == null) {
					
					//When the parent of a successor does not have a left child. Implying 
					//parent of a successor and successor itself are equal
					successorParent.left = temp1.right.left;
					temp1.right = successorParent;
				}
				else {
					
					//When we have both of the children and none of the above mentioned
					//special case follows
					tempSwap = successorParent.left.right;
					successorParent.left.right = temp1.right.right;
					successorParent.left.left = temp1.right.left;
					temp1.right = successorParent.left;
					successorParent.left = tempSwap;
				}
			}
		}
		
		//Recursive calls until the node that needs to be deleted is found as either of
		//the children of temp1 (reference node)
		else if (temp1.val > node.val){
			
			deleteFromBST(temp1.left, node);
		}
		else if (temp1.val < node.val){
			deleteFromBST(temp1.right, node);
		}
		return root;
	}
}
