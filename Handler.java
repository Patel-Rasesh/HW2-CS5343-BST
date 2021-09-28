
public class Handler {

	public static void main(String[] args) {
		BinarySearchTree testTree = new BinarySearchTree();
		Node root = new Node(40);
		Node newNode1 = new Node(60);
		Node newNode2 = new Node(20);
		Node newNode3 = new Node(80);
		Node newNode4 = new Node(50);
		Node newNode5 = new Node(10);
		testTree.insertToBST(root, newNode1);
		testTree.insertToBST(root, newNode2);
		testTree.insertToBST(root, newNode3);
		testTree.insertToBST(root, newNode4);
		testTree.insertToBST(root, newNode5);

		Node newNode6 = new Node(30);
		Node newNode7 = new Node(15);
		Node newNode8 = new Node(5);
		Node newNode9 = new Node(35);
		Node newNode10 = new Node(25);
		testTree.insertToBST(root, newNode6);
		testTree.insertToBST(root, newNode7);
		testTree.insertToBST(root, newNode8);
		testTree.insertToBST(root, newNode9);
		testTree.insertToBST(root, newNode10);

		Node newNode11 = new Node(45);
		Node newNode12 = new Node(55);
		Node newNode13 = new Node(70);
		Node newNode14 = new Node(90);
		Node newNode15 = new Node(32);
		testTree.insertToBST(root, newNode11);
		testTree.insertToBST(root, newNode12);
		testTree.insertToBST(root, newNode13);
		testTree.insertToBST(root, newNode14);
		testTree.insertToBST(root, newNode15);
		
		Node newNode16 = new Node(33);
		Node newNode17 = new Node(48);
		Node newNode18 = new Node(46);
		testTree.insertToBST(root, newNode16);
		testTree.insertToBST(root, newNode17);
		testTree.insertToBST(root, newNode18);
		
		testTree.inorderTraversal(root);
		
//		Node parentOfSuccessor = testTree.findSuccessor(newNode1.right);
//		System.out.println(parentOfSuccessor.val);
//		
		testTree.deleteFromBST(root, newNode18);
		//testTree.deleteFromBST(root, newNode9);
		System.out.println("\nafter deletion");
		testTree.inorderTraversal(root);
		
	}

}
