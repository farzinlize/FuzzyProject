package com.fuzzybunny;

import java.util.ArrayList;

public class Graph{
	private ArrayList<Node> nodes;

	public Graph(){
		this.nodes = new ArrayList<>();
	}

	public Graph(ArrayList<Node> nodes){
		this.nodes = nodes;
	}
	
	public Node findNode(String name){
		for(Node n:nodes){
			if(n.name.equals(name))
				return n;
		}
		return null;
	}
	
	public void addNode(Node n){
		this.nodes.add(n);
	}
	
	public ArrayList<Node> allVertex(){
		return nodes;
	}

}
