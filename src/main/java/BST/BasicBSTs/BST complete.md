# 二叉树完整学习手册 (LeetCode Java版)

## 目录
1. [基础模板代码](#基础模板代码)
2. [遍历模板流程图](#遍历模板流程图)
3. [LeetCode核心考点分类](#leetcode核心考点分类)

---


## LeetCode核心考点分类

### 考点1: 递归基础 (Recursion Basics)

#### 1.1 最大深度 (LeetCode 104)
**问题：** 给定一个二叉树，找出其最大深度。二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

```java
public int maxDepth(TreeNode root) {
    if (root == null) return 0;
    
    int leftDepth = maxDepth(root.left);
    int rightDepth = maxDepth(root.right);
    
    return Math.max(leftDepth, rightDepth) + 1;
}
```

#### 1.2 最小深度 (LeetCode 111)
**问题：** 给定一个二叉树，找出其最小深度。最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

```java
public int minDepth(TreeNode root) {
    if (root == null) return 0;
    
    // 叶子节点
    if (root.left == null && root.right == null) return 1;
    
    // 只有一侧子树时，不能取min，要取存在的那一侧
    if (root.left == null) return minDepth(root.right) + 1;
    if (root.right == null) return minDepth(root.left) + 1;
    
    return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
}
```

#### 1.3 翻转二叉树 (LeetCode 226)
**问题：** 给你一棵二叉树的根节点 root，翻转这棵二叉树，并返回其根节点。（即左右子树互换）

```java
public TreeNode invertTree(TreeNode root) {
    if (root == null) return null;
    
    // 交换左右子树
    TreeNode temp = root.left;
    root.left = root
       .right;
    root.right = temp;
    
    // 递归翻转左右子树
    invertTree(root.left);
    invertTree(root.right);
    
    return root;
}
```

#### 1.4 对称二叉树 (LeetCode 101)
**问题：** 给你一个二叉树的根节点 root，检查它是否轴对称。

```java
public boolean isSymmetric(TreeNode root) {
    if (root == null) return true;
    return isMirror(root.left, root.right);
}

private boolean isMirror(TreeNode left, TreeNode right) {
    if (left == null && right == null) return true;
    if (left == null || right == null) return false;
    
    return (left.val == right.val) 
        && isMirror(left.left, right.right)   // 外侧
        && isMirror(left.right, right.left);  // 内侧
}
```

#### 1.5 相同的树 (LeetCode 100)
**问题：** 给你两棵二叉树的根节点 p 和 q，编写一个函数来检验这两棵树是否相同。如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。

```java
public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null) return true;
    if (p == null || q == null) return false;
    
    return (p.val == q.val) 
        && isSameTree(p.left, q.left)
        && isSameTree(p.right, q.right);
}
```

---

### 考点2: 路径问题 (Path Problems)

#### 2.1 路径总和 (LeetCode 112)
**问题：** 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum。判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和 targetSum。如果存在，返回 true；否则，返回 false。

```java
public boolean hasPathSum(TreeNode root, int targetSum) {
    if (root == null) return false;
    
    // 叶子节点
    if (root.left == null && root.right == null) {
        return root.val == targetSum;
    }
    
    return hasPathSum(root.left, targetSum - root.val) 
        || hasPathSum(root.right, targetSum - root.val);
}
```

#### 2.2 路径总和 II (LeetCode 113)
**问题：** 给你二叉树的根节点 root 和一个整数目标和 targetSum，找出所有从根节点到叶子节点路径总和等于给定目标和的路径。

```java
public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    dfs(root, targetSum, path, result);
    return result;
}

private void dfs(TreeNode node, int sum, List<Integer> path, List<List<Integer>> result) {
    if (node == null) return;
    
    path.add(node.val);
    
    // 叶子节点且路径和匹配
    if (node.left == null && node.right == null && sum == node.val) {
        result.add(new ArrayList<>(path));  // 必须复制
    }
    
    dfs(node.left, sum - node.val, path, result);
    dfs(node.right, sum - node.val, path, result);
    
    path.remove(path.size() - 1);  // 回溯
}
```

#### 2.3 二叉树的所有路径 (LeetCode 257)
**问题：** 给你一个二叉树的根节点 root，按任意顺序，返回所有从根节点到叶子节点的路径。路径格式为 "1->2->5"。

```java
public List<String> binaryTreePaths(TreeNode root) {
    List<String> result = new ArrayList<>();
    if (root == null) return result;
    dfs(root, "", result);
    return result;
}

private void dfs(TreeNode node, String path, List<String> result) {
    if (node == null) return;
    
    path += node.val;
    
    // 叶子节点
    if (node.left == null && node.right == null) {
        result.add(path);
        return;
    }
    
    path += "->";
    dfs(node.left, path, result);
    dfs(node.right, path, result);
}
```

#### 2.4 路径总和 III (LeetCode 437)
**问题：** 给定一个二叉树的根节点 root，和一个整数 targetSum，求该二叉树里节点值之和等于 targetSum 的路径的数目。路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

```java
public int pathSum(TreeNode root, int targetSum) {
    if (root == null) return 0;
    
    // 以当前节点为起点的路径数 + 左子树路径数 + 右子树路径数
    return countPaths(root, targetSum) 
         + pathSum(root.left, targetSum)
         + pathSum(root.right, targetSum);
}

private int countPaths(TreeNode node, long sum) {
    if (node == null) return 0;
    
    int count = 0;
    if (node.val == sum) count++;
    
    count += countPaths(node.left, sum - node.val);
    count += countPaths(node.right, sum - node.val);
    
    return count;
}
```

#### 2.5 二叉树的最大路径和 (LeetCode 124)
**问题：** 二叉树中的路径被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中至多出现一次。该路径至少包含一个节点，且不一定经过根节点。路径和是路径中各节点值的总和。给你一个二叉树的根节点 root，返回其最大路径和。

```java
private int maxSum = Integer.MIN_VALUE;

public int maxPathSum(TreeNode root) {
    maxGain(root);
    return maxSum;
}

private int maxGain(TreeNode node) {
    if (node == null) return 0;
    
    // 只取正增益
    int leftGain = Math.max(maxGain(node.left), 0);
    int rightGain = Math.max(maxGain(node.right), 0);
    
    // 更新全局最大值（左-根-右的路径）
    int currentPathSum = node.val + leftGain + rightGain;
    maxSum = Math.max(maxSum, currentPathSum);
    
    // 返回单边最大增益（只能选左或右）
    return node.val + Math.max(leftGain, rightGain);
}
```

---

### 考点3: 构造二叉树 (Tree Construction)

#### 3.1 从前序与中序遍历构造 (LeetCode 105)
**问题：** 给定两个整数数组 preorder 和 inorder，其中 preorder 是二叉树的前序遍历，inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。

```java
private int preIndex = 0;
private Map<Integer, Integer> inorderMap = new HashMap<>();

public TreeNode buildTree(int[] preorder, int[] inorder) {
    // 中序数组建立值到索引的映射
    for (int i = 0; i < inorder.length; i++) {
        inorderMap.put(inorder[i], i);
    }
    
    return build(preorder, 0, inorder.length - 1);
}

private TreeNode build(int[] preorder, int inLeft, int inRight) {
    if (inLeft > inRight) return null;
    
    // 前序的第一个是根
    int rootVal = preorder[preIndex++];
    TreeNode root = new TreeNode(rootVal);
    
    // 找到根在中序中的位置
    int inIndex = inorderMap.get(rootVal);
    
    // 递归构造左右子树
    root.left = build(preorder, inLeft, inIndex - 1);
    root.right = build(preorder, inIndex + 1, inRight);
    
    return root;
}
```

#### 3.2 从中序与后序遍历构造 (LeetCode 106)
**问题：** 给定两个整数数组 inorder 和 postorder，其中 inorder 是二叉树的中序遍历，postorder 是同一棵树的后序遍历，请你构造并返回这颗二叉树。

```java
private int postIndex;
private Map<Integer, Integer> inorderMap = new HashMap<>();

public TreeNode buildTree(int[] inorder, int[] postorder) {
    postIndex = postorder.length - 1;
    
    for (int i = 0; i < inorder.length; i++) {
        inorderMap.put(inorder[i], i);
    }
    
    return build(postorder, 0, inorder.length - 1);
}

private TreeNode build(int[] postorder, int inLeft, int inRight) {
    if (inLeft > inRight) return null;
    
    // 后序的最后一个是根
    int rootVal = postorder[postIndex--];
    TreeNode root = new TreeNode(rootVal);
    
    int inIndex = inorderMap.get(rootVal);
    
    // 注意：先构造右子树（因为后序是从后往前）
    root.right = build(postorder, inIndex + 1, inRight);
    root.left = build(postorder, inLeft, inIndex - 1);
    
    return root;
}
```

#### 3.3 最大二叉树 (LeetCode 654)
**问题：** 给定一个不重复的整数数组 nums。最大二叉树可以用下面的算法从 nums 递归地构建：
1. 创建一个根节点，其值为 nums 中的最大值
2. 递归地在最大值左边的子数组前缀上构建左子树
3. 递归地在最大值右边的子数组后缀上构建右子树

返回 nums 构建的最大二叉树。

```java
public TreeNode constructMaximumBinaryTree(int[] nums) {
    return build(nums, 0, nums.length - 1);
}

private TreeNode build(int[] nums, int left, int right) {
    if (left > right) return null;
    
    // 找到最大值的索引
    int maxIndex = left;
    for (int i = left + 1; i <= right; i++) {
        if (nums[i] > nums[maxIndex]) {
            maxIndex = i;
        }
    }
    
    TreeNode root = new TreeNode(nums[maxIndex]);
    root.left = build(nums, left, maxIndex - 1);
    root.right = build(nums, maxIndex + 1, right);
    
    return root;
}
```

---

### 考点4: 二叉搜索树 (Binary Search Tree)

#### 4.1 验证BST (LeetCode 98)
**问题：** 给你一个二叉树的根节点 root，判断其是否是一个有效的二叉搜索树。有效二叉搜索树定义如下：
- 节点的左子树只包含小于当前节点的数
- 节点的右子树只包含大于当前节点的数
- 所有左子树和右子树自身必须也是二叉搜索树

```java
public boolean isValidBST(TreeNode root) {
    return validate(root, null, null);
}

private boolean validate(TreeNode node, Integer lower, Integer upper) {
    if (node == null) return true;
    
    int val = node.val;
    
    // 检查当前节点是否在范围内
    if (lower != null && val <= lower) return false;
    if (upper != null && val >= upper) return false;
    
    // 左子树所有节点 < val，右子树所有节点 > val
    return validate(node.left, lower, val) 
        && validate(node.right, val, upper);
}
```

#### 4.2 BST中第K小的元素 (LeetCode 230)
**问题：** 给定一个二叉搜索树的根节点 root，和一个整数 k，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。

```java
private int count = 0;
private int result = 0;

public int kthSmallest(TreeNode root, int k) {
    inorder(root, k);
    return result;
}

private void inorder(TreeNode node, int k) {
    if (node == null) return;
    
    inorder(node.left, k);
    
    count++;
    if (count == k) {
        result = node.val;
        return;
    }
    
    inorder(node.right, k);
}
```

#### 4.3 BST的最近公共祖先 (LeetCode 235)
**问题：** 给定一个二叉搜索树，找到该树中两个指定节点的最近公共祖先。最近公共祖先的定义为："对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。"

```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    int parentVal = root.val;
    int pVal = p.val;
    int qVal = q.val;
    
    // p和q都在左子树
    if (pVal < parentVal && qVal < parentVal) {
        return lowestCommonAncestor(root.left, p, q);
    }
    // p和q都在右子树
    else if (pVal > parentVal && qVal > parentVal) {
        return lowestCommonAncestor(root.right, p, q);
    }
    // 一个在左，一个在右，或者其中一个就是root
    else {
        return root;
    }
}
```

#### 4.4 将有序数组转换为BST (LeetCode 108)
**问题：** 给你一个整数数组 nums，其中元素已经按升序排列，请你将其转换为一棵高度平衡二叉搜索树。高度平衡二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1」的二叉树。

```java
public TreeNode sortedArrayToBST(int[] nums) {
    return build(nums, 0, nums.length - 1);
}

private TreeNode build(int[] nums, int left, int right) {
    if (left > right) return null;
    
    // 选择中间元素作为根
    int mid = left + (right - left) / 2;
    TreeNode root = new TreeNode(nums[mid]);
    
    root.left = build(nums, left, mid - 1);
    root.right = build(nums, mid + 1, right);
    
    return root;
}
```

#### 4.5 删除BST中的节点 (LeetCode 450)
**问题：** 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。

```java
public TreeNode deleteNode(TreeNode root, int key) {
    if (root == null) return null;
    
    if (key < root.val) {
        root.left = deleteNode(root.left, key);
    } else if (key > root.val) {
        root.right = deleteNode(root.right, key);
    } else {
        // 找到要删除的节点
        
        // 情况1：叶子节点或只有一个子节点
        if (root.left == null) return root.right;
        if (root.right == null) return root.left;
        
        // 情况2：有两个子节点
        // 找到右子树的最小节点（后继节点）
        TreeNode minNode = findMin(root.right);
        root.val = minNode.val;
        root.right = deleteNode(root.right, minNode.val);
    }
    
    return root;
}

private TreeNode findMin(TreeNode node) {
    while (node.left != null) {
        node = node.left;
    }
    return node;
}
```

---

### 考点5: 公共祖先问题 (Lowest Common Ancestor)

#### 5.1 二叉树的最近公共祖先 (LeetCode 236)
**问题：** 给定一个二叉树，找到该树中两个指定节点的最近公共祖先。最近公共祖先的定义为："对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。"

```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    // 递归终止条件
    if (root == null || root == p || root == q) return root;
    
    // 在左右子树中查找
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    
    // 如果p和q分别在左右子树，root就是LCA
    if (left != null && right != null) return root;
    
    // 否则返回非空的那个（p和q都在同一侧）
    return left != null ? left : right;
}
```

---

### 考点6: 序列化与反序列化 (Serialization)

#### 6.1 序列化和反序列化二叉树 (LeetCode 297)
**问题：** 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。请设计一个算法来实现二叉树的序列化与反序列化。

```java
public class Codec {
    
    // 序列化：前序遍历
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }
    
    private void serializeHelper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("null,");
            return;
        }
        
        sb.append(node.val).append(",");
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);
    }
    
    // 反序列化
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserializeHelper(queue);
    }
    
    private TreeNode deserializeHelper(Queue<String> queue) {
        String val = queue.poll();
        if (val.equals("null")) return null;
        
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = deserializeHelper(queue);
        node.right = deserializeHelper(queue);
        
        return node;
    }
}
```

---

### 考点7: 树的修改 (Tree Modification)

#### 7.1 合并二叉树 (LeetCode 617)
**问题：** 给你两棵二叉树：root1 和 root2。想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。

```java
public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
    if (root1 == null) return root2;
    if (root2 == null) return root1;
    
    // 合并当前节点
    TreeNode merged = new TreeNode(root1.val + root2.val);
    
    // 递归合并左右子树
    merged.left = mergeTrees(root1.left, root2.left);
    merged.right = mergeTrees(root1.right, root2.right);
    
    return merged;
}
```

#### 7.2 修剪二叉搜索树 (LeetCode 669)
**问题：** 给你二叉搜索树的根节点 root，同时给定最小边界 low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在 [low, high] 中。修剪树不应该改变保留在树中的元素的相对结构（即，如果没有被移除，原有的父代子代关系都应当保留）。返回修剪好的二叉搜索树的新的根节点。

```java
public TreeNode trimBST(TreeNode root, int low, int high) {
    if (root == null) return null;
    
    // 当前节点太小，去右子树找
    if (root.val < low) {
        return trimBST(root.right, low, high);
    }
    
    // 当前节点太大，去左子树找
    if (root.val > high) {
        return trimBST(root.left, low, high);
    }
    
    // 当前节点在范围内，递归修剪左右子树
    root.left = trimBST(root.left, low, high);
    root.right = trimBST(root.right, low, high);
    
    return root;
}
```

#### 7.3 二叉树展开为链表 (LeetCode 114)
**问题：** 给你二叉树的根结点 root，请你将它展开为一个单链表：
- 展开后的单链表应该同样使用 TreeNode，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null
- 展开后的单链表应该与二叉树前序遍历顺序相同

```java
public void flatten(TreeNode root) {
    if (root == null) return;
    
    // 后序遍历：先处理左右子树
    flatten(root.left);
    flatten(root.right);
    
    // 保存右子树
    TreeNode right = root.right;
    
    // 将左子树移到右边
    root.right = root.left;
    root.left = null;
    
    // 找到新右子树的末尾
    TreeNode curr = root;
    while (curr.right != null) {
        curr = curr.right;
    }
    
    // 连接原来的右子树
    curr.right = right;
}
```

---

### 考点8: 树的属性判断 (Tree Properties)

#### 8.1 平衡二叉树 (LeetCode 110)
**问题：** 给定一个二叉树，判断它是否是高度平衡的二叉树。高度平衡二叉树定义为：一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1。

```java
public boolean isBalanced(TreeNode root) {
    return checkHeight(root) != -1;
}

private int checkHeight(TreeNode node) {
    if (node == null) return 0;
    
    int leftHeight = checkHeight(node.left);
    if (leftHeight == -1) return -1;  // 左子树不平衡
    
    int rightHeight = checkHeight(node.right);
    if (rightHeight == -1) return -1;  // 右子树不平衡
    
    // 检查当前节点是否平衡
    if (Math.abs(leftHeight - rightHeight) > 1) return -1;
    
    return Math.max(leftHeight, rightHeight) + 1;
}
```

#### 8.2 完全二叉树的节点个数 (LeetCode 222)
**问题：** 给你一棵完全二叉树的根节点 root，求出该树的节点个数。完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。

```java
public int countNodes(TreeNode root) {
    if (root == null) return 0;
    
    int leftDepth = getDepth(root.left);
    int rightDepth = getDepth(root.right);
    
    if (leftDepth == rightDepth) {
        // 左子树是满二叉树
        return (1 << leftDepth) + countNodes(root.right);
    } else {
        // 右子树是满二叉树
        return (1 << rightDepth) + countNodes(root.left);
    }
}

private int getDepth(TreeNode node) {
    int depth = 0;
    while (node != null) {
        depth++;
        node = node.left;
    }
    return depth;
}
```

#### 8.3 二叉树的直径 (LeetCode 543)
**问题：** 给你一棵二叉树的根节点，返回该树的直径。二叉树的直径是指树中任意两个节点之间最长路径的长度。这条路径可能经过也可能不经过根节点。两节点之间路径的长度由它们之间边数表示。

```java
private int diameter = 0;

public int diameterOfBinaryTree(TreeNode root) {
    depth(root);
    return diameter;
}

private int depth(TreeNode node) {
    if (node == null) return 0;
    
    int leftDepth = depth(node.left);
    int rightDepth = depth(node.right);
    
    // 更新直径（左深度 + 右深度）
    diameter = Math.max(diameter, leftDepth + rightDepth);
    
    return Math.max(leftDepth, rightDepth) + 1;
}
```

#### 8.4 二叉树的坡度 (LeetCode 563)
**问题：** 给你一个二叉树的根节点 root，计算并返回整个树的坡度。一个树的节点的坡度定义即为，该节点左子树的节点之和和右子树节点之和的差的绝对值。如果没有左子树的话，左子树的节点之和为 0；没有右子树的话也是一样。空结点的坡度是 0。整个树的坡度就是其所有节点的坡度之和。

```java
private int totalTilt = 0;

public int findTilt(TreeNode root) {
    sum(root);
    return totalTilt;
}

private int sum(TreeNode node) {
    if (node == null) return 0;
    
    int leftSum = sum(node.left);
    int rightSum = sum(node.right);
    
    // 累加当前节点的坡度
    totalTilt += Math.abs(leftSum - rightSum);
    
    return node.val + leftSum + rightSum;
}
```

---

### 考点9: 层序遍历变种 (Level Order Variants)

#### 9.1 二叉树的右视图 (LeetCode 199)
**问题：** 给定一个二叉树的根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

```java
public List<Integer> rightSideView(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) return result;
    
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    
    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        
        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            
            // 每层的最后一个节点
            if (i == levelSize - 1) {
                result.add(node.val);
            }
            
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }
    
    return result;
}
```

#### 9.2 二叉树的层平均值 (LeetCode 637)
**问题：** 给定一个非空二叉树的根节点 root，以数组的形式返回每一层节点的平均值。

```java
public List<Double> averageOfLevels(TreeNode root) {
    List<Double> result = new ArrayList<>();
    if (root == null) return result;
    
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    
    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        double sum = 0;
        
        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            sum += node.val;
            
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        
        result.add(sum / levelSize);
    }
    
    return result;
}
```

#### 9.3 N叉树的层序遍历 (LeetCode 429)
**问题：** 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。

```java
class Node {
    public int val;
    public List<Node> children;
}

public List<List<Integer>> levelOrder(Node root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;
    
    Queue<Node> queue = new LinkedList<>();
    queue.offer(root);
    
    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        List<Integer> currentLevel = new ArrayList<>();
        
        for (int i = 0; i < levelSize; i++) {
            Node node = queue.poll();
            currentLevel.add(node.val);
            
            // 将所有子节点入队
            for (Node child : node.children) {
                queue.offer(child);
            }
        }
        
        result.add(currentLevel);
    }
    
    return result;
}
```

#### 9.4 在每个树行中找最大值 (LeetCode 515)
**问题：** 给定一棵二叉树的根节点 root，请找出该二叉树中每一层的最大值。

```java
public List<Integer> largestValues(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) return result;
    
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    
    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        int maxVal = Integer.MIN_VALUE;
        
        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            maxVal = Math.max(maxVal, node.val);
            
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        
        result.add(maxVal);
    }
    
    return result;
}
```

#### 9.5 二叉树的锯齿形层序遍历 (LeetCode 103)
**问题：** 给你二叉树的根节点 root，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

```java
public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;
    
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    boolean leftToRight = true;
    
    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        List<Integer> currentLevel = new ArrayList<>();
        
        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            
            // 根据方向决定插入位置
            if (leftToRight) {
                currentLevel.add(node.val);
            } else {
                currentLevel.add(0, node.val);  // 头部插入
            }
            
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        
        result.add(currentLevel);
        leftToRight = !leftToRight;  // 切换方向
    }
    
    return result;
}
```

---

### 考点10: 子树问题 (Subtree Problems)

#### 10.1 另一棵树的子树 (LeetCode 572)
**问题：** 给你两棵二叉树 root 和 subRoot。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true；否则，返回 false。二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。

```java
public boolean isSubtree(TreeNode root, TreeNode subRoot) {
    if (root == null) return false;
    
    // 检查当前树是否相同，或者在左右子树中找
    return isSameTree(root, subRoot) 
        || isSubtree(root.left, subRoot)
        || isSubtree(root.right, subRoot);
}

private boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null) return true;
    if (p == null || q == null) return false;
    
    return p.val == q.val 
        && isSameTree(p.left, q.left)
        && isSameTree(p.right, q.right);
}
```

#### 10.2 左叶子之和 (LeetCode 404)
**问题：** 给定二叉树的根节点 root，返回所有左叶子之和。叶子节点是指没有子节点的节点。左叶子是指一个节点是其父节点的左子节点，并且它是一个叶子节点。

```java
public int sumOfLeftLeaves(TreeNode root) {
    if (root == null) return 0;
    
    int sum = 0;
    
    // 检查左孩子是否为叶子节点
    if (root.left != null && root.left.left == null && root.left.right == null) {
        sum += root.left.val;
    }
    
    return sum + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
}
```

#### 10.3 找树左下角的值 (LeetCode 513)
**问题：** 给定一个二叉树的根节点 root，请找出该二叉树的最底层最左边节点的值。假设二叉树中至少有一个节点。

```java
public int findBottomLeftValue(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    int leftmost = 0;
    
    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        
        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            
            // 每层的第一个节点
            if (i == 0) {
                leftmost = node.val;
            }
            
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }
    
    return leftmost;
}
```

---

### 考点11: 高级递归技巧 (Advanced Recursion)

#### 11.1 打家劫舍 III (LeetCode 337)
**问题：** 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root。除了 root 之外，每栋房子有且只有一个"父"房子与之相连。一番侦察之后，聪明的小偷意识到"这个地方的所有房屋的排列类似于一棵二叉树"。如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。给定二叉树的 root。返回在不触动警报的情况下，小偷能够盗取的最高金额。

```java
public int rob(TreeNode root) {
    int[] result = robHelper(root);
    return Math.max(result[0], result[1]);
}

// 返回 [不抢当前节点的最大值, 抢当前节点的最大值]
private int[] robHelper(TreeNode node) {
    if (node == null) return new int[]{0, 0};
    
    int[] left = robHelper(node.left);
    int[] right = robHelper(node.right);
    
    // 不抢当前节点：左右子节点可抢可不抢
    int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
    
    // 抢当前节点：左右子节点都不能抢
    int rob = node.val + left[0] + right[0];
    
    return new int[]{notRob, rob};
}
```

#### 11.2 二叉树中的最大路径和 (已在考点2.5)

#### 11.3 分发糖果 (LeetCode 979)
**问题：** 给定一个有 n 个节点的二叉树的根节点 root，其中树中每个节点 node 都对应有 node.val 枚硬币。整棵树上一共有 n 枚硬币。在一次移动中，我们可以选择两个相邻的节点，然后将一枚硬币从其中一个节点移动到另一个节点。移动可以是从父节点到子节点，或者从子节点移动到父节点。返回使每个节点都只有一枚硬币所需的最少移动次数。

```java
public int distributeCoins(TreeNode root) {
    int[] moves = {0};
    dfs(root, moves);
    return moves[0];
}

private int dfs(TreeNode node, int[] moves) {
    if (node == null) return 0;
    
    int left = dfs(node.left, moves);
    int right = dfs(node.right, moves);
    
    // 当前节点需要移动的硬币数
    moves[0] += Math.abs(left) + Math.abs(right);
    
    // 返回净余额（正数表示多余，负数表示缺少）
    return node.val + left + right - 1;
}
```

---

### 考点12: 前缀树相关 (Trie Related)

#### 12.1 实现前缀树 (LeetCode 208)
**问题：** Trie（发音类似 "try"）或者说前缀树是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。请你实现 Trie 类：
- `Trie()` 初始化前缀树对象
- `void insert(String word)` 向前缀树中插入字符串 word
- `boolean search(String word)` 如果字符串 word 在前缀树中，返回 true；否则，返回 false
- `boolean startsWith(String prefix)` 如果之前已经插入的字符串 word 的前缀之一为 prefix，返回 true；否则，返回 false

```java
class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd = false;
}

class Trie {
    private TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }
    
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd;
    }
    
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }
    
    private TrieNode searchPrefix(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }
}
```

---

### 考点13: 特殊树结构 (Special Tree Structures)

#### 13.1 填充每个节点的下一个右侧节点指针 (LeetCode 116)
**问题：** 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。初始状态下，所有 next 指针都被设置为 NULL。

```java
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;
}

public Node connect(Node root) {
    if (root == null) return null;
    
    Queue<Node> queue = new LinkedList<>();
    queue.offer(root);
    
    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        Node prev = null;
        
        for (int i = 0; i < levelSize; i++) {
            Node node = queue.poll();
            
            // 连接前一个节点
            if (prev != null) {
                prev.next = node;
            }
            prev = node;
            
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }
    
    return root;
}

// 优化版本（O(1)空间）
public Node connectOptimized(Node root) {
    if (root == null) return null;
    
    Node leftmost = root;
    
    while (leftmost.left != null) {
        Node head = leftmost;
        
        while (head != null) {
            // 连接同一父节点的左右子节点
            head.left.next = head.right;
            
            // 连接不同父节点的子节点
            if (head.next != null) {
                head.right.next = head.next.left;
            }
            
            head = head.next;
        }
        
        leftmost = leftmost.left;
    }
    
    return root;
}
```

---

## 复杂度分析总结

### 时间复杂度
- **遍历操作**：O(n) - 需要访问所有节点
- **查找操作**（BST）：O(log n) 平均，O(n) 最坏
- **构造树**：O(n) - 需要处理所有节点

### 空间复杂度
- **递归**：O(h) - h为树的高度，递归栈空间
- **层序遍历**：O(w) - w为树的最大宽度，队列空间
- **平衡树**：O(log n)
- **退化为链表**：O(n)

---

## 刷题技巧总结

### 1. 递归三步法
1. **确定递归函数的参数和返回值**
2. **确定终止条件**
3. **确定单层递归的逻辑**

### 2. 常见递归模式
- **自顶向下**：传递信息给子节点（如路径和）
- **自底向上**：从子节点收集信息（如树高）
- **分治**：左右子树独立处理后合并

### 3. 遍历选择指南
- **前序**：需要先处理根，再处理子树（如复制树）
- **中序**：BST相关题目（有序性质）
- **后序**：需要先处理子树，再处理根（如删除节点）
- **层序**：按层处理，涉及层级信息

### 4. 调试技巧
- 画出递归树
- 打印每层的输入输出
- 用小规模测试用例验证

---

## 练习建议

### 入门阶段（10题）
- 前/中/后序遍历（递归+迭代）
- 最大深度
- 翻转二叉树
- 对称二叉树
- 路径总和

### 进阶阶段（15题）
- 构造二叉树
- 验证BST
- 最近公共祖先
- 序列化/反序列化
- 二叉树的最大路径和

### 高级阶段（10题）
- 打家劫舍 III
- 完全二叉树节点个数
- 修剪BST
- 分发糖果
- Morris遍历

---

**祝你刷题顺利！💪**
