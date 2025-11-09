package DesignPattern.Facade.Facade1;

public class Division extends Operation {

	public Division(double a, double b) {
		super(a, b);
	}

	@Override
	public double evaluate() {

		//START YOUR CODE
		if (b == 0.0) {
			throw new ArithmeticException("Division by zero");
		}
		return a / b;
		//END YOUR CODE
	}
}
