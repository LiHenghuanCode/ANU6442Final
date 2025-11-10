# 树结构模拟练习题

## 说明
本文档包含6道模拟题,覆盖BST基础、AVL树和红黑树的核心概念。
每道题都包含完整的题目描述、方法签名和测试用例提示。

---

## 第一部分:二叉搜索树(BST)基础

### 题目1:查找偶数子节点和 (Even Children Sum)

**问题描述:**

给定一个二叉搜索树,实现一个方法来计算所有拥有**偶数个直接子节点**的节点值之和。

例如:
```
        10
       /  \
      5    15
     /      \
    3       20
```
- 节点10有2个子节点(偶数)→ 计入
- 节点5有1个子节点(奇数)→ 不计入
- 节点15有1个子节点(奇数)→ 不计入
- 节点3有0个子节点(偶数)→ 计入
- 节点20有0个子节点(偶数)→ 计入

**结果:** 10 + 3 + 20 = 33

**方法签名:**
```java
public Integer evenChildrenSum()
```

**实现要求:**
- 可以添加辅助方法
- 必须使用递归实现
- 空树返回0

**提示:**
- 叶子节点(0个子节点)也是偶数
- 考虑用helper方法传递当前节点

---

### 题目2:后序遍历实现 (Postorder Traversal)

**问题描述:**

实现一个方法,返回二叉搜索树的后序遍历结果。后序遍历的顺序是:**左子树 → 右子树 → 根节点**。

例如对于树:
```
        8
       / \
      3   10
     / \    \
    1   6   14
       / \   /
      4   7 13
```

**后序遍历结果:** [1, 4, 7, 6, 3, 13, 14, 10, 8]

**方法签名:**
```java
public List<Integer> postOrder()
```

**实现要求:**
- 返回一个包含所有节点值的List
- 按后序遍历顺序排列
- 使用递归实现

**Node类结构:**
```java
public class Node {
    Integer value;
    Node left;
    Node right;
}
```

---

## 第二部分:AVL树

### 题目3:AVL树最小节点测试 (Minimal AVL Tree Testing)

**问题描述:**

作为软件测试工程师,你需要为学生提交的AVL树实现创建参数化测试用例。你的目标是**用最少的节点数**来验证AVL树的正确性。

**任务:**
1. 实现`data()`方法,返回测试用例集合
2. 实现`createTree(int index)`方法,根据索引创建对应的测试树

**测试用例要求:**
- 每个测试用例包含:树的索引、插入序列、期望的前序遍历结果
- 必须覆盖以下场景:
  - LL旋转(左左情况)
  - RR旋转(右右情况)  
  - LR旋转(左右情况)
  - RL旋转(右左情况)
- **所有测试树的总节点数最少**

**方法签名:**
```java
public static Collection<Object[]> data()
public AVLTree createTree(int index)
```

**AVL树接口:**
```java
public abstract class AVLTree {
    public abstract AVLTree insert(Integer value);
    public List<Integer> preOrder();
    public int getBalanceFactor();
    public int getHeight();
}
```

**提示:**
- LL旋转:连续插入三个递增值到左侧
- 最小测试树可以只用3-4个节点
- 前序遍历顺序:根 → 左 → 右

**示例测试用例格式:**
```java
// 测试用例0: LL旋转
插入序列: 3, 2, 1
期望前序遍历: [2, 1, 3]
```

---

### 题目4:AVL树最大深度差 (Maximum Height Difference)

**问题描述:**

实现一个方法,找出AVL树中**任意节点的左右子树高度差的最大值**。对于正确的AVL树,这个值应该≤1。

例如:
```
        10 (差值=1)
       /  \
      5    15 (差值=2,不平衡!)
     / \     \
    3   7    20
   /          \
  1           25
```

节点15的左子树高度=0,右子树高度=2,差值=2(违反AVL性质)

**方法签名:**
```java
public int maxHeightDifference()
```

**实现要求:**
- 返回树中所有节点的最大高度差
- 需要遍历所有节点
- 可以添加辅助方法

**提示:**
- 可以重用`getHeight()`和`getBalanceFactor()`方法
- 考虑递归遍历所有节点

---

## 第三部分:红黑树

### 题目5:红黑树性质4检验 (Property 4 Verification)

**问题描述:**

实现一个方法检验红黑树的**性质4**:如果一个节点是红色,则它的两个子节点都必须是黑色。

**红黑树性质回顾:**
1. 每个节点是红色或黑色
2. 根节点是黑色
3. 所有叶节点(NIL)是黑色
4. **红色节点的子节点必须是黑色**(不能有连续的红色节点)
5. 从任意节点到其叶子节点的所有路径包含相同数量的黑色节点

**方法签名:**
```java
public boolean testProp4()
```

**返回值:**
- `true`: 性质4成立
- `false`: 存在红色节点有红色子节点

**Node类结构:**
```java
public class Node<T> {
    Colour colour; // RED 或 BLACK
    T value;
    Node<T> parent;
    Node<T> left, right;
}
```

**测试示例:**
```
正确的树:
        B(10)
       /    \
     R(5)   R(15)
     / \     / \
   B(3) B(7) B(12) B(20)

错误的树:
        B(10)
       /    \
     R(5)   R(15)
     / \     
   R(3) B(7)  ← 节点5(红)的左子节点3(红)违反性质4
```

**提示:**
- 只需检查红色节点的子节点颜色
- 需要递归遍历整棵树
- 空节点(NIL)视为黑色

---

### 题目6:红黑树JSON序列化 (JSON Serialization)

**问题描述:**

实现三个方法,完成红黑树的JSON序列化功能:
1. 从JSON文件读取数据
2. 构建红黑树
3. 按层序遍历(从左到右)输出JSON

**JSON输入格式:**
```json
[
  {"id": 50, "name": "Alice", "score": 95},
  {"id": 25, "name": "Bob", "score": 87},
  {"id": 75, "name": "Charlie", "score": 92}
]
```

**JSON输出格式(按层序遍历):**
```json
{
  "students": [
    {"id": 50, "name": "Alice", "score": 95, "color": "BLACK"},
    {"id": 25, "name": "Bob", "score": 87, "color": "RED"},
    {"id": 75, "name": "Charlie", "score": 92, "color": "RED"}
  ]
}
```

**方法签名:**
```java
// 读取JSON文件
public List<Student> readJson(String fileName)

// 构建红黑树
public RBTree<Integer, Student> createTree(List<Student> students)

// 保存为JSON(层序遍历,从左到右)
public void saveJson(RBTree<Integer, Student> tree)
```

**层序遍历要求:**
- 从上到下,每层从**左到右**
- 示例树:
```
          50(B)
         /    \
      25(R)   75(R)
      /  \    /  \
   10(B) 30(B) 60(B) 80(B)
```
输出顺序:[50, 25, 75, 10, 30, 60, 80]

**实现要求:**
- 不允许使用外部JSON库
- 必须使用Java 8+标准库
- 输出文件名必须是`students.json`
- JSON格式区分大小写,必须严格遵循格式

**Student类:**
```java
public class Student {
    Integer id;
    String name;
    Integer score;
    
    // 构造器和getter/setter
}
```

**提示:**
- 层序遍历提示:使用Queue
- JSON手动拼接或使用StringBuilder
- 注意逗号和缩进的处理

---

## 附录:通用说明

### 测试建议
1. 先画出树的结构图
2. 手动计算期望结果
3. 编写边界测试用例(空树、单节点等)

### 评分要点
- **正确性**(60%):核心逻辑正确
- **效率**(20%):使用合适的算法
- **代码质量**(20%):清晰的命名和注释

### 常见陷阱
- 忘记处理空节点(null)
- 递归没有终止条件
- AVL旋转后忘记更新高度
- 红黑树忘记检查NIL节点

---

**祝你练习顺利!** 🌲
