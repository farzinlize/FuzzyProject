package com.fuzzybunny;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] arg){
		File file = new File("DataSet\\Sample\\input.txt");
		try {
			FileInputStream fin = new FileInputStream(file);
			InputReader in = new InputReader(fin);
			Graph g = new Graph();
			int vertexsAmount = in.nextInt();
			int edgesAmount = in.nextInt();
			for(int i=0;i<vertexsAmount;i++){
				String nodeName = in.next();
				g.addNode(new Node(nodeName));
			}
			for(int i=0;i<edgesAmount;i++){
				String sourceName = in.next();
				String destName = in.next();
				int edgeWeight = in.nextInt();
				Node source = g.findNode(sourceName);
				Node dest = g.findNode(destName);
				source.addEdge(dest, edgeWeight);
			}
//			ArrayList<ArrayList<Node>> result = Algorithems.runTarjan(g);
//			System.out.println("Algo Done");
//			int i=1;
//			for(ArrayList<Node> compo: result){
//				System.out.println(i++);
//				for(Node n:compo){
//					System.out.println(n.name);
//				}
//			}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} 
	}
	
}
