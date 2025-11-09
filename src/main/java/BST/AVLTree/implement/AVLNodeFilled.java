package BST.AVLTree.implement;

import BST.AVLTree.implement.AVLNode;

import java.util.Comparator;

class AVLNodeFilled<T> extends AVLNode<T> {
	final AVLNode<T> left, right;
	final T value;
	private final int height, balance, size;
	public AVLNodeFilled(Comparator<T> comparator, T value, AVLNode<T> left, AVLNode<T> right) {
		super(comparator);
		this.value = value;
		this.left = left;
		this.right = right;
		this.size = left.size() + right.size() + 1;
		this.height = Math.max(left.height(), right.height())+1;


		this.balance = left.height() - right.height();
	}

	public int height() {
		return height;
	}
	public int balanceFactor() {
		return balance;
	}
	public int size() {
		return size;
	}

	public String toString() {
		if (left instanceof AVLNodeEmpty<T> && right instanceof AVLNodeEmpty<T>)
			return value.toString();
		else
			return "%s -> (%s, %s)".formatted(value.toString(), left.toString(), right.toString());
	}

	public AVLNodeFilled<T> insert(T element) {

		int cmp = comparator.compare(element, value);
		AVLNodeFilled<T> rebuilt = this;

		if (cmp < 0) {
			AVLNodeFilled<T> newLeft = left.insert(element);
			// 以同一个 value，替换左子树为 newLeft
			rebuilt = new AVLNodeFilled<>(comparator, value, newLeft, right);
		} else if (cmp > 0) {
			AVLNodeFilled<T> newRight = right.insert(element);
			rebuilt = new AVLNodeFilled<>(comparator, value, left, newRight);
		} else {
			return this;
		}

		int b = rebuilt.balanceFactor();
		if (b > 1) {
			AVLNodeFilled<T> L = (AVLNodeFilled<T>) rebuilt.left;
			if (L.balanceFactor() >= 0) {
				return rebuilt.rightRotate();
			} else {
				AVLNodeFilled<T> newLeft = L.leftRotate();
				AVLNodeFilled<T> mid = new AVLNodeFilled<>(comparator, rebuilt.value, newLeft, rebuilt.right);
				return mid.rightRotate();
			}
		} else if (b < -1) {
			AVLNodeFilled<T> R = (AVLNodeFilled<T>) rebuilt.right;
			if (R.balanceFactor() <= 0) {
				return rebuilt.leftRotate();
			} else {
				AVLNodeFilled<T> newRight = R.rightRotate();
				AVLNodeFilled<T> mid = new AVLNodeFilled<>(comparator, rebuilt.value, rebuilt.left, newRight);
				return mid.leftRotate();
			}
		}
		return rebuilt;
	}


	private AVLNodeFilled<T> leftRotate() {
		AVLNodeFilled<T> x = this;
		AVLNodeFilled<T> y = (AVLNodeFilled<T>) x.right;
		AVLNode<T> yL = y.left;


		AVLNodeFilled<T> newX = new AVLNodeFilled<>(comparator, x.value, x.left, yL);

		return new AVLNodeFilled<>(comparator, y.value, newX, y.right);
	}


	private AVLNodeFilled<T> rightRotate() {
		// TODO: Complete this method
		AVLNodeFilled<T> y = this;
		AVLNodeFilled<T> x = (AVLNodeFilled<T>) y.left;
		AVLNode<T> xR = x.right;

		AVLNodeFilled<T> newY = new AVLNodeFilled<>(comparator, y.value, xR, y.right);

		return new AVLNodeFilled<>(comparator, x.value, x.left, newY);
	}

	public T getAtIndex(int i) {
		if (i < left.size()) return left.getAtIndex(i);
		else if (i == left.size()) return value;
		return right.getAtIndex(i - left.size() - 1);
	}

	public boolean contains(T element) {
		if (comparator.compare(value, element) < 0) {
			return right.contains(element);
		} else if (comparator.compare(element, value) < 0) {
			return left.contains(element);
		}
		return true;
	}

	public T get(T element) {
		if (comparator.compare(value, element) < 0) {
			return right.get(element);
		} else if (comparator.compare(element, value) < 0) {
			return left.get(element);
		}
		return value;
	}
}
