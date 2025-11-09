## 8. State 状态模式

### 模式简介

对象<u>状态改变</u>时，行为改变，类似switch case。
**案例**：订单状态 - 订单有待支付、已支付、配送中、已完成等状态，每个状态下的行为不同。  
**何时使用**：本来都是if-else改变状态，现在有很多子类状态，而且添加新状态只要写子类；而且状态之间有相互关联，一个状态可以切换到下一个状态


### 代码写法要点
1. 定义状态接口（State）：声明状态相关的公共行为（如 handle()、doAction()）。
2. 定义具体状态类（ConcreteState）：实现状态接口，每个类对应一种状态。
3. 定义环境类（Context）也可能名字是StateManager：
持有一个 State 类型的成员变量。
对外提供行为方法，内部将调用当前状态对象的方法。
提供 setState() 方法在运行时切换状态对象。
4. 具体状态类（ConcreteState）根据事件触发调用setState()切换当前状态

### 具体代码
以游戏角色状态为例：

#### 改造前
直接使用 if/else 判断状态：
每次新增或修改状态都要改 if 逻辑。
难以维护和扩展。
```java
public class Player {
    private String state = "idle"; // 站立、奔跑、跳跃

    public void handleInput(String input) {
        if (state.equals("idle")) {
            if (input.equals("run")) {
                System.out.println("Player starts running.");
                state = "running";
            } else if (input.equals("jump")) {
                System.out.println("Player jumps.");
                state = "jumping";
            }
        } else if (state.equals("running")) {
            if (input.equals("stop")) {
                System.out.println("Player stops running.");
                state = "idle";
            } else if (input.equals("jump")) {
                System.out.println("Player jumps while running.");
                state = "jumping";
            }
        } else if (state.equals("jumping")) {
            System.out.println("Player lands and returns to idle.");
            state = "idle";
        }
    }
}

```
#### 改造后：使用状态模式
```java
// 状态接口
public interface State {
    void handleInput(PlayerContext context, String input);
}

// 具体状态：站立
public class IdleState implements State {
    @Override
    public void handleInput(PlayerContext context, String input) {
        if (input.equals("run")) {
            System.out.println("Player starts running.");
            context.setState(new RunningState());
        } else if (input.equals("jump")) {
            System.out.println("Player jumps from idle.");
            context.setState(new JumpingState());
        } else {
            System.out.println("Idle: ignoring input " + input);
        }
    }
}

// 具体状态：奔跑
public class RunningState implements State {
    @Override
    public void handleInput(PlayerContext context, String input) {
        if (input.equals("stop")) {
            System.out.println("Player stops running.");
            context.setState(new IdleState());
        } else if (input.equals("jump")) {
            System.out.println("Player jumps while running.");
            context.setState(new JumpingState());
        } else {
            System.out.println("Running: ignoring input " + input);
        }
    }
}

// 具体状态：跳跃
public class JumpingState implements State {
    @Override
    public void handleInput(PlayerContext context, String input) {
        System.out.println("Player lands and returns to idle.");
        context.setState(new IdleState());
    }
}

// 上下文类：持有当前状态
public class PlayerContext {
    private State state;

    public PlayerContext() {
        this.state = new IdleState(); // 初始状态
    }

    public void setState(State state) {
        this.state = state;
    }

    public void handleInput(String input) {
        state.handleInput(this, input);
    }
}

// 客户端
public class Client {
    public static void main(String[] args) {
        PlayerContext player = new PlayerContext();
        player.handleInput("run");
        player.handleInput("jump");
        player.handleInput("stop");
        player.handleInput("run");
    }
}

```
