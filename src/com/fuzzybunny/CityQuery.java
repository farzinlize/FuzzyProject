package com.fuzzybunny;

import java.util.ArrayList;

public class CityQuery implements Element {

	private final Node commenNode;
	private final boolean mode;
	private ArrayList<Query> queris;
	private int maxCity;
	
	public CityQuery(Node n, boolean mode){
		this.commenNode = n;
		this.mode = mode;
	}
	
	public void addQuery(Query q){
		this.queris.add(q);
	}
	
	public void removeQuery(Query q){
		this.queris.remove(q);
	}
	
	@Override
	public int getIntValue() {
		return commenNode.number;
	}

}
