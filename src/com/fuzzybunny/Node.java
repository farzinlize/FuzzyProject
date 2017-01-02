package com.fuzzybunny;

import java.util.ArrayList;

public class Node{
	private boolean onStack;
	private boolean defined;
	private int index;
  	private int lowlink;
  	public String name;
  	
  	public Node(String name){
  		this.onStack = false;
  		this.defined = false;
  		this.index = 0;
  		this.lowlink =0;
  	}
  	
  	public ArrayList<Node> allNeighbours(){
  		return null;
  	}
  	public boolean isDefined(){
  		return this.defined;
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
  	public void setOnStack(boolean onStack) {
  		this.onStack = onStack;
  	}
}
