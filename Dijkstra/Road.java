
public class Road {
	private Intersection i1, i2;
	private String strID;
	private double length;
	private boolean drawn = false;
	
	public Road(String str, Intersection a, Intersection b) {
		strID = str;
		i1 = a;
		i2 = b;
	}
	
	@Override
	public String toString() {
		return strID;
	}
	
	public Road getRoad(Intersection a, Intersection b) {
		if((a.toString().equals(i1.toString()) && b.toString().equals(i2.toString())) ||
				(a.toString().equals(i2.toString()) && b.toString().equals(i1.toString()))) {
			return this;
		}
		return null;
	}
	
	public Intersection getIntsersection1() {
		return i1;
	}
	
	public Intersection getIntersection2() {
		return i2;
	}
	
	public Intersection getOtherInt(Intersection a) {
		if(i1.toString().equals(a.toString())) {
			return i2;
		}
		return i1;
	}
	
	/*
	 * calculates the length of the road using haversine formula
	 */
	public double getLength() {
		final double RADIUS = 63560;
		double distance = 0;
		double lat1 = i1.getLat();
		double lat2 = i2.getLat();
		double longitude1 = i1.getLong();
		double longitude2 = i2.getLong();
		
		double sinLat = Math.pow(Math.sin((lat2-lat1)/2), 2);
		double sinLong = Math.pow(Math.sin((longitude2-longitude1)/2), 2);
		double cos1 = Math.cos(lat1);
		double cos2 = Math.cos(lat2);

		distance = 2*RADIUS*Math.asin((Math.sqrt(sinLat+cos1*cos2*sinLong)));
		
		return distance;
	}

	public boolean isDrawn() {
		return drawn;
	}

	public void setDrawn(boolean drawn) {
		this.drawn = drawn;
	}
}
