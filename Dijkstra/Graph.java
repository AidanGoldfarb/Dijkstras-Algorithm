import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.Stack;

public class Graph {
	private Hashtable<String , Intersection> table;//string = strID which hashes the intersection 
	PriorityQueue<Intersection> pQ = new PriorityQueue<Intersection>();
	Stack<Intersection> stack = new Stack<Intersection>();
	boolean directions;
	
	public Graph() {
		directions = StreetMap.directions;
		table = new Hashtable<String, Intersection>();
	}
	
	public Hashtable<String,Intersection> getTable(){
		return table;
	}
	
	public void addRoad(Road r) {
		r.getIntsersection1();
	}
	
	public void add(String str, Intersection i) {
		table.put(str, i);
	}
	
	public Intersection getIntersection(String str) {
//		try {
//			return table.get(str);
//		}catch(NullPointerException e) {
//			System.out.println("HERE");
//			return table.get("i0");
//		}
//		
		return table.get(str);
	}
	
	public Stack<Intersection> dijkstra(Intersection a, Intersection b){
		//PriorityQueue<Intersection> pQ = new PriorityQueue<Intersection>();
		a.setDistance(0);
		pQ.add(a);
		a.setVisited(true);
		outerloop:
		while(!pQ.isEmpty()) {
			//System.out.println(pQ);
			Intersection c = pQ.poll();
			c.setVisited(true);
			if(c.toString().equals(b.toString())) {
				break outerloop;
			}
			for(int i = 0; i<c.getList().size(); i++) {
				Road r = c.getList().get(i);
				Intersection temp = r.getOtherInt(c);
				if(r.getLength() + c.getDistance() < temp.getDistance()) {
					temp.prevStrID = c.toString();
					temp.setDistance(r.getLength() + c.getDistance());
					//System.out.println("Distance of " + temp + " " + temp.getDistance() );
				}
				
				if(!temp.ifVisited() && !temp.beenAdded()) {
					pQ.offer(temp);
					temp.setAdded(true);
				}
			}
			
		}
		
		Stack<Intersection> s = new Stack<Intersection>();
		s.push(b);
		
		while(!(s.peek().toString()).equals(a.toString())) {
			Intersection peek = s.peek();
			Intersection prev = getIntersection(peek.prevStrID);
			s.push(prev);
		}
		
		if(directions) {
			System.out.print("Path: ");
			System.out.println(s);
			System.out.println(b.getDistance()*.000621371 + " miles");
		}
		
		stack = s;
		return s;
	}

	public Stack<Intersection> getStack() {
		return stack;
	}

	
	
	
}
