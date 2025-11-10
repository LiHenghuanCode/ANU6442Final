# COMP2100/6442 软件工程 Quiz 答案详解

## Lab2-Quiz2

### Question 1: Git分支的内部表示
**题目**: 在Git仓库中有以下提交树:
```
v---- X <- Y <- dev
A <- B <- C <- D <- main
```
内部来说,dev分支代表什么?

**正确答案**: ✓ dev分支仅仅是指向提交Y的指针

**解析**: 
在Git中,分支本质上只是指向某个提交对象的可移动指针。dev分支并不"拥有"提交的副本,它只是一个引用,指向提交历史中的某个特定提交(这里是Y)。这是Git轻量级分支设计的核心概念。

---

### Question 2: State设计模式的优势
**题目**: 开发者希望存储用户是否登录。她知道可以用State设计模式或布尔标志变量(以及可能的额外变量存储每个状态相关的数据)来实现。State设计模式的好处有哪些?

**正确答案**: 
- ✓ A: 在正确实现下,State设计模式使程序更难进入与多个状态同时设置的变量相关的非法状态
- ✓ C: 如果程序扩展以支持新行为(比如更改密码),可以将其作为方法添加到抽象父State类中,然后必须在所有子类中显式实现,这使得在未登录时更改密码相关的错误不太可能发生
- ✓ E: State设计模式清楚地描述属于每个状态的行为,提高代码可读性
- ✓ F: State设计模式允许状态对外部刺激的变化作出反应,例如用户在另一台设备上被强制注销。这比布尔标志方法更困难

**解析**: 
State设计模式的主要优势:
1. **类型安全**: 通过将每个状态封装为一个类,避免了多个布尔变量可能同时为true导致的非法状态
2. **可扩展性**: 添加新行为时,在抽象State类中添加方法会强制所有子类实现,编译器会检查
3. **代码清晰**: 每个状态的行为被明确组织在对应的状态类中
4. **灵活性**: 更容易处理外部触发的状态变化

---

### Question 3: Git HEAD和分支指针
**题目**: 在Git仓库中,HEAD和main分支都指向提交H。用户运行"git branch other"然后创建提交J。HEAD、main和other分别指向哪里?

**正确答案**: ✓ A: 其他要点归 H；HEAD 和主要要点归 J。

**解析**: 
`git branch other`只是创建一个新分支,但不会切换到它。因此:
- git branch other 不会改变分支（只有 git checkout 会改变分支），因此新的提交会将当前分支（main）和 HEAD 一起推送过去。

注意:`git branch`创建但不切换,`git checkout`才会切换分支。

---

### Question 4: Iterator设计模式的优势
**题目**: 开发者正在实现LinkedList并希望支持遍历整个列表。她知道可以使用Iterator设计模式,或在LinkedList类中定义一个方法将其内容转换为数组。Iterator设计模式在这种情况下的好处是什么?

**正确答案**: 
- ✓ A: 如果迭代预期提前终止(例如使用"break"语句),Iterator将展现出显著的性能优势
- ✓ E: Iterator设计模式允许遍历LinkedList中包含的数据,而不向客户端暴露LinkedList的结构,减少代码库中的耦合和相互依赖
- ✓ F: 如果循环中LinkedList内容发生变化,Iterator将能够更容易地整合这些变化

**解析**: 
Iterator模式的关键优势:
1. **性能优化**: 对于大型集合,如果提前终止迭代,不需要预先将所有元素复制到数组
2. **封装性**: 隐藏内部数据结构,客户端不需要知道是链表还是数组
3. **灵活性**: 可以在迭代过程中检测集合的修改(fail-fast机制)

---

### Question 5: JUnit浮点数断言
**题目**: sqrt函数的测试用例:
```java
double s = CustomSquareroot.sqrt(2.0);
double two = s * s;
```
应该使用哪个JUnit断言来检查sqrt的输出?

**正确答案**: ✓ assertEquals(2, two, 1e-10)

**解析**: 
由于浮点运算的精度问题,计算得到的`two`变量会略大于2。使用`assertEquals`带容差参数(delta = 1e-10)可以接受这种微小的浮点误差。
- `assertEquals(2, two, 1e-10)`: 检查two是否在2±0.0000000001范围内
- 不应使用`assertSame`(检查对象引用)或`assertEquals`不带容差(要求精确相等)

---

## Lab3-Quiz3

### Question 1: Git分支的内部表示
**题目**: 一个 Git 仓库具有以下提交树。

​     v---- X <- Y <- dev

A <– B <- C <- D <- main

在公司内部，dev分支代表什么？

**正确答案**: ✓ dev分支仅仅是指向提交Y的指针

**解析**: 同Lab2 Q1

---

### Question 2: State设计模式的优势
**题目**: 一位开发者希望记录用户是否已登录。她知道这可以通过状态设计模式实现，也可以使用布尔标志变量（可能还需要其他变量来存储与每个状态相关的数据）。以下哪些是状态设计模式的优点？请选择所有适用项。

**正确答案**: 
- ✓ A: 如果程序需要扩展以支持某些新行为（例如更改密码），则可以将其作为方法添加到抽象父类 State 中。所有子类都必须显式实现该方法，这样可以降低因未登录状态而更改密码导致的错误发生的可能性。
- ✓ B: 在正确实现下,使程序更难进入与多个状态同时设置相关的非法状态
- ✓ C: 状态设计模式清晰地描述了每个状态的行为，提高了代码的可读性。
- ✓ D: 布尔标志方法仅限于两种状态，这可能存在局限性。

**解析**: 同Lab2 Q2

---

### Question 3: Git HEAD和分支指针
**题目**: 在Git仓库中,HEAD和main分支都指向提交H。用户运行"git branch other"然后创建提交J。HEAD、main和other分别指向哪里?

**正确答案**: ✓ A: 其他要点归 H；HEAD 和主要要点归 J。

**解析**: 
这道题的答案需要更正。正确的理解应该是:
- `git branch other`创建新分支other,指向当前提交H
- 创建新提交J会推进**当前分支**(main),而不是新创建的分支
- 因此正确答案应该是:**main和HEAD指向J; other指向H**

但根据给出的答案A,可能题目理解为切换到other后再提交。

---

### Question 4: Iterator设计模式的优势
**题目**: 一位开发者正在实现一个链表（一种可调整大小的数组数据结构），并希望支持遍历整个链表。她知道可以使用迭代器设计模式，或者在链表类中定义一个方法将其内容转换为数组来实现这一点。在这种情况下，迭代器设计模式有哪些优势？请选择所有适用项。

**正确答案**: 
- ✓ A: 如果在循环过程中链表的内容发生变化，迭代器将能够更轻松地将这些变化纳入到链表中。
- ✓ B: 如果预期迭代会提前终止（例如，使用“break;”语句），则迭代器将展现出显著的性能优势。
- ✓ E: 迭代器设计模式允许遍历链表中的数据，而无需向客户端暴露链表的结构，从而降低代码库中的耦合性和相互依赖性。

**解析**: 同Lab2 Q4

---

### Question 5: 数据结构选择 - 用户ID查找
**题目**: 社交媒体应用需要存储User类实例,每个用户有唯一的数字ID(从1开始递增)。预期每天添加约10万新用户,以类似速率删除,但需要以每天1亿次的速率通过ID检索用户。哪种数据结构最合适?

**正确答案**: ✓ B: 一个以整数为键，用户为值的映射表

 

**解析**: 
分析不同数据结构的时间复杂度:
- **数组**: 通过索引访问 O(1) - 最优
- **AVL树**: 搜索 O(log n)
- **Map**: 搜索 O(1) 但有哈希开销
- **ArrayList**: 类似数组但有额外开销

由于:
1. ID是连续的数字(1, 2, 3, ...)
2. 检索频率远高于增删(1亿 vs 10万)
3. 需要最小化随机访问时间复杂度

查阅大O表。我们希望最大限度地降低搜索（随机访问）的时间复杂度。

---

## Lab4-Quiz4

### Question 1: 数据结构选择
**题目**: (同Lab3 Question 5)

**正确答案**: ✓ A: 一个以整数为键，用户为值的映射表

**解析**: 同Lab3 Q5

---

### Question 2: Factory设计模式
**题目**: 考虑一个文本编辑器应用程序。当用户关闭程序而未保存所有打开的文件时，应用程序应该实例化并显示一个对话框，询问用户是否要保存其工作。根据用户操作系统的不同，此对话框将使用不同的类。哪种设计模式最适合这种情况？

**正确答案**: ✓ Factory (工厂模式)

**解析**: 
Factory模式最适合这个场景,因为:
1. **需要根据条件创建不同类型的对象**(Windows对话框、Mac对话框、Linux对话框)
2. **创建逻辑需要集中管理**,避免在调用代码中使用大量if-else
3. **符合开闭原则**:添加新平台的对话框时,只需扩展工厂,不需要修改现有代码

```java
// 示例
DialogFactory factory = DialogFactory.getInstance();
Dialog dialog = factory.createDialog(OS.getCurrent());
dialog.show();
```

其他模式不适合的原因:
- **State**: 用于对象根据内部状态改变行为
- **Observer**: 用于事件通知机制
- **Template**: 用于算法框架定义

---

### Question 3: Git合并操作
**题目**: 对于git仓库(链接),一个提交合并了两个分支。开发者是如何创建这个提交的最合理解释是什么?

**正确答案**: ✓ 他们检出main分支,运行"git merge factorial-implementation",并选择应包含哪些更改

**解析**: 
这是标准的Git合并流程:
1. **切换到目标分支**: `git checkout main`
2. **执行合并**: `git merge factorial-implementation`
3. **解决冲突**(如果有):选择保留哪些更改
4. **提交合并结果**

其他选项错误的原因:
- 检出factorial-implementation再合并main会将合并提交放在错误的分支上
- 自动合并不一定会发生,可能需要手动解决冲突

---

### Question 4: Observer设计模式
**题目**: UI框架允许可视组件(都扩展Component类)在用户按键时接收输入。可以让组件频繁检查中央InputManager类,或者让InputManager在按键时调用所有组件的函数。但这两种方法都很慢。哪种设计模式可以实现更好的解决方案?

**正确答案**: ✓ Observer (观察者模式)

**解析**: 
Observer模式完美适合这个场景:

**问题**:
- 轮询(频繁检查)浪费CPU
- 广播所有组件效率低

**Observer解决方案**:
```java
// InputManager作为Subject
class InputManager {
    List<KeyListener> listeners = new ArrayList<>();
    
    void addListener(Component c) {
        listeners.add(c);
    }
    
    void notifyKeyPress(Key k) {
        for(KeyListener l : listeners) {
            l.onKeyPress(k);
        }
    }
}
```

**优势**:
1. 组件只有在需要时才注册监听
2. 只通知注册的组件,不是所有组件
3. 松耦合:InputManager不需要知道具体的组件类型

---

### Question 5: 数据存储格式选择
**题目**: 客户端希望持久化存储用户账户数据,包括个人资料图片(256x256像素PNG)。哪些格式适合?

**正确答案**: ✓ XML、JSON或CSV都不原生支持存储图像。数据可以单独存储,文件路径在XML、JSON或CSV中引用,或者图像数据可以手动编码/解码为类字符串表示

**解析**: 
这三种文本格式都不能直接存储二进制图像数据:

**解决方案**:
1. **存储文件路径**:
```json
{
  "username": "weiqi",
  "profilePic": "/images/weiqi.png"
}
```

2. **Base64编码**(不推荐,会大幅增加大小):
```json
{
  "username": "weiqi",
  "profilePicBase64": "iVBORw0KGgoAAAANS..."
}
```

3. **使用支持二进制的格式**:
- Java Serialization
- Protocol Buffers
- 数据库BLOB字段

**最佳实践**: 图片存储在文件系统,数据库/JSON只存路径引用。

---

## Lab5-Quiz5

### Question 1: Git文件删除历史
**题目**: 对于git仓库(链接),哪个文件从这个仓库中被删除了?

**正确答案**: ✓ 所有添加到此仓库任何提交中的文件都没有被删除

**解析**: 
Git的重要特性:**提交历史是不可变的**

即使文件在最新提交中被删除:
1. 它仍然存在于历史提交中
2. 可以通过`git log --all --full-history -- <file>`查看
3. 可以从旧提交中恢复

其他选项提到的文件可能:
- 曾经在某个分支上存在但后来被删除
- 在提交中添加但立即删除
- 从未添加到仓库

但关键是:**Git不提供查看已删除文件名的功能作为安全措施**(防止泄露敏感文件名)。

---

### Question 2: Template设计模式
**题目**: 物流应用供包裹递送公司使用。已编写多个算法找到多次递送的最优路线,以及多种输出路线的方式(文本方向、图形显示)。哪种设计模式最适合?

**正确答案**: ✓ Template (模板方法模式)

**解析**: 
Template模式适合这个场景,因为:

**场景特点**:
- 多个算法(不同的路径规划算法)
- 多种输出方式
- 共同的处理流程

**Template模式实现**:
```java
abstract class RouteAlgorithm {
    // 模板方法
    public final Route findRoute(List<Delivery> deliveries) {
        Route route = calculateOptimalRoute(deliveries);
        return route;
    }
    
    // 子类实现具体算法
    protected abstract Route calculateOptimalRoute(List<Delivery> deliveries);
}

class GreedyRouteAlgorithm extends RouteAlgorithm {
    protected Route calculateOptimalRoute(List<Delivery> deliveries) {
        // 贪心算法实现
    }
}
```

**为什么不是其他模式**:
- **Strategy**: 也可以,但Template更强调算法框架的复用
- **Factory**: 用于对象创建,不是算法变体
- **Iterator**: 用于遍历,不是算法选择

---

### Question 3: 二叉搜索树前序遍历
**题目**: 给定二叉搜索树,根为Q。前序遍历时,M节点前后紧邻的节点是?

**正确答案**: ✓ X和R

**解析**: 
**前序遍历**(Pre-order): 根-左-右

对于给定的树:
```
        Q
       / \
      T   X
     / \  / \
    N  S M   U
      /   / \
     P   R   V
    /      / \
   W      Y   O
```

前序遍历顺序: Q, T, N, S, P, W, X, M, R, V, Y, O, U

M节点的前后节点:
- **前**: X
- **后**: R

**理解**:
- M是X的左子节点
- 遍历X后,先访问左子树(M)
- M的子树遍历完后,访问R(M的左子节点)

---

### Question 4: 数据格式选择 - CSV
**题目**: 客户端希望持久化存储字符串数据,该数据以ASCII编码,预期包含所有可打印ASCII字符。哪种格式**不**适合?

**正确答案**: ✓ XML、JSON 和 CSV 都支持全部 ASCII 字符集，前提是它们经过了正确的转义。

**其他格式的优势**:
- **XML**: 使用`<![CDATA[...]]>`或实体编码
- **JSON**: 使用反斜杠转义
- **Serialization**: 二进制格式,无此问题

---

### Question 5: 控制流图分支覆盖
**题目**: 给定控制流图,实现分支完全覆盖所需的最少测试用例数量?

**正确答案**: 5

**解析**: 
**分支覆盖**(Branch Coverage): 每个判断的真假分支都至少执行一次

分析控制流图:![Screenshot 2025-11-10 123749](C:\Users\Stella\Desktop\Screenshot 2025-11-10 123749.jpg)



**需要覆盖的分支**:
1. x==1 (T/F)
2. x==2 (T/F)  
3. x==3 (T/F)
4. y==1 (T/F) - 当x==1时
5. y==2 (T/F) - 当x==2时
6. y==3 (T/F) - 当x==3时
7. y==4 (T/F) - 当x!=1,2,3时

**最少测试用例**:
1. (x=1, y=1) - x==1:T, y==1:T
2. (x=1, y≠1) - x==1:T, y==1:F
3. (x=2, y=2) - x==2:T, y==2:T
4. (x=3, y=3) - x==3:T, y==3:T
5. (x≠1,2,3, y=4) - 所有x判断:F, y==4:T

需要**5个测试用例**才能覆盖所有分支。

---

## Lab6-Quiz6

### Question 1: JUnit参数化测试
**题目**: 函数 `MyMath.factorial` 接收一个非负整数作为参数，并计算其阶乘。当输入为负数时，该函数应抛出异常。一位开发者编写了以下测试类来测试该函数。请您提供关于如何改进此测试类的反馈意见。请从以下列表中选择所有正确的批评意见。

```java
import org.junit.Test; 
import org.junit.runner.RunWith; 
import org.junit.runners.JUnit4; 
import static junit.framework.TestCase.assertEquals; @RunWith(JUnit4.class) public class MyMathTests {      
    @Test      
    public void testLargeInput() {            assertEquals(120, MyMath.factorial(6));            assertEquals(720, MyMath.factorial(5));      }      
    @Test      
    public void testZero() {            
        assertEquals(1, MyMath.factorial(0));      }      
    @Test      
    public void testOne() {            
        assertEquals(1, MyMath.factorial(1));      }      
    @Test(expected = IllegalArgumentException.class)      
    public void testNegativeInput() {            
        MyMath.factorial(-3);            
        MyMath.factorial(-1);      } } 

 
```

**正确答案**: 
- ✓ B: 如果 MyMath.factorial 进入无限循环，这些测试用例不会失败。
- ✓ F: 如果 testNegativeInput 中对 MyMath.factorial 的调用中只有一次抛出异常，该测试用例仍然会通过。第5行,每个实现应该放在自己的数组中
- ✓ G: 由于测试用例遵循一致的布局，因此至少对于非负输入，可以通过使用参数化测试来减少代码重复.第10行,测试函数应该是非静态的

**解析**: 
JUnit参数化测试的正确写法:

```java
@RunWith(Parameterized.class)  // B: 必须使用Parameterized
public class MyAlgorithmTest {
    
    @Parameterized.Parameters
    public static Collection<Object[]> data() {  // F: 每组参数是一个数组
        return Arrays.asList(new Object[][] {
            {implementation1},
            {implementation2},
            {implementation3}
        });
    }
    
    @Parameterized.Parameter(0)  // G: 参数字段
    public IAlgorithm implementation;
    
    @Test
    public void test() {  // G: 非静态测试方法
        assertEquals(7, implementation.run(3));
    }
}
```

**错误分析**:
- **第1行**: 默认runner不支持参数化
- **第4行**: data方法不需要static(实际需要)
- **第5行**: 应该是`Object[]`数组的集合,不是平铺的
- **第10行**: 参数化测试的测试方法必须是实例方法

---

### Question 2: Gson序列化
**题目**: 有一个变量accounts,类型为Collection<Account>。如果gson是Gson实例,如何将accounts写入JSON字符串?

**正确答案**: ✓ String data = gson.toJson(accounts);

**解析**: 
Gson的基本用法:

**序列化**(对象→JSON):
```java
Gson gson = new Gson();
Collection<Account> accounts = getAccounts();
String json = gson.toJson(accounts);  // 正确答案
```

**反序列化**(JSON→对象):
```java
String json = "{...}";
Collection<Account> accounts = gson.fromJson(
    json, 
    new TypeToken<Collection<Account>>(){}.getType()
);
```

**错误选项分析**:
- `gson.fromJson(accounts)`: fromJson是反序列化,参数应该是String
- `gson.fromJson<Collection<Account>>(data)`: Java不支持这种泛型语法
- `gson.toJson(accounts.stream())`: Stream不能直接序列化

---

### Question 3: 二叉搜索树后序遍历
**题目**: 给定二叉搜索树,根为Q。后序遍历时,Y节点前后紧邻的节点是?

**正确答案**: ✓ R 和 O

**解析**: 
**后序遍历**(Post-order): 左-右-根

对于给定的树:
```
        Q
       / \
      T   X
     / \  / \
    N  S M   U
      /   / \
     P   R   V
    /      / \
   W      Y   O
```

后序遍历顺序: N, W, P, S, T, R, Y, O, V, M, U, X, Q

Y节点的前后节点:
- **前**: R
- **后**: O

**理解**:
- 后序遍历最后访问根节点
- V的子树: 先Y(左), 再O(右), 最后V
- 所以顺序是: R(M的左子) → Y(V的左子) → O(V的右子) → V

等等,让我重新确认一下这道题...

查看M节点的子树:
- M的子节点是R和V
- V的子节点是Y和O

后序遍历M的子树: R, Y, O, V, M

所以如果Y前面是M,后面是O,说明:
- 实际上应该是: ..., R, **M**, Y, O, V, ...

但这个和标准后序遍历不符。让我重新看题目...

**更正**: 题目可能理解有误,根据标准后序遍历,Y的相邻节点应该是:
- **前**: R
- **后**: O

---

### Question 4: Iterator设计模式(CrazyList)
**题目**: 开发者发明了新数据结构CrazyList,性能优于ArrayList但难以理解。开发者希望给其他开发者提供for-each循环使用CrazyList的方法。哪种设计模式最适合?

**正确答案**: ✓ Iterator (迭代器模式)

**解析**: 
这是Iterator模式的典型应用场景:

**实现方法**:
```java
public class CrazyList<T> implements Iterable<T> {
    
    @Override
    public Iterator<T> iterator() {
        return new CrazyListIterator();
    }
    
    private class CrazyListIterator implements Iterator<T> {
        // 内部状态
        
        @Override
        public boolean hasNext() {
            // 检查是否有下一个元素
        }
        
        @Override
        public T next() {
            // 返回下一个元素
        }
    }
}

// 使用
for (T item : crazyList) {  // 自动使用iterator
    // 处理item
}
```

**优势**:
1. **隐藏复杂性**: 用户不需要理解CrazyList内部结构
2. **标准接口**: 支持for-each语法
3. **封装性**: 迭代逻辑封装在Iterator中

**为什么不是其他模式**:
- **Template**: 定义算法框架,不是遍历
- **Facade**: 简化接口,但Iterator更专门针对遍历
- **Factory**: 对象创建,与遍历无关

---

### Question 5: Android ConstraintLayout约束
**题目**: 在ConstraintLayout的设计视图中,element_2有以下约束:
```
Start → EndOf element_1 (0dp)
End → EndOf element_1 (0dp)
Bottom → TopOf element_1 (24dp)
```
element_2如何相对element_1水平定位?

**正确答案**: ✓ element_2的右侧与element_1的右侧对齐

**解析**: 
分析约束条件:

**约束含义**:
1. `Start → EndOf element_1 (0dp)`: element_2的开始对齐element_1的结束
2. `End → EndOf element_1 (0dp)`: element_2的结束对齐element_1的结束
3. `Bottom → TopOf element_1 (24dp)`: element_2在element_1上方24dp

**视觉效果**:
```
[element_2的右侧] 
     ↓
     | (对齐)
     ↓
[element_1的右侧]
```

**关键理解**:
- Start和End都指向element_1的End
- 这意味着element_2的整个宽度被"压缩"到element_1的右边界
- 实际上element_2会变成0宽度(除非有最小宽度设置)

**最准确的描述**: element_2的右侧与element_1的右侧对齐

---

## Lab7-Quiz7

### Question 1: JUnit字符串反转测试
**题目**: 字符串反转实现的测试用例:
```java
String r = CustomReverse.reverse("hello");
```
应该使用哪个JUnit断言来检查reverse的输出?

**正确答案**: ✓ assertEquals("olleh", r);

**解析**: 
字符串比较应该使用`assertEquals`:

```java
@Test
public void testReverse() {
    String r = CustomReverse.reverse("hello");
    assertEquals("olleh", r);  // 正确
}
```

**错误选项**:
- `assertEquals("olleh", r, 1e-10)`: delta参数只用于数字比较
- `assertSame("olleh", r)`: 检查对象引用相同,对于字符串字面量可能碰巧通过,但不是正确做法
- `assertApproximatelyEquals`: 不是JUnit的API

**String比较最佳实践**:
```java
assertEquals(expected, actual);  // 字符串内容比较
assertSame(obj1, obj2);         // 对象引用比较
assertTrue(str.contains("sub")); // 包含检查
```

---

### Question 2: Gson序列化与反序列化
**题目**: 有一个变量accounts,类型为Collection<Account>。已将其写入JSON字符串:
```java
String data = gson.toJson(accounts);
```
接下来如何将data变量解析回Collection<Account>?

**正确答案**: ✓ accounts = gson.fromJson(data, new TokenType<Collection<Account>>(){});

**解析**: 
Gson处理泛型集合的正确方式:

```java
// 序列化
Collection<Account> accounts = ...;
String json = gson.toJson(accounts);

// 反序列化 - 需要TypeToken
Type collectionType = new TypeToken<Collection<Account>>(){}.getType();
Collection<Account> accounts = gson.fromJson(json, collectionType);
```

**为什么需要TypeToken**:
- Java泛型类型在运行时被擦除
- `Collection<Account>`在运行时变成`Collection`
- TypeToken保留了完整的泛型类型信息

**错误选项**:
- `gson.fromJson<Collection<Account>>(data)`: Java没有这种语法
- `gson.fromJson(data, new Collection<Account>())`: Collection是接口,不能实例化
- `gson.fromJson(data)`: 缺少类型信息

---

### Question 3: 二叉搜索树删除节点
**题目**: 给定二叉搜索树,根为Q。当前X的左子节点是M。如果用标准算法删除M节点,X的左子节点可能变成什么?

![image-20251110124143871](C:\Users\Stella\AppData\Roaming\Typora\typora-user-images\image-20251110124143871.png)

**正确答案**: ✓ Y

**解析**: 
二叉搜索树(BST)删除节点的标准算法:

**情况分析**:
M节点有两个子节点(R和V):

```
    X
   / \
  M   U
 / \
R   V
   / \
  Y   O
```

**删除有两个子节点的节点**:
1. 找到M的**中序后继**(右子树最小值)或**中序前驱**(左子树最大值)
2. 用后继/前驱替换M
3. 删除后继/前驱节点

**M的中序后继**:
- M的右子树(V)的最左节点
- V的左子节点是Y
- 因此后继是**Y**

**删除后结构**:
```
    X
   / \
  Y   U
 / \
R   V
     \
      O
```

所以X的新左子节点是**Y**。

---

### Question 4: Observer设计模式(UI输入)
**题目**: (同Lab4 Question 4)

**正确答案**: ✓ Observer (观察者模式)

**解析**: 同Lab4 Q4

---

### Question 5: Liskov替换原则违反
**题目**: 视频游戏引擎经常使用类似上图所示的类来表示二维空间中的位置。开发人员的任务是扩展此功能，编写一个 Vector3 类，通过添加 z 轴来表示三维空间中的位置。

 

开发者正在考虑将 Vector3 实现为 Vector2 的子类。为什么这样做会违反里氏替换原则？

```java
class Vector2 {

      private double x, y;

      public Vector2(double x, double y) {

           this.x = x;

           this.y = y;

      }

     

      // Returns the distance from the origin to this vector.

      public double getLength() {

           return Math.sqrt(x*x + y*y);

      }

     

      // Scales the vectors together componentwise

      public Vector2 dilate(Vector2 other) {

           return new Vector2(x*other.x, y*other.y);

      }

}
```

**正确答案**: ✓ Vector3的dilate方法实现不能满足Vector2的dilate方法接口,因为它的参数必须是Vector3类型

**解析**: 
**Liskov替换原则**(LSP): 子类对象必须能够替换父类对象而不破坏程序正确性

**问题分析**:

```java
class Vector2 {
    double x, y;
    
    public Vector2 dilate(Vector2 other) {
        return new Vector2(x*other.x, y*other.y);
    }
}

class Vector3 extends Vector2 {
    double z;
    
    @Override
    public Vector2 dilate(Vector2 other) {
        // 问题:other可能不是Vector3,没有z分量!
        // 如果强制转换,会在运行时失败
        return new Vector3(x*other.x, y*other.y, z*???);
    }
}
```

**LSP违反**:
1. **类型问题**: dilate期望Vector2参数,但Vector3需要Vector3参数
2. **行为不一致**: 不能安全地将Vector3当作Vector2使用
3. **前置条件加强**: 子类要求更严格的参数类型

**正确设计**:
- 不要让Vector3继承Vector2
- 或者使用泛型: `class Vector2<T extends Vector2<T>>`
- 或者使用组合而非继承

---

## Lab8-Quiz8

### Question 1: 路径覆盖与分支覆盖关系
**题目**: 通过仪器化,测试套件MyTests被发现达到70%的路径覆盖。关于MyTests的分支覆盖,我们知道什么?

**正确答案**: ✓ MyTests 的分支覆盖率可能低于 70%。

**解析**: 
覆盖率层次关系:

**从严格到宽松**:
```
语句覆盖 < 分支覆盖 < 路径覆盖
```

**关系说明**:
- **语句覆盖**: 每行代码至少执行一次
- **分支覆盖**: 每个判断的true/false分支都执行
- **路径覆盖**: 所有可能的执行路径都覆盖

**数学关系**:
```
路径覆盖 ≥ 分支覆盖 ≥ 语句覆盖
```

**因此**:
- 70%路径覆盖 → **至少**70%分支覆盖
- 可能有更高的分支覆盖(例如90%)
- 但路径覆盖总是最严格的

**示例**:
```java
if (a) {
    if (b) {
        // 路径1
    } else {
        // 路径2
    }
} else {
    // 路径3
}
```
- 4条路径: a&&b, a&&!b, !a, (还有一条隐含路径)
- 2个分支: a的true/false, b的true/false
- 覆盖50%路径 → 可能100%分支覆盖

---

### Question 2: OpenCSV注解
**题目**: 假设汽车销售平台使用的 CSV 文档格式如下：

```
id-brand-model-year-price
1234-"Mercedes-Benz"-"C-Class C300"-2018-50000
```
一位开发者想要使用 OpenCSV 将此数据序列化为 Car 类的实例。应该给字段“private int yearMade;”添加哪个注解？

**正确答案**: ✓ @CsvBindByPosition(position = 3)

**解析**: 
OpenCSV的两种绑定方式:

**1. 按位置绑定**:
```java
class Car {
    @CsvBindByPosition(position = 0)
    private int id;
    
    @CsvBindByPosition(position = 1)
    private String brand;
    
    @CsvBindByPosition(position = 2)
    private String model;
    
    @CsvBindByPosition(position = 3)  // 正确答案
    private int yearMade;
    
    @CsvBindByPosition(position = 4)
    private int price;
}
```

**2. 按名称绑定**(需要标题行):
```java
@CsvBindByName(column = "year")
private int yearMade;
```

**题目中的CSV**:
- 没有标题行
- year在第4个位置(索引3)
- 因此使用`@CsvBindByPosition(position = 3)`

---

### Question 3: AVL树旋转
**题目**:考虑以下 AVL 树，键按升序排列，根节点为 5。

当插入钥匙 6 时，为了平衡树，需要进行何种类型的旋转（如果有的话）？

```
    5
   / \
  2   8
     / \
    7   9
```

**正确答案**: ✓ Right-left (右左旋转)

**解析**: 
分析插入6后的情况:

**插入前**:
```
    5
   / \
  2   8
     / \
    7   9
```

**插入6后**:
```
    5 (bf=-2)
   / \
  2   8 (bf=+1)
     / \
    7   9
   /
  6
```

**平衡因子(Balance Factor)**:
- BF = 左子树高度 - 右子树高度
- 节点5: BF = 1 - 3 = -2 (不平衡!)
- 节点8: BF = 2 - 1 = +1

**旋转类型判断**:
- 失衡节点5的右子树(8)的左子树插入
- 这是**Right-Left**情况

**Right-Left旋转步骤**:
1. **右旋**节点8(围绕7):
```
    5
   / \
  2   7
       \
        8
         \
          9
```

2. **左旋**节点5(围绕7):
```
      7
     / \
    5   8
   /     \
  2       9
```

**AVL旋转类型总结**:
- **Left-Left** (LL): 左子树的左子树 → 右旋
- **Right-Right** (RR): 右子树的右子树 → 左旋
- **Left-Right** (LR): 左子树的右子树 → 左旋+右旋
- **Right-Left** (RL): 右子树的左子树 → 右旋+左旋 ✓

---

### Question 4: Facade设计模式
**题目**: 考虑在电子邮件应用程序中使用外观模式，该应用程序使用 SpamFilter 作为垃圾邮件包的外观。假设 SpamFilter 也位于 spam 包中，您预期 spam 包中的类会使用哪些访问修饰符？

**正确答案**: ✓ SpamFilter是public,其他类没有访问修饰符

**解析**: 
Facade模式的访问控制:

**设计原则**:
- **Facade类**: public(对外暴露)
- **包内类**: package-private(默认访问级别)

```java
// SpamFilter.java - public
package spam;

public class SpamFilter {  // 唯一的public类
    private BayesianAnalyzer analyzer;
    private BlacklistChecker blacklist;
    private HeuristicScanner scanner;
    
    public boolean isSpam(Email email) {
        // 协调内部类工作
        return analyzer.analyze(email) 
            || blacklist.contains(email.sender())
            || scanner.scan(email);
    }
}

// BayesianAnalyzer.java - package-private
package spam;

class BayesianAnalyzer {  // 无修饰符=package-private
    boolean analyze(Email email) {
        // 内部实现
    }
}
```

**优势**:
1. **封装**: 外部只能访问SpamFilter
2. **灵活性**: 可以自由修改内部类而不影响外部
3. **简化**: 用户只需学习一个接口

**错误选项**:
- 所有类public: 违反Facade的封装目的
- SpamFilter protected: 外部包无法访问
- 所有类无修饰符: SpamFilter也无法被外部访问

---

### Question 5: JML注解
**题目**: 

```java
/**

 * Interchanges the elements at indices i and j of the array.

 * (1)

 */

public static void <T> swap(T[] array, int i, int j) {

      T oldi = array[i];

      array[i] = array[j];

      array[j] = oldi;

}
```

方法swap交换数组中索引i和j的元素:

```java
public static void <T> swap(T[] array, int i, int j)
```
哪个JML注解的语法正确,可以给参数i非正式描述?

**正确答案**: ✓ @param i The first index.

**解析**: 
JML(Java Modeling Language)注解语法:

**正确的JML注解**:
```java
/**
 * Interchanges the elements at indices i and j of the array.
 * @param i The first index.
 * @param j The second index.
 */
public static <T> void swap(T[] array, int i, int j) {
    T oldi = array[i];
    array[i] = array[j];
    array[j] = oldi;
}
```

**JML形式化规范**:
```java
/*@ requires 0 <= i && i < array.length;
  @ requires 0 <= j && j < array.length;
  @ ensures array[i] == \old(array[j]);
  @ ensures array[j] == \old(array[i]);
  @*/
public static <T> void swap(T[] array, int i, int j)
```

**错误选项分析**:
- `@argument The first index = i`: argument不是标准Javadoc标签
- `@parameter "The first index" i`: 语法错误
- `@input i: "The first index."`: input不是标准标签

**Javadoc vs JML**:
- **Javadoc**: 文档注释(`@param`, `@return`, `@throws`)
- **JML**: 形式化规范(`@requires`, `@ensures`, `@invariant`)

---

## Lab9-Quiz9

### Question 1: JUnit参数化测试修复

**题目**: 
测试类代码片段:

```java
1  @RunWith(...)
2  public class MyAlgorithmTest {
3      
4      @Parameterized.Parameters
5      public static Collection<Object[]> data() {
6          return Arrays.asList(implementation1, implementation2, implementation3);
7      }
8      
9      @Parameterized.Parameter()
10     public IAlgorithm implementation;
11     
12     @Test(timeout = 1000)
13     public static void test() {
14         assertEquals(7, implementation.run(3));
15     }
16 }
```

存在一些问题阻止测试用例工作。**必须**修复哪些问题?

**正确答案**: 

- ✓ B: 第1行,runner必须改为参数化runner
- ✓ D: 第10行,测试函数应该是非静态的
- ✓ E: 第5行,每个实现应该放在自己的数组中

**解析**: 
JUnit参数化测试的正确写法:

**必须修复的问题**:

**1. 第1行 - Runner配置**:

```java
// ✗ 错误
@RunWith(...)

// ✓ 正确
@RunWith(Parameterized.class)
```

必须使用`Parameterized`运行器才能支持参数化测试。

**2. 第5行 - 数据格式**:

```java
// ✗ 错误
return Arrays.asList(implementation1, implementation2, implementation3);

// ✓ 正确
return Arrays.asList(
    new Object[] { implementation1 },
    new Object[] { implementation2 },
    new Object[] { implementation3 }
);
```

每个测试参数组必须是`Object[]`数组,即使只有一个参数。

**3. 第13行 - 测试方法必须是实例方法**:

```java
// ✗ 错误
public static void test() {

// ✓ 正确
public void test() {  // 非静态!
```

参数化测试会为每组参数创建测试类实例,测试方法必须是实例方法。

**完整的正确代码**:

```java
@RunWith(Parameterized.class)
public class MyAlgorithmTest {
    
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            { new AlgorithmImpl1() },
            { new AlgorithmImpl2() },
            { new AlgorithmImpl3() }
        });
    }
    
    @Parameterized.Parameter(0)
    public IAlgorithm implementation;
    
    @Test(timeout = 1000)
    public void test() {
        assertEquals(7, implementation.run(3));
    }
}
```

**其他选项为什么不需要修复**:

**A (第7行参数索引)**: 

- `@Parameterized.Parameter()`默认索引是0
- 如果只有一个参数,不写索引也可以
- 这不是**必须**修复的问题

**C (第4行 data函数)**: 

- 参数提供方法必须是`static`的 ✓
- 这个是正确的,不需要修改

**F (第8行后添加参数)**: 

- 题目中测试只需要一个参数(implementation)
- 不需要额外参数

**参数化测试的优势**:

```java
// 不使用参数化(代码重复)
@Test
public void testAlgorithm1() {
    IAlgorithm impl = new AlgorithmImpl1();
    assertEquals(7, impl.run(3));
}

@Test
public void testAlgorithm2() {
    IAlgorithm impl = new AlgorithmImpl2();
    assertEquals(7, impl.run(3));
}

// 使用参数化(简洁高效)
@RunWith(Parameterized.class)
public class MyAlgorithmTest {
    // 自动运行多个测试用例
}
```

---

### Question 2: 序列化格式文件大小比较

**题目**: 
LogEntry类包含一些String字段,但主要是数值基本类型(int和double)。实例通常需要约10KB的RAM。

哪种排序正确地描述了LogEntry存储在JSON、CSV、XML和Serialized格式中的文件大小比较?

**正确答案**: ✓ D: Serialized < CSV < JSON < XML

**解析**: 
不同序列化格式的文件大小分析:

**1. Serialized (最小)**:

```java
// Java原生序列化
class LogEntry implements Serializable {
    private int id;           // 4 bytes
    private double value;     // 8 bytes
    private String message;   // 变长
}

// 序列化后的二进制格式(紧凑)
// - 元数据开销: ~50-100 bytes
// - 数据本身: 最小化存储
// - 数字以二进制存储(int=4字节, double=8字节)
```

**特点**:

- ✅ 二进制格式,最紧凑
- ✅ 数字直接以二进制存储
- ✅ 字段名不重复存储
- ❌ 不可读,不跨语言

**2. CSV (第二小)**:

```csv
id,value,message
123,45.67,Error occurred
456,89.01,Warning message
```

**特点**:

- ✅ 纯文本,相对紧凑
- ✅ 只有一行表头(字段名只出现一次)
- ✅ 数字转为文本但较短
- ✅ 最小的标记开销
- ❌ 不支持嵌套结构

**文件大小估算**:

```
每条记录约: 30-50 bytes
- id: 3-5 chars
- value: 5-10 chars
- message: 10-30 chars
- 分隔符: 2 chars
```

**3. JSON (第三)**:

```json
{
  "id": 123,
  "value": 45.67,
  "message": "Error occurred"
}
```

**特点**:

- ✅ 可读性好
- ✅ 跨语言支持
- ❌ 字段名在每条记录中重复
- ❌ 额外的标点符号(括号、引号、逗号)
- ❌ 数字转为文本

**文件大小估算**:

```
每条记录约: 70-100 bytes
- 字段名重复: "id","value","message" = 20 chars
- 标点符号: {}""":, = 8 chars
- 数据值: 30-50 chars
- 缩进空格(如果格式化): 10-20 chars
```

**4. XML (最大)**:

```xml
<LogEntry>
  <id>123</id>
  <value>45.67</value>
  <message>Error occurred</message>
</LogEntry>
```

**特点**:

- ✅ 自描述性强
- ✅ 支持复杂嵌套
- ❌ 开始和结束标签都要写字段名
- ❌ 大量的尖括号
- ❌ 最冗长的语法

**文件大小估算**:

```
每条记录约: 120-150 bytes
- 开始标签: <id><value><message><LogEntry> = 40 chars
- 结束标签: </id></value></message></LogEntry> = 50 chars
- 数据值: 30-50 chars
```

**实际对比示例**:

假设一个LogEntry:

```java
id = 12345;
value = 98.765;
message = "System error";
```

**Serialized (约60 bytes)**:

```
[二进制头][class info][int:12345][double:98.765][String:"System error"]
```

**CSV (约35 bytes)**:

```
12345,98.765,System error
```

**JSON (约65 bytes)**:

```json
{"id":12345,"value":98.765,"message":"System error"}
```

**XML (约120 bytes)**:

```xml
<LogEntry><id>12345</id><value>98.765</value><message>System error</message></LogEntry>
```

**为什么这个顺序**:

| 格式       | 原因                                          |
| ---------- | --------------------------------------------- |
| Serialized | 二进制存储,最紧凑                             |
| CSV        | 文本但简洁,字段名只出现一次                   |
| JSON       | 每条记录都重复字段名 + 标点符号多             |
| XML        | 每个字段名出现两次(开始和结束标签) + 最多标点 |

**题目解释的补充**:
题目提到:

> Serialized has a larger overhead than CSV, but is more efficient when parsing numbers because binary is less wasteful than storing a decimal number in ASCII.

这解释了为什么Serialized整体更小:

- 虽然有序列化头的开销(~50-100 bytes)
- 但对于**数值密集**的数据,二进制存储远比ASCII高效
  - int 12345: 4 bytes (Serialized) vs 5 bytes (CSV)
  - double 98.765: 8 bytes (Serialized) vs 6 bytes (CSV)
- LogEntry"主要是数值基本类型",所以二进制优势明显

**结论**: Serialized < CSV < JSON < XML ✓

---

### Question 3: 红黑树验证

**题目**: 
考虑以下红黑树,节点按升序排序,根节点3,NULL节点隐藏。这是有效的红黑树吗?

```
      3(B)
     /    \
   1(B)   6(R)
         /    \
       4(B)   9(B)
             /
           7(R)
```

**正确答案**: ✓ B: 是的,所有红黑树条件都满足

**解析**: 
验证红黑树的5条性质:

**红黑树性质**:

1. 每个节点要么是红色,要么是黑色
2. 根节点是黑色
3. 所有叶子节点(NIL)是黑色
4. 红色节点的两个子节点都是黑色
5. 从任一节点到其叶子的所有路径都包含相同数量的黑色节点

**逐条验证**:

**性质1**: ✓ 每个节点都有颜色标记

**性质2**: ✓ 根节点3是黑色(B)

**性质3**: ✓ 所有NIL叶子节点是黑色(图中未显示但存在)

**性质4**: 检查所有红色节点的子节点

```
节点6(R):
  - 左子: 4(B) ✓
  - 右子: 9(B) ✓

节点7(R):
  - 左子: NIL(B) ✓
  - 右子: NIL(B) ✓

所有红色节点的子节点都是黑色 ✓
```

**性质5**: 检查黑色高度

```
完整的树结构(包含NIL):
        3(B)
       /    \
    1(B)    6(R)
    / \     /    \
  NIL NIL 4(B)   9(B)
          / \    /  \
        NIL NIL 7(R) NIL
                / \
              NIL NIL

路径1: 3(B) → 1(B) → NIL
       黑色节点: 3, 1, NIL = 3个

路径2: 3(B) → 6(R) → 4(B) → NIL
       黑色节点: 3, 4, NIL = 3个

路径3: 3(B) → 6(R) → 9(B) → 7(R) → NIL
       黑色节点: 3, 9, NIL = 3个

路径4: 3(B) → 6(R) → 9(B) → NIL(右)
       黑色节点: 3, 9, NIL = 3个
```

所有路径的黑色高度 = 3 ✓

**为什么不是其他选项**:

**A: "节点7是叶子节点但是红色"**:

- ❌ 错误理解
- 节点7不是叶子节点,它有两个NIL子节点
- 红黑树的"叶子节点"指NIL节点,不是指没有非NIL子节点的节点
- NIL节点才是真正的叶子,它们都是黑色的 ✓

**C: "根节点有平衡因子-2"**:

- ❌ 概念混淆
- 平衡因子是AVL树的概念,不是红黑树的
- 红黑树不要求严格平衡(高度差≤1)
- 红黑树只要求黑色高度相同

**D: "节点6是红色但有两个黑色子节点"**:

- ❌ 这实际上是**正确的**配置!
- 红色节点的子节点必须是黑色 ✓
- 节点6(R)有黑色子节点4(B)和9(B),完全符合规则

**红黑树 vs AVL树对比**:

| 特性     | 红黑树     | AVL树           |
| -------- | ---------- | --------------- |
| 平衡条件 | 黑高相同   | 高度差≤1        |
| 平衡程度 | 弱平衡     | 强平衡          |
| 树的高度 | ≤2log(n+1) | ≤1.44log(n+2)   |
| 插入旋转 | 最多2次    | 最多1次但回溯多 |
| 删除旋转 | 最多3次    | 可能多次        |
| 查找速度 | 较快       | 最快            |
| 插入删除 | 较快       | 较慢            |

**红黑树的实际高度**:

```
对于这棵树:
- 节点数: 6
- 黑高: 3
- 实际高度: 4

最大高度 ≤ 2 × 黑高 = 2 × 3 = 6
实际高度 4 < 6 ✓
```

**红黑树的应用**:

- Java: TreeMap, TreeSet
- C++ STL: map, set, multimap, multiset
- Linux内核: 进程调度CFS, 内存管理
- Nginx: Timer管理

**结论**: 这是一棵**有效的红黑树** ✓

---

### Question 4: DAO设计模式 UML图

**题目**: 
假设类MyDAO为类X实现了DAO设计模式。

在UML图中,哪个箭头正确显示了MyDAO和X之间的关系?

![image-20251110130235191](C:\Users\Stella\AppData\Roaming\Typora\typora-user-images\image-20251110130235191.png)



**正确答案**: ✓ a

**解析**: 
DAO(Data Access Object)设计模式的UML关系:

**DAO模式结构**:

```java
// 领域对象
class User {
    private int id;
    private String name;
    private String email;
}

// DAO接口
interface UserDAO {
    User findById(int id);
    List<User> findAll();
    void save(User user);
    void update(User user);
    void delete(int id);
}

// DAO实现
class UserDAOImpl implements UserDAO {
    private Connection connection;
    
    public User findById(int id) {
        // 数据库查询
        // 创建并返回User对象
    }
    
    public List<User> findAll() {
        // 返回多个User对象
    }
}
```

**MyDAO和X的关系分析**:

**1. 关系类型: 组合(Composition)**

```
MyDAO ◆────→ X
      1     *
```

**为什么是组合**:

- DAO **创建**领域对象(从数据库查询创建)
- DAO **管理**领域对象的生命周期
- DAO负责对象的持久化和删除
- 强"拥有"关系: DAO控制X的存在

**2. 多重性: 1 to many (*)**

- 一个DAO实例可以管理多个领域对象
- 例如: UserDAO可以创建和管理多个User实例

**完整的DAO模式UML**:

```
    <<interface>>
       DAO<T>
    +----------+
    | +find()  |
    | +save()  |
    | +delete()|
    +----------+
         △
         |
         | implements
         |
     MyDAO ◆────→ X
      1          *
      |
      | uses
      |
   Database
```

**为什么不是其他选项**:

**b: 聚合 (空心菱形)**:

```
MyDAO ◇────→ X
      1   1..*
```

- ❌ 聚合表示"has-a"关系,但生命周期独立
- DAO和领域对象不是独立的生命周期
- DAO创建、控制和销毁领域对象

**c: 依赖 (虚线箭头)**:

```
MyDAO ‥‥‥‥→ X
      1    0..*
```

- ❌ 依赖是最弱的关系,只表示"使用"
- DAO对X的关系比依赖强得多
- DAO不仅使用X,还创建和管理X

**d: 关联 (实心箭头)**:

```
MyDAO ─────→ X
      1   0..*
```

- △ 关联可以表示,但不够精确
- 没有体现DAO"拥有"和"管理"X的强关系
- 组合更准确地表达了这种关系

**组合 vs 聚合 vs 关联**:

| 关系 | 符号 | 生命周期     | 强度 | 例子           |
| ---- | ---- | ------------ | ---- | -------------- |
| 组合 | ◆    | 部分依赖整体 | 最强 | House-Room     |
| 聚合 | ◇    | 独立         | 较强 | Team-Player    |
| 关联 | →    | 独立         | 一般 | Student-Course |
| 依赖 | ‥→   | 临时         | 最弱 | Client-Service |

**DAO模式中的组合关系**:

```java
class UserDAO {
    public User findById(int id) {
        // 1. 从数据库查询数据
        ResultSet rs = statement.executeQuery(...);
        
        // 2. 创建领域对象(DAO创建并拥有)
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        
        // 3. 返回对象
        return user;
    }
    
    public void delete(int id) {
        // DAO控制对象的"死亡"
        statement.executeUpdate("DELETE FROM users WHERE id = ?", id);
        // 对应的User对象概念上被"删除"
    }
}
```

**DAO模式的优势**:

1. **分离关注点**: 业务逻辑与数据访问分离
2. **易于测试**: 可以mock DAO进行单元测试
3. **易于切换**: 可以换不同的数据库实现
4. **统一接口**: 提供标准的CRUD操作

**实际使用示例**:

```java
// Service层使用DAO
class UserService {
    private UserDAO userDAO;
    
    public User getUserById(int id) {
        return userDAO.findById(id);  // DAO创建并返回User
    }
    
    public void registerUser(User user) {
        // 业务逻辑
        if (userDAO.findByEmail(user.getEmail()) != null) {
            throw new DuplicateEmailException();
        }
        // DAO负责持久化
        userDAO.save(user);
    }
}
```

**结论**: MyDAO和X的关系是**组合 (1 to many)** ✓

---

### Question 5: 版权保护范围

**题目**: 
假设澳大利亚法律适用。

移动应用的哪些方面通常受到**版权**保护? 选择所有适用项。

**正确答案**: 

- ✓ B: 程序的图标 (The program's icon)
- ✓ D: 程序的视觉设计 (The program's visual design)
- ✓ F: 程序的代码库 (The program's codebase)

**解析**: 
版权法保护的范围分析:

**受版权保护的内容**:

**1. 图标 (Icon)** ✓

```
为什么受保护:
- 图标是原创的艺术作品
- 属于"图形作品"范畴
- 自动获得版权保护(无需注册)
```

**例子**:

- Apple的应用图标
- Android Material Design图标
- 自定义app图标设计

**保护内容**:

- 图标的具体设计
- 颜色搭配
- 图形元素组合

**2. 视觉设计 (Visual Design)** ✓

```
为什么受保护:
- UI界面布局是原创设计
- 视觉元素的排列组合
- 界面的"外观和感觉"(Look and Feel)
```

**包含**:

- 屏幕布局
- 按钮样式
- 颜色方案
- 字体选择和排版
- 动画效果

**案例**:

- Apple vs Samsung: iPhone界面设计侵权案
- 界面的"总体外观"受保护

**3. 代码库 (Codebase)** ✓

```
为什么受保护:
- 源代码是文学作品
- 二进制可执行文件也受保护
- 包括注释、文档
```

**保护范围**:

```java
// 这段代码受版权保护
public class UserManager {
    private List<User> users = new ArrayList<>();
    
    /**
     * 添加用户到系统
     * 这个注释也受保护
     */
    public void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
            notifyListeners(user);
        }
    }
}
```

**不受版权保护的内容**:

**A: 程序的名称** ❌

```
为什么不受版权保护:
- 名称太短,缺乏原创性
- 名称属于商标法保护范围
- 可以注册商标,但不受版权保护
```

**例子**:

- "Instagram" - 商标保护
- "Photoshop" - 商标保护
- "Microsoft Word" - 商标保护

**C: 程序的功能 (Features)** ❌

```
为什么不受版权保护:
- 功能是"想法"或"概念"
- 版权只保护表达,不保护想法
- 功能可能受专利保护
```

**例子**:

```
功能: "用户可以上传照片并添加滤镜"
- 这个功能概念不受版权保护 ❌
- 但实现这个功能的代码受保护 ✓
- 如果有独特的算法,可以申请专利 ✓
```

**E: 程序的算法** ❌

```
为什么不受版权保护:
- 算法是抽象的数学方法
- 版权不保护"过程"和"方法"
- 可能受专利法保护(如果足够创新)
```

**例子**:

```
算法: "使用快速排序对列表排序"
- 算法本身不受版权保护 ❌
- 但算法的代码实现受保护 ✓
- RSA加密算法曾受专利保护(现已过期)
```

**版权 vs 专利 vs 商标**:

| 保护类型     | 保护对象                   | 期限          | 例子               |
| ------------ | -------------------------- | ------------- | ------------------ |
| **版权**     | 原创表达(代码、设计、图标) | 作者终身+70年 | 源代码、UI设计     |
| **专利**     | 发明、算法、方法           | 20年          | 压缩算法、加密方法 |
| **商标**     | 品牌标识、名称             | 永久(需续展)  | App名称、Logo      |
| **商业秘密** | 保密信息                   | 保密期间      | 推荐算法、用户数据 |

**实际案例**:

**1. Oracle vs Google (API版权案)**:

```java
// Java API
public interface List<E> {
    boolean add(E e);
    E get(int index);
}
```

- 争议: API声明是否受版权保护
- 结果: 最高法院判决Google合理使用

**2. Apple vs Samsung (外观设计)**:

- 争议: iPhone的圆角矩形设计、图标网格
- 结果: Samsung赔偿5.39亿美元

**3. Tetris vs 克隆游戏**:

- 游戏规则不受保护 ❌
- 但特定的实现(代码、图形)受保护 ✓

**澳大利亚版权法特点**:

1. **自动保护**: 创作完成即获得版权,无需注册
2. **保护期限**: 作者死后70年
3. **Fair Dealing**: 合理使用例外(教育、评论等)

**开发者需要注意**:

```java
// ✗ 侵权
// 直接复制Instagram的UI设计
// 复制别人的源代码

// ✓ 合法
// 实现相同的功能(如照片分享)
// 使用开源库(遵守许可证)
// 参考界面但用自己的设计
```

**开源许可证**:

```
MIT License: 可以自由使用、修改、分发 ✓
GPL: 修改后必须开源
Apache 2.0: 需要保留版权声明
```

**总结**:
受版权保护的:

- ✓ 图标(艺术作品)
- ✓ 视觉设计(UI外观)
- ✓ 代码(文学作品)

不受版权保护的:

- ❌ 名称(商标保护)
- ❌ 功能(想法不受保护)
- ❌ 算法(可能专利保护)

## Lab10-Quiz10

### Question 1: 覆盖率类型关系
**题目**: 关于不同覆盖率类型之间的关系,哪个陈述是正确的?

**正确答案**: ✓ 路径覆盖包含分支覆盖

**解析**: 
覆盖率层次结构:

```
语句覆盖 ⊆ 判定覆盖 ⊆ 分支覆盖 ⊆ 路径覆盖
```

**详细解释**:

**1. 语句覆盖**(Statement Coverage):
- 每条语句至少执行一次
- 最弱的覆盖标准

**2. 判定覆盖**(Decision Coverage):
- 每个判定的结果(true/false)都要出现
- 等同于分支覆盖

**3. 分支覆盖**(Branch Coverage):
- 每个分支(if的true/false, switch的每个case)都执行
- 比语句覆盖强

**4. 条件覆盖**(Condition Coverage):
- 每个条件的true/false都要出现
- 与分支覆盖不同

**5. 路径覆盖**(Path Coverage):
- 所有可能的执行路径都覆盖
- 最强的覆盖标准

**示例**:
```java
if (a && b) {
    x = 1;
} else {
    x = 2;
}
```

| 测试用例 | 语句覆盖 | 分支覆盖 | 条件覆盖 | 路径覆盖 |
|---------|---------|---------|---------|---------|
| a=T,b=T | 50%     | 50%     | 50%     | 50%     |
| a=F,b=F | 100%    | 100%    | 50%     | 100%    |
| 需要2组  | ✓       | ✓       | ✗       | ✓       |

**关系总结**:
- 路径覆盖 **⊇** 分支覆盖 ✓(正确答案)
- 分支覆盖 **⊇** 条件覆盖 ✗(错误)
- 分支覆盖 **⊇** 语句覆盖 ✓

---

### Question 2: 流式读取(SAX)的优势
**题目**: 当输入文件大小极大时,推荐使用流式数据读取(如SAX)。我们期望在哪个方面看到使用流式系统的最大改进?

**正确答案**: ✓ 内存使用(即程序在读取文件时使用更少内存)

**解析**: 
DOM vs SAX的对比:

**DOM(Document Object Model)**:
```java
DocumentBuilder builder = ...;
Document doc = builder.parse(file);  // 整个文件加载到内存
NodeList nodes = doc.getElementsByTagName("item");
```

**SAX(Simple API for XML)**:
```java
SAXParser parser = ...;
parser.parse(file, new DefaultHandler() {
    @Override
    public void startElement(...) {
        // 边读边处理,不保存整个文档
    }
});
```

**内存使用对比**:
| 方法 | 10MB文件 | 1GB文件 |
|-----|---------|---------|
| DOM | ~30MB   | ~3GB    |
| SAX | ~1MB    | ~1MB    |

**SAX的优势**:
1. **内存效率**: 只存储当前处理的部分 ✓(主要优势)
2. **适合大文件**: 可以处理超过内存大小的文件
3. **速度快**: 不需要构建整个树结构

**劣势**:
- 只能单向遍历
- 不能随机访问
- 编程复杂度高

**其他选项错误的原因**:
- **并发性**: SAX和DOM的并发处理能力相似
- **运行时间**: 可能略快,但不是主要优势
- **正确性**: 两者都能正确解析,与格式无关

---

### Question 3: B树删除操作
**题目**: B树(阶数3),如果删除节点14,根节点及其子节点将包含哪些键?

![image-20251110124546245](C:\Users\Stella\AppData\Roaming\Typora\typora-user-images\image-20251110124546245.png)

```
       5|12
      / | \
    3   8|10  14
```

**正确答案**: ✓ (5)作为根,子节点(3)和(8,10,12)

**解析**: 
B树删除操作:

**初始状态**:
```
        5|12
       / | \
     3  8|10  14
```

**删除14后**:
- 14所在的叶子节点变空
- 需要重新平衡

**B树规则**:
- 阶数3: 每个节点1-2个键
- 根节点至少1个键
- 内部节点至少⌈3/2⌉-1=1个键

**重新平衡过程**:
1. 14被删除,右子树空
2. 从父节点借12下来
3. 合并10和12

**删除后结构**:
```
      5
     / \
    3   8|10|12
```

等等,这个答案有问题。让我重新分析...

**正确分析**:
删除14后:
```
        5|12
       / | \
     3  8|10  (空)
```

由于最右子节点为空,需要合并:
- 将12并入中间节点: 8|10|12
- 根节点只剩5

**最终结构**:
```
      5
     / \
    3   8|10|12
```

所以答案是: **(5)作为根,子节点(3)和(8,10,12)**

---

### Question 4: State设计模式 - 状态转换方法
**题目**: 在State设计模式中,某些基于状态的操作需要修改当前状态。有两种明显的方法:
- (i) 这些操作应返回新状态,然后用Context类分配
- (ii) 这些操作应执行类似Context.setState(State newState)的方法

哪种方法通常更好?为什么?

**正确答案**: ✓ (i),因为它减少了Context和State之间的耦合

**解析**: 
两种状态转换模式对比:

**方法(i) - 返回新状态**:
```java
abstract class State {
    abstract State handleEvent();
}

class LoggedOutState extends State {
    State handleEvent() {
        // 验证成功后
        return new LoggedInState();  // 返回新状态
    }
}

class Context {
    private State state;
    
    void event() {
        state = state.handleEvent();  // Context负责更新
    }
}
```

**方法(ii) - 调用Context.setState**:
```java
abstract class State {
    protected Context context;
    abstract void handleEvent();
}

class LoggedOutState extends State {
    void handleEvent() {
        context.setState(new LoggedInState());  // State直接修改Context
    }
}
```

**为什么(i)更好**:

**1. 降低耦合**:
- (i): State不需要知道Context的存在
- (ii): State必须持有Context引用

**2. 测试性**:
```java
// (i) 容易测试
@Test
void testStateTransition() {
    State state = new LoggedOutState();
    State newState = state.handleEvent();
    assertTrue(newState instanceof LoggedInState);
}

// (ii) 需要mock Context
@Test
void testStateTransition() {
    Context context = mock(Context.class);
    State state = new LoggedOutState(context);
    state.handleEvent();
    verify(context).setState(any(LoggedInState.class));
}
```

**3. 灵活性**:
- (i): Context可以决定是否接受状态转换
- (ii): State直接修改,Context失去控制

**4. 线程安全**:
- (i): 不可变性更容易实现线程安全
- (ii): 可能需要同步机制

**权衡**:
- (ii)在某些情况下更简单(State需要Context的其他功能时)
- 但总体上(i)是更好的设计

---

### Question 5: 递归下降解析器
**题目**: 给定文法:
```
sentence → noun_phrase verb noun_phrase
noun_phrase → adjective noun_phrase | noun | noun_phrase "that" verb noun_phrase
noun → "cats" | "dogs" | "rabbits" | "people"
adjective → "white" | "black" | "gray" | "brown"
verb → "like" | "chase" | "talk to" | "see"
```

使用tokeniser(忽略大小写和标点)和递归下降解析器,对输入"Dogs chase gray rabbits that talk to white rabbits."解析时,解析树根节点是什么?

**正确答案**: ✓ 一个Sentence,verb="chase"

**解析**: 
递归下降解析过程:

**输入**: Dogs chase gray rabbits that talk to white rabbits.

**词法分析(Tokenisation)**:
```
[dogs, chase, gray, rabbits, that, talk, to, white, rabbits]
```

**语法分析(Parsing)**:

1. **sentence** → noun_phrase verb noun_phrase

2. **第一个noun_phrase**: "Dogs"
   - noun_phrase → noun
   - noun → "dogs"

3. **verb**: "chase"
   - verb → "chase"

4. **第二个noun_phrase**: "gray rabbits that talk to white rabbits"
   - noun_phrase → adjective noun_phrase
   - adjective → "gray"
   - noun_phrase → noun_phrase "that" verb noun_phrase
     - noun_phrase → noun → "rabbits"
     - "that"
     - verb → "talk to"
     - noun_phrase → adjective noun_phrase
       - adjective → "white"
       - noun_phrase → noun → "rabbits"

**解析树**:
```
           Sentence (verb="chase")
          /    |    \
    NP(dogs) chase  NP(gray rabbits that...)
                    /      |      \
                 gray   rabbits   that...
```

**根节点**: Sentence,主要动词是"chase"

**关键理解**:
- 递归下降从最顶层规则开始(sentence)
- 根节点总是起始符号(sentence)
- 树的结构反映了文法规则的应用顺序

---

## 总结

这份文档整理了COMP2100/6442课程中Lab2到Lab10的所有Quiz题目和答案解析,涵盖了以下核心主题:

### 主要知识点
1. **Git版本控制**: 分支、提交、合并操作
2. **设计模式**: State、Factory、Observer、Iterator、Template、Facade
3. **数据结构**: BST、AVL树、B树、数组、链表
4. **测试**: JUnit、覆盖率(语句、分支、路径)、参数化测试
5. **序列化**: JSON(Gson)、XML、CSV(OpenCSV)
6. **面向对象原则**: Liskov替换原则、封装、耦合
7. **Android开发**: ConstraintLayout约束
8. **编译原理**: 递归下降解析器、文法

### 学习建议
- 理解概念比记忆答案更重要
- 通过画图帮助理解树结构和Git提交图
- 实践编写代码来理解设计模式
- 关注设计原则背后的"为什么"

祝你考试顺利!🎓
