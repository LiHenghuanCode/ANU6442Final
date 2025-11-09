package DesignPattern.Iterator.IteratorPractice1;

import java.util.ArrayList;
import java.util.List;

public class GreenGrocery implements Grocery<Fruit> {

	private final List<Fruit> fruits = new ArrayList<>();

	public GreenGrocery() {
	}

	public List<Fruit> getFruits() {
		return fruits;
	}

	@Override
	public void restock(List<Fruit> fruits) {

		//START YOUR CODE
		this.fruits.addAll(fruits);
		//END YOUR CODE
	}

	@Override
	public Iterator<Fruit> getIterator() {
		return new FruitIterator(this);
	}


	/**
	 * Inner class FruitIterator
	 * Implement the Iterator interface to iterate over the fruits in the GreenGrocery
	 */


	public class FruitIterator implements Iterator<Fruit> {

		private final GreenGrocery grocery;

		public FruitIterator(GreenGrocery grocery) {
			this.grocery = grocery;
		}

		int index;

		@Override
		public boolean hasNext() {

			// START YOUR CODE

			//You are allowed to change this return value.
			return index < grocery.getFruits().size();
			// END YOUR CODE
		}

		@Override
		public Fruit next() {
			// START YOUR CODE
			return grocery.getFruits().get(index++);
			//You are allowed to change this return value.
			// END YOUR CODE
		}


	}
}
