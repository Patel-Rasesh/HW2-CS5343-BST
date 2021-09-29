public class Node {
		int val;
		Node left;
		Node right;
		
		//Constructor for Node class
		Node(int v){
			val = v;
			left = null;
			right = null;
		}

		@Override
		public String toString() {
			return "Val=" + val;
		}
}
