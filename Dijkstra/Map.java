import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.Stack;

import javax.swing.JPanel;

public class Map extends JPanel{
	
	private static final long serialVersionUID = 1L;
	Hashtable<String,Intersection> table;
	ArrayList<Double> lList;
	ArrayList<Double> logList;
	//PriorityQueue<Intersection> pQ;
	Stack<Intersection> stack;
	Graph graph;
	double minLat, maxLat, minLong, maxLong;
	boolean directions;
	ArrayList<Road> roadList;
	int size;
	
	public Map(Graph g, ArrayList<Double> latList, ArrayList<Double> longList, ArrayList<Road> roadList2, String str){
		table = g.getTable();
		graph = g;
		lList = latList;
		logList = longList;
		stack = g.getStack();
		this.setVisible(true);
		directions = StreetMap.directions;
		roadList = roadList2;
		if(str.equals("ur.txt")) {
			size = 6;
		}
		else {
			size = 2;
		}
	}
	
	public void paint(Graphics g) {
		maxLat = getMax(lList);
		minLat = getMin(lList);
		maxLong = getMax(logList);
		minLong = getMin(logList);
		int w = getWidth()-1;
		int h = getHeight()-1;
		for(Intersection i: table.values()) {
			double lat = i.getLat();
			double lon = Math.abs(i.getLong());
			int fLat = spreadLat(lat,maxLat,minLat);
			int fLon = spreadLong(lon, maxLong, minLong);
			table.get(i.toString()).setX(fLon);
			table.get(i.toString()).setY(fLat);
			g.fillOval(fLon, h-fLat, size, size); //make size 10 for ur.txt
			i.setBeenDrawn(true);
		}
		drawRoads(roadList,g);
		if(directions) {
			drawPath(stack,g);
		}
	}

	/*
	 * given two intersections, draw road between them
	 */
	private void drawRoads(ArrayList<Road> list, Graphics g) { //TODO fix road drawing
		g.setColor(Color.BLUE);
		int h = getHeight()-1;
		ArrayList<Road> rlist = list;
		int x1,y1,x2,y2;
		for(int i = 0; i<rlist.size(); i++) {
			Road temp = rlist.get(i);
			Intersection a = temp.getIntsersection1();
			Intersection b = temp.getIntersection2();
			x1 = a.getX();
			y1 = a.getY();
			x2 = b.getX();
			y2 = b.getY();
			int s = size/2;
			//System.out.println(x1 + " " + y1 + " " + x2 + " " + y2 );
			g.drawLine(x1+s, h-y1+s, x2+s, h-y2+s);
		}
	}

	private void drawPath(Stack<Intersection> stack, Graphics g) {
		int w = getWidth()-1;
		int h = getHeight()-1;
		Stack<Intersection> p = stack;
		g.setColor(Color.RED);
		Intersection temp = p.pop();
		for(int i = 0; i<p.size()-2; i++) {
			int x = temp.getX();
			int y = temp.getY();
			Intersection next = p.pop();
			int x2 = next.getX();
			int y2 = next.getY();
			int s = size/2;
			Graphics2D g2 = (Graphics2D) g;
	        g2.setStroke(new BasicStroke(3));
			g2.drawLine(x+s, h-(y-s), x2+s, h-(y2-s));
			temp = next;
		}
		
	}

	/*
	 * spreads out doubles so they wont be mapped on
	 * the same point when truncated 
	 */
	private int spreadLat(double a, double max, double min) {
		int value = 0;
		double max1 = max;
		double min1 = min;
		double range = max1 - min1;
		double temp = a-min1;
		value = (int)((temp*getHeight())/range);
		
		return value;
	}
	
	private int spreadLong(double a, double b, double c) {
		int value = 0;
		double max1 = b;
		double min1 = c;
		double range = max1 - min1;
		double temp = a-min1;
		value = (int)((temp*getWidth())/range);
		return value; 
	}

	private double getMin(ArrayList<Double> list) {
		int len = list.size();
		double min = list.get(0);
		for(int i = 1; i<len; i++) {
			if(list.get(i) < min) {
				min = list.get(i);
			}
		}
		return Math.abs(min);
	}

	private double getMax(ArrayList<Double> list) {
		int len = list.size();
		double max = list.get(0);
		for(int i = 1; i<len; i++) {
			if(list.get(i) > max) {
				max = list.get(i);
			}
		}
		return Math.abs(max);
	}
		

}
