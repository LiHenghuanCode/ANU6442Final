## 3. Decorator 装饰器模式

### 模式简介

动态给对象添加<u>额外包装</u>，装饰器提供比继承更灵活的扩展方式。  
**案例**：咖啡加料 - 基础咖啡可以加奶、加糖、加巧克力，任意组合。  
**何时使用**：需要在运行时动态添加功能，且不想使用大量子类时。

### 代码写法要点
1. 重点在于对于拓展功能时，**组合优于继承**，采用装饰器而不是继承去拓展功能
2. 把功能拆分成组件，不同的类去组合这些不同的组件，从而实现不同功能。而不使用大量子类。
3. 定义抽象组件接口：这样不同类的不需要挨个添加不同组件的成员变量，添加一个接口就好
4. 编写具体组件类（被装饰对象）
5. 编写抽象装饰器类 ，这样具体类知道我要怎么装饰要怎么组合。并且
6. 抽象装饰器注入组件，作为成员变量
7. 抽象装饰器类 implements抽象组件接口，这样装饰后的类又可以当作一个组件
8. 具体装饰器类继承抽象装饰器类。


### 具体案例

老代码
1. 打折需要单独写一个类
2. 修改打折额度，比如七折改成八折，需要改源码
3. 当需要打折和积分共用时，需要再单独写一个子类

```java
// 基础接口
public interface Payment {
    void pay(double amount);
}

// 基础实现
public class BasicPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount);
    }
}

// 子类：加上“打折功能”
public class DiscountPayment extends BasicPayment {
    @Override
    public void pay(double amount) {
        double discounted = amount * 0.9;
        System.out.println("Applied 10% discount.");
        super.pay(discounted);
    }
}

// 子类：加上“积分功能”
public class PointsPayment extends BasicPayment {
    @Override
    public void pay(double amount) {
        super.pay(amount);
        System.out.println("Added points for user.");
    }
}

// 客户端
public class Client {
    public static void main(String[] args) {
        Payment p1 = new DiscountPayment();
        p1.pay(100);

        Payment p2 = new PointsPayment();
        p2.pay(200);
    }
}

```
改造后


```java
// 1. 抽象组件
public interface Payment {
    void pay(double amount);
}

// 2. 具体组件
public class BasicPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount);
    }
}

// 3. 抽象装饰器
public abstract class PaymentDecorator implements Payment {
    protected Payment decoratedPayment; // 组合而非继承

    public PaymentDecorator(Payment payment) {
        this.decoratedPayment = payment;
    }

    @Override
    public void pay(double amount) {
        decoratedPayment.pay(amount);
    }
}

// 4. 具体装饰器A：打折
public class DiscountDecorator extends PaymentDecorator {
    public DiscountDecorator(Payment payment) {
        super(payment);
    }

    @Override
    public void pay(double amount) {
        double discounted = amount * 0.9;
        System.out.println("Applied 10% discount.");
        super.pay(discounted);
    }
}

// 5. 具体装饰器B：积分
public class PointsDecorator extends PaymentDecorator {
    public PointsDecorator(Payment payment) {
        super(payment);
    }

    @Override
    public void pay(double amount) {
        super.pay(amount);
        System.out.println("Added points for user.");
    }
}

// 6. 客户端：动态叠加功能
public class Client {
    public static void main(String[] args) {
        Payment payment = new BasicPayment();

        // 动态组合：先打折再加积分
        Payment decorated = new PointsDecorator(new DiscountDecorator(payment));
        decorated.pay(200);
    }
}

```
