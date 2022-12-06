import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ConsoleTesting {
	public static void main(String[] args) throws FileNotFoundException {
		Graph graph = new Graph(); 
		
		File input = new File("MD Towns(1).txt");
		Scanner fileScanner = new Scanner(input);
		
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
			
			
			/*
			Scanner lineScanner = new Scanner(fileScanner.next()); 
			lineScanner.useDelimiter("(,|;)"); 
			while(lineScanner.hasNext()) {
				String roadName = lineScanner.next(); 
				int weight = Integer.parseInt(lineScanner.next());  
				String sourceTownString = lineScanner.next(); 
				String destinationTownString = lineScanner.next(); 
				
				Town sourceTown = new Town(sourceTownString); 
				Town destinationTown = new Town(destinationTownString); 
				
				graph.addVertex(sourceTown);
				graph.addVertex(destinationTown);
				
				graph.addEdge(sourceTown, destinationTown, weight, roadName); 
				
			*/
				
			}
			System.out.println(graph.vertexSet()); 
			System.out.println(graph.edgeSet()); 
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		  TownGraphManager graph = new TownGraphManager();
//		  String[] town = new String[12];
//		  
//		  for (int i = 1; i < 12; i++) {
//			  town[i] = "Town_" + i;
//			  graph.addTown(town[i]);
//		  }
//		  
//		  graph.addRoad(town[1], town[2], 2, "Road_1");
////		  graph.graph.addEdge(new Town(town[1]), new Town(town[2]), 2, "Road_1"); 
//		  graph.addRoad(town[1], town[3], 4, "Road_2");
//		  graph.addRoad(town[1], town[5], 6, "Road_3");
//		  graph.addRoad(town[3], town[7], 1, "Road_4");
//		  graph.addRoad(town[3], town[8], 2, "Road_5");
//		  graph.addRoad(town[4], town[8], 3, "Road_6");
//		  graph.addRoad(town[6], town[9], 3, "Road_7");
//		  graph.addRoad(town[9], town[10], 4, "Road_8");
//		  graph.addRoad(town[8], town[10], 2, "Road_9");
//		  graph.addRoad(town[5], town[10], 5, "Road_10");
//		  graph.addRoad(town[10], town[11], 3, "Road_11");
//		  graph.addRoad(town[2], town[11], 6, "Road_12");
//		
//		  
////		  graph.graph.dijkstraShortestPath(new Town(town[1]));
////		  
////		  Iterator<Road> sourceRoadIterator = graph.graph.getTown(new Town(town[6])).getRoadIterator(); 
////		  while(sourceRoadIterator.hasNext()) {
////			  Road nextRoad = sourceRoadIterator.next(); 
////			  System.out.println(nextRoad.getWeight() + " miles to " + nextRoad.getDestination()); 
////		  }
//		  
//		  ArrayList<String> resultList = graph.graph.shortestPath(new Town(town[1]), new Town(town[3])); 
//		  System.out.println(graph.graph.getTown(new Town(town[2])).getPathCost());
//		  for(String str : resultList)
//			  System.out.println(str);
////		  
////		  System.out.println("\n\n\n\n\n\n"); 
////		  
////		  ArrayList<String> resultList2 = graph.getPath(town[1],town[11]);
////		  ArrayList<String> resultList2 = graph.graph.shortestPath(new Town(town[1]), new Town(town[11]));
////		  System.out.println(resultList2.size());
////		  System.out.println(graph.graph.getTown(new Town(town[11])).getPathCost());
////		  for(String str : resultList2)
////			  System.out.println(str);
//		  
//		//  System.out.println(graph.graph.getTown(new Town(town[3])).getPreviousTown()); 
}
