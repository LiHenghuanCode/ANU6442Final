package BST.Btree.implement;

import java.lang.reflect.Field;

public class BTestBuilder<T extends Comparable<T>> {
    private final int t;
    private final BTree<T> tree;

    public BTestBuilder(int t) {
        this.t = t;
        this.tree = new BTree<>(t);
    }

    public BNode<T> makeWithCast(Object keys, Object children, int n, boolean isLeaf) {
        return make((T[]) keys, (BNode<T>[]) children, n, isLeaf);
    }

    @SuppressWarnings("unchecked")
    public BNode<T> make(T[] keys, BNode<T>[] children, int n, boolean isLeaf) {
        return new BNodeFilled<T>(t, keys, children, n, isLeaf);
    }

    public BNode<T> empty() {
        return new BNodeEmpty<>(t);
    }

    public void setTreeRoot(BNode<T> root) {
        try {
            Field rootField = tree.getClass().getDeclaredField("root");
            rootField.setAccessible(true);
            rootField.set(tree, root);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void setTreeRootWithCast(Object root) {
        setTreeRoot((BNode<T>) root);
    }

    public BTree<T> getTree() {
        return tree;
    }
}
