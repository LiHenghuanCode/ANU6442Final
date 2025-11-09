package BST.Btree.implement;

class BNodeEmpty<T extends Comparable<T>> extends BNode<T> {

    public BNodeEmpty(int t) {
        super(t);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public BNodeFilled<T> insert(T element) {
        // 空节点插入：创建只有一个元素的新节点
        T[] keys = (T[]) new Comparable[1];
        keys[0] = element;

        BNode<T>[] children = new BNode[2];
        children[0] = new BNodeEmpty<>(t);
        children[1] = new BNodeEmpty<>(t);

        return new BNodeFilled<>(t, keys, children, 1, true);
    }

    @Override
    public T get(T element) {
        return null;
    }

    @Override
    public boolean contains(T element) {
        return false;
    }

    @Override
    public T getAtIndex(int i) {
        return null;
    }

    @Override
    public String toString() {
        return ".";
    }
}
