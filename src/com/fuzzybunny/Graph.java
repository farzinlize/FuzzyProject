package com.fuzzybunny;

import java.util.ArrayList;

public class Graph{
	private ArrayList<Node> nodes;

	public Graph(){
		this.nodes = new ArrayList<>();
	}

	public ArrayList<Node> allVertex(){
		return nodes;
	}

}
