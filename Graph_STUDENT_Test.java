import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Graph_STUDENT_Test {
	private Graph graph; 
	@BeforeEach
	void setUp() throws Exception {
		graph = new Graph(); 
		
		Town town = new Town("Cali"); 
		graph.addVertex(town);
		
		town = new Town("Chi"); 
		graph.addVertex(town);
		
		town = new Town("Tex"); 
		graph.addVertex(town);
		
		town = new Town("Ten"); 
		graph.addVertex(town);
		
		town = new Town("Verm"); 
		graph.addVertex(town);
		
		town = new Town("Miam"); 
		graph.addVertex(town);
		
		
		
		
		Town sTown = new Town("Cali");
		Town eTown = new Town("Chi"); 
		graph.addEdge(sTown, eTown, 78, "CaliChi"); 
		
		sTown = new Town("Cali");
		eTown = new Town("Tex"); 
		graph.addEdge(sTown, eTown, 166, "CaliTex"); 
		
		sTown = new Town("Tex");
		eTown = new Town("Chi"); 
		graph.addEdge(sTown, eTown, 295, "TexChi"); 
		
		sTown = new Town("Chi");
		eTown = new Town("Ten"); 
		graph.addEdge(sTown, eTown, 120, "ChiTen"); 
		
		sTown = new Town("Chi");
		eTown = new Town("Verm"); 
		graph.addEdge(sTown, eTown, 272, "ChiVerm"); 
		
		sTown = new Town("Ten");
		eTown = new Town("Verm"); 
		graph.addEdge(sTown, eTown, 65, "TenVerm"); 
		
		sTown = new Town("Tex");
		eTown = new Town("Miam"); 
		graph.addEdge(sTown, eTown, 148, "TexMiam"); 
		
		
	}

	@AfterEach
	void tearDown() throws Exception {
		graph = null;
	}


	@Test
	void testGetTown() {
		/*
		 * I want the getTown method to return the town with its edgelist data 
		 */
		
		assertTrue(graph.getTown(new Town("Chi")) != null);
		
		/*
		 * obtain the town object for Miam, get the road to texas, and check if the road from texas to miami [vice versa] 
		 * has a weight of 148
		 */
		assertTrue(graph.getTown(new Town("Miam")).getRoadTo(new Town("Tex")).getWeight() == 148); 
	}

	@Test
	void testGetEdge() {
		assertTrue(graph.getEdge(new Town("Cali"), new Town("Tex")).getName().equals("CaliTex"));
		assertTrue(graph.getEdge(new Town("Tex"), new Town("Chi")).getWeight() == 295); 
	}

	@Test
	void testAddEdge() {
		/*
		 * I have effectively tested this through set up and other tests so I will just add 
		 * another one and test that instead of testing existing edges
		 */
		
		graph.addEdge(new Town("Miam"), new Town("Verm"), 151, "MiamVerm"); 
		
		assertTrue(graph.containsEdge(new Town("Miam"), new Town("Verm"))); 
		assertTrue(graph.getEdge(new Town("Miam"), new Town("Verm")).getWeight() == 151); 
	}

	@Test
	void testAddVertex() {
		/*
		 * again already tested by setup, other tests, and manager test but I will do a bit more
		 * 
		 */
		
		graph.addVertex(new Town("Deli")); 
		assertTrue(graph.vertexSet().toString().contains("Deli")); 
	}

	@Test
	void testContainsEdge() {
		/*
		 * tested before but will do more
		 */
		assertFalse(graph.containsEdge(new Town("Ten"), new Town("Cali"))); 
		assertTrue(graph.containsEdge(new Town("Tex"), new Town("Cali"))); 
	}

	@Test
	void testContainsVertex() {
		assertTrue(graph.containsVertex(new Town("Verm"))); 
		assertFalse(graph.containsVertex(new Town("Maim"))); 
	}

	@Test
	void testEdgeSet() {
		assertTrue(graph.edgeSet().toString().equals("[TexChi, TenVerm, TexMiam, ChiVerm, CaliTex, ChiTen, CaliChi]")); 
	}

	@Test
	void testEdgesOf() {
		
		assertTrue(graph.edgesOf(new Town("Chi")).toString().equals("[TexChi, ChiVerm, ChiTen, CaliChi]")); 
		assertTrue(graph.edgesOf(new Town("Verm")).toString().equals("[TenVerm, ChiVerm]")); 
	}

	@Test
	void testRemoveEdge() {
		graph.removeEdge(new Town("Tex"), new Town("Miam"), 148, "TexMiam"); 
		assertTrue(graph.edgeSet().toString().equals("[TexChi, TenVerm, ChiVerm, CaliTex, ChiTen, CaliChi]"));
		assertTrue(graph.vertexSet().toString().equals("[Verm, Cali, Chi, Tex, Ten, Miam]")); 
	}

	@Test
	void testRemoveVertex() {
		graph.removeVertex(new Town("Chi")); 
		assertTrue(graph.edgeSet().toString().equals("[TenVerm, TexMiam, CaliTex]")); 
		assertTrue(graph.vertexSet().toString().equals("[Verm, Cali, Tex, Ten, Miam]"));
	}

	@Test
	void testVertexSet() {
		/*
		 * i've tested this extensively throughout other tests but I will test it again 
		 */
		
		Set<Town> townSet = graph.vertexSet(); 
		
		assertTrue(townSet.size() == 6); 
		assertTrue(townSet.toString().equals("[Verm, Cali, Chi, Tex, Ten, Miam]")); 
		
		
	}

	@Test
	void testShortestPath() {
		assertTrue(graph.shortestPath(new Town("Cali"), new Town("Miam")).toString().equals("[Cali via CaliTex to Tex 166 mi, Tex via TexMiam to Miam 148 mi]"));
		assertTrue(graph.getTown(new Town ("Miam")).getPathCost() == 314);
		
		
		assertTrue(graph.shortestPath(new Town("Ten"), new Town("Miam")).toString().equals("[Ten via ChiTen to Chi 120 mi, Chi via CaliChi to Cali 78 mi, Cali via CaliTex to Tex 166 mi, Tex via TexMiam to Miam 148 mi]"));
		assertTrue(graph.getTown(new Town ("Miam")).getPathCost() == 512);
	}

	@Test
	void testDijkstraShortestPath() {
		/*
		 * this is tested via shortest path test no reason to do another.
		 */
		assertTrue(graph.getTown(new Town ("Miam")).getPathCost() == 2147483647);
		assertTrue(graph.getTown(new Town ("Miam")).getPreviousTown() == null);
		assertTrue(graph.getTown(new Town ("Cali")).getPathCost() == 2147483647);
		assertTrue(graph.getTown(new Town ("Cali")).getPreviousTown() == null);
		assertTrue(graph.getTown(new Town ("Verm")).getPathCost() == 2147483647);
		assertTrue(graph.getTown(new Town ("Verm")).getPreviousTown() == null);
		
		graph.dijkstraShortestPath(new Town("Chi"));
		assertTrue(graph.getTown(new Town ("Verm")).getPathCost() == 185);
		assertTrue(graph.getTown(new Town ("Cali")).getPathCost() == 78);
		
	}

	@Test
	void testGetTownCollection() {
		assertTrue(graph.getTownCollection().toString().equals("[Verm, Cali, Chi, Tex, Ten, Miam]")); 
	}

	@Test
	void testRemoveRoadNoWeight() {
		graph.removeRoadNoWeight("Cali", "Chi"); 
		assertTrue(graph.edgeSet().toString().equals("[TexChi, TenVerm, TexMiam, ChiVerm, CaliTex, ChiTen]"));
	}

}
