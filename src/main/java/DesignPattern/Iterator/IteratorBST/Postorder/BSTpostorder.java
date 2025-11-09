package DesignPattern.Iterator.IteratorBST.Postorder;

import java.util.*;

public class BSTpostorder implements Iterable<Integer> {
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

    // 基于两个栈的后序遍历
    public List<Integer> postorderIterative() {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();

        stack1.push(root);
        while (!stack1.isEmpty()) {
            Node node = stack1.pop();
            stack2.push(node);

            if (node.left != null) stack1.push(node.left);
            if (node.right != null) stack1.push(node.right);
        }

        while (!stack2.isEmpty()) {
            result.add(stack2.pop().value);
        }

        return result;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new BSTPostorderIterator(root);
    }

    // ----------------------------
    //   后序遍历迭代器实现
    // ----------------------------
    private class BSTPostorderIterator implements Iterator<Integer> {
        private Stack<Node> stack1 = new Stack<>();
        private Stack<Node> stack2 = new Stack<>();

        BSTPostorderIterator(Node root) {
            if (root != null) {
                stack1.push(root);
                while (!stack1.isEmpty()) {
                    Node node = stack1.pop();
                    stack2.push(node);
                    if (node.left != null) stack1.push(node.left);
                    if (node.right != null) stack1.push(node.right);
                }
            }
        }

        @Override
        public boolean hasNext() {
            return !stack2.isEmpty();
        }

        @Override
        public Integer next() {
            return stack2.pop().value;
        }
    }
}
