package com.fuzzybunny;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

	public static void main(String[] arg) {
		Long time = System.currentTimeMillis();
		File file = new File("DataSet\\XLarge-1\\input.txt");
		try {
			FileInputStream fin = new FileInputStream(file);
			InputReader in = new InputReader(fin);
			Graph g = new Graph();
			HashMap<String, Integer> nodeMap = new HashMap<>();
			int vertexsAmount = in.nextInt();
			int edgesAmount = in.nextInt();
			for (int i = 0; i < vertexsAmount; i++) {
				String nodeName = in.next();
				nodeMap.put(nodeName, i);
				g.addNode(new Node(nodeName, i));
			}
			for (int i = 0; i < edgesAmount; i++) {
				Node source = g.findNode(nodeMap.get(in.next()));
				Node dest = g.findNode(nodeMap.get(in.next()));
				int edgeWeight = in.nextInt();
				source.addEdge(dest, edgeWeight);
			}
			// Tarjan Run and Answer
			ArrayList<ArrayList<Node>> resultTarjan = Algorithems.runTarjan(g);
			File out1 = new File("Answer\\output-1.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(out1));
			int city = 0;
			for (ArrayList<Node> compo : resultTarjan) {
				for (Node n : compo) {
					n.setTopologyID(city);
					writer.write(n.name + "\n");
					//System.out.println(n.name);
				}
				city++;
				//System.out.println("---");
				writer.write("---\n");
			}
			writer.close();
			// end of tarjan
			int queryAmount = in.nextInt();
			InsertionList<Query> list = new InsertionList<Query>();
			ArrayList<Query> piorityList = new ArrayList<>();
			for (int i = 0; i < queryAmount; i++) {
				Node start = g.findNode(nodeMap.get(in.next()));
				Node end = g.findNode(nodeMap.get(in.next()));
				Query q = new Query(start, end, i);
				if (!q.checkTopology())
					list.addElement(q);
				piorityList.add(q);
			}
			// Query listed and sorted
			int notAnswered = list.size(), i = 0, j = 0, maxCity = Integer.MAX_VALUE;
			while (i < notAnswered) {
				Query startPoint = list.getElement(i);
				maxCity = startPoint.getStart().getTopologyID();
				while (j < notAnswered) {
					Query current = list.getElement(j);
					if (current.getIntValue() == startPoint.getIntValue()) {
						j++;
						if (current.getEndCity() < maxCity)
							maxCity = current.getEndCity();
					}
					else 
						break ;
				}
				ArrayList<Node> subGraphNodes = new ArrayList<>();
				for (int k = startPoint.getStart().getTopologyID(); k > maxCity; k--) {
					ArrayList<Node> compo = resultTarjan.get(k);
					subGraphNodes.addAll(compo);
				}
				Algorithems.runDijkstra(new Graph(subGraphNodes), startPoint.getStart());
				i=j;
			}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (Exception e) {
			System.out.println("Someting wrong :" + e.getMessage());
		}
		System.out.println("Time: " + (System.currentTimeMillis() - time));
	}

}
