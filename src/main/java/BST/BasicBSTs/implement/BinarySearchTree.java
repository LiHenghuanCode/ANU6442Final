package BST.BasicBSTs.implement;

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> {

    private class Node {
        T value;
        Node left, right;
        Node(T value) { this.value = value; }
    }

    private Node root;
    private int size = 0;

    // ================= 基础操作 =================

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public boolean contains(T value) {
        return contains(root, value);
    }

    private boolean contains(Node node, T value) {
        if (node == null) return false;
        int cmp = value.compareTo(node.value);
        if (cmp == 0) return true;
        else if (cmp < 0) return contains(node.left, value);
        else return contains(node.right, value);
    }

    public void insert(T value) {
        root = insert(root, value);
    }

    private Node insert(Node node, T value) {
        if (node == null) {
            size++;
            return new Node(value);
        }
        int cmp = value.compareTo(node.value);
        if (cmp < 0) node.left = insert(node.left, value);
        else if (cmp > 0) node.right = insert(node.right, value);
        return node;
    }

    public void remove(T value) {
        root = remove(root, value);
    }

    private Node remove(Node node, T value) {
        if (node == null) return null;

        int cmp = value.compareTo(node.value);
        if (cmp < 0) node.left = remove(node.left, value);
        else if (cmp > 0) node.right = remove(node.right, value);
        else {
            if (node.left == null) { size--; return node.right; }
            else if (node.right == null) { size--; return node.left; }
            Node minNode = findMin(node.right);
            node.value = minNode.value;
            node.right = remove(node.right, minNode.value);
        }
        return node;
    }

    // ================= 极值 =================

    public T findMin() {
        if (root == null) throw new NoSuchElementException("Tree is empty");
        return findMin(root).value;
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public T findMax() {
        if (root == null) throw new NoSuchElementException("Tree is empty");
        return findMax(root).value;
    }

    private Node findMax(Node node) {
        while (node.right != null) node = node.right;
        return node;
    }

    // ================= 遍历 =================

    public List<T> preorder() {
        List<T> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    private void preorder(Node node, List<T> res) {
        if (node == null) return;
        res.add(node.value);
        preorder(node.left, res);
        preorder(node.right, res);
    }

    public List<T> inorder() {
        List<T> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(Node node, List<T> res) {
        if (node == null) return;
        inorder(node.left, res);
        res.add(node.value);
        inorder(node.right, res);
    }

    public List<T> postorder() {
        List<T> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    private void postorder(Node node, List<T> res) {
        if (node == null) return;
        postorder(node.left, res);
        postorder(node.right, res);
        res.add(node.value);
    }

    public List<T> levelOrder() {
        List<T> result = new ArrayList<>();
        if (root == null) return result;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node n = q.poll();
            result.add(n.value);
            if (n.left != null) q.add(n.left);
            if (n.right != null) q.add(n.right);
        }
        return result;
    }

    // ================= 工具 =================

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public T predecessor(T value) {
        Node curr = root, pre = null;
        while (curr != null) {
            int cmp = value.compareTo(curr.value);
            if (cmp > 0) {
                pre = curr;
                curr = curr.right;
            } else curr = curr.left;
        }
        return pre == null ? null : pre.value;
    }

    public T successor(T value) {
        Node curr = root, suc = null;
        while (curr != null) {
            int cmp = value.compareTo(curr.value);
            if (cmp < 0) {
                suc = curr;
                curr = curr.left;
            } else curr = curr.right;
        }
        return suc == null ? null : suc.value;
    }

    // 最近公共祖先（LCA）
    public T lowestCommonAncestor(T a, T b) {
        Node node = root;
        while (node != null) {
            if (a.compareTo(node.value) < 0 && b.compareTo(node.value) < 0)
                node = node.left;
            else if (a.compareTo(node.value) > 0 && b.compareTo(node.value) > 0)
                node = node.right;
            else return node.value;
        }
        return null;
    }

    // 镜像反转
    public void invert() {
        invert(root);
    }

    private void invert(Node node) {
        if (node == null) return;
        Node tmp = node.left;
        node.left = node.right;
        node.right = tmp;
        invert(node.left);
        invert(node.right);
    }

    // ================= 示例 =================
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);
        bst.insert(2);
        bst.insert(4);
        bst.insert(6);
        bst.insert(8);

        System.out.println("中序遍历: " + bst.inorder());
        System.out.println("最小值: " + bst.findMin());
        System.out.println("最大值: " + bst.findMax());
        System.out.println("高度: " + bst.height());
        System.out.println("前驱(6): " + bst.predecessor(6));
        System.out.println("后继(6): " + bst.successor(6));
        System.out.println("LCA(2,4): " + bst.lowestCommonAncestor(2, 4));

        bst.remove(7);
        System.out.println("删除7后中序遍历: " + bst.inorder());
    }
}

