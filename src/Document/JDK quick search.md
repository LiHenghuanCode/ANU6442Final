# Java基础工具类 - 快速查阅指南

## 🔍 场景速查表

### 字符串操作

| 需求 | 方法 | 示例 |
|------|------|------|
| 连接字符串 | `String.join()` | `String.join(", ", list)` |
| 格式化输出 | `String.format()` | `String.format("Name: %s", name)` |
| 分割字符串 | `split()` | `str.split(",")` |
| 去除空格 | `strip()` | `str.strip()` (Java 11+) |
| 重复字符串 | `repeat()` | `"Hi".repeat(3)` (Java 11+) |
| 判断空白 | `isBlank()` | `str.isBlank()` (Java 11+) |

### 集合操作

| 需求 | 方法 | 示例 |
|------|------|------|
| 创建不可变List | `List.of()` | `List.of("A", "B", "C")` (Java 9+) |
| 创建不可变Set | `Set.of()` | `Set.of(1, 2, 3)` (Java 9+) |
| 创建不可变Map | `Map.of()` | `Map.of("key", "value")` (Java 9+) |
| 数组转List | `Arrays.asList()` | `Arrays.asList(1, 2, 3)` |
| 创建空集合 | `Collections.emptyList()` | 返回不可变空List |
| 创建n个相同元素 | `Collections.nCopies()` | `Collections.nCopies(5, "X")` |
| 排序List | `Collections.sort()` | `Collections.sort(list)` |
| 反转List | `Collections.reverse()` | `Collections.reverse(list)` |
| 打乱List | `Collections.shuffle()` | `Collections.shuffle(list)` |

### 数组操作

| 需求 | 方法 | 示例 |
|------|------|------|
| 排序 | `Arrays.sort()` | `Arrays.sort(arr)` |
| 二分查找 | `Arrays.binarySearch()` | `Arrays.binarySearch(arr, 5)` |
| 填充 | `Arrays.fill()` | `Arrays.fill(arr, 0)` |
| 批量设置 | `Arrays.setAll()` | `Arrays.setAll(arr, i -> i * 2)` (Java 8+) |
| 复制 | `Arrays.copyOf()` | `Arrays.copyOf(arr, len)` |
| 比较 | `Arrays.equals()` | `Arrays.equals(arr1, arr2)` |
| 找不同 | `Arrays.mismatch()` | `Arrays.mismatch(arr1, arr2)` (Java 9+) |
| 转字符串 | `Arrays.toString()` | `Arrays.toString(arr)` |

### 文件操作

| 需求 | 方法 | 示例 |
|------|------|------|
| 读取所有行 | `Files.readAllLines()` | `Files.readAllLines(path)` |
| 读取为字符串 | `Files.readString()` | `Files.readString(path)` (Java 11+) |
| 写入字符串 | `Files.writeString()` | `Files.writeString(path, str)` (Java 11+) |
| 写入行列表 | `Files.write()` | `Files.write(path, lines)` |
| 复制文件 | `Files.copy()` | `Files.copy(src, dest)` |
| 移动文件 | `Files.move()` | `Files.move(src, dest)` |
| 比较文件 | `Files.mismatch()` | `Files.mismatch(f1, f2)` (Java 12+) |

### 日期时间

| 需求 | 方法 | 示例 |
|------|------|------|
| 当前日期 | `LocalDate.now()` | 返回今天的日期 |
| 当前时间 | `LocalTime.now()` | 返回当前时间 |
| 当前日期时间 | `LocalDateTime.now()` | 返回当前日期和时间 |
| 格式化 | `format()` | `date.format(formatter)` |
| 解析字符串 | `parse()` | `LocalDate.parse("2024-01-01")` |
| 日期加减 | `plusDays()` | `date.plusDays(7)` |
| 计算间隔 | `ChronoUnit.between()` | `ChronoUnit.DAYS.between(d1, d2)` |

### 数学运算

| 需求 | 方法 | 示例 |
|------|------|------|
| 绝对值 | `Math.abs()` | `Math.abs(-5)` |
| 最大值 | `Math.max()` | `Math.max(10, 20)` |
| 最小值 | `Math.min()` | `Math.min(10, 20)` |
| 幂运算 | `Math.pow()` | `Math.pow(2, 3)` |
| 平方根 | `Math.sqrt()` | `Math.sqrt(16)` |
| 随机数 | `Math.random()` | `Math.random()` |
| 正确取模 | `Math.floorMod()` | `Math.floorMod(-10, 3)` (Java 8+) |
| 向下取整除法 | `Math.floorDiv()` | `Math.floorDiv(-10, 3)` (Java 8+) |

## 🔧 常用代码模板

### 1. 安全的List遍历与修改
```java
// 使用Iterator安全删除
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    String item = it.next();
    if (shouldRemove(item)) {
        it.remove();  // 安全删除
    }
}

// 使用removeIf (Java 8+)
list.removeIf(item -> shouldRemove(item));
```

### 2. Map的常用操作
```java
// 存在则更新，不存在则插入
map.compute(key, (k, v) -> v == null ? 1 : v + 1);

// 不存在则插入
map.putIfAbsent(key, value);

// 获取或返回默认值
int count = map.getOrDefault(key, 0);

// 合并值
map.merge(key, 1, Integer::sum);
```

### 3. 文件读写模板
```java
// 读取所有行
List<String> lines = Files.readAllLines(Paths.get("file.txt"));

// 写入行列表
Files.write(Paths.get("file.txt"), 
           Arrays.asList("Line1", "Line2"),
           StandardOpenOption.APPEND);

// 逐行处理大文件
try (Stream<String> stream = Files.lines(Paths.get("large.txt"))) {
    stream.forEach(line -> process(line));
}
```

