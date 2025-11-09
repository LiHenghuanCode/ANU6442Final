package DesignPattern.Facade.Facade1;

public class Calculator {

	Operation operation;
	/*
	 */

	public double add(double a, double b) {

		//START YOUR CODE
		operation = new Addition(a, b);
		return operation.evaluate();
		//END YOUR CODE
	}

	public double subtract(double a, double b) {

		//START YOUR CODE
		operation = new Subtraction(a, b);
		return operation.evaluate();
		//END YOUR CODE
	}

	public double multiply(double a, double b) {

		//START YOUR CODE
		operation = new Multiplication(a, b);
		return operation.evaluate();
		//END YOUR CODE
	}

	public double divide(double a, double b) {

		//START YOUR CODE
		operation = new Division(a, b);
		return operation.evaluate();
		//END YOUR CODE
	}
}
