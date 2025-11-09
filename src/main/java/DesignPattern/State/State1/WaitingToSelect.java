package DesignPattern.State.State1;

public class WaitingToSelect extends State {
    public WaitingToSelect(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void handle(Event event) {
        if (event == Event.ItemSelected) {
            machine.setState(new WaitingToPay(machine));
        } else if (event == Event.Cancelled || event == Event.Timeout) {
            machine.setState(new Idle(machine));
        }
    }
}
