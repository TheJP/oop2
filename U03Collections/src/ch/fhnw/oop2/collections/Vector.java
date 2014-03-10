package ch.fhnw.oop2.collections;

import java.util.Iterator;

/**
 * Generic vector (similar to ArrayList)
 * @author JP
 *
 * @param <T>
 */
public class Vector<T> implements Iterable<T> {

	//Initial capacity of the vector
	protected final int INITIAL_CAPACITY = 16;
	protected T[] content;
	private int size = 0;

	/**
	 * Default constructor. Creates a vector with an initial capacitiy of 16
	 */
	@SuppressWarnings("unchecked")
	public Vector(){
		content = (T[]) new Object[INITIAL_CAPACITY];
	}
	/**
	 * Copy constructor
	 * @param v
	 */
	@SuppressWarnings("unchecked")
	public Vector(Vector<T> v){
		content = (T[]) new Object[v.capacity()];
		size = v.size();
		for(int i = 0; i < size; i++){
			content[i] = v.get(i);
		}
	}
	/**
	 * Access element at index
	 * @param index
	 * @return
	 */
	public T get(int index){
		if(index >= size()){ throw new IndexOutOfBoundsException(); }
		return content[index];
	}
	/**
	 * Write element at index
	 * @param index
	 * @param f
	 */
	public void set(int index, T value){
		if(index >= size()){ throw new IndexOutOfBoundsException(); }
		content[index] = value;
	}
	/**
	 * Returns the element count
	 * @return
	 */
	public int size(){
		return size;
	}
	/**
	 * Returns the current capacity of the internal array
	 * @return
	 */
	public int capacity(){
		return content.length;
	}
	/**
	 * Adds an element at the back of the vector
	 * Executes a resize if needed (Resize doubles the capacity)
	 * @param value
	 */
	@SuppressWarnings("unchecked")
	public void pushBack(T value){
		//Resize code
		if(size() >= capacity()){
			T[] tmp = (T[])new Object[capacity()*2];
			for(int i = 0; i < size(); i++){
				tmp[i] = content[i];
			}
			content = tmp;
		}
		//push code
		content[size++] = value;
	}
	/**
	 * Removes an element at the front of the vector
	 * @return
	 */
	public T popFront(){
		if(size <= 0){ return null; }
		T tmp = content[0];
		for(int i = 1; i < size; i++){
			content[i-1] = content[i];
		}
		size--;
		return tmp;
	}

	/**
	 * Checks if the container is empty
	 * @return true if the container is empty
	 */
	public boolean isEmpty(){
		return size <= 0;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private int index = 0;
			@Override
			public boolean hasNext() {
				return index < size();
			}
			@Override
			public T next() {
				return get(index++);
			}
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
}
