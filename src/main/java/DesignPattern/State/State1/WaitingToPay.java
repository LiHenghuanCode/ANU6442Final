package DesignPattern.State.State1;

public class WaitingToPay extends State {
    public WaitingToPay(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void handle(Event event) {
        if (event == Event.ItemPaid) {
            machine.setState(new WaitingToDispense(machine));
        } else if (event == Event.Cancelled || event == Event.Timeout) {
            machine.setState(new Idle(machine));
        }
    }
}
