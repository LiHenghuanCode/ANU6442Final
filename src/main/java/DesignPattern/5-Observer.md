## 5. Observer 观察者模式

### 模式简介

平台与对象间的一对多关系，当平台发送通知或有其他方法时，所有依赖它的对象都会收到通知并自动更新。  
**案例**：订阅通知 - 用户订阅YouTube频道，新视频上传时自动通知所有订阅者。  
**何时使用**：当一个平台对象的改变需要同时改变其他对象，而且不知道具体有多少对象需要改变时。

### 代码写法要点
1. 重点在于注入成员变量到 Subject里时，用List加接口
2. 定义 Observer（观察者接口）：声明 update() 方法，用于接收通知。
3. 定义 Subject（主题接口）：声明 register()、unregister()、notifyObservers()。
4. 在具体主题类 中维护观察者列表(注意用List或者其他集合)，状态改变时调用通知方法。
5. 在具体观察者类 中实现 update() 方法，定义收到消息时的行为。

### 具体代码

糟糕写法：
聊天服务器类，不用List，
有时有多个类时，应该让它变成接口或者抽象类
```java
// 用户类
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public void receive(String message) {
        System.out.println(name + " received: " + message);
    }
}

// 聊天服务器类（未使用观察者）
public class ChatServer {
    private User user1;
    private User user2;

    public void setUsers(User u1, User u2) {
        this.user1 = u1;
        this.user2 = u2;
    }

    public void newMessage(String message) {
        System.out.println("Server got new message: " + message);
        user1.receive(message);
        user2.receive(message);
    }
}

// 客户端
public class Client {
    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        User alice = new User("Alice");
        User bob = new User("Bob");

        server.setUsers(alice, bob);
        server.newMessage("Hello everyone!");
    }
}

```
1. 注意用接口怼在观察者上
2. 接口注入到具体被观察类里，用List维护
3. 具体被观察类里update方法( 或者类似逻辑）遍历List，调用接口方法


```java
// 1. 观察者接口
public interface Observer {
    void update(String message);
}

// 2. 主题接口
public interface Subject {
    void register(Observer o);
    void unregister(Observer o);
    void notifyObservers(String message);
}

// 具体观察者类：用户
public class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received: " + message);
    }
}

public class ChatServer implements Subject {
    // 
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void unregister(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer o : observers) {
            o.update(message);
        }
    }

    public void newMessage(String message) {
        System.out.println("Server got new message: " + message);
        notifyObservers(message);
    }
}

// 客户端
public class Client {
    public static void main(String[] args) {
        ChatServer server = new ChatServer();

        Observer alice = new User("Alice");
        Observer bob = new User("Bob");
        Observer carol = new User("Carol");

        server.register(alice);
        server.register(bob);
        server.register(carol);

        server.newMessage("Welcome to the chat!");
        System.out.println("---- Bob leaves ----");
        server.unregister(bob);
        server.newMessage("Bob has left the chat.");
    }
}

```
