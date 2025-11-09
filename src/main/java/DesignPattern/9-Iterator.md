## 9. Iterator 迭代器模式

### 模式简介

提供继承了迭代器的各种子迭代器，根据要求顺序访问不同的集合元素。  
**案例**：Java集合 - ArrayList、HashSet都能用for-each遍历，内部实现各不相同。  
**何时使用**：统一遍历不同聚合对象；具有多种遍历方式且不在乎性能

### 增强遍历的本质
一个语法糖
```java
for (String s : list) {
    System.out.println(s);
}

```
等价于
```java
for (Iterator<String> it = list.iterator(); it.hasNext(); ) {
    String s = it.next();
    System.out.println(s);
}

```
### Iterator 迭代器写法要点
1. implements Iterable。如果不支持增强遍历，可以不写
2. 考试是也可能自己写一个迭代器接口，
3. 总之写好hasNext(),next();

代码直接看包里的解法
