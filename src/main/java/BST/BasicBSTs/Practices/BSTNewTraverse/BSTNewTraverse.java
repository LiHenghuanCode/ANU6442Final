package BST.BasicBSTs.Practices.BSTNewTraverse;

import java.util.ArrayList;
import java.util.List;

public class BSTNewTraverse {

	private Node root;

	public BSTNewTraverse() {
		this.root = null;
	}

	public void insert(Key key) {
		Node parent = null;
		Node current = this.root;
		while (current != null) {
			if (current.key.compareTo(key) < 0) {
				parent = current;
				current = current.right;
			} else if (current.key.compareTo(key) > 0) {
				parent = current;
				current = current.left;
			}
		}

		if (parent == null && current == null) {
			this.root = new Node(key);
		} else {
			Node newNode = new Node(key);
			if (parent.key.compareTo(key) < 0) {
				parent.right = newNode;
			} else if (parent.key.compareTo(key) > 0) {
				parent.left = newNode;
			}
		}
	}

	public List<Key> invertedPreOrder() {
		// TODO
		// HINT: Use recursion
		// START YOUR CODE
		List<Key> result = new ArrayList<>();
		Helper(result,root);
		return result;
		// END YOUR CODE
	}
	private void Helper(List<Key> result, Node root){
		if(root == null) {
			return;
		}

		result.add(root.key);
		Helper(result,root.right);
		Helper(result,root.left);
	}



}
