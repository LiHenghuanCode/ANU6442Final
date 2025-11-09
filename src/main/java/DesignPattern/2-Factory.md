## 2. Factory 工厂模式

### 模式简介

**案例**：数据库工厂 - 根据配置创建MySQL、Oracle或PostgreSQL连接。  
**何时使用**：创建逻辑复杂、需要根据条件决定创建哪种对象；对象需要很多，但是模板都差不多（比如敌人对象）

### 代码写法要点
1. 重点在于把new 这个写法，封装到工厂类里
2. 工厂类里提供一个static方法去创建对应的类实例
3. 这个方法使用时其实已经暗中包含了模板方法或者多态的写法

### 具体案例

这是一个写的不好的代码
缺陷：
1. 低层程序员(Client)自己创建实例
2. 低层程序员自己 **写逻辑决定** 创建哪个类
```java
// 支付接口
public interface Payment {
    void pay(double amount);
}

// 不同支付方式的实现
public class CreditCardPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Credit Card.");
    }
}

public class PayPalPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal.");
    }
}

public class WeChatPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid ¥" + amount + " using WeChat Pay.");
    }
}

// 客户端（改造前）
public class Client {
    public static void main(String[] args) {
        String type = "paypal";  // 用户选择的支付方式

        Payment payment = null;
        if (type.equalsIgnoreCase("credit")) {
            payment = new CreditCardPayment();
        } else if (type.equalsIgnoreCase("paypal")) {
            payment = new PayPalPayment();
        } else if (type.equalsIgnoreCase("wechat")) {
            payment = new WeChatPayment();
        }

        if (payment != null) {
            payment.pay(199.99);
        }
    }
}
```
工厂方法改造：
1. 保持接口和三个支付类不变
2. 建一个PaymentFactory类，**创建和决定逻辑**封装到创建方法类里
3. 创建方法类static
4. 客户端只管传参并调用PaymentFactory类的类方法。

```java
public class PaymentFactory {
    public static Payment createPayment(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Payment type cannot be null");
        }

        switch (type.toLowerCase()) {
            case "credit":
                return new CreditCardPayment();
            case "paypal":
                return new PayPalPayment();
            case "wechat":
                return new WeChatPayment();
            default:
                throw new IllegalArgumentException("Unknown payment type: " + type);
        }
    }
}

// 客户端调用
public class Client {
    public static void main(String[] args) {
        Payment payment1 = PaymentFactory.createPayment("credit");
        payment1.pay(299.99);

        Payment payment2 = PaymentFactory.createPayment("paypal");
        payment2.pay(88.88);
    }
}
```
