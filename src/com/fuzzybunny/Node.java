package com.fuzzybunny;

import java.util.ArrayList;

public class Node {
	private boolean onStack;
	private boolean defined;
	private boolean topologyed;
	private int index;
  	private int lowlink;
  	private int color;
  	private ArrayList<Edge> outEdges;
  	private int topologyID;
  	public String name;
  	public int number;
  	
  	public Node(String name, int num){
  		this.onStack = false;
  		this.defined = false;
  		this.topologyed = false;
  		this.index = 0;
  		this.lowlink =0;
  		this.color = -1;
  		this.name = name;
  		this.outEdges = new ArrayList<>();
  		this.number = num;
  	}
  	
  	public ArrayList<Node> allNeighbours(){
  		ArrayList<Node> out = new ArrayList<>();
  		for(Edge e:outEdges){
  			out.add(e.destination);
  		}
  		return out;
  	}
  	
  	public void addEdge(Node dest, int witgh){
  		this.outEdges.add(new Edge(this, dest, witgh));
  	}
  	public ArrayList<Edge> allEdges(){
  		return outEdges;
  	}
  	public boolean isDefined(){
  		return this.defined;
  	}
  	public void setDefined(boolean state){
  		this.defined = state;
  	}
  	public int getIndex() {
  		return index;
	}
  	public void setIndex(int index) {
  		this.index = index;
	}
  	public int getLowlink() {
  		return lowlink;
  	}
  	public void setLowlink(int lowlink) {
  		this.lowlink = lowlink;
  	}
  	public boolean isOnStack() {
  		return onStack;
  	}
  	public int getTopologyID() throws Exception{
  		if(topologyed)
  			return topologyID;
  		else
  			throw new Exception("not topolyed yet. node: " + name);
  	}
  	public void setTopologyID(int ID){
  		this.topologyID = ID;
  		this.topologyed = true;
  	}
  	public void setOnStack(boolean onStack) {
  		this.onStack = onStack;
  	}
  	public int getColor(){
  		return this.color;
  	}
  	public void setColor(int c){
  		this.color = c;
  	}
}
