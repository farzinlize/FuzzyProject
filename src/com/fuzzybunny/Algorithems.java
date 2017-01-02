package com.fuzzybunny;

import java.util.Stack;

public class Algorithems{

	public static void runTarjan(){
		Algorithems al = new Algorithems();
		al.tarjan(null);
	}
	
  private class Strongconnect{
    private Stack<Node> s;
    private int index;
    public Strongconnect(Stack<Node> s, int index){
    	this.s = s;
    	this.index = index;
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
    	
    }
  }

  public void tarjan(Graph graph){
    int index = 0;
    Strongconnect st = new Strongconnect(new Stack<>(), index);
    for(Node v:graph.allVertex()){
    	if(!v.isDefined()){
    		st.doIt(v);
    	}
    }
  }

}
