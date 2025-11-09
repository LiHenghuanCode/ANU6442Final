package DesignPattern.Iterator.IteratorList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 一个简单的单向链表实现
 */
public class MyLinkedList<T> implements Iterable<T> {

    // 内部节点类（链表的基本单元）
    class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> head;  // 链表头
    private Node<T> tail;  // 链表尾
    private int size;      // 元素数量

    /** 添加到链表末尾 */
    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (head == null) { // 空链表
            head = newNode;
            tail = newNode;
        } else {            // 非空链表
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /** 返回链表中元素个数 */
    public int size() {
        return size;
    }

    /** 获取第 i 个元素（0-based） */
    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        Node<T> current = head;
        for (int i = 0; i < index; i++)
            current = current.next;
        return current.value;
    }

    /** 迭代器实现，使链表可用于 for-each */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head; // 从头开始迭代

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                T value = current.value;
                current = current.next;
                return value;
            }
        };
    }

    // 测试
    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        System.out.println("链表长度: " + list.size());

        // 使用 for-each 遍历（自动调用 iterator()）
        for (String s : list) {
            System.out.println(s);
        }

        // 手动使用迭代器
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.println("next(): " + it.next());
        }
    }
}

