package com.fuzzybunny;

import java.util.ArrayList;

public class InsertionList<T extends Element> {

	private ArrayList<T> list;
	
	public InsertionList(){
		this.list = new ArrayList<>();
	}
	
	public T getElement(int index){
		return list.get(index);
	}
	
	public void addElement(T element){
		boolean flag = false;
		for(int i=0;i<list.size();i++){
			T current = list.get(i);
			if(element.getIntValue()<=current.getIntValue()){
				list.add(i, element);
				flag = true;
				break ;
			}
		}
		if(!flag){
			list.add(element);
		}
	}

	public int size() {
		return list.size();
	}
	
}
