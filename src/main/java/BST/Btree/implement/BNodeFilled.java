package BST.Btree.implement;

class BNodeFilled<T extends Comparable<T>> extends BNode<T> {
    final T[] keys;              // 关键字数组
    final BNode<T>[] children;   // 子节点数组
    final int n;                 // 当前关键字数量
    final boolean isLeaf;        // 是否为叶节点
    private final int size;      // 子树大小

    @SuppressWarnings("unchecked")
    public BNodeFilled(int t, T[] keys, BNode<T>[] children, int n, boolean isLeaf) {
        super(t);
        this.keys = keys;
        this.children = children;
        this.n = n;
        this.isLeaf = isLeaf;

        // 计算子树总大小
        int totalSize = n;
        if (!isLeaf) {
            for (int i = 0; i <= n; i++) {
                totalSize += children[i].size();
            }
        }
        this.size = totalSize;
    }

    @Override
    public int size() {
        return size;
    }

    // 检查节点是否满
    public boolean isFull() {
        return n == 2 * t - 1;
    }

    @Override
    public BNodeFilled<T> insert(T element) {
        // 如果当前节点满了，需要先分裂
        if (isFull()) {
            // 创建新根，把当前节点作为其子节点
            BNode<T>[] newRootChildren = new BNode[2];
            newRootChildren[0] = this;
            newRootChildren[1] = new BNodeEmpty<>(t);

            T[] newRootKeys = (T[]) new Comparable[0];
            BNodeFilled<T> newRoot = new BNodeFilled<>(t, newRootKeys, newRootChildren, 0, false);

            // 分裂子节点
            BNodeFilled<T> splitResult = newRoot.splitChild(0);
            // 在新根中插入
            return splitResult.insertNonFull(element);
        }

        return insertNonFull(element);
    }

    // 在非满节点中插入
    private BNodeFilled<T> insertNonFull(T element) {
        if (isLeaf) {
            // 叶节点：直接插入
            return insertInLeaf(element);
        } else {
            // 内部节点：找到子节点位置
            int i = findChildIndex(element);

            // 如果子节点满了，先分裂
            if (children[i] instanceof BNodeFilled) {
                BNodeFilled<T> child = (BNodeFilled<T>) children[i];
                if (child.isFull()) {
                    // 分裂子节点
                    BNodeFilled<T> afterSplit = splitChild(i);
                    // 重新确定插入位置
                    if (element.compareTo(afterSplit.keys[i]) > 0) {
                        i++;
                    }
                    // 递归插入
                    BNode<T> newChild = afterSplit.children[i].insert(element);
                    return afterSplit.replaceChild(i, newChild);
                }
            }

            // 子节点未满，直接递归插入
            BNode<T> newChild = children[i].insert(element);
            return replaceChild(i, newChild);
        }
    }

    // 在叶节点插入关键字
    private BNodeFilled<T> insertInLeaf(T element) {
        // 找到插入位置
        int pos = 0;
        while (pos < n && element.compareTo(keys[pos]) > 0) {
            pos++;
        }

        // 如果元素已存在，不插入
        if (pos < n && element.compareTo(keys[pos]) == 0) {
            return this;
        }

        // 创建新的关键字数组
        T[] newKeys = (T[]) new Comparable[n + 1];
        for (int i = 0; i < pos; i++) {
            newKeys[i] = keys[i];
        }
        newKeys[pos] = element;
        for (int i = pos; i < n; i++) {
            newKeys[i + 1] = keys[i];
        }

        // 创建新的子节点数组（叶节点的子节点都是空）
        BNode<T>[] newChildren = new BNode[n + 2];
        for (int i = 0; i <= n + 1; i++) {
            newChildren[i] = new BNodeEmpty<>(t);
        }

        return new BNodeFilled<>(t, newKeys, newChildren, n + 1, true);
    }

    // 分裂子节点
    private BNodeFilled<T> splitChild(int i) {
        BNodeFilled<T> fullChild = (BNodeFilled<T>) children[i];

        // 中间位置
        int mid = t - 1;

        // 创建新的右子节点，包含后半部分关键字
        T[] rightKeys = (T[]) new Comparable[t - 1];
        for (int j = 0; j < t - 1; j++) {
            rightKeys[j] = fullChild.keys[mid + 1 + j];
        }

        BNode<T>[] rightChildren = new BNode[t];
        if (fullChild.isLeaf) {
            for (int j = 0; j < t; j++) {
                rightChildren[j] = new BNodeEmpty<>(t);
            }
        } else {
            for (int j = 0; j < t; j++) {
                rightChildren[j] = fullChild.children[mid + 1 + j];
            }
        }

        BNodeFilled<T> rightNode = new BNodeFilled<>(t, rightKeys, rightChildren, t - 1, fullChild.isLeaf);

        // 创建新的左子节点，包含前半部分关键字
        T[] leftKeys = (T[]) new Comparable[t - 1];
        for (int j = 0; j < t - 1; j++) {
            leftKeys[j] = fullChild.keys[j];
        }

        BNode<T>[] leftChildren = new BNode[t];
        if (fullChild.isLeaf) {
            for (int j = 0; j < t; j++) {
                leftChildren[j] = new BNodeEmpty<>(t);
            }
        } else {
            for (int j = 0; j < t; j++) {
                leftChildren[j] = fullChild.children[j];
            }
        }

        BNodeFilled<T> leftNode = new BNodeFilled<>(t, leftKeys, leftChildren, t - 1, fullChild.isLeaf);

        // 中间关键字上移到父节点
        T midKey = fullChild.keys[mid];

        // 创建新的父节点
        T[] newKeys = (T[]) new Comparable[n + 1];
        for (int j = 0; j < i; j++) {
            newKeys[j] = keys[j];
        }
        newKeys[i] = midKey;
        for (int j = i; j < n; j++) {
            newKeys[j + 1] = keys[j];
        }

        BNode<T>[] newChildren = new BNode[n + 2];
        for (int j = 0; j < i; j++) {
            newChildren[j] = children[j];
        }
        newChildren[i] = leftNode;
        newChildren[i + 1] = rightNode;
        for (int j = i + 1; j <= n; j++) {
            newChildren[j + 1] = children[j];
        }

        return new BNodeFilled<>(t, newKeys, newChildren, n + 1, false);
    }

    // 替换子节点
    private BNodeFilled<T> replaceChild(int i, BNode<T> newChild) {
        BNode<T>[] newChildren = new BNode[n + 1];
        for (int j = 0; j <= n; j++) {
            newChildren[j] = (j == i) ? newChild : children[j];
        }
        return new BNodeFilled<>(t, keys, newChildren, n, isLeaf);
    }

    // 找到元素应该在的子节点索引
    private int findChildIndex(T element) {
        int i = 0;
        while (i < n && element.compareTo(keys[i]) > 0) {
            i++;
        }
        return i;
    }

    @Override
    public T get(T element) {
        int i = 0;
        while (i < n && element.compareTo(keys[i]) > 0) {
            i++;
        }

        // 找到了
        if (i < n && element.compareTo(keys[i]) == 0) {
            return keys[i];
        }

        // 叶节点未找到
        if (isLeaf) {
            return null;
        }

        // 递归搜索子节点
        return children[i].get(element);
    }

    @Override
    public boolean contains(T element) {
        return get(element) != null;
    }

    @Override
    public T getAtIndex(int i) {
        if (isLeaf) {
            return (i >= 0 && i < n) ? keys[i] : null;
        }

        // 计算累计大小
        int accumulated = 0;
        for (int j = 0; j < n; j++) {
            int leftSize = children[j].size();
            if (i < accumulated + leftSize) {
                return children[j].getAtIndex(i - accumulated);
            }
            accumulated += leftSize;

            if (i == accumulated) {
                return keys[j];
            }
            accumulated++;
        }

        // 最后一个子树
        return children[n].getAtIndex(i - accumulated);
    }

    @Override
    public String toString() {
        if (isLeaf) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < n; i++) {
                sb.append(keys[i]);
                if (i < n - 1) sb.append(",");
            }
            sb.append("]");
            return sb.toString();
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < n; i++) {
                sb.append(keys[i]);
                if (i < n - 1) sb.append(",");
            }
            sb.append("] -> (");
            for (int i = 0; i <= n; i++) {
                sb.append(children[i].toString());
                if (i < n) sb.append(", ");
            }
            sb.append(")");
            return sb.toString();
        }
    }
}

