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
	
//	public Graph(ArrayList<ArrayList<Node>> components){
//		this.nodes = new ArrayList<>();
//		for(ArrayList<Node> compo:components){
//			nodes.addAll(compo);
//		}
//	}
	
	public Node findNode(int number){
		return nodes.get(number);
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
	
	public ArrayList<Node> allNodes(){
		return nodes;
	}

}
