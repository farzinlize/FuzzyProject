package com.fuzzybunny;

import java.util.ArrayList;

public class InsertionList<T extends Element> {

	private ArrayList<T> list;
	
	public InsertionList(){
		this.list = new ArrayList<>();
	}
	
	public void addElement(T element){
		for(int i=0;i<list.size();i++){
			T current = list.get(i);
			if(element.getIntValue()<current.getIntValue()){
				list.add(i, element);
			}
		}
	}
	
}
