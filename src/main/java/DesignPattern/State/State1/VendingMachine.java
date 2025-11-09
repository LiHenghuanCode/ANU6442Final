package DesignPattern.State.State1;

public class VendingMachine {

	private State state = new Idle(this);

	public State getState() {

		// START YOUR CODE

		return state;  // 返回当前状态

		// END YOUR CODE
	}

	public void setState(State state) {
		// START YOUR CODE
		this.state = state;  // 设置新状态

		// END YOUR CODE
	}
}

