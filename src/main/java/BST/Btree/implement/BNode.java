package BST.Btree.implement;

abstract class BNode<T extends Comparable<T>> {
    protected final int t;  // 最小度数

    public BNode(int t) {
        this.t = t;
    }

    public abstract int size();

    public abstract BNodeFilled<T> insert(T element);

    public abstract T get(T element);

    public abstract boolean contains(T element);

    public abstract T getAtIndex(int i);
}
