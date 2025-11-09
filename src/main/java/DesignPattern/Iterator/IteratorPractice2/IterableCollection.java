package DesignPattern.Iterator.IteratorPractice2;

public interface IterableCollection<T> {

    Iterator<T> createIterator();
}
