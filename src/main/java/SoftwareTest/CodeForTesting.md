# 单元测试编写流程与示例
---

## 一、总体流程

编写单元测试时，一般遵循以下五个步骤：

| 步骤 | 目标 | 关键点 |
|------|------|--------|
| **1. 明确测试目标** | 理解要验证的函数或类的行为 | 输出正确值？抛异常？状态改变？ |
| **2. 划分输入场景** | 设计不同输入类别 | 正常、边界、异常三类 |
| **3. 准备测试数据** | 构造测试所需对象或参数 | new、mock、随机或固定值 |
| **4. 执行被测方法** | 调用要测试的方法 | 注意捕获异常和返回值 |
| **5. 验证结果（断言）** | 用断言验证输出是否符合预期 | assertEquals / assertTrue / assertThrows 等 |

---

## 二、编写规范

1. **每个测试方法只测一个逻辑点**  
   命名清晰：

2. **保持测试独立性**  
   不依赖上一个测试的结果，不修改共享状态。

3. **可重复**  
   每次运行结果相同，不依赖外部系统（网络、文件等）。

4. **覆盖正常和异常路径**  
   正常值、边界值、非法输入都要测。

5. **保持简洁**  
   测试逻辑清晰、一眼看懂。复杂逻辑可提取 helper。

---
# 写代码时如何选择和编写测试类型（实战指导）

---

## 一、总体思路：边写代码边测试

> 你不是“写完再测”，而是**写一块逻辑，就测试一块逻辑**。
>
> **测试类型的选择，取决于代码的职责范围**。

---

## 二、判断逻辑：写什么测试

| 你写的代码类型 | 应该写的测试 | 目的 |
|----------------|---------------|------|
| 独立算法或函数 | **单元测试（Unit Test）** | 验证函数逻辑正确性 |
| 有分支 / 条件判断 | **分支覆盖测试** | 每个条件路径都要执行到 |
| 对外提供接口（API） | **集成测试（Integration Test）** | 验证模块交互或外部服务调用 |
| 涉及输入输出（文件、网络） | **黑盒功能测试** | 检查功能输出是否符合需求 |
| 依赖其他模块或数据库 | **Mock 测试** | 模拟外部依赖，单独验证逻辑 |
| 修复 bug | **回归测试（Regression Test）** | 确保旧功能仍正常 |
| 写框架层逻辑（例如模式代码） | **行为测试 / 模式验证** | 检查是否符合设计模式预期行为 |

---

## 三、测试编写流程（实战）

### Step 1：写功能前先写最小测试（TDD 风格）
```java
@Test
public void testAddNumbers() {
    assertEquals(5, MathUtil.add(2, 3));
}
```
→ 这时 `MathUtil.add()` 还不存在；编译不通过。  
→ 你实现它后再跑测试，看到绿灯。

**优点**：防止逻辑偏离需求。

---

### Step 2：边写逻辑边补分支测试

```java
public int classify(int n) {
    if (n < 0) return -1;
    if (n == 0) return 0;
    return 1;
}
```

```java
@Test
public void testClassify() {
    assertEquals(-1, classify(-5)); // 分支1
    assertEquals(0, classify(0));   // 分支2
    assertEquals(1, classify(10));  // 分支3
}
```

---

### Step 3：当方法调用其他模块时，用 Mock

```java
@Mock private PaymentGateway gateway;

@Test
public void testOrderPayment() {
    when(gateway.charge(anyDouble())).thenReturn(true);
    boolean result = orderService.payOrder(100);
    assertTrue(result);
}
```

**意义：**  
不依赖外部服务（如支付接口），但能验证逻辑是否正确调用。

---

### Step 4：黑盒测试（不看内部实现）

```java
@Test
public void testHashingBoxNormal() throws Exception {
    Thing t = new Thing(Material.SILVER, 3, Shape.SQUARE, "Comp2100");
    int expected = 4905;  // 根据题意手算
    assertEquals(expected, hashingBox.hash(t));
}
```

---

### Step 5：边界测试

```java
@Test
public void testSizeBoundary() throws Exception {
    Thing t1 = new Thing(Material.GOLD, 1, Shape.TRIANGLE, "aaaa");
    Thing t2 = new Thing(Material.GOLD, 31, Shape.TRIANGLE, "aaaa");
    assertDoesNotThrow(() -> hashingBox.hash(t1));
    assertDoesNotThrow(() -> hashingBox.hash(t2));
}
```

---

### Step 6：异常测试（期望抛出）

```java
@Test(expected = ComponentOutOfRangeException.class)
public void testInvalidInput() throws Exception {
    Thing t = new Thing(Material.STEEL, 32, Shape.CIRCLE, "abc");
    hashingBox.hash(t);
}
```

---

### Step 7：组合逻辑（集成层）

```java
@Test
public void testOrderFlow() {
    Cart cart = new Cart();
    cart.addItem("apple", 2);
    Order order = new Order(cart);
    assertEquals(200, order.checkout());
}
```

---

### Step 8：回归测试

每次修改函数逻辑时：
1. 跑旧测试；
2. 确认未失败；
3. 新增覆盖新行为的测试。

---

## 四、代码层测试策略

| 场景 | 推荐测试类型 | 核心断言点 |
|------|---------------|-------------|
| 算法 / 数据结构 | 单元测试 + 边界测试 | 结果是否正确 |
| 分支 / 条件逻辑 | 单元测试 | 各分支都覆盖 |
| 工具类（纯函数） | 单元测试 | 输入→输出 |
| 异常处理 | 异常测试 | 抛出异常类型 |
| I/O 操作 | 黑盒测试 | 文件 / 输出一致 |
| 调用外部接口 | Mock + 集成测试 | 调用次数、参数 |
| 组合模块逻辑 | 集成测试 | 模块交互正确性 |
| 性能关键逻辑 | 性能测试 | 执行时间或资源消耗 |

---

## 五、命名规范

| 代码类 | 测试类 |
|--------|--------|
| `UserService` | `UserServiceTest` |
| `MathUtil` | `MathUtilTest` |
| `HashingBox` | `HashingBoxTest` |

---

## 六、测试选择流程图

```
            ┌────────────────────────────┐
            │ 你写了新函数 / 类？         │
            └──────────────┬─────────────┘
                           │
                           ▼
                  ┌─────────────────┐
                  │ 是否独立逻辑？   │
                  └──────┬──────────┘
                         │是
                         ▼
               写单元测试（Unit Test）
                         │否
                         ▼
        ┌────────────────────────────┐
        │ 是否依赖其他模块/外部系统？│
        └──────────────┬─────────────┘
                       │是
                       ▼
             写集成测试 + Mock 测试
                       │否
                       ▼
             写黑盒测试（功能对比）
```

---

## 七、总结一句话

> **写测试的核心不是“多”，而是“刚好能证明代码没错”。**
> - 写算法 → 单元测试
> - 写接口 → 集成测试
> - 写流程 → 系统测试
> - 修 bug → 回归测试
> - 调依赖 → Mock
> - 写边界 → 边界测试
> - 抛异常 → 异常测试

---

## 八、推荐实践顺序

1. 写一个失败测试（红灯）
2. 实现功能让测试通过（绿灯）
3. 重构代码保持通过
4. 加入边界和异常测试
5. 集成 CI/CD 自动执行所有测试

---

**总结：**
> - 函数越独立 → 用单元测试
> - 模块越复杂 → 用集成测试
> - 系统越大 → 用系统测试
> - 改动越多 → 加回归测试








## 五、如何在项目中组合使用

### 典型项目测试金字塔：






## 以Practice1为例

### 一、题目本质
我们有一个抽象类：

```java
abstract class HashingBox {
int hash(Thing thing);
}
```

它有 13 个不同的实现类 (HashingBox0 ~ HashingBox12)。
你的任务是写 JUnit 参数化测试，来验证：
哪些实现是正确的；
哪些实现有 bug。
也就是说：
不需要自己实现算法，只要通过测试，让正确的类全部通过，错误的类至少失败一个测试。

### 二、核心目标

我们要测的是每个 hash() 方法是否符合官方算法规范。
因此测试思路就是：

用一系列精心设计的 Thing 输入对象，让错误实现必定算错。



# 以Practice1为例HashingBox 测试设计思路与实现指南

## 一、任务概述

题目给出了一个抽象类 `HashingBox`，以及 13 个不同实现类（`HashingBox0` ~ `HashingBox12`）。  
你的任务是通过 **JUnit 参数化测试** 来判断哪些实现是正确的，哪些实现存在错误。

测试方法必须满足：
- 正确实现通过所有测试；
- 错误实现至少失败一个测试。

**核心考点：**  
黑盒测试设计（Black-box Testing）+ 边界分析（Boundary Value Analysis）+ 参数化测试。

---

## 二、正确算法逻辑（标准参考）

### 1. `sizeHash`
取 **小于等于 size 的最大质数 (prime)**。  
特例：`size == 1` 时，`sizeHash = 2`。

示例：
| size | sizeHash |
|------|-----------|
| 1 | 2 |
| 3 | 3 |
| 6 | 5 |
| 31 | 31 |

---

### 2. `nameHash`
规则如下：
1. 只取前 6 个字符；
2. 只计算字母（A–Z, a–z）的 ASCII；
3. 计算公式：nameHash = (sum(字母ASCII前6个)) * sizeHash + name.length()

示例：
name = "Comp2100"
前6字符 = "Comp21"
字母部分 = C(67) + o(111) + m(109) + p(112) = 399
nameHash = 399 * 3 + 8 = 1205


---

### 3. `materialShapeHash`
取对应枚举的数值相乘：

| Material | 值 | Shape | 值 |
|-----------|----|--------|----|
| STEEL | 1018 | CIRCLE | 1 |
| SILVER | 925 | SEMICIRCLE | 2 |
| GOLD | 999 | TRIANGLE | 3 |
|  |  | SQUARE | 4 |
|  |  | PENTAGON | 5 |

示例：SILVER + SQUARE => 925 * 4 = 3700

---

### 4. 最终结果
hash = nameHash + materialShapeHash

---

### 5. 异常情况
若 `size < 0` 或 `size > 31`  
则抛出 `ComponentOutOfRangeException`。

---

## 三、黑盒测试设计思路

我们不知道实现细节，只能从输入和输出判断算法行为是否符合规范。

**目标：**
- 选取最少的测试用例；
- 覆盖所有可能出错的逻辑分支；
- 让错误实现暴露问题。

---

## 四、测试思维流程

| 步骤 | 测试目的 | 样例输入 | 预期结果 |
|------|-----------|-----------|-----------|
| 1 | 验证标准计算是否正确 | `("Comp2100", 3, SILVER, SQUARE)` | `4905` |
| 2 | 特例 size==1 | `("aaaa", 1, GOLD, TRIANGLE)` | `3777` |
| 3 | 最大值 size==31 | `("abc", 31, STEEL, PENTAGON)` | 正确 prime=31 |
| 4 | 非法 size>31 | `("abc", 32, STEEL, CIRCLE)` | 抛异常 |
| 5 | 含非字母字符 | `("a1b2c3", 7, SILVER, TRIANGLE)` | 只算字母 |
| 6 | 名字超6个字母 | `("abcdefghij", 10, GOLD, SQUARE)` | 只算前6个字母 |
| 7 | 特殊组合 | `(“abc”, 5, SILVER, SEMICIRCLE)` | 不应为0 |

---

## 五、参数化测试实现结构

使用 **JUnit Parameterized Test**  
自动对 13 个实现类重复运行相同测试。

```java
@RunWith(Parameterized.class)
public class Q2Test {

    @Parameters
    public static Iterable<HashingBox> getImplementations() {
        return Arrays.asList(
            new HashingBox0(), new HashingBox1(), new HashingBox2(),
            new HashingBox3(), new HashingBox4(), new HashingBox5(),
            new HashingBox6(), new HashingBox7(), new HashingBox8(),
            new HashingBox9(), new HashingBox10(), new HashingBox11(), new HashingBox12()
        );
    }

    @Parameter
    public HashingBox hashingBox;

    // 1. 标准功能测试
    @Test(timeout = 1000)
    public void testNormalCase() throws ComponentOutOfRangeException {
        Thing t = new Thing(Material.SILVER, 3, Shape.SQUARE, "Comp2100");
        assertEquals(4905, hashingBox.hash(t));
    }

    // 2. 特例 size == 1
    @Test(timeout = 1000)
    public void testSizeOneCase() throws ComponentOutOfRangeException {
        Thing t = new Thing(Material.GOLD, 1, Shape.TRIANGLE, "aaaa");
        assertEquals(3777, hashingBox.hash(t));
    }

    // 3. 上界 size == 31
    @Test(timeout = 1000)
    public void testSizeBoundary() throws ComponentOutOfRangeException {
        Thing t = new Thing(Material.STEEL, 31, Shape.PENTAGON, "abc");
        int sizeHash = 31;
        int nameHash = (97 + 98 + 99) * 31 + 3; // abc
        int expected = nameHash + 1018 * 5;
        assertEquals(expected, hashingBox.hash(t));
    }

    // 4. 非法输入
    @Test(timeout = 1000, expected = ComponentOutOfRangeException.class)
    public void testOutOfRange() throws ComponentOutOfRangeException {
        Thing t = new Thing(Material.STEEL, 32, Shape.CIRCLE, "abc");
        hashingBox.hash(t);
    }

    // 5. 字符过滤测试
    @Test(timeout = 1000)
    public void testIgnoreNonLetters() throws ComponentOutOfRangeException {
        Thing t = new Thing(Material.SILVER, 7, Shape.TRIANGLE, "a1b2c3");
        int sizeHash = 7;
        int nameHash = (97 + 98 + 99) * 7 + 6;
        int expected = nameHash + 925 * 3;
        assertEquals(expected, hashingBox.hash(t));
    }

    // 6. 只取前6字符
    @Test(timeout = 1000)
    public void testLongNameLimit6() throws ComponentOutOfRangeException {
        Thing t = new Thing(Material.GOLD, 10, Shape.SQUARE, "abcdefghij");
        int sizeHash = 7;
        int nameHash = (97 + 98 + 99 + 100 + 101 + 102) * 7 + 10;
        int expected = nameHash + 999 * 4;
        assertEquals(expected, hashingBox.hash(t));
    }

    // 7. 特殊组合 (SILVER + SEMICIRCLE)
    @Test(timeout = 1000)
    public void testSilverSemicircleNotZero() throws ComponentOutOfRangeException {
        Thing t = new Thing(Material.SILVER, 5, Shape.SEMICIRCLE, "abc");
        int sizeHash = 5;
        int nameHash = (97 + 98 + 99) * 5 + 3;
        int expected = nameHash + 925 * 2;
        assertEquals(expected, hashingBox.hash(t));
    }
}
```
