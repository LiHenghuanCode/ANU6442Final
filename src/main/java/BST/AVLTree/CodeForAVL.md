
AVL 树 是一种 自平衡二叉搜索树（Self-Balancing Binary Search Tree）。

它在普通二叉搜索树（BST）的基础上，在每个节点维护一个“平衡因子”（balance factor），
从而保证树的高度始终接近 log₂(n)，避免退化为链表。

### 平衡因子（Balance Factor）
对于任意一个节点：
```java
balanceFactor = height(left subtree) - height(right subtree)
```
balanceFactor ∈ {-1, 0, +1}

当插入或删除导致不平衡时，需要旋转来恢复平衡。

### 右旋（Right Rotation）
1. 触发时机
2. 
当出现 左-左（LL）不平衡 时使用右旋。
即：某个节点的左子树过高，并且新节点插入在左子树的左侧。

2. 结构示意

失衡前：

        y
       /
      x
     /
    z

此时：
y 是失衡的节点；
x 是 y 的左孩子；
z 是 x 的左孩子。

3. 右旋操作过程
目标：让 x 上移成为新的根，y 下移到右边。
步骤：
暂存 x 的右子树（记为 T2），因为旋转后它要成为 y 的左子树；
把 y 放到 x 的右边；
把 T2 接到 y 的左边。
操作后：
        x
       / \
      z   y
         /
       T2

```java
AVLNode rightRotate(AVLNode y) {
    AVLNode x = y.left;   // x 是 y 的左子
    AVLNode T2 = x.right; // 暂存 x 的右子

    // 执行旋转
    x.right = y;
    y.left = T2;

    // 更新高度
    y.height = Math.max(height(y.left), height(y.right)) + 1;
    x.height = Math.max(height(x.left), height(x.right)) + 1;

    // 返回新根
    return x;
}

```

### 左旋（Left Rotation）
1. 触发时机

当出现 右-右（RR）不平衡 时使用左旋。
即：某个节点的右子树过高，并且新节点插入在右子树的右侧。

2. 结构示意
失衡前：

    x
     \
      y
       \
        z
此时：
x 是失衡的节点；
y 是 x 的右孩子；
z 是 y 的右孩子。
3. 左旋操作过程
目标：让 y 上移成为新的根，x 下移到左边。
步骤：
暂存 y 的左子树（记为 T2），因为旋转后它要成为 x 的右子树；
把 x 放到 y 的左边；
把 T2 接到 x 的右边。
旋转后：
        y
       / \
      x   z
       \
       T2
