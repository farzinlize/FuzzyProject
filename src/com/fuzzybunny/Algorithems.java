package com.fuzzybunny;

import java.util.ArrayList;
import java.util.Stack;

public class Algorithems {

	public static ArrayList<ArrayList<Node>> runTarjan(Graph g) {
		Algorithems al = new Algorithems();
		return al.tarjan(g);
	}

	public static int[] runDijkstra(Graph g, Node start){
		int[] dictances = new int[20000];
		FuzzyHeap<Node> heap = new FuzzyHeap<>(20000);
		for(Node n:g.allNodes()){
			dictances[n.number] = Integer.MAX_VALUE;
			n.setColor(-1);									//color -1 means: not started
		}
		dictances[start.number] = 0;
		heap.addElement(start, dictances[start.number]);
		start.setColor(0); 									//color 0 means: in heap
		while(!heap.isEmpty()){
			Node current = heap.deleteMin();
			if(current.getColor()==1)
				continue ;
			current.setColor(1); 							//color 1 means: dequeued from heap
			for(Edge edge:current.allEdges()){
				Node neighbour = edge.destination;
				if(neighbour.getColor()==1)
					continue ;
				int alt = dictances[current.number] + edge.weight;
				if(alt < dictances[neighbour.number])
					dictances[neighbour.number] = alt;
				heap.addElement(neighbour, dictances[neighbour.number]);
				neighbour.setColor(0);
			}
		}
		return dictances;
	}
	
	private class Strongconnect {
		private Stack<Node> s;
		private int index;
		private ArrayList<ArrayList<Node>> components;

		public Strongconnect(Stack<Node> s, int index) {
			this.s = s;
			this.index = index;
			components = new ArrayList<>();
		}

		public ArrayList<ArrayList<Node>> getAnswer() {
			return components;
		}

		private void doIt(Node v) {
			v.setIndex(index);
			v.setLowlink(index);
			v.setDefined(true);
			index++;
			s.push(v);
			v.setOnStack(true);

			for (Node w : v.allNeighbours()) {
				if (!w.isDefined()) {
					doIt(w);
					v.setLowlink(Math.min(v.getLowlink(), w.getLowlink()));
				} else if (w.isOnStack()) {
					v.setLowlink(Math.min(v.getLowlink(), w.getLowlink()));
				}
			}
			if (v.getLowlink() == v.getIndex()) {
				// start a new component
				ArrayList<Node> component = new ArrayList<>();
				Node w;
				do {
					w = s.pop();
					w.setOnStack(false);
					component.add(w);
				} while (!w.name.equals(v.name));
				components.add(component);
			}
		}
	}

	public ArrayList<ArrayList<Node>> tarjan(Graph graph) {
		int index = 0;
		Strongconnect st = new Strongconnect(new Stack<>(), index);
		for (Node v : graph.allNodes()) {
			if (!v.isDefined()) {
				st.doIt(v);
			}
		}
		return st.getAnswer();
	}

}
