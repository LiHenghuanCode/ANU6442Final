# ANU COMP6442 Software Construction - 完整考试复习问答集
# Comprehensive Exam Review Q&A Guide

**课程代码 Course Code:** COMP6442 / COMP2100  
**编制日期 Compiled:** November 2025  
**适用对象 Target:** ANU Computing Students preparing for Software Construction exams

---

## 📋 目录 Table of Contents

1. [设计模式 Design Patterns](#1-设计模式-design-patterns)
2. [结对编程 Pair Programming](#2-结对编程-pair-programming)
3. [语法解析 Parsing](#3-语法解析-parsing)
4. [持久化数据 Persistent Data](#4-持久化数据-persistent-data)
5. [软件测试 Testing](#5-软件测试-testing)
6. [树形结构  Tree Structures](#6-树形结构  Tree Structures)
7. [重构与SOLID原则 Refactoring & SOLID Principles](#7-重构与SOLID原则 Refactoring & SOLID Principles)
8. [UML建模 UML Modeling](#8-uml建模-uml-modeling)
9. [软件专利 Software Patents](#9-软件专利-software-patents)
10. [Android 应用开发基础 (Android App Development Fundamentals)](#10-Android 应用开发基础 (Android App Development Fundamentals))

---

## 1. 设计模式 Design Patterns

### Q1.1: 什么是Singleton模式？请解释其实现方式及在多线程环境下的注意事项。
### What is the Singleton Pattern? Explain its implementation and considerations in multi-threaded environments.

**中文答案：**

**定义：** Singleton（单例模式）是一种创建型设计模式，确保一个类只有一个实例，并提供一个全局访问点。

---

**English Answer:**

**Definition:** The Singleton pattern is a creational design pattern that ensures a class has only one instance and provides a global point of access to it.

---

### Q1.2: 解释Factory Method模式与Abstract Factory模式的区别，并给出实际应用场景。
### Explain the difference between Factory Method and Abstract Factory patterns, with practical use cases.

**中文答案：**

**Factory Method（工厂方法模式）：**

**定义：** 定义一个创建对象的接口，让子类决定实例化哪个类。

**应用场景：**
- 日志记录器（选择文件日志、数据库日志、控制台日志）
- 支付系统（创建不同的支付方式：信用卡、PayPal、支付宝）

---

**Abstract Factory（抽象工厂模式）：**

**定义：** 提供一个接口，用于创建相关或依赖对象的家族，而不需要指定具体类。

**应用场景：**
- UI主题系统（创建一整套风格一致的组件：按钮、文本框、滚动条）
- 跨平台应用（为Windows、Mac、Linux创建相应的UI组件家族）
- 数据库访问层（为MySQL、PostgreSQL、Oracle创建相应的连接、命令、数据读取器）

---

**核心区别对比表：**

| 特性 | Factory Method | Abstract Factory |
|------|----------------|------------------|
| 复杂度 | 较简单 | 较复杂 |
| 对象数量 | 创建单一产品 | 创建产品家族 |
| 继承层次 | 通过子类创建对象 | 组合多个工厂方法 |
| 扩展性 | 添加新产品需新子类 | 添加新产品家族需新工厂 |
| 使用场景 | 单一产品类型选择 | 需要一组相关产品 |

---

**English Answer:**

**Factory Method Pattern:**

**Definition:** Defines an interface for creating an object, but lets subclasses decide which class to instantiate.

**Use Cases:**
- Logging systems (file logger, database logger, console logger)
- Payment systems (creating different payment methods: credit card, PayPal, Alipay)

---

**Abstract Factory Pattern:**

**Definition:** Provides an interface for creating families of related or dependent objects without specifying their concrete classes.

**Use Cases:**
- UI theme systems (creating consistent sets of components: buttons, textboxes, scrollbars)
- Cross-platform applications (creating UI component families for Windows, Mac, Linux)
- Database access layers (creating connections, commands, readers for MySQL, PostgreSQL, Oracle)

---

**Key Differences:**

| Feature | Factory Method | Abstract Factory |
|---------|----------------|------------------|
| Complexity | Simpler | More complex |
| Object Count | Single product | Product families |
| Inheritance | Creates through subclasses | Composes multiple factory methods |
| Extensibility | New product needs new subclass | New family needs new factory |
| Use Case | Single product type selection | Related product groups needed |

### Q1.3: 什么是装饰器（Decorator）模式？请解释其结构与优点，并给出实际应用示例。

What is the Decorator Pattern? Explain its structure and advantages, and provide practical examples.

**中文答案：**

定义：
 装饰器模式是一种结构型设计模式，用于在不修改原有类的情况下，动态地给对象添加新的功能。它通过将对象封装在一个“装饰器类”中，实现行为的扩展。

**应用示例：**
 在咖啡订单系统中，装饰器可以为基础咖啡动态添加糖、奶、巧克力等配料，而无需修改 `BasicCoffee` 类。

**优点：**

1. 符合开闭原则（对扩展开放，对修改关闭）；
2. 可灵活组合多个装饰器；
3. 避免了继承体系的复杂膨胀。

**English Answer:**

**Definition:**
 The Decorator pattern is a structural design pattern that allows adding new functionality to objects dynamically without modifying their structure. It wraps the original object inside a decorator class to extend behavior.

**Use Case:**
 In a coffee-ordering system, decorators can add milk, sugar, or chocolate to a base coffee dynamically without altering the `BasicCoffee` class.

**Advantages:**

1. Follows the Open/Closed Principle;
2. Enables flexible composition of multiple behaviors;
3. Avoids creating numerous subclasses for each variation.

### Q1.4: 什么是外观（Facade）模式？请解释其作用与典型应用场景。

What is the Facade Pattern? Explain its purpose and typical use cases.

**中文答案：**

定义：
 外观模式是一种结构型设计模式，它为复杂的子系统提供一个统一的简化接口。外观隐藏了系统的复杂性，让客户端可以更容易地使用系统。

**应用场景：**

- 计算机启动系统（封装 CPU、Memory、Disk 操作）
- 数据库连接封装类（简化连接与关闭逻辑）
- Web API 封装（统一调用多个微服务接口）

**优点：**

1. 降低系统复杂性；
2. 让客户端与子系统解耦；
3. 改善可维护性与可读性。

**English Answer:**

**Definition:**
 The Facade pattern is a structural design pattern that provides a simplified interface to a complex subsystem. It hides the complexity of interactions among multiple classes.

**Use Case:**

- Booting a computer (encapsulating CPU, Memory, Disk operations)
- Database connection management
- Unified API gateway for microservices

**Advantages:**

1. Simplifies client interaction with complex systems;
2. Promotes loose coupling between client and subsystem;
3. Improves readability and maintainability.

### Q1.5: 什么是观察者（Observer）模式？请说明其结构与典型应用。

What is the Observer Pattern? Describe its structure and common applications.

**中文答案：**

定义：
 观察者模式是一种行为型设计模式，它定义了对象间的一对多依赖关系，当一个对象状态改变时，所有依赖它的对象都会自动收到通知并更新。

**应用场景：**

- 新闻订阅系统
- GUI 事件监听（如按钮点击事件）
- 股票价格监控系统

**English Answer:**

**Definition:**
 The Observer pattern defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified automatically.

**Use Cases:**

- News subscription and notification systems
- Event listeners in GUIs
- Real-time data dashboards

### Q1.6: 请解释策略模式的概念及其应用场景。

What is the Strategy Pattern and where is it applied?

**中文答案：**

定义：
 策略模式定义了一系列算法，并将每个算法封装起来，使它们可以互换使用，从而使算法的变化独立于使用算法的客户。

**应用场景：**

- 支付系统（信用卡、PayPal、支付宝）
- 数据压缩算法切换（ZIP、RAR、GZIP）
- 路径规划（最短路径、避开拥堵路线）

------

**English Answer:**

**Definition:**
 The Strategy pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable so that algorithm behavior can change at runtime.

**Use Cases:**

- Payment processing systems
- Data compression algorithms
- Dynamic route selection in navigation apps

### Q1.7: 什么是模板方法模式？请结合实例说明其优势。

What is the Template Method Pattern? Illustrate its advantages with an example.

**中文答案：**

定义：
 模板方法模式在基类中定义算法的骨架，并允许子类在不改变结构的情况下重新定义算法的某些步骤。

**应用场景：**

- 文件解析流程（读取→处理→保存）
- 报告生成（加载数据→分析→输出）
- 游戏AI（模板控制整体流程，不同子类定义策略）

------

**English Answer:**

**Definition:**
 The Template Method pattern defines the skeleton of an algorithm in a base class and lets subclasses override specific steps without changing the algorithm’s structure.

**Use Cases:**

- File processing pipelines
- Report generation systems
- Game AI decision workflows

### Q1.8: 解释状态模式及其使用场景。

Explain the State Pattern and give examples of where it’s useful.

**中文答案：**

定义：
 状态模式允许对象在其内部状态变化时改变行为，看起来就像修改了其类一样。

**应用场景：**

- 媒体播放器播放状态（播放、暂停、停止）
- 文档编辑器（草稿、发布、归档状态）
- 游戏角色状态管理（攻击、防御、休息）

------

**English Answer:**

**Definition:**
 The State pattern allows an object to change its behavior when its internal state changes, appearing as if the object’s class has changed.

**Use Cases:**

- Media players (play, pause, stop)
- Document lifecycle management
- Game character states

### Q1.9: 请解释迭代器模式的作用及实现原理。

What is the Iterator Pattern and how does it work?

**中文答案：**

定义：
 迭代器模式提供一种统一的方式来顺序访问集合中的元素，而无需暴露集合的内部结构。

**应用场景：**

- 自定义集合类遍历
- 树、图遍历统一接口
- Java 集合框架中 `Iterator` 的标准实现

------

**English Answer:**

**Definition:**
 The Iterator pattern provides a standard way to traverse elements in a collection without exposing its internal structure.

**Use Cases:**

- Iterating over custom data structures
- Standardized traversal across lists, trees, and graphs
- Basis of Java’s built-in `Iterator` and `Iterable` interfaces

---

## 2. 结对编程 Pair Programming

### Q2.1: 解释Pair Programming中Driver和Navigator的角色及其责任。描述Ping-Pong编程风格。
### Explain the roles and responsibilities of Driver and Navigator in Pair Programming. Describe the Ping-Pong programming style.

**中文答案：**

**角色定义：**

**1. Driver（驾驶员）：**
- **主要职责：** 实际编写代码，操作键盘和鼠标
- **工作重点：** 
  - 专注于当前正在实现的具体代码细节
  - 处理语法和实现逻辑
  - 将想法转化为可执行代码
- **思维层次：** 战术层面（tactical level）
- **类比：** 就像开车的司机，专注于当前的路况和操作

**2. Navigator（导航员）：**
- **主要职责：** 指导整体方向，思考大局
- **工作重点：**
  - 审查代码质量和逻辑
  - 考虑潜在问题和边界情况
  - 规划下一步要做什么
  - 查找文档和资源
  - 思考设计模式和架构决策
- **思维层次：** 战略层面（strategic level）
- **类比：** 就像副驾驶的导航员，看地图、规划路线、注意路标

---

**Ping-Pong编程风格（结合TDD）：**

**定义：** 一种结合测试驱动开发（TDD）的结对编程方式，两人交替扮演Driver角色。

**工作流程：**

```
步骤1: Person A（Driver）写一个失败的测试用例
       Person B（Navigator）指导测试设计

步骤2: Person B（Driver）实现代码使测试通过
       Person A（Navigator）审查实现

步骤3: Person B（可选）重构代码
       Person A（Navigator）提供重构建议

步骤4: 角色互换，返回步骤1
```

**English Answer:**

**Role Definitions:**

**1. Driver:**
- **Primary Responsibility:** Writes the actual code, operates keyboard and mouse
- **Focus Areas:**
  - Concentrates on specific code details being implemented
  - Handles syntax and implementation logic
  - Converts ideas into executable code
- **Thinking Level:** Tactical level
- **Analogy:** Like the driver of a car, focused on current road conditions and operations

**2. Navigator:**
- **Primary Responsibility:** Guides overall direction, thinks big picture
- **Focus Areas:**
  - Reviews code quality and logic
  - Considers potential issues and edge cases
  - Plans next steps
  - Searches documentation and resources
  - Thinks about design patterns and architectural decisions
- **Thinking Level:** Strategic level
- **Analogy:** Like the co-pilot navigator, reading maps, planning routes, watching for signs

---

**Ping-Pong Programming Style (with TDD):**

**Definition:** A pair programming approach combined with Test-Driven Development (TDD), where two people alternate as Driver.

**Workflow:**

```
Step 1: Person A (Driver) writes a failing test
        Person B (Navigator) guides test design

Step 2: Person B (Driver) implements code to pass the test
        Person A (Navigator) reviews implementation

Step 3: Person B (Optional) refactors the code
        Person A (Navigator) provides refactoring suggestions

Step 4: Swap roles, return to Step 1
```

---

## 3. 语法解析 Parsing

### Q3.1: 解释递归下降解析器（Recursive Descent Parser）的工作原理。给出一个解析算术表达式的完整示例。
### Explain how a Recursive Descent Parser works. Provide a complete example of parsing arithmetic expressions.

**中文答案：**

**递归下降解析器定义：**

递归下降解析器是一种自顶向下的解析技术，其中每个非终结符对应一个解析函数。这些函数相互递归调用，以匹配输入序列。

---

**语法规则示例（算术表达式）：**

```
Expression ::= Term (('+' | '-') Term)*
Term       ::= Factor (('*' | '/') Factor)*
Factor     ::= NUMBER | '(' Expression ')'
```

**解释：**
- `Expression`：由一个或多个`Term`组成，通过`+`或`-`连接
- `Term`：由一个或多个`Factor`组成，通过`*`或`/`连接
- `Factor`：可以是数字或括号括起来的表达式

这种层次结构自动处理了运算符优先级（乘除优先于加减）。

---

**完整Java实现：**

```java
import java.util.*;

public class RecursiveDescentParser {
    
    // Token类型
    enum TokenType {
        NUMBER, PLUS, MINUS, MULTIPLY, DIVIDE, LPAREN, RPAREN, EOF
    }
    
    // Token类
    static class Token {
        TokenType type;
        String value;
        
        Token(TokenType type, String value) {
            this.type = type;
            this.value = value;
        }
    }
    
    // 词法分析器（Tokenizer）
    static class Tokenizer {
        private String input;
        private int pos = 0;
        
        Tokenizer(String input) {
            this.input = input.replaceAll("\\s+", ""); // 移除空格
        }
        
        Token nextToken() {
            if (pos >= input.length()) {
                return new Token(TokenType.EOF, "");
            }
            
            char current = input.charAt(pos);
            
            // 数字
            if (Character.isDigit(current)) {
                StringBuilder number = new StringBuilder();
                while (pos < input.length() && Character.isDigit(input.charAt(pos))) {
                    number.append(input.charAt(pos++));
                }
                return new Token(TokenType.NUMBER, number.toString());
            }
            
            // 操作符和括号
            pos++;
            switch (current) {
                case '+': return new Token(TokenType.PLUS, "+");
                case '-': return new Token(TokenType.MINUS, "-");
                case '*': return new Token(TokenType.MULTIPLY, "*");
                case '/': return new Token(TokenType.DIVIDE, "/");
                case '(': return new Token(TokenType.LPAREN, "(");
                case ')': return new Token(TokenType.RPAREN, ")");
                default: throw new RuntimeException("Unexpected character: " + current);
            }
        }
    }
    
    // 解析器
    static class Parser {
        private Tokenizer tokenizer;
        private Token currentToken;
        
        Parser(String input) {
            this.tokenizer = new Tokenizer(input);
            this.currentToken = tokenizer.nextToken();
        }
        
        // 移动到下一个token
        private void consume(TokenType expected) {
            if (currentToken.type == expected) {
                currentToken = tokenizer.nextToken();
            } else {
                throw new RuntimeException("Expected " + expected + " but got " + currentToken.type);
            }
        }
        
        // Expression ::= Term (('+' | '-') Term)*
        public int parseExpression() {
            int result = parseTerm();
            
            while (currentToken.type == TokenType.PLUS || currentToken.type == TokenType.MINUS) {
                TokenType op = currentToken.type;
                consume(currentToken.type);
                int right = parseTerm();
                
                if (op == TokenType.PLUS) {
                    result += right;
                } else {
                    result -= right;
                }
            }
            
            return result;
        }
        
        // Term ::= Factor (('*' | '/') Factor)*
        private int parseTerm() {
            int result = parseFactor();
            
            while (currentToken.type == TokenType.MULTIPLY || currentToken.type == TokenType.DIVIDE) {
                TokenType op = currentToken.type;
                consume(currentToken.type);
                int right = parseFactor();
                
                if (op == TokenType.MULTIPLY) {
                    result *= right;
                } else {
                    if (right == 0) {
                        throw new RuntimeException("Division by zero");
                    }
                    result /= right;
                }
            }
            
            return result;
        }
        
        // Factor ::= NUMBER | '(' Expression ')'
        private int parseFactor() {
            if (currentToken.type == TokenType.NUMBER) {
                int value = Integer.parseInt(currentToken.value);
                consume(TokenType.NUMBER);
                return value;
            } else if (currentToken.type == TokenType.LPAREN) {
                consume(TokenType.LPAREN);
                int result = parseExpression();  // 递归调用
                consume(TokenType.RPAREN);
                return result;
            } else {
                throw new RuntimeException("Unexpected token: " + currentToken.type);
            }
        }
    }
    
    // 测试
    public static void main(String[] args) {
        String[] testCases = {
            "2 + 3 * 4",           // 期望: 14
            "(2 + 3) * 4",         // 期望: 20
            "10 / 2 - 3",          // 期望: 2
            "2 * (3 + 4) - 5 / 5"  // 期望: 13
        };
        
        for (String expr : testCases) {
            Parser parser = new Parser(expr);
            int result = parser.parseExpression();
            System.out.println(expr + " = " + result);
        }
    }
}
```

---

**输出：**
```
2 + 3 * 4 = 14
(2 + 3) * 4 = 20
10 / 2 - 3 = 2
2 * (3 + 4) - 5 / 5 = 13
```

---

**工作原理详解：**

1. **词法分析（Tokenization）：** 
   - 将输入字符串分解为tokens
   - 例如：`"2 + 3"` → `[NUMBER(2), PLUS, NUMBER(3)]`

2. **语法分析（Parsing）：**
   - `parseExpression()`处理加减法（最低优先级）
   - `parseTerm()`处理乘除法（中等优先级）
   - `parseFactor()`处理数字和括号（最高优先级）

3. **递归调用：**
   - 遇到括号时，`parseFactor()`递归调用`parseExpression()`
   - 这自然处理了嵌套结构

4. **运算符优先级：**
   - 通过解析函数的层次结构自动实现
   - 越底层的函数优先级越高

---

**关键概念：**

- **LL(1)解析：** 从左到右读取，最左推导，向前看1个token
- **预测性解析：** 通过查看当前token决定使用哪个产生式
- **错误处理：** 当遇到意外token时抛出异常

---

**English Answer:**

**Recursive Descent Parser Definition:**

A recursive descent parser is a top-down parsing technique where each non-terminal in the grammar corresponds to a parsing function. These functions recursively call each other to match the input sequence.

---

**Grammar Rules Example (Arithmetic Expressions):**

```
Expression ::= Term (('+' | '-') Term)*
Term       ::= Factor (('*' | '/') Factor)*
Factor     ::= NUMBER | '(' Expression ')'
```

**Explanation:**
- `Expression`: Consists of one or more `Terms` connected by `+` or `-`
- `Term`: Consists of one or more `Factors` connected by `*` or `/`
- `Factor`: Can be a number or an expression in parentheses

This hierarchical structure automatically handles operator precedence (multiplication/division before addition/subtraction).

---

**How It Works:**

1. **Tokenization:**
   - Breaks input string into tokens
   - Example: `"2 + 3"` → `[NUMBER(2), PLUS, NUMBER(3)]`

2. **Parsing:**
   - `parseExpression()` handles addition/subtraction (lowest precedence)
   - `parseTerm()` handles multiplication/division (medium precedence)
   - `parseFactor()` handles numbers and parentheses (highest precedence)

3. **Recursive Calls:**
   - When encountering parentheses, `parseFactor()` recursively calls `parseExpression()`
   - This naturally handles nested structures

4. **Operator Precedence:**
   - Automatically implemented through parsing function hierarchy
   - Lower-level functions have higher precedence

---

**Key Concepts:**

- **LL(1) Parsing:** Left-to-right reading, Leftmost derivation, 1 token lookahead
- **Predictive Parsing:** Decides which production to use by looking at current token
- **Error Handling:** Throws exceptions when unexpected tokens are encountered



---

## 4. 持久化数据 Persistent Data

### Q4.1: 比较JSON、XML和Java序列化三种持久化方式的优缺点。什么时候应该选择哪种方式？
### Compare JSON, XML, and Java Serialization for data persistence. When should each be used?

**中文答案：**

**详细对比表：**

| 特性 | JSON | XML | Java Serialization |
|------|------|-----|-------------------|
| **可读性** | 高（简洁） | 中（冗长） | 低（二进制） |
| **文件大小** | 小 | 大 | 中 |
| **解析速度** | 快 | 慢 | 最快（同语言） |
| **数据类型支持** | 有限 | 有限 | 完整Java类型 |
| **跨语言** | ✅ 优秀 | ✅ 优秀 | ❌ 仅Java |
| **模式验证** | 有（JSON Schema） | 强（XSD） | 无 |
| **安全性** | 安全 | 较安全 | ⚠️ 存在反序列化漏洞 |
| **版本兼容** | 灵活 | 灵活 | 脆弱 |

---

**1. JSON (JavaScript Object Notation)**

**优点：**
- ✅ 轻量级，文件小
- ✅ 人类可读性强
- ✅ 几乎所有编程语言都支持
- ✅ 与Web API完美集成
- ✅ 解析速度快

**缺点：**
- ❌ 不支持注释
- ❌ 数据类型有限（无日期类型）
- ❌ 不支持循环引用

**示例代码：**

```java
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

class Student {
    private String name;
    private int age;
    private String[] courses;
    
    Student(String name, int age, String[] courses) {
        this.name = name;
        this.age = age;
        this.courses = courses;
    }
    
    // Getters and setters
}

public class JSONExample {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        // 序列化
        Student student = new Student("Heng", 22, new String[]{"COMP6442", "COMP3900"});
        String json = gson.toJson(student);
        System.out.println("JSON:\n" + json);
        
        // 反序列化
        Student restored = gson.fromJson(json, Student.class);
        System.out.println("Name: " + restored.name);
    }
}
```

**JSON输出：**
```json
{
  "name": "Heng",
  "age": 22,
  "courses": [
    "COMP6442",
    "COMP3900"
  ]
}
```

**适用场景：**
- ✅ Web API数据交换
- ✅ 配置文件（简单结构）
- ✅ NoSQL数据库（MongoDB）
- ✅ 前后端数据传输

---

**2. XML (eXtensible Markup Language)**

**优点：**
- ✅ 强大的模式验证（XSD）
- ✅ 支持命名空间
- ✅ 支持注释
- ✅ 层次结构清晰
- ✅ 工业标准（SOAP, RSS）

**缺点：**
- ❌ 冗长，文件大
- ❌ 解析速度慢
- ❌ 语法复杂

**示例代码：**

```java
import javax.xml.bind.*;
import javax.xml.bind.annotation.*;
import java.io.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
class Student {
    @XmlElement
    private String name;
    
    @XmlElement
    private int age;
    
    @XmlElementWrapper(name = "courses")
    @XmlElement(name = "course")
    private String[] courses;
    
    // 默认构造函数（JAXB需要）
    public Student() {}
    
    public Student(String name, int age, String[] courses) {
        this.name = name;
        this.age = age;
        this.courses = courses;
    }
}

public class XMLExample {
    public static void main(String[] args) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        // 序列化
        Student student = new Student("Heng", 22, new String[]{"COMP6442", "COMP3900"});
        marshaller.marshal(student, System.out);
        
        // 反序列化
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Student restored = (Student) unmarshaller.unmarshal(new StringReader(xmlString));
    }
}
```

**XML输出：**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<student>
  <name>Heng</name>
  <age>22</age>
  <courses>
    <course>COMP6442</course>
    <course>COMP3900</course>
  </courses>
</student>
```

**适用场景：**
- ✅ 企业应用集成（SOAP）
- ✅ 复杂配置文件（Maven pom.xml）
- ✅ 文档标记（XHTML）
- ✅ 需要严格验证的数据

---

**3. Java Serialization**

**优点：**
- ✅ 完整保留Java对象状态
- ✅ 支持复杂对象图
- ✅ 支持循环引用
- ✅ 序列化/反序列化最快（同JVM）

**缺点：**
- ❌ 仅限Java环境
- ❌ 二进制格式不可读
- ❌ **安全漏洞风险**（反序列化攻击）
- ❌ 版本兼容性差
- ❌ 文件较大

**示例代码：**

```java
import java.io.*;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;  // 版本控制
    
    private String name;
    private int age;
    private transient String password;  // transient: 不序列化
    
    Student(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }
    
    // Getters and setters
}

public class SerializationExample {
    public static void main(String[] args) {
        try {
            // 序列化
            Student student = new Student("Heng", 22, "secret123");
            ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("student.ser")
            );
            out.writeObject(student);
            out.close();
            
            // 反序列化
            ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("student.ser")
            );
            Student restored = (Student) in.readObject();
            in.close();
            
            System.out.println("Name: " + restored.name);
            System.out.println("Password: " + restored.password);  // null（因为transient）
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

**关键字说明：**
- `Serializable`：标记接口，表示类可被序列化
- `serialVersionUID`：版本号，用于版本控制
- `transient`：标记字段不被序列化（如密码、临时数据）

**安全风险示例：**

```java
// ⚠️ 危险：反序列化未知来源的数据
ObjectInputStream in = new ObjectInputStream(untrustedSource);
Object obj = in.readObject();  // 可能执行恶意代码！
```

**适用场景：**
- ✅ JVM间通信（RMI）
- ✅ 会话状态保存
- ✅ 深拷贝对象
- ❌ **不推荐用于持久化存储**

---

**决策树：**

```
需要跨语言支持？
├─ 是 → 数据简单？
│         ├─ 是 → JSON ✅
│         └─ 否 → XML
└─ 否 → 仅Java环境？
          ├─ 是 → 需要完整对象状态？
          │         ├─ 是 → Java Serialization（谨慎使用）
          │         └─ 否 → JSON/XML
          └─ 否 → JSON
```

---

**最佳实践建议：**

1. **优先选择JSON：**
   - 现代应用的首选
   - 除非有特殊需求

2. **谨慎使用Java Serialization：**
   - 避免序列化来自不可信源的数据
   - 使用`SerialKiller`等库增强安全性
   - 考虑替代方案（如Protobuf）

3. **XML适用于：**
   - 遗留系统集成
   - 需要严格模式验证
   - 复杂文档结构

---

**English Answer:**

**Detailed Comparison:**

| Feature | JSON | XML | Java Serialization |
|---------|------|-----|-------------------|
| **Readability** | High (concise) | Medium (verbose) | Low (binary) |
| **File Size** | Small | Large | Medium |
| **Parse Speed** | Fast | Slow | Fastest (same language) |
| **Data Type Support** | Limited | Limited | Full Java types |
| **Cross-language** | ✅ Excellent | ✅ Excellent | ❌ Java only |
| **Schema Validation** | Yes (JSON Schema) | Strong (XSD) | None |
| **Security** | Safe | Relatively safe | ⚠️ Deserialization vulnerabilities |
| **Version Compatibility** | Flexible | Flexible | Fragile |

---

**1. JSON (JavaScript Object Notation)**

**Advantages:**
- ✅ Lightweight, small files
- ✅ Highly human-readable
- ✅ Supported by almost all programming languages
- ✅ Perfect integration with Web APIs
- ✅ Fast parsing

**Disadvantages:**
- ❌ No comment support
- ❌ Limited data types (no native date type)
- ❌ No circular reference support

**Use Cases:**
- ✅ Web API data exchange
- ✅ Configuration files (simple structures)
- ✅ NoSQL databases (MongoDB)
- ✅ Frontend-backend data transmission

---

**2. XML (eXtensible Markup Language)**

**Advantages:**
- ✅ Powerful schema validation (XSD)
- ✅ Namespace support
- ✅ Comment support
- ✅ Clear hierarchical structure
- ✅ Industry standard (SOAP, RSS)

**Disadvantages:**
- ❌ Verbose, large files
- ❌ Slow parsing
- ❌ Complex syntax

**Use Cases:**
- ✅ Enterprise application integration (SOAP)
- ✅ Complex configuration files (Maven pom.xml)
- ✅ Document markup (XHTML)
- ✅ Data requiring strict validation

---

**3. Java Serialization**

**Advantages:**
- ✅ Fully preserves Java object state
- ✅ Supports complex object graphs
- ✅ Supports circular references
- ✅ Fastest serialization/deserialization (same JVM)

**Disadvantages:**
- ❌ Java environment only
- ❌ Binary format not human-readable
- ❌ **Security vulnerability risks** (deserialization attacks)
- ❌ Poor version compatibility
- ❌ Larger files

**Keyword Explanations:**
- `Serializable`: Marker interface indicating class can be serialized
- `serialVersionUID`: Version number for version control
- `transient`: Marks field as not serializable (e.g., passwords, temp data)

**Security Risk Example:**

```java
// ⚠️ Dangerous: Deserializing data from untrusted sources
ObjectInputStream in = new ObjectInputStream(untrustedSource);
Object obj = in.readObject();  // May execute malicious code!
```

**Use Cases:**
- ✅ JVM-to-JVM communication (RMI)
- ✅ Session state saving
- ✅ Deep copying objects
- ❌ **Not recommended for persistent storage**

---

**Decision Tree:**

```
Need cross-language support?
├─ Yes → Data simple?
│         ├─ Yes → JSON ✅
│         └─ No → XML
└─ No → Java-only environment?
          ├─ Yes → Need complete object state?
          │         ├─ Yes → Java Serialization (use cautiously)
          │         └─ No → JSON/XML
          └─ No → JSON
```

---

**Best Practice Recommendations:**

1. **Prefer JSON:**
   - First choice for modern applications
   - Unless special requirements exist

2. **Use Java Serialization Cautiously:**
   - Avoid serializing data from untrusted sources
   - Use libraries like `SerialKiller` for enhanced security
   - Consider alternatives (like Protobuf)

3. **XML Suitable For:**
   - Legacy system integration
   - Strict schema validation required
   - Complex document structures

---

## 5. 软件测试 Testing

### Q5.1: 解释Black-box Testing和White-box Testing的区别。给出JUnit测试示例。
### Explain the difference between Black-box and White-box Testing. Provide JUnit test examples.

**中文答案：**

**对比表：**

| 特性 | Black-box Testing | White-box Testing |
|------|-------------------|-------------------|
| **中文名称** | 黑盒测试 | 白盒测试 |
| **关注点** | 输入输出关系 | 内部代码逻辑 |
| **测试依据** | 需求规格说明 | 代码结构 |
| **测试者** | 不需要了解代码 | 需要了解代码 |
| **覆盖目标** | 功能覆盖 | 代码覆盖（语句、分支、路径） |
| **测试阶段** | 系统测试、验收测试 | 单元测试、集成测试 |
| **优点** | 从用户角度、发现需求偏差 | 发现逻辑错误、提高覆盖率 |
| **缺点** | 无法保证代码覆盖 | 可能忽略需求错误 |

---

**Black-box Testing（黑盒测试）**

**定义：** 不关注内部实现，只测试输入和预期输出。

**测试技术：**
1. **等价类划分（Equivalence Partitioning）**
2. **边界值分析（Boundary Value Analysis）**
3. **决策表测试（Decision Table Testing）**

**示例：测试一个年龄验证函数**

```java
// 被测试的类
public class AgeValidator {
    /**
     * 验证年龄是否有效
     * 规则：年龄必须在 0-150 之间
     * @param age 年龄
     * @return true if valid, false otherwise
     */
    public boolean isValidAge(int age) {
        return age >= 0 && age <= 150;
    }
    
    /**
     * 获取年龄类别
     * 0-12: 儿童
     * 13-17: 青少年
     * 18-59: 成年人
     * 60+: 老年人
     */
    public String getAgeCategory(int age) {
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Invalid age");
        }
        if (age <= 12) return "儿童";
        if (age <= 17) return "青少年";
        if (age <= 59) return "成年人";
        return "老年人";
    }
}
```

**Black-box测试代码（使用JUnit 5）：**

```java
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AgeValidatorBlackBoxTest {
    private AgeValidator validator;
    
    @BeforeEach
    public void setUp() {
        validator = new AgeValidator();
    }
    
    // 等价类划分测试
    @Test
    @DisplayName("测试有效年龄范围")
    public void testValidAge() {
        // 有效等价类：0-150
        assertTrue(validator.isValidAge(0));
        assertTrue(validator.isValidAge(50));
        assertTrue(validator.isValidAge(150));
    }
    
    @Test
    @DisplayName("测试无效年龄范围")
    public void testInvalidAge() {
        // 无效等价类：< 0 和 > 150
        assertFalse(validator.isValidAge(-1));
        assertFalse(validator.isValidAge(151));
        assertFalse(validator.isValidAge(200));
    }
    
    // 边界值分析测试
    @Test
    @DisplayName("测试边界值")
    public void testBoundaryValues() {
        // 下边界
        assertFalse(validator.isValidAge(-1));  // 边界外
        assertTrue(validator.isValidAge(0));    // 边界上
        assertTrue(validator.isValidAge(1));    // 边界内
        
        // 上边界
        assertTrue(validator.isValidAge(149));  // 边界内
        assertTrue(validator.isValidAge(150));  // 边界上
        assertFalse(validator.isValidAge(151)); // 边界外
    }
    
    // 测试年龄分类功能（基于规格说明）
    @Test
    @DisplayName("测试年龄分类")
    public void testAgeCategories() {
        assertEquals("儿童", validator.getAgeCategory(0));
        assertEquals("儿童", validator.getAgeCategory(12));
        assertEquals("青少年", validator.getAgeCategory(13));
        assertEquals("青少年", validator.getAgeCategory(17));
        assertEquals("成年人", validator.getAgeCategory(18));
        assertEquals("成年人", validator.getAgeCategory(59));
        assertEquals("老年人", validator.getAgeCategory(60));
        assertEquals("老年人", validator.getAgeCategory(150));
    }
    
    // 测试异常情况
    @Test
    @DisplayName("测试无效输入抛出异常")
    public void testInvalidInputThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            validator.getAgeCategory(-1);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            validator.getAgeCategory(200);
        });
    }
}
```

---

**White-box Testing（白盒测试）**

**定义：** 基于代码内部结构设计测试，确保代码逻辑被充分测试。

**覆盖标准：**
1. **语句覆盖（Statement Coverage）：** 每条语句至少执行一次
2. **分支覆盖（Branch Coverage）：** 每个条件的true和false分支都被执行
3. **路径覆盖（Path Coverage）：** 所有可能的执行路径都被测试

**示例：测试一个复杂计算器**

```java
public class Calculator {
    /**
     * 计算函数，包含多个分支
     */
    public int calculate(int a, int b, String operation) {
        int result = 0;
        
        if (operation == null) {  // 分支1
            throw new IllegalArgumentException("Operation cannot be null");
        }
        
        switch (operation) {
            case "add":           // 分支2
                result = a + b;
                if (result > 100) {  // 分支3
                    System.out.println("Warning: Result exceeds 100");
                }
                break;
            case "subtract":      // 分支4
                result = a - b;
                break;
            case "multiply":      // 分支5
                result = a * b;
                break;
            case "divide":        // 分支6
                if (b == 0) {     // 分支7
                    throw new ArithmeticException("Division by zero");
                }
                result = a / b;
                break;
            default:              // 分支8
                throw new IllegalArgumentException("Unknown operation");
        }
        
        return result;
    }
}
```

**White-box测试代码：**

```java
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

public class CalculatorWhiteBoxTest {
    private Calculator calculator;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
        System.setOut(new PrintStream(outputStream));
    }
    
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }
    
    // 测试所有分支
    
    @Test
    @DisplayName("分支1: Null操作")
    public void testNullOperation() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculate(5, 3, null);
        });
    }
    
    @Test
    @DisplayName("分支2: 加法 - 结果不超过100")
    public void testAdditionUnder100() {
        assertEquals(8, calculator.calculate(5, 3, "add"));
        // 验证没有警告信息
        assertEquals("", outputStream.toString());
    }
    
    @Test
    @DisplayName("分支2+3: 加法 - 结果超过100（触发警告）")
    public void testAdditionOver100() {
        assertEquals(150, calculator.calculate(100, 50, "add"));
        // 验证警告信息被输出
        assertTrue(outputStream.toString().contains("Warning: Result exceeds 100"));
    }
    
    @Test
    @DisplayName("分支4: 减法")
    public void testSubtraction() {
        assertEquals(2, calculator.calculate(5, 3, "subtract"));
        assertEquals(-2, calculator.calculate(3, 5, "subtract"));
    }
    
    @Test
    @DisplayName("分支5: 乘法")
    public void testMultiplication() {
        assertEquals(15, calculator.calculate(5, 3, "multiply"));
        assertEquals(0, calculator.calculate(0, 5, "multiply"));
    }
    
    @Test
    @DisplayName("分支6: 除法 - 正常情况")
    public void testDivision() {
        assertEquals(2, calculator.calculate(6, 3, "divide"));
        assertEquals(5, calculator.calculate(15, 3, "divide"));
    }
    
    @Test
    @DisplayName("分支6+7: 除法 - 除数为零")
    public void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> {
            calculator.calculate(5, 0, "divide");
        });
    }
    
    @Test
    @DisplayName("分支8: 未知操作")
    public void testUnknownOperation() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculate(5, 3, "modulo");
        });
    }
    
    // 路径覆盖测试
    @Test
    @DisplayName("路径覆盖: 所有操作类型")
    public void testAllOperationPaths() {
        // 确保所有switch分支都被执行
        calculator.calculate(5, 3, "add");
        calculator.calculate(5, 3, "subtract");
        calculator.calculate(5, 3, "multiply");
        calculator.calculate(6, 3, "divide");
    }
}
```

**代码覆盖率报告示例：**

```
Calculator.java:
- Statement Coverage: 95% (19/20 lines)
- Branch Coverage: 100% (8/8 branches)
- Path Coverage: 87% (7/8 paths)

未覆盖代码:
- Line 15: result > 100 的边界情况
```

---

**选择测试类型的决策树：**

```
你是否了解代码内部实现？
├─ 是 → White-box Testing
│         ├─ 目标：提高代码覆盖率
│         └─ 关注：分支、路径、边界条件
└─ 否 → Black-box Testing
          ├─ 目标：验证功能需求
          └─ 关注：输入输出、等价类、边界值
```

---

**English Answer:**

**Comparison Table:**

| Feature | Black-box Testing | White-box Testing |
|---------|-------------------|-------------------|
| **Chinese Name** | 黑盒测试 | 白盒测试 |
| **Focus** | Input-output relationships | Internal code logic |
| **Test Basis** | Requirements specification | Code structure |
| **Tester** | Doesn't need code knowledge | Needs code knowledge |
| **Coverage Goal** | Functional coverage | Code coverage (statement, branch, path) |
| **Test Phase** | System testing, acceptance testing | Unit testing, integration testing |
| **Advantages** | User perspective, finds requirement gaps | Finds logic errors, improves coverage |
| **Disadvantages** | Cannot guarantee code coverage | May miss requirement errors |

---

**Black-box Testing**

**Definition:** Doesn't focus on internal implementation, only tests inputs and expected outputs.

**Testing Techniques:**
1. **Equivalence Partitioning**
2. **Boundary Value Analysis**
3. **Decision Table Testing**

---

**White-box Testing**

**Definition:** Designs tests based on internal code structure, ensuring code logic is thoroughly tested.

**Coverage Criteria:**
1. **Statement Coverage:** Every statement executed at least once
2. **Branch Coverage:** Both true and false branches of every condition executed
3. **Path Coverage:** All possible execution paths tested



------

## 6. 树形结构  Tree Structures 

### Q6.2: 解释B-Tree的结构和操作。B-Tree与AVL树有什么区别？

### Explain B-Tree structure and operations. What are the differences between B-Tree and AVL trees?

**中文答案：**

**B-Tree定义：**

B-Tree是一种自平衡的多路搜索树，主要用于数据库和文件系统中。与AVL树不同，B-Tree的每个节点可以有多个键和多个子节点。

---

**B-Tree属性（以m阶B-Tree为例）：**

1. **节点结构：**
   - 每个节点最多有m个子节点
   - 每个节点（除根节点外）最少有⌈m/2⌉个子节点
   - 根节点至少有2个子节点（如果不是叶子节点）

2. **键的数量：**
   - 每个节点最多有m-1个键
   - 每个节点（除根节点外）最少有⌈m/2⌉-1个键
   - 所有叶子节点在同一层

3. **键的顺序：**
   - 节点内的键按升序排列
   - k₁ < k₂ < ... < kₙ

4. **子节点性质：**
   - 对于键kᵢ，左子树所有键 < kᵢ，右子树所有键 > kᵢ

---

**B-Tree示例（3阶B-Tree，每个节点最多2个键）：**

```
            [30]
           /    \
      [10,20]   [40,50]
     /  |   \   /  |   \
   [5] [15] [25] [35] [45] [55]
```

---

**B-Tree vs AVL Tree对比：**

| 特性             | B-Tree                 | AVL Tree               |
| ---------------- | ---------------------- | ---------------------- |
| 每个节点子节点数 | 多个（m个）            | 最多2个                |
| 每个节点键数     | 多个（m-1个）          | 1个                    |
| 树的高度         | 更矮（O(logₘ n)）      | 较高（O(log₂ n)）      |
| 适用场景         | 磁盘存储、数据库       | 内存中快速查找         |
| 平衡条件         | 所有叶子同层           | 左右子树高度差≤1       |
| 旋转操作         | 分裂和合并             | 单旋转或双旋转         |
| 磁盘I/O          | 优化的（一次读多个键） | 较多（每次读一个节点） |

---

**B-Tree的分裂过程图示：**

```
插入前（节点满了）:
[10, 20, 30, 40, 50]

分裂后:
      [30]          ← 中间键上移
     /    \
[10, 20]  [40, 50]  ← 分成两个节点
```

---

**为什么B-Tree适合磁盘存储？**

1. **减少磁盘I/O：** 每个节点存储多个键，一次磁盘读取获取更多信息
2. **树高度低：** 相同数量的数据，B-Tree高度比二叉树低得多
3. **顺序访问友好：** 节点内键有序，便于范围查询

**示例计算：**

- 存储100万个键
- AVL树（二叉）高度 ≈ log₂(1,000,000) ≈ 20层 → 需要20次磁盘读取
- B-Tree (t=100)高度 ≈ log₁₀₀(1,000,000) ≈ 3层 → 只需3次磁盘读取

---

**English Answer:**

**B-Tree Definition:**

A B-Tree is a self-balancing multi-way search tree primarily used in databases and file systems. Unlike AVL trees, each node in a B-Tree can have multiple keys and multiple children.

---

**B-Tree Properties (for m-order B-Tree):**

1. **Node Structure:**
   - Each node has at most m children
   - Each node (except root) has at least ⌈m/2⌉ children
   - Root has at least 2 children (if not a leaf)

2. **Number of Keys:**
   - Each node has at most m-1 keys
   - Each node (except root) has at least ⌈m/2⌉-1 keys
   - All leaf nodes are at the same level

3. **Key Ordering:**
   - Keys within a node are in ascending order
   - k₁ < k₂ < ... < kₙ

4. **Child Properties:**
   - For key kᵢ, all keys in left subtree < kᵢ, all keys in right subtree > kᵢ

---

**B-Tree Example (3-order B-Tree, max 2 keys per node):**

```
            [30]
           /    \
      [10,20]   [40,50]
     /  |   \   /  |   \
   [5] [15] [25] [35] [45] [55]
```

---

**B-Tree vs AVL Tree Comparison:**

| Feature             | B-Tree                         | AVL Tree                          |
| ------------------- | ------------------------------ | --------------------------------- |
| Children per node   | Multiple (m)                   | At most 2                         |
| Keys per node       | Multiple (m-1)                 | 1                                 |
| Tree height         | Shorter (O(logₘ n))            | Taller (O(log₂ n))                |
| Use case            | Disk storage, databases        | Fast lookup in memory             |
| Balance condition   | All leaves at same level       | Left-right subtree height diff ≤1 |
| Rotation operations | Split and merge                | Single or double rotation         |
| Disk I/O            | Optimized (read multiple keys) | More (read one node at a time)    |

[Complete Java implementation same as Chinese version above]

---

**Why B-Tree is Suitable for Disk Storage:**

1. **Reduces Disk I/O:** Each node stores multiple keys, one disk read gets more information
2. **Low Tree Height:** For same amount of data, B-Tree height is much lower than binary tree
3. **Sequential Access Friendly:** Keys within nodes are ordered, convenient for range queries

**Example Calculation:**

- Storing 1 million keys
- AVL tree (binary) height ≈ log₂(1,000,000) ≈ 20 levels → needs 20 disk reads
- B-Tree (t=100) height ≈ log₁₀₀(1,000,000) ≈ 3 levels → only needs 3 disk reads

------

## 7. 重构与SOLID原则 Refactoring & SOLID Principles

### Q7.1: 解释SOLID五大原则,并给出每个原则的代码示例。

### Explain the five SOLID principles with code examples for each.

**中文答案：**

**SOLID是面向对象设计的五大原则，旨在使软件更易维护、扩展和理解。**

---

### **1. S - Single Responsibility Principle (单一职责原则)**

**定义：** 一个类应该只有一个引起它变化的原因。换句话说，一个类应该只负责一项职责。

**❌ 违反SRP的代码：**

```java
// 一个类负责多个职责
public class Employee {
    private String name;
    private double salary;
    
    // 职责1: 计算工资
    public double calculatePay() {
        return salary * 1.1;  // 加10%奖金
    }
    
    // 职责2: 报告生成
    public String generateReport() {
        return "Employee: " + name + ", Salary: " + salary;
    }
    
    // 职责3: 数据库操作
    public void saveToDatabase() {
        // 数据库保存代码
        System.out.println("Saving " + name + " to database");
    }
}
```

**问题：**

- HR部门改工资计算规则 → 需要修改这个类
- IT部门改报告格式 → 需要修改这个类
- DBA改数据库结构 → 需要修改这个类
- **违反了SRP: 一个类有三个变化原因！**

---

**✅ 遵循SRP的代码：**

```java
// 职责1: 员工数据
public class Employee {
    private String name;
    private double salary;
    
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    
    public String getName() {
        return name;
    }
    
    public double getSalary() {
        return salary;
    }
}

// 职责2: 工资计算
public class PayrollCalculator {
    public double calculatePay(Employee employee) {
        return employee.getSalary() * 1.1;
    }
}

// 职责3: 报告生成
public class ReportGenerator {
    public String generateReport(Employee employee) {
        return "Employee: " + employee.getName() + 
               ", Salary: " + employee.getSalary();
    }
}

// 职责4: 数据持久化
public class EmployeeRepository {
    public void save(Employee employee) {
        System.out.println("Saving " + employee.getName() + " to database");
    }
}
```

**优点：**

- 每个类只有一个职责
- 修改工资计算不影响报告生成
- 易于测试和维护

---

### **2. O - Open/Closed Principle (开闭原则)**

**定义：** 软件实体应该对扩展开放，对修改关闭。即可以通过添加新代码来扩展功能，而不是修改现有代码。

**❌ 违反OCP的代码：**

```java
public class DiscountCalculator {
    public double calculateDiscount(String customerType, double amount) {
        if (customerType.equals("Regular")) {
            return amount * 0.05;  // 5%折扣
        } else if (customerType.equals("Premium")) {
            return amount * 0.10;  // 10%折扣
        } else if (customerType.equals("VIP")) {
            return amount * 0.20;  // 20%折扣
        }
        // 添加新客户类型需要修改这个方法！❌
        return 0;
    }
}
```

**问题：** 每次添加新客户类型都要修改`calculateDiscount`方法

---

**✅ 遵循OCP的代码：**

```java
// 抽象折扣策略
public interface DiscountStrategy {
    double calculateDiscount(double amount);
}

// 具体策略1
public class RegularDiscount implements DiscountStrategy {
    public double calculateDiscount(double amount) {
        return amount * 0.05;
    }
}

// 具体策略2
public class PremiumDiscount implements DiscountStrategy {
    public double calculateDiscount(double amount) {
        return amount * 0.10;
    }
}

// 具体策略3
public class VIPDiscount implements DiscountStrategy {
    public double calculateDiscount(double amount) {
        return amount * 0.20;
    }
}

// 添加新类型只需新建类，无需修改现有代码✅
public class GoldDiscount implements DiscountStrategy {
    public double calculateDiscount(double amount) {
        return amount * 0.30;
    }
}

// 使用
public class DiscountCalculator {
    private DiscountStrategy strategy;
    
    public DiscountCalculator(DiscountStrategy strategy) {
        this.strategy = strategy;
    }
    
    public double calculate(double amount) {
        return strategy.calculateDiscount(amount);
    }
}
```

**优点：**

- 添加新折扣类型不需修改现有代码
- 符合"对扩展开放，对修改关闭"

---

### **3. L - Liskov Substitution Principle (里氏替换原则)**

**定义：** 子类对象应该能够替换父类对象而不影响程序的正确性。即子类必须能够完全替代父类。

**❌ 违反LSP的代码：**

```java
public class Rectangle {
    protected int width;
    protected int height;
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getArea() {
        return width * height;
    }
}

// 正方形继承矩形
public class Square extends Rectangle {
    @Override
    public void setWidth(int width) {
        this.width = width;
        this.height = width;  // 强制宽高相等
    }
    
    @Override
    public void setHeight(int height) {
        this.width = height;  // 强制宽高相等
        this.height = height;
    }
}

// 测试代码
public class Test {
    public static void testRectangle(Rectangle r) {
        r.setWidth(5);
        r.setHeight(4);
        assert r.getArea() == 20 : "面积应该是20";  // ❌ Square会失败!
    }
    
    public static void main(String[] args) {
        testRectangle(new Rectangle());  // ✅ 通过
        testRectangle(new Square());     // ❌ 失败! 面积是16而不是20
    }
}
```

**问题：** `Square`不能完全替代`Rectangle`，破坏了LSP

---

**✅ 遵循LSP的代码：**

```java
// 使用接口而不是继承
public interface Shape {
    int getArea();
}

public class Rectangle implements Shape {
    private int width;
    private int height;
    
    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public int getArea() {
        return width * height;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
}

public class Square implements Shape {
    private int side;
    
    public Square(int side) {
        this.side = side;
    }
    
    public int getArea() {
        return side * side;
    }
    
    public void setSide(int side) {
        this.side = side;
    }
}
```

**优点：**

- `Rectangle`和`Square`互不影响
- 各自维护自己的不变式
- 符合LSP

---

### **4. I - Interface Segregation Principle (接口隔离原则)**

**定义：** 客户端不应该被强迫依赖它不使用的方法。应该使用多个专门的接口，而不是单一的总接口。

**❌ 违反ISP的代码：**

```java
// 臃肿的接口
public interface Worker {
    void work();
    void eat();
    void sleep();
}

// 人类工人
public class HumanWorker implements Worker {
    public void work() {
        System.out.println("Human working");
    }
    
    public void eat() {
        System.out.println("Human eating");
    }
    
    public void sleep() {
        System.out.println("Human sleeping");
    }
}

// 机器人工人
public class RobotWorker implements Worker {
    public void work() {
        System.out.println("Robot working");
    }
    
    // ❌ 机器人不需要吃饭和睡觉，但被强迫实现
    public void eat() {
        throw new UnsupportedOperationException("Robots don't eat");
    }
    
    public void sleep() {
        throw new UnsupportedOperationException("Robots don't sleep");
    }
}
```

**问题：** `RobotWorker`被迫实现不需要的方法

---

**✅ 遵循ISP的代码：**

```java
// 分离成多个专门的接口
public interface Workable {
    void work();
}

public interface Eatable {
    void eat();
}

public interface Sleepable {
    void sleep();
}

// 人类工人实现所有接口
public class HumanWorker implements Workable, Eatable, Sleepable {
    public void work() {
        System.out.println("Human working");
    }
    
    public void eat() {
        System.out.println("Human eating");
    }
    
    public void sleep() {
        System.out.println("Human sleeping");
    }
}

// 机器人只实现需要的接口
public class RobotWorker implements Workable {
    public void work() {
        System.out.println("Robot working");
    }
    // ✅ 不需要实现eat和sleep
}
```

**优点：**

- 接口更小、更专注
- 类只实现需要的接口
- 避免"胖接口"

---

### **5. D - Dependency Inversion Principle (依赖倒置原则)**

**定义：** 

1. 高层模块不应该依赖低层模块，两者都应该依赖抽象
2. 抽象不应该依赖细节，细节应该依赖抽象

**❌ 违反DIP的代码：**

```java
// 低层模块
public class MySQLDatabase {
    public void save(String data) {
        System.out.println("Saving to MySQL: " + data);
    }
}

// 高层模块直接依赖低层模块
public class UserService {
    private MySQLDatabase database;  // ❌ 直接依赖具体实现
    
    public UserService() {
        this.database = new MySQLDatabase();
    }
    
    public void saveUser(String userData) {
        database.save(userData);
    }
}
```

**问题：** 如果要换成PostgreSQL，必须修改`UserService`代码

---

**✅ 遵循DIP的代码：**

```java
// 抽象层
public interface Database {
    void save(String data);
}

// 低层模块1
public class MySQLDatabase implements Database {
    public void save(String data) {
        System.out.println("Saving to MySQL: " + data);
    }
}

// 低层模块2
public class PostgreSQLDatabase implements Database {
    public void save(String data) {
        System.out.println("Saving to PostgreSQL: " + data);
    }
}

// 低层模块3
public class MongoDatabase implements Database {
    public void save(String data) {
        System.out.println("Saving to MongoDB: " + data);
    }
}

// 高层模块依赖抽象
public class UserService {
    private Database database;  // ✅ 依赖抽象接口
    
    // 依赖注入
    public UserService(Database database) {
        this.database = database;
    }
    
    public void saveUser(String userData) {
        database.save(userData);
    }
}

// 使用
public class Main {
    public static void main(String[] args) {
        // 可以轻松切换数据库实现
        UserService service1 = new UserService(new MySQLDatabase());
        service1.saveUser("User1");
        
        UserService service2 = new UserService(new PostgreSQLDatabase());
        service2.saveUser("User2");
        
        UserService service3 = new UserService(new MongoDatabase());
        service3.saveUser("User3");
    }
}
```

**优点：**

- 高层模块不依赖低层模块的具体实现
- 易于切换不同的实现
- 符合"面向接口编程"原则

---

**SOLID原则总结表：**

| 原则    | 关键词   | 核心思想               | 主要好处       |
| ------- | -------- | ---------------------- | -------------- |
| **S**RP | 单一职责 | 一个类只做一件事       | 高内聚，低耦合 |
| **O**CP | 开闭原则 | 对扩展开放，对修改关闭 | 易于扩展       |
| **L**SP | 里氏替换 | 子类能替换父类         | 继承正确使用   |
| **I**SP | 接口隔离 | 接口应该小而专注       | 避免胖接口     |
| **D**IP | 依赖倒置 | 依赖抽象而非具体       | 松耦合         |

---

**记忆口诀：**

```
Single responsibility (单一)
Open/Closed (开闭)
Liskov substitution (里氏)
Interface segregation (接口隔离)
Dependency inversion (依赖倒置)

→ SOLID = 可靠的代码
```

---

**English Answer:**

**SOLID represents five principles of object-oriented design aimed at making software more maintainable, extensible, and understandable.**

[All code examples and explanations translated to English following the same structure as Chinese version above]

---

## 8. UML建模 UML Modeling

### Q8.1: 解释UML类图中的各种关系（关联、聚合、组合、继承、依赖）并给出示例。

### Explain various relationships in UML class diagrams (Association, Aggregation, Composition, Inheritance, Dependency) with examples.

**中文答案：**

**UML类图中的关系类型：**

---

### **1. 关联 (Association) - 实线**

**定义：** 表示两个类之间有联系，一个类知道另一个类的属性和方法。

**符号：** 实线，可以带箭头表示方向

**代码示例：**

```java
// 双向关联
public class Student {
    private Course course;  // 学生知道课程
    
    public void enrollIn(Course c) {
        this.course = c;
        c.addStudent(this);  // 双向
    }
}

public class Course {
    private List<Student> students;  // 课程知道学生
    
    public void addStudent(Student s) {
        students.add(s);
    }
}
```

**UML图示：**

```
Student ————— Course
  (学生)          (课程)
```

**多重性标记：**

```
Student 1..*————— 0..* Course
  一个学生可以选多门课       一门课可以有多个学生
```

---

### **2. 聚合 (Aggregation) - 空心菱形**

**定义：** 表示整体与部分的关系，但部分可以独立存在。"has-a"关系，较弱的拥有。

**符号：** 空心菱形在整体一侧

**代码示例：**

```java
// 部门有多个员工，但员工可以独立存在
public class Department {
    private List<Employee> employees;
    
    public Department() {
        this.employees = new ArrayList<>();
    }
    
    public void addEmployee(Employee emp) {
        employees.add(emp);
    }
    
    // 部门解散后，员工仍然存在
}

public class Employee {
    private String name;
    
    public Employee(String name) {
        this.name = name;
    }
}
```

**UML图示：**

```
Department ◇————— Employee
  (部门)               (员工)
```

**特点：**

- 员工对象在部门外部创建
- 部门解散，员工仍存在
- 生命周期独立

---

### **3. 组合 (Composition) - 实心菱形**

**定义：** 表示强拥有关系，部分不能独立于整体存在。整体销毁，部分也销毁。

**符号：** 实心菱形在整体一侧

**代码示例：**

```java
// 房子有房间，房间不能独立于房子存在
public class House {
    private List<Room> rooms;
    
    public House() {
        this.rooms = new ArrayList<>();
        // 房间在房子创建时创建
        rooms.add(new Room("客厅"));
        rooms.add(new Room("卧室"));
        rooms.add(new Room("厨房"));
    }
    
    // 房子销毁时，房间也随之销毁
    // Java中由GC自动处理
}

public class Room {
    private String name;
    
    public Room(String name) {
        this.name = name;
    }
    
    // 房间不能独立存在
}
```

**UML图示：**

```
House ◆————— Room
 (房子)          (房间)
```

**特点：**

- 房间对象在房子内部创建
- 房子销毁，房间也销毁
- 生命周期相同

---

### **4. 继承/泛化 (Inheritance/Generalization) - 空心三角箭头**

**定义：** 表示"is-a"关系，子类继承父类的属性和方法。

**符号：** 空心三角箭头指向父类

**代码示例：**

```java
// 父类
public abstract class Animal {
    protected String name;
    
    public Animal(String name) {
        this.name = name;
    }
    
    public abstract void makeSound();
}

// 子类1
public class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " says: Woof!");
    }
}

// 子类2
public class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " says: Meow!");
    }
}
```

**UML图示：**

```
        Animal
          △
          |
    ______|______
    |           |
   Dog         Cat
```

---

### **5. 依赖 (Dependency) - 虚线箭头**

**定义：** 表示一个类使用另一个类，通常是临时性的、弱关系。

**符号：** 虚线箭头指向被依赖的类

**代码示例：**

```java
public class Car {
    private String model;
    
    public Car(String model) {
        this.model = model;
    }
}

public class Driver {
    private String name;
    
    public Driver(String name) {
        this.name = name;
    }
    
    // 方法参数：临时依赖Car
    public void drive(Car car) {
        System.out.println(name + " is driving " + car.model);
    }
    
    // 局部变量：临时依赖Car
    public void rentCar() {
        Car rental = new Car("Toyota");
        drive(rental);
    }
}
```

**UML图示：**

```
Driver - - - > Car
             (虚线箭头)
```

**特点：**

- 通常出现在方法参数或局部变量
- 临时性关系
- 最弱的耦合

---

### **6. 实现 (Realization) - 虚线空心三角**

**定义：** 表示类实现接口。

**符号：** 虚线空心三角箭头指向接口

**代码示例：**

```java
// 接口
public interface Drawable {
    void draw();
}

// 实现类
public class Circle implements Drawable {
    private int radius;
    
    public Circle(int radius) {
        this.radius = radius;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing circle with radius " + radius);
    }
}
```

**UML图示：**

```
<<interface>>
  Drawable
     △
     :
     :
  Circle
 (虚线三角)
```

---

**完整UML类图示例：**

```
┌─────────────────────────────────────────────────────┐
│                   University                        │
│ ◆───────────────────────────────────────────────┐  │
│ │                 Department                     │  │
│ │ ◇───────────────────────────────────────┐     │  │
│ │ │            Professor                   │     │  │
│ │ │ - - - - - - - - - - - - - - - - - - >  │     │  │
│ │ │                            Course      │     │  │
│ │ │            Student                     │     │  │
│ │ │ ─────────────────────────────────┐    │     │  │
│ │ └─────────────────────────────────│────┘     │  │
│ └───────────────────────────────────│──────────┘  │
└─────────────────────────────────────│─────────────┘
                                      │
                                      ▼
                                   Person
                                      △
                              ________|________
                              |               |
                          Professor        Student
                              
说明：
◆ = 组合 (Composition)
◇ = 聚合 (Aggregation)
─ = 关联 (Association)
- - > = 依赖 (Dependency)
△ = 继承 (Inheritance)
```

**完整Java代码实现：**

```java
// 1. 继承关系
public abstract class Person {
    protected String name;
    protected int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public abstract String getRole();
}

// 2. 继承实现
public class Professor extends Person {
    private String department;
    
    public Professor(String name, int age, String dept) {
        super(name, age);
        this.department = dept;
    }
    
    @Override
    public String getRole() {
        return "Professor";
    }
    
    // 3. 依赖关系: 方法参数
    public void teach(Course course) {
        System.out.println(name + " is teaching " + course.getName());
    }
}

public class Student extends Person {
    private String studentId;
    
    public Student(String name, int age, String id) {
        super(name, age);
        this.studentId = id;
    }
    
    @Override
    public String getRole() {
        return "Student";
    }
}

// 4. 普通类
public class Course {
    private String name;
    private String code;
    
    public Course(String name, String code) {
        this.name = name;
        this.code = code;
    }
    
    public String getName() {
        return name;
    }
}

// 5. 聚合关系: Department has Professors
public class Department {
    private String name;
    private List<Professor> professors;  // 聚合：教授可独立存在
    
    public Department(String name) {
        this.name = name;
        this.professors = new ArrayList<>();
    }
    
    public void addProfessor(Professor prof) {
        professors.add(prof);
    }
}

// 6. 组合关系: University has Departments
public class University {
    private String name;
    private List<Department> departments;  // 组合：系不能独立存在
    
    public University(String name) {
        this.name = name;
        this.departments = new ArrayList<>();
        // 系在大学创建时创建
        departments.add(new Department("Computer Science"));
        departments.add(new Department("Mathematics"));
    }
}
```

---

**关系强度对比（从强到弱）：**

```
组合 > 聚合 > 关联 > 依赖
 ◆      ◇      ─     - - >

组合: 整体与部分，生命周期相同
聚合: 整体与部分，生命周期独立
关联: 知道对方，可以互相调用
依赖: 临时使用，最弱耦合
```

---

**决策树：选择哪种关系？**

```
两个类之间有关系吗？
├─ 没有关系 → 无需画线
└─ 有关系
    ├─ 一个类是另一个类的子类？
    │   └─ 是 → 继承 (空心三角)
    ├─ 一个类实现另一个接口？
    │   └─ 是 → 实现 (虚线空心三角)
    └─ 一个类使用另一个类
        ├─ 整体-部分关系？
        │   ├─ 部分可以独立存在？
        │   │   ├─ 是 → 聚合 (空心菱形)
        │   │   └─ 否 → 组合 (实心菱形)
        ├─ 有成员变量吗？
        │   └─ 有 → 关联 (实线)
        └─ 只在方法中临时使用？
            └─ 是 → 依赖 (虚线箭头)
```

---

**English Answer:**

**Relationship Types in UML Class Diagrams:**

[All explanations, code examples, and diagrams translated to English following the same structure as Chinese version above]

---

## 9. 软件专利 Software Patents

### Q9.1: 什么是计算机实现发明(CII)？在澳大利亚,软件如何获得专利保护？

### What are Computer-Implemented Inventions (CII)? How can software be patented in Australia?

**中文答案：**

**计算机实现发明(CII)定义：**

CII (Computer-Implemented Invention) 是指使用计算机、计算机网络或其他可编程设备来实现的发明，其中计算机程序在发明中起关键作用。

---

**专利的基本要求（全球通用）：**

1. **新颖性(Novelty)：** 发明必须是新的，在申请日之前未被公开
2. **创造性/非显而易见性(Inventive Step/Non-obvious)：** 对于该领域技术人员不是显而易见的
3. **实用性/工业应用性(Usefulness/Industrial Applicability)：** 可以在工业中应用

---

**澳大利亚软件专利法规：**

在澳大利亚，软件本身**不能**直接获得专利，但是：

**✅ 可以专利的：**

- 解决**技术问题**的CII
- 产生**技术效果**的软件方法
- 与硬件结合实现**技术改进**的系统

**❌ 不能专利的：**

- 纯粹的**抽象算法**
- **商业方法**本身
- 仅仅是**数学公式**
- 单纯的**数据处理**方法

---

**关键判例：Commissioner of Patents v RPL Central Pty Ltd (2015)**

这个案例确立了澳大利亚软件专利的判定标准。

**核心测试：**

```
软件发明是否"实质上"是计算机实现的方案？
├─ 是纯软件实现吗？
│   ├─ 是 → 可能不可专利
│   └─ 否 → 可能可专利
└─ 是否解决技术问题？
    ├─ 是 → 可能可专利
    └─ 否 → 不可专利
```

---

**示例分析：**

### **✅ 可专利的例子：**

**例1：图像压缩算法**

```
发明：一种减少图像文件大小的新方法

分析：
✅ 技术问题: 减少存储空间和传输时间
✅ 技术效果: 更高的压缩率，更快的处理速度
✅ 非抽象: 具体的编码技术，不只是数学公式
结论：可专利
```

**例2：网络路由优化**

```
发明：一种改进数据包路由效率的系统

分析：
✅ 技术问题: 减少网络拥塞
✅ 技术效果: 更快的数据传输
✅ 硬件结合: 与路由器硬件配合工作
结论：可专利
```

---

### **❌ 不可专利的例子：**

**例1：商业流程软件**

```
发明：一种在线购物车结账流程

分析：
❌ 纯商业方法: 只是商业流程的软件实现
❌ 无技术改进: 不解决技术问题
❌ 抽象概念: 可以用人工完成
结论：不可专利
```

**例2：数学计算器**

```
发明：一个执行特定数学公式的应用

分析：
❌ 纯数学方法: 只是公式的实现
❌ 无技术创新: 没有技术突破
❌ 显而易见: 任何程序员都能实现
结论：不可专利
```

---

**澳大利亚专利申请流程：**

```
第1步: 专利检索 (Patent Search)
    ↓ 确认新颖性
第2步: 准备专利申请
    - 说明书 (Specification)
    - 权利要求 (Claims)
    - 摘要 (Abstract)
    - 附图 (Drawings)
    ↓
第3步: 提交临时申请 (Provisional Application)
    - 获得申请日期
    - 费用: ~$110 AUD
    - 有效期: 12个月
    ↓
第4步: 提交标准专利申请 (Standard Application)
    - 在临时申请后12个月内提交
    - 费用: ~$370 AUD
    ↓
第5步: 审查请求 (Request for Examination)
    - 提交后5年内请求
    - 费用: ~$490 AUD
    ↓
第6步: 审查过程 (Examination)
    - IP Australia审查
    - 可能需要修改
    ↓
第7步: 授权 (Grant)
    - 专利有效期: 20年
    - 年费: 逐年递增
```

---

**国际专利申请（PCT）：**

**PCT (Patent Cooperation Treaty) 途径：**

```
澳大利亚申请
    ↓
PCT国际申请 (30-31个月内)
    ↓
进入国家阶段 (指定国家)
    - 美国
    - 欧洲
    - 中国
    - 日本
    - 等等...
```

**优点：**

- 一次申请覆盖150+国家
- 延迟进入国家阶段的时间
- 获得国际检索报告

---

**软件专利撰写技巧：**

**❌ 错误的权利要求：**

```
一种方法，包括：
1. 接收用户输入
2. 处理数据
3. 显示结果
```

**问题：** 太抽象，没有技术细节

---

**✅ 正确的权利要求：**

```
一种图像处理方法，包括：
1. 通过卷积神经网络分析输入图像
2. 使用[具体算法名称]识别图像中的对象
3. 基于对象的空间关系进行语义分割
4. 将结果存储在[具体数据结构]中

其中，所述卷积神经网络包括：
- 5层卷积层，每层使用ReLU激活函数
- 池化层使用2×2最大池化
- 全连接层输出1000个类别
```

**关键点：**
✅ 具体的技术细节
✅ 特定的算法或结构
✅ 可测量的技术效果

---

**软件专利 vs 版权 vs 商业秘密：**

| 保护方式     | 保护内容 | 保护期限       | 公开要求 | 成本          |
| ------------ | -------- | -------------- | -------- | ------------- |
| **专利**     | 技术思想 | 20年           | 必须公开 | 高 ($10,000+) |
| **版权**     | 代码表达 | 作者死后70年   | 无需公开 | 低 (自动获得) |
| **商业秘密** | 秘密信息 | 永久(保密期间) | 必须保密 | 中 (保密措施) |

---

**决策树：如何保护你的软件？**

```
你的软件有技术创新吗？
├─ 是 → 考虑专利
│   ├─ 预算充足？
│   │   ├─ 是 → 申请专利
│   │   └─ 否 → 商业秘密
│   └─ 愿意公开技术？
│       ├─ 是 → 专利
│       └─ 否 → 商业秘密
└─ 否 → 版权保护
    ├─ 代码原创性高
    └─ 自动获得
```

---

**考试常见问题：**

**Q: 为什么纯软件难以获得专利？**
**A:** 

1. 法律认为软件本质是数学算法或抽象思想
2. 抽象思想不可专利（公共领域）
3. 必须证明有"技术贡献"

**Q: 什么是"技术效果"？**
**A:**

- 更快的处理速度
- 更低的内存使用
- 更高的准确率
- 硬件性能改进
- **不是**：更好的用户体验、更方便的流程

**Q: 澳大利亚和美国专利法的区别？**
**A:**

- **澳大利亚：** 更严格，强调"技术问题"
- **美国：** 相对宽松，但Alice案后也收紧
- **欧洲：** 最严格，几乎不承认软件专利

---

**English Answer:**

**Computer-Implemented Invention (CII) Definition:**

CII refers to inventions that are implemented using computers, computer networks, or other programmable devices, where computer programs play a key role in the invention.

---

**Basic Patent Requirements (Universal):**

1. **Novelty:** Invention must be new, not publicly disclosed before application date
2. **Inventive Step/Non-obviousness:** Not obvious to person skilled in the art
3. **Usefulness/Industrial Applicability:** Can be applied in industry

---

**Australian Software Patent Law:**

In Australia, software itself **cannot** be directly patented, but:

**✅ Patentable:**

- CII solving **technical problems**
- Software methods producing **technical effects**
- Systems achieving **technical improvements** combined with hardware

**❌ Not Patentable:**

- Pure **abstract algorithms**
- **Business methods** themselves
- Merely **mathematical formulas**
- Simple **data processing** methods

---

**Key Case: Commissioner of Patents v RPL Central Pty Ltd (2015)**

This case established the criteria for software patents in Australia.

**Core Test:**

```
Is the software invention "substantially" a computer-implemented scheme?
├─ Is it pure software implementation?
│   ├─ Yes → Likely not patentable
│   └─ No → Potentially patentable
└─ Does it solve a technical problem?
    ├─ Yes → Potentially patentable
    └─ No → Not patentable
```

---

[Complete examples, patent application process, comparison tables, and decision trees translated to English following the same structure as Chinese version above]

---

**Exam Common Questions:**

**Q: Why is pure software difficult to patent?**
**A:**

1. Law considers software essentially mathematical algorithms or abstract ideas
2. Abstract ideas are not patentable (public domain)
3. Must prove "technical contribution"

**Q: What is a "technical effect"?**
**A:**

- Faster processing speed
- Lower memory usage
- Higher accuracy
- Hardware performance improvement
- **NOT**: Better user experience, more convenient process

**Q: Differences between Australian and US patent law?**
**A:**

- **Australia:** Stricter, emphasizes "technical problem"
- **United States:** Relatively lenient, but tightened after Alice case
- **Europe:** Most strict, almost no software patents

## 

## 10. Android 应用开发基础 (Android App Development Fundamentals)

### Q10.1: 什么是 Android 系统？Android 应用是如何构建与运行的？

**What is Android OS? How are Android apps built and executed?**

------

**中文答案：**

定义：
 Android 是一个基于 Linux 内核的多用户操作系统，每个应用在系统中作为独立用户运行。每个应用拥有独立的用户 ID (UID)，从而确保了进程与文件的安全隔离。

**关键特性：**

1. **语言支持**：Android 应用可使用 Kotlin、Java 或 C++ 编写。
2. **打包格式**：编译完成后，所有代码与资源被打包为 `.apk` 文件（Android Package），即安装文件。
3. **安全机制**：
   - 每个应用运行在独立沙箱中；
   - 系统通过 UID 控制文件访问权限；
   - 遵循最小权限原则 (Principle of Least Privilege)，默认仅能访问必要资源；
   - 若需访问相机、定位等资源，必须向用户请求权限。

------

**English Answer:**

**Definition:**
 Android is a multi-user operating system based on the Linux kernel. Each app runs as a unique user with its own UID, ensuring process and data isolation.

**Key Features:**

1. **Languages:** Android apps can be written in Kotlin, Java, or C++.
2. **Package:** After compilation, code and resources are stored in an `.apk` file used for installation.
3. **Security:**
   - Apps run in isolated sandboxes.
   - System assigns each app a unique ID (UID).
   - Follows the Principle of Least Privilege—access only required components.
   - Apps must explicitly request permissions (e.g., camera, GPS).

------

### Q10.2: Android 应用的四大核心组件是什么？

**What are the four main components of an Android app?**

------

**中文答案：**

Android 应用由四大组件组成，每个组件都是系统或用户进入应用的入口点：

| 组件                   | 功能说明                               | 示例                       |
| ---------------------- | -------------------------------------- | -------------------------- |
| **Activity**           | 处理用户界面交互的入口点；对应单个屏幕 | 登录页、设置页             |
| **Service**            | 在后台运行的组件，用于执行长时间操作   | 播放音乐、同步数据         |
| **Broadcast Receiver** | 响应系统广播事件                       | 电量低、电源连接、收到短信 |
| **Content Provider**   | 管理共享数据（文件、数据库等）         | 通讯录、图片库             |

------

**English Answer:**

Android apps consist of four main components, each serving as an entry point:

| Component              | Description                                                  | Example             |
| ---------------------- | ------------------------------------------------------------ | ------------------- |
| **Activity**           | Entry point for user interaction; represents a single screen | Login page          |
| **Service**            | Runs in the background for long-running tasks                | Music playback      |
| **Broadcast Receiver** | Responds to system-wide events                               | Low battery alert   |
| **Content Provider**   | Manages shared data (files, databases, etc.)                 | Contacts or gallery |

------

### Q10.3: 什么是 Intent？Intent 有哪两种类型？

**What is an Intent? What are the two types of Intents?**

------

**中文答案：**

**定义：**
 Intent 是 Android 中用于在组件之间传递信息的对象。它描述了要执行的操作与所需数据，用于启动 Activity、Service 或 Broadcast Receiver。

**分类：**

1. **显式 Intent (Explicit Intent)**

   - 指定目标组件（类名已知）；
   - 常用于在同一应用内跳转。

   ```
   Intent intent = new Intent(getApplicationContext(), ActivityB.class);
   startActivity(intent);
   ```

2. **隐式 Intent (Implicit Intent)**

   - 不指定组件名称，而是声明要执行的通用动作；
   - 由系统选择合适的应用响应（如打开地图、浏览网页）。

------

**English Answer:**

**Definition:**
 An Intent is a messaging object that facilitates communication between Android components.

**Types:**

1. **Explicit Intent:** Specifies the exact component to start (within your app).
2. **Implicit Intent:** Declares a general action, allowing other apps to handle it (e.g., open location in Maps).

------

### Q10.4: 请解释 Activity 的生命周期 (Activity Lifecycle)

**Explain the Activity Lifecycle in Android.**

------

**中文答案：**

Activity 是 Android 应用的核心交互单元，每个 Activity 对应一个窗口。系统通过一系列回调方法控制其生命周期。

**主要阶段与回调：**

| 生命周期方法  | 说明                            |
| ------------- | ------------------------------- |
| `onCreate()`  | 初始化 UI，加载布局文件         |
| `onStart()`   | Activity 即将对用户可见         |
| `onResume()`  | 用户可以与界面交互              |
| `onPause()`   | Activity 部分可见，准备进入后台 |
| `onStop()`    | Activity 不再可见               |
| `onRestart()` | 从后台重新回到前台              |
| `onDestroy()` | Activity 被销毁，释放资源       |

示例：

```
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toast.makeText(this, "onCreate!", Toast.LENGTH_SHORT).show();
}
```

------

**English Answer:**

The Activity Lifecycle consists of callback methods that manage visibility and user interaction:

| Method        | Description                       |
| ------------- | --------------------------------- |
| `onCreate()`  | Initialize and set layout         |
| `onStart()`   | Activity becoming visible         |
| `onResume()`  | App in foreground and interactive |
| `onPause()`   | Activity partially obscured       |
| `onStop()`    | Activity fully hidden             |
| `onRestart()` | Re-enter foreground               |
| `onDestroy()` | Destroyed and cleaned up          |

------

### Q10.5: Android 的布局 (Layout) 与视图层级 (View Hierarchy) 是如何组织的？

**How are layouts and the UI hierarchy structured in Android?**

------

**中文答案：**

Android 的用户界面 (UI) 由视图 (View) 与视图组 (ViewGroup) 构成的层级结构组成。

- **View**：UI 元素（按钮、文本框、图片等）
- **ViewGroup**：容器，用于管理子 View 的布局方式

**常见布局类型：**

1. **LinearLayout** – 线性排列（水平或垂直）
2. **ConstraintLayout** – 通过约束定义组件相对位置
3. **RelativeLayout / FrameLayout** – 相对定位或重叠布局

示例：

```
<LinearLayout
    android:orientation="vertical">
    <Button android:text="Click Me" />
    <TextView android:text="Hello World" />
</LinearLayout>
```

------

**English Answer:**

Android UI is a hierarchy of **Views** (widgets) and **ViewGroups** (layouts).

**Common Layouts:**

- **LinearLayout:** Arranges views in a single row/column.
- **ConstraintLayout:** Flexible, position elements using constraints.
- **RelativeLayout:** Position elements relative to others.

Layouts are defined in XML under `res/layout/` and loaded with `setContentView()` in `onCreate()`.

------

### Q10.6: 什么是 Adapter？它在动态布局中起什么作用？

**What is an Adapter, and what role does it play in dynamic layouts?**

------

**中文答案：**

**定义：**
 Adapter 是一种桥梁，用于在动态布局中将数据与界面组件连接。它会为数据源中的每个元素创建对应的视图。

**常见类型：**

- **ArrayAdapter**：用于数组或列表数据
- **CursorAdapter**：用于数据库结果集
- **RecyclerView.Adapter**：用于高性能列表显示

示例：

```
ArrayAdapter adapter = new ArrayAdapter(
    this, android.R.layout.simple_list_item_1, items);
listView.setAdapter(adapter);
```

------

**English Answer:**

**Definition:**
 An Adapter acts as a bridge between a data source and a UI component that displays the data (e.g., ListView, RecyclerView).

**Example:**

```
ArrayAdapter ad = new ArrayAdapter(
    this, android.R.layout.simple_list_item_1, arrItems);
listView.setAdapter(ad);
```

Adapters populate layouts dynamically at runtime.

------

### Q10.7: Android 中的事件监听器 (Event Listener) 是如何工作的？

**How do Event Listeners work in Android?**

------

**中文答案：**

事件监听器是接口，用于响应用户与界面的交互。常见事件包括点击、长按、焦点变化、触摸等。

示例：

```
Button button = findViewById(R.id.myButton);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        // 点击事件逻辑
    }
});
```

**常见监听方法：**

- `onClick()`
- `onLongClick()`
- `onKey()`
- `onTouch()`

------

**English Answer:**

An Event Listener is an interface containing callback methods triggered by user interactions (e.g., touch, click).

Example:

```
button.setOnClickListener(v ->
    Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show());
```

------

### Q10.8: Toast 是什么？它与 Notification 有什么不同？

**What is a Toast, and how does it differ from a Notification?**

------

**中文答案：**

**Toast 定义：**
 Toast 是一种轻量级提示，短暂显示在屏幕上方，不打断当前操作。

示例：

```
Toast.makeText(getApplicationContext(),
    "Sending message...", Toast.LENGTH_SHORT).show();
```

**Toast vs Notification 对比：**

| 特性     | Toast    | Notification       |
| -------- | -------- | ------------------ |
| 显示时间 | 短暂     | 可持续存在         |
| 用户交互 | 不可点击 | 可点击、可跳转     |
| 用途     | 临时提示 | 系统或后台事件提醒 |

------

**English Answer:**

A **Toast** displays a short, non-interruptive message overlay.
 Unlike **Notifications**, it disappears automatically and doesn’t support user interaction.

------

### Q10.9: Android 的样式 (Style) 与主题 (Theme) 有何作用？

**What are Styles and Themes in Android?**

------

**中文答案：**

**定义：**

- **Style（样式）**：定义单个组件的外观属性，如字体、颜色、背景。
- **Theme（主题）**：应用于整个应用、Activity 或视图层级的全局样式集合。

**声明方式：**

```
<resources>
    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorAccent">@color/colorAccent</item>
    </style>
</resources>
```

文件位置：`res/values/styles.xml`

------

**English Answer:**

**Definition:**

- **Style:** Defines visual attributes for individual views (e.g., font, color).
- **Theme:** A set of styles applied globally to the entire app or Activity.

Declared in `res/values/styles.xml` and applied via the `AndroidManifest.xml`.

## 11. 职业道德与专业行为 (Ethics and Professionalism)

### Q11.1: 什么是“职业精神”(Professionalism)？为什么在技术行业中至关重要？

**What is “Professionalism”, and why is it important in the technology industry?**

------

**中文答案：**

定义：
 “职业精神”是指从业者在工作中表现出的专业知识、责任意识与道德操守。
 一名专业人士不仅要具备技术能力，还要能**在伦理约束下负责任地运用技能**，以促进社会福祉。

**关键特征：**

1. 拥有系统的专业知识与技能；
2. 遵守行业伦理规范与法律法规；
3. 在面对复杂或模糊情况时，做出有益社会的决策；
4. 以诚实、尊重和责任感为基础开展工作。

**在科技行业的重要性：**

- 技术影响社会安全、隐私与公平；
- 专业精神确保技术被正当地使用；
- 提升公众对 ICT 专业人员的信任。

------

**English Answer:**

**Definition:**
 Professionalism refers to the combination of expertise, responsibility, and ethical conduct demonstrated by individuals in their profession. A professional applies their knowledge ethically and responsibly to benefit society.

**Key Characteristics:**

1. Possesses specialized skills and knowledge;
2. Follows ethical and legal standards;
3. Makes socially responsible decisions in complex situations;
4. Acts with honesty, respect, and accountability.

**Importance in Tech Industry:**

- Technology impacts safety, privacy, and fairness;
- Ensures responsible use of digital systems;
- Builds public trust in ICT professionals.

------

### Q11.2: 什么是“伦理”(Ethics)？在 ICT 行业中如何体现？

**What are “Ethics”, and how do they apply to ICT professionals?**

------

**中文答案：**

定义：
 伦理是指一套用于判断行为是否可接受的**道德原则或规则体系**。在 ICT 行业中，伦理规范帮助专业人士在面对技术风险和社会影响时，选择正确的行动。

**ICT 领域的伦理应用：**

1. 数据隐私与安全（如不滥用用户数据）；
2. 人工智能的公平与透明；
3. 网络安全责任（防止技术被滥用）；
4. 尊重知识产权与他人劳动成果。

------

**English Answer:**

**Definition:**
 Ethics are moral principles or rules that define acceptable and unacceptable behavior. In ICT, ethics guide professionals to act responsibly when technology can affect individuals or society.

**Applications in ICT:**

1. Protecting data privacy and security;
2. Ensuring fairness and transparency in AI;
3. Preventing misuse of technology;
4. Respecting intellectual property and others’ contributions.

------

### Q11.3: 什么是 **ACS 职业道德守则 (ACS Code of Professional Ethics)**？其核心价值是什么？

**What is the ACS Code of Professional Ethics, and what are its core values?**

------

**中文答案：**

**定义：**
 ACS 职业道德守则是一份指导文件，规定了澳大利亚计算机学会 (ACS) 成员应遵守的**价值观、行为标准与职业责任**。
 它帮助 ICT 专业人员在技术复杂、伦理冲突的情境下做出正确判断。

**核心价值观（Core Values）：**

| 核心价值                     | 含义                                                         |
| ---------------------------- | ------------------------------------------------------------ |
| **诚实 (Honesty)**           | 在信息设计、开发和交流中保持真实与透明；不误导用户或利益相关方。 |
| **可信任 (Trustworthiness)** | 以公共利益为先，维护人类尊严与社会福利。                     |
| **尊重 (Respect)**           | 尊重他人、组织与社会，避免因技术行为造成伤害。               |

**作用：**

- 作为职业行为的指导框架，而非详尽规则手册；
- 随技术发展而保持适应性；
- 强调个人判断与社会责任。

------

**English Answer:**

**Definition:**
 The ACS Code of Professional Ethics outlines the **values, standards, and responsibilities** expected from ICT professionals. It provides guidance in navigating ethical challenges within evolving technologies.

**Core Values:**

| Value               | Description                                                  |
| ------------------- | ------------------------------------------------------------ |
| **Honesty**         | Communicate truthfully and design ICT systems transparently. |
| **Trustworthiness** | Uphold public welfare and human dignity.                     |
| **Respect**         | Treat others fairly and minimize harm through professional actions. |

**Purpose:**

- A guiding framework, not an exhaustive rulebook;
- Stays relevant as technologies evolve;
- Encourages professionals to use sound judgment ethically.

------

### Q11.4: ACS 如何帮助成员应对伦理挑战？

**How does ACS support members in handling ethical challenges?**

------

**中文答案：**

**主要支持机制：**

1. **ACS Ethics Education Program (伦理教育计划)**
   - 在线自学课程，24/7 可访问；
   - 涵盖人工智能伦理、网络安全伦理、数据隐私等主题；
   - 提供数字徽章、证书与持续专业发展 (CPD) 积分；
   - 课程内容与国际技能框架 SFIA 对齐。
2. **案例学习 (Case Studies)**
   - 通过实际情境帮助理解道德冲突与决策方式；
   - 提升在 AI、数据使用等复杂场景中的判断力。
3. **职业行为与投诉程序 (Professional Conduct & Complaints Procedure)**
   - 保障成员的专业问责机制；
   - 维护公众对 ICT 行业的信任与信心。

------

**English Answer:**

**Key Support Mechanisms:**

1. **ACS Ethics Education Program:**
   - Self-paced online learning (24/7 access).
   - Topics include AI ethics, cybersecurity, data privacy.
   - Offers digital badges, certificates, and CPD points.
   - Aligned with **SFIA** (Skills Framework for the Information Age).
2. **Case Studies:**
   - Real-life examples illustrating ethical dilemmas and decisions.
   - Helps professionals develop sound ethical judgment.
3. **Code of Conduct & Complaints Procedure:**
   - Ensures accountability and reinforces public trust.

------

### Q11.5: 为什么职业伦理对未来科技从业者至关重要？

**Why is professional ethics essential for future technology professionals?**

------

**中文答案：**

1. **科技影响面广：** ICT 决策可能影响隐私、就业、安全与公平。
2. **伦理判断是竞争力：** 雇主更重视“负责任的创新”而非单纯技术能力。
3. **维持公众信任：** 职业伦理有助于社会信任数字技术与从业者。
4. **持续学习的重要性：** 技术变化迅速，伦理认知必须与时俱进。

**总结：**
 未来的 ICT 专业人员不仅是代码的编写者，更是**技术伦理的守护者**。
 他们应主动学习、应用并传播伦理价值，以推动技术造福社会。

------

**English Answer:**

1. **Broad impact:** ICT decisions affect privacy, employment, safety, and fairness.
2. **Ethical competence as advantage:** Employers value responsible innovation.
3. **Public trust:** Ethics ensures that society trusts technology and its creators.
4. **Lifelong learning:** Ethical awareness must evolve with rapid technological change.

**Summary:**
 Future ICT professionals are not just developers—they are **guardians of digital ethics**.
 Their responsibility is to apply ethical principles that ensure technology serves humanity.

## 10. 职业道德与专业精神 Professional Ethics and Professionalism

### Q10.1: 什么是计算机专业的职业道德？为什么对软件工程师尤其重要？

**What is professional ethics in computing, and why is it particularly important for software engineers?**

------

**中文答案：**

**定义：**
 职业道德（Professional Ethics）是指从业人员在其职业活动中应遵循的行为规范和价值原则。在计算机科学与软件工程领域，它规范了开发者如何负责任地使用技术、保护用户权益、并避免造成社会伤害。

**软件工程师的重要性：**

1. **影响范围大：** 软件系统支撑现代社会的几乎所有基础设施（银行、电网、医疗、交通）。
2. **潜在风险高：** 程序错误或恶意设计可能导致经济损失、隐私泄露甚至生命威胁。
3. **公众信任：** 社会依赖程序员的专业判断来确保安全、公平与透明。
4. **道德责任：** 工程师不仅是技术执行者，更是社会责任的承担者。

------

**English Answer:**

**Definition:**
 Professional ethics refers to the moral principles and behavioral standards that guide professionals in their work.
 In computing, it defines how software engineers should act responsibly, safeguard user rights, and avoid causing harm to individuals or society.

**Importance for Software Engineers:**

1. **Wide impact:** Software underpins critical systems such as finance, healthcare, and infrastructure.
2. **High risk:** Bugs or unethical design choices can cause major harm or privacy breaches.
3. **Public trust:** Society depends on developers’ integrity and judgment.
4. **Moral duty:** Engineers are not just coders but custodians of ethical technology.

------

### Q10.2: 澳大利亚计算机学会（ACS）的职业道德准则（Code of Ethics）包含哪些核心原则？

**What are the core principles of the Australian Computer Society (ACS) Code of Ethics?**

------

**中文答案：**

ACS职业道德准则包括以下六大核心价值Intellectual Property - Softwar…：

1. **Primacy of the Public Interest（公众利益至上）**
   - 在任何决策中优先考虑公众的安全、隐私与福祉。
   - 如发现安全隐患，应及时报告并采取措施。
2. **Enhancement of Quality of Life（提升生活质量）**
   - 促进技术应用对社会整体幸福感与可持续发展的积极影响。
3. **Honesty（诚实与透明）**
   - 不夸大技术能力，不隐瞒风险，不误导客户或公众。
4. **Competence（专业胜任力）**
   - 持续学习、更新技能，确保提供高质量的技术服务。
5. **Professional Development（职业成长）**
   - 指导并支持同行，帮助行业维持高标准。
6. **Professionalism（专业精神）**
   - 以尊重、公平与合作的态度从事工作，遵守法律与组织规范。

------

**English Answer:**

According to the **Australian Computer Society (ACS) Code of Ethics**, the six core values areIntellectual Property - Softwar…:

1. **Primacy of the Public Interest** – Put the public’s safety, privacy, and welfare above all else.
2. **Enhancement of Quality of Life** – Promote technology that improves human well-being and sustainability.
3. **Honesty** – Be truthful about your abilities, limitations, and potential system risks.
4. **Competence** – Maintain and continually update your skills to deliver competent service.
5. **Professional Development** – Support others’ learning and uphold the profession’s integrity.
6. **Professionalism** – Treat others with fairness and respect; comply with laws and ethical standards.

------

### Q10.3: 在开发或发布软件时，如何平衡商业利益与职业道德？

**How should software engineers balance commercial interests with professional ethics?**

------

**中文答案：**

**平衡策略：**

1. **透明沟通：** 清楚告知客户或用户技术的限制与潜在风险。
2. **拒绝不道德请求：** 若上级要求实施侵犯隐私或违法的功能，应拒绝执行。
3. **数据保护优先：** 对用户数据进行最小化收集与加密存储。
4. **长期责任意识：** 不追求短期利益，应考虑产品对社会和环境的长期影响。
5. **遵循法律与道德双重标准：** 即使法律允许的行为，也要评估其伦理后果。

------

**English Answer:**

**Balancing Strategies:**

1. **Transparency:** Clearly communicate system limitations and potential risks.
2. **Moral resistance:** Refuse unethical or illegal instructions from employers or clients.
3. **Data responsibility:** Apply minimal data collection and ensure encryption and consent.
4. **Long-term accountability:** Consider the societal and environmental impact beyond profit.
5. **Beyond legality:** Evaluate not only what is legal but also what is morally right.

------

### Q10.4: 如果工程师发现公司产品存在隐私风险，应如何应对？

**What should an engineer do upon discovering privacy risks in their company’s product?**

------

**中文答案：**

1. **内部报告：** 首先通过正式渠道（如安全团队或上级主管）报告问题。
2. **记录证据：** 保留沟通记录和技术细节，以便后续追踪。
3. **建议改进：** 提供技术解决方案（如加密、访问控制、数据匿名化）。
4. **若问题被忽视：** 可向公司合规部门或相关监管机构（如 OAIC）举报。
5. **依据ACS准则行事：** 公众利益优先，保护用户隐私是首要责任。

------

**English Answer:**

1. **Report internally** to the relevant team or supervisor.
2. **Document evidence** of the issue for accountability.
3. **Recommend fixes,** such as encryption or anonymization.
4. **If ignored,** escalate to compliance departments or regulators (e.g., OAIC).
5. **Follow ACS principles,** prioritizing public interest and user privacy.

------

### Q10.5: 职业道德在AI与自动化时代有哪些新挑战？

**What new ethical challenges arise in the era of AI and automation?**

------

**中文答案：**

1. **算法偏见（Algorithmic Bias）：** 模型可能基于不公平的数据做出歧视性决策。
2. **数据隐私（Data Privacy）：** 大规模训练数据常包含个人敏感信息。
3. **责任归属（Accountability）：** 当AI造成损害时，难以明确谁应承担责任。
4. **透明度（Transparency）：** 黑箱算法使公众无法理解决策过程。
5. **失业与社会公平（Employment & Fairness）：** 自动化取代部分职业，引发伦理和经济问题。

------

**English Answer:**

1. **Algorithmic Bias:** Unfair data can lead to discriminatory decisions.
2. **Data Privacy:** Massive datasets often contain sensitive personal information.
3. **Accountability:** It’s difficult to assign blame when AI causes harm.
4. **Transparency:** Black-box algorithms lack explainability.
5. **Employment & Fairness:** Automation displaces jobs, raising ethical and social concerns.

------

### 🌏 总结 Summary

- **职业道德**是计算机专业的核心底线。
- **ACS Code of Ethics** 为澳大利亚计算机行业提供了清晰的指导框架。
- 工程师需在商业、技术与伦理三者之间保持平衡，始终以公众利益为首。
