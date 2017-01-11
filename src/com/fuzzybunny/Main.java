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
		//in Master
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
			System.out.println("Read: " + (System.currentTimeMillis() - time));
			// Tarjan Run and Answer
			ArrayList<ArrayList<Node>> resultTarjan = Algorithems.runTarjan(g);
			System.out.println("Tarjan End: " + (System.currentTimeMillis() - time));
			File out1 = new File("Answer\\my_output-1.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(out1));
			int city = 0;
			for (ArrayList<Node> compo : resultTarjan) {
				for (Node n : compo) {
					n.setTopologyID(city);
					writer.write(n.name + "\n");
				}
				city++;
				writer.write("---\n");
			}
			writer.close();
			// end of tarjan
			System.out.println("Tarjan result write: " + (System.currentTimeMillis() - time));
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
			System.out.println("Query Read and sort: " + (System.currentTimeMillis() - time));
			int notAnswered = list.size(), i = 0, j = 0, maxCity = Integer.MAX_VALUE;
			int proccess = 0, check = notAnswered/100+1;
			System.out.println("--------------------------------------------------");
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
				for (int k = startPoint.getStart().getTopologyID(); k >= maxCity; k--) {
					ArrayList<Node> compo = resultTarjan.get(k);
					subGraphNodes.addAll(compo);
				}
				int[] answer = Algorithems.runDijkstra(new Graph(subGraphNodes), startPoint.getStart());
				for(int k=i;k<j;k++){
					Query q = list.getElement(k);
					q.makeAnswer(answer[q.getEndNumber()]);
					proccess++;
					if((proccess/check) > 1){
						System.out.print("#");
						proccess=0;
					}
				}
				i=j;
			}
			System.out.println("");
			System.out.println("All dijkstra end: " + (System.currentTimeMillis() - time));
			File out2 = new File("Answer\\my_output-2.txt");
			BufferedWriter writer2 = new BufferedWriter(new FileWriter(out2));
			for(Query q:piorityList){
				int toWrite = q.returnAnswer();
				if(toWrite==-1)
					writer2.write("Impossible" + "\n");
				else
					writer2.write(toWrite + "\n");
			}
			writer2.close();
			System.out.println("Dijkstra result write: " + (System.currentTimeMillis() - time));
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (Exception e) {
			System.out.println("Someting wrong :" + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("Time: " + (System.currentTimeMillis() - time));
	}

}
