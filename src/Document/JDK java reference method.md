# Java 基础工具类完整参考手册

## 目录
1. [基础运算符](#1-基础运算符)
2. [String 字符串工具](#2-string-字符串工具)
3. [数组基础操作 []](#3-数组基础操作 [])
4. [集合框架 Collections](#4-集合框架 Collections)
5. [泛型，枚举，异常](#5-泛型，枚举，异常)
6. [Lambda 表达式与函数式编程](#6-Lambda 表达式与函数式编程)
7. [日期时间工具](#7-日期时间工具)
8. [数学工具 Math](#8-数学工具 Math)
9. [文件 I/O 工具](#9-文件 I/O 工具)
10. [其他常用工具类](#10-其他常用工具类)
11. [其他方法](#11-其他方法)

---

## 1. 基础运算符

### 1.1 三元运算符

```java
condition ? value1 : value2

// 示例
int a = 5, b = 3;
int max = (a > b) ? a : b;  // max = 5

String result = (score >= 60) ? "Pass" : "Fail";
```

### 1.2 instanceof 运算符

```java
object instanceof Class

// 示例
String str = "Hello";
boolean isString = str instanceof String;  // true
boolean isObject = str instanceof Object;  // true
```

---

## 2. String 字符串工具

### 2.1 String 类常用方法

```java
// 字符串长度
int length()

// 字符获取
char charAt(int index)
char[] toCharArray()

// 子串
String substring(int beginIndex)
String substring(int beginIndex, int endIndex)

// 查找
int indexOf(String str)
int indexOf(String str, int fromIndex)
int lastIndexOf(String str)
boolean contains(CharSequence s)
boolean startsWith(String prefix)
boolean endsWith(String suffix)

// 比较
boolean equals(Object obj)
boolean equalsIgnoreCase(String anotherString)
int compareTo(String anotherString)
int compareToIgnoreCase(String str)

// 替换
String replace(char oldChar, char newChar)
String replace(CharSequence target, CharSequence replacement)
String replaceAll(String regex, String replacement)
String replaceFirst(String regex, String replacement)

// 分割
String[] split(String regex)
String[] split(String regex, int limit)

// 大小写转换
String toLowerCase()
String toUpperCase()

// 去空格
String trim()
String strip()  // Java 11+

// 判断
boolean isEmpty()
boolean isBlank()  // Java 11+

// 重复（Java 11+）
String repeat(int count)
```

### 2.2 StringBuilder 类

```java
// 构造
StringBuilder()
StringBuilder(int capacity)
StringBuilder(String str)

// 追加
StringBuilder append(String str)
StringBuilder append(char c)
StringBuilder append(int i)
StringBuilder append(Object obj)

// 插入
StringBuilder insert(int offset, String str)
StringBuilder insert(int offset, char c)

// 删除
StringBuilder delete(int start, int end)
StringBuilder deleteCharAt(int index)

// 替换
StringBuilder replace(int start, int end, String str)

// 反转
StringBuilder reverse()

// 容量
int capacity()
void ensureCapacity(int minimumCapacity)
void setLength(int newLength)

// 转换
String toString()
```

### 2.3 StringBuffer 类
> 线程安全版本的 StringBuilder，方法基本相同

------

## 3. 数组基础操作 []

#### 3.1 数组声明与初始化

```java
// 声明数组
int[] arr1;              // 推荐写法
int arr2[];              // 也可以，但不推荐

// 创建数组
int[] arr3 = new int[5];                    // 创建长度为5的数组，默认值为0
int[] arr4 = new int[]{1, 2, 3, 4, 5};     // 创建并初始化
int[] arr5 = {1, 2, 3, 4, 5};              // 简化写法

// 二维数组
int[][] matrix1 = new int[3][4];           // 3行4列
int[][] matrix2 = {{1, 2}, {3, 4}, {5, 6}};

// 不规则数组
int[][] irregular = new int[3][];
irregular[0] = new int[2];
irregular[1] = new int[3];
irregular[2] = new int[4];
```

#### 3.2 数组基本操作

```java
// 访问元素
int[] arr = {1, 2, 3, 4, 5};
int first = arr[0];      // 1
int last = arr[arr.length - 1];  // 5

// 修改元素
arr[0] = 10;

// 数组长度
int length = arr.length;

// 遍历数组
// 方式1：for 循环
for (int i = 0; i < arr.length; i++) {
    System.out.println(arr[i]);
}

// 方式2：增强for循环（for-each）
for (int num : arr) {
    System.out.println(num);
}

// 方式3：Java 8 Stream
Arrays.stream(arr).forEach(System.out::println);
```

#### 3.3 数组复制

```java
int[] original = {1, 2, 3, 4, 5};

// 方式1：Arrays.copyOf
int[] copy1 = Arrays.copyOf(original, original.length);

// 方式2：Arrays.copyOfRange
int[] copy2 = Arrays.copyOfRange(original, 1, 4);  // {2, 3, 4}

// 方式3：System.arraycopy
int[] copy3 = new int[5];
System.arraycopy(original, 0, copy3, 0, original.length);

// 方式4：clone
int[] copy4 = original.clone();
```

#### 3.4 数组排序

```java
int[] arr = {5, 2, 8, 1, 9};

// 升序排序
Arrays.sort(arr);  // {1, 2, 5, 8, 9}

// 部分排序
Arrays.sort(arr, 0, 3);  // 只排序前3个元素

// 对象数组排序（使用Comparator）
String[] strs = {"banana", "apple", "cherry"};
Arrays.sort(strs);  // 自然排序
Arrays.sort(strs, Comparator.reverseOrder());  // 降序

// 自定义排序
Integer[] nums = {5, 2, 8, 1, 9};
Arrays.sort(nums, (a, b) -> b - a);  // 降序
```

#### 3.5 数组查找

```java
int[] arr = {1, 2, 3, 4, 5};

// 线性查找
public static int linearSearch(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == target) {
            return i;
        }
    }
    return -1;
}

// 二分查找（数组必须已排序）
int index = Arrays.binarySearch(arr, 3);  // 返回索引2
```

#### 3.6 数组填充

```java
int[] arr = new int[5];

// 填充整个数组
Arrays.fill(arr, 10);  // {10, 10, 10, 10, 10}

// 填充部分数组
Arrays.fill(arr, 1, 4, 20);  // {10, 20, 20, 20, 10}
```

#### 3.7 数组比较

```java
int[] arr1 = {1, 2, 3};
int[] arr2 = {1, 2, 3};
int[] arr3 = {1, 2, 4};

// 比较数组内容
boolean equal1 = Arrays.equals(arr1, arr2);  // true
boolean equal2 = Arrays.equals(arr1, arr3);  // false

// 深度比较（用于多维数组）
int[][] matrix1 = {{1, 2}, {3, 4}};
int[][] matrix2 = {{1, 2}, {3, 4}};
boolean deepEqual = Arrays.deepEquals(matrix1, matrix2);  // true
```

#### 3.8 数组转换

```java
int[] arr = {1, 2, 3, 4, 5};

// 数组转字符串
String str = Arrays.toString(arr);  // "[1, 2, 3, 4, 5]"

// 多维数组转字符串
int[][] matrix = {{1, 2}, {3, 4}};
String matrixStr = Arrays.deepToString(matrix);  // "[[1, 2], [3, 4]]"

// 数组转List
Integer[] intArray = {1, 2, 3, 4, 5};
List<Integer> list = Arrays.asList(intArray);

// 基本类型数组转List（Java 8+）
int[] primitiveArray = {1, 2, 3, 4, 5};
List<Integer> list2 = Arrays.stream(primitiveArray)
                            .boxed()
                            .collect(Collectors.toList());
```

#### 3.9 数组常用算法示例

```java
// 求和
int[] arr = {1, 2, 3, 4, 5};
int sum = Arrays.stream(arr).sum();  // 15

// 求平均值
double avg = Arrays.stream(arr).average().orElse(0.0);  // 3.0

// 求最大值
int max = Arrays.stream(arr).max().orElse(Integer.MIN_VALUE);  // 5

// 求最小值
int min = Arrays.stream(arr).min().orElse(Integer.MAX_VALUE);  // 1

// 反转数组
public static void reverse(int[] arr) {
    int left = 0, right = arr.length - 1;
    while (left < right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
        left++;
        right--;
    }
}

// 数组去重
int[] arr2 = {1, 2, 2, 3, 3, 3, 4, 5};
int[] unique = Arrays.stream(arr2).distinct().toArray();
```

---

## 4. 集合框架 Collections

### List 接口（ArrayList, LinkedList）

```java
// 添加
boolean add(E e)
void add(int index, E element)
boolean addAll(Collection<? extends E> c)

// 删除
boolean remove(Object o)
E remove(int index)
boolean removeAll(Collection<?> c)
void clear()

// 获取
E get(int index)
int indexOf(Object o)
int lastIndexOf(Object o)

// 修改
E set(int index, E element)

// 判断
boolean contains(Object o)
boolean containsAll(Collection<?> c)
boolean isEmpty()

// 大小
int size()

// 子列表
List<E> subList(int fromIndex, int toIndex)

// 转换
Object[] toArray()
<T> T[] toArray(T[] a)

// 迭代
Iterator<E> iterator()
ListIterator<E> listIterator()

// 排序（Java 8+）
void sort(Comparator<? super E> c)
```

### ArrayList 详解

#### 创建 ArrayList

```java
// 1. 默认构造（初始容量为10）
ArrayList<String> list1 = new ArrayList<>();

// 2. 指定初始容量
ArrayList<String> list2 = new ArrayList<>(20);

// 3. 从其他集合创建
ArrayList<String> list3 = new ArrayList<>(Arrays.asList("A", "B", "C"));

// 4. 使用工厂方法（Java 9+，创建不可变列表）
List<String> list4 = List.of("A", "B", "C");
```

#### 添加元素

```java
ArrayList<String> list = new ArrayList<>();

// 添加到末尾
list.add("Apple");           // ["Apple"]
list.add("Banana");          // ["Apple", "Banana"]

// 添加到指定位置
list.add(1, "Orange");       // ["Apple", "Orange", "Banana"]

// 批量添加
list.addAll(Arrays.asList("Grape", "Mango"));
// ["Apple", "Orange", "Banana", "Grape", "Mango"]

// 在指定位置批量添加
list.addAll(2, Arrays.asList("Kiwi", "Peach"));
```

#### 访问元素

```java
ArrayList<String> list = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));

// 获取元素
String first = list.get(0);        // "A"
String last = list.get(list.size() - 1);  // "D"

// 查找索引
int index1 = list.indexOf("B");    // 1
int index2 = list.lastIndexOf("B"); // 1（最后一次出现的位置）

// 检查是否包含
boolean contains = list.contains("C");  // true
```

#### 修改元素

```java
ArrayList<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));

// 修改指定位置的元素
String old = list.set(1, "X");  // 返回旧值"B"，list变为["A", "X", "C"]
```

#### 删除元素

```java
ArrayList<String> list = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "B"));

// 按索引删除
String removed1 = list.remove(0);  // 删除"A"，返回被删除的元素

// 按对象删除（删除第一个匹配的元素）
boolean removed2 = list.remove("B");  // 删除第一个"B"，返回true

// 批量删除
list.removeAll(Arrays.asList("C", "D"));  // 删除所有的"C"和"D"

// 保留指定元素
list.retainAll(Arrays.asList("A", "B"));  // 只保留"A"和"B"

// 清空列表
list.clear();

// 按条件删除（Java 8+）
list.removeIf(s -> s.startsWith("A"));  // 删除所有以"A"开头的元素
```

#### 遍历 ArrayList

```java
ArrayList<String> list = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));

// 方式1：for循环
for (int i = 0; i < list.size(); i++) {
    System.out.println(list.get(i));
}

// 方式2：增强for循环
for (String item : list) {
    System.out.println(item);
}

// 方式3：Iterator
Iterator<String> iterator = list.iterator();
while (iterator.hasNext()) {
    String item = iterator.next();
    System.out.println(item);
    // 可以在遍历时安全删除
    // iterator.remove();
}

// 方式4：ListIterator（可以双向遍历）
ListIterator<String> listIterator = list.listIterator();
while (listIterator.hasNext()) {
    System.out.println(listIterator.next());
}

// 方式5：forEach方法（Java 8+）
list.forEach(item -> System.out.println(item));
list.forEach(System.out::println);  // 方法引用

// 方式6：Stream（Java 8+）
list.stream().forEach(System.out::println);
```

#### 排序与搜索

```java
ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9));

// 排序
Collections.sort(numbers);  // 升序：[1, 2, 5, 8, 9]
Collections.sort(numbers, Collections.reverseOrder());  // 降序

// 或使用List的sort方法（Java 8+）
numbers.sort(Comparator.naturalOrder());  // 升序
numbers.sort(Comparator.reverseOrder());  // 降序
numbers.sort((a, b) -> a - b);  // 自定义比较器

// 二分查找（列表必须已排序）
Collections.sort(numbers);
int index = Collections.binarySearch(numbers, 5);  // 返回索引

// 查找最大值和最小值
Integer max = Collections.max(numbers);
Integer min = Collections.min(numbers);
```

#### 其他常用方法

```java
ArrayList<String> list = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));

// 获取大小
int size = list.size();

// 判断是否为空
boolean empty = list.isEmpty();

// 子列表（返回视图，修改会影响原列表）
List<String> subList = list.subList(1, 3);  // ["B", "C"]

// 转换为数组
String[] array1 = list.toArray(new String[0]);
Object[] array2 = list.toArray();

// 克隆
ArrayList<String> clone = (ArrayList<String>) list.clone();

// 确保容量
list.ensureCapacity(100);

// 裁剪容量到当前大小
list.trimToSize();
```

### LinkedList 详解

#### 创建 LinkedList

```java
// 1. 默认构造
LinkedList<String> list1 = new LinkedList<>();

// 2. 从其他集合创建
LinkedList<String> list2 = new LinkedList<>(Arrays.asList("A", "B", "C"));
```

#### List 接口方法

```java
LinkedList<String> list = new LinkedList<>();

// 添加元素（同ArrayList）
list.add("Apple");
list.add(0, "Banana");
list.addAll(Arrays.asList("Orange", "Grape"));

// 访问元素
String first = list.get(0);
int index = list.indexOf("Apple");

// 修改元素
list.set(0, "Kiwi");

// 删除元素
list.remove(0);
list.remove("Apple");
list.clear();
```

#### Deque 接口方法（双端队列特有）

```java
LinkedList<String> deque = new LinkedList<>();

// 在头部操作
deque.addFirst("A");        // 在头部添加
deque.offerFirst("B");      // 在头部添加（推荐，不抛异常）
String first = deque.getFirst();     // 获取头部元素（会抛异常）
String first2 = deque.peekFirst();   // 获取头部元素（不抛异常）
String removed = deque.removeFirst(); // 删除头部元素（会抛异常）
String removed2 = deque.pollFirst();  // 删除头部元素（不抛异常）

// 在尾部操作
deque.addLast("C");         // 在尾部添加
deque.offerLast("D");       // 在尾部添加（推荐，不抛异常）
String last = deque.getLast();       // 获取尾部元素（会抛异常）
String last2 = deque.peekLast();     // 获取尾部元素（不抛异常）
String removed3 = deque.removeLast(); // 删除尾部元素（会抛异常）
String removed4 = deque.pollLast();   // 删除尾部元素（不抛异常）

// 方法对比
/**
 * 抛异常的方法：add, get, remove
 * 不抛异常的方法：offer, peek, poll（失败返回null或false）
 */
```

#### Queue 接口方法（队列操作）

```java
LinkedList<String> queue = new LinkedList<>();

// 入队（添加到尾部）
queue.offer("A");
queue.offer("B");
queue.offer("C");

// 查看队首元素（不删除）
String head = queue.peek();  // "A"

// 出队（删除并返回队首元素）
String removed = queue.poll();  // "A"

// 队列大小
int size = queue.size();

// 判断是否为空
boolean empty = queue.isEmpty();
```

#### Stack 操作（栈）

```java
LinkedList<String> stack = new LinkedList<>();

// 入栈（添加到头部）
stack.push("A");
stack.push("B");
stack.push("C");  // 栈：C -> B -> A

// 查看栈顶元素（不删除）
String top = stack.peek();  // "C"

// 出栈（删除并返回栈顶元素）
String popped = stack.pop();  // "C"
```

#### 遍历 LinkedList

```java
LinkedList<String> list = new LinkedList<>(Arrays.asList("A", "B", "C", "D"));

// 方式1：增强for循环
for (String item : list) {
    System.out.println(item);
}

// 方式2：Iterator（可正向遍历）
Iterator<String> iterator = list.iterator();
while (iterator.hasNext()) {
    System.out.println(iterator.next());
}

// 方式3：descendingIterator（反向遍历）
Iterator<String> descIterator = list.descendingIterator();
while (descIterator.hasNext()) {
    System.out.println(descIterator.next());
}

// 方式4：forEach（Java 8+）
list.forEach(System.out::println);

// 方式5：Stream
list.stream().forEach(System.out::println);

// 注意：避免使用for循环和get(i)遍历LinkedList，因为每次get都是O(n)
```

#### LinkedList 特有方法总结

```java
// 头部操作（推荐使用不抛异常的版本）
addFirst(E)     / offerFirst(E)     // 添加到头部
getFirst()      / peekFirst()       // 获取头部元素
removeFirst()   / pollFirst()       // 删除头部元素

// 尾部操作
addLast(E)      / offerLast(E)      // 添加到尾部
getLast()       / peekLast()        // 获取尾部元素
removeLast()    / pollLast()        // 删除尾部元素

// 栈操作
push(E)         // 入栈（等同于addFirst）
pop()           // 出栈（等同于removeFirst）
peek()          // 查看栈顶（等同于peekFirst）

// 队列操作
offer(E)        // 入队（等同于offerLast）
poll()          // 出队（等同于pollFirst）
peek()          // 查看队首（等同于peekFirst）
```

#### LinkedList vs ArrayList

```java
/**
 * LinkedList 优点：
 * - 头尾插入/删除快：O(1)
 * - 不需要扩容
 * - 实现了Deque接口，可作为双端队列、栈使用
 * 
 * LinkedList 缺点：
 * - 随机访问慢：O(n)
 * - 每个节点需要额外存储两个指针，内存开销大
 * - 缓存不友好
 * 
 * 使用场景：
 * - 频繁在头尾插入/删除
 * - 需要队列或栈的功能
 * - 很少随机访问
 */

// 示例：选择合适的数据结构
// 场景1：需要快速随机访问 → 使用ArrayList
List<String> randomAccess = new ArrayList<>();

// 场景2：频繁在头尾操作 → 使用LinkedList
Deque<String> deque = new LinkedList<>();

// 场景3：需要线程安全 → 使用CopyOnWriteArrayList或Collections.synchronizedList
List<String> threadSafe = new CopyOnWriteArrayList<>();
List<String> syncList = Collections.synchronizedList(new ArrayList<>());
```

---

### Set 接口（HashSet, TreeSet, LinkedHashSet）

```java
// 添加
boolean add(E e)
boolean addAll(Collection<? extends E> c)

// 删除
boolean remove(Object o)
boolean removeAll(Collection<?> c)
void clear()

// 判断
boolean contains(Object o)
boolean containsAll(Collection<?> c)
boolean isEmpty()

// 大小
int size()

// 转换
Object[] toArray()
<T> T[] toArray(T[] a)

// 迭代
Iterator<E> iterator()
```

### Map 接口（HashMap, TreeMap, LinkedHashMap）

```java
// 添加/修改
V put(K key, V value)
void putAll(Map<? extends K, ? extends V> m)
V putIfAbsent(K key, V value)  // Java 8+

// 获取
V get(Object key)
V getOrDefault(Object key, V defaultValue)  // Java 8+

// 删除
V remove(Object key)
boolean remove(Object key, Object value)
void clear()

// 判断
boolean containsKey(Object key)
boolean containsValue(Object value)
boolean isEmpty()

// 大小
int size()

// 集合视图
Set<K> keySet()
Collection<V> values()
Set<Map.Entry<K, V>> entrySet()

// 遍历（Java 8+）
void forEach(BiConsumer<? super K, ? super V> action)

// 替换（Java 8+）
V replace(K key, V value)
boolean replace(K key, V oldValue, V newValue)
void replaceAll(BiFunction<? super K, ? super V, ? extends V> function)

// 计算（Java 8+）
V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)
V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction)
V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)

// 合并（Java 8+）
V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction)
```

### Collections 工具类

```java
// 排序
static <T extends Comparable<? super T>> void sort(List<T> list)
static <T> void sort(List<T> list, Comparator<? super T> c)

// 查找
static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key)
static <T> int binarySearch(List<? extends T> list, T key, Comparator<? super T> c)
static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll)
static <T extends Object & Comparable<? super T>> T min(Collection<? extends T> coll)

// 反转
static void reverse(List<?> list)

// 打乱
static void shuffle(List<?> list)

// 旋转
static void rotate(List<?> list, int distance)

// 替换
static <T> boolean replaceAll(List<T> list, T oldVal, T newVal)

// 填充
static <T> void fill(List<? super T> list, T obj)

// 复制
static <T> void copy(List<? super T> dest, List<? extends T> src)


```

---

### Arrays 数组工具类

```java
// 排序
static void sort(int[] a)
static void sort(int[] a, int fromIndex, int toIndex)
static <T> void sort(T[] a, Comparator<? super T> c)

// 查找（二分查找，数组必须已排序）
static int binarySearch(int[] a, int key)
static int binarySearch(int[] a, int fromIndex, int toIndex, int key)
static <T> int binarySearch(T[] a, T key, Comparator<? super T> c)

// 比较
static boolean equals(int[] a, int[] a2)
static boolean deepEquals(Object[] a1, Object[] a2)

// 填充
static void fill(int[] a, int val)
static void fill(int[] a, int fromIndex, int toIndex, int val)

// 复制
static int[] copyOf(int[] original, int newLength)
static int[] copyOfRange(int[] original, int from, int to)

// 转字符串
static String toString(int[] a)
static String deepToString(Object[] a)

// 转 List
static <T> List<T> asList(T... a)

// 流操作（Java 8+）
static IntStream stream(int[] array)
static <T> Stream<T> stream(T[] array)

// 并行排序（Java 8+）
static void parallelSort(int[] a)

// 设置所有元素（Java 8+）
static void setAll(int[] array, IntUnaryOperator generator)
static <T> void setAll(T[] array, IntFunction<? extends T> generator)
```

---

## 5. 泛型，枚举，异常

### 5.1 泛型的类型擦除

```java
/**
 * Java泛型使用类型擦除实现：
 * 1. 编译时检查类型安全
 * 2. 运行时擦除类型信息
 * 3. 泛型类型被替换为Object或边界类型
 */

// 编译前
public class Box<T> {
    private T content;
    
    public void set(T content) {
        this.content = content;
    }
    
    public T get() {
        return content;
    }
}

// 编译后（类型擦除）
public class Box {
    private Object content;
    
    public void set(Object content) {
        this.content = content;
    }
    
    public Object get() {
        return content;
    }
}

// 有边界的类型擦除
public class NumberBox<T extends Number> {
    private T number;
}

// 编译后
public class NumberBox {
    private Number number;  // 擦除为边界类型
}
```

#### 泛型的限制

```java
/**
 * 1. 不能用基本数据类型作为类型参数
 */
// Box<int> intBox = new Box<>();  // 错误！
Box<Integer> integerBox = new Box<>();  // 正确

/**
 * 2. 不能创建泛型数组
 */
// List<String>[] array = new List<String>[10];  // 错误！
List<?>[] array = new List<?>[10];  // 可以用通配符

/**
 * 3. 不能实例化类型参数
 */
public class Box<T> {
    // private T instance = new T();  // 错误！
    
    // 解决方案：通过反射或工厂方法
    public T createInstance(Class<T> clazz) throws Exception {
        return clazz.newInstance();
    }
}

/**
 * 4. 不能使用instanceof判断泛型类型
 */
// if (obj instanceof Box<String>) { }  // 错误！
if (obj instanceof Box<?>) { }  // 正确

/**
 * 5. 不能创建泛型异常类
 */
// public class GenericException<T> extends Exception { }  // 错误！

/**
 * 6. 不能在静态上下文中使用类型参数
 */
public class Box<T> {
    // private static T staticField;  // 错误！
    // public static T staticMethod() { }  // 错误！
    
    public static <E> void genericStaticMethod(E param) { }  // 正确
}
```

### 5.2 枚举

```java
// 定义枚举
public enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

// 使用枚举
Day today = Day.MONDAY;
System.out.println(today);  // MONDAY

// 比较枚举
if (today == Day.MONDAY) {
    System.out.println("It's Monday!");
}

// switch语句中使用枚举
switch (today) {
    case MONDAY:
        System.out.println("Start of work week");
        break;
    case FRIDAY:
        System.out.println("End of work week");
        break;
    case SATURDAY:
    case SUNDAY:
        System.out.println("Weekend!");
        break;
    default:
        System.out.println("Midweek");
}
```

### 5.3 try-catch-finally 基本用法

```java
// 基本语法
try {
    // 可能抛出异常的代码
    int result = 10 / 0;
} catch (ArithmeticException e) {
    // 处理异常
    System.out.println("Division by zero: " + e.getMessage());
} finally {
    // 无论是否发生异常都会执行
    System.out.println("Finally block executed");
}

// 多个catch块
try {
    String str = null;
    System.out.println(str.length());
    int[] arr = new int[5];
    System.out.println(arr[10]);
} catch (NullPointerException e) {
    System.out.println("Null pointer exception: " + e.getMessage());
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Array index out of bounds: " + e.getMessage());
} catch (Exception e) {
    // 捕获所有其他异常（应放在最后）
    System.out.println("Other exception: " + e.getMessage());
}

// 多重捕获（Java 7+）
try {
    // 可能抛出多种异常的代码
} catch (IOException | SQLException e) {
    // 处理多种异常
    System.out.println("Exception: " + e.getMessage());
}
```



------

## 6. Lambda 表达式与函数式编程

#### Lambda 表达式基础

```java
// 传统方式：匿名类
Runnable r1 = new Runnable() {
    @Override
    public void run() {
        System.out.println("Hello World");
    }
};

// Lambda表达式
Runnable r2 = () -> System.out.println("Hello World");

// Lambda表达式语法
// 1. 无参数，无返回值
() -> System.out.println("Hello");

// 2. 一个参数，可以省略括号
x -> x * x

// 3. 多个参数
(x, y) -> x + y

// 4. 带类型声明
(int x, int y) -> x + y

// 5. 带代码块
(x, y) -> {
    int sum = x + y;
    return sum;
}

// 6. 返回值（单行表达式会自动返回）
x -> x * 2  // 等同于 x -> { return x * 2; }
```

#### 函数式接口

```java
// 自定义函数式接口
@FunctionalInterface
public interface Calculator {
    int calculate(int a, int b);
}

// 使用Lambda实现
Calculator add = (a, b) -> a + b;
Calculator multiply = (a, b) -> a * b;

System.out.println(add.calculate(5, 3));       // 8
System.out.println(multiply.calculate(5, 3));  // 15

// 内置函数式接口（java.util.function包）

// 1. Predicate<T>: T -> boolean（断言）
Predicate<Integer> isEven = n -> n % 2 == 0;
System.out.println(isEven.test(4));  // true
System.out.println(isEven.test(5));  // false

// Predicate组合
Predicate<Integer> isPositive = n -> n > 0;
Predicate<Integer> isPositiveEven = isEven.and(isPositive);
System.out.println(isPositiveEven.test(4));   // true
System.out.println(isPositiveEven.test(-4));  // false

// 2. Function<T, R>: T -> R（函数）
Function<String, Integer> stringLength = s -> s.length();
System.out.println(stringLength.apply("Hello"));  // 5

// Function组合
Function<String, String> toUpper = s -> s.toUpperCase();
Function<String, Integer> upperLength = toUpper.andThen(stringLength);
System.out.println(upperLength.apply("hello"));  // 5

// 3. Consumer<T>: T -> void（消费者）
Consumer<String> printer = s -> System.out.println(s);
printer.accept("Hello World");

// Consumer链式调用
Consumer<String> upperPrinter = s -> System.out.println(s.toUpperCase());
Consumer<String> bothPrinter = printer.andThen(upperPrinter);
bothPrinter.accept("hello");  // 输出：hello 和 HELLO

// 4. Supplier<T>: () -> T（供应者）
Supplier<Double> randomValue = () -> Math.random();
System.out.println(randomValue.get());

Supplier<String> greeting = () -> "Hello World";
System.out.println(greeting.get());

// 5. BiFunction<T, U, R>: (T, U) -> R
BiFunction<Integer, Integer, Integer> adder = (a, b) -> a + b;
System.out.println(adder.apply(5, 3));  // 8

// 6. BiConsumer<T, U>: (T, U) -> void
BiConsumer<String, Integer> printPair = (key, value) -> 
    System.out.println(key + ": " + value);
printPair.accept("Age", 25);

// 7. BiPredicate<T, U>: (T, U) -> boolean
BiPredicate<String, Integer> lengthEquals = (str, len) -> 
    str.length() == len;
System.out.println(lengthEquals.test("hello", 5));  // true

// 8. UnaryOperator<T>: T -> T（继承自Function<T, T>）
UnaryOperator<Integer> square = x -> x * x;
System.out.println(square.apply(5));  // 25

// 9. BinaryOperator<T>: (T, T) -> T（继承自BiFunction<T, T, T>）
BinaryOperator<Integer> max = (a, b) -> a > b ? a : b;
System.out.println(max.apply(5, 3));  // 5
```

#### 方法引用

```java
// 1. 静态方法引用：ClassName::staticMethod
// Lambda: x -> Math.sqrt(x)
Function<Double, Double> sqrt = Math::sqrt;
System.out.println(sqrt.apply(16.0));  // 4.0

// 2. 实例方法引用：instance::instanceMethod
String str = "Hello";
// Lambda: () -> str.length()
Supplier<Integer> lengthSupplier = str::length;
System.out.println(lengthSupplier.get());  // 5

// 3. 类的实例方法引用：ClassName::instanceMethod
// Lambda: s -> s.length()
Function<String, Integer> length = String::length;
System.out.println(length.apply("Hello"));  // 5

// 4. 构造器引用：ClassName::new
// Lambda: () -> new ArrayList<>()
Supplier<List<String>> listSupplier = ArrayList::new;
List<String> list = listSupplier.get();

// 带参数的构造器引用
Function<Integer, List<String>> listFactory = ArrayList::new;
List<String> list2 = listFactory.apply(10);  // 初始容量为10

// 示例：使用方法引用
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

// Lambda: name -> System.out.println(name)
names.forEach(System.out::println);

// Lambda: name -> name.length()
List<Integer> lengths = names.stream()
                             .map(String::length)
                             .collect(Collectors.toList());

// Lambda: name -> name.toUpperCase()
List<String> upperNames = names.stream()
                               .map(String::toUpperCase)
                               .collect(Collectors.toList());
```



------

## 7. 日期时间工具

### Date 类（旧 API）

```java
// 构造
Date()
Date(long date)

// 获取时间戳
long getTime()

// 设置时间戳
void setTime(long time)

// 比较
boolean before(Date when)
boolean after(Date when)
int compareTo(Date anotherDate)

// 转字符串
String toString()
```

### Calendar 类（旧 API）

```java
// 获取实例
static Calendar getInstance()

// 获取字段值
int get(int field)
// field 常量：YEAR, MONTH, DAY_OF_MONTH, HOUR_OF_DAY, MINUTE, SECOND

// 设置字段值
void set(int field, int value)
void set(int year, int month, int date)
void set(int year, int month, int date, int hourOfDay, int minute, int second)

// 日期计算
void add(int field, int amount)
void roll(int field, int amount)

// 转换
Date getTime()
long getTimeInMillis()
void setTime(Date date)
void setTimeInMillis(long millis)

// 清除
void clear()
void clear(int field)
```

---

## 8. 数学工具 Math

```java
// 绝对值
static int abs(int a)
static long abs(long a)
static float abs(float a)
static double abs(double a)

// 最大值/最小值
static int max(int a, int b)
static int min(int a, int b)
static double max(double a, double b)
static double min(double a, double b)

// 幂运算
static double pow(double a, double b)
static double sqrt(double a)
static double cbrt(double a)  // 立方根

// 取整
static double ceil(double a)   // 向上取整
static double floor(double a)  // 向下取整
static long round(double a)    // 四舍五入

// 三角函数
static double sin(double a)
static double cos(double a)
static double tan(double a)
static double asin(double a)
static double acos(double a)
static double atan(double a)
static double atan2(double y, double x)

// 对数
static double log(double a)    // 自然对数
static double log10(double a)  // 以10为底
static double exp(double a)    // e^a

// 随机数
static double random()  // [0.0, 1.0)

// 符号
static double signum(double d)  // 返回 -1.0, 0.0 或 1.0

// 其他
static double toRadians(double angdeg)  // 角度转弧度
static double toDegrees(double angrad)  // 弧度转角度

// 常量
static final double E = 2.718281828459045
static final double PI = 3.141592653589793
```

### Random 类

```java
// 构造
Random()
Random(long seed)

// 生成随机数
int nextInt()
int nextInt(int bound)  // [0, bound)
long nextLong()
float nextFloat()
double nextDouble()
boolean nextBoolean()

// 生成高斯分布随机数
double nextGaussian()

// 填充数组
void nextBytes(byte[] bytes)
```

---

## 9. 文件 I/O 工具

### File 类

```java
// 构造
File(String pathname)
File(String parent, String child)
File(File parent, String child)

// 文件信息
String getName()
String getPath()
String getAbsolutePath()
String getParent()
boolean exists()
boolean isFile()
boolean isDirectory()
boolean canRead()
boolean canWrite()
boolean isHidden()
long length()
long lastModified()

// 文件操作
boolean createNewFile()
boolean delete()
boolean mkdir()
boolean mkdirs()
boolean renameTo(File dest)
boolean setLastModified(long time)
boolean setReadOnly()
boolean setWritable(boolean writable)

// 目录操作
String[] list()
File[] listFiles()
File[] listFiles(FileFilter filter)

// 路径分隔符
static String separator       // 系统路径分隔符 (/ 或 \)
static String pathSeparator   // 路径列表分隔符 (; 或 :)
```

### Files 工具类（Java 7+）

```java
// 文件读写
static byte[] readAllBytes(Path path)
static List<String> readAllLines(Path path)
static List<String> readAllLines(Path path, Charset cs)
static String readString(Path path)  // Java 11+

static Path write(Path path, byte[] bytes, OpenOption... options)
static Path write(Path path, Iterable<? extends CharSequence> lines, OpenOption... options)
static Path writeString(Path path, CharSequence csq, OpenOption... options)  // Java 11+

// 文件流
static Stream<String> lines(Path path)
static BufferedReader newBufferedReader(Path path)
static BufferedWriter newBufferedWriter(Path path, OpenOption... options)

// 文件操作
static Path copy(Path source, Path target, CopyOption... options)
static Path move(Path source, Path target, CopyOption... options)
static void delete(Path path)
static boolean deleteIfExists(Path path)
static Path createFile(Path path, FileAttribute<?>... attrs)
static Path createDirectory(Path dir, FileAttribute<?>... attrs)
static Path createDirectories(Path dir, FileAttribute<?>... attrs)

// 文件信息
static boolean exists(Path path, LinkOption... options)
static boolean notExists(Path path, LinkOption... options)
static boolean isDirectory(Path path, LinkOption... options)
static boolean isRegularFile(Path path, LinkOption... options)
static boolean isReadable(Path path)
static boolean isWritable(Path path)
static long size(Path path)

// 遍历目录
static Stream<Path> list(Path dir)
static Stream<Path> walk(Path start, int maxDepth, FileVisitOption... options)
static Stream<Path> find(Path start, int maxDepth, BiPredicate<Path, BasicFileAttributes> matcher)

// 文件流
Stream<String> stream = Files.lines(Paths.get("file.txt"));
stream.forEach(System.out::println);
stream.close();

BufferedReader reader = Files.newBufferedReader(Paths.get("file.txt"));
BufferedWriter writer = Files.newBufferedWriter(Paths.get("file.txt"));

// 文件操作
Files.copy(Paths.get("source.txt"), Paths.get("dest.txt"));
Files.move(Paths.get("old.txt"), Paths.get("new.txt"));
Files.delete(Paths.get("file.txt"));
boolean deleted = Files.deleteIfExists(Paths.get("file.txt"));
Files.createFile(Paths.get("file.txt"));
Files.createDirectory(Paths.get("dir"));
Files.createDirectories(Paths.get("parent/child/grandchild"));

// 文件信息
boolean exists = Files.exists(Paths.get("file.txt"));
boolean notExists = Files.notExists(Paths.get("file.txt"));
boolean isDir = Files.isDirectory(Paths.get("dir"));
boolean isFile = Files.isRegularFile(Paths.get("file.txt"));
boolean readable = Files.isReadable(Paths.get("file.txt"));
boolean writable = Files.isWritable(Paths.get("file.txt"));
long size = Files.size(Paths.get("file.txt"));

// 遍历目录
Stream<Path> list = Files.list(Paths.get("."));  // 列出当前目录
list.forEach(System.out::println);
list.close();

Stream<Path> walk = Files.walk(Paths.get("."), 2);  // 递归遍历（最大深度2）
walk.forEach(System.out::println);
walk.close();

// 查找文件
Stream<Path> found = Files.find(Paths.get("."), 10,
    (path, attrs) -> path.toString().endsWith(".txt"));
found.forEach(System.out::println);
found.close();
```

### Scanner 类

```java
// 构造
Scanner(File source)
Scanner(InputStream source)
Scanner(String source)

// 判断
boolean hasNext()
boolean hasNextInt()
boolean hasNextDouble()
boolean hasNextLine()

// 读取
String next()
int nextInt()
double nextDouble()
String nextLine()

// 分隔符
Scanner useDelimiter(String pattern)

// 关闭
void close()
```

---

## 10. 其他常用工具类

### Object 类

```java
// 对象比较
boolean equals(Object obj)
int hashCode()

// 对象信息
String toString()
Class<?> getClass()

// 对象克隆
protected Object clone()

// 线程相关
void wait()
void wait(long timeout)
void notify()
void notifyAll()
```

### Objects 工具类（Java 7+）

```java
// 空值检查
static <T> T requireNonNull(T obj)
static <T> T requireNonNull(T obj, String message)
static boolean isNull(Object obj)
static boolean nonNull(Object obj)

// 比较
static boolean equals(Object a, Object b)
static boolean deepEquals(Object a, Object b)
static int compare(T a, T b, Comparator<? super T> c)

// 哈希码
static int hash(Object... values)
static int hashCode(Object o)

// 字符串
static String toString(Object o)
static String toString(Object o, String nullDefault)
```

### Character 类

#### 创建与转换

```java
// 构造（已过时，建议使用 valueOf）
Character ch1 = new Character('A');
Character ch2 = Character.valueOf('A');

// 自动装箱
Character ch3 = 'A';

// 转换
char primitiveChar = ch3.charValue();
```

#### 判断方法

```java
// 字母判断
static boolean isLetter(char ch)           // 是否为字母
static boolean isDigit(char ch)            // 是否为数字
static boolean isLetterOrDigit(char ch)    // 是否为字母或数字

// 大小写判断
static boolean isLowerCase(char ch)        // 是否为小写字母
static boolean isUpperCase(char ch)        // 是否为大写字母

// 空白字符判断
static boolean isWhitespace(char ch)       // 是否为空白字符
static boolean isSpaceChar(char ch)        // 是否为空格字符

// 其他判断
static boolean isDefined(char ch)          // 是否在Unicode中定义
static boolean isISOControl(char ch)       // 是否为ISO控制字符

// 示例
char ch = 'A';
boolean isLetter = Character.isLetter(ch);         // true
boolean isDigit = Character.isDigit(ch);           // false
boolean isUpper = Character.isUpperCase(ch);       // true
boolean isWhitespace = Character.isWhitespace(' '); // true
```

#### 转换方法

```java
// 大小写转换
static char toLowerCase(char ch)
static char toUpperCase(char ch)
static char toTitleCase(char ch)

// 示例
char upper = Character.toUpperCase('a');  // 'A'
char lower = Character.toLowerCase('A');  // 'a'

// 数字转换
static int digit(char ch, int radix)      // 字符转数字（按指定进制）
static char forDigit(int digit, int radix) // 数字转字符

// 示例
int num = Character.digit('5', 10);       // 5
char ch = Character.forDigit(15, 16);     // 'f'
```

#### 获取字符属性

```java
// 获取字符类型
static int getType(char ch)

// 获取数值
static int getNumericValue(char ch)

// 示例
int type = Character.getType('A');        // 返回类型常量
int value = Character.getNumericValue('5'); // 5
```

#### 比较方法

```java
// 比较
int compareTo(Character anotherCharacter)
static int compare(char x, char y)

// 示例
Character ch1 = 'A';
Character ch2 = 'B';
int result = ch1.compareTo(ch2);  // 负数，因为 'A' < 'B'
int result2 = Character.compare('A', 'B');  // 负数
```

#### 常用常量

```java
Character.MIN_VALUE    // '\u0000' (最小值)
Character.MAX_VALUE    // '\uffff' (最大值)
Character.SIZE         // 16 (字符所占位数)
Character.BYTES        // 2 (字节数)

// 字符类型常量
Character.UPPERCASE_LETTER
Character.LOWERCASE_LETTER
Character.DECIMAL_DIGIT_NUMBER
Character.SPACE_SEPARATOR
// 等等...
```

------

## 11. 其他方法

### 11.1 List.of() / Set.of() / Map.of()（Java 9+）

```java
// 创建不可变集合
List<String> list = List.of("A", "B", "C");
Set<String> set = Set.of("A", "B", "C");
Map<String, Integer> map = Map.of("A", 1, "B", 2, "C", 3);

// Map.ofEntries 用于更多键值对
Map<String, Integer> bigMap = Map.ofEntries(
    Map.entry("A", 1),
    Map.entry("B", 2),
    Map.entry("C", 3)
);
```

### 11.2 String.join()

```java
// 连接字符串
String joined = String.join(", ", "Apple", "Banana", "Cherry");
// 结果: "Apple, Banana, Cherry"

// 连接集合
List<String> list = Arrays.asList("Apple", "Banana", "Cherry");
String joined2 = String.join(", ", list);
```

### 11.3 String.format()

```java
// 格式化字符串
String formatted = String.format("Name: %s, Age: %d", "John", 25);
String number = String.format("%.2f", 3.14159);  // "3.14"
String hex = String.format("%x", 255);  // "ff"
```

### 11.4 Collections.nCopies()

```java
// 创建包含n个相同元素的列表
List<String> list = Collections.nCopies(5, "Hello");
// 结果: ["Hello", "Hello", "Hello", "Hello", "Hello"]
```

### 11.5 Arrays.asList()

```java
// 数组转List（返回固定大小的列表）
List<String> list = Arrays.asList("A", "B", "C");
// 注意：不能添加或删除元素，但可以修改
```

### 11.6 Arrays.setAll()

```java
// 使用生成器设置数组所有元素
int[] arr = new int[10];
Arrays.setAll(arr, i -> i * 2);
// 结果: [0, 2, 4, 6, 8, 10, 12, 14, 16, 18]
```

### 11.13 Arrays.mismatch()（Java 9+）

```java
// 找出两个数组第一个不匹配的位置
int[] arr1 = {1, 2, 3, 4, 5};
int[] arr2 = {1, 2, 9, 4, 5};
int index = Arrays.mismatch(arr1, arr2);  // 2
```

### 11.14 Objects实用方法

```java
// 安全的equals和hashCode
boolean equal = Objects.equals(obj1, obj2);  // 处理null
int hash = Objects.hash(name, age, email);   // 联合哈希
```

### 11.15 Files实用方法（Java 11+）

```java
// readString / writeString
String content = Files.readString(path);
Files.writeString(path, "Hello World");

// mismatch (Java 12+)
long pos = Files.mismatch(file1, file2);  // 比较文件
```

## 12. 常用代码示例

### 1. 集合遍历

```java
// List 遍历
List<String> list = new ArrayList<>();
// 方式1：for-each
for (String s : list) {
    System.out.println(s);
}
// 方式2：迭代器
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    System.out.println(it.next());
}
// 方式3：lambda (Java 8+)
list.forEach(s -> System.out.println(s));

// Map 遍历
Map<String, Integer> map = new HashMap<>();
// 方式1：entrySet
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}
// 方式2：keySet
for (String key : map.keySet()) {
    System.out.println(key + ": " + map.get(key));
}
// 方式3：lambda (Java 8+)
map.forEach((k, v) -> System.out.println(k + ": " + v));
```

### 2. 文件读写

```java
// 读取文件
Path path = Paths.get("file.txt");
List<String> lines = Files.readAllLines(path);

// 写入文件
List<String> lines = Arrays.asList("line1", "line2", "line3");
Files.write(path, lines);

// 使用 BufferedReader
try (BufferedReader br = Files.newBufferedReader(path)) {
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
}
```

---

## 快速查询索引

### 按功能分类

**字符串操作**: String, StringBuilder, StringBuffer  
**集合操作**: List, Set, Map, Queue, Collections  
**数组操作**: Arrays  
**日期时间**: LocalDate, LocalTime, LocalDateTime, DateTimeFormatter  
**数学计算**: Math, Random  
**文件操作**: Path, Files, Scanner  
**其他工具**: Objects, System, Optional, UUID

### 常用场景快速定位

- **排序**: `Collections.sort()`, `Arrays.sort()`, `list.sort()`
- **查找**: `Collections.binarySearch()`, `Arrays.binarySearch()`
- **遍历**: `for-each`, `Iterator`, `forEach()`, `Stream`
- **转换**: `toArray()`, `Arrays.asList()`, `Collectors.toList()`
- **文件读取**: `Files.readAllLines()`, `Files.readString()`
- **日期格式化**: `DateTimeFormatter.ofPattern()`
- **字符串拼接**: `String.join()`, `StringBuilder.append()`

---

**文档版本**: v2.0 (整理版)  
**最后更新**: 2024  
**适用于**: Java 8 及以上版本  
**说明**: 
- 已按逻辑重新分类组织
- 标注 "Java X+" 的方法需要对应版本支持
- 补充了常用的边角方法
