package ch.fhnw.oop2.collections;

import java.util.NoSuchElementException;

public class ComparableVector<T extends Comparable<T>> extends Vector<T> {

	/**
	 * Default constructor
	 */
	public ComparableVector() {
		super();
	}
	/**
	 * Copy constructor
	 * @param v
	 */
	public ComparableVector(ComparableVector<T> v){
		super(v);
	}

	/**
	 * Get max element using the compareTo() method
	 * @return
	 * @throws NoSuchElementException If the container is empty
	 */
	public T getMax(){
		if(isEmpty()){ throw new NoSuchElementException(); }
		T max = get(0);
		for(int i = size() - 1; i >= 0; i--){
			if(get(i).compareTo(max) > 0){ max = get(i); }
		}
		return max;
	}

	/**
	 * Get min element using the compareTo() method
	 * @return
	 * @throws NoSuchElementException If the container is empty
	 */
	public T getMin(){
		if(isEmpty()){ throw new NoSuchElementException(); }
		T min = get(0);
		for(int i = size() - 1; i >= 0; i--){
			if(get(i).compareTo(min) < 0){ min = get(i); }
		}
		return min;
	}
}
