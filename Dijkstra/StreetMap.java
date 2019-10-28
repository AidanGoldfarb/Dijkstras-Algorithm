import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JFrame;


public class StreetMap{
	static boolean show,directions = false;
	static int count = 0;
	static ArrayList<Road> roadList = new ArrayList<Road>();
	static String fileName;
	static String[] sint = new String [1];
	public static void main(String[] args) {
		fileName = args[0];
		String intersection1 = "";
		String intersection2 = "";
		
		double startTimeAlgo, endTimeAlgo, totalTimeAlgo, startTimeMap, endTimeMap, totalTimeMap;
		
		if(args.length != 2 && args[1].equals("--show") && args[2].equals("--directions")) { 
			show = true;
			directions = true;
			intersection1 = args[4];
			intersection2 = args[3];
		}
		else if(args[1].equals("--directions")) {
			directions = true;
			intersection1 = args[3];
			intersection2 = args[2];
		}
		else if(args[1].equals("--show")) {
			show = true;
			intersection1 = null;
			intersection2 = null;
		}
		else {
			intersection1 = args[2]; 
			intersection2 = args[1];
		}
		
		Graph g = new Graph();
		ArrayList<Double> latList = new ArrayList<Double>();
		ArrayList<Double> longList = new ArrayList<Double>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
			String line;
			while((line = br.readLine()) != null) {
				String [] sArr = line.split("\\s+");
				if(sArr[0].equals("i")) {
					Intersection i = new Intersection(sArr[1], Double.parseDouble(sArr[2]), Double.parseDouble(sArr[3]));
					g.add(sArr[1], i);
					latList.add(i.getLat());
					longList.add(i.getLong());
					if(count != 1) {
						count++;
						sint[0] = sArr[1];
					}
				}
				else if(sArr[0].equals("r")) {
					Road r = new Road(sArr[1], g.getIntersection(sArr[2]), g.getIntersection(sArr[3]));
					roadList.add(r);
					count++;
					g.getIntersection(sArr[2]).addRoad(r);
					g.getIntersection(sArr[3]).addRoad(r);
					//System.out.println("Road: " + r + " with length " + + r.getLength() + "m");
					//call get i1 and i2 and add that to the intersection's adj list
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Intersection a,b;
		if(intersection1 != null) {
			 a = g.getIntersection(intersection1);
			 b = g.getIntersection(intersection2);
		}
		else {
			 a = g.getIntersection(sint[0]);
			 b = g.getIntersection(sint[0]);
		}
		
		startTimeAlgo = System.nanoTime();
		
		g.dijkstra(a, b);
		endTimeAlgo = System.nanoTime();
		totalTimeAlgo = (endTimeAlgo - startTimeAlgo)/1000000;
		
		startTimeMap = System.nanoTime();
		if(show) {
			JFrame frame = new JFrame("Map");
			Map map = new Map(g, latList, longList, roadList, fileName);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(1000, 1000);
			frame.add(map);
			frame.setVisible(true);
		}
		endTimeMap = System.nanoTime();
		totalTimeMap = (endTimeMap - startTimeMap)/1000000;
		
		System.out.println("Shortest path for " + fileName + " finished in: " + totalTimeAlgo + " miliseconds");
		System.out.println("Map for " + fileName + " finished in: " + totalTimeMap + " miliseconds");
	
		
	}

}
