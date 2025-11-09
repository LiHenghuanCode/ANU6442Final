## 4. Facade 外观模式

### 模式简介

为子系统中提供统一的外观接口，使子系统更易使用。  
**案例**：订单系统 - 下单需要检查库存、处理支付、创建物流、发送通知，外观类统一调用。  
**何时使用**：需要简化复杂子系统的使用，客户端不依赖子系统时，并且可以协调子系统调用顺序

### 代码写法要点
1. 重点是进一步封装，把多个操作封装到一个操作里
2. 让客户端少写代码，只需要new 一个类，然后调用方法

### 具体代码
以用 Socket 发送消息为例
改造前
客户端要自己处理Socket连接、数据编码、发送、关闭等多个步骤，非常繁琐：

```java
// 模拟Socket连接类
public class ConnectionManager {
    public void connect(String ip, int port) {
        System.out.println("Connecting to " + ip + ":" + port + " ...");
    }

    public void disconnect() {
        System.out.println("Disconnected from server.");
    }
}

// 模拟消息编码类
public class MessageEncoder {
    public String encode(String message) {
        return "[Encoded]" + message;
    }
}

// 模拟数据发送类
public class MessageSender {
    public void send(String encodedMessage) {
        System.out.println("Sending message: " + encodedMessage);
    }
}

// 客户端（未使用外观）
public class Client {
    public static void main(String[] args) {
        ConnectionManager connection = new ConnectionManager();
        MessageEncoder encoder = new MessageEncoder();
        MessageSender sender = new MessageSender();

        connection.connect("127.0.0.1", 8080);
        String encoded = encoder.encode("Hello Server!");
        sender.send(encoded);
        connection.disconnect();
    }
}

```

使用外观类统一封装整个“发消息”流程，客户端只需一行调用。
```java
// 外观类
public class SocketFacade {
    private ConnectionManager connection;
    private MessageEncoder encoder;
    private MessageSender sender;

    public SocketFacade() {
        this.connection = new ConnectionManager();
        this.encoder = new MessageEncoder();
        this.sender = new MessageSender();
    }

    // 高层接口：一键发消息
    public void sendMessage(String ip, int port, String message) {
        connection.connect(ip, port);
        String encoded = encoder.encode(message);
        sender.send(encoded);
        connection.disconnect();
    }
}

// 客户端（使用外观）
public class Client {
    public static void main(String[] args) {
        SocketFacade socketFacade = new SocketFacade();
        socketFacade.sendMessage("127.0.0.1", 8080, "Hello Server!");
    }
}

```
