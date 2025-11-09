package BST.Btree.implement;

public class BTreeExample {
    public static void main(String[] args) {
        System.out.println("=== B树示例 (t=3) ===\n");

        // 创建B树，最小度数为3
        BTree<Integer> tree = new BTree<>(3);

        // 插入测试
        System.out.println("插入数据: 10, 20, 5, 6, 12, 30, 7, 17, 3, 8, 25, 15, 35, 40");
        int[] values = {10, 20, 5, 6, 12, 30, 7, 17, 3, 8, 25, 15, 35, 40};

        for (int val : values) {
            tree.insert(val);
            System.out.println("\n插入 " + val + " 后:");
            tree.printTree();
        }

        System.out.println("\n=== 最终树结构 ===");
        tree.printTree();

        System.out.println("\n=== 中序遍历 ===");
        tree.inorderTraversal();

        System.out.println("\n=== 查找测试 ===");
        System.out.println("查找 6: " + tree.get(6));
        System.out.println("查找 15: " + tree.get(15));
        System.out.println("查找 100: " + tree.get(100));

        System.out.println("\n=== 包含测试 ===");
        System.out.println("包含 20: " + tree.contains(20));
        System.out.println("包含 50: " + tree.contains(50));

        System.out.println("\n=== 索引访问测试 ===");
        System.out.println("树的大小: " + tree.size());
        System.out.println("索引 0: " + tree.getAtIndex(0));
        System.out.println("索引 5: " + tree.getAtIndex(5));
        System.out.println("索引 10: " + tree.getAtIndex(10));

        System.out.println("\n=== 随机访问测试 ===");
        System.out.print("随机获取5个元素: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(tree.getRandom() + " ");
        }
        System.out.println();

        // 测试小树
        System.out.println("\n\n=== 小型B树示例 (t=2) ===");
        BTree<String> smallTree = new BTree<>(2);
        String[] words = {"apple", "banana", "cherry", "date", "elderberry", "fig", "grape"};

        for (String word : words) {
            smallTree.insert(word);
        }

        System.out.println("\n树结构:");
        smallTree.printTree();

        System.out.println("\n中序遍历:");
        smallTree.inorderTraversal();
    }
}

