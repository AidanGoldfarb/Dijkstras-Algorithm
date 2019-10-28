import java.util.ArrayList;

public class Intersection implements Comparable<Intersection>{

	private double lat, longitude;
	private String strID;
	private ArrayList<Road> adjList;  //every intersection has a list of roads connected to it
	private double distance = Double.MAX_VALUE;
	private boolean visited = false;
	public String prevStrID; 
	private int x,y = 0;
	private boolean beenAdded = false;
	private boolean beenDrawn = false;
	
	public Intersection(String a, double b, double c) {
		strID = a;
		lat = b;
		longitude = c;	
		adjList = new ArrayList<Road>();
		prevStrID = "";
		
	}
	
	@Override
	public String toString() {
		return strID;// + " " + this.hashCode();
	}
	
	public boolean ifVisited() {
		return visited;
	}
	
	public void setVisited(boolean a) {
		visited = a;
	}
	
	
	public ArrayList<Road> getList(){
		return adjList;
	}
	
	public void addRoad(Road r) {
		adjList.add(r);
	}

	public double getLat() {
		return lat;
	}

	public double getLong() {
		return longitude;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public void setDistance(double a) {
		distance = a;
	}


	@Override
	public int compareTo(Intersection o) {
		double d = o.getDistance();
		if(distance < d) {
			return -1; 
		}
		else if(distance > d) {
			return 1;
		}
		return 0;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean beenAdded() {
		return beenAdded;
	}

	public void setAdded(boolean b) {
		beenAdded = b;
		
	}

	public void setBeenDrawn(boolean beenDrawn) {
		this.beenDrawn = beenDrawn;
	}

	public boolean hasBeenDrawn() {
		return beenDrawn;
	}

	
}
