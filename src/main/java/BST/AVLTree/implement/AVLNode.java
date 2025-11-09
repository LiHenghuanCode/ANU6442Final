package BST.AVLTree.implement;

import java.util.Comparator;


abstract class AVLNode<T> {
	protected final Comparator<T> comparator;


	public AVLNode(Comparator<T> comparator) {
		this.comparator = comparator;
	}


	public abstract int height();


	public abstract int balanceFactor();


	public abstract int size();


	public abstract AVLNodeFilled<T> insert(T element);


	public abstract T getAtIndex(int i);


	public abstract T get(T element);


	public abstract boolean contains(T element);
}
