package DesignPattern.Iterator.IteratorBST.Inorder;

import java.util.*;

public class BSTinorder implements Iterable<Integer>{
    // 内部节点类
    class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    private Node root;

    // 插入节点
    public void insert(int value) {
        root = insertRec(root, value);
    }

    private Node insertRec(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }
        if (value < node.value) {
            node.left = insertRec(node.left, value);
        } else if (value > node.value) {
            node.right = insertRec(node.right, value);
        }
        return node;
    }

    // 基于栈的中序遍历
    public List<Integer> inorderIterative() {
        List<Integer> result = new ArrayList<>();
        Deque<Node> stack = new LinkedList<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            // 一路向左压栈
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            // 弹出栈顶
            current = stack.pop();
            result.add(current.value);
            // 转向右子树
            current = current.right;
        }
        return result;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new BSTIterator(root);
    }
    // ----------------------------
    //   中序遍历迭代器实现
    // ----------------------------
    private class BSTIterator implements Iterator<Integer> {
        private Stack<Node> stack = new Stack<>();

        BSTIterator(Node root) {
            pushLeft(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Integer next() {
            Node node = stack.pop();
            int val = node.value;
            if (node.right != null) {
                pushLeft(node.right);
            }
            return val;
        }

        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
    }

}
