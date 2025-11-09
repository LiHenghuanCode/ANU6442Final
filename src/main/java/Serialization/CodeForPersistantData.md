## 一、怎么做持久化 persistentData
Java 序列化（Serialization）
XML 持久化（DOM 方式）
JSON 持久化（使用 Gson 库）
CSV（Comma-Separated Values，逗号分隔值）

以简单的User类为例

```java
public class User  {
    private String name;
    private int id;
    private List<String> skills;
}
```
### 序列化

#### 1. 首先类`implements Serializable`

```java
public class User implements Serializable {
    private static final long serialVersionUID = 1L; // 建议加上版本号
    private String name;
    private int id;
    private List<String> skills;
}
```

#### 2. 序列化（写入文件）,得到文件`user.ser`
```java
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class SerializeUser {
    public static void main(String[] args) {
        User user = new User("Alice", 1001, Arrays.asList("Java", "Spring", "Docker"));
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("user.ser"))) {
            out.writeObject(user); // 写入对象
            System.out.println("User 对象已序列化到文件 user.ser");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

#### 3. 反序列化（从文件恢复对象）
```java
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class DeserializeUser {
    public static void main(String[] args) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("user.ser"))) {
            User user = (User) in.readObject(); // 强制类型转换
            System.out.println("反序列化得到对象: " + user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
#### 4. 多个 User 对象（例如保存到一个 ArrayList<User> 里）一起序列化到文件

本质上是把List作为对象进行了序列化
```java
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SerializeUserList {
    public static void main(String[] args) {
        // 创建多个 User 对象
        List<User> users = new ArrayList<>();
        users.add(new User("Alice", 1001, Arrays.asList("Java", "Spring", "Docker")));
        users.add(new User("Bob", 1002, Arrays.asList("HTML", "CSS", "JS")));
        users.add(new User("Carol", 1003, Arrays.asList("Python", "ML", "AI")));

        // 写入文件
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("users.ser"))) {
            out.writeObject(users);
            System.out.println("用户列表已序列化到文件 users.ser");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class DeserializeUserList {
    public static void main(String[] args) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("users.ser"))) {
            List<User> users = (List<User>) in.readObject();
            System.out.println("从文件反序列化得到用户列表：");
            for (User u : users) {
                System.out.println("  " + u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```

### XML 持久化（DOM 方式）
比如我们有一个 User 对象：

User{name="Alice", id=1001, skills=["Java", "Spring", "Docker"]}

保存为 XML 后会长这样：
```xml
<user>
    <name>Alice</name>
    <id>1001</id>
    <skills>
        <skill>Java</skill>
        <skill>Spring</skill>
        <skill>Docker</skill>
    </skills>
</user>
```
DOM 是 XML 的一种标准处理方式。
它的原理是：
把 XML 整个文件读入内存；
在内存中形成一棵“文档树”（Document Tree）；
每个节点是一个 Element、Text 或 Attribute；
可以像操作树一样创建、遍历、修改、保存。

#### 1.写入 XML 文件（序列化成 XML）
```java
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import java.io.File;
import java.util.Arrays;

public class XMLWriteExample {
    public static void main(String[] args) throws Exception {
        User user = new User("Alice", 1001, Arrays.asList("Java", "Spring", "Docker"));

        // 1. 创建 Document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        // 2. 根节点 <user>
        Element root = doc.createElement("user");
        doc.appendChild(root);

        // 3. 添加子节点 <name>, <id>
        Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(user.getName()));
        root.appendChild(name);

        Element id = doc.createElement("id");
        id.appendChild(doc.createTextNode(String.valueOf(user.getId())));
        root.appendChild(id);

        // 4. 嵌套 <skills>
        Element skills = doc.createElement("skills");
        for (String skill : user.getSkills()) {
            Element s = doc.createElement("skill");
            s.appendChild(doc.createTextNode(skill));
            skills.appendChild(s);
        }
        root.appendChild(skills);

        // 5. 写出到文件
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(new File("user.xml")));

        System.out.println("User 对象已保存为 user.xml");
    }
}

```
### 2.从 XML 文件读取（反序列化）
```java
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLReadExample {
    public static void main(String[] args) throws Exception {
        File xmlFile = new File("user.xml");

        // 1. 解析 XML 文件为 Document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        // 2. 读取节点内容
        String name = doc.getElementsByTagName("name").item(0).getTextContent();
        int id = Integer.parseInt(doc.getElementsByTagName("id").item(0).getTextContent());

        // 3. 读取 skill 列表
        NodeList skillNodes = doc.getElementsByTagName("skill");
        List<String> skills = new ArrayList<>();
        for (int i = 0; i < skillNodes.getLength(); i++) {
            skills.add(skillNodes.item(i).getTextContent());
        }

        User user = new User(name, id, skills);
        System.out.println("从 XML 文件读取的对象：" + user);
    }
}
```
### JSON 持久化（使用 Gson 库）

#### 1. 写入 JSON 文件（序列化）
```java
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.util.Arrays;

public class JSONWriteExample {
    public static void main(String[] args) {
        User user = new User("Alice", 1001, Arrays.asList("Java", "Spring", "Docker"));
        try (FileWriter writer = new FileWriter("user.json")) {
            gson.toJson(user, writer); // 序列化写入文件
            System.out.println("User 对象已保存为 user.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
#### 2. 读取 JSON 文件（反序列化）
```java
import com.google.gson.Gson;
import java.io.FileReader;

public class JSONReadExample {
    public static void main(String[] args) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("user.json")) {
            User user = gson.fromJson(reader, User.class); // 反序列化
            System.out.println("从 JSON 文件读取的对象：" + user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
#### 3.保存多个对象的示例（List<User>）
```java
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.*;

public class JSONListExample {
    public static void main(String[] args) throws Exception {
        List<User> users = Arrays.asList(
            new User("Alice", 1001, Arrays.asList("Java", "Spring")),
            new User("Bob", 1002, Arrays.asList("HTML", "CSS")),
            new User("Carol", 1003, Arrays.asList("Python", "AI"))
        );

        Gson gson = new Gson();

        // 写入文件
        try (FileWriter writer = new FileWriter("users.json")) {
            gson.toJson(users, writer);
            System.out.println("用户列表已保存到 users.json");
        }

        // 从文件读取回来
        try (FileReader reader = new FileReader("users.json")) {
            List<User> list = gson.fromJson(reader, new TypeToken<List<User>>(){}.getType());
            System.out.println("反序列化后的用户列表：");
            list.forEach(System.out::println);
        }
    }
}
```
### CSV
CSV 是一种最简单、最轻量的结构化数据持久化格式。
它本质上就是纯文本文件，每一行表示一条记录，用 逗号（,） 分隔字段。

例如一个用户列表：
```text
name,id,skills
Alice,1001,"Java|Spring|Docker"
Bob,1002,"HTML|CSS|JS"
Carol,1003,"Python|ML|AI"
```
#### 写入 CSV 文件（序列化）
可以手动使用 FileWriter 或 PrintWriter 来写。
```java
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class CSVWriteExample {
    public static void main(String[] args) {
        List<User> users = Arrays.asList(
            new User("Alice", 1001, Arrays.asList("Java", "Spring", "Docker")),
            new User("Bob", 1002, Arrays.asList("HTML", "CSS", "JS")),
            new User("Carol", 1003, Arrays.asList("Python", "ML", "AI"))
        );

        try (PrintWriter writer = new PrintWriter(new FileWriter("users.csv"))) {
            // 写表头
            writer.println("name,id,skills");
            // 写数据行
            for (User u : users) {
                String skillStr = String.join("|", u.getSkills());
                writer.println(u.getName() + "," + u.getId() + ",\"" + skillStr + "\"");
            }

            System.out.println("用户列表已保存到 users.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```
#### 读取 CSV 文件（反序列化）
用 BufferedReader 读取每一行，然后按逗号拆分。
```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReadExample {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("users.csv"))) {
            String line = reader.readLine(); // 读表头
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 3);
                String name = parts[0];
                int id = Integer.parseInt(parts[1]);
                String skillsPart = parts[2].replace("\"", ""); // 去掉引号
                List<String> skills = Arrays.asList(skillsPart.split("\\|"));

                users.add(new User(name, id, skills));
            }

            System.out.println("从 CSV 文件读取的用户列表：");
            users.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```
