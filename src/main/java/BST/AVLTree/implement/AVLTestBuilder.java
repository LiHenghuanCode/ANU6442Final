package BST.AVLTree.implement;

import java.lang.reflect.Field;
import java.util.Comparator;


public class AVLTestBuilder<T> {
	private final Comparator<T> comparator;
	private final AVLTree<T> tree;

	public AVLTestBuilder(Comparator<T> comparator) {
		this.comparator = comparator;
		this.tree = new AVLTree<>(comparator);
	}

	public AVLNode<T> makeWithCast(T value, Object left, Object right) {
		return make(value, (AVLNode<T>) left, (AVLNode<T>) right);
	}

	public AVLNode<T> make(T value, AVLNode<T> left, AVLNode<T> right) {
		return new AVLNodeFilled<T>(comparator, value, left, right);
	}

	public AVLNode<T> make(T value) {
		return new AVLNodeFilled<T>(comparator, value, empty(), empty());
	}

	public AVLNode<T> empty() {
		return new AVLNodeEmpty<>(comparator);
	}

	public void setTreeRoot(AVLNode<T> root) {
		try {
			// Here, we use an advanced Java technique called reflection
			// to overwrite a private field (root) within the AVLTree class
			// Reflection is a code smell in that it should typically be avoided,
			// but it can be extremely useful for writing test code.
			Field rootField = tree.getClass().getDeclaredField("root");
			rootField.setAccessible(true);
			rootField.set(tree, root);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public void setTreeRootWithCast(Object root) {
		setTreeRoot((AVLNode<T>) root);
	}

	public AVLTree<T> getTree() {
		return tree;
	}
}
