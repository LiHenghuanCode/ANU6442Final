# 序列化练习题

本文档包含四道涵盖 XML 和 JSON 序列化的 Java 练习题，帮助你准备考试。

---

## 题目 1：图书馆藏书集合（XML 序列化）

### 问题描述

本任务的目标是编写一个程序，以 XML 格式加载/存储图书列表。`BookCollection.java` 类包含一个 `Book` 实例列表。每本书都有 `title` 和 `author`，需要作为 XML 节点的属性保存。此外，每本书可以有三个可选属性：`publisher`、`year` 和 `genres`。`genres` 属性包含一个 `genre` 元素列表。注意，并非每本书都有全部三个属性，某些书的属性可能缺失。

### 提供的类

```java
public class Book {
    private String title;
    private String author;
    private String publisher;
    private Integer year;
    private List<String> genres;
    
    // 已提供构造器、getter、setter 和建造者方法
    // withTitle(), withAuthor(), withPublisher(), withYear(), addGenre()
}

public class BookCollection {
    private final List<Book> books;
    
    public BookCollection(List<Book> books) {
        this.books = books;
    }
    
    public List<Book> getBooks() {
        return books;
    }
}
```

### 你的任务

在 `BookCollection.java` 中实现以下两个方法：

1. **`saveToFile(File file)`** - 将图书列表保存到 XML 文件
2. **`loadFromFile(File file)`** - 从 XML 文件加载并创建 `BookCollection`

### XML 格式示例

```xml
<books>
    <book title="1984" author="George Orwell">
        <publisher>Penguin Books</publisher>
        <year>1949</year>
        <genres>
            <genre>Dystopian</genre>
            <genre>Science Fiction</genre>
        </genres>
    </book>
    <book title="Pride and Prejudice" author="Jane Austen">
        <year>1813</year>
    </book>
</books>
```

### 提示

- 使用 `Element` 的 `setAttribute(String name, String value)` 方法设置 `title` 和 `author` 属性
- 使用 `Element` 的 `getAttribute(String name)` 方法获取 `title` 和 `author` 属性
- 仔细处理可选属性（publisher、year、genres）
- 使用 `getElementsByTagName()` 在加载时获取子元素

### 需要考虑的测试用例

- 具有所有属性的书籍
- 仅有 title 和 author 的书籍
- 有 genres 但没有 publisher 的书籍
- 有 year 但没有 genres 的书籍

---

## 题目 2：医院患者记录（JSON 序列化）

### 问题描述

本任务的目标是编写一个程序，以 JSON 格式加载/存储医院的患者记录。`Hospital.java` 类包含医院名称和一个 `Patient` 实例列表。每个患者都有患者姓名和一个诊断列表。`hospital.json` 文件中存在一些重复的患者。你的任务是在 `Hospital.java` 类中实现三个方法，以从给定的 JSON 文件加载数据，合并同名患者的诊断，并将处理后的数据写入 JSON 文件。

### 提供的类

```java
public class Patient {
    private String name;
    private List<String> diagnoses;
    
    public Patient(String name, List<String> diagnoses) {
        this.name = name;
        this.diagnoses = diagnoses;
    }
    
    // 已提供 getter 和 setter
}

public class Hospital {
    private String name;
    private List<Patient> patients;
    
    // 已提供 getter 和 setter
}
```

### 你的任务

在 `Hospital.java` 中实现以下三个方法：

1. **`loadFromJsonFile(File file)`** - 从文件加载 JSON 数据以创建医院对象
2. **`mergePatients()`** - 合并同名患者的诊断
3. **`saveToJsonFile(File file)`** - 将医院对象保存到 JSON 文件

### 示例输入（hospital.json）

```json
{
  "name": "中心医院",
  "patients": [
    {
      "name": "张三",
      "diagnoses": ["流感", "高血压"]
    },
    {
      "name": "李四",
      "diagnoses": ["糖尿病"]
    },
    {
      "name": "张三",
      "diagnoses": ["哮喘"]
    }
  ]
}
```

### 示例输出（hospital_processed.json）

```json
{
  "name": "中心医院",
  "patients": [
    {
      "name": "张三",
      "diagnoses": ["流感", "高血压", "哮喘"]
    },
    {
      "name": "李四",
      "diagnoses": ["糖尿病"]
    }
  ]
}
```

### 要求

- 重复的诊断不应被添加到合并后的列表中
- 具有唯一姓名的患者应保持不变
- 使用 Gson 库进行 JSON 序列化/反序列化
- 考虑使用 `Map<String, List<String>>` 进行合并

---

## 题目 3：产品库存系统（XML 序列化）

### 问题描述

本任务的目标是编写一个程序，以 XML 格式加载/存储产品库存。`Inventory.java` 类包含一个 `Product` 实例列表。每个产品都有 `name` 和 `category`，需要作为 XML 节点的属性保存。此外，每个产品可以有三个可选属性：`price`、`stock` 和 `specifications`。`price` 属性有两个属性：`amount` 和 `currency`。`specifications` 属性包含一个 `spec` 元素列表，其中每个 spec 都有一个 `key` 属性和一个文本值。

### 提供的类

```java
public class Price {
    private final Double amount;
    private final String currency;
    
    public Price(Double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }
    
    // 已提供 getter
}

public class Product {
    private String name;
    private String category;
    private Price price;
    private Integer stock;
    private Map<String, String> specifications;
    
    // 已提供构造器、getter 和建造者方法
    // withName(), withCategory(), withPrice(), withStock(), addSpecification()
}

public class Inventory {
    private final List<Product> products;
    
    public Inventory(List<Product> products) {
        this.products = products;
    }
    
    public List<Product> getProducts() {
        return products;
    }
}
```

### 你的任务

在 `Inventory.java` 中实现以下两个方法：

1. **`saveToFile(File file)`** - 将产品列表保存到 XML 文件
2. **`loadFromFile(File file)`** - 从 XML 文件加载并创建 `Inventory`

### XML 格式示例

```xml
<inventory>
    <product name="笔记本电脑" category="电子产品">
        <price amount="6999.99" currency="CNY"/>
        <stock>25</stock>
        <specifications>
            <spec key="内存">16GB</spec>
            <spec key="存储">512GB SSD</spec>
            <spec key="处理器">Intel i7</spec>
        </specifications>
    </product>
    <product name="办公椅" category="家具">
        <stock>50</stock>
    </product>
</inventory>
```

### 提示

- `price` 元素是自闭合的，带有属性
- 规格使用键值结构，键作为属性
- 仔细处理可选属性
- 使用 `HashMap` 在加载时存储规格

### 特别注意事项

- 将 `amount` 属性解析为 `Double` 类型
- 处理没有价格或规格的产品
- 确保规格按照它们在 XML 中出现的顺序存储

---

## 题目 4：餐厅菜单系统（JSON 序列化）

### 问题描述

本任务的目标是编写一个程序，以 JSON 格式加载/存储餐厅菜单数据。`Restaurant.java` 类包含餐厅名称和一个 `MenuItem` 实例列表。每个菜单项都有菜品名称和一个配料列表。`menu.json` 文件中存在重复的菜单项（代表季节性变化或食谱更新）。你的任务是实现三个方法来加载 JSON 文件，合并同名菜品的配料（保留不同版本中的所有唯一配料），并保存处理后的数据。

### 提供的类

```java
public class MenuItem {
    private String name;
    private List<String> ingredients;
    
    public MenuItem(String name, List<String> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }
    
    // 已提供 getter 和 setter
}

public class Restaurant {
    private String name;
    private List<MenuItem> menuItems;
    
    // 已提供 getter 和 setter
}
```

### 你的任务

在 `Restaurant.java` 中实现以下三个方法：

1. **`loadFromJsonFile(File file)`** - 从文件加载 JSON 数据以创建餐厅对象
2. **`mergeMenuItems()`** - 合并同名菜单项的配料
3. **`saveToJsonFile(File file)`** - 将餐厅对象以格式化输出的方式保存到 JSON 文件

### 示例输入（menu.json）

```json
{
  "name": "美食厨房",
  "menuItems": [
    {
      "name": "凯撒沙拉",
      "ingredients": ["罗马生菜", "面包丁", "帕尔马干酪", "凯撒酱"]
    },
    {
      "name": "玛格丽特披萨",
      "ingredients": ["面团", "番茄酱", "马苏里拉奶酪"]
    },
    {
      "name": "凯撒沙拉",
      "ingredients": ["罗马生菜", "面包丁", "帕尔马干酪", "凤尾鱼", "柠檬"]
    },
    {
      "name": "玛格丽特披萨",
      "ingredients": ["面团", "番茄酱", "新鲜马苏里拉", "罗勒"]
    }
  ]
}
```

### 示例输出（menu_processed.json）

```json
{
  "name": "美食厨房",
  "menuItems": [
    {
      "name": "凯撒沙拉",
      "ingredients": ["罗马生菜", "面包丁", "帕尔马干酪", "凯撒酱", "凤尾鱼", "柠檬"]
    },
    {
      "name": "玛格丽特披萨",
      "ingredients": ["面团", "番茄酱", "马苏里拉奶酪", "新鲜马苏里拉", "罗勒"]
    }
  ]
}
```

### 要求

- 使用带有 `GsonBuilder().setPrettyPrinting()` 的 Gson 进行格式化输出
- 保持首次出现的菜单项的顺序
- 保留所有唯一配料（即使是相似的配料，如"马苏里拉奶酪"和"新鲜马苏里拉"）
- 使用适当的数据结构进行高效合并

### 实现策略

1. 使用 `LinkedHashMap` 保持插入顺序
2. 对于每个菜单项，检查名称是否已存在
3. 如果存在，合并配料同时避免完全重复
4. 如果是新的，添加到 map 中并复制其配料列表

---

## 所有题目的通用提示

### XML 序列化提示

1. **创建文档：**
   ```java
   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
   DocumentBuilder builder = factory.newDocumentBuilder();
   Document doc = builder.newDocument();
   ```

2. **设置属性：**
   ```java
   element.setAttribute("attributeName", value);
   ```

3. **创建带文本的元素：**
   ```java
   Element elem = doc.createElement("tagName");
   elem.setTextContent(textValue);
   ```

4. **写入文件：**
   ```java
   TransformerFactory tf = TransformerFactory.newInstance();
   Transformer transformer = tf.newTransformer();
   transformer.setOutputProperty(OutputKeys.INDENT, "yes");
   transformer.transform(new DOMSource(doc), new StreamResult(file));
   ```

5. **从文件读取：**
   ```java
   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
   DocumentBuilder builder = factory.newDocumentBuilder();
   Document doc = builder.parse(file);
   NodeList nodes = doc.getElementsByTagName("tagName");
   ```

### JSON 序列化提示

1. **使用 Gson：**
   ```java
   Gson gson = new Gson();
   // 或使用格式化输出：
   Gson gson = new GsonBuilder().setPrettyPrinting().create();
   ```

2. **从文件读取：**
   ```java
   try (FileReader reader = new FileReader(file)) {
       return gson.fromJson(reader, ClassName.class);
   }
   ```

3. **写入文件：**
   ```java
   try (FileWriter writer = new FileWriter(file)) {
       gson.toJson(object, writer);
   }
   ```

4. **合并策略：**
   - 使用 `HashMap` 或 `LinkedHashMap` 跟踪唯一条目
   - 在处理前检查集合是否为 null/空
   - 创建新列表以避免引用问题

### 常见陷阱

- **空值检查：** 在处理之前始终检查可选属性是否为 null
- **Element vs Node：** 遍历 NodeList 时使用 `instanceof Element`
- **列表初始化：** 在添加元素之前初始化列表
- **深拷贝：** 合并时，创建新的 ArrayList 实例以避免引用问题
- **属性 vs 文本内容：** 了解何时在 XML 中使用属性与文本节点
- **大小写敏感性：** XML 和 JSON 都是大小写敏感的

### 测试策略

1. 测试完整对象（所有属性都存在）
2. 测试最小对象（仅必需属性）
3. 测试空集合
4. 测试带重复项的合并
5. 测试不带重复项的合并
6. 验证输出文件格式是否与预期结构匹配

---

## 练习流程

对于每个问题：

1. **理解结构：** 研究提供的类及其关系
2. **分析格式：** 仔细查看示例输入/输出
3. **规划方法：** 在编码前勾勒出算法
4. **逐步实现：** 从基本结构开始，然后添加可选属性
5. **增量测试：** 在集成之前独立测试每个方法
6. **处理边界情况：** 考虑 null 值、空列表和重复数据
7. **验证输出：** 检查输出格式是否与规范完全匹配

祝你练习顺利！
