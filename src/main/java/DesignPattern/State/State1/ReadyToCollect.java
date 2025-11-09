package DesignPattern.State.State1;

public class ReadyToCollect extends State {
    public ReadyToCollect(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void handle(Event event) {
        // 任何事件都返回Idle状态
        machine.setState(new Idle(machine));
        System.out.println("Got it");
    }
}
