import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class Graph implements GraphInterface<Town, Road> {
	
	private HashMap<String, Town> vertices; 
	
	public Graph() {
		vertices = new HashMap<String, Town>(); 
	}
	
	public Town getTown(Town town) {
		return vertices.get(town.getName()); 
	}
	public Road getEdge(Town sourceTown, Town destinationTown) {
		sourceTown = vertices.get(sourceTown.getName()); 
		Road resultRoad = null; 
		
		if(sourceTown != null) {
			Iterator<Road> roadIterator = sourceTown.getRoadIterator(); 
			
			while(roadIterator.hasNext()) {
				Road nextRoad = roadIterator.next(); 
				if(nextRoad.getDestination().getName().equals(destinationTown.getName())) {
					resultRoad = nextRoad; 
				}
			}
		}
		
		return resultRoad;
	}
	
	public Road addEdge(Town sourceTown, Town destinationTown, int miles, String roadName) {
		if(sourceTown == null || destinationTown == null) 
			throw new NullPointerException();
		/*
		 * search vertices dictionary using the town name to find the stored 
		 * vertex object for each town. (the one we are given does not contain 
		 * the necessary adjacency info so we must find the "mirror" of it within 
		 * our dictionary. 
		 * 
		 * The dictionary's get method will return null if the town's are not included 
		 * within the graph. Thus, we throw an IllegalArgumentException if the sourceTown
		 * OR destinationTown is null after the execution of these two lines. [b/c it 
		 * signifies that one or both towns have yet to be added... according to their name] 
		 */
		sourceTown = vertices.get(sourceTown.getName()); 
		destinationTown = vertices.get(destinationTown.getName()); 
		
		if(sourceTown == null || destinationTown == null) 
			throw new IllegalArgumentException("One or more Town's do not exist"); 
		
		
		
		Road resultRoad = null; 
		//ASSERTION: both sourceTown and destinationTown are not null and both have the real adjacency info for the real town in the graph 
		/*
		 * Since the road has two end points [two towns] despite being stored within the town it comes from I will store one version of the edge 
		 * [source to destination] in the source vertex and another version of the edge [destination to source] in the destination vertex. 
		 */
		Road sourceToDestination = new Road(sourceTown, destinationTown, miles, roadName); 
		Road destinationToSource = new Road(destinationTown, sourceTown, miles, roadName); 
		
		if(!sourceTown.containsRoad(sourceToDestination) && !destinationTown.containsRoad(destinationToSource)) { 
			sourceTown.addRoad(sourceToDestination); 
			destinationTown.addRoad(destinationToSource); 
			resultRoad = sourceToDestination; 
		}
		return resultRoad; 
	}
	
	public boolean addVertex(Town town) {
		if(town == null)
			throw new NullPointerException(); 
		
		boolean containsTown = vertices.containsKey(town.getName()); 
		
		if(!containsTown) 
			vertices.put(town.getName(), town); 
		
		/*
		 * when containsTown is false the vertex is added [true should be returned] 
		 * and then containsTown is true the vertex is not added [false should be returned] 
		 * so whether the town was added or not is equivalent to the logical negation of 
		 * whether a duplicate of the town was found 
		 */
		return !containsTown; 
	
	}
	
	public boolean containsEdge(Town sourceTown, Town destinationTown) {
		/*
		 * no weight information is given so I am going to assume that this method 
		 * does not account for weights being different. Although that sounds like a bad idea. 
		 */
		
		
		if(sourceTown == null || destinationTown == null)
			throw new NullPointerException(); 
		
		sourceTown = vertices.get(sourceTown.getName()); 
		destinationTown = vertices.get(destinationTown.getName()); 
		
		if(sourceTown == null || destinationTown == null)
			/*
			 * this will only execute if the town names were not found within 
			 * the dictionary 
			 */
			throw new IllegalArgumentException(); 
		
		
		/*
		 * get road iterator for sourceTown and check if there is an edge 
		 * to destinationTown 
		 */
		Iterator<Road> sourceRoadIterator = sourceTown.getRoadIterator(); 
		boolean sourceTownContainsEdge = false; 
		while(sourceRoadIterator.hasNext() && !sourceTownContainsEdge) {
			Road currentRoad = sourceRoadIterator.next(); 
			if(currentRoad.getDestination().equals(destinationTown))
				sourceTownContainsEdge = true; 
		}
		
		/*
		 * do the same for the destinationTown to ensure it has an edge to sourceTown
		 */
		
		Iterator<Road> destinationRoadIterator = destinationTown.getRoadIterator(); 
		boolean destinationTownContainsEdge = false; 
		while(destinationRoadIterator.hasNext() && !destinationTownContainsEdge) {
			Road currentRoad = destinationRoadIterator.next(); 
			if(currentRoad.getDestination().equals(sourceTown))
				destinationTownContainsEdge = true; 
		}
		
		return (destinationTownContainsEdge && sourceTownContainsEdge); 
	}
	
	
	public boolean containsVertex(Town town) {
		return vertices.containsKey(town.getName()); 
	}
	
	public Set<Road> edgeSet() {
		/*
		 * ASSUMPTION: this it is legal to treat an edge from A to B as distinct when compared to 
		 * an edge from B to A. Thus, we are just returning the set of all edge objects stored in the graph
		 * 
		 */
		Collection<Town> townCollection = vertices.values();
		
		HashSet<Road> roadSet = new HashSet<>(); 
		
		for(Town town : townCollection) {
			Iterator<Road> roadIterator = town.getRoadIterator(); 
			while(roadIterator.hasNext()) 
				roadSet.add(roadIterator.next()); 
		}
		
		
		return roadSet; 
	}
	
	public Set<Road> edgesOf(Town town) {
		/*
		 * here I will only return the set of roads leaving the town. 
		 */
		
		if(town == null)
			throw new NullPointerException(); 
		
		town = vertices.get(town.getName()); 
		
		if(town == null)
			throw new IllegalArgumentException(); 
		
		HashSet<Road> roadSet = new HashSet<>(); 
		Iterator<Road> roadIterator = town.getRoadIterator(); 
		
		while(roadIterator.hasNext())
			roadSet.add(roadIterator.next()); 
		
		return roadSet; 
	}
	
	public Road removeEdge(Town sourceTown, Town destinationTown, int weight, String roadName) {
		if(sourceTown == null || destinationTown == null)
			throw new NullPointerException(); 
		
		sourceTown = vertices.get(sourceTown.getName()); 
		destinationTown = vertices.get(destinationTown.getName()); 
		
		if(sourceTown == null || destinationTown == null || weight < 1 || roadName == null)
			throw new IllegalArgumentException(); 
		
		 
		Road sourceSearchRoad = new Road(sourceTown, destinationTown, weight, roadName); 
		Road destinationSearchRoad = new Road(destinationTown, sourceTown, weight, roadName); 
		
		/*
		 * both sourceTown and destinationTown must contain this particular edge for it to 
		 * be removed. That is, sourceTown must have this edge in its edgeList and desintationTown
		 * must have this edge in its edgeList 
		 */
		Road resultRoad = null; 
		boolean roadContained = sourceTown.containsRoad(sourceSearchRoad) && destinationTown.containsRoad(destinationSearchRoad);
		
		 
		if(roadContained) {
			/*
			 * note to self: I think I could have slightly better efficiency if I made the Town class's containsRoad method return 
			 * the index within the internal edgeList at which the edge is located instead of true or false. This would prevent me from 
			 * searching the edgeList twice. I will go back and make this improvement If I have enough time.
			 */
			
			
			if(sourceTown.removeRoad(sourceSearchRoad) == null || destinationTown.removeRoad(destinationSearchRoad) == null) 
				/*
				 * this signals something bad happened. I do not expect this to occur but it is a good marker to say 
				 * something is seriously wrong
				 */
				throw new IllegalStateException(); 
			
			
			/*
			 * if we got to this point successful removal has occurred so we will return one of the search roads 
			 */
			resultRoad = sourceSearchRoad; 
			
		}
		
		return resultRoad; 
	}
	
	public boolean removeVertex(Town town) {
		if(town == null)
			throw new NullPointerException(); 
		
		town = vertices.get(town.getName());
		if(town == null)
			throw new IllegalArgumentException(); 
		/*
		 * to remove all roads to town we will obtain an iterator for all the roads 
		 * connecting to town then remove each destinationTown's connection to town 
		 * parameter 
		 */
		Iterator<Road> roadIterator = town.getRoadIterator(); 
		while(roadIterator.hasNext()) {
			Road currentRoad = roadIterator.next(); 
			currentRoad.getDestination().removeAllRoadsTo(town);  
		}
		
		/*
		 * not sure how I can take into account the "truth/falsity" of the removeAllRoadsTo method 
		 * used earlier. I will probably come back and investigate that. 
		 */
		return vertices.remove(town.getName()) != null; 
	}
	
	public Set<Town> vertexSet() {
		return new HashSet<Town>(vertices.values()); 
	}

	
	public ArrayList<String> shortestPath(Town sourceTown, Town destinationTown) {
		if(sourceTown == null || destinationTown == null) 
			throw new NullPointerException();
		
		sourceTown = vertices.get(sourceTown.getName()); 
		destinationTown = vertices.get(destinationTown.getName()); 
		
		if(sourceTown == null || destinationTown == null) 
			throw new IllegalArgumentException();
		
		dijkstraShortestPath(sourceTown);
		
		int totalPathCost = destinationTown.getPathCost(); 
		
		/*
		 * here I am putting all the vertices from end to start into 
		 * a stack so I can access them in reverse order for printing 
		 * [sourceVertex to endVertex] 
		 */
		Stack<Town> townStack = new Stack<>(); 
		Town currentTown = destinationTown; 
		if(destinationTown.getPreviousTown() != null) //CHANGED THIS DURING GUI TESTING IN CASE THIS BREAKS THE ALGORITM SOME HOW
			townStack.push(currentTown); 
		
		while(currentTown.getPreviousTown() != null) {
			currentTown = currentTown.getPreviousTown(); 
			townStack.push(currentTown); 
		}
		
		ArrayList<String> resultList = new ArrayList<String>(); 
		
		
		/*
		 * CHANGED BELOW DURING GUI TESTING AS WELL IN CASE THIS BREAKS THE PROGRAM 
		 */
		if(!townStack.isEmpty())
			currentTown = townStack.pop(); //reusing this variable b/c I can't come up with a similar name lol
		while(!townStack.isEmpty()) {
			Town nextTown = townStack.pop(); 
			Road roadToNextTown = currentTown.getRoadTo(nextTown); 
			
			String str = currentTown.getName() + " via " + roadToNextTown.getName() + " to " 
							+ nextTown.getName() + " " + roadToNextTown.getWeight() + " mi"; 
			
			resultList.add(str); 
			
			currentTown = nextTown; 
		}
		return resultList;
	}

	@Override
	public void dijkstraShortestPath(Town sourceTown) {
		initializeDijkstra();
		PriorityQueue<EntryPQ> pQueue = new PriorityQueue<>(); 
		
		/*
		 * get real source vertex stored in the vertices dictionary 
		 */
		sourceTown = vertices.get(sourceTown.getName());
		
		/*
		 * if sourceTown is null throw illegal argument exception b/c 
		 * town was not found in vertices dictionary 
		 */
		if(sourceTown == null) 
			throw new IllegalArgumentException(); 
		/*
		 *Pass EntryPQ object for origin town
		 */
		pQueue.add(new EntryPQ(sourceTown, 0, null)); 
		
		while(!pQueue.isEmpty()) {
			EntryPQ frontEntry = pQueue.remove(); 
			Town frontTown = frontEntry.getSourceTown(); 
			
			
			if(!frontTown.isVisited()) {
				frontTown.visit();
				frontTown.setPathCost(frontEntry.getPathCost());
				frontTown.setPreviousTown(frontEntry.getPreviousTown());
				
				/*
				 * get frontTown's road iterator to iterate through neighbors
				 */
				Iterator<Road> frontTownRoadIterator = frontTown.getRoadIterator(); 
				while(frontTownRoadIterator.hasNext()) {
					Road nextRoad = frontTownRoadIterator.next();
					Town nextNeighbor = nextRoad.getDestination(); 
					int weightOfEdgeToNeighbor = nextRoad.getWeight(); 
					if(!nextNeighbor.isVisited()) {
						int nextCost = weightOfEdgeToNeighbor + frontTown.getPathCost(); 
						pQueue.add(new EntryPQ(nextNeighbor, nextCost, frontTown)); 
					}
				}
			}
		}
		
		
	}
	
	
	private void initializeDijkstra() {
		Iterator<Town> townIterator = vertices.values().iterator(); 
		
		while(townIterator.hasNext()) {
			Town currentTown = townIterator.next(); 
			currentTown.unVisit(); 
			currentTown.setPathCost(Integer.MAX_VALUE);
		} 
	}
	
	public Collection<Town> getTownCollection() {
		return vertices.values(); 
	}
	
	public boolean removeRoadNoWeight(String sourceTownStr, String destinationTownStr) {
		Town sourceTown = vertices.get(sourceTownStr);
		Town destinationTown = vertices.get(destinationTownStr); 
		
		return sourceTown.deleteRoadTo(destinationTown) && destinationTown.deleteRoadTo(sourceTown); 
	}
	
	
	
	
	private class EntryPQ implements Comparable<EntryPQ> {
		private Town sourceTown; 
		private int pathCost; 
		private Town previousTown; 
		
		private EntryPQ(Town source, int cost, Town previous) {
			this.sourceTown = source; 
			this.pathCost = cost; 
			this.previousTown = previous; 
		}

		@Override
		public int compareTo(EntryPQ otherEntry) {
			int returnValue = -111111111; 
			if(this.pathCost > otherEntry.pathCost)
				returnValue = 1; 
			else if(this.pathCost == otherEntry.pathCost)
				returnValue = 0; 
			else if(this.pathCost < otherEntry.pathCost)
				returnValue = -1;
			
			return returnValue; 
		}
		
		public Town getSourceTown() {
			return this.sourceTown; 
		}
		
		public int getPathCost() {
			return this.pathCost; 
		}
		
		public Town getPreviousTown() {
			return this.previousTown; 
		}
		
	}
	
	
	
}
