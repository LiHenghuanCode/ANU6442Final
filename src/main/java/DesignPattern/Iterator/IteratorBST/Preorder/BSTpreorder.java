package DesignPattern.Iterator.IteratorBST.Preorder;

import java.util.*;

public class BSTpreorder implements Iterable<Integer> {
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

    // 基于栈的前序遍历
    public List<Integer> preorderIterative() {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Deque<Node> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            result.add(current.value);

            // 注意：先压右子树，再压左子树
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
        return result;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new BSTPreorderIterator(root);
    }

    // ----------------------------
    //   前序遍历迭代器实现
    // ----------------------------
    private class BSTPreorderIterator implements Iterator<Integer> {
        private Stack<Node> stack = new Stack<>();

        BSTPreorderIterator(Node root) {
            if (root != null) stack.push(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Integer next() {
            Node node = stack.pop();
            int val = node.value;

            // 注意顺序：先右后左
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            return val;
        }
    }
}
