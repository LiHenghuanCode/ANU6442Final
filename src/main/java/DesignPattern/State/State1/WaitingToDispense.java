package DesignPattern.State.State1;

public class WaitingToDispense extends State {
    public WaitingToDispense(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void handle(Event event) {
        if (event == Event.ItemDispensed) {
            machine.setState(new ReadyToCollect(machine));
        }
    }
}
