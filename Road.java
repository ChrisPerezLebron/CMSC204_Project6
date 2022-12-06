
public class Road {
	private Town startTown; 
	private Town endTown; 
	private int weight; 
	private String name; 
	
	public Road(Town source, Town destination, int miles, String roadName) {
		startTown = source; 
		endTown = destination; 
		weight = miles; 
		name = roadName; 
	}
	
	public Road(Town source, Town destination, String roadName) {
		this(source, destination, 1, roadName); 
	}
	
	public boolean contains(Town town) {
		/*
		 * ASSUMPTION: containment is determined by the return of the equals() method 
		 * for the town class rather than by reference. 
		 */
		
		return startTown.equals(town) || endTown.equals(town); 
	}
	
	/*
	 * COME BACK TO THIS 
	 */
	public String toString() {
		/*
		 * come back to this finish this based on what the prof 
		 * expects in terms of formatting 
		 */
//		return "Source: " + startTown + " Destination: " + endTown + " Weight: " + weight + " Road Name: " + name;
		return name; 
	}
	
	public String getName() {
		return this.name; 
	}
	
	public Town getDestination() {
		/*
		 * probably use copy constructor. 
		 */
		/*
		 * this method may cause some problems because if JUnit expects a specific town to be 
		 * returned as destination but in this edge we store it as source [b/c each edge will have 
		 * a mirrored version in it's destination vertex's edgeList]. 
		 */
		return endTown;
	}
	
	public Town getSource() {
		/*
		 * probably use copy constructor. 
		 */
		/*
		 * this method may cause some problems because if JUnit expects a specific town to be 
		 * returned as destination but in this edge we store it as source [b/c each edge will have 
		 * a mirrored version in it's destination vertex's edgeList]. 
		 */
		return new Town(startTown); 
	}
	
	public int compareTo(Road other) {
		/*QUESTION/CONFUSION: 
		 * compare returns 0 for equality when the names are equal but the equals method 
		 * returns true for equality based on a different set of criteria. This does not 
		 * seem like proper convention but this is what the javadoc specified. 
		 */
		
		/*
		 * ??????? implementing the dijkstra algorithm is far easier using a priority queue 
		 * however, this priority should be determined by WEIGHT rather than by name. But, 
		 * the java doc says to make the compareTo method compare names which will provide us 
		 * with little to no benefit while making the implementation of dijkstra algorithm a 
		 * living nightmare????? 
		 * 
		 * I guess alternatively, I could pass a separate comparator object to the priority queue. 
		 * But, this would simply be a band aid solution to poor project specifications. 
		 */
		return name.compareTo(other.name); 
	}
	
	public int getWeight() {
		return weight; 
	}
	
	public boolean equals(Object other) {
		/*
		 * the java doc says simply to check that the end points for both roads are the same. 
		 * ASSUMPTION: "same" or "equality" of two towns is determined by the town's equals method
		 * rather than by reference. 
		 * 
		 * Also, the java doc does not specify whether or not to account for edge weight in equality. 
		 * However, I believe this is necessary. An edge {A, B} of weight 30 is equal to an edge 
		 * {B, A} of weight 30 but is NOT equal to an edge {A, B} of weight 72. 
		 */
		boolean flag = false; 
		if(other instanceof Road) {
			Road otherRoad = (Road)other; 
			flag = (this.startTown.equals(otherRoad.startTown) || this.startTown.equals(otherRoad.endTown) )
					&& (this.endTown.equals(otherRoad.startTown) || this.endTown.equals(otherRoad.endTown) ) 
					&& (this.weight == otherRoad.weight); 
		}
		
		return flag; 
	}
	
	public int hashCode() {
		/*
		 * maybe test this to see if integer wrapping occurs? That is the only forseable issue I see 
		 * with this implementation. 
		 */
//		int hash = 31 * startTown.hashCode(); 
//		hash = hash * 31 + endTown.hashCode(); 
//		hash = hash * 31 + weight; 
//		return hash;
		
		return startTown.hashCode() + endTown.hashCode() + weight; 
	}
	
	
}


