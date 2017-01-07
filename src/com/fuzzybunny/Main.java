package com.fuzzybunny;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public static void main(String[] arg) {
		Long time = System.currentTimeMillis();
		File file = new File("DataSet\\XLarge-1\\input.txt");
		File out = new File("DataSet\\XLarge-1\\output-2.txt");
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
			ArrayList<ArrayList<Node>> result = Algorithems.runTarjan(g);
			int city = 0;
			for (ArrayList<Node> compo : result) {
				for (Node n : compo) {
					n.setTopologyID(city);
					System.out.println(n.name);
				}
				city++;
				System.out.println("---");
			}
			// end of tarjan
			int queryAmount = in.nextInt();
			InsertionList<Query> list = new InsertionList<Query>();
			ArrayList<Query> piorityList = new ArrayList<>();
			int im = 0, yes = 0, no = 0, im2 = 0;
			Scanner scan = new Scanner(new FileInputStream(out));
			boolean flag1;
			for (int i = 0; i < queryAmount; i++) {
				flag1 = false;
				Node start = g.findNode(nodeMap.get(in.next()));
				Node end = g.findNode(nodeMap.get(in.next()));
				Query q = new Query(start, end, i);
				String ans = scan.next();
				if (ans.equals("Impossible")) {
					im2++;
					flag1 = true;
				}
				boolean flag2 = q.checkTopology();
				if (flag2) {
					im++;
					if (flag1)
						yes++;
					else
						no++;
				} else {
					list.addElement(q);
				}
				piorityList.add(q);
			}
			System.out.println("YES: " + yes);
			System.out.println("NO: " + no);
			System.out.println("IM in ans: " + im2);
			System.out.println("IM in my: " + im);
			// Query listed and sorted
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (Exception e) {
			System.out.println("Someting wrong");
		}
		System.out.println("Time: " + (System.currentTimeMillis() - time));
	}

}
