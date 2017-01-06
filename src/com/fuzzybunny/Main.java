package com.fuzzybunny;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	
	public static void main(String[] arg){
		File file = new File("DataSet\\Large-1\\input.txt");
		try {
			FileInputStream fin = new FileInputStream(file);
			InputReader in = new InputReader(fin);
			Graph g = new Graph();
			HashMap<String, Integer> nodeMap = new HashMap<>();
			int vertexsAmount = in.nextInt();
			int edgesAmount = in.nextInt();
			for(int i=0;i<vertexsAmount;i++){
				String nodeName = in.next();
				nodeMap.put(nodeName, i);
				g.addNode(new Node(nodeName, i));
			}
			for(int i=0;i<edgesAmount;i++){
				String sourceName = in.next();
				String destName = in.next();
				int edgeWeight = in.nextInt();
				Node source = g.findNode(sourceName);
				Node dest = g.findNode(destName);
				source.addEdge(dest, edgeWeight);
			}
			//Tarjan Run and Answer
			ArrayList<ArrayList<Node>> result = Algorithems.runTarjan(g);
			int topologyID=0;
			for(ArrayList<Node> compo: result){
				for(Node n:compo){
					n.setTopologyID(topologyID++);
					System.out.println(n.name);
				}
				System.out.println("---");
			}
			//end of tarjan
			int queryAmount = in.nextInt();
			InsertionList<Query> list = new InsertionList<Query>();
			ArrayList<Query> piorityList = new ArrayList<>();
			for(int i=0;i<queryAmount;i++){
				Node start = g.findNode(nodeMap.get(in.next()));
				Node end = g.findNode(nodeMap.get(in.next()));
				Query q = new Query(start, end, i);
				list.addElement(q);
				piorityList.add(q);
			}
			//querys listed and sorted
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (Exception e) {
			System.out.println("FUCK");
		} 
	}
	
}
