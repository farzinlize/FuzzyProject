package com.fuzzybunny;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] arg){
		File file = new File("DataSet\\Large-1\\input.txt");
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
			ArrayList<ArrayList<Node>> result = Algorithems.runTarjan(g);
			for(ArrayList<Node> compo: result){
				for(Node n:compo){
					System.out.println(n.name);
				}
				System.out.println("---");
			}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} 
	}
	
}
