# 设计模式模拟练习题

## 说明
本文档包含4道设计模式模拟题,覆盖Facade、Factory Method、Iterator和综合应用场景。
每道题都包含完整的题目描述、类图结构、方法签名和实现要求。

---

## 题目1: 智能家居控制系统 (Facade Pattern) - 20分

### 问题背景

你需要为智能家居系统实现一个**统一控制面板**,简化对多个家电设备的操作。系统包含以下设备:

- **灯光系统** (Light): 可调节亮度
- **空调系统** (AirConditioner): 可设置温度
- **音响系统** (MusicPlayer): 可播放音乐
- **窗帘系统** (Curtain): 可开关窗帘

用户希望通过简单的场景模式控制所有设备,而不是分别操作每个设备。

### 类结构

```java
// 设备基类
public abstract class Device {
    protected String name;
    
    public Device(String name) {
        this.name = name;
    }
    
    public abstract void operate();
}

// 具体设备类
public class Light extends Device {
    private int brightness;
    
    public Light(String name, int brightness) {
        super(name);
        this.brightness = brightness;
    }
    
    @Override
    public void operate() {
        // TODO: 实现灯光操作
    }
    
    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }
}

public class AirConditioner extends Device {
    private int temperature;
    
    public AirConditioner(String name, int temperature) {
        super(name);
        this.temperature = temperature;
    }
    
    @Override
    public void operate() {
        // TODO: 实现空调操作
    }
    
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}

public class MusicPlayer extends Device {
    private String playlist;
    
    public MusicPlayer(String name, String playlist) {
        super(name);
        this.playlist = playlist;
    }
    
    @Override
    public void operate() {
        // TODO: 实现音乐播放
    }
    
    public void setPlaylist(String playlist) {
        this.playlist = playlist;
    }
}

public class Curtain extends Device {
    private boolean isOpen;
    
    public Curtain(String name, boolean isOpen) {
        super(name);
        this.isOpen = isOpen;
    }
    
    @Override
    public void operate() {
        // TODO: 实现窗帘操作
    }
    
    public void setOpen(boolean open) {
        this.isOpen = open;
    }
}
```

### 任务要求

#### Task A) 实现各设备的 `operate()` 方法 (8分)

每个设备的`operate()`方法应该执行该设备的核心功能:
- **Light**: 输出 `"[name] is set to brightness [brightness]%"`
- **AirConditioner**: 输出 `"[name] is set to [temperature]°C"`
- **MusicPlayer**: 输出 `"[name] is playing [playlist]"`
- **Curtain**: 输出 `"[name] is [open/closed]"`

#### Task B) 实现 `SmartHomeFacade` 外观类 (12分)

实现外观类提供三种场景模式:

```java
public class SmartHomeFacade {
    private Light livingRoomLight;
    private AirConditioner ac;
    private MusicPlayer player;
    private Curtain curtain;
    
    public SmartHomeFacade() {
        // TODO: 初始化所有设备
        // 灯光默认50%亮度
        // 空调默认24°C
        // 音响默认"Chill Playlist"
        // 窗帘默认关闭
    }
    
    /**
     * 观影模式:
     * - 灯光调至20%
     * - 空调设置22°C
     * - 音响播放"Movie Soundtracks"
     * - 窗帘关闭
     */
    public void movieMode() {
        // TODO: 实现观影模式
    }
    
    /**
     * 睡眠模式:
     * - 灯光关闭(0%)
     * - 空调设置26°C
     * - 音响停止播放("Silent")
     * - 窗帘关闭
     */
    public void sleepMode() {
        // TODO: 实现睡眠模式
    }
    
    /**
     * 离家模式:
     * - 所有设备关闭
     * - 窗帘关闭
     */
    public void leaveHomeMode() {
        // TODO: 实现离家模式
    }
}
```

### 实现要点

- 不允许在场景模式方法中直接创建设备对象
- 必须调用设备的`operate()`方法来执行操作
- 场景切换前需要先配置设备参数,再调用`operate()`

### 测试示例

```java
SmartHomeFacade home = new SmartHomeFacade();
home.movieMode();
/* 期望输出:
Living Room Light is set to brightness 20%
Air Conditioner is set to 22°C
Music Player is playing Movie Soundtracks
Curtain is closed
*/
```

---

## 题目2: 游戏角色创建系统 (Factory Method + Template Method) - 25分

### 问题背景

你正在开发一款RPG游戏,需要实现不同职业的角色创建系统。每个职业都有不同的初始属性和技能。

### 职业设定

1. **战士 (Warrior)**
   - 默认生命值: 150
   - 默认攻击力: 80
   - 技能: "Power Strike"

2. **法师 (Mage)**
   - 默认生命值: 100
   - 默认攻击力: 120
   - 技能: "Fireball"

3. **刺客 (Assassin)**
   - 默认生命值: 120
   - 默认攻击力: 100
   - 技能: "Shadow Step"

### 命名规则

每个职业有预定义的名字列表,创建角色时按顺序循环分配:
- **Warrior名字**: ["Arthas", "Garrosh", "Varian", "Anduin"]
- **Mage名字**: ["Jaina", "Kael'thas", "Medivh"]
- **Assassin名字**: ["Valeera", "Garona", "Maiev", "Akama", "Zul'jin"]

### 类结构

```java
// 角色基类
public abstract class Character {
    private final String id;
    private final String name;
    private final int health;
    private final int attackPower;
    
    public Character(String id, String name, int health, int attackPower) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }
    
    public abstract String useSkill();
    
    public String getId() { return id; }
    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getAttackPower() { return attackPower; }
    
    @Override
    public String toString() {
        return String.format("[%s] %s (HP:%d, ATK:%d) - Skill: %s",
            id, name, health, attackPower, useSkill());
    }
}

// 角色创建器基类
public abstract class CharacterCreator {
    protected int healthConfig;
    protected int attackConfig;
    
    public CharacterCreator(Integer health, Integer attack) {
        // TODO: Task A - 实现构造器
        // 验证参数(不能为负数)
        // 如果为null,使用默认值0(子类会覆盖)
    }
    
    public abstract Character createCharacter(String id, String name);
    
    public abstract String getNextName();
    
    /**
     * 模板方法:创建单个角色
     * 1. 生成UUID作为id
     * 2. 获取下一个名字
     * 3. 创建角色
     */
    public Character createCharacterTemplate() {
        // TODO: Task B - 实现模板方法
    }
    
    /**
     * 批量创建角色
     */
    public List<Character> createParty(int size) {
        // TODO: Task B - 实现批量创建
    }
}
```

### 任务要求

#### Task A) 实现具体角色类和创建器类 (15分)

实现以下类:
1. `Warrior.java`, `Mage.java`, `Assassin.java`
2. `WarriorCreator.java`, `MageCreator.java`, `AssassinCreator.java`

**要求:**
- 构造器验证: health和attack不能为负数,否则抛出`IllegalArgumentException`
- null值处理:
  - `WarriorCreator`: null时使用默认值(150, 80)
  - `MageCreator`: null时使用默认值(100, 120)
  - `AssassinCreator`: null时使用默认值(120, 100)
- `getNextName()`: 循环遍历名字列表

#### Task B) 实现`CharacterCreator`的模板方法 (10分)

实现:
- `createCharacterTemplate()`: 使用UUID生成id,调用`getNextName()`和`createCharacter()`
- `createParty(int size)`: 批量创建指定数量的角色

### 测试示例

```java
CharacterCreator creator = new WarriorCreator(null, null);
List<Character> party = creator.createParty(3);
/* 期望输出:
[uuid1] Arthas (HP:150, ATK:80) - Skill: Power Strike
[uuid2] Garrosh (HP:150, ATK:80) - Skill: Power Strike
[uuid3] Varian (HP:150, ATK:80) - Skill: Power Strike
*/

// 自定义属性
CharacterCreator mageCreator = new MageCreator(200, 150);
Character customMage = mageCreator.createCharacterTemplate();
/* 期望输出:
[uuid4] Jaina (HP:200, ATK:150) - Skill: Fireball
*/
```

---

## 题目3: 音乐播放列表迭代器 (Iterator Pattern) - 20分

### 问题背景

你需要为音乐播放器实现一个播放列表系统,支持多种迭代方式:
1. **顺序播放**: 按添加顺序播放
2. **随机播放**: 打乱顺序播放
3. **收藏优先播放**: 先播放收藏的歌曲,再播放其他歌曲

### 类结构

```java
// 歌曲类
public class Song {
    private String title;
    private String artist;
    private int duration; // 秒
    private boolean isFavorite;
    
    public Song(String title, String artist, int duration, boolean isFavorite) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.isFavorite = isFavorite;
    }
    
    // Getters
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public int getDuration() { return duration; }
    public boolean isFavorite() { return isFavorite; }
    
    @Override
    public String toString() {
        String fav = isFavorite ? "★" : "☆";
        return String.format("%s %s - %s (%d:%02d)",
            fav, title, artist, duration / 60, duration % 60);
    }
}

// 播放列表接口
public interface Playlist {
    void addSong(Song song);
    void addSongs(List<Song> songs);
    PlaylistIterator getIterator(PlayMode mode);
}

// 播放模式枚举
public enum PlayMode {
    SEQUENTIAL,   // 顺序播放
    SHUFFLE,      // 随机播放
    FAVORITES_FIRST // 收藏优先
}

// 迭代器接口
public interface PlaylistIterator {
    boolean hasNext();
    Song next();
    void reset(); // 重置到开头
}
```

### 任务要求

#### Task A) 实现 `MusicPlaylist` 类 (8分)

```java
public class MusicPlaylist implements Playlist {
    private List<Song> songs = new ArrayList<>();
    
    @Override
    public void addSong(Song song) {
        // TODO: 实现添加单首歌曲
    }
    
    @Override
    public void addSongs(List<Song> songs) {
        // TODO: 实现批量添加
    }
    
    @Override
    public PlaylistIterator getIterator(PlayMode mode) {
        // TODO: 根据播放模式返回不同的迭代器
        switch (mode) {
            case SEQUENTIAL:
                return new SequentialIterator(this);
            case SHUFFLE:
                return new ShuffleIterator(this);
            case FAVORITES_FIRST:
                return new FavoritesFirstIterator(this);
            default:
                throw new IllegalArgumentException("Unknown play mode");
        }
    }
    
    public List<Song> getSongs() {
        return new ArrayList<>(songs); // 返回副本
    }
}
```

#### Task B) 实现三种迭代器 (12分)

**1. 顺序迭代器** (3分)
```java
public class SequentialIterator implements PlaylistIterator {
    private MusicPlaylist playlist;
    private int currentIndex = 0;
    
    public SequentialIterator(MusicPlaylist playlist) {
        this.playlist = playlist;
    }
    
    @Override
    public boolean hasNext() {
        // TODO: 实现
    }
    
    @Override
    public Song next() {
        // TODO: 实现
    }
    
    @Override
    public void reset() {
        // TODO: 实现
    }
}
```

**2. 随机迭代器** (4分)
```java
public class ShuffleIterator implements PlaylistIterator {
    private List<Song> shuffledSongs;
    private int currentIndex = 0;
    
    public ShuffleIterator(MusicPlaylist playlist) {
        // TODO: 复制歌曲列表并打乱顺序
        // 提示: 使用Collections.shuffle()
    }
    
    @Override
    public boolean hasNext() {
        // TODO: 实现
    }
    
    @Override
    public Song next() {
        // TODO: 实现
    }
    
    @Override
    public void reset() {
        // TODO: 重置并重新打乱
    }
}
```

**3. 收藏优先迭代器** (5分)
```java
public class FavoritesFirstIterator implements PlaylistIterator {
    private List<Song> orderedSongs;
    private int currentIndex = 0;
    
    public FavoritesFirstIterator(MusicPlaylist playlist) {
        // TODO: 先添加收藏歌曲,再添加非收藏歌曲
    }
    
    @Override
    public boolean hasNext() {
        // TODO: 实现
    }
    
    @Override
    public Song next() {
        // TODO: 实现
    }
    
    @Override
    public void reset() {
        // TODO: 实现
    }
}
```

### 测试示例

```java
MusicPlaylist playlist = new MusicPlaylist();
playlist.addSong(new Song("Shape of You", "Ed Sheeran", 233, true));
playlist.addSong(new Song("Blinding Lights", "The Weeknd", 200, false));
playlist.addSong(new Song("Levitating", "Dua Lipa", 203, true));
playlist.addSong(new Song("Peaches", "Justin Bieber", 198, false));

// 收藏优先播放
PlaylistIterator iterator = playlist.getIterator(PlayMode.FAVORITES_FIRST);
while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
/* 期望输出:
★ Shape of You - Ed Sheeran (3:53)
★ Levitating - Dua Lipa (3:23)
☆ Blinding Lights - The Weeknd (3:20)
☆ Peaches - Justin Bieber (3:18)
*/
```

---

## 题目4: 在线订餐系统 (综合题: Facade + Factory + Iterator) - 30分

### 问题背景

你需要为在线订餐平台实现一个订单处理系统,涉及多个子系统:
- **支付系统** (Payment)
- **库存系统** (Inventory)
- **配送系统** (Delivery)
- **通知系统** (Notification)

同时需要支持不同类型的餐品(Factory)和订单浏览(Iterator)。

### Part A: 餐品工厂 (10分)

#### 餐品类型

```java
public abstract class FoodItem {
    protected String id;
    protected String name;
    protected double price;
    protected int preparationTime; // 准备时间(分钟)
    
    public FoodItem(String id, String name, double price, int preparationTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.preparationTime = preparationTime;
    }
    
    public abstract String getCategory();
    
    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getPreparationTime() { return preparationTime; }
}

// 具体餐品类
public class MainCourse extends FoodItem {
    public MainCourse(String id, String name, double price, int time) {
        super(id, name, price, time);
    }
    
    @Override
    public String getCategory() {
        return "Main Course";
    }
}

public class Beverage extends FoodItem {
    public Beverage(String id, String name, double price, int time) {
        super(id, name, price, time);
    }
    
    @Override
    public String getCategory() {
        return "Beverage";
    }
}

public class Dessert extends FoodItem {
    public Dessert(String id, String name, double price, int time) {
        super(id, name, price, time);
    }
    
    @Override
    public String getCategory() {
        return "Dessert";
    }
}
```

#### 工厂类

```java
public abstract class FoodFactory {
    protected List<String> namePool;
    protected int nameIndex = 0;
    
    public FoodFactory(List<String> namePool) {
        if (namePool == null || namePool.isEmpty()) {
            throw new IllegalArgumentException("Name pool cannot be empty");
        }
        this.namePool = namePool;
    }
    
    public abstract FoodItem createFood(String id, double price, int time);
    
    protected String getNextName() {
        // TODO: 循环获取名字
    }
    
    public FoodItem createFoodTemplate(double price, int time) {
        // TODO: 生成UUID作为id,调用createFood
    }
    
    public List<FoodItem> createMenu(int size, double basePrice, int baseTime) {
        // TODO: 批量创建,每个item价格和时间略有不同
        // 价格: basePrice + (i * 1.5)
        // 时间: baseTime + (i * 2)
    }
}

// 具体工厂
public class MainCourseFactory extends FoodFactory {
    public static final List<String> NAMES = 
        List.of("Steak", "Salmon", "Pasta", "Burger");
    
    public MainCourseFactory() {
        super(NAMES);
    }
    
    @Override
    public FoodItem createFood(String id, double price, int time) {
        // TODO: 实现
    }
}

// 类似实现 BeverageFactory 和 DessertFactory
```

### Part B: 订单管理与迭代器 (10分)

```java
public class Order {
    private String orderId;
    private List<FoodItem> items = new ArrayList<>();
    private String status; // PENDING, CONFIRMED, DELIVERED
    private double totalPrice;
    
    public Order(String orderId) {
        this.orderId = orderId;
        this.status = "PENDING";
    }
    
    public void addItem(FoodItem item) {
        items.add(item);
        totalPrice += item.getPrice();
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    // Getters
    public String getOrderId() { return orderId; }
    public List<FoodItem> getItems() { return new ArrayList<>(items); }
    public String getStatus() { return status; }
    public double getTotalPrice() { return totalPrice; }
}

// 订单管理器
public class OrderManager {
    private List<Order> orders = new ArrayList<>();
    
    public void addOrder(Order order) {
        orders.add(order);
    }
    
    /**
     * 获取迭代器,支持三种过滤模式:
     * - ALL: 所有订单
     * - PENDING: 只显示待处理订单
     * - HIGH_VALUE: 只显示总价>50的订单
     */
    public OrderIterator getIterator(FilterMode mode) {
        // TODO: 根据模式返回对应迭代器
    }
}

public enum FilterMode {
    ALL, PENDING, HIGH_VALUE
}

public interface OrderIterator {
    boolean hasNext();
    Order next();
}

// TODO: 实现 AllOrdersIterator, PendingOrdersIterator, HighValueOrdersIterator
```

### Part C: 订单处理外观 (10分)

```java
// 子系统
public class PaymentSystem {
    public boolean processPayment(String orderId, double amount) {
        System.out.println("Processing payment for order " + orderId + ": $" + amount);
        return amount > 0;
    }
}

public class InventorySystem {
    public boolean checkStock(List<FoodItem> items) {
        System.out.println("Checking stock for " + items.size() + " items");
        return true; // 简化处理
    }
    
    public void reserveItems(List<FoodItem> items) {
        System.out.println("Reserved " + items.size() + " items");
    }
}

public class DeliverySystem {
    public String scheduleDelivery(String orderId, int estimatedTime) {
        System.out.println("Scheduled delivery for order " + orderId + " in " + estimatedTime + " mins");
        return "DELIVERY-" + orderId;
    }
}

public class NotificationSystem {
    public void sendConfirmation(String orderId, String deliveryId) {
        System.out.println("Confirmation sent for order " + orderId);
    }
}

// 外观类
public class OrderFacade {
    private PaymentSystem payment;
    private InventorySystem inventory;
    private DeliverySystem delivery;
    private NotificationSystem notification;
    
    public OrderFacade() {
        // TODO: 初始化所有子系统
    }
    
    /**
     * 处理订单的完整流程:
     * 1. 检查库存
     * 2. 处理支付
     * 3. 预留库存
     * 4. 安排配送
     * 5. 发送通知
     * 6. 更新订单状态为CONFIRMED
     */
    public boolean processOrder(Order order) {
        // TODO: 实现订单处理流程
        // 返回true表示成功,false表示失败
    }
    
    /**
     * 快速下单:创建订单 + 处理订单
     */
    public Order quickOrder(List<FoodItem> items) {
        // TODO: 创建新订单,添加商品,调用processOrder
    }
}
```

### 综合测试示例

```java
// 1. 创建菜单
MainCourseFactory mcFactory = new MainCourseFactory();
BeverageFactory bevFactory = new BeverageFactory();
List<FoodItem> menu = new ArrayList<>();
menu.addAll(mcFactory.createMenu(2, 15.0, 20));
menu.addAll(bevFactory.createMenu(2, 5.0, 5));

// 2. 快速下单
OrderFacade facade = new OrderFacade();
Order order1 = facade.quickOrder(List.of(menu.get(0), menu.get(2)));

// 3. 订单管理
OrderManager manager = new OrderManager();
manager.addOrder(order1);

Order order2 = new Order("ORDER-002");
order2.addItem(menu.get(1));
order2.addItem(menu.get(3));
manager.addOrder(order2);

// 4. 遍历高价值订单
OrderIterator iterator = manager.getIterator(FilterMode.HIGH_VALUE);
while (iterator.hasNext()) {
    Order o = iterator.next();
    System.out.println("Order " + o.getOrderId() + ": $" + o.getTotalPrice());
}
```

### 实现要点

- Factory: 确保名字循环分配,UUID生成正确
- Iterator: 过滤逻辑正确,不修改原始订单列表
- Facade: 处理流程完整,异常情况返回false
- 总配送时间 = 所有商品准备时间之和

---

## 附录: 设计模式要点回顾

### Facade模式
- **目的**: 为复杂子系统提供统一接口
- **关键**: 外观类聚合多个子系统,提供高层方法
- **优点**: 降低客户端与子系统耦合

### Factory Method模式
- **目的**: 定义创建对象的接口,让子类决定实例化哪个类
- **关键**: 抽象工厂定义模板方法,具体工厂实现创建逻辑
- **优点**: 符合开闭原则,易于扩展新产品

### Iterator模式
- **目的**: 提供顺序访问集合元素的方法,无需暴露内部结构
- **关键**: 迭代器接口定义hasNext/next,具体迭代器维护遍历状态
- **优点**: 支持多种遍历方式,集合与遍历逻辑解耦

### 综合应用建议
1. 先理清各模式的职责边界
2. Factory负责对象创建,Iterator负责遍历,Facade负责流程编排
3. 注意对象生命周期管理
4. 保持单一职责原则

---

**加油Weiqi!设计模式的精髓在于分离关注点、提高可扩展性** 🚀
