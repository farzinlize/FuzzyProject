package com.fuzzybunny;

public class FuzzyHeap<T extends Element> {

	@SuppressWarnings("hiding")
	private class Entry<T> {
		private T value;
		private int piority;
		
		public Entry(T value, int p){
			this.value = value;
			piority = p;
		}
	}

	private int[] mark;
	private Entry<T>[] array;
	private int size;

	@SuppressWarnings("unchecked")
	public FuzzyHeap(int cap) {
		size = 1;
		array = new Entry[cap];
		mark = new int[cap];
		for(int i=0;i<cap;i++){
			mark[i]=-1;
		}
	}

	public void addElement(T value, int piority) {
		int checkIndex = mark[value.getIntValue()];
		if(checkIndex!=-1){
			array[checkIndex].piority = piority;
			bubbleUp(checkIndex);
		}
		
		array[size++] = new Entry<T>(value, piority);
		mark[array[size-1].value.getIntValue()] = size-1;
		bubbleUp(size-1);
	}

	public void bubbleUp(int vertex){
		int index = vertex;
		while(index!=1 && array[index].piority<array[index/2].piority){
			Entry<T> temp = array[index];
			array[index] = array[index/2];
			array[index/2] = temp;
			
			int intTemp = mark[array[index].value.getIntValue()];
			mark[array[index].value.getIntValue()] = mark[array[index/2].value.getIntValue()];
			mark[array[index/2].value.getIntValue()] = intTemp;
			
			index = index/2;
		}
	}

	public T deleteMin(){
		Entry<T> min = array[1];
		array[1] = array[(size--)-1];
		
		mark[array[1].value.getIntValue()] = 1; 
		
		bubbleDown(1);
		return min.value;
	}
	
	public void bubbleDown(int vertex){
		int index = vertex;
		while(true){
			if(2*index>=size)
				break ; 	 //no child
			else if(2*index+1==size && array[index].piority>array[2*index].piority){ //one child and need bubble
				Entry<T> temp = array[index];
				array[index] = array[index*2];
				array[index*2] = temp;
				
				int intTemp = mark[array[index].value.getIntValue()];
				mark[array[index].value.getIntValue()] = mark[array[index*2].value.getIntValue()];
				mark[array[index*2].value.getIntValue()] = intTemp;
				
				break;
			}
			else if(2*index+1<size && array[index].piority>Integer.min(array[2*index].piority, array[2*index+1].piority)){ //two child and need bubble
				if(array[2*index].piority < array[2*index+1].piority){
					Entry<T> temp = array[index];
					array[index] = array[index*2];
					array[index*2] = temp;
					
					int intTemp = mark[array[index].value.getIntValue()];
					mark[array[index].value.getIntValue()] = mark[array[index*2].value.getIntValue()];
					mark[array[index*2].value.getIntValue()] = intTemp;
					
					index = index*2;
				}
				else{
					Entry<T> temp = array[index];
					array[index] = array[index*2+1];
					array[index*2+1] = temp;
					
					int intTemp = mark[array[index].value.getIntValue()];
					mark[array[index].value.getIntValue()] = mark[array[index*2].value.getIntValue()];
					mark[array[index*2].value.getIntValue()] = intTemp;
					
					index = index*2+1;
				}
			}
			else
				break;
		}
	}
	
	public boolean isEmpty(){
		if(size==1)
			return true;
		return false;
	}
	public int size() {
		return size-1;
	}

//	public static void main(String[] args){
//		//FuzzyHeap<String> h = new FuzzyHeap<>(20);
//		h.addElement("bye", 10);
//		h.addElement("chetory", 3);
//		h.addElement("lol", 4);
//		h.addElement("sexy", 6);
//		h.addElement("salam", 0);
//		while(!h.isEmpty()){
//			System.out.println(h.deleteMin());
//		}
//	}
	
}
