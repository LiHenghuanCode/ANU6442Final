package BST.Btree.implement;

import java.util.Random;

public class BTree<T extends Comparable<T>> {
    private static final Random random = new Random();
    private BNode<T> root;
    private final int t;  // 最小度数

    public BTree(int t) {
        if (t < 2) {
            throw new IllegalArgumentException("最小度数必须 >= 2");
        }
        this.t = t;
        this.root = new BNodeEmpty<>(t);
    }

    private BTree(int t, BNode<T> root) {
        this.t = t;
        this.root = root;
    }

    public BTree<T> clone() {
        return new BTree<>(t, root);
    }

    public boolean insert(T element) {
        if (root.contains(element)) return false;

        BNodeFilled<T> newRoot = root.insert(element);
        root = newRoot;

        return true;
    }

    public T get(T value) {
        return root.get(value);
    }

    public boolean contains(T value) {
        return root.contains(value);
    }

    @Override
    public String toString() {
        return "BTree[t=" + t + ", " + root.toString() + "]";
    }

    public T getRandom() {
        if (root.size() == 0) return null;
        return root.getAtIndex(random.nextInt(root.size()));
    }

    public T getAtIndex(int i) {
        if (root instanceof BNodeEmpty) return null;
        return root.getAtIndex(i);
    }

    public int size() {
        return root.size();
    }

    // 中序遍历
    public void inorderTraversal() {
        inorderHelper(root);
        System.out.println();
    }

    private void inorderHelper(BNode<T> node) {
        if (node instanceof BNodeEmpty) return;

        BNodeFilled<T> filled = (BNodeFilled<T>) node;

        for (int i = 0; i < filled.n; i++) {
            // 遍历左子树
            inorderHelper(filled.children[i]);
            // 打印关键字
            System.out.print(filled.keys[i] + " ");
        }
        // 遍历最后一个子树
        inorderHelper(filled.children[filled.n]);
    }

    // 打印树结构
    public void printTree() {
        printHelper(root, "", true);
    }

    private void printHelper(BNode<T> node, String prefix, boolean isTail) {
        if (node instanceof BNodeEmpty) return;

        BNodeFilled<T> filled = (BNodeFilled<T>) node;

        System.out.print(prefix + (isTail ? "└── " : "├── "));
        System.out.print("[");
        for (int i = 0; i < filled.n; i++) {
            System.out.print(filled.keys[i]);
            if (i < filled.n - 1) System.out.print(",");
        }
        System.out.println("]");

        if (!filled.isLeaf) {
            for (int i = 0; i <= filled.n; i++) {
                boolean isLast = (i == filled.n);
                printHelper(filled.children[i], prefix + (isTail ? "    " : "│   "), isLast);
            }
        }
    }
}
