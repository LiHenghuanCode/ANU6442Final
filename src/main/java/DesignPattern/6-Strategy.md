## 6. Strategy 策略模式

### 模式简介

定义多种算法,把它们封装到不同的策略里，继承同一个策略接口,并使它们在上下文中根据要求进行替换。  
**案例**：支付方式 - 同一个订单可以选择支付宝、微信或银行卡支付,算法可切换（算法为独立代码，非模板）。  
**何时使用**：编译时上下文对象先决定使用哪个策略，运行时可以改变为不同策略。客户端知道选择哪个策略。

### 代码写法要点:

1. 定义策略接口（Strategy）：声明统一的算法方法（如 pay()）。
2. 定义具体策略类（Concrete Strategy）：实现不同算法逻辑（如支付宝支付、微信支付）。
3. 定义上下文类（Context）：持有一个策略对象，通过组合方式执行策略逻辑。
4. 客户端在运行时选择策略：通过传入不同策略对象切换行为，无需修改上下文代码。
5. 优点：消除大量 if/else 逻辑，算法独立可扩展，符合开闭原则。

### 具体代码
以“支付方式”场景为例：

#### 改造前
客户端直接用 if-else 选择支付方式，代码臃肿、难以扩展：
每增加一种支付方式，都要修改 PaymentService。
不符合开闭原则，扩展性差。
```java
public class PaymentService {
    public void pay(String type, double amount) {
        if (type.equalsIgnoreCase("alipay")) {
            System.out.println("Paying $" + amount + " using Alipay");
        } else if (type.equalsIgnoreCase("wechat")) {
            System.out.println("Paying $" + amount + " using WeChat Pay");
        } else if (type.equalsIgnoreCase("credit")) {
            System.out.println("Paying $" + amount + " using Credit Card");
        } else {
            System.out.println("Unknown payment type.");
        }
    }
}

// 客户端（未使用策略模式）
public class Client {
    public static void main(String[] args) {
        PaymentService service = new PaymentService();
        service.pay("wechat", 100);
    }
}
```

#### 改造后：使用策略模式
```java
// 策略接口
public interface PaymentStrategy {
    void pay(double amount);
}

// 具体策略1：支付宝支付
public class AlipayStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paying $" + amount + " using Alipay");
    }
}

// 具体策略2：微信支付
public class WeChatStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paying $" + amount + " using WeChat Pay");
    }
}

// 具体策略3：信用卡支付
public class CreditCardStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paying $" + amount + " using Credit Card");
    }
}

// 上下文类：持有策略对象
public class PaymentContext {
    private PaymentStrategy strategy;

    public PaymentContext(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    // 执行策略
    public void executePayment(double amount) {
        strategy.pay(amount);
    }

    // 可运行时动态切换策略
    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }
}

// 客户端
public class Client {
    public static void main(String[] args) {
        // 选择不同支付策略
        PaymentContext context = new PaymentContext(new WeChatStrategy());
        context.executePayment(99.99);

        // 动态切换为支付宝
        context.setStrategy(new AlipayStrategy());
        context.executePayment(199.99);
    }
}

```
