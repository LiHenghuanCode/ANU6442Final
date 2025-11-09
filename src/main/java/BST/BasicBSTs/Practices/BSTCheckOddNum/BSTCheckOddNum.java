package BST.BasicBSTs.Practices.BSTCheckOddNum;/*
 * Given a binary search tree, implement a method to find the sum of the values of all the nodes that have an odd number of direct children. You can define additional methods of BST and Node classes to complete the task. The method signature is: public Integer oddNodeSum()
 * 
 */

public class BSTCheckOddNum{
	
	Node root;

	/**
	 * Notes.md:
	 * find the sum of the values of all the nodes that have an odd number of direct children.
	 *
	 * Please implement this method and feel free to add additional helper methods
	 * @return
	 */
	public Integer oddNodeSum() {
		// START YOUR CODE
//you are allowed to change this return statement
		return oddNodeSumHelper(root);
		// END YOUR CODE
	}

	private Integer oddNodeSumHelper(Node node) {
		if (node == null) return 0;

		// 注意不是判断子节点的值，而是判断子节点的数量
		int children = 0;
		if (node.left != null) children++;
		if (node.right != null) children++;

		int sum = 0;
		if (children % 2 == 1) {
			sum += node.value;
		}

		sum += oddNodeSumHelper(node.left);
		sum += oddNodeSumHelper(node.right);

		return sum;
	}



	public BSTCheckOddNum() {
		this.root = null;
	}

	/**
	 * This implementation of insert follows the pseudo code in the lecture slide.
	 * Node that we did not use recursion here.
	 * 
	 * @param value value of inserted node
	 * @return inserted node (not the entire tree)
	 */
	public Node insert(Integer value) {
		Node parent = null;
		Node current = this.root;
		while (current != null) {
			if(current.value.compareTo(value) < 0) {
				parent = current;
				current = current.right;
			}else if (current.value.compareTo(value) > 0){
				parent = current;
				current = current.left;
			}
		}
		
		if (parent == null && current == null) {
			this.root = new Node(value);  // no parent = root is empty
			return root;
		}else {
			Node newNode = new Node(value);
			
			if(parent.value.compareTo(value) < 0) {
				parent.right = newNode;
				newNode.parent = parent;
			}else if(parent.value.compareTo(value) > 0) {
				parent.left = newNode;
				newNode.parent = parent;
			}
			return newNode;
		}
	}
	
	public class Node {

		Integer value;
		Node parent;
		Node left;
		Node right;

		public Node(Integer value) {
			this.value = value;
			this.parent = null;
			this.left = null;
			this.right = null;
		}
	}
}
