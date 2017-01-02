package com.fuzzybunny;

public class Edge {
	public Node source;
	public Node destination;
	public int weight;
	
	public Edge(Node s, Node d, int w){
		this.source = s;
		this.destination = d;
		this.weight = w;
	}
}
