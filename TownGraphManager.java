import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class TownGraphManager implements TownGraphManagerInterface{
	private Graph graph; 
	
	public TownGraphManager() {
		graph = new Graph(); 
	}
	public boolean addRoad(String town1, String town2, int weight, String roadName) { 
		/*
		 * ASSUMPTION: both towns have already been added
		 */
		Town sourceTown = new Town(town1); 
		Town destinationTown = new Town(town2); 
		
		boolean added = true;
		try {
			added = graph.addEdge(sourceTown, destinationTown, weight, roadName) != null; 
			
		} catch(Exception e) {
			added = false; 
		}
		
		return added; 
	}
	
	public String getRoad(String town1, String town2) {
		Town sourceTown = new Town(town1); 
		Town destinationTown = new Town(town2); 
		
		Road resultRoad = graph.getEdge(sourceTown, destinationTown); 
		
		return resultRoad == null ? null : resultRoad.toString(); 
	}
	
	public boolean addTown(String townName) {
		Town town = new Town(townName); 
		return graph.addVertex(town); 
		
	}
	
	public Town getTown(String name) {
		Town town = new Town(name); 
		return graph.getTown(town); 
	}
	
	public boolean containsTown(String townName) {
		Town town = new Town(townName); 
		return graph.containsVertex(town); 
	}
	
	public boolean containsRoadConnection(String town1, String town2) {
		Town sourceTown = new Town(town1);
		Town destinationTown = new Town(town2);
		
		return graph.containsEdge(sourceTown, destinationTown); 
	}
	
	public ArrayList<String> allRoads(){
		ArrayList<String> resultList = new ArrayList<>();
		
		Set<Road> roadSet = graph.edgeSet(); 
		
		Iterator<Road> roadIterator = roadSet.iterator(); 
		
		while(roadIterator.hasNext())
			resultList.add(roadIterator.next().toString()); 
		
		Collections.sort(resultList);
		
		return resultList;
	}
	
	public boolean deleteRoadConnection(String town1, String town2, String Road) {
		/*
		 * this does not account for weight? but the method within the graph class does?? but the equals method's 
		 * specification within the road class java doc does not account for weight?? 
		 * 
		 * does this project expect us to do all of the above simultaneously? Who is creating these projects???? 
		 * 
		 * ASSUMPTION: weight does not matter and there can only be one road between any two towns. 
		 */
		
		/*
		 * ???????????? How am I supposed to program this? The internal graph method requires a weight 
		 * and utilizes the weight to verify equality but this method omits the weight? but includes the 
		 * road name? I would need to make a new method just to delete a road connection in this manner and 
		 * maybe even change the equality definition. 
		 * 
		 * I will simply make a new method but these leaves the other road deletion method completely unused.
		 * 
		 */
		
		return graph.removeRoadNoWeight(town1, town2); 
	}

	
	public boolean deleteTown(String townName) {
		Town town = new Town(townName); 
		return graph.removeVertex(town); 
	}

	
	public ArrayList<String> allTowns() {
		Iterator<Town> townIterator = graph.getTownCollection().iterator(); 
		ArrayList<String> resultList = new ArrayList<>(); 
		
		while(townIterator.hasNext()) {
			resultList.add(townIterator.next().getName()); 
		}
		
		Collections.sort(resultList);
		
		return resultList; 
	}

	
	public ArrayList<String> getPath(String town1, String town2) {
		Town townOne = new Town(town1); 
		Town townTwo = new Town(town2); 
		
		return graph.shortestPath(townOne, townTwo);
	}

	@Override
	public void populateTownGraph(File file) throws FileNotFoundException, IOException {
		/*
		 * ASSUMPTION: text files will be formatted as seen in the text files. The road name 
		 * cannot have a space while the town names can have spaces 
		 */
		
		Scanner fileScanner = new Scanner(file);
		
		while(fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine(); 
			
			String roadName = line.substring(0, line.indexOf(',')); 
			
			int weight = Integer.parseInt(line.substring(line.indexOf(',') + 1, line.indexOf(';'))); 
			
			/*
			 * update line string
			 */
			line = line.substring(line.indexOf(';') + 1); 
			
			String sourceTownString = line.substring(0, line.indexOf(';')); 
			String destinationTownString = line.substring(line.indexOf(';') + 1); 
			
			Town sourceTown = new Town(sourceTownString); 
			Town destinationTown = new Town(destinationTownString); 
			
			graph.addVertex(sourceTown);
			graph.addVertex(destinationTown);
			
			graph.addEdge(sourceTown, destinationTown, weight, roadName); 
		
		}
		
	}
	
	
	
	
}
