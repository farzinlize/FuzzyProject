package com.fuzzybunny;

import java.util.ArrayList;
import java.util.Stack;

public class Algorithems{

	public static ArrayList<ArrayList<Node>> runTarjan(Graph g){
		Algorithems al = new Algorithems();
		return al.tarjan(g);
	}
	
  private class Strongconnect{
    private Stack<Node> s;
    private int index;
    private ArrayList<ArrayList<Node>> components;
    public Strongconnect(Stack<Node> s, int index){
    	this.s = s;
    	this.index = index;
    	components = new ArrayList<>();
    }
    
    public ArrayList<ArrayList<Node>> getAnswer(){
    	return components;
    }
    
    private void doIt(Node v){
    	v.setIndex(index);
    	v.setLowlink(index);
    	index++;
    	s.push(v);
    	v.setOnStack(true);
    	
    	for(Node w:v.allNeighbours()){
    		if(!w.isDefined()){
    			doIt(w);
    			v.setLowlink(Math.min(v.getLowlink(), w.getLowlink()));
    		} else if(w.isOnStack()){
    			v.setLowlink(Math.min(v.getLowlink(), w.getLowlink()));
    		}
    	}
    	if(v.getLowlink() == v.getIndex()){
    		//start a new component
    		ArrayList<Node> component = new ArrayList<>();
    		Node w;
    		do {
    			w = s.pop();
    			w.setOnStack(false);
    			component.add(w);
    		} while(!w.name.equals(v.name));
    		components.add(component);
    	}
    }
  }

  public ArrayList<ArrayList<Node>> tarjan(Graph graph){
    int index = 0;
    Strongconnect st = new Strongconnect(new Stack<>(), index);
    for(Node v:graph.allVertex()){
    	if(!v.isDefined()){
    		st.doIt(v);
    	}
    }
    return st.getAnswer();
  }

}