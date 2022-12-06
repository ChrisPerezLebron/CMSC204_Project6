import java.util.ArrayList;
import java.util.Iterator;

public class Town implements Comparable<Town>{
	private String name; 
	private ArrayList<Road> edgeList ; 
	private boolean isVisited; 
	private Town previousTown;
	private int pathCost; 
	
	public Town(String townName) {
		name = townName; 
		edgeList = new ArrayList<Road>(); 
		isVisited = false; 
		previousTown = null; 
		pathCost = Integer.MAX_VALUE; 
		
	}
	
	public Town(Town otherTown) {
		this.name = otherTown.name; 
		this.edgeList = new ArrayList<Road>(); 
		for(Road r : otherTown.edgeList) {
			this.edgeList.add(r); 
		}
		this.isVisited = otherTown.isVisited; 
		this.previousTown = otherTown.previousTown; 
		this.pathCost = otherTown.pathCost; 
	}
	
	public String getName() {
		return this.name; 
	}
	
	public int compareTo(Town other) {
		return name.compareTo(other.name); 
	}
	
	public String toString() {
		return this.name; 
	}
	
	public boolean equals(Object obj) {
		boolean flag = false; 
		if ((obj instanceof Town)) {
			Town objTown = (Town) obj; 
			flag = name.equals(objTown.name);
		}
			
		return flag; 
	}
	
	public int hashCode() {
		return name.hashCode(); 
	}
	
	public Iterator<Road> getRoadIterator() {
		return edgeList.iterator(); 
	}
	
	public boolean containsRoad(Road road) {
		/*
		 * check if this road is a duplicate. Two roads are equal if they have the 
		 * same end points [regardless of which order because graph is undirected... 
		 * Although, i will ensure that all roads in vertex X's edgeList name X as 
		 * the source and it's neighbor as the destination] 
		 */
		boolean isDuplicate = false;
		int index = 0;
		while(!isDuplicate && index < edgeList.size()) {
			if(edgeList.get(index).equals(road))
				isDuplicate = true; 
			index++; 
		}
		return isDuplicate; 
	}
	
	public boolean addRoad(Road newRoad) {
		
		
		boolean added = false; 
		if(!containsRoad(newRoad)) {
			edgeList.add(newRoad); 
			added = true;
		}
	
		return added; 
	}
	
	public Road removeRoad(Road road) {
		boolean isRemoved = false; 
		int index = 0; 
		Road roadRemoved = null; 
		while(index < edgeList.size() && !isRemoved) {
			Road currentRoad = edgeList.get(index);
			if(currentRoad.equals(road)) {
				roadRemoved = currentRoad; 
				edgeList.remove(index); 
			}
			index++;
		}
		
		return roadRemoved; 
	
	}
	
	/*
	 * this removes all roads from this town to destinationTown
	 */
	public boolean removeAllRoadsTo(Town destinationTown) {
		/*
		 * I hope I am not over engineering here but I believe I need a dynamic terminating index because
		 * if I remove one edge to this town to destination town all the indexes will change by minus 1 
		 * so size will reduce. 
		 */
		int endIndex = edgeList.size(); 
		boolean removed = false;
		
		for(int index = 0; index < endIndex; index++) {
			if(edgeList.get(index).getDestination().equals(destinationTown)) {
				edgeList.remove(index); 
				/*
				 * since we removed an edge the current index already points to the 
				 * next road so we need to decrement the index so that it points to the next
				 * road when the next iteration begins 
				 */
				index--;
				/*
				 * following similar logic the "end" of the array has been reduced by one 
				 * so decrement the end goal as well
				 */
				endIndex--; 
				removed = true;
			}
		}
		
		return removed; 
	}
	
	public void visit() {
		this.isVisited = true; 
	}
	
	public void unVisit() {
		this.isVisited = false; 
	}
	
	public boolean isVisited() {
		return isVisited; 
	}
	
	public int getPathCost() {
		return this.pathCost; 
		
	}
	
	public void setPathCost(int cost) {
		this.pathCost = cost; 
	}
	
	public void setPreviousTown(Town town) {
		this.previousTown = town; 
	}
	
	public Town getPreviousTown() {
		return this.previousTown; 
	}
	
	public Road getRoadTo(Town destinationTown) {
		Iterator<Road> roadIterator = getRoadIterator(); 
		Road resultRoad = null;
		boolean found = false;
		while(!found && roadIterator.hasNext()) {
			Road nextRoad = roadIterator.next(); 
			if(nextRoad.getDestination().equals(destinationTown)) {
				resultRoad = nextRoad;
				found = true; 
			}
		}
		
		return resultRoad; 
	}
	
	public boolean deleteRoadTo(Town destinationTown) {
		Iterator<Road> roadIterator = getRoadIterator();
		boolean found = false;
		int index = 0;
		while(!found && roadIterator.hasNext()) {
			Road nextRoad = roadIterator.next(); 
			if(nextRoad.getDestination().equals(destinationTown)) {
				edgeList.remove(index); 
				found = true; 
			}
			index++; 
		}
		
		return found; 
	}
}
