## 1. Singleton 单例模式

### 模式简介

确保一个类只有一个实例</u>，封装实例，并提供全局访问接口  
**案例**：数据库连接池 - 该应用共享数据库连接的各实例。  
**何时使用**：需要严格控制全局只有一个实例时，如配置管理器、线程池、缓存等。1、多个地方需要访问同一份资源，数据库连接很贵（占内存、占端口）；2、应用需要统一的配置；3、出现场景 = "贵重资源"

### 代码写法要点
1. 添加自己作为成员属性命名instance，并把该属性private static，
2. 构造函数private，防止多余对象产生
3.	提供 public static      getInstance 的类方法返回上面的成员属性实例
4.	注意在类内部用new实例化该单例
5. 根据什么时候实例分为懒汉和饿汉。

### 具体案例

一个原始类
```java
public class Database{
    String url;
    public Database(String url){
        this.url = url;
    }
}
```
改造为单例模式
```java
public class Database{
    String url;
    // 1. 添加成员属性，设为private static。
    private static Database instance= new Database(); // 在这里实例化属于饿汉。
    // 2. 构造方法私有
    private Database(String url){
        this.url = url;
    }
    // 3. 提供public static getInstance 的类方法返回上面的成员属性实例
    public static Database getInstance(){ // 注意返回类型为类自己
        return this.instance;
    }
}
```
