package com.fuzzybunny;

public class FuzzyHeap<T> {

	private class Entry<T> {
		private T value;
		private int piority;
		
		public Entry(T value, int p){
			this.value = value;
			piority = p;
		}
	}

	private Entry[] array;
	private int size;

	public FuzzyHeap(int cap) {
		size = 1;
		array = new Entry[cap];
	}

	public void addElement(T value, int piority) {
		array[size++] = new Entry<T>(value, piority);
		if(size==2)
			return ;
		bubbleUp(size-1);
	}

	public void bubbleUp(int vertex){
		//left
	}
	
	public boolean isEmpty(){
		if(size==1)
			return true;
		return false;
	}
	public int size() {
		return size-1;
	}

}
